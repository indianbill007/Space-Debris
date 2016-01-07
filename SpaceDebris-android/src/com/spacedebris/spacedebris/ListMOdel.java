package com.spacedebris.spacedebris;

import android.graphics.Bitmap;

public class ListMOdel 
{
  public  String name;
  public  int score;
  public  Bitmap image;
  public  String FB_ID;
  public  int Level;
/**
 * @return the level
 */
public int getLevel() {
	return Level;
}
/**
 * @param level the level to set
 */
public void setLevel(int level) {
	Level = level;
}
/**
 * @return the fB_ID
 */
public  String getFB_ID() {
	return FB_ID;
}
/**
 * @param fB_ID the fB_ID to set
 */
public  void setFB_ID(String fB_ID) {
	FB_ID = fB_ID;
}
/**
 * @return the name
 */
public  String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public  void setName(String name) {
	this.name = name;
}
/**
 * @return the score
 */
public  int getScore() {
	return score;
}
/**
 * @param score the score to set
 */
public  void setScore(int score) {
	this.score = score;
}
/**
 * @return the image
 */
public  Bitmap getImage() {
	return image;
}
/**
 * @param image the image to set
 */
public  void setImage(Bitmap image) {
	//this.image = image;
}
  
}
