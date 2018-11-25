package com.mycompany.a2;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class SoundMissile{

	private Media m;
	public SoundMissile(String file) {
		try{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+ file);
			m = MediaManager.createMedia(is, "audio/wav");
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

}
