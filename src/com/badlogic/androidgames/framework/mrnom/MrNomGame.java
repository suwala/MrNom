package com.badlogic.androidgames.framework.mrnom;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame{
	
	@Override
	public Screen getStartScreen(){
		return new LoadingScreen(this);
	}

}
