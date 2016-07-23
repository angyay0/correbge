package com.example.corre;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.net.Uri;
import balam.games.Library.Engine;
import balam.games.skeleton.GameGraphics;
import balam.games.skeleton.GameSkeleton;
import balam.games.skeleton.Input.TouchEvent;
import balam.games.skeleton.Screen;

public class MainGameScreen extends Screen {
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	
	private int controlS = 0;
	private int controlS2 = 1920;
	private float timeA,timeB,timeC,timeD;
	private float timeE,timeF;
	private boolean paused = false;
	private boolean lose = false;
	boolean one = true;
	private double seed = 0.00034;
	private double plusSeed = 0.00005;
	private int px = 50;
	private int py = 650;
	private Random rnd = new Random();
	
	public MainGameScreen(GameSkeleton game) {
		super(game);
		Resources.feel.setLooping(true);
		Resources.feel.play();
		timeA = (float) (System.nanoTime()/1000000000.0);
		timeC = timeA;
		timeE = timeA;
		
		for(int i=0;i<1;i++){
			enemies.add( new Enemy(1920,680,Resources.rock.getWidth(),Resources.rock.getHeight(),2) );
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void present(float delta) {
		if( !paused && !lose ){
			timeB = (float) (System.nanoTime()/1000000000.0);
			timeD = timeB;
		
			if( timeB-timeA > 0.1 ){
				timeA = timeB;
			
				controlS  -= 10;
				controlS2 -= 10;
			}
		
			if( controlS < -1920 ) controlS = 1920;
			if( controlS2 < -1920 ) controlS2 = 1920;
			
		}
		drawEnemies();
		animateWalk(game.getGraphics(),(timeD-timeC));
		checkCollision(timeD-timeC);
	}

	private void drawEnemies() {
		for(Enemy e: enemies){
			game.getGraphics().drawCImage(Resources.rock, e.X, e.Y);
		}
		
	}

	@Override
	public void release() {
		if( Resources.feel.isPlaying() )
			Resources.feel.stop();
	}

	@Override
	public void resume() {      
	}

	@Override
	public void update(float delta) {
		timeF = (float) (System.nanoTime()/1000000000.0);
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		
		
		for( TouchEvent te: touchEvents ){
			if( te.type == TouchEvent.TOUCHUP ){
				if( Engine.touchOnBounds(te, 900,0,Resources.pause.getWidth(),Resources.pause.getHeight())){
					paused = !paused;
				}else if( Engine.touchOnBounds(te,800,600,200,200) && paused){
					share();
				}else if( Engine.touchOnBounds(te,0,100,1920,1200)){
					doJump((float) 0.35);
				}
				
				Resources.pop.play();
			}
		}
		
		
	}
	
	private void animateWalk(GameGraphics g,float tiker){
		adjustRender();
		if( !paused && !lose ){
			if( tiker < 0.20 ){
				g.drawCImage(Resources.player,px,py,340,112,0,250);
			}else if( tiker > 0.10){
				g.drawCImage(Resources.player,px,py,684,112,250,250);
			}
			
			if( tiker > 0.30 )
				timeC = timeD;
		}else
			g.drawCImage(Resources.player,px,py,684,112,250,250);
	}
	
	private void adjustRender(){
		game.getGraphics().clear(0);
		if( !lose ){
			game.getGraphics().drawCImage(Resources.background[0],controlS,0);
			game.getGraphics().drawCImage(Resources.background[1],controlS2,0);
			
			if( !paused )
				game.getGraphics().drawCImage(Resources.pause,900,0);
			else{
				game.getGraphics().drawCImage(Resources.play_pause,900,0);
				game.getGraphics().drawCImage(Resources.npfs,0,0);
				game.getGraphics().drawCImage(Resources.social,800,600,198,200,206,200);
			}
		}else{
			game.getGraphics().drawCImage(Resources.background[0],0,0);
			//game.getGraphics().drawCImage(Resources.lose,600,200);
		}
		
		
	}
	
	private void share(){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.facebook.com/sharer.php?u=https://www.facebook.com/pages/Ent/1662410670650851&t=Corre Game Powered By QCTL Engine&s=Estoy jugando Ent,¡Unete!"));
		game.getActivity().startActivity(intent);
	}
	
	private void checkCollision(float delta){
		Enemy e;
		for(int i=0;i<enemies.size();i++){
			e = enemies.get(i);
			if( (px > e.X && px < e.X+(e.W/2) && py < e.Y-25) || ( px > e.X && py < e.Y-25) ){
				lose = true;
				break;
			}
		}
		
		for( int i=0;i<enemies.size();i++ ){
			e = enemies.get(i);
			if( e.X < (-e.W) )
				enemies.remove();
			else{
				if( delta > 0.2 && delta < 0.25 )
					e.X -= 40;
			}
				
		}
	}

	private void doJump(float ticker){
		if( timeF-timeE > ticker/3 && timeF-timeE < (ticker/3)*2 ){
			if( one ){
				py -= 200;
				one = false;
			}
		}else if( timeF-timeE > ticker ){
			if( !one ){
				py += 200;
				one = true;
			}
			timeE = timeF;
		}
		
		
		
	}
}
