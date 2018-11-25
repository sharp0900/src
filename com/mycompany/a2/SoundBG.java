package com.mycompany.a2;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class SoundBG implements Runnable{

	private Media m;
	public SoundBG(String file) {
		try{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+ file);
			m = MediaManager.createMedia(is, "audio/mp3", this);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void pause() {
		m.pause();
	}
	
	public void play() {
		m.play();
	}
	
	
	@Override
	public void run() {
		m.setTime(0);
		m.play();	
	}
	

}
