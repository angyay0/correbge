package com.example.corre;

import balam.games.Library.Engine;
import balam.games.canvas.Graphics.Format;
import balam.games.skeleton.GameGraphics;
import balam.games.skeleton.GameSkeleton;
import balam.games.skeleton.Screen;

public class LoadingScreen extends Screen {
	private float timeA,timeB,diference;
	
	public LoadingScreen(GameSkeleton game) {
		super(game);
		Resources.loading = game.getGraphics().newCImage("more/load.png", Format.RGB565 );
		Resources.background[0] = game.getGraphics().newCImage("scenes/Scene.jpg", Format.RGB565 );
		timeA = (float) (System.nanoTime()/1000000000.0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void present(float delta) {
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float delta) {
		timeB = (float) (System.nanoTime()/1000000000.0);
		diference = timeB-timeA;
		
		clearnewload(game.getGraphics());

		if( (diference) > 0.45 ){
			game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,36,159,36,165 );
			if( (diference) > 0.90 ){
				clearnewload(game.getGraphics());
				game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,195,159,36,165 );
				if( (diference) > 1.35 ){
					clearnewload(game.getGraphics());
					game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,354,159,36,165 );
					if( (diference) > 1.80 ){
						clearnewload(game.getGraphics());
						game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,513,159,36,165 );
						if( (diference) > 2.15 ){
							clearnewload(game.getGraphics());
							game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,672,156,36,165 );
							if( (diference) > 2.60 ){
								clearnewload(game.getGraphics());
								game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,831,156,36,165 );
								if( (diference) > 3.05 ){
									clearnewload(game.getGraphics());
									game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,987,159,36,165 );
									if( (diference) > 3.50 ){
										clearnewload(game.getGraphics());
										game.getGraphics().drawCImage( Resources.loading,(game.getGraphics().getWidth()/2)-20,(game.getGraphics().getHeight()/2)-40,1149,159,36,165 );
										loadElements();
										if( (diference) > 4 ){
											Engine.vibrateDevice( 250, game.getActivity() );
											if( (diference) > 4.50 )
												game.setScreen( new TitleScreen(game) );
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void clearnewload(GameGraphics gameGraphics){
		gameGraphics.clear(0);
		gameGraphics.drawCImage(Resources.background[0], 0, 0);
	}
	
	private void loadElements(){
		Resources.title = game.getGraphics().newCImage("title.png", Format.RGB565);
		Resources.background[1] = game.getGraphics().newCImage("scenes/Scene7.jpg",Format.RGB565 );
		Resources.play = game.getGraphics().newCImage("more/play.png",Format.RGB565 );
		Resources.exit = game.getGraphics().newCImage("more/exit_3.png",Format.RGB565 );
		Resources.mainfx = game.getMedia().newMusic("sound/mainfx.mp3");
		Resources.pop = game.getMedia().newMusic("sound/pop.mp3");
		Resources.feel = game.getMedia().newMusic("sound/feel.mp3");
	}

}