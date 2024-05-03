package com.dark30.mm;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.Context;
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
import android.os.Vibrator;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ui.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intuit.sdp.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import androidx.core.app.ActivityCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ViewvActivity extends AppCompatActivity {
	
	private FloatingActionButton _fab;
	private String filePath = "";
	private String fileName = "";
	private String dlink = "";
	private String fsize = "";
	
	private ArrayList<String> videolink = new ArrayList<>();
	private ArrayList<String> size = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private RelativeLayout ime;
	private LinearLayout linear7;
	private TextView textview5;
	private LinearLayout linear16;
	private LinearLayout linear15;
	private ImageView imageview1;
	private ImageView imageview6;
	private LinearLayout linear17;
	private TextView textview6;
	private RelativeLayout linear14;
	private LinearLayout linear8;
	private LinearLayout linear19;
	private LinearLayout linear20;
	private CardView cardview1;
	private ImageView imageview2;
	private ImageView imageview7;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private TextView textview1;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private ImageView imageview3;
	private TextView textview2;
	private ImageView imageview4;
	private TextView textview3;
	private ImageView imageview5;
	private TextView textview4;
	
	private Intent i = new Intent();
	private Intent intent = new Intent();
	private SharedPreferences sh;
	private Vibrator Vib;
	private AlertDialog.Builder D;
	private RequestNetwork player;
	private RequestNetwork.RequestListener _player_request_listener;
	private RequestNetwork download;
	private RequestNetwork.RequestListener _download_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.viewv);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
		_fab = findViewById(R.id._fab);
		
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		ime = findViewById(R.id.ime);
		linear7 = findViewById(R.id.linear7);
		textview5 = findViewById(R.id.textview5);
		linear16 = findViewById(R.id.linear16);
		linear15 = findViewById(R.id.linear15);
		imageview1 = findViewById(R.id.imageview1);
		imageview6 = findViewById(R.id.imageview6);
		linear17 = findViewById(R.id.linear17);
		textview6 = findViewById(R.id.textview6);
		linear14 = findViewById(R.id.linear14);
		linear8 = findViewById(R.id.linear8);
		linear19 = findViewById(R.id.linear19);
		linear20 = findViewById(R.id.linear20);
		cardview1 = findViewById(R.id.cardview1);
		imageview2 = findViewById(R.id.imageview2);
		imageview7 = findViewById(R.id.imageview7);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		textview1 = findViewById(R.id.textview1);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		linear13 = findViewById(R.id.linear13);
		imageview3 = findViewById(R.id.imageview3);
		textview2 = findViewById(R.id.textview2);
		imageview4 = findViewById(R.id.imageview4);
		textview3 = findViewById(R.id.textview3);
		imageview5 = findViewById(R.id.imageview5);
		textview4 = findViewById(R.id.textview4);
		sh = getSharedPreferences("sh", Activity.MODE_PRIVATE);
		Vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	final MaterialAlertDialogBuilder D = new MaterialAlertDialogBuilder(this);
		player = new RequestNetwork(this);
		download = new RequestNetwork(this);
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (sh.contains("sha")) {
					if (sh.contains("sh") || getIntent().getStringExtra("vip").equals("none")) {
						if (getIntent().hasExtra("link")) {
							if (getIntent().getStringExtra("media").isEmpty()) {
								i.putExtra("vip", getIntent().getStringExtra("vip"));
								i.putExtra("title", getIntent().getStringExtra("title"));
								i.putExtra("data", getIntent().getStringExtra("link"));
								i.setClass(getApplicationContext(), ExoActivity.class);
								startActivity(i);
							}
							else {
								player.startRequestNetwork(RequestNetworkController.GET,getIntent().getStringExtra("link"), "", _player_request_listener);
								_Prog_Dialogue_show(true, "", "Generating direct link.....");
							}
						}
					}
					else {
						if (!sh.contains("sh")) {
							D.setTitle("KEY !");
							D.setIcon(R.drawable.app_icon);
							D.setMessage("Your are not vip membership !");
							D.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							D.create().show();
						}
					}
				}
				Vib.vibrate((long)(300));
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().hasExtra("vip")) {
					i.setClass(getApplicationContext(), Main4Activity.class);
					startActivity(i);
				}
				Vib.vibrate((long)(300));
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (sh.contains("tele")) {
					intent.setAction(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(sh.getString("tele", "")));
					startActivity(intent);
				}
				Vib.vibrate((long)(300));
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (sh.contains("sha")) {
					if (sh.contains("sh") || getIntent().getStringExtra("vip").equals("none")) {
						if (getIntent().hasExtra("link")) {
							if (getIntent().getStringExtra("media").isEmpty()) {
								_DownloadFile(getIntent().getStringExtra("link"), "/Dark3.0/");
							}
							else {
								download.startRequestNetwork(RequestNetworkController.GET,getIntent().getStringExtra("link"), "", _download_request_listener);
								_Prog_Dialogue_show(true, "", "Generating direct link.....");
							}
						}
					}
					else {
						if (!sh.contains("sh")) {
							D.setTitle("KEY !");
							D.setIcon(R.drawable.app_icon);
							D.setMessage("Your are not vip membership !");
							D.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							D.create().show();
						}
					}
				}
				Vib.vibrate((long)(300));
				_LIBA();
			}
		});
		
		_player_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				videolink.clear();
				size.clear();
				
				class web extends AsyncTask<Void, Void, Void> { 
										@Override
					
										protected Void doInBackground(Void... arg0) {
												
												org.jsoup.nodes.Document doc  = org.jsoup.Jsoup.parse(_response);
						
											
												org.jsoup.select.Elements data_ = doc.getElementsByClass("input popsok");
						dlink = data_.attr("href");
						
												for (org.jsoup.nodes.Element links : data_ ) {
							
							size.add(links.text());
														videolink.add(dlink);
							
						}
						
												
												return null;
						
												 }
					
					
										protected void onPostExecute(Void result) {
						fsize = size.get((int)(0));
						if (dlink.isEmpty()) {
							prog.dismiss();
							i.putExtra("vip", getIntent().getStringExtra("vip"));
							i.putExtra("title", getIntent().getStringExtra("title"));
							i.putExtra("data", getIntent().getStringExtra("link"));
							i.setClass(getApplicationContext(), ExoActivity.class);
							startActivity(i);
						}
						return ;
						
																    }
					
								}
				
								new web().execute();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_download_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				videolink.clear();
				size.clear();
				
				class web extends AsyncTask<Void, Void, Void> { 
										@Override
					
										protected Void doInBackground(Void... arg0) {
												
												org.jsoup.nodes.Document doc  = org.jsoup.Jsoup.parse(_response);
						
											
												org.jsoup.select.Elements data_ = doc.getElementsByClass("input popsok");
						dlink = data_.attr("href");
						
												for (org.jsoup.nodes.Element links : data_ ) {
							
							size.add(links.text());
														videolink.add(dlink);
							
						}
						
												
												return null;
						
												 }
					
					
										protected void onPostExecute(Void result) {
						fsize = size.get((int)(0));
						if (dlink.isEmpty()) {
							prog.dismiss();
							_DownloadFile(dlink, "/Dark3.0/");
						}
						return ;
						
																    }
					
								}
				
								new web().execute();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("image1"))).into(imageview1);
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("image2"))).into(imageview2);
		textview1.setText(getIntent().getStringExtra("title"));
		textview5.setText(getIntent().getStringExtra("info"));
		textview6.setText(getIntent().getStringExtra("time"));
		if (getIntent().getStringExtra("vip").equals("true")) {
			imageview6.setImageResource(R.drawable.ic__1);
			imageview7.setImageResource(R.drawable.ic__1);
		}
		MaterialRippleLayout.on(linear11)
		           .rippleColor(0xFF9E9E9E)
		           .create();
		MaterialRippleLayout.on(linear12)
		           .rippleColor(0xFF9E9E9E)
		           .create();
		MaterialRippleLayout.on(linear13)
		           .rippleColor(0xFF9E9E9E)
		           .create();
		_LIBA();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		_LIBA();
		super.onSaveInstanceState(outState);
	}
	
	public void _LIBA() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN); getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
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
				              request.setTitle(getIntent().getStringExtra("title"));
						          request.setDescription("Dark3.0 - " + getIntent().getStringExtra("title"));
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
	
	
	public void _Ripple() {
		
	}
	public static class MaterialRippleLayout extends FrameLayout {
		    private static final int     DEFAULT_DURATION        = 350;
		    private static final int     DEFAULT_FADE_DURATION   = 75;
		    private static final float   DEFAULT_DIAMETER_DP     = 35;
		    private static final float   DEFAULT_ALPHA           = 0.2f;
		    private static final int     DEFAULT_COLOR           = Color.BLACK;
		    private static final int     DEFAULT_BACKGROUND      = Color.TRANSPARENT;
		    private static final boolean DEFAULT_HOVER           = true;
		    private static final boolean DEFAULT_DELAY_CLICK     = true;
		    private static final boolean DEFAULT_PERSISTENT      = false;
		    private static final boolean DEFAULT_SEARCH_ADAPTER  = false;
		    private static final boolean DEFAULT_RIPPLE_OVERLAY  = false;
		    private static final int     DEFAULT_ROUNDED_CORNERS = 0;
		    private static final int  FADE_EXTRA_DELAY = 50;
		    private static final long HOVER_DURATION   = 2500;
		    private final Paint paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
		    private final Rect  bounds = new Rect();
		    private int      rippleColor;
		    private boolean  rippleOverlay;
		    private boolean  rippleHover;
		    private int      rippleDiameter;
		    private int      rippleDuration;
		    private int      rippleAlpha;
		    private boolean  rippleDelayClick;
		    private int      rippleFadeDuration;
		    private boolean  ripplePersistent;
		    private android.graphics.drawable.Drawable rippleBackground;
		    private boolean  rippleInAdapter;
		    private float    rippleRoundedCorners;
		    private float radius;
		    private AdapterView parentAdapter;
		    private View childView;
		    private AnimatorSet    rippleAnimator;
		    private ObjectAnimator hoverAnimator;
		    private Point currentCoords  = new Point();
		    private Point previousCoords = new Point();
		    private int layerType;
		    private boolean eventCancelled;
		    private boolean prepressed;
		    private int     positionInAdapter;
		    private GestureDetector   gestureDetector;
		    private PerformClickEvent pendingClickEvent;
		    private PressedEvent      pendingPressEvent;
		    public static RippleBuilder on(View view) {
			        return new RippleBuilder(view);
			    }
		    public MaterialRippleLayout(Context context) {
			        this(context, null, 0);
			    }
		    public MaterialRippleLayout(Context context, AttributeSet attrs) {
			        this(context, attrs, 0);
			    }
		    public MaterialRippleLayout(Context context, AttributeSet attrs, int defStyle) {
			        super(context, attrs, defStyle);
			        setWillNotDraw(false);
			        gestureDetector = new GestureDetector(context, longClickListener);
			        
			        rippleColor = DEFAULT_COLOR;
			        rippleDiameter =  (int) dpToPx(getResources(), DEFAULT_DIAMETER_DP);
			        rippleOverlay = DEFAULT_RIPPLE_OVERLAY;
			        rippleHover = DEFAULT_HOVER;
			        rippleDuration = DEFAULT_DURATION;
			        rippleAlpha = (int) (255 * DEFAULT_ALPHA);
			        rippleDelayClick = DEFAULT_DELAY_CLICK;
			        rippleFadeDuration = DEFAULT_FADE_DURATION;
			        rippleBackground = new android.graphics.drawable.ColorDrawable(DEFAULT_BACKGROUND);
			        ripplePersistent = DEFAULT_PERSISTENT;
			        rippleInAdapter = DEFAULT_SEARCH_ADAPTER;
			        rippleRoundedCorners = DEFAULT_ROUNDED_CORNERS;
			
			        paint.setColor(rippleColor);
			        paint.setAlpha(rippleAlpha);
			        enableClipPathSupportIfNecessary();
			    }
		    @SuppressWarnings("unchecked")
		    public <T extends View> T getChildView() {
			        return (T) childView;
			    }
		    @Override
		    public final void addView(View child, int index, ViewGroup.LayoutParams params) {
			        if (getChildCount() > 0) {
				            throw new IllegalStateException("MaterialRippleLayout can host only one child");
				        }
			        childView = child;
			        super.addView(child, index, params);
			    }
		    @Override
		    public void setOnClickListener(OnClickListener onClickListener) {
			        if (childView == null) {
				            throw new IllegalStateException("MaterialRippleLayout must have a child view to handle clicks");
				        }
			        childView.setOnClickListener(onClickListener);
			    }
		    @Override
		    public void setOnLongClickListener(OnLongClickListener onClickListener) {
			        if (childView == null) {
				            throw new IllegalStateException("MaterialRippleLayout must have a child view to handle clicks");
				        }
			        childView.setOnLongClickListener(onClickListener);
			    }
		    @Override
		    public boolean onInterceptTouchEvent(MotionEvent event) {
			        return !findClickableViewInChild(childView, (int) event.getX(), (int) event.getY());
			    }
		    @Override
		    public boolean onTouchEvent(MotionEvent event) {
			        boolean superOnTouchEvent = super.onTouchEvent(event);
			        if (!isEnabled() || !childView.isEnabled()) return superOnTouchEvent;
			        boolean isEventInBounds = bounds.contains((int) event.getX(), (int) event.getY());
			        if (isEventInBounds) {
				            previousCoords.set(currentCoords.x, currentCoords.y);
				            currentCoords.set((int) event.getX(), (int) event.getY());
				        }
			        boolean gestureResult = gestureDetector.onTouchEvent(event);
			        if (gestureResult || hasPerformedLongPress) {
				            return true;
				        } else {
				            int action = event.getActionMasked();
				            switch (action) {
					                case MotionEvent.ACTION_UP:
					                    pendingClickEvent = new PerformClickEvent();
					                    if (prepressed) {
						                        childView.setPressed(true);
						                        postDelayed(
						                            new Runnable() {
							                                @Override public void run() {
								                                    childView.setPressed(false);
								                                }
							                            }, ViewConfiguration.getPressedStateDuration());
						                    }
					                    if (isEventInBounds) {
						                        startRipple(pendingClickEvent);
						                    } else if (!rippleHover) {
						                        setRadius(0);
						                    }
					                    if (!rippleDelayClick && isEventInBounds) {
						                        pendingClickEvent.run();
						                    }
					                    cancelPressedEvent();
					                    break;
					                case MotionEvent.ACTION_DOWN:
					                    setPositionInAdapter();
					                    eventCancelled = false;
					                    pendingPressEvent = new PressedEvent(event);
					                    if (isInScrollingContainer()) {
						                        cancelPressedEvent();
						                        prepressed = true;
						                        postDelayed(pendingPressEvent, ViewConfiguration.getTapTimeout());
						                    } else {
						                        pendingPressEvent.run();
						                    }
					                    break;
					                case MotionEvent.ACTION_CANCEL:
					                    if (rippleInAdapter) {
						                        currentCoords.set(previousCoords.x, previousCoords.y);
						                        previousCoords = new Point();
						                    }
					                    childView.onTouchEvent(event);
					                    if (rippleHover) {
						                        if (!prepressed) {
							                            startRipple(null);
							                        }
						                    } else {
						                        childView.setPressed(false);
						                    }
					                    cancelPressedEvent();
					                    break;
					                case MotionEvent.ACTION_MOVE:
					                    if (rippleHover) {
						                        if (isEventInBounds && !eventCancelled) {
							                            invalidate();
							                        } else if (!isEventInBounds) {
							                            startRipple(null);
							                        }
						                    }
					                    if (!isEventInBounds) {
						                        cancelPressedEvent();
						                        if (hoverAnimator != null) {
							                            hoverAnimator.cancel();
							                        }
						                        childView.onTouchEvent(event);
						                        eventCancelled = true;
						                    }
					                    break;
					            }
				            return true;
				        }
			    }
		    private void cancelPressedEvent() {
			        if (pendingPressEvent != null) {
				            removeCallbacks(pendingPressEvent);
				            prepressed = false;
				        }
			    }
		    private boolean hasPerformedLongPress;
		    private android.view.GestureDetector.SimpleOnGestureListener longClickListener = new GestureDetector.SimpleOnGestureListener() {
			        public void onLongPress(MotionEvent e) {
				            hasPerformedLongPress = childView.performLongClick();
				            if (hasPerformedLongPress) {
					                if (rippleHover) {
						                    startRipple(null);
						                }
					                cancelPressedEvent();
					            }
				        }
			        @Override
			        public boolean onDown(MotionEvent e) {
				            hasPerformedLongPress = false;
				            return super.onDown(e);
				        }
			    };
		    private void startHover() {
			        if (eventCancelled) return;
			        if (hoverAnimator != null) {
				            hoverAnimator.cancel();
				        }
			        final float radius = (float) (Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2)) * 1.2f);
			        hoverAnimator = ObjectAnimator.ofFloat(this, radiusProperty, rippleDiameter, radius)
			            .setDuration(HOVER_DURATION);
			        hoverAnimator.setInterpolator(new android.view.animation.LinearInterpolator());
			        hoverAnimator.start();
			    }
		    private void startRipple(final Runnable animationEndRunnable) {
			        if (eventCancelled) return;
			        float endRadius = getEndRadius();
			        cancelAnimations();
			        rippleAnimator = new AnimatorSet();
			        rippleAnimator.addListener(new AnimatorListenerAdapter() {
				            @Override public void onAnimationEnd(Animator animation) {
					                if (!ripplePersistent) {
						                    setRadius(0);
						                    setRippleAlpha(rippleAlpha);
						                }
					                if (animationEndRunnable != null && rippleDelayClick) {
						                    animationEndRunnable.run();
						                }
					                childView.setPressed(false);
					            }
				        });
			        ObjectAnimator ripple = ObjectAnimator.ofFloat(this, radiusProperty, radius, endRadius);
			        ripple.setDuration(rippleDuration);
			        ripple.setInterpolator(new android.view.animation.DecelerateInterpolator());
			        ObjectAnimator fade = ObjectAnimator.ofInt(this, circleAlphaProperty, rippleAlpha, 0);
			        fade.setDuration(rippleFadeDuration);
			        fade.setInterpolator(new android.view.animation.AccelerateInterpolator());
			        fade.setStartDelay(rippleDuration - rippleFadeDuration - FADE_EXTRA_DELAY);
			        if (ripplePersistent) {
				            rippleAnimator.play(ripple);
				        } else if (getRadius() > endRadius) {
				            fade.setStartDelay(0);
				            rippleAnimator.play(fade);
				        } else {
				            rippleAnimator.playTogether(ripple, fade);
				        }
			        rippleAnimator.start();
			    }
		    private void cancelAnimations() {
			        if (rippleAnimator != null) {
				            rippleAnimator.cancel();
				            rippleAnimator.removeAllListeners();
				        }
			        if (hoverAnimator != null) {
				            hoverAnimator.cancel();
				        }
			    }
		    private float getEndRadius() {
			        final int width = getWidth();
			        final int height = getHeight();
			        final int halfWidth = width / 2;
			        final int halfHeight = height / 2;
			        final float radiusX = halfWidth > currentCoords.x ? width - currentCoords.x : currentCoords.x;
			        final float radiusY = halfHeight > currentCoords.y ? height - currentCoords.y : currentCoords.y;
			        return (float) Math.sqrt(Math.pow(radiusX, 2) + Math.pow(radiusY, 2)) * 1.2f;
			    }
		    private boolean isInScrollingContainer() {
			        ViewParent p = getParent();
			        while (p != null && p instanceof ViewGroup) {
				            if (((ViewGroup) p).shouldDelayChildPressedState()) {
					                return true;
					            }
				            p = p.getParent();
				        }
			        return false;
			    }
		    private AdapterView findParentAdapterView() {
			        if (parentAdapter != null) {
				            return parentAdapter;
				        }
			        ViewParent current = getParent();
			        while (true) {
				            if (current instanceof AdapterView) {
					                parentAdapter = (AdapterView) current;
					                return parentAdapter;
					            } else {
					                try {
						                    current = current.getParent();
						                } catch (NullPointerException npe) {
						                    throw new RuntimeException("Could not find a parent AdapterView");
						                }
					            }
				        }
			    }
		    private void setPositionInAdapter() {
			        if (rippleInAdapter) {
				            positionInAdapter = findParentAdapterView().getPositionForView(MaterialRippleLayout.this);
				        }
			    }
		    private boolean adapterPositionChanged() {
			        if (rippleInAdapter) {
				            int newPosition = findParentAdapterView().getPositionForView(MaterialRippleLayout.this);
				            final boolean changed = newPosition != positionInAdapter;
				            positionInAdapter = newPosition;
				            if (changed) {
					                cancelPressedEvent();
					                cancelAnimations();
					                childView.setPressed(false);
					                setRadius(0);
					            }
				            return changed;
				        }
			        return false;
			    }
		    private boolean findClickableViewInChild(View view, int x, int y) {
			        if (view instanceof ViewGroup) {
				            ViewGroup viewGroup = (ViewGroup) view;
				            for (int i = 0; i < viewGroup.getChildCount(); i++) {
					                View child = viewGroup.getChildAt(i);
					                final Rect rect = new Rect();
					                child.getHitRect(rect);
					                final boolean contains = rect.contains(x, y);
					                if (contains) {
						                    return findClickableViewInChild(child, x - rect.left, y - rect.top);
						                }
					            }
				        } else if (view != childView) {
				            return (view.isEnabled() && (view.isClickable() || view.isLongClickable() || view.isFocusableInTouchMode()));
				        }
			        return view.isFocusableInTouchMode();
			    }
		    @Override
		    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			        super.onSizeChanged(w, h, oldw, oldh);
			        bounds.set(0, 0, w, h);
			        rippleBackground.setBounds(bounds);
			    }
		    @Override
		    public boolean isInEditMode() {
			        return true;
			    }
		    @Override
		    public void draw(Canvas canvas) {
			        final boolean positionChanged = adapterPositionChanged();
			        if (rippleOverlay) {
				            if (!positionChanged) {
					                rippleBackground.draw(canvas);
					            }
				            super.draw(canvas);
				            if (!positionChanged) {
					                if (rippleRoundedCorners != 0) {
						                    Path clipPath = new Path();
						                    RectF rect = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
						                    clipPath.addRoundRect(rect, rippleRoundedCorners, rippleRoundedCorners, Path.Direction.CW);
						                    canvas.clipPath(clipPath);
						                }
					                canvas.drawCircle(currentCoords.x, currentCoords.y, radius, paint);
					            }
				        } else {
				            if (!positionChanged) {
					                rippleBackground.draw(canvas);
					                canvas.drawCircle(currentCoords.x, currentCoords.y, radius, paint);
					            }
				            super.draw(canvas);
				        }
			    }
		    private Property<MaterialRippleLayout, Float> radiusProperty
		        = new Property<MaterialRippleLayout, Float>(Float.class, "radius") {
			        @Override
			        public Float get(MaterialRippleLayout object) {
				            return object.getRadius();
				        }
			        @Override
			        public void set(MaterialRippleLayout object, Float value) {
				            object.setRadius(value);
				        }
			    };
		    private float getRadius() {
			        return radius;
			    }
		    public void setRadius(float radius) {
			        this.radius = radius;
			        invalidate();
			    }
		    private Property<MaterialRippleLayout, Integer> circleAlphaProperty
		        = new Property<MaterialRippleLayout, Integer>(Integer.class, "rippleAlpha") {
			        @Override
			        public Integer get(MaterialRippleLayout object) {
				            return object.getRippleAlpha();
				        }
			        @Override
			        public void set(MaterialRippleLayout object, Integer value) {
				            object.setRippleAlpha(value);
				        }
			    };
		    public int getRippleAlpha() {
			        return paint.getAlpha();
			    }
		    public void setRippleAlpha(Integer rippleAlpha) {
			        paint.setAlpha(rippleAlpha);
			        invalidate();
			    }
		    public void setRippleColor(int rippleColor) {
			        this.rippleColor = rippleColor;
			        paint.setColor(rippleColor);
			        paint.setAlpha(rippleAlpha);
			        invalidate();
			    }
		    public void setRippleOverlay(boolean rippleOverlay) {
			        this.rippleOverlay = rippleOverlay;
			    }
		    public void setRippleDiameter(int rippleDiameter) {
			        this.rippleDiameter = rippleDiameter;
			    }
		    public void setRippleDuration(int rippleDuration) {
			        this.rippleDuration = rippleDuration;
			    }
		    public void setRippleBackground(int color) {
			        rippleBackground = new android.graphics.drawable.ColorDrawable(color);
			        rippleBackground.setBounds(bounds);
			        invalidate();
			    }
		    public void setRippleHover(boolean rippleHover) {
			        this.rippleHover = rippleHover;
			    }
		    public void setRippleDelayClick(boolean rippleDelayClick) {
			        this.rippleDelayClick = rippleDelayClick;
			    }
		    public void setRippleFadeDuration(int rippleFadeDuration) {
			        this.rippleFadeDuration = rippleFadeDuration;
			    }
		    public void setRipplePersistent(boolean ripplePersistent) {
			        this.ripplePersistent = ripplePersistent;
			    }
		    public void setRippleInAdapter(boolean rippleInAdapter) {
			        this.rippleInAdapter = rippleInAdapter;
			    }
		    public void setRippleRoundedCorners(int rippleRoundedCorner) {
			        this.rippleRoundedCorners = rippleRoundedCorner;
			        enableClipPathSupportIfNecessary();
			    }
		    public void setDefaultRippleAlpha(float alpha) {
			        this.rippleAlpha = (int) (255 * alpha);
			        paint.setAlpha(rippleAlpha);
			        invalidate();
			    }
		    public void performRipple() {
			        currentCoords = new Point(getWidth() / 2, getHeight() / 2);
			        startRipple(null);
			    }
		    public void performRipple(Point anchor) {
			        currentCoords = new Point(anchor.x, anchor.y);
			        startRipple(null);
			    }
		    private void enableClipPathSupportIfNecessary() {
			        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
				            if (rippleRoundedCorners != 0) {
					                layerType = getLayerType();
					                setLayerType(LAYER_TYPE_SOFTWARE, null);
					            } else {
					                setLayerType(layerType, null);
					            }
				        }
			    }
		    private class PerformClickEvent implements Runnable {
			        @Override public void run() {
				            if (hasPerformedLongPress) return;
				            if (getParent() instanceof AdapterView) {
					                if (!childView.performClick())
					                    clickAdapterView((AdapterView) getParent());
					            } else if (rippleInAdapter) {
					                clickAdapterView(findParentAdapterView());
					            } else {
					                childView.performClick();
					            }
				        }
			        private void clickAdapterView(AdapterView parent) {
				            final int position = parent.getPositionForView(MaterialRippleLayout.this);
				            final long itemId = parent.getAdapter() != null
				                ? parent.getAdapter().getItemId(position)
				                : 0;
				            if (position != AdapterView.INVALID_POSITION) {
					                parent.performItemClick(MaterialRippleLayout.this, position, itemId);
					            }
				        }
			    }
		    private final class PressedEvent implements Runnable {
			        private final MotionEvent event;
			        public PressedEvent(MotionEvent event) {
				            this.event = event;
				        }
			        @Override
			        public void run() {
				            prepressed = false;
				            childView.setLongClickable(false);
				            childView.onTouchEvent(event);
				            childView.setPressed(true);
				            if (rippleHover) {
					                startHover();
					            }
				        }
			    }
		    static float dpToPx(android.content.res.Resources resources, float dp) {
			        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
			    }
		    public static class RippleBuilder {
			        private final Context context;
			        private final View    child;
			        private int     rippleColor         = DEFAULT_COLOR;
			        private boolean rippleOverlay       = DEFAULT_RIPPLE_OVERLAY;
			        private boolean rippleHover         = DEFAULT_HOVER;
			        private float   rippleDiameter      = DEFAULT_DIAMETER_DP;
			        private int     rippleDuration      = DEFAULT_DURATION;
			        private float   rippleAlpha         = DEFAULT_ALPHA;
			        private boolean rippleDelayClick    = DEFAULT_DELAY_CLICK;
			        private int     rippleFadeDuration  = DEFAULT_FADE_DURATION;
			        private boolean ripplePersistent    = DEFAULT_PERSISTENT;
			        private int     rippleBackground    = DEFAULT_BACKGROUND;
			        private boolean rippleSearchAdapter = DEFAULT_SEARCH_ADAPTER;
			        private float   rippleRoundedCorner = DEFAULT_ROUNDED_CORNERS;
			        public RippleBuilder(View child) {
				            this.child = child;
				            this.context = child.getContext();
				        }
			        public RippleBuilder rippleColor(int color) {
				            this.rippleColor = color;
				            return this;
				        }
			        public RippleBuilder rippleOverlay(boolean overlay) {
				            this.rippleOverlay = overlay;
				            return this;
				        }
			        public RippleBuilder rippleHover(boolean hover) {
				            this.rippleHover = hover;
				            return this;
				        }
			        public RippleBuilder rippleDiameterDp(int diameterDp) {
				            this.rippleDiameter = diameterDp;
				            return this;
				        }
			        public RippleBuilder rippleDuration(int duration) {
				            this.rippleDuration = duration;
				            return this;
				        }
			        public RippleBuilder rippleAlpha(float alpha) {
				            this.rippleAlpha = alpha;
				            return this;
				        }
			        public RippleBuilder rippleDelayClick(boolean delayClick) {
				            this.rippleDelayClick = delayClick;
				            return this;
				        }
			        public RippleBuilder rippleFadeDuration(int fadeDuration) {
				            this.rippleFadeDuration = fadeDuration;
				            return this;
				        }
			        public RippleBuilder ripplePersistent(boolean persistent) {
				            this.ripplePersistent = persistent;
				            return this;
				        }
			        public RippleBuilder rippleBackground(int color) {
				            this.rippleBackground = color;
				            return this;
				        }
			        public RippleBuilder rippleInAdapter(boolean inAdapter) {
				            this.rippleSearchAdapter = inAdapter;
				            return this;
				        }
			        public RippleBuilder rippleRoundedCorners(int radiusDp) {
				            this.rippleRoundedCorner = radiusDp;
				            return this;
				        }
			        public MaterialRippleLayout create() {
				            MaterialRippleLayout layout = new MaterialRippleLayout(context);
				            layout.setRippleColor(rippleColor);
				            layout.setDefaultRippleAlpha(rippleAlpha);
				            layout.setRippleDelayClick(rippleDelayClick);
				            layout.setRippleDiameter((int) dpToPx(context.getResources(), rippleDiameter));
				            layout.setRippleDuration(rippleDuration);
				            layout.setRippleFadeDuration(rippleFadeDuration);
				            layout.setRippleHover(rippleHover);
				            layout.setRipplePersistent(ripplePersistent);
				            layout.setRippleOverlay(rippleOverlay);
				            layout.setRippleBackground(rippleBackground);
				            layout.setRippleInAdapter(rippleSearchAdapter);
				            layout.setRippleRoundedCorners((int) dpToPx(context.getResources(), rippleRoundedCorner));
				            ViewGroup.LayoutParams params = child.getLayoutParams();
				            ViewGroup parent = (ViewGroup) child.getParent();
				            int index = 0;
				            if (parent != null && parent instanceof MaterialRippleLayout) {
					                throw new IllegalStateException("MaterialRippleLayout could not be created: parent of the view already is a MaterialRippleLayout");
					            }
				            if (parent != null) {
					                index = parent.indexOfChild(child);
					                parent.removeView(child);
					            }
				            layout.addView(child, new ViewGroup.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
				            if (parent != null) {
					                parent.addView(layout, index, params);
					            }
				            return layout;
				        }
			    }
	}
	{
	}
	
	
	public void _Prog_Dialogue_show(final boolean _ifShow, final String _title, final String _message) {
		if (_ifShow) {
			if (prog == null){
				prog = new ProgressDialog(this);
				prog.setMax(100);
				prog.setIndeterminate(true);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
			}
			prog.setMessage(_message);
			prog.show();
		}
		else {
			if (prog != null){
				prog.dismiss();
			}
		}
	}
	private ProgressDialog prog;
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