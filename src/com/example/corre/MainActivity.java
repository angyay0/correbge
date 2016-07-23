package com.example.corre;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import balam.games.ready.Game;
import balam.games.skeleton.Screen;


public class MainActivity extends Game {
	
	@Override
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
	

	
}