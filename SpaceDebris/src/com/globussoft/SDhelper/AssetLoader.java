package com.globussoft.SDhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture;
	public static TextureRegion bg, grass, logo,points,bg1,bg2,bg3;
	public static TextureAtlas atlas, atlas1, bgatlas, manatlas,ImagePackV2,startatlas,superastronut_atlas,newbgatlas;
	
	public static TextureRegion man, spacedebrislogo, Debris0, Debris1,
			Debris2, Debris3, Debris4, button,retry,gameover,tap,star,levelBackground,bigstar,superpower;
 
	
	public static Sound fly, dead, coin,power,lifeover;
	public static Music playing_sound;
	public static BitmapFont font, shadow,levelfont,levelbigfont,lifefont,extralifefont,blackfont,numberfont,numberfont1;
	public static Preferences prefs;

	public static TextureRegion superpower1,superpower2,superpower3,superpower4,superpower5,superpower6,superpower7,superpower8;
	public static Animation supermanAnimation1,supermanAnimation2;
	
	public static void load() {

		superastronut_atlas=new TextureAtlas(Gdx.files.internal("data/superpower.atlas"));
		
		superpower1=superastronut_atlas.findRegion("superpower _anim1");
		superpower1.flip(false, true);
		
		superpower2=superastronut_atlas.findRegion("superpower _anim2");
		superpower2.flip(false, true);
		
		superpower3=superastronut_atlas.findRegion("superpower _anim3");
		superpower3.flip(false, true);
		
		superpower4=superastronut_atlas.findRegion("superpower _anim4");
		superpower4.flip(false, true);
		
		superpower5=superastronut_atlas.findRegion("superpower_anim5");
		superpower5.flip(false, true);
		
		superpower6=superastronut_atlas.findRegion("superpower_anim6");
		superpower6.flip(false, true);
		
		superpower7=superastronut_atlas.findRegion("superpower_anim7");
		superpower7.flip(false, true);
		
		superpower8=superastronut_atlas.findRegion("superpower_anim8");
		superpower8.flip(false, true);

		
		
		
		TextureRegion[] animation1={superpower1,superpower2,superpower3,superpower4};
		supermanAnimation1 = new Animation(0.1f, animation1);
		supermanAnimation1.setPlayMode(Animation.LOOP_PINGPONG);
		
		TextureRegion[] animation2={superpower5,superpower6,superpower7,superpower8};
		supermanAnimation2 = new Animation(0.06f, animation2);
		supermanAnimation2.setPlayMode(Animation.LOOP_PINGPONG);
		
		superpower=superastronut_atlas.findRegion("superpower");
		superpower.flip(false, true);

		
		startatlas=new TextureAtlas(Gdx.files.internal("data/star.atlas"));
		star=startatlas.findRegion("star_2_small");
		
		bigstar=startatlas.findRegion("Bigstar");
		
		ImagePackV2=new TextureAtlas(Gdx.files.internal("data/ImagePackV2"));
		man = ImagePackV2.findRegion("astronautplay_hdpi");
		man.flip(false, true);
		
		tap=ImagePackV2.findRegion("tap_hdpi");
		tap.flip(false, true);
		
		retry=ImagePackV2.findRegion("retry_hdpi");
		retry.flip(false, true);
		
		levelBackground=ImagePackV2.findRegion("levelbg_hdpi");
		
		
		bgatlas = new TextureAtlas(Gdx.files.internal("data/Background"));
		bg = bgatlas.findRegion("Background");
		bg.flip(false, true);

		newbgatlas = new TextureAtlas(Gdx.files.internal("data/newbg.atlas"));
		bg1 = newbgatlas.findRegion("bg1");
		bg1.flip(false, true);
		bg2 = newbgatlas.findRegion("bg2");
		bg2.flip(false, true);
		bg3 = newbgatlas.findRegion("bg3");
		bg3.flip(false, true);
		// obstracle
		Debris0 = ImagePackV2.findRegion("Debris0_hdpi");
		Debris1 = ImagePackV2.findRegion("Debris1_hdpi");
		Debris2 = ImagePackV2.findRegion("Debris2_hdpi");
		Debris3 = ImagePackV2.findRegion("Debris3_hdpi");
		Debris4 = ImagePackV2.findRegion("Debris4_hdpi");

		points=ImagePackV2.findRegion("+10_hdpi");
		points.flip(false, true);
		// mainmanu spacedebris icon

		loadExtraLifescreen();

		
		atlas1 = new TextureAtlas(Gdx.files.internal("data/splash"));
				
		logo = atlas1.findRegion("Splashscreen");
		
		
		
		//gameover screen
		
		gameover=ImagePackV2.findRegion("gameover_hdpi");
		gameover.flip(false, true);
	// Sound
		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.ogg"));
		fly = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		power=Gdx.audio.newSound(Gdx.files.internal("data/power.ogg"));
        playing_sound=Gdx.audio.newMusic(Gdx.files.internal("data/SpaceLong.mp3"));
		lifeover=Gdx.audio.newSound(Gdx.files.internal("data/lifeover.ogg"));
		
  
    lifefont=new BitmapFont(Gdx.files.internal("data/fontfinal.fnt"));
    lifefont.setScale(.75f, -.75f);
    
    numberfont=new BitmapFont(Gdx.files.internal("data/timenumber.fnt"));
    numberfont.setScale(.3f, -.3f);
    
  /*  numberfont1=new BitmapFont(Gdx.files.internal("data/XXXXXX.fnt"));
    numberfont1.setScale(.3f, -.3f);*/
    
	levelfont= new BitmapFont(Gdx.files.internal("data/text.fnt"));
	levelfont.setScale(.25f, .25f);
		levelbigfont=new BitmapFont(Gdx.files.internal("data/text.fnt"));
		levelbigfont.setScale(0.75f,0.75f);
		
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

	
		
		blackfont=new BitmapFont(Gdx.files.internal("data/blackfont.fnt"));
	    blackfont.setScale(1f,1f);
		
		
		prefs = Gdx.app.getPreferences("SpaceDebris");
		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
		
		if (!prefs.contains("level")) {
			prefs.putInteger("level", 1);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static void setLevel(int levelcleared)
	{
		try {
			prefs.putInteger("level", levelcleared);
			prefs.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void setIsLife(boolean flag)
	
	{
	
		prefs.putBoolean("islife", flag);
	}
	
	public static boolean getisLifeAlive()
	{
		return prefs.getBoolean("islife");
	}
	
	public static int getLevel() {
		return prefs.getInteger("level");
	}
	
	public static void setLifeBackTimer(String timer)
	{
		prefs.putString("time", timer);
		prefs.flush();	
	}
	
	public static String getLifeBackTimer() {
		return prefs.getString("time");
	}
	
	public static void setExtraLife(int life)
	{
		prefs.putInteger("life", life);
		prefs.flush();	
	}
	
	public static int getExtraLife() {
		return prefs.getInteger("life");
	}

	public static void setIsRunFirst(Boolean bol)
	{
		prefs.putBoolean("first", bol);
		prefs.flush();	
	}
	
	
	public static Boolean isRunFirstTime()
	{
		return prefs.getBoolean("first");
		
	}
	
	public static void setmusicon(Boolean bol)
	{
		prefs.putBoolean("music", bol);
		prefs.flush();	
	}
	
	
	public static Boolean ismusicon()
	{
		return prefs.getBoolean("music",true);
		
	}
	
	public static void setSystemTime(long val) 
	{
		prefs.putLong("systime", val);
		
		prefs.flush();
	}

	public static long getSystemTime() {
		return prefs.getLong("systime",0);
	}
	
	public static void setCountdownTime(long val) {
		prefs.putLong("countdown", val);
		
		prefs.flush();
	}

	public static long getCountdownTime() {
		return prefs.getLong("countdown");
	}
	
	public static void loadExtraLifescreen(){
		
		extralifefont=new BitmapFont(Gdx.files.internal("data/terrorprofont.fnt"));
		extralifefont.setScale(1.5f, 1.5f);
	}
	
	public static void disposeExtraLifescreen(){
		extralifefont.dispose();
	}
	
	public static void dispose() {
		// We must dispose of the texture when we are finished.
		 disposeExtraLifescreen();
		atlas1.dispose();
		dead.dispose();
		power.dispose();
		fly.dispose();
		coin.dispose();
		font.dispose();
	    shadow.dispose();
	    blackfont.dispose();
	    lifefont.dispose();
	    ImagePackV2.dispose();
        numberfont.dispose();
        playing_sound.dispose();
        superastronut_atlas.dispose();
        newbgatlas.dispose();
	}
}
