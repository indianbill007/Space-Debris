package com.spacedebris.spacedebris;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GameObjects.Constantsvalue;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.flurry.android.FlurryAgent;
import com.globussoft.SDhelper.AssetLoader;
import com.globussoft.interfaces.Facebookconnectbackinterface;
import com.globussoft.interfaces.LifeInterface;
import com.globussoft.interfaces.LifebackInterface;
import com.globussoft.interfaces.RankBackInterface;
import com.globussoft.interfaces.RankInterface;
import com.globussoft.interfaces.TopScoreReturnBack;
import com.globussoft.interfaces.TopThreeScoreDialog;
import com.globussoft.interfaces.facebookinterface;
import com.gobussoft.spacedebris.SpaceDebrisGame;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;
import com.meetme.android.horizontallistview.HorizontalListView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.SendCallback;
import com.spacedebris.database.LocalData;
import com.spacedebris.database.ModelUserDatas;
import com.spacedebris.inappbilling.util.IabHelper;
import com.spacedebris.inappbilling.util.IabResult;
import com.spacedebris.inappbilling.util.Inventory;
import com.spacedebris.inappbilling.util.Purchase;
import com.spacedebris.spacedebris.Application.TrackerName;

import net.sqlcipher.database.*;

public class MainActivity extends AndroidApplication implements
		facebookinterface, LifeInterface, TopThreeScoreDialog, RankInterface {

	ProgressDialog progress_dialog;
	public static final String db_name = "spacedebrisvtwo";
	public static final String table_name = "SpacedebrisTableVTWO";
	public static final String KEY_UserID = "userid";
	public static final String KEY_Username = "username";
	public static final String KEY_Level = "level";
	public static final String KEY_Score = "score";
	public static final String KEY_Time = "time";
	public static final String UserId = "NEW_USER_ID",
			UserName = "NEW_USER_NAME";
	AlarmManager alarmManager;
	private PendingIntent pendingIntent;
	List<GraphUser> tags;
	public static TextView counter;

	Timer timer, timer2;
	Handler handler = new Handler();
	ProgressDialog progressDialog, progressbar_graphstory;
	static ArrayList<ListMOdel> Mainlist = new ArrayList<ListMOdel>();
	static ArrayList<String> sublist = new ArrayList<String>();
	public static ArrayList<ModelUserDatas> localDataArray_list = new ArrayList<ModelUserDatas>();
	public static ArrayList<ModelUserDatas> parseDataArray_list = new ArrayList<ModelUserDatas>();
	public static ArrayList<Integer> levels = new ArrayList<Integer>();
	public LocalData localData;
	// adMob
	friendlistadapter flistadapter;
	private InterstitialAd interstitial;
	ArrayList<String> beatedlistID = new ArrayList<String>();
	public static ArrayList<ListMOdel> Playerdata_Array = new ArrayList<ListMOdel>();
	public static ArrayList<ListMOdel> Playerdata_Array2 = new ArrayList<ListMOdel>();
	public static ArrayList<FriendListModel> totalplayers_with_image = new ArrayList<FriendListModel>();
	public static ArrayList<FriendListModel> friend_list = new ArrayList<FriendListModel>();
	public static ArrayList<String> friendid_list = new ArrayList<String>();

	public static ArrayList<FriendListModel> invitable_friend_list = new ArrayList<FriendListModel>();
	public static ArrayList<FriendListModel> invitable_friend_list_new = new ArrayList<FriendListModel>();

	public static ArrayList<String> invited_friend_list_name = new ArrayList<String>();

	CustomListAdapter adapter, firstadapter;
	// friendlistadapter flistadapter;
	Bitmap myimagebitmap, bitmap2;
	int countime = 0;
	// friend status
	public ArrayList<String> selectedfriend = new ArrayList<String>();
	public static ArrayList<ListMOdel> status_Array = new ArrayList<ListMOdel>();
	public static ArrayList<ListMOdel> status_Array1 = new ArrayList<ListMOdel>();
	public static ArrayList<StatusListModel> status_Array2 = new ArrayList<StatusListModel>();
	// friendsbeated
	ArrayList<String> faceookbeatedid = new ArrayList<String>();
	ProgressBar waiting;
	StatusListAdapter statusAdapter;
	TextView Rank1, Rank2, Rank3;
	TextView levelname, ranklevelname;
	// horizontal list view
	private HorizontalListView listview, first_listview;
	private TextView nofriend_status, rank_dialogtext;
	private ListView statusListview;
	GridView friendlistview;
	String level1;
	Dialog waitDialog, rankdialog, friendlistDialog, dialog, statusDialog,
			PushDialog, PushDialog1, PushDialogplain;
	TextView dynamictext, scoreext, nodatatext;
	ImageView life_request_image;
	// send req for life
	private String requestId;
	Boolean highscoreflag = false;
	String highscorelevel = "";
	int highscore_value;
	boolean facebook_first = false;
	public String expected_msg = "sending life request";
	public String expected_lifebackmsg = "sending extra life";
	// call back interfaces
	RankBackInterface rankBackInterface;
	public LifebackInterface lifebackInterface;
	Facebookconnectbackinterface facebookconnectbackinterface;
	TopScoreReturnBack topScoreReturnBack;
	public ConnectionDetector detector;
	// propert constant value for opengraph
	// String IMAGEURL = "http://i.imgur.com/20psd4D.png?1";
	// String IMAGEURL = "https://sdp.globusgames.com/assets/sdp.png";
	String IMAGEURL = "http://i.imgur.com/4uuzVfF.jpg";
	String DESC = "SpaceMan is an addictive arcade game, For more information please click here";
	// String GLOBUSGAMEURL = "https://sdp.globusgames.com";
	String PLAYSTOREURL = "https://play.google.com/store/apps/details?id=com.n3media.spaceman";
	// in app billing
	IabHelper mHelper;
	// static final String ITEM_SKU = "android.test.purchased";
	static final String ITEM_SKU = "com.globussoft.spacedebrisextralife";
	public IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener;
	String senderID = "";
	String level;
	public int numberlevel, score;
	// private RevMob revmob;
	// private RevMobFullscreen fullscreen;
	private Boolean sessionStatus = false;
	private boolean doubleBackToExitPressedOnce = false;
	String fbuserid = "";
	String fbusername = "";
	String sendername = "";
	String beatedfriend_name;
	String beatedFriendID;
	Boolean beated_flag = false;
	boolean fbstatus;
	static boolean status;
	public static ArrayList<String> friendlist = new ArrayList<String>();
	public static ArrayList<String> invitablefriendlist = new ArrayList<String>();

	static boolean postion = false, postion1 = false, postion2 = false;
	private static final List<String> PERMISSIONS = Arrays.asList(
			"user_friends", "publish_actions");
	private static final List<String> PUBLISH_PERMISSIONS = Arrays
			.asList("publish_actions");
	private UiLifecycleHelper uiHelper;

	private static final String TAG = "Chartboost";

	SharedPreferences preferences;
	Editor editor;
	RelativeLayout.LayoutParams gameViewParams;
	AdView adView;
	AdRequest adRequest;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// mAdView = (AdView) findViewById(R.id.adView);
		detector = new ConnectionDetector(getApplicationContext());

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;

		RelativeLayout layout = new RelativeLayout(this);

		gameViewParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		gameViewParams.bottomMargin = 1;

		View gameView = initializeForView(new SpaceDebrisGame(this, this, this,
				this), cfg);

		layout.addView(gameView, gameViewParams);

		adView = new AdView(this);
		adView.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));
		adView.setAdSize(AdSize.SMART_BANNER);

		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		layout.addView(adView, adParams);
		setContentView(layout);

		InitDialog3();
		InitDialog2();
		// InitDialog1();
		PushDialog();

		pushDialogplain();
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		// add encryption
		SQLiteDatabase.loadLibs(getApplicationContext());

		// create local table
		localData = new LocalData(getApplicationContext());
		CreateTable();
		// Parse installation class for Devices

		ParseInstallation.getCurrentInstallation().saveInBackground();

		// AdMob integration
		// Create the interstitial.
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId(getResources().getString(
				R.string.interstitial_ad_unit_id));

		interstitial.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
			}

			@Override
			public void onAdClosed() {
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				// TODO Auto-generated method stub
				super.onAdFailedToLoad(errorCode);
			}
		});

		// chart boost

		Chartboost.startWithAppId(this, getResources()
				.getString(R.string.appId),
				getResources().getString(R.string.appSignature));
		Chartboost.setLoggingLevel(Level.ALL);
		Chartboost.setDelegate(delegate);
		Chartboost.onCreate(this);

		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsLZzJF/jGSMU5uUE4Vg08NosV/6NTVWRZ84oB1PD4qajRMQnQ1DIwxNFIkfGLJTCT2+VsLxjC6hJNsREmnGzMdo0UEg/BTwCSp/VocohhFSCZ0IK67OP0kT3EEximNT2hPHk6omuOlta0G6fI0RGGol1xcGvZh+k0SKBtEQDbnQLw6qCf8h6DiI/1JySjvwhLxs5/TeiiI/ev9kjHEedEPYHQFED26txeoRRSe5CwfTjijBY/xybCSq8YzzuCufeI9SX8NkwnmYltxYeuXIofI7qrAh8s/nYpoMULHHfUVGm6dxUEYWR78KHJnEhZzrcgN530sj1bvCaunZmPOW7XwIDAQAB";
		mHelper = new IabHelper(MainActivity.this, base64EncodedPublicKey);
		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				if (!result.isSuccess()) { // Oh noes, there was a problem.
					Log.d("Error", "Problem setting up In-app Billing: "
							+ result);
				}
				System.out.println("IAB sucessfull");
			}
		});

		// open above code to work IAB

		// generating keyhash
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.spacedebris.spacedebris",
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash:",
						Base64.encodeToString(md.digest(), Base64.DEFAULT));
				System.out.println("@@@@@@@@"
						+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}

		try {
			mReceivedInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
				public void onQueryInventoryFinished(IabResult result,
						Inventory inventory) {

					if (result.isFailure()) {
						// Handle failure
					} else {
						mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
								mConsumeFinishedListener);
					}
				}

			};
		} catch (Exception e) {
			// TODO: handle exception
		}

		// check fb session is valid or not

		preferences = getApplicationContext().getSharedPreferences("facebook",
				0);
		// Set the values
		Gson gson = new Gson();

		String list = preferences.getString("invited_list", null);
		if (list != null) {
			invited_friend_list_name = gson.fromJson(list, ArrayList.class);
		}

		System.out.println("########invited friend list= "
				+ invited_friend_list_name.toString());

		fbstatus = preferences.getBoolean("fbstatus", false);

		if (fbstatus == true && detector.isConnectingToInternet()) {

			Constantsvalue.isconnecttofb = true;
			try {
				fbconnect();
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			try {

				fbuserid = preferences.getString("fbuserid", null);
				fbusername = preferences.getString("fbusername", null);
				if (fbuserid == null)
					fbuserid = "NEW_USER_ID";
				if (fbusername == null)
					fbusername = "NEW_USER_NAME";
				localDataArray_list = getUserData(fbuserid);

				/*
				 * if (localDataArray_list.size() > 0) { AssetLoader
				 * .setLevel(localDataArray_list.get(0).getLevel() + 1); for
				 * (int i = 0; i < localDataArray_list.size(); i++) {
				 * levels.add(localDataArray_list.get(i).getLevel()); if
				 * (AssetLoader.getLevel() < localDataArray_list.get(i)
				 * .getLevel()) {
				 * AssetLoader.setLevel(localDataArray_list.get(i) .getLevel() +
				 * 1); } } } else { AssetLoader.setLevel(1); }
				 */

			} catch (Exception e) {

			}
		}

		Intent myIntent = new Intent(MainActivity.this,
				AddedLifeCustomReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
				myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		NotificationManager mNotificationManager = (NotificationManager) this
				.getApplicationContext().getSystemService(
						this.getApplicationContext().NOTIFICATION_SERVICE);
		mNotificationManager.cancelAll();
		alarmManager.cancel(pendingIntent);
	}

	@Override
	public void onBackPressed() {
		if (Chartboost.onBackPressed())
			return;
		else {
			if (doubleBackToExitPressedOnce) {
				try {
					AssetLoader.setSystemTime(System.currentTimeMillis());
				} catch (Exception e) {
					// TODO: handle exception
				}

				finish();
				return;
			}
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please click BACK again to exit",
				Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);

		uiHelper.onActivityResult(requestCode, resultCode, data,
				new FacebookDialog.Callback() {
					@Override
					public void onError(FacebookDialog.PendingCall pendingCall,
							Exception error, Bundle data) {
						Log.e("Activity",
								String.format("Error: %s", error.toString()));
					}

					@Override
					public void onComplete(
							FacebookDialog.PendingCall pendingCall, Bundle data) {
						Log.i("Activity", "Success!");
					}
				});

		if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	public void fbconnect() {

		if (detector.isConnectingToInternet() == true) {

			localDataArray_list.clear();
			friendlist.clear();
			friend_list.clear();
			Constant.list.clear();
			friendid_list.clear();

			// TODO Auto-generated method stub
			Session.openActiveSession(MainActivity.this, true,
					new Session.StatusCallback() {

						@Override
						public void call(Session session, SessionState state,
								Exception exception) {

							if (session.isOpened()) {
								fbstatus = true;
								editor = preferences.edit();
								editor.putBoolean("fbstatus", true);
								editor.commit();
								Constantsvalue.isconnecttofb = true;

								sessionStatus = true;

								Request.newMeRequest(session,
										new Request.GraphUserCallback() {

											@Override
											public void onCompleted(
													GraphUser user,
													Response response) {
												if (user != null) {

													try {
														fbusername = user
																.getName();
														fbuserid = user.getId();
													} catch (Exception e) {
														// TODO: handle
														// exception
													}

													new createBitmap()
															.execute();
													friendlist.add(fbuserid);
													friendid_list.add(fbuserid);

													editor = preferences.edit();
													editor.putString(
															"fbuserid",
															fbuserid);
													editor.putString(
															"fbusername",
															fbusername);
													editor.commit();

													FriendListModel model = new FriendListModel();
													String fbid = fbuserid;
													String name = fbusername;
													String url = "https://graph.facebook.com/"
															+ fbuserid
															+ "/picture?type=small";
													String[] aa = { url, name,
															fbid };
													/*
													 * new downloadimageaa()
													 * .execute(aa);
													 */
													model.setFB_ID(fbid);
													model.setName(name);
													model.setUrl(url);
													friend_list.add(model);

													// fetch level and store in
													// internal database
													ParseQuery<ParseObject> fetchlevelQurry = ParseQuery
															.getQuery("GameScore");
													fetchlevelQurry
															.whereEqualTo(
																	"PlayerFacebookID",
																	fbuserid);

													fetchlevelQurry
															.addDescendingOrder("Level");
													fetchlevelQurry
															.findInBackground(new FindCallback<ParseObject>() {
																@Override
																public void done(
																		List<ParseObject> LevelmaxList,
																		ParseException arg1) {

																	AssetLoader
																			.setLevel(getLocalLevel());

																	if (arg1 == null) {
																		if (LevelmaxList
																				.size() > 0) {

																			if (getLocalLevel() - 1 < LevelmaxList
																					.get(0)
																					.getInt("Level")) {
																				int parselevel = LevelmaxList
																						.get(0)
																						.getInt("Level");
																				AssetLoader
																						.setLevel(parselevel + 1);
																			}

																		}
																	}
																}
															});
													updateUser(fbuserid,
															fbusername);
													SyncData();

													ParseInstallation
															.getCurrentInstallation()
															.put("facebookId",
																	fbuserid);
													ParseInstallation
															.getCurrentInstallation()
															.saveInBackground(
																	new SaveCallback() {

																		@Override
																		public void done(
																				ParseException arg0) {
																			// TODO
																			// Auto-generated
																			// method
																			// stub

																		}
																	});

												}
											}
										}).executeAsync();

								// Check for publish permissions
								List<String> permissions = session
										.getPermissions();

								if (!isSubsetOf(PERMISSIONS, permissions)) {
									System.out.println("i am in permission");
									Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(
											MainActivity.this, PERMISSIONS);
									session.requestNewPublishPermissions(newPermissionsRequest);
									return;

								}

								// fetching friend list
								new Request(Session.getActiveSession(),
										"/me/friends", null, HttpMethod.GET,
										new Request.Callback() {

											@Override
											public void onCompleted(
													Response response) {

												GraphObject graphObject = response
														.getGraphObject();

												if (graphObject != null) {
													JSONObject jsonObject = graphObject
															.getInnerJSONObject();
													// System.out.println(jsonObject);

													try {
														JSONArray array = jsonObject
																.getJSONArray("data");
														for (int i = 0; i < array
																.length(); i++) {
															JSONObject object = (JSONObject) array
																	.get(i);

															friendlist.add(object
																	.getString("id"));
															friendid_list.add(object
																	.getString("id"));
															FriendListModel model = new FriendListModel();
															String fbid = object
																	.getString("id");
															String name = object
																	.getString("name");
															String url = "https://graph.facebook.com/"
																	+ fbid
																	+ "/picture?type=small";

															String[] aa = {
																	url, name,
																	fbid };
															/*
															 * new
															 * downloadimageaa()
															 * .execute(aa);
															 */

															model.setFB_ID(fbid);
															model.setName(name);
															model.setUrl(url);
															friend_list
																	.add(model);

														}

													} catch (JSONException e) {

														e.printStackTrace();
													}

													inviable_friend();
													// friendStatus();
												}

											}
										}).executeAsync();

								// store invitable friend

							}

						}
					});

			// send track to ganalyst
			Tracker t = ((Application) getApplication())
					.getTracker(TrackerName.APP_TRACKER);
			t.send(new HitBuilders.EventBuilder().setLabel("Facebook Log In")
					.build());

		}

		else {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(getApplicationContext(), "No Internet !!!",
							Toast.LENGTH_LONG).show();
				}
			});

		}

	}

	private void SyncData() {

		localDataArray_list = getUserData(fbuserid);
		ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
		query.whereEqualTo("PlayerFacebookID", fbuserid);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {
				if (e == null) {

					// if data in parse>local
					if (localDataArray_list.size() > scoreList.size()) {
						for (int i = 0; i < localDataArray_list.size(); i++) {

							int localscore = localDataArray_list.get(i)
									.getScore();
							int locallevel = localDataArray_list.get(i)
									.getLevel();
							String currentime = localDataArray_list.get(i)
									.getTime();

							storeLocalToParse(localscore, locallevel,
									currentime);
						}

					} else {
						for (int i = 0; i < scoreList.size(); i++) {
							ModelUserDatas temp = new ModelUserDatas();
							temp.setScore(scoreList.get(i).getInt("Score"));
							temp.setLevel(scoreList.get(i).getInt("Level"));
							temp.setUserid(fbuserid);
							temp.setUsername(fbusername);
							temp.setTime(scoreList.get(i).getString("Time"));

							storeDataInOffline(temp);
						}
					}
					/*
					 * fbuserid=preferences.getString("fbuserid", null);
					 * fbusername=preferences.getString("fbusername", null);
					 * if(fbuserid==null) fbuserid="NEW_USER_ID";
					 * if(fbusername==null) fbusername="NEW_USER_NAME";
					 */

				}
			}
		});
	}

	public void storeDataOffline(ModelUserDatas model) {

		ModelUserDatas modelnew = new ModelUserDatas();
		modelnew = model;
		localDataArray_list.clear();
		// levels.clear();
		localDataArray_list = getUserData(model.getUserid());

		// printing data

		if (localDataArray_list.size() > 0) {
			for (int i = 0; i <= localDataArray_list.size() - 1; i++) {
				model = localDataArray_list.get(i);

			}

		}

		// printing data
		model = modelnew;
		/*
		 * model.setLevel(levelZERO); model.setScore(scorezero);
		 * model.setUserid("Noid"); model.setUsername("NoName");
		 */
		if (localDataArray_list.size() > 0) {
			if (levels.contains(model.getLevel())) {
				ModelUserDatas temp = new ModelUserDatas();
				temp = getUserData(model.getUserid(), model.getLevel());
				if (temp.getScore() < model.getScore()) {
					updateUserLevelScore(model);

				}
			} else {

				addLevelScore(model);
				levels.add(model.getLevel());

			}

		} else {
			addLevelScore(model);
			levels.add(model.getLevel());

		}
		localDataArray_list = getUserData(model.getUserid());

		if (localDataArray_list.size() > 0) {
			for (int i = 0; i <= localDataArray_list.size() - 1; i++) {
				model = localDataArray_list.get(i);

			}

		}

	}

	public void storeDataInOffline(ModelUserDatas model) {

		ModelUserDatas temp = getUserData(model.getUserid(), model.getLevel());

		if (temp != null) {

			if (temp.getScore() < model.getScore()) {

				updateUserLevelScore(model);

			}
		} else {
			addLevelScore(model);
		}

	}

	// getLevel of local

	/*
	 * public int getLocalLevel() { int currentlevel;
	 * localDataArray_list=localData.getUserData(fbuserid);
	 * 
	 * if(localDataArray_list.size()>0) {
	 * 
	 * currentlevel=localDataArray_list.get(0).getLevel()+1; for(int
	 * i=0;i<localDataArray_list.size();i++) {
	 * levels.add(localDataArray_list.get(i).getLevel());
	 * if(currentlevel<localDataArray_list.get(i).getLevel()) {
	 * currentlevel=localDataArray_list.get(i).getLevel()+1; } } } else {
	 * currentlevel=1; }
	 * 
	 * 
	 * return currentlevel; }
	 */

	public int getLocalLevel() {

		int currentlevel;
		localDataArray_list = getUserData(fbuserid);

		if (localDataArray_list.size() > 0) {
			currentlevel = localDataArray_list.get(0).getLevel() + 1;
			for (int i = 0; i < localDataArray_list.size(); i++) {
				// levels.add(localDataArray_list.get(i).getLevel());
				if (currentlevel <= localDataArray_list.get(i).getLevel()) {
					currentlevel = localDataArray_list.get(i).getLevel() + 1;
				}
			}
		} else {
			currentlevel = 1;
		}
		System.out.println(localDataArray_list.size()
				+ " in getLevel() Constants.currentLevel " + currentlevel);
		return currentlevel;
	}

	private void inviable_friend() {
		Bundle bundle = new Bundle();
		bundle.putInt("limit", 5000);
		new Request(Session.getActiveSession(), "/me/taggable_friends", bundle,
				HttpMethod.GET, new Request.Callback() {

					@Override
					public void onCompleted(Response response) {

						GraphObject graphObject = response.getGraphObject();

						if (graphObject != null) {
							JSONObject jsonObject = graphObject
									.getInnerJSONObject();
							// System.out.println(jsonObject);
							invitable_friend_list.clear();
							try {
								JSONArray array = jsonObject
										.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject object = (JSONObject) array
											.get(i);

									invitablefriendlist.add(object
											.getString("id"));

									JSONObject imageobject = object
											.getJSONObject("picture");
									JSONObject dataimageobject = imageobject
											.getJSONObject("data");

									String url = dataimageobject
											.getString("url");

									String fbid = object.getString("id");
									String name = object.getString("name");
									FriendListModel model = new FriendListModel();

									model.setFB_ID(fbid);
									model.setName(name);
									model.setUrl(url);

									invitable_friend_list.add(model);

									String[] aa = { url, name, fbid };
									// new
									// downloadinvitable_image().execute(aa);

								}
System.out.println("print invitable_friend_list>>>>>>>"+invitable_friend_list.size());
								System.out
										.println("1 invitable_friend_list original ---------"
												+ invitable_friend_list
														.toString());

							} catch (JSONException e) {

								e.printStackTrace();
							}

						}

					}
				}).executeAsync();
	}

	@Override
	public void facebookshare() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeDataInParse(final int scorezero, final int levelZERO) {

		if (scorezero > 18 && scorezero < 120 && fbuserid.length() > 0) {

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.println("Current date=====" + dateFormat.format(date));
			final String currenttime = dateFormat.format(date);
			status = true;
			numberlevel = levelZERO;
			level = numberlevel + "";
			score = scorezero;
			final ModelUserDatas model = new ModelUserDatas();
			model.setLevel(levelZERO);
			model.setScore(scorezero);
			model.setUserid(fbuserid);
			model.setUsername(fbusername);
			model.setTime(currenttime);
			detector = new ConnectionDetector(getApplicationContext());
			if (detector.isConnectingToInternet() == true) {
				if (fbstatus) {
					CompleteLevel(level, score);
					ParseQuery<ParseObject> query = ParseQuery
							.getQuery("GameScore");
					query.whereEqualTo("PlayerFacebookID", fbuserid);
					query.whereEqualTo("Level", levelZERO);
					query.findInBackground(new FindCallback<ParseObject>() {
						public void done(List<ParseObject> scoreList,
								ParseException e) {

							if (e == null) {
								if (scoreList.size() > 0) {
									int scor = scoreList.get(0).getInt("Score");

									if (scor < scorezero) {

										ParseQuery<ParseObject> parseQuery = ParseQuery
												.getQuery("GameScore");
										parseQuery.getInBackground(scoreList
												.get(0).getObjectId(),
												new GetCallback<ParseObject>() {
													@Override
													public void done(
															ParseObject arg0,
															ParseException arg1) {
														System.out
																.println("exception in parse==========="
																		+ arg1);
														if (arg1 == null) {
															arg0.put("Score",
																	scorezero);
															arg0.put("Time",
																	currenttime);
															arg0.saveInBackground(new SaveCallback() {
																@Override
																public void done(
																		ParseException arg0) {
																	storeDataOffline(model);
																	FriendScoreCompareMethod();
																}
															});
														}

													}
												});
									}
								} else {
									ParseObject parse = new ParseObject(
											"GameScore");
									parse.put("PlayerFacebookID", fbuserid);
									parse.put("Name", fbusername);
									parse.put("Score", scorezero);
									parse.put("Level", levelZERO);

									parse.put("Time", currenttime);
									parse.saveInBackground(new SaveCallback() {
										@Override
										public void done(ParseException arg0) {
											storeDataOffline(model);
											System.out
													.println("new row created exception=="
															+ arg0);
											if (arg0 == null) {
												FriendScoreCompareMethod();
											}
										}
									});
									storeDataOffline(model);
								}
								// friendStatus();
							}
						}
					});
				} else {

					storeDataOffline(model);
				}

			} else {

				storeDataOffline(model);
			}
		}
	}

	public void updateUser(String fbuserid, String fbusername) {
		localDataArray_list = getUserData("NEW_USER_ID");
		ModelUserDatas temp = new ModelUserDatas();

		if (localDataArray_list.size() > 0) {
			for (int i = 0; i < localDataArray_list.size(); i++) {
				temp = localDataArray_list.get(i);

			}

		}
		for (int i = 0; i < localDataArray_list.size(); i++) {
			temp.setLevel(localDataArray_list.get(i).getLevel());
			temp.setScore(localDataArray_list.get(i).getScore());
			temp.setUserid(fbuserid);
			temp.setUsername(fbusername);
			temp.setTime(localDataArray_list.get(i).getTime());
			updateModel(temp);

		}

		localDataArray_list = getUserData(fbuserid);

		if (localDataArray_list.size() > 0) {
			for (int i = 0; i <= localDataArray_list.size() - 1; i++) {
				temp = localDataArray_list.get(i);

			}
			for (int i = 0; i < levels.size(); i++) {

			}

		} else {

		}

	}

	public void storeLocalToParse(final int scorezero, final int levelZERO,
			final String currenttime) {
		System.out.println("inside store local data to parse " + scorezero
				+ "------ " + levelZERO);

		ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
		query.whereEqualTo("PlayerFacebookID", fbuserid);
		query.whereEqualTo("Level", levelZERO);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {

				if (e == null) {
					if (scoreList.size() > 0) {
						int scor = scoreList.get(0).getInt("Score");
						if (scor < scorezero) {

							ParseQuery<ParseObject> parseQuery = ParseQuery
									.getQuery("GameScore");
							parseQuery.getInBackground(scoreList.get(0)
									.getObjectId(),
									new GetCallback<ParseObject>() {
										@Override
										public void done(ParseObject arg0,
												ParseException arg1) {
											if (arg1 == null) {
												arg0.put("Score", scorezero);
												arg0.put("Time", currenttime);
												arg0.saveInBackground(new SaveCallback() {
													@Override
													public void done(
															ParseException arg0) {

													}
												});
											}
										}
									});
						}
					} else {
						ParseObject parse = new ParseObject("GameScore");
						parse.put("PlayerFacebookID", fbuserid);
						parse.put("Name", fbusername);
						parse.put("Score", scorezero);
						parse.put("Level", levelZERO);
						parse.put("Time", currenttime);
						parse.saveInBackground(new SaveCallback() {
							@Override
							public void done(ParseException arg0) {

							}
						});

					}
				}
			}
		});
	}

	// Compare score and fetch your global rank

	// here i am fetching score and frndslist for checking weather i beat
	// someone or not.

	public void FriendScoreCompareMethod() {

		if (Session.getActiveSession() != null) {
			System.out
					.println("***********************Inside 2nd active session");
			ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
			System.out.println("number level    " + numberlevel);
			query.whereEqualTo("Level", numberlevel);
			query.whereContainedIn("PlayerFacebookID", friendid_list);
			query.whereLessThan("Score", score);
			System.out.println("score in       " + score);
			query.addDescendingOrder("Score");
			query.findInBackground(new FindCallback<ParseObject>() {

				public void done(List<ParseObject> scoreList, ParseException e) {
					System.out.println("Error in getting friend beated data "
							+ e);
					if (e == null) {

						System.out.println("size of friend beated list "
								+ scoreList.size());
						if (scoreList.size() > 0) {
							faceookbeatedid.clear();
							for (int i = 0; i < scoreList.size(); i++) {
								String name = scoreList.get(i)
										.getString("Name");
								System.out.println("@@@@@@@@@@@   " + name);
								String facebookid = scoreList.get(i).getString(
										"PlayerFacebookID");
								faceookbeatedid.add(facebookid);
								beatAfriend(name, level, score);
							}

							JSONObject jobObject = new JSONObject();
							try {
								jobObject
										.put("action",
												"com.globussoft.spacedebris.UPDATE_STATUS");
								jobObject.put("Message", fbusername
										+ " beat you on Level " + level);
								jobObject.put("Type", "3");
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							// post feed dialog to beated 1st friend
							BeatedFriendFeed(level, score);

							ParseQuery pushQuery = ParseInstallation.getQuery();
							pushQuery.whereContainedIn("facebookId",
									faceookbeatedid);
							ParsePush androidpush = new ParsePush();
							androidpush.setQuery(pushQuery);
							// androidpush.setExpirationTime(300000);
							androidpush.setData(jobObject);
							androidpush.sendInBackground(new SendCallback() {

								@Override
								public void done(ParseException arg0) {

									// BeatedFriendFeed(level, score);

								}
							});
						} else {

						}

					}
				}
			});

		}
	}// end of scomparescore activessn

	// <----------------------------------------Graph
	// story--------------------------------------------------------->
	// Beat friend graph story for beating friend
	private void beatAfriend(final String friendname, final String level,
			final int score) {

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (detector.isConnectingToInternet() == true && sessionStatus) {
					askPermission();
					RequestBatch requestBatch = new RequestBatch();
					// graph story
					OpenGraphObject book = OpenGraphObject.Factory
							.createForPost("sdphantom:friend");
					book.setProperty("type", "sdphantom:friend");
					book.setProperty("title", "Beat  " + friendname);
					book.setProperty("image", IMAGEURL);
					book.setProperty("description", "Beat  " + friendname
							+ " at level " + level + " and made score " + score
							+ " !!!  " + Constant.story_desc);
					// book.setProperty("url", GLOBUSGAMEURL);

					OpenGraphAction ogAction = GraphObject.Factory
							.create(OpenGraphAction.class);

					// setprpoerty name should be same as object name e.g:friend
					ogAction.setProperty("friend", book);
					ogAction.setType("sdphantom:beat");
					ogAction.setExplicitlyShared(true);
					Request.Callback actionCallback = new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							FacebookRequestError error = response.getError();
							if (error != null) {

							} else {
								String actionId = null;
								try {
									JSONObject graphResponse = response
											.getGraphObject()
											.getInnerJSONObject();
									actionId = graphResponse.getString("id");
								} catch (JSONException e) {
									Log.i("Tag", "JSON error " + e.getMessage());
								}
								AlertDialog alertDialog = new AlertDialog.Builder(
										MainActivity.this).create();
								alertDialog.setTitle("Congratulation!!");
								alertDialog.setMessage("You beat " + friendname
										+ " at level " + level
										+ " and made score " + score + " !!!");
								alertDialog.setIcon(R.drawable.icon);
								// alertDialog.show();
							}
						}
					};
					Request actionRequest = Request
							.newPostOpenGraphActionRequest(
									Session.getActiveSession(), ogAction,
									actionCallback);
					requestBatch.add(actionRequest);
					requestBatch.executeAsync();
				}

			}
		});
	}

	// set highscore story
	private void setHighscore(final int highscore, final String level) {

		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (detector.isConnectingToInternet() == true && sessionStatus) {

					askPermission();
					RequestBatch requestBatch = new RequestBatch();
					// graph story
					OpenGraphObject book = OpenGraphObject.Factory
							.createForPost("sdphantom:highscore");
					book.setProperty("type", "sdphantom:highscore");
					book.setTitle("New HighScore");
					book.setProperty("image", IMAGEURL);
					book.setProperty("description", "Set new highscore of "
							+ highscore + " in level " + " " + level + ".  "
							+ Constant.story_desc);
					// book.setProperty("url", GLOBUSGAMEURL);

					OpenGraphAction ogAction = GraphObject.Factory
							.create(OpenGraphAction.class);

					// setprpoerty name should be same as object name e.g:friend
					ogAction.setProperty("highscore", book);
					ogAction.setType("sdphantom:get");
					ogAction.setExplicitlyShared(true);
					Request.Callback actionCallback = new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							FacebookRequestError error = response.getError();
							if (error != null) {

							} else {
								String actionId = null;
								try {
									JSONObject graphResponse = response
											.getGraphObject()
											.getInnerJSONObject();
									actionId = graphResponse.getString("id");
								} catch (JSONException e) {
									Log.i("Tag", "JSON error " + e.getMessage());
								}
								AlertDialog alertDialog = new AlertDialog.Builder(
										MainActivity.this).create();
								alertDialog.setTitle("High Score");
								alertDialog
										.setMessage("Story has been successfuly posted to facebook");
								alertDialog.setIcon(R.drawable.icon);
								// alertDialog.show();
							}
						}
					};
					Request actionRequest = Request
							.newPostOpenGraphActionRequest(
									Session.getActiveSession(), ogAction,
									actionCallback);
					requestBatch.add(actionRequest);
					requestBatch.executeAsync();
				}
			}
		});
	}

	// complete a level

	private void CompleteLevel(final String level, final int score) {

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (detector.isConnectingToInternet() == true && sessionStatus) {
					askPermission();
					RequestBatch requestBatch1 = new RequestBatch();
					// graph story
					OpenGraphObject book = OpenGraphObject.Factory
							.createForPost("sdphantom:level");
					book.setProperty("type", "sdphantom:level");
					book.setTitle("Space Debris Phantom");
					book.setProperty("image", "http://i.imgur.com/xU03BCc.png");
					book.setProperty("description", "Cleared level " + " "
							+ level + " & Scored " + score + ". "
							+ Constant.story_desc);
					// book.setProperty("url", GLOBUSGAMEURL);

					// Set up the object request callback
					OpenGraphAction ogAction = GraphObject.Factory
							.create(OpenGraphAction.class);
					// setprpoerty name should be same as object name e.g:friend
					ogAction.setProperty("level", book);
					ogAction.setType("sdphantom:complete");
					ogAction.setExplicitlyShared(true);
					Request.Callback actionCallback = new Request.Callback() {
						@Override
						public void onCompleted(Response response) {

							FacebookRequestError error = response.getError();
							if (error != null) {

								System.out.println("response===>>>>>>>>"
										+ response);
							} else {
								AlertDialog alertDialog = new AlertDialog.Builder(
										MainActivity.this).create();
								alertDialog.setTitle("Level Cleared");
								alertDialog
										.setMessage("Story has been successfuly posted to facebook");
								alertDialog.setIcon(R.drawable.icon);
								// alertDialog.show();

							}
						}
					};
					Request actionRequest = Request
							.newPostOpenGraphActionRequest(
									Session.getActiveSession(), ogAction,
									actionCallback);
					requestBatch1.add(actionRequest);
					requestBatch1.executeAsync();
				}
			}
		});
	}

	// send a life

	private void SendLife(final String me, final String friend) {

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (detector.isConnectingToInternet() == true
						&& Session.getActiveSession() != null) {
					RequestBatch requestBatch1 = new RequestBatch();
					// graph story
					OpenGraphObject book = OpenGraphObject.Factory
							.createForPost("sdphantom:life");
					book.setProperty("type", "sdphantom:life");
					book.setTitle("Space Debris Phantom");
					book.setProperty("image", IMAGEURL);
					book.setProperty("description", "Now " + friend
							+ " has one extra life gifted by " + me);
					// book.setProperty("url", GLOBUSGAMEURL);

					OpenGraphAction ogAction = GraphObject.Factory
							.create(OpenGraphAction.class);

					// setprpoerty name should be same as object name e.g:friend
					ogAction.setProperty("life", book);
					ogAction.setType("sdphantom:send");
					ogAction.setExplicitlyShared(true);
					Request.Callback actionCallback = new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							FacebookRequestError error = response.getError();
							if (error != null) {
								// -----------------------------------------------------alert
								// dialog

								AlertDialog alertDialog = new AlertDialog.Builder(
										MainActivity.this).create();
								alertDialog.setTitle("Sent one Extra Life");
								alertDialog
										.setMessage("Story has been successfuly posted to facebook");
								alertDialog.setIcon(R.drawable.icon);
								alertDialog.show();

							} else {
								String actionId = null;
								try {
									JSONObject graphResponse = response
											.getGraphObject()
											.getInnerJSONObject();
									actionId = graphResponse.getString("id");
								} catch (JSONException e) {
									Log.i("Tag", "JSON error " + e.getMessage());
								}
								Toast.makeText(
										getApplicationContext()
												.getApplicationContext(),
										actionId, Toast.LENGTH_LONG).show();
							}
						}
					};
					Request actionRequest = Request
							.newPostOpenGraphActionRequest(
									Session.getActiveSession(), ogAction,
									actionCallback);
					requestBatch1.add(actionRequest);
					requestBatch1.executeAsync();
				}
			}
		});
	}

	// say thank you for a life

	private void thankyou(final String me, final String friend) {

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (detector.isConnectingToInternet() == true
						&& Session.getActiveSession() != null) {
					RequestBatch requestBatch1 = new RequestBatch();
					// graph story
					OpenGraphObject book = OpenGraphObject.Factory
							.createForPost("sdphantom:life");
					book.setProperty("type", "sdphantom:life");
					book.setTitle("thank you " + friend + " for extra life");
					book.setProperty("image", IMAGEURL);
					book.setProperty("description", friend
							+ "got one extra life gifted by " + me);
					// book.setProperty("url", GLOBUSGAMEURL);

					OpenGraphAction ogAction = GraphObject.Factory
							.create(OpenGraphAction.class);

					// setprpoerty name should be same as object name e.g:friend
					ogAction.setProperty("level", book);
					ogAction.setType("sdphantom:say");

					Request.Callback actionCallback = new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							FacebookRequestError error = response.getError();
							if (error != null) {
								// -----------------------------------------------------alert
								// dialog

								AlertDialog alertDialog = new AlertDialog.Builder(
										MainActivity.this).create();
								alertDialog
										.setTitle("Thank you For extra Life");
								alertDialog
										.setMessage("Story has been successfuly posted to facebook");
								alertDialog.setIcon(R.drawable.icon);
								alertDialog.show();

							} else {
								String actionId = null;
								try {
									JSONObject graphResponse = response
											.getGraphObject()
											.getInnerJSONObject();
									actionId = graphResponse.getString("id");
								} catch (JSONException e) {
									Log.i("Tag", "JSON error " + e.getMessage());
								}
								Toast.makeText(
										getApplicationContext()
												.getApplicationContext(),
										actionId, Toast.LENGTH_LONG).show();
							}
						}
					};
					Request actionRequest = Request
							.newPostOpenGraphActionRequest(
									Session.getActiveSession(), ogAction,
									actionCallback);
					requestBatch1.add(actionRequest);
					requestBatch1.executeAsync();
				}
			}
		});
	}

	// <----------------------------------------Graph story
	// End--------------------------------------------------------->

	// publish dialog

	private void completeLevelFeed(final String level, final int score) {
		if (detector.isConnectingToInternet() == true && sessionStatus) {

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// connect fb

					Bundle params = new Bundle();
					params.putString("name", "Space Debris Phantom");
					params.putString("caption", "I cleared level " + level
							+ " and scored " + score);
					params.putString("description", Constant.Game_Desc);
					params.putString("link", PLAYSTOREURL);
					params.putString("picture", IMAGEURL);
					WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(
							MainActivity.this, Session.getActiveSession(),
							params)).setOnCompleteListener(
							new OnCompleteListener() {

								@Override
								public void onComplete(Bundle values,
										FacebookException error) {

								}

							}).build();
					feedDialog.show();
				}
			});

		} else {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {

					// Toast.makeText(getApplicationContext(),
					// "Please connect to facebook",Toast.LENGTH_LONG).show();
					fbconnect();
				}
			});
		}
	}

	// publish dialog for beated friend.

	private void BeatedFriendFeed(final String level, final int score) {
		if (detector.isConnectingToInternet() == true && sessionStatus) {

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// connect fb
					Bundle params = new Bundle();
					params.putString("name", "Space Debris Phantom");
					params.putString("caption", "I beat you on level " + level
							+ " and scored " + score);
					params.putString("description", Constant.Game_Desc);
					params.putString("link", PLAYSTOREURL);
					params.putString("picture", IMAGEURL);
					params.putString("to", faceookbeatedid.get(0));
					WebDialog beat_feedDialog = (new WebDialog.FeedDialogBuilder(
							MainActivity.this, Session.getActiveSession(),
							params)).setOnCompleteListener(
							new OnCompleteListener() {

								@Override
								public void onComplete(Bundle values,
										FacebookException error) {

								}

							}).build();
					beat_feedDialog.show();
				}
			});

		}
	}

	// Get life after sharing publish feed

	private void RefillLife() {
		if (detector.isConnectingToInternet() == true && sessionStatus) {

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// connect fb

					Bundle params = new Bundle();
					params.putString("name", "Space Debris Phantom");
					// params.putString("caption", ");
					params.putString("description", Constant.desc);
					params.putString("link", PLAYSTOREURL);
					params.putString("picture", IMAGEURL);
					WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(
							MainActivity.this, Session.getActiveSession(),
							params)).setOnCompleteListener(
							new OnCompleteListener() {

								@Override
								public void onComplete(Bundle values,
										FacebookException error) {

									if (error == null) {

										final String postId = values
												.getString("post_id");
										if (postId != null) {
											if (AssetLoader.getExtraLife() < 11) {

												Toast.makeText(
														getApplicationContext(),
														"Lifes added Successfully",
														Toast.LENGTH_LONG)
														.show();
												Constantsvalue.isfbshared = true;
											}
										}
									}
								}

							}).build();
					feedDialog.show();
				}
			});

		} else {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {

					// Toast.makeText(getApplicationContext(),
					// "Please connect to facebook",Toast.LENGTH_LONG).show();
					fbconnect();
				}
			});
		}
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {

		if (state.isOpened() && requestId != null) {

			/*
			 * getRequestData(requestId); requestId = null;
			 */
		} else if (state.isClosed()) {
			Log.i("message", "Logged out...");
		}
	}

	public void consumeItem() {
		mHelper.queryInventoryAsync(mReceivedInventoryListener);

	}

	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
			if (result.isFailure()) {

				// Handle error
				return;
			} else if (purchase.getSku().equals(ITEM_SKU)) {

				consumeItem();

			}

		}

	};

	IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
		public void onConsumeFinished(Purchase purchase, IabResult result) {

			if (result.isSuccess()) {

				AssetLoader.setExtraLife(500);

				String android_id = Secure.getString(getApplicationContext()
						.getContentResolver(), Secure.ANDROID_ID);
				ParseObject object = new ParseObject("LifeDetails");
				object.put("DeviceType", "android");
				object.put("DeviceUniqueID", android_id);
				object.put("TransactionID", purchase.getOrderId());
				object.saveInBackground();

				Toast.makeText(getApplicationContext(),
						"SUCCESSFUL, Go back to continue ", Toast.LENGTH_LONG)
						.show();

			} else {
				// handle error

				Toast.makeText(getApplicationContext(),
						"Failed, Please Check the network connection",
						Toast.LENGTH_LONG).show();
			}
		}
	};

	public static String getDate(long milliSeconds, String dateFormat) {
		// Create a DateFormatter object for displaying date in specified
		// format.
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in
		// milliseconds to date.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

	@Override
	public void beatafriendStory(int score, int level) {
		//

	}

	@Override
	public void sethighscoreStory(int highscore, int level) {
		level = level + 1;
		highscorelevel = level + "";
		highscoreflag = true;

		dynamictext.setText("Highscore !!!");

		if (highscoreflag) {
			setHighscore(highscore_value, highscorelevel);
			highscoreflag = false;
		}

	}

	@Override
	public void asklife() {

		CompleteLevel("3", 45);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (detector.isConnectingToInternet() == true && sessionStatus) {

					// Collections.shuffle(invitable_friend_list);
					friendlistDialog.show();
					invitable_friend_list_new.clear();
					for (int i = 0; i < invitable_friend_list.size(); i++) {
						boolean isPresent = false;
						for (int j = 0; j < invited_friend_list_name.size(); j++) {
							if (invitable_friend_list.get(i).getName()
									.contains(invited_friend_list_name.get(j))) {

								isPresent = true;
							}
						}

						if (!isPresent) {
							invitable_friend_list_new.add(invitable_friend_list
									.get(i));
						}

					}

					if (invitable_friend_list_new.size() > 5) {
						flistadapter = new friendlistadapter(
								getApplicationContext(),
								invitable_friend_list_new);
					} else {

						Gson gson = new Gson();
						invited_friend_list_name.clear();
						String jsonText = gson.toJson(invited_friend_list_name);
						editor = preferences.edit();
						editor.putString("invited_list", jsonText);
						editor.commit();

						flistadapter = new friendlistadapter(
								getApplicationContext(), invitable_friend_list);
					}

					friendlistview.setAdapter(flistadapter);
				} else {

					// if no internet then conncet
					fbconnect_after_nolife();

				}
			}
		});

		// RefillLife();

		/*
		 * Session.openActiveSession(MainActivity.this, true, new
		 * StatusCallback() {
		 * 
		 * @Override public void call(Session session, SessionState state,
		 * Exception exception) { if (session.isOpened()) { //
		 * sendRequestDialog();
		 * 
		 * } } });
		 * 
		 * runOnUiThread(new Runnable() {
		 * 
		 * @Override public void run() { if (detector.isConnectingToInternet()
		 * == true && sessionStatus) {
		 * System.out.println("inside ask life^^^^^^^^^^^^^^^^^^^");
		 * friendlistDialog.show(); } else {
		 * Toast.makeText(getApplicationContext(), "Facebook not connected",
		 * Toast.LENGTH_LONG).show(); } } });
		 * 
		 * flistadapter = new friendlistadapter(getApplicationContext(),
		 * totalplayers_with_image);
		 * System.out.println("totalplayers_with_image>>>>>>>>>>>>>>>>" +
		 * totalplayers_with_image.size());
		 * friendlistview.setAdapter(flistadapter);
		 */

	}

	/*
	 * private void sendRequestDialog() { String id = ""; Bundle params = new
	 * Bundle(); params.putString("message", "sending life request");
	 * 
	 * params.putString("data", "{\"badge_of_awesomeness\":\"0\"," +
	 * "\"social_karma\":\"5\"}");
	 * 
	 * for (int i = 0; i < flistadapter.selectedfriend.size(); i++) { id = id +
	 * flistadapter.selectedfriend.get(i) + ","; }
	 * 
	 * System.out.println("id---" + id);
	 * 
	 * params.putString("to", id);
	 * 
	 * WebDialog requestsDialog = (new WebDialog.RequestsDialogBuilder(
	 * MainActivity.this, Session.getActiveSession(), params))
	 * .setOnCompleteListener(new OnCompleteListener() {
	 * 
	 * @Override public void onComplete(Bundle values, FacebookException error)
	 * { if (error != null) { if (error instanceof
	 * FacebookOperationCanceledException) { Toast.makeText(
	 * getApplicationContext() .getApplicationContext(), "Request cancelled",
	 * Toast.LENGTH_SHORT) .show(); } else { Toast.makeText(
	 * getApplicationContext() .getApplicationContext(), "Network Error",
	 * Toast.LENGTH_SHORT) .show(); } } else { final String requestId = values
	 * .getString("request"); if (requestId != null) {
	 * 
	 * friendlistDialog.cancel(); Toast.makeText( getApplicationContext()
	 * .getApplicationContext(), "Request sent", Toast.LENGTH_SHORT) .show();
	 * 
	 * } else { Toast.makeText( getApplicationContext()
	 * .getApplicationContext(), "Request cancelled", Toast.LENGTH_SHORT)
	 * .show(); } } } })
	 * 
	 * .build();
	 * 
	 * requestsDialog.show(); }
	 */

	public void sendrequest(String friendid) {

		Bundle params = new Bundle();

		params.putString("message", "sending extra life");
		params.putString("data", "{\"badge_of_awesomeness\":\"1\","
				+ "\"social_karma\":\"5\"}");

		// Optionally provide a 'to' param to direct the request at a specific
		// user

		params.putString("to", friendid);

		// Give the structured request information

		WebDialog requestsDialog = (new WebDialog.RequestsDialogBuilder(
				MainActivity.this, Session.getActiveSession(), params))
				.setOnCompleteListener(new OnCompleteListener() {
					@Override
					public void onComplete(Bundle values,
							FacebookException error) {
						if (error != null) {
							if (error instanceof FacebookOperationCanceledException) {
								Toast.makeText(
										getApplicationContext()
												.getApplicationContext(),
										"Request cancelled", Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(
										getApplicationContext()
												.getApplicationContext(),
										"Network Error", Toast.LENGTH_SHORT)
										.show();
							}
						} else {
							final String requestId = values
									.getString("request");
							if (requestId != null) {

								friendlistDialog.cancel();
								Toast.makeText(
										getApplicationContext()
												.getApplicationContext(),
										"Request sent", Toast.LENGTH_SHORT)
										.show();
								SendLife(fbusername, sendername);
							} else {
								Toast.makeText(
										getApplicationContext()
												.getApplicationContext(),
										"Request cancelled", Toast.LENGTH_SHORT)
										.show();
							}
						}
					}
				})

				.build();

		requestsDialog.show();

	}

	// in this method we will fetch the data which we got from notification
	/*
	 * private void getRequestData(final String inRequestId) { // Create a new
	 * request for an HTTP GET with the // request ID as the Graph path.
	 * 
	 * System.out.println("getreq data");
	 * 
	 * System.out.println("id>>>>>>>>>>>>>>" + inRequestId); Request request =
	 * new Request(Session.getActiveSession(), inRequestId, null,
	 * HttpMethod.GET, new Request.Callback() {
	 * 
	 * @Override public void onCompleted(Response response) { // Process the
	 * returned response GraphObject graphObject = response.getGraphObject();
	 * System.out .println("GraphObject response" + graphObject);
	 * 
	 * FacebookRequestError error = response.getError(); boolean processError =
	 * false;
	 * 
	 * // Default message String message = "Incoming request"; if (graphObject
	 * != null) {
	 * 
	 * System.out.println("Graph Object>>>"+graphObject); // Check if there is
	 * extra data if (graphObject.getProperty("data") != null) { try { // Get
	 * the data, parse info to get the // key/value info JSONObject dataObject =
	 * new JSONObject( (String) graphObject .getProperty("data"));
	 * 
	 * System.out.println("data" + dataObject); // Get the value for the key -
	 * // badge_of_awesomeness String life = dataObject
	 * .getString("badge_of_awesomeness"); // Get the value for the key -
	 * social_karma String karma = dataObject .getString("social_karma"); // Get
	 * the sender's name JSONObject fromObject = (JSONObject) graphObject
	 * .getProperty("from"); String sender = fromObject .getString("name");
	 * final String senderID = fromObject .getString("id"); String title =
	 * sender + " sent you a gift"; // Create the text for the alert based on //
	 * the sender // and the data message = title + "\n\n" + "life: " + life +
	 * " Karma: " + karma; System.out.println("life==================" + life);
	 * 
	 * // <------------> if (life.equals("0")) { sendrequest(senderID);
	 * 
	 * } else if (life.equals("2")) { System.out
	 * .println("------------request id =null----------"); } else if
	 * (life.equals("1")) {
	 * 
	 * lifebackInterface.gotlife();
	 * 
	 * System.out .println("increase life count.main activity");
	 * 
	 * }
	 * 
	 * Toast.makeText(getApplicationContext(), message,
	 * Toast.LENGTH_LONG).show();
	 * 
	 * } catch (JSONException e) { message = "Error getting request info";
	 * processError = true; } } else if (error != null) { message =
	 * "Error getting request info"; Toast.makeText(getApplicationContext(),
	 * message, Toast.LENGTH_LONG).show(); } }
	 * 
	 * if (!processError) { deleteRequest(inRequestId); requestId = null; } }
	 * }); // Execute the request asynchronously.
	 * Request.executeBatchAsync(request); }
	 */

	// in this method we will fetch the data which we got from notification
	private void getRequestData(final String inRequestId) {
		// Create a new request for an HTTP GET with the
		// request ID as the Graph path.
		Request request = new Request(Session.getActiveSession(), inRequestId,
				null, HttpMethod.GET, new Request.Callback() {

					@Override
					public void onCompleted(Response response) {
						// Process the returned response
						GraphObject graphObject = response.getGraphObject();

						FacebookRequestError error = response.getError();
						boolean processError = false;

						// Default message
						String message = "Incoming request";
						if (graphObject != null) {
							// Check if there is extra data
							if (graphObject.getProperty("message") != null) {
								// Get the data, parse info to get the
								// key/value info
								String incoming_message = (String) graphObject
										.getProperty("message").toString()
										.trim();

								JSONObject fromObject = (JSONObject) graphObject
										.getProperty("from");

								try {
									senderID = fromObject.getString("id");
									sendername = fromObject.getString("name");
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								if (expected_msg.equals(incoming_message)) {

									/*
									 * show_send_life_CustomDialog("  " +
									 * sendername +
									 * " request you for an extra life", true);
									 */

								} else if (expected_lifebackmsg
										.equals(incoming_message)) {

									/*
									 * showCustomDialog(
									 * "  You got an extra life from " +
									 * sendername, false);
									 */
								} else {
									Toast.makeText(getApplicationContext(),
											"Something Went wrong",
											Toast.LENGTH_LONG).show();
								}

								Toast.makeText(getApplicationContext(),
										message, Toast.LENGTH_LONG).show();
							} else if (error != null) {
								message = "Error getting request info";
								Toast.makeText(getApplicationContext(),
										message, Toast.LENGTH_LONG).show();
							}
						}

						if (!processError) {
							deleteRequest(inRequestId);
							requestId = null;
						}
					}
				});
		// Execute the request asynchronously.
		Request.executeBatchAsync(request);
	}

	// method for deleting request from facebbok
	private void deleteRequest(String inRequestId) {

		Request request = new Request(Session.getActiveSession(), inRequestId,
				null, HttpMethod.DELETE, new Request.Callback() {

					@Override
					public void onCompleted(Response response) {

					}
				});
		// Execute the request asynchronously.
		Request.executeBatchAsync(request);
	}

	private boolean isSubsetOf(Collection<String> subset,
			Collection<String> superset) {
		for (String string : subset) {
			if (!superset.contains(string)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void askLifeExecution(LifebackInterface lifebackInterface) {

		this.lifebackInterface = lifebackInterface;

	}

	@Override
	public void shareclearedlevel(int score, int level) {

		/*
		 * String updatedlevel=level+""; CompleteLevel(updatedlevel,score);
		 */
	}

	/*
	 * protected void showCustomDialog(String text, final Boolean flag) {
	 * 
	 * dialog = new Dialog(MainActivity.this,
	 * android.R.style.Theme_Translucent);
	 * dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	 * 
	 * dialog.setCancelable(true); dialog.setContentView(R.layout.back_dialog);
	 * 
	 * ImageView accept, cancel; TextView msg; cancel = (ImageView)
	 * dialog.findViewById(R.id.back_exit); accept = (ImageView)
	 * dialog.findViewById(R.id.back_exitcancel); msg = (TextView)
	 * dialog.findViewById(R.id.exittextview);
	 * 
	 * life_request_image = (ImageView) dialog.findViewById(R.id.imageView1);
	 * msg.setText(text); new createBitmapofuser().execute();
	 * 
	 * accept.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) {
	 * 
	 * dialog.dismiss(); if (flag == true) { sendrequest(senderID); } else {
	 * lifebackInterface.gotlife(); thankyou(fbusername, sendername);
	 * 
	 * } } });
	 * 
	 * cancel.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub dialog.dismiss(); } }); dialog.show(); }
	 */

	/*
	 * // showsendlifecustomdialog protected void
	 * show_send_life_CustomDialog(String text, final Boolean flag) {
	 * 
	 * dialog = new Dialog(MainActivity.this,
	 * android.R.style.Theme_Translucent);
	 * dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	 * 
	 * dialog.setCancelable(true);
	 * dialog.setContentView(R.layout.back_dialog_two);
	 * 
	 * ImageView accept, cancel; TextView msg; cancel = (ImageView)
	 * dialog.findViewById(R.id.back_exit); accept = (ImageView)
	 * dialog.findViewById(R.id.back_exitcancel); msg = (TextView)
	 * dialog.findViewById(R.id.exittextview); life_request_image = (ImageView)
	 * dialog.findViewById(R.id.imageView1); msg.setText(text); new
	 * createBitmapofuser().execute();
	 * 
	 * accept.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) {
	 * 
	 * dialog.dismiss(); if (flag == true) { sendrequest(senderID); } else {
	 * lifebackInterface.gotlife(); thankyou(fbusername, sendername);
	 * 
	 * } } });
	 * 
	 * cancel.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub dialog.dismiss(); } }); dialog.show(); }
	 */

	private void pushDialogplain() {

		PushDialogplain = new Dialog(MainActivity.this);
		PushDialogplain.requestWindowFeature(Window.FEATURE_NO_TITLE);
		PushDialogplain.setContentView(R.layout.push_dialog_plain);
		ImageView pushclose = (ImageView) PushDialogplain
				.findViewById(R.id.pushClose);
		TextView pushtext = (TextView) PushDialogplain
				.findViewById(R.id.push_text);
		pushtext.setText(Constant.push_msg);
		Button ok = (Button) PushDialogplain.findViewById(R.id.updatebtn);
		pushclose.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				PushDialogplain.dismiss();
				Constant.push_msg = null;
				Constant.push_type = null;

			}
		});

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Constant.push_msg = null;

				PushDialogplain.dismiss();

			}
		});
		PushDialogplain.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		PushDialogplain.setCancelable(true);
	}

	private void PushDialog() {

		PushDialog = new Dialog(MainActivity.this);
		PushDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		PushDialog.setContentView(R.layout.push_dialog);
		ImageView pushclose = (ImageView) PushDialog
				.findViewById(R.id.pushClose);
		TextView pushtext = (TextView) PushDialog.findViewById(R.id.push_text);
		pushtext.setText(Constant.push_msg);
		Button updatebtn = (Button) PushDialog.findViewById(R.id.updatebtn);
		pushclose.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				PushDialog.dismiss();
				Constant.push_msg = null;
				Constant.push_type = null;
			}
		});

		updatebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("market://details?id=com.globussoft.spacedebris"));
				startActivity(intent);
				Constant.push_msg = null;
				Constant.push_type = null;
				PushDialog.dismiss();
			}
		});
		PushDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		PushDialog.setCancelable(true);

	}

	private void PushDialog1() {

		PushDialog1 = new Dialog(MainActivity.this);
		PushDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		PushDialog1.setContentView(R.layout.push_dialog1);
		ImageView pushclose = (ImageView) PushDialog1
				.findViewById(R.id.pushClose1);
		TextView pushtext = (TextView) PushDialog1
				.findViewById(R.id.push_text1);
		pushtext.setText(Constant.push_msg);
		Button updatebtn = (Button) PushDialog1.findViewById(R.id.go);
		pushclose.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				PushDialog1.dismiss();
				Constant.push_msg = null;
				Constant.push_type = null;

			}
		});

		updatebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(Constant.URL));
				startActivity(intent);
				Constant.push_msg = null;
				Constant.push_type = null;
				PushDialog1.dismiss();
			}
		});
		PushDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		PushDialog1.setCancelable(true);

	}

	@Override
	public void showPushDialog() {

		if (Constant.push_msg != null) {
			System.out.println("push msgh" + Constant.push_msg);
			runOnUiThread(new Runnable() {

				@Override
				public void run() {

					if (Constant.push_type != null) {
						PushDialog1();
						if (Constant.push_type.equals("1")) {
							PushDialog.show();
						} else if (Constant.push_type.equals("2")) {

							PushDialog1.show();
						} else if (Constant.push_type.equals("3")) {
							PushDialogplain.show();
							if (Constant.push_type != null) {
								System.out.println("Constant.extralife="
										+ Constant.extralife);
								System.out
										.println("AssetLoader.getExtraLife()="
												+ AssetLoader.getExtraLife());
								int totallife = AssetLoader.getExtraLife()
										+ Constant.extralife;
								System.out
										.println("life added equals to:::::::::::::"
												+ totallife);
								AssetLoader.setExtraLife(totallife);
							}

						}
					}
				}
			});

		}

	}

	// rank dialog
	private void InitDialog2() {

		rankdialog = new Dialog(MainActivity.this);
		rankdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		rankdialog.setContentView(R.layout.rankdialog);
		Button Rbtn1 = (Button) rankdialog.findViewById(R.id.share);
		Button share = (Button) rankdialog.findViewById(R.id.play);

		ImageView Rclose = (ImageView) rankdialog.findViewById(R.id.close);
		ranklevelname = (TextView) rankdialog.findViewById(R.id.level);
		dynamictext = (TextView) rankdialog.findViewById(R.id.dynamic);
		scoreext = (TextView) rankdialog.findViewById(R.id.score);
		listview = (HorizontalListView) rankdialog
				.findViewById(R.id.horizontalListView);

		Rbtn1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				rankdialog.dismiss();
				rankBackInterface.clickplay();

			}
		});

		Rclose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				rankdialog.dismiss();

			}
		});

		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String updatedlevel = level + "";
				completeLevelFeed(updatedlevel, score);

			}
		});

		rankdialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		rankdialog.setCancelable(true);

	}

	// frienddialog

	private void InitDialog3() {
		friendlistDialog = new Dialog(MainActivity.this);
		friendlistDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		friendlistDialog.setContentView(R.layout.friendlist);
		friendlistview = (GridView) friendlistDialog
				.findViewById(R.id.friendlistgrid);

		friendlistDialog.setCancelable(true);
		friendlistDialog.getWindow()
				.setBackgroundDrawable(new ColorDrawable(0));
		ImageView sendbtn = (ImageView) friendlistDialog
				.findViewById(R.id.sendbtn);
		ImageView cancel = (ImageView) friendlistDialog
				.findViewById(R.id.closebtn);
		// TextView text=(TextView) friendlistDialog.findViewById(R.id.tagtext);
		counter = (TextView) friendlistDialog.findViewById(R.id.counter);
		/*
		 * text.setText(Html.fromHtml("<b><font color=\"#262222\">" +
		 * "Ask Your friend for extra lives."+"</font></b>"));
		 */
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				friendlistDialog.cancel();
			}
		});

		sendbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				friendlistDialog.hide();
				selectedfriend.clear();

				for (int i = 0; i < invitable_friend_list_new.size(); i++) {

					if (flistadapter.mCheckStates.get(i)) {

						invited_friend_list_name.add(invitable_friend_list_new
								.get(i).getName());

						selectedfriend.add(invitable_friend_list_new.get(i)
								.getFB_ID());
					}
				}

				System.out.println("#####after clicking share btn  btn="
						+ invited_friend_list_name.toString());
				shareStorydialog();

			}
		});

	}

	@Override
	public void topThreeScore(final int numberlevel,
			final TopScoreReturnBack topScoreReturnBack) {

		if (detector.isConnectingToInternet() == true && sessionStatus) {

			this.topScoreReturnBack = topScoreReturnBack;
			level1 = numberlevel + "";

			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					waitDialog = new Dialog(MainActivity.this,
							R.style.PauseDialog);
					waitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					waitDialog.setContentView(R.layout.dialog);
					// ADMOB INITIALISAION
					// mAdView = (AdView) waitDialog.findViewById(R.id.adView);

					Button btn1 = (Button) waitDialog.findViewById(R.id.play);
					ImageView close = (ImageView) waitDialog
							.findViewById(R.id.close);
					levelname = (TextView) waitDialog.findViewById(R.id.level);
					first_listview = (HorizontalListView) waitDialog
							.findViewById(R.id.horizontalListView);
					nofriend_status = (TextView) waitDialog
							.findViewById(R.id.textView1);
					nofriend_status.setVisibility(View.INVISIBLE);
					first_listview.setVisibility(View.INVISIBLE);

					btn1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							waitDialog.dismiss();
							topScoreReturnBack.playclick();
						}
					});
					close.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							waitDialog.dismiss();
						}
					});
					waitDialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(0));
					waitDialog.setCancelable(true);

					levelname.setText("Level " + level1);

					Playerdata_Array2.clear();
					waitDialog.show();

				}
			});

			if (Session.getActiveSession() != null) {
				ParseQuery<ParseObject> query = ParseQuery
						.getQuery("GameScore");
				query.whereEqualTo("Level", numberlevel);
				query.whereContainedIn("PlayerFacebookID", friendid_list);
				query.addDescendingOrder("Score");
				query.findInBackground(new FindCallback<ParseObject>() {
					@Override
					public void done(List<ParseObject> scoreList,
							ParseException e) {
						if (e == null) {
							Log.d("score", "Retrieved " + scoreList.size()
									+ " scores");
							if (scoreList.size() > 0) {
								ArrayList<ListMOdel> listyoyo = new ArrayList<ListMOdel>();
								listyoyo.clear();
								Playerdata_Array.clear();
								for (int i = 0; i < scoreList.size(); i++) {
									ListMOdel model = new ListMOdel();
									model.setFB_ID(scoreList.get(i).getString(
											"PlayerFacebookID"));
									model.setScore(scoreList.get(i).getInt(
											"Score"));
									model.setName(scoreList.get(i).getString(
											"Name"));
									for (int j = 0; j < Constant.list.size(); j++) {
										if (scoreList
												.get(i)
												.getString("PlayerFacebookID")
												.equals(Constant.list.get(j)
														.getFB_ID())) {

											break;
										}

									}
									listyoyo.add(model);
								}
								first_listview
										.setAdapter(new CustomListAdapter(
												MainActivity.this, listyoyo));
								first_listview.setVisibility(View.VISIBLE);
								waiting.setVisibility(View.INVISIBLE);
								nofriend_status.setVisibility(View.INVISIBLE);

								// new DownloadImage().execute();
							} else {
								waiting.setVisibility(View.INVISIBLE);
								nofriend_status.setVisibility(View.VISIBLE);
								first_listview.setVisibility(View.INVISIBLE);
								// Toast.makeText(getApplicationContext(),
								// "list empty", Toast.LENGTH_SHORT).show();
							}
						}
					}
				});
			}
		} else {
			topScoreReturnBack.playclick();
		}

	}

	private class createBitmap extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			String picture = "https://graph.facebook.com/" + fbuserid
					+ "/picture?type=small";

			try {
				URL url = new URL(picture);
				HttpURLConnection connection = null;
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				// InputStream input = connection.getInputStream();
				myimagebitmap = BitmapFactory.decodeStream(url.openConnection()
						.getInputStream());
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

	}

	/*
	 * private class createBitmapofuser extends AsyncTask<Void, Void, Void> {
	 * 
	 * @Override protected Void doInBackground(Void... params) { String picture
	 * = "https://graph.facebook.com/" + senderID + "/picture?type=normal";
	 * 
	 * try { URL url = new URL(picture); HttpURLConnection connection =
	 * (HttpURLConnection) url.openConnection(); connection.setDoInput(true);
	 * connection.connect(); bitmap2 =
	 * BitmapFactory.decodeStream(url.openConnection() .getInputStream()); }
	 * catch (MalformedURLException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); }
	 * 
	 * 
	 * }
	 * 
	 * @Override protected void onPostExecute(Void result) { // TODO
	 * Auto-generated method stub super.onPostExecute(result);
	 * life_request_image.setImageBitmap(bitmap2);
	 * 
	 * }
	 * 
	 * }
	 */

	@Override
	public void RankExecution(final int level, final int score,
			RankBackInterface rankBackInterface) {
		this.rankBackInterface = rankBackInterface;

		final String levelR = level + "";

		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				rankdialog.show();

				ranklevelname.setText("Level : " + levelR);
				scoreext.setText("Score : " + score);
				if (detector.isConnectingToInternet() == true && sessionStatus) {

					// getLocalRank(level, score);

					// getLocalRank(level, score);
					if (Session.getActiveSession() != null) {
						ParseQuery<ParseObject> query = ParseQuery
								.getQuery("GameScore");
						query.whereEqualTo("Level", level);
						query.whereContainedIn("PlayerFacebookID",
								friendid_list);
						query.addDescendingOrder("Score");
						query.findInBackground(new FindCallback<ParseObject>() {
							@Override
							public void done(List<ParseObject> scoreList,
									ParseException e) {
								if (e == null) {
									Log.d("score",
											"Retrieved " + scoreList.size()
													+ " scores");
									if (scoreList.size() > 0) {

										System.out.println("Size of array is "
												+ scoreList.size());
										Playerdata_Array2.clear();
										for (int i = 0; i < scoreList.size(); i++) {
											ListMOdel model = new ListMOdel();
											model.setFB_ID(scoreList.get(i)
													.getString(
															"PlayerFacebookID"));
											model.setScore(scoreList.get(i)
													.getInt("Score"));
											model.setName(scoreList.get(i)
													.getString("Name"));
											for (int j = 0; j < Constant.list
													.size(); j++) {
												if (scoreList
														.get(i)
														.getString(
																"PlayerFacebookID")
														.equals(Constant.list
																.get(j)
																.getFB_ID())) {

													break;
												}

											}
											Playerdata_Array2.add(model);

										}
										adapter = new CustomListAdapter(
												getApplicationContext(),
												Playerdata_Array2);
										listview.setAdapter(adapter);
										listview.setVisibility(View.VISIBLE);
										waiting.setVisibility(View.INVISIBLE);

									} else {
										waiting.setVisibility(View.INVISIBLE);

										listview.setVisibility(View.INVISIBLE);
									}
								}
							}
						});
					}
				}
			}
		});

	}

	public void getLocalRank(int level, int score) {
		boolean status = false;
		int index = 0;

		for (int i = 0; i < Playerdata_Array2.size(); i++) {
			if (fbusername.contains(Playerdata_Array2.get(i).getName())) {
				if (score > Playerdata_Array2.get(i).getScore())
					Playerdata_Array2.get(i).setScore(score);
				status = true;

			}
		}
		if (status == false) {
			ListMOdel listmodel = new ListMOdel();
			listmodel.setName(fbusername);
			listmodel.setScore(score);
			listmodel.setFB_ID(fbuserid);
			listmodel.setImage(myimagebitmap);
			Playerdata_Array2.add(listmodel);
		}

		Collections.sort(Playerdata_Array2, new Comparator<ListMOdel>() {

			@Override
			public int compare(ListMOdel p1, ListMOdel p2) {
				// TODO Auto-generated method stub
				return p2.getScore() - p1.getScore();
			}
		});

		adapter = new CustomListAdapter(getApplicationContext(),
				Playerdata_Array2);
		listview.setAdapter(adapter);

	}

	private class creatingAllFriendImages extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			totalplayers_with_image.clear();

			for (int i = 0; i < friend_list.size(); i++) {
				String finalURL = friend_list.get(i).getUrl();
				URL url = null;
				try {
					url = new URL(finalURL);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpURLConnection connection = null;
				try {
					connection = (HttpURLConnection) url.openConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				connection.setDoInput(true);
				try {
					connection.connect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InputStream input = null;
				try {
					input = connection.getInputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Bitmap myBitmap = null;
				try {
					myBitmap = BitmapFactory.decodeStream(url.openConnection()
							.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FriendListModel model = new FriendListModel();

				model.setName(friend_list.get(i).getName());
				model.setFB_ID(friend_list.get(i).getFB_ID());
				totalplayers_with_image.add(model);

			}
			return null;
		}

	}

	// onclicking fb button , connect to facebook
	@Override
	public void facebookconnectExecution() {

		facebook_first = true;
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				fbconnect();

			}
		});

	}

	// chartboost

	@Override
	public void ChartBoost() {

		if (detector.isConnectingToInternet()) {

			Constantsvalue.ishitdebris = false;
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							loadAdmob();
							showAdDialog();

						}
					});

				}
			});
		} else {
			Constantsvalue.chatboosterdetail = 2;
			Constantsvalue.didseenvideo = false;
		}

	}

	/*
	 * public void chatboosttimer() { countime = 0; timer2 = new Timer();
	 * timer2.scheduleAtFixedRate(new TimerTask() {
	 * 
	 * @Override public void run() { countime++;
	 * System.out.println("counttime==" + countime); if (countime > 8) {
	 * System.out.println("timer running=" + countime); timer2.cancel();
	 * 
	 * Constantsvalue.chatboosterdetail = 2; Constantsvalue.didseenvideo =
	 * false;
	 * 
	 * displayInterstitial();
	 * 
	 * runOnUiThread(new Runnable() {
	 * 
	 * @Override public void run() { progressDialog.hide();
	 * Chartboost.onStop(MainActivity.this); Toast.makeText( MainActivity.this,
	 * "Sorry no video ads" + "\n\n" + "available at this time.",
	 * Toast.LENGTH_SHORT).show();
	 * 
	 * } }); } } }, 100, 1000);
	 * 
	 * }
	 */
	// chatboost video ad.
	private ChartboostDelegate delegate = new ChartboostDelegate() {

		@Override
		public boolean shouldRequestInterstitial(String location) {
			Log.i(TAG, "SHOULD REQUEST INTERSTITIAL '"
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public boolean shouldDisplayInterstitial(String location) {
			Log.i(TAG, "SHOULD DISPLAY INTERSTITIAL '"
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public void didCacheInterstitial(String location) {
			Log.i(TAG, "DID CACHE INTERSTITIAL '"
					+ (location != null ? location : "null"));
		}

		@Override
		public void didFailToLoadInterstitial(String location,
				CBImpressionError error) {
			if (Constantsvalue.isfromshare) {
				Constantsvalue.isfromshare = false;
				// RefillLife();
			}

		}

		@Override
		public void didDismissInterstitial(String location) {
			Log.i(TAG, "DID DISMISS INTERSTITIAL: "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didCloseInterstitial(String location) {
			Log.i(TAG, "DID CLOSE INTERSTITIAL: "
					+ (location != null ? location : "null"));

			if (Constantsvalue.isfromshare) {
				Constantsvalue.isfromshare = false;
				// RefillLife();
			}
		}

		@Override
		public void didClickInterstitial(String location) {
			Log.i(TAG, "DID CLICK INTERSTITIAL: "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didDisplayInterstitial(String location) {
			Log.i(TAG, "DID DISPLAY INTERSTITIAL: "
					+ (location != null ? location : "null"));
		}

		@Override
		public boolean shouldRequestMoreApps(String location) {
			Log.i(TAG, "SHOULD REQUEST MORE APPS: "
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public boolean shouldDisplayMoreApps(String location) {
			Log.i(TAG, "SHOULD DISPLAY MORE APPS: "
					+ (location != null ? location : "null"));
			return true;
		}

		@Override
		public void didFailToLoadMoreApps(String location,
				CBImpressionError error) {

		}

		@Override
		public void didCacheMoreApps(String location) {
			Log.i(TAG, "DID CACHE MORE APPS: "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didDismissMoreApps(String location) {
			Log.i(TAG, "DID DISMISS MORE APPS "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didCloseMoreApps(String location) {
			Log.i(TAG, "DID CLOSE MORE APPS: "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didClickMoreApps(String location) {
			Log.i(TAG, "DID CLICK MORE APPS: "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didDisplayMoreApps(String location) {
			Log.i(TAG, "DID DISPLAY MORE APPS: "
					+ (location != null ? location : "null"));
		}

		@Override
		public void didFailToRecordClick(String uri, CBClickError error) {
			Log.i(TAG, "DID FAILED TO RECORD CLICK "
					+ (uri != null ? uri : "null") + ", error: " + error.name());
			Toast.makeText(
					getApplicationContext(),
					"FAILED TO RECORD CLICK " + (uri != null ? uri : "null")
							+ ", error: " + error.name(), Toast.LENGTH_SHORT)
					.show();
		}

		@Override
		public boolean shouldDisplayRewardedVideo(String location) {
			Log.i(TAG, String.format("SHOULD DISPLAY REWARDED VIDEO: '%s'",
					(location != null ? location : "null")));
			return true;
		}

		@Override
		public void didCacheRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CACHE REWARDED VIDEO: '%s'",
					(location != null ? location : "null")));

		}

		@Override
		public void didFailToLoadRewardedVideo(String location,
				CBImpressionError error) {
			Log.i(TAG, String.format(
					"DID FAIL TO LOAD REWARDED VIDEO: '%s', Error:  %s",
					(location != null ? location : "null"), error.name()));
			// progressDialog.hide();
			Constantsvalue.chatboosterdetail = 2;
			Constantsvalue.didseenvideo = false;
			System.out.println("chatboost error=" + error);
			displayInterstitial();

			runOnUiThread(new Runnable() {

				@Override
				public void run() {

					Toast.makeText(
							MainActivity.this,
							"Sorry no video ads" + "\n\n"
									+ "available at this time.",
							Toast.LENGTH_SHORT).show();

				}
			});
		}

		@Override
		public void didDismissRewardedVideo(String location) {
			Log.i(TAG, String.format("DID DISMISS REWARDED VIDEO '%s'",
					(location != null ? location : "null")));
			Constantsvalue.chatboosterdetail = 2;
			// GameScreen.setAdClosestatus(true);
			// Constantsvalue.didseenvideo=false;

		}

		@Override
		public void didCloseRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CLOSE REWARDED VIDEO '%s'",
					(location != null ? location : "null")));
			/*
			 * if(AssetLoader.getExtraLife()<5) {
			 * AssetLoader.setExtraLife(AssetLoader.getExtraLife()+1); //
			 * GameWorld.adresume(); // GameScreen.adseen=true; }
			 */
		}

		@Override
		public void didClickRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CLICK REWARDED VIDEO '%s'",
					(location != null ? location : "null")));
		}

		@Override
		public void didCompleteRewardedVideo(String location, int reward) {
			Log.i(TAG, String.format(
					"DID COMPLETE REWARDED VIDEO '%s' FOR REWARD %d",
					(location != null ? location : "null"), reward));
			Constantsvalue.didseenvideo = true;

		}

		@Override
		public void didDisplayRewardedVideo(String location) {
			Log.i(TAG, String.format(
					"DID DISPLAY REWARDED VIDEO '%s' FOR REWARD", location));
			timer2.cancel();
			// progressDialog.hide();

		}

		@Override
		public void willDisplayVideo(String location) {
			Log.i(TAG, String.format("WILL DISPLAY VIDEO '%s", location));
		}

	};

	@Override
	protected void onStart() {
		super.onStart();
		Chartboost.onStart(this);
		FlurryAgent.onStartSession(MainActivity.this);
	}

	@Override
	protected void onStop() {

		try {
			AssetLoader.setSystemTime(System.currentTimeMillis());
			setalaram();
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("!!!!!!!!!!!!!!!!!!!Main activity onstop");
		super.onStop();
		Chartboost.onStop(this);
		FlurryAgent.onEndSession(MainActivity.this);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();

		Chartboost.onPause(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
		if (mHelper != null)
			mHelper.dispose();
		mHelper = null;
		Chartboost.onDestroy(this);

		/*
		 * if (detector.isConnectingToInternet()) {
		 * 
		 * 
		 * Session session = Session.getActiveSession(); if (session != null){
		 * session.closeAndClearTokenInformation();
		 * 
		 * } }
		 */
	}

	@Override
	protected void onResume() {

		// TODO Auto-generated method stub

		super.onResume();

		Chartboost.onResume(this);

		uiHelper.onResume();

		Uri intentUri = this.getIntent().getData();
		if (intentUri != null) {
			String requestIdParam = intentUri.getQueryParameter("request_ids");
			if (requestIdParam != null) {

				// System.out.println("request Param get from URI is not null");
				String array[] = requestIdParam.split(",");
				requestId = array[0];
				// Log.i(TAG, "Request id: "+requestId);
				if (requestId != null) { // Getting credential of facebook

					Session.openActiveSession(MainActivity.this, true,
							new StatusCallback() {
								@Override
								public void call(Session session,
										SessionState state, Exception exception) {
									if (session.isOpened()) {
										// getRequestData(requestId);

									}
								}
							});

				}
			}
		}

	}

	public void friendStatus() {

		status_Array.clear();
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// initFriendStatus();
			}
		});
		status_Array.clear();
		status_Array1.clear();

		if (detector.isConnectingToInternet() == true && sessionStatus) {

			ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");

			query.whereContainedIn("PlayerFacebookID", friendid_list);
			query.addDescendingOrder("Level");
			query.findInBackground(new FindCallback<ParseObject>() {

				@Override
				public void done(List<ParseObject> statusList, ParseException e) {

					if (e == null) {
						if (statusList.size() > 0) {
							Mainlist.clear();
							sublist.clear();
							for (int i = 0; i < statusList.size(); i++) {
								if (i == 0) {
									ListMOdel model = new ListMOdel();
									model.setFB_ID(statusList.get(i).getString(
											"PlayerFacebookID"));
									model.setLevel(statusList.get(i).getInt(
											"Level"));
									model.setName(statusList.get(i).getString(
											"Name"));
									for (int j = 0; j < Constant.list.size(); j++) {
										if (statusList
												.get(i)
												.getString("PlayerFacebookID")
												.equals(Constant.list.get(j)
														.getFB_ID())) {

											break;
										}
									}
									Mainlist.add(model);
									sublist.add(statusList.get(i).getString(
											"PlayerFacebookID"));

								} else {
									if (!sublist.contains(statusList.get(i)
											.getString("PlayerFacebookID"))) {
										ListMOdel model = new ListMOdel();
										model.setFB_ID(statusList.get(i)
												.getString("PlayerFacebookID"));
										model.setLevel(statusList.get(i)
												.getInt("Level"));
										model.setName(statusList.get(i)
												.getString("Name"));
										for (int j = 0; j < Constant.list
												.size(); j++) {
											if (statusList
													.get(i)
													.getString(
															"PlayerFacebookID")
													.equals(Constant.list
															.get(j).getFB_ID())) {

												break;
											}
										}
										Mainlist.add(model);
										sublist.add(statusList.get(i)
												.getString("PlayerFacebookID"));
									}
								}
							}
							statusListview.setAdapter(new StatusListAdapter(
									MainActivity.this, Mainlist));
							statusListview.setVisibility(View.VISIBLE);
							waiting.setVisibility(View.INVISIBLE);
							nodatatext.setVisibility(View.INVISIBLE);
						} else {
							statusListview.setVisibility(View.INVISIBLE);
							waiting.setVisibility(View.INVISIBLE);
							nodatatext.setVisibility(View.VISIBLE);
						}
					}
				}
			});
		}
	}

	/*
	 * public void initFriendStatus() { statusDialog = new
	 * Dialog(MainActivity.this);
	 * statusDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	 * statusDialog.setContentView(R.layout.friend_status); //ADMOB
	 * INITIALISAION // mAdView = (AdView) waitDialog.findViewById(R.id.adView);
	 * 
	 * Button btn1 = (Button) statusDialog.findViewById(R.id.play);
	 * 
	 * statusListview=(ListView) statusDialog.findViewById(R.id.listview1);
	 * waiting = (ProgressBar) statusDialog.findViewById(R.id.progressBar1);
	 * nodatatext=(TextView) statusDialog.findViewById(R.id.nodataText);
	 * nodatatext.setVisibility(View.GONE);
	 * statusListview.setVisibility(View.INVISIBLE);
	 * waiting.setVisibility(View.VISIBLE); btn1.setOnClickListener(new
	 * OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub System.out.println("inside play click"); statusDialog.dismiss(); //
	 * topScoreReturnBack.playclick(); } });
	 * 
	 * statusDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
	 * statusDialog.setCancelable(true); }
	 */

	@Override
	public void showStatus()

	{
		if (detector.isConnectingToInternet() == true && sessionStatus) {
			handler.post(new Runnable() {

				@Override
				public void run() {

					statusDialog = new Dialog(MainActivity.this);
					statusDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					statusDialog.setContentView(R.layout.friend_status);
					nodatatext = (TextView) statusDialog
							.findViewById(R.id.nodataText);

					Button btn1 = (Button) statusDialog.findViewById(R.id.play);
					statusListview = (ListView) statusDialog
							.findViewById(R.id.listview1);
					waiting = (ProgressBar) statusDialog
							.findViewById(R.id.progressBar1);
					statusListview.setVisibility(View.INVISIBLE);
					waiting.setVisibility(View.VISIBLE);
					btn1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							statusDialog.dismiss();
							// topScoreReturnBack.playclick();
						}
					});

					statusDialog.getWindow().setBackgroundDrawable(
							new ColorDrawable(0));
					statusDialog.setCancelable(true);

					if (Constantsvalue.isShownStatusDialog) {
						statusDialog.show();
						Constantsvalue.isShownStatusDialog = false;
					}

				}
			});
			friendStatus();
		}
	}

	public void askPermission() {
		Session session = Session.getActiveSession();
		if (session != null) {
			// Check for publish permissions
			List<String> permissions = session.getPermissions();
			if (!isSubsetOf(PUBLISH_PERMISSIONS, permissions)) {

				Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(
						MainActivity.this, PUBLISH_PERMISSIONS);
				session.requestNewPublishPermissions(newPermissionsRequest);
			}

		}
	}

	@Override
	public void showbanner() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (detector.isConnectingToInternet()) {
					// adMOb-adRequest
					adRequest = new AdRequest.Builder().build();
					adView.loadAd(adRequest);
				}

			}
		});

	}

	@Override
	public void destroybanner() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (detector.isConnectingToInternet()) {
					adView.destroy();
				}
			}
		});

	}

	protected void showAdDialog() {

		Constantsvalue.isclickoncancel = false;
		dialog = new Dialog(MainActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCancelable(false);
		dialog.setContentView(R.layout.back_dialog);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		ImageView accept, cancel;
		TextView msg;
		cancel = (ImageView) dialog.findViewById(R.id.back_exit);
		accept = (ImageView) dialog.findViewById(R.id.back_exitcancel);
		msg = (TextView) dialog.findViewById(R.id.exittextview);

		life_request_image = (ImageView) dialog.findViewById(R.id.imageView1);
		msg.setText("Do you want to save your Phantom?");

		accept.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				dialog.dismiss();
				/*
				 * if
				 * (Chartboost.hasRewardedVideo(CBLocation.LOCATION_GAMEOVER))
				 * 
				 * Chartboost.showRewardedVideo(CBLocation.LOCATION_GAMEOVER);
				 */

				// progressDialog = ProgressDialog.show(MainActivity.this, "",
				// "Loading....", true, false);
				// chatboosttimer();

				Constantsvalue.chatboosterdetail = 2;
				Constantsvalue.didseenvideo = false;

				displayInterstitial();
				Toast.makeText(
						MainActivity.this,
						"Sorry no video ads" + "\n\n"
								+ "available at this time.", Toast.LENGTH_SHORT)
						.show();

			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				// Constantsvalue.isclickoncancel=true;
				Constantsvalue.chatboosterdetail = 2;
				Constantsvalue.didseenvideo = false;

				displayInterstitial();

			}
		});
		dialog.show();
	}

	@Override
	public void logout() {

		if (detector.isConnectingToInternet()) {

			Session session = Session.getActiveSession();
			if (session != null) {
				session.closeAndClearTokenInformation();
				fbstatus = false;
				editor = preferences.edit();
				editor.putBoolean("fbstatus", false);
				editor.commit();
				Constantsvalue.isconnecttofb = false;
				sessionStatus = false;
			}
		} else {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(getApplicationContext(),
							"Connect to Internet.", Toast.LENGTH_SHORT).show();

				}
			});

		}
	}

	// interstitial.
	@Override
	public void loadAdmob() {
		/*runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (detector.isConnectingToInternet()) {

					adRequest = new AdRequest.Builder().build();
					interstitial.loadAd(adRequest);
				}
			}
		});*/

	}

	@Override
	public void displayInterstitial() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (detector.isConnectingToInternet()) {
					if (interstitial.isLoaded()) {
						interstitial.show();
					}

				}
			}
		});
	}

	class downloadimageaa extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			String name = params[1];
			String fbid = params[2];
			URL url1 = null;
			try {
				url1 = new URL(url);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HttpURLConnection connection = null;
			try {
				connection = (HttpURLConnection) url1.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			connection.setDoInput(true);
			try {
				connection.connect();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			InputStream input = null;
			try {
				input = connection.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Bitmap myBitmap = null;
			try {

				myBitmap = BitmapFactory.decodeStream(url1.openConnection()
						.getInputStream());

				FriendListModel temp = new FriendListModel();
				temp.setFB_ID(fbid);

				temp.setName(name);

				Constant.list.add(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return myBitmap;
		}

	}

	// invitable friend image download

	class downloadinvitable_image extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			String name = params[1];
			String fbid = params[2];
			URL url1 = null;
			try {
				url1 = new URL(url);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HttpURLConnection connection = null;
			try {
				connection = (HttpURLConnection) url1.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			connection.setDoInput(true);
			try {
				connection.connect();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			InputStream input = null;
			try {
				input = connection.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Bitmap myBitmap = null;
			try {

				myBitmap = BitmapFactory.decodeStream(url1.openConnection()
						.getInputStream());

				FriendListModel temp = new FriendListModel();
				temp.setFB_ID(fbid);

				temp.setName(name);

				totalplayers_with_image.add(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return myBitmap;
		}

	}

	public void shareStorydialog() {

		progressbar_graphstory = ProgressDialog.show(MainActivity.this, "",
				"Sharing....", true, false);

		askPermission();
		RequestBatch requestBatch = new RequestBatch();
		// graph story testspacetwo
		OpenGraphObject book = OpenGraphObject.Factory
				.createForPost("spacedebris:live");
		book.setProperty("type", "spacedebris:live");
		book.setTitle("Request for a Life");
		book.setProperty("image", IMAGEURL);
		book.setProperty("link", PLAYSTOREURL);
		book.setProperty(
				"description",
				"Hi friends, Please help me to clear this game with a life. Join it and give me a life please.");

		OpenGraphAction ogAction = GraphObject.Factory
				.create(OpenGraphAction.class);

		// setprpoerty name should be same as object name e.g:friend
		ogAction.setProperty("live", book);
		ogAction.setType("spacedebris:ask");
		ogAction.setExplicitlyShared(true);
		List<GraphUser> tags = new ArrayList<GraphUser>();
		for (int i = 0; i < selectedfriend.size(); i++) {
			GraphUser user = GraphObject.Factory.create(GraphUser.class);
			user.setId(selectedfriend.get(i));
			tags.add(user);
		}

		ogAction.setTags(tags);

		Request.Callback actionCallback = new Request.Callback() {
			@Override
			public void onCompleted(Response response) {
				FacebookRequestError error = response.getError();
				if (error != null) {
					System.out.println("ERROR=======" + error);
					System.out
							.println("ERROR=======" + error.getSubErrorCode());

					progressbar_graphstory.hide();
					Toast.makeText(getApplicationContext(),
							"Something Went wrong,Please try again",
							Toast.LENGTH_SHORT).show();

				} else {
					Gson gson = new Gson();
					String jsonText = gson.toJson(invited_friend_list_name);
					editor = preferences.edit();
					editor.putString("invited_list", jsonText);
					editor.commit();

					progressbar_graphstory.hide();
					String actionId = null;
					try {
						JSONObject graphResponse = response.getGraphObject()
								.getInnerJSONObject();
						actionId = graphResponse.getString("id");
					} catch (JSONException e) {
						Log.i("Tag", "JSON error " + e.getMessage());
					}

					AssetLoader.setExtraLife(2);

					AlertDialog alertDialog = new AlertDialog.Builder(
							MainActivity.this).create();

					alertDialog.setIcon(R.drawable.icon);
					if (AssetLoader.getExtraLife() == 0) {
						alertDialog.setTitle("No Lives Added");
						alertDialog
								.setMessage("Please tag atleast five friends to gain lives.");
					} else {
						alertDialog.setTitle("Lives Added");
						alertDialog
								.setMessage("Now You have enough lives to continue the game");
						Constantsvalue.isfbshared = true;
					}
					alertDialog.show();

					// alertDialog.show();
				}
			}
		};
		Request actionRequest = Request.newPostOpenGraphActionRequest(
				Session.getActiveSession(), ogAction, actionCallback);
		requestBatch.add(actionRequest);
		requestBatch.executeAsync();
	}

	@Override
	public void purchaseLife() {

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mHelper.launchPurchaseFlow(MainActivity.this, ITEM_SKU, 10001,
						mPurchaseFinishedListener, "mypurchasetoken");

			}
		});

	}

	@Override
	public void joincontest() {

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("http://www.globusgames.com/space-debris-phantom-leader-board/"));
				startActivity(intent);

			}
		});
	}

	// *****************************SQLLITE DATA
	// BASE******************************
	public void addLevelScore(ModelUserDatas modelUserDatas) {

		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");

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

		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");

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
		cursor.close();
		return modelUserDatas;
	}

	public ArrayList<ModelUserDatas> getUserData(String userId) {

		ArrayList<ModelUserDatas> modelUserDatasAllLevels = new ArrayList<ModelUserDatas>();

		String query = "SELECT * FROM " + table_name + " WHERE " + KEY_UserID
				+ " = '" + userId + "'";
		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");

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

		cursor.close();

		return modelUserDatasAllLevels;
	}

	public void updateUserLevelScore(ModelUserDatas modelUserDatas) {

		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");
		String updateQuery = "UPDATE " + table_name + " SET " + KEY_Score
				+ " = '" + modelUserDatas.getScore() + "', " + KEY_Time
				+ " = '" + modelUserDatas.getTime() + "' WHERE " + KEY_UserID
				+ " = '" + modelUserDatas.getUserid() + "' AND " + KEY_Level
				+ " = '" + modelUserDatas.getLevel() + "'";
		System.out.println(updateQuery);
		database.execSQL(updateQuery);
	}

	public void updateModel(ModelUserDatas modelUserDatas) {

		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");

		String updateQuery = "UPDATE " + table_name + " SET " + KEY_UserID
				+ " = '" + modelUserDatas.getUserid() + "', " + KEY_Username
				+ " = '" + modelUserDatas.getUsername() + "' WHERE "
				+ KEY_UserID + " = '" + UserId + "' AND " + KEY_Username
				+ " = '" + UserName + "'";

		System.out.println(updateQuery);

		database.execSQL(updateQuery);

	}

	public void deleteAllRows() {
		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");
		String query = "DELETE FROM " + table_name;
		System.out.println(query);
		database.execSQL(query);
	}

	public void deleteThisUserData(String userID) {
		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");
		String query = "DELETE FROM " + table_name + " WHERE " + KEY_UserID
				+ " = " + userID;
		System.out.println(query);
		database.execSQL(query);
	}

	public void CreateTable() {

		String querry = "CREATE TABLE IF NOT EXISTS " + table_name + "("
				+ KEY_UserID + " TEXT," + KEY_Username + " TEXT," + KEY_Level
				+ " INTEGER," + KEY_Score + " INTEGER," + KEY_Time + " TEXT)";

		System.out.println(querry);

		SQLiteDatabase database = localData
				.getWritableDatabase("spacedebrisdatabase");

		database.execSQL(querry);

	}

	void setalaram() {
		int lifecount = 10 - AssetLoader.getExtraLife();
		int alaramtime = 0;
		if (AssetLoader.getExtraLife() < 10) {
			alaramtime = lifecount * 300000;
			alarmManager.set(AlarmManager.RTC_WAKEUP,
					AssetLoader.getSystemTime() + alaramtime, pendingIntent);
		}
	}

	void setlifeinparse() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Players");
		query.whereEqualTo("PlayerFacebookID", fbuserid);

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> list, ParseException e) {

				if (e == null) {
					if (list.size() > 0) {
						System.out.println(list.toString());
						ParseQuery<ParseObject> parseQuery = ParseQuery
								.getQuery("Players");
						parseQuery.getInBackground(list.get(0).getObjectId(),
								new GetCallback<ParseObject>() {

									@Override
									public void done(ParseObject obj,
											ParseException arg1) {
										if (arg1 == null) {
											obj.put("Life",
													AssetLoader.getExtraLife());
											obj.saveInBackground(new SaveCallback() {

												@Override
												public void done(
														ParseException arg0) {

												}
											});
										}
									}

								});

					} else {
						ParseObject parse = new ParseObject("Players");
						parse.put("PlayerFacebookID", fbuserid);
						parse.put("Name", fbusername);
						parse.put("Life", AssetLoader.getExtraLife());

						parse.saveInBackground(new SaveCallback() {
							@Override
							public void done(ParseException arg0) {

							}
						});
					}
				}
			}
		});

	}

	void getlifefromparse() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Players");
		query.whereEqualTo("PlayerFacebookID", fbuserid);

		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> list, ParseException e) {

				if (e == null) {
					if (list.size() > 0) {
						AssetLoader.setExtraLife(list.get(0).getInt("Life"));
					}
				}
			}
		});

	}

	@Override
	public void synclife() {
		if (detector.isConnectingToInternet() == true && sessionStatus
				&& fbuserid.length() > 0) {

			setlifeinparse();

		}
	}

	// fblogin at nolife screen

	public void fbconnect_after_nolife() {

		if (detector.isConnectingToInternet() == true) {

			localDataArray_list.clear();
			friendlist.clear();
			friend_list.clear();
			Constant.list.clear();
			friendid_list.clear();

			// TODO Auto-generated method stub
			Session.openActiveSession(MainActivity.this, true,
					new Session.StatusCallback() {

						@Override
						public void call(Session session, SessionState state,
								Exception exception) {

							if (session.isOpened()) {
								fbstatus = true;
								editor = preferences.edit();
								editor.putBoolean("fbstatus", true);
								editor.commit();
								Constantsvalue.isconnecttofb = true;

								sessionStatus = true;

								Request.newMeRequest(session,
										new Request.GraphUserCallback() {

											@Override
											public void onCompleted(
													GraphUser user,
													Response response) {
												if (user != null) {

													try {
														fbusername = user
																.getName();
														fbuserid = user.getId();
													} catch (Exception e) {
														// TODO: handle
														// exception
													}

													new createBitmap()
															.execute();
													friendlist.add(fbuserid);
													friendid_list.add(fbuserid);

													editor = preferences.edit();
													editor.putString(
															"fbuserid",
															fbuserid);
													editor.putString(
															"fbusername",
															fbusername);
													editor.commit();

													FriendListModel model = new FriendListModel();
													String fbid = fbuserid;
													String name = fbusername;
													String url = "https://graph.facebook.com/"
															+ fbuserid
															+ "/picture?type=small";
													String[] aa = { url, name,
															fbid };
													/*
													 * new downloadimageaa()
													 * .execute(aa);
													 */
													model.setFB_ID(fbid);
													model.setName(name);
													model.setUrl(url);
													friend_list.add(model);

													// fetch level and store in
													// internal database
													ParseQuery<ParseObject> fetchlevelQurry = ParseQuery
															.getQuery("GameScore");
													fetchlevelQurry
															.whereEqualTo(
																	"PlayerFacebookID",
																	fbuserid);

													fetchlevelQurry
															.addDescendingOrder("Level");
													fetchlevelQurry
															.findInBackground(new FindCallback<ParseObject>() {
																@Override
																public void done(
																		List<ParseObject> LevelmaxList,
																		ParseException arg1) {

																	AssetLoader
																			.setLevel(getLocalLevel());

																	if (arg1 == null) {
																		if (LevelmaxList
																				.size() > 0) {

																			if (getLocalLevel() - 1 < LevelmaxList
																					.get(0)
																					.getInt("Level")) {
																				int parselevel = LevelmaxList
																						.get(0)
																						.getInt("Level");
																				AssetLoader
																						.setLevel(parselevel + 1);
																			}

																		}
																	}
																}
															});
													updateUser(fbuserid,
															fbusername);
													SyncData();

													ParseInstallation
															.getCurrentInstallation()
															.put("facebookId",
																	fbuserid);
													ParseInstallation
															.getCurrentInstallation()
															.saveInBackground(
																	new SaveCallback() {

																		@Override
																		public void done(
																				ParseException arg0) {
																			// TODO
																			// Auto-generated
																			// method
																			// stub

																		}
																	});

												}
											}
										}).executeAsync();

								// Check for publish permissions
								List<String> permissions = session
										.getPermissions();

								if (!isSubsetOf(PERMISSIONS, permissions)) {
									System.out.println("i am in permission");
									Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(
											MainActivity.this, PERMISSIONS);
									session.requestNewPublishPermissions(newPermissionsRequest);
									return;

								}

								// fetching friend list
								new Request(Session.getActiveSession(),
										"/me/friends", null, HttpMethod.GET,
										new Request.Callback() {

											@Override
											public void onCompleted(
													Response response) {

												GraphObject graphObject = response
														.getGraphObject();

												if (graphObject != null) {
													JSONObject jsonObject = graphObject
															.getInnerJSONObject();
													// System.out.println(jsonObject);

													try {
														JSONArray array = jsonObject
																.getJSONArray("data");
														for (int i = 0; i < array
																.length(); i++) {
															JSONObject object = (JSONObject) array
																	.get(i);

															friendlist.add(object
																	.getString("id"));
															friendid_list.add(object
																	.getString("id"));
															FriendListModel model = new FriendListModel();
															String fbid = object
																	.getString("id");
															String name = object
																	.getString("name");
															String url = "https://graph.facebook.com/"
																	+ fbid
																	+ "/picture?type=small";

															String[] aa = {
																	url, name,
																	fbid };
															/*
															 * new
															 * downloadimageaa()
															 * .execute(aa);
															 */

															model.setFB_ID(fbid);
															model.setName(name);
															model.setUrl(url);
															friend_list
																	.add(model);

														}

													} catch (JSONException e) {

														e.printStackTrace();
													}

													inviable_friend_nolife();
													// friendStatus();
												}

											}
										}).executeAsync();

								// store invitable friend

							}

						}
					});

			// send track to ganalyst
			Tracker t = ((Application) getApplication())
					.getTracker(TrackerName.APP_TRACKER);
			t.send(new HitBuilders.EventBuilder().setLabel("Facebook Log In")
					.build());

		}

		else {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(getApplicationContext(), "No Internet !!!",
							Toast.LENGTH_LONG).show();
				}
			});

		}

	}

	private void inviable_friend_nolife() {

		progress_dialog = ProgressDialog.show(this, "Loading..",
				"Downloading friend list", true);

		new Request(Session.getActiveSession(), "/me/taggable_friends", null,
				HttpMethod.GET, new Request.Callback() {

					@Override
					public void onCompleted(Response response) {

						GraphObject graphObject = response.getGraphObject();

						if (graphObject != null) {
							JSONObject jsonObject = graphObject
									.getInnerJSONObject();
							// System.out.println(jsonObject);
							invitable_friend_list.clear();
							try {
								JSONArray array = jsonObject
										.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject object = (JSONObject) array
											.get(i);

									invitablefriendlist.add(object
											.getString("id"));

									JSONObject imageobject = object
											.getJSONObject("picture");
									JSONObject dataimageobject = imageobject
											.getJSONObject("data");

									String url = dataimageobject
											.getString("url");

									String fbid = object.getString("id");
									String name = object.getString("name");
									FriendListModel model = new FriendListModel();

									model.setFB_ID(fbid);
									model.setName(name);
									model.setUrl(url);

									invitable_friend_list.add(model);

									String[] aa = { url, name, fbid };
									// new
									// downloadinvitable_image().execute(aa);

								}

								
								runOnUiThread(new Runnable() {
									public void run() {

										progress_dialog.dismiss();
										// Collections.shuffle(invitable_friend_list);
										friendlistDialog.show();

										// remove item which match shared pref
										// data
										System.out
										.println("invitable_friend_list>>>>>>>>>>>>>"
												+ invitable_friend_list);

										System.out
												.println("invitable_friend_list original ---------"
														+ invitable_friend_list
																.toString());

										invitable_friend_list_new.clear();

										for (int i = 0; i < invitable_friend_list
												.size(); i++) {
											boolean isPresent = false;
											for (int j = 0; j < invited_friend_list_name
													.size(); j++) {
												if (invitable_friend_list
														.get(i)
														.getName()
														.contains(
																invited_friend_list_name
																		.get(j))) {

													isPresent = true;
												}
											}

											if (!isPresent) {
												invitable_friend_list_new
														.add(invitable_friend_list
																.get(i));
											}

										}

										if (invitable_friend_list_new.size() > 5) {
											flistadapter = new friendlistadapter(
													getApplicationContext(),
													invitable_friend_list_new);
										} else {

											Gson gson = new Gson();
											invited_friend_list_name.clear();
											String jsonText = gson
													.toJson(invited_friend_list_name);
											editor = preferences.edit();
											editor.putString("invited_list",
													jsonText);
											editor.commit();

											flistadapter = new friendlistadapter(
													getApplicationContext(),
													invitable_friend_list);
										}

										friendlistview.setAdapter(flistadapter);

									}
								});

							} catch (JSONException e) {
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										progress_dialog.dismiss();

									}
								});

								e.printStackTrace();
							}

						}

					}
				}).executeAsync();
	}

}
