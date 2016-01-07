package com.spacedebris.database;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import android.content.Context;


public class LocalData extends SQLiteOpenHelper {

	public static final String db_name = "spacedebrisvtwo";
	public static final String table_name = "SpacedebrisTableVTWO";
	public static final String KEY_UserID = "userid";
	public static final String KEY_Username = "username";
	public static final String KEY_Level = "level";
	public static final String KEY_Score = "score";
    public static final String KEY_Time="time";
	public static final String UserId = "NEW_USER_ID",
			UserName = "NEW_USER_NAME";

	public LocalData(Context context) {
		super(context, db_name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
/*		String querry = "CREATE TABLE IF NOT EXISTS " + table_name + "("
				+ KEY_UserID + " TEXT," + KEY_Username + " TEXT," + KEY_Level
				+ " INTEGER," + KEY_Score + " INTEGER,"+KEY_Time +" TEXT)";
		
		System.out.println(querry);

		//SQLiteDatabase database = this.getWritableDatabase();

		db.execSQL(querry);*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}

/*	public void CreateTable() {

		
		
		String querry = "CREATE TABLE IF NOT EXISTS " + table_name + "("
				+ KEY_UserID + " TEXT," + KEY_Username + " TEXT," + KEY_Level
				+ " INTEGER," + KEY_Score + " INTEGER,"+KEY_Time +" TEXT)";
		
		System.out.println(querry);

		SQLiteDatabase database = this.getWritableDatabase();

		database.execSQL(querry);

	}*/

	/*public void addLevelScore(ModelUserDatas modelUserDatas) {

		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_UserID, modelUserDatas.getUserid());
		contentValues.put(KEY_Username, modelUserDatas.getUsername());
		contentValues.put(KEY_Level, modelUserDatas.getLevel());
		contentValues.put(KEY_Score, modelUserDatas.getScore());
		contentValues.put(KEY_Time, modelUserDatas.getTime());
		database.insert(table_name, null, contentValues);
	}

	public ModelUserDatas getUserData(String userId, int level) {
		
		ModelUserDatas modelUserDatas = null;

		String query = "SELECT * FROM " + table_name + " WHERE " + KEY_UserID
				+ " = '" + userId + "' AND " + KEY_Level + " = '" + level + "'";

		SQLiteDatabase database = this.getReadableDatabase();

		System.out.println(query);

		Cursor cursor = database.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			
			System.out.println(query);

			modelUserDatas = new ModelUserDatas();
			modelUserDatas.setUserid(cursor.getString(0));
			modelUserDatas.setUsername(cursor.getString(1));
			modelUserDatas.setLevel(Integer.parseInt(cursor.getString(2)));
			modelUserDatas.setScore(Integer.parseInt(cursor.getString(3)));
			modelUserDatas.setTime(cursor.getString(4));
			
			

		}

		return modelUserDatas;
	}

	public ArrayList<ModelUserDatas> getUserData(String userId) {

		
		ArrayList<ModelUserDatas> modelUserDatasAllLevels = new ArrayList<ModelUserDatas>();

		String query = "SELECT * FROM " + table_name + " WHERE " + KEY_UserID
				+ " = '" + userId + "'";
		SQLiteDatabase database = this.getReadableDatabase();

		Cursor cursor = database.rawQuery(query, null);

		ModelUserDatas modelUserDatas;

		if (cursor.moveToFirst()) {

			do {
				modelUserDatas = new ModelUserDatas();
				modelUserDatas.setUserid(cursor.getString(0));
				modelUserDatas.setUsername(cursor.getString(1));
				modelUserDatas.setLevel(Integer.parseInt(cursor.getString(2)));
				modelUserDatas.setScore(Integer.parseInt(cursor.getString(3)));
				modelUserDatas.setTime(cursor.getString(4));
				
				modelUserDatasAllLevels.add(modelUserDatas);
			} while (cursor.moveToNext());
		}

		return modelUserDatasAllLevels;
	}

	public void updateUserLevelScore(ModelUserDatas modelUserDatas) {

		SQLiteDatabase database = this.getWritableDatabase();
		String updateQuery = "UPDATE " + table_name + " SET " + KEY_Score
				+ " = '" + modelUserDatas.getScore() + "', " + KEY_Time
				+ " = '" + modelUserDatas.getTime()+"' WHERE " + KEY_UserID
				+ " = '" + modelUserDatas.getUserid() + "' AND " + KEY_Level
				+ " = '" + modelUserDatas.getLevel() + "'";
		System.out.println(updateQuery);
		database.execSQL(updateQuery);
	}

	public void updateModel(ModelUserDatas modelUserDatas) {

		SQLiteDatabase database = this.getWritableDatabase();

		String updateQuery = "UPDATE " + table_name + " SET " + KEY_UserID
				+ " = '" + modelUserDatas.getUserid() + "', " + KEY_Username
				+ " = '" + modelUserDatas.getUsername() + "' WHERE "
				+ KEY_UserID + " = '" + UserId + "' AND " + KEY_Username
				+ " = '" + UserName + "'";

		System.out.println(updateQuery);

		database.execSQL(updateQuery);

	}

	public void deleteAllRows() {
		SQLiteDatabase database = this.getWritableDatabase();
		String query = "DELETE FROM " + table_name;
		System.out.println(query);
		database.execSQL(query);
	}

	public void deleteThisUserData(String userID) {
		SQLiteDatabase database = this.getWritableDatabase();
		String query = "DELETE FROM " + table_name + " WHERE " + KEY_UserID
				+ " = " + userID;
		System.out.println(query);
		database.execSQL(query);
	}*/

/*	public String getBestLevelOfId(String userId) {

		String level = "0";

		String query = "SELECT MAX(" + KEY_Level + ") FROM " + table_name
				+ " WHERE " + KEY_UserID + " = '" + userId + "'";

		// String query = SELECT id, MAX(rev) FROM t1

		SQLiteDatabase database = this.getReadableDatabase();

		System.out.println(query+" defult  Level - "+level);

		Cursor cursor = database.rawQuery(query, null);

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				level = (cursor.getString(0));
				System.out.println(" MAX LEVEL - "+level);
			}
		}
		
		return level.trim();
	}*/

}
