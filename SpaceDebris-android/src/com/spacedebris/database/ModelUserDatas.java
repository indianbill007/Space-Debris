package com.spacedebris.database;

public class ModelUserDatas 
{

	String userid;
	String username;
	int level;
	int score;
    String time;
	/**
	 * @return the date
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param date the date to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	public ModelUserDatas(String userid, String username, int level, int score,String time) 
	{
		this.userid = userid;
		this.username = username;
		this.level = level;
		this.score = score;
		this.time=time;
	}

	public ModelUserDatas() 
	{
	}

	/**
	 * @return the userid
	 */
	public String getUserid() 
	{
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}

	/**
	 * @return the level
	 */
	public int getLevel() 
	{
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) 
	{
		this.level = level;
		
	}

	/**
	 * @return the score
	 */
	public int getScore() 
	{
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score)
	{
		this.score = score;
	}

}
