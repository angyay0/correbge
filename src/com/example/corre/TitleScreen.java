package com.example.corre;

import java.util.List;

import balam.games.Library.Engine;
import balam.games.canvas.Graphics.Format;
import balam.games.skeleton.GameSkeleton;
import balam.games.skeleton.Input.TouchEvent;
import balam.games.skeleton.Screen;

public class TitleScreen extends Screen {

	public TitleScreen(GameSkeleton game) {
		super(game);
		Resources.mainfx.play();
		Resources.play_pause = game.getGraphics().newCImage("more/play_pause.png",Format.RGB565 );
		Resources.player = game.getGraphics().newCImage("personaje.png",Format.RGB565 );
		Resources.pause = game.getGraphics().newCImage("more/pause.png",Format.RGB565 );
		Resources.social = game.getGraphics().newCImage("more/social.png",Format.RGB565 );
		Resources.bird = game.getGraphics().newCImage("enemies/ave.png",Format.RGB565 );
		Resources.rock = game.getGraphics().newCImage("enemies/roca_1.png",Format.RGB565 );
		Resources.fire = game.getGraphics().newCImage("enemies/fogata_5.png",Format.RGB565 );
		Resources.npfs = game.getGraphics().newCImage("more/npfs.png",Format.RGB565);
	}

	@Override
	public void pause() {}

	
	@Override
	public void present(float arg0) {
		game.getGraphics().clear(0);
		game.getGraphics().drawCImage(Resources.background[0], 0,0);
		game.getGraphics().drawCImage(Resources.title, 200, 200);
		game.getGraphics().drawCImage(Resources.play,1000,200);
		game.getGraphics().drawCImage(Resources.exit,0,game.getGraphics().getHeight()-Resources.exit.getHeight());
	}

	@Override
	public void release() {
		Resources.mainfx.stop();
	}

	@Override
	public void resume() {}

	@Override
	public void update(float arg0) {
		TouchEvent touch;
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		for(int i=0;i<touchEvents.size();i++){
			touch = touchEvents.get(i);
			if( touch.type == TouchEvent.TOUCHDOWN ){
				if( Engine.touchOnBounds(touch,0,game.getGraphics().getHeight()-Resources.exit.getHeight(),
						Resources.exit.getWidth(),Resources.exit.getHeight())){
					System.exit(0);
				}else if( Engine.touchOnBounds(touch,1000,200,Resources.play.getWidth(),Resources.play.getHeight())){
					game.setScreen( new MainGameScreen(game) );
				}
				
			}
		}
	}
	
	

}
