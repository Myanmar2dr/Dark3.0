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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.google.android.exoplayer2.ui.*;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intuit.sdp.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import android.net.Uri;

public class ExoActivity extends AppCompatActivity {
	
	private String data = "";
	private boolean flag = false;
	
	private RelativeLayout relativelayout;
	private PlayerView player_view;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private TextView textview1;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private ProgressBar progressbar1;
	
	private SharedPreferences sh;
	private Intent i = new Intent();
	private AlertDialog.Builder D;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.exo);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		relativelayout = findViewById(R.id.relativelayout);
		player_view = findViewById(R.id.player_view);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear7 = findViewById(R.id.linear7);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		textview1 = findViewById(R.id.textview1);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		progressbar1 = findViewById(R.id.progressbar1);
		sh = getSharedPreferences("sh", Activity.MODE_PRIVATE);
	final MaterialAlertDialogBuilder D = new MaterialAlertDialogBuilder(this);
	}
	
	private void initializeLogic() {
		if (sh.contains("sha")) {
			if (sh.contains("sh") || getIntent().getStringExtra("vip").equals("none")) {
				data = getIntent().getStringExtra("data");
				textview1.setText(getIntent().getStringExtra("title"));
				btFullScreen = player_view.findViewById(R.id.bt_fullscreen);
				btFullScreen.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (flag) {
							btFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_exit));
							setRequestedOrientation(1);
							flag = false;
						}
						else {
							btFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_enter));
							setRequestedOrientation(0);
							flag = true;
						}
					}
				});
				 player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector(new Factory(new DefaultBandwidthMeter())), new DefaultLoadControl());
				        
				MediaSource mediaSource;
				 Uri videoUrl = Uri.parse(data);
				            mediaSource = new ExtractorMediaSource(videoUrl, new DefaultHttpDataSourceFactory("exoplayer_video"), new DefaultExtractorsFactory(), null, null);
				     
				player_view.setPlayer(player);
				player_view.setKeepScreenOn(true);
				player.prepare(mediaSource);
				player.setPlayWhenReady(true);
				player.addListener(new EventListener() {
					                public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
						                }
					                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
						                }
					                public void onLoadingChanged(boolean isLoading) {
						                }
					                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
						                   
						            
						if (playbackState == 2) {
							progressbar1.setVisibility(View.VISIBLE);
						} else if (playbackState == 3) {
							progressbar1.setVisibility(View.GONE);
						}
						    }
					                public void onRepeatModeChanged(int repeatMode) {
						                }
					                public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
						                }
					                public void onPlayerError(ExoPlaybackException error) {
						                }
					                public void onPositionDiscontinuity(int reason) {
						                }
					                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
						                }
					                public void onSeekProcessed() {
						                }
					            });
			}
			else {
				if (!sh.contains("sh")) {
					i.setClass(getApplicationContext(), Main4Activity.class);
					startActivity(i);
				}
			}
		}
		_More();
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		 if (player != null) {
			            player_view.setPlayer(null);
			            player.release();
			            player = null;
			        }
	}
	
	@Override
	public void onPause() {
		super.onPause();
		 if (player != null) {
			            player.setPlayWhenReady(false);
			            player.getPlaybackState();
			        }
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (player != null) {
				progressbar1.setVisibility(View.VISIBLE);
		}
		else {
				progressbar1.setVisibility(View.GONE);
		}
	}
	public void _More() {
		//Add this in onCreate to hide status bar on landscape // Dz moh
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
					            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
					        }
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		    getWindow().setStatusBarColor(Color.TRANSPARENT);
		    // Dz moh
		    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		
		
		
		
		hideSystemUI();
		
		    
		    getWindow().getDecorView()
		               .setOnSystemUiVisibilityChangeListener(new View
		               .OnSystemUiVisibilityChangeListener() {
				        @Override
				        public void onSystemUiVisibilityChange(int visibility) {
						            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
								                hideSystemUI();
								            }
						        }
				    });
			
			
		
	}
	
	
	public void _extrea() {
	}
	
	// Add The More block 
	private static final int UI_OPTIONS = View.SYSTEM_UI_FLAG_LOW_PROFILE
	        | View.SYSTEM_UI_FLAG_FULLSCREEN
	        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
	        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;        
	
	
	
	private void hideSystemUI() {
			    ActionBar actionBar = getActionBar();
			    if (actionBar != null) actionBar.hide();
			    getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
	}
	{
	}
	
	
	public void _extra() {
	}
	private ImageView btFullScreen;
	private SimpleExoPlayer player;
	protected void onRestart() {
		        super.onRestart();
		        if (player != null) {
			            player.setPlayWhenReady(true);
			            player.getPlaybackState();
			        }
		    }
	{
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