package com.dark30.mm;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ui.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intuit.sdp.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String ase = "";
	private String value = "";
	private String Ver = "";
	private boolean found = false;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView app_icon;
	private TextView textview1;
	private LinearLayout linear3;
	private TextView textview2;
	
	private RequestNetwork Request;
	private RequestNetwork.RequestListener _Request_request_listener;
	private AlertDialog.Builder D;
	private Intent i1 = new Intent();
	private Intent i3 = new Intent();
	private SharedPreferences sh;
	
	private OnCompleteListener FCM_onCompleteListener;
	private DatabaseReference abc = _firebase.getReference("abc");
	private ChildEventListener _abc_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		if (ActivityCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == -1) {
				ActivityCompat.requestPermissions(this, new String[]{"android.permission.POST_NOTIFICATIONS"}, 1000);
		}
		ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
		_subscribeFCMTopic("qgrpx");
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		app_icon = findViewById(R.id.app_icon);
		textview1 = findViewById(R.id.textview1);
		linear3 = findViewById(R.id.linear3);
		textview2 = findViewById(R.id.textview2);
		Request = new RequestNetwork(this);
	final MaterialAlertDialogBuilder D = new MaterialAlertDialogBuilder(this);
		sh = getSharedPreferences("sh", Activity.MODE_PRIVATE);
		
		_Request_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				class task1 extends AsyncTask<Void, Void, Void> { 
					@Override
					protected Void doInBackground(Void... arg0) {
						
						map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
						
						return null;
						 }
					protected void onPostExecute(Void result) {
						if (map.get("staus").toString().equals("on")) {
							_CHECKSHA1();
							if (map.get("sha").toString().equals(ase)) {
								sh.edit().putString("sha", map.get("sha").toString()).commit();
								if (map.containsKey("version")) {
									if (Double.parseDouble(Ver) == Double.parseDouble(map.get("version").toString())) {
										sh.edit().putString("tele", map.get("tele").toString()).commit();
										sh.edit().putString("telem", map.get("telem").toString()).commit();
										sh.edit().putString("ver", map.get("version").toString()).commit();
										sh.edit().putString("ads", map.get("ads").toString()).commit();
										i1.setAction(Intent.ACTION_MAIN);
										i1.setClass(getApplicationContext(), Main2Activity.class);
										startActivity(i1);
										finishAffinity();
									}
									else {
										if (Double.parseDouble(Ver) < Double.parseDouble(map.get("version").toString())) {
											D.setTitle("Update Available !");
											D.setIcon(R.drawable.app_icon);
											D.setMessage("Please upgrade Black3.0\nThanks");
											D.setPositiveButton("Upgrade", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface _dialog, int _which) {
													if (!map.get("url").toString().isEmpty()) {
														_DownloadFile(map.get("url").toString(), "/Dark3.0/");
													}
												}
											});
											D.setCancelable(false);
											D.create().show();
										}
									}
								}
							}
							else {
								D.setTitle("Chone App detect !");
								D.setIcon(R.drawable.app_icon);
								D.setMessage("Please download from legal store\nThanks");
								D.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										finish();
									}
								});
								D.setCancelable(false);
								D.create().show();
							}
						}
						else {
							D.setTitle("Server Offline !");
							D.setIcon(R.drawable.app_icon);
							D.setMessage("Server is under maintenance \nPlease a few minutes \nThanks ");
							D.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									finish();
								}
							});
							D.setCancelable(false);
							D.create().show();
						}
										return ;
										    }
				}
				new task1().execute();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
					D.setTitle("VPN !");
					D.setIcon(R.drawable.app_icon);
					D.setMessage("Can't collect data ! Please use vpn connection");
					D.setPositiveButton("ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
									Request.startRequestNetwork(RequestNetworkController.GET, getString(R.string.src1), "", _Request_request_listener);
							}
					});
					D.setCancelable(false);
					D.create().show();
			}
		};
		
		FCM_onCompleteListener = new OnCompleteListener<InstanceIdResult>() {
			@Override
			public void onComplete(Task<InstanceIdResult> task) {
				final boolean _success = task.isSuccessful();
				final String _token = task.getResult().getToken();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_abc_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				abc.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						boolean found = false;
						for (int i = listmap.size() - 1; i >= 0; i--) {
								String value = listmap.get(i).get("text").toString();
							if (value.equals(sh.getString("sh", ""))) {
								SketchwareUtil.showMessage(getApplicationContext(), "Your are membership !");
								found = true;
								break;
							}
						}
						if (!found) {
							sh.edit().remove("sh").commit();
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		abc.addChildEventListener(_abc_child_listener);
	}
	
	private void initializeLogic() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		
		android.content.pm.PackageManager manager = this.getPackageManager();
		        try {
				            android.content.pm.PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
				            String packageName = info.packageName;
				            int versionCode = info.versionCode;
				            String versionName = info.versionName;
				            Ver = versionName;
				            
				        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
				            // TODO Auto-generated catch block
				e.printStackTrace();
				        }
		        
		app_icon.setImageResource(R.drawable.app_icon);
		textview1.setText(getString(R.string.app_name));
		Request.startRequestNetwork(RequestNetworkController.GET, getString(R.string.src1), "", _Request_request_listener);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		_LIBA();
		super.onSaveInstanceState(outState);
	}
	
	public void _CHECKSHA1() {
		ase = MainActivity.this.getSignture(MainActivity.this);
	}
	
	
	public void _external() {
	}
	/*

//original code is not by me but it has been changed ( some part are coded by arab ware channel )
*/
	
		public String getSignture(Context context) {
		try {
					android.content.pm.PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
							getPackageName(), android.content.pm.PackageManager.GET_SIGNATURES);
			    //note sample just checks the first signature
					for (android.content.pm.Signature signature : packageInfo.signatures) {
							// SHA1 the signature
							String sha1 = getSHA1_(signature.toByteArray());
							// check is matches hardcoded value
							return sha1;
					}
		} catch(android.content.pm.PackageManager.NameNotFoundException e) {}
		
			return "";
		}
	  
	  //computed the sha1 hash of the signature
	  public String getSHA1_(byte[] sig) {
		try {
			  		java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA1");
			digest.update(sig);
						byte[] hashtext = digest.digest();
						return bytes_To_Hex_(hashtext);
		} catch(java.security.NoSuchAlgorithmException e) {}
					return "";
		}
	  
	  //util method to convert byte array to hex string
	  public String bytes_To_Hex_(byte[] bytes) {
		  	final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
							'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] hexChars = new char[bytes.length * 2];
				int v;
				for (int j = 0; j < bytes.length; j++) {
						v = bytes[j] & 0xFF;
						hexChars[j * 2] = hexArray[v >>> 4];
						hexChars[j * 2 + 1] = hexArray[v & 0x0F];
				}
				return new String(hexChars);
		}
	  
	    
	
	{
	}
	
	
	public void _LIBA() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN); getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	}
	
	
	public void _getDeviceFCMToken() {
		FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
			@Override
			public void onComplete(@NonNull Task<InstanceIdResult> task) {
				if (task.isSuccessful()) {
					sh.edit().putString("token", task.getResult().getToken()).commit();
				} else {
				}}});
	}
	
	
	public void _subscribeFCMTopic(final String _name) {
		if (_name.matches("[a-zA-Z0-9-_.~%]{1,900}")) {
			String topicName = java.text.Normalizer.normalize(_name, java.text.Normalizer.Form.NFD);
			topicName = topicName.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			FirebaseMessaging.getInstance().subscribeToTopic(topicName).addOnCompleteListener(new OnCompleteListener<Void>() {
				@Override
				public void onComplete(@NonNull Task<Void> task) {
					if (task.isSuccessful()) {
						
					} else {
						
					}}});
		}
		_getDeviceFCMToken();
	}
	
	
	public void _glideFromURL(final String _url, final ImageView _imageview) {
		
		Glide.with(getApplicationContext())
		.load(_url)
		.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
		.error(R.drawable.app_icon)
		.into(_imageview);
	}
	
	
	public void _DownloadFile(final String _url, final String _path) {
		FileUtil.makeDir(_path);
		
		try {
				    android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				    android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				    
				    if (networkInfo != null && networkInfo.isConnected()) {
						        final String urlDownload = _url;
						        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlDownload));
						        final String fileName = URLUtil.guessFileName(urlDownload, null, null);
						       request.setTitle("Dark3.0");
				            request.setDescription("Upgrade"+fileName);
						        request.allowScanningByMediaScanner();
						        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
						        request.setDestinationInExternalPublicDir(_path, fileName);
						        final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
						        final long downloadId = manager.enqueue(request);
						
						        new Thread(new Runnable() {
								            @Override
								            public void run() {
										                boolean downloading = true;
										
										                while (downloading) {
												                    DownloadManager.Query q = new DownloadManager.Query();
												                    q.setFilterById(downloadId);
												                    android.database.Cursor cursor = manager.query(q);
												
												                    if (cursor != null && cursor.moveToFirst()) {
														                        int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
														                        int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
														
														                        if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
																                            downloading = false;
																                        }
														
														                        final int dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);
														
														                        if (dl_progress == 100) {
																                            final String filePath = _path.concat(fileName);
																                            runOnUiThread(new Runnable() {
																		                                @Override
																		                                public void run() {
																				                                    showMessage("Downloaded in " + filePath);
																				                                }
																		                            });
																                        }
														                    } else {
														                        // Handle the case where the cursor is empty
														                        downloading = false; // Stop the download loop
														                        runOnUiThread(new Runnable() {
																                            @Override
																                            public void run() {
																		                                showMessage("Download failed"); // Show an error message
																		                            }
																                        });
														                    }
												                    
												                    if(cursor != null) {
														                        cursor.close(); // Close the cursor to avoid memory leaks
														                    }
												                }
										            }
								        }).start();
						    } else {
						        runOnUiThread(new Runnable() {
								            @Override
								            public void run() {
										                showMessage("No Internet Connection.");
										            }
								        });
						    }
		} catch (Exception e) {
				    runOnUiThread(new Runnable() {
						        @Override
						        public void run() {
								            showMessage("Error");
								        }
						    });
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}