package com.dark30.mm;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import com.android.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ui.*;
import com.google.firebase.FirebaseApp;
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

public class Fag1FragmentActivity extends Fragment {
	
	private String title = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private SwipeRefreshLayout swiperefreshlayout1;
	private LinearLayout linear1;
	private ListView listview1;
	private TextView textview1;
	
	private RequestNetwork Req;
	private RequestNetwork.RequestListener _Req_request_listener;
	private Intent i = new Intent();
	private SharedPreferences sh;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.fag1_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		swiperefreshlayout1 = _view.findViewById(R.id.swiperefreshlayout1);
		linear1 = _view.findViewById(R.id.linear1);
		listview1 = _view.findViewById(R.id.listview1);
		textview1 = _view.findViewById(R.id.textview1);
		Req = new RequestNetwork((Activity) getContext());
		sh = getContext().getSharedPreferences("sh", Activity.MODE_PRIVATE);
		
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				swiperefreshlayout1.setRefreshing(false);
				Req.startRequestNetwork(RequestNetworkController.GET, getString(R.string.src2), "apis", _Req_request_listener);
			}
		});
		
		listview1.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView abs, int _scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView abs, int _firstVisibleItem, int _visibleItemCount, int _totalItemCount) {
				
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (listmap.get((int)(listmap.size() - _position) - 1).containsKey("title")) {
					i.putExtra("title", listmap.get((int)(listmap.size() - _position) - 1).get("title").toString());
					i.putExtra("image1", listmap.get((int)(listmap.size() - _position) - 1).get("image1").toString());
					i.putExtra("image2", listmap.get((int)(listmap.size() - _position) - 1).get("image2").toString());
					i.putExtra("info", listmap.get((int)(listmap.size() - _position) - 1).get("info").toString());
					i.putExtra("vip", listmap.get((int)(listmap.size() - _position) - 1).get("vip").toString());
					i.putExtra("link", listmap.get((int)(listmap.size() - _position) - 1).get("link").toString());
					i.putExtra("time", listmap.get((int)(listmap.size() - _position) - 1).get("time").toString());
					i.putExtra("media", listmap.get((int)(listmap.size() - _position) - 1).get("media").toString());
					i.setClass(getContext().getApplicationContext(), ViewvActivity.class);
					startActivity(i);
				}
			}
		});
		
		_Req_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				class task1 extends AsyncTask<Void, Void, Void> { 
					@Override
					protected Void doInBackground(Void... arg0) {
						
						listmap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						
						return null;
						 }
					protected void onPostExecute(Void result) {
						if (listmap.size() > 0) {
							textview1.setVisibility(View.GONE);
						}
						SketchwareUtil.sortListMap(listmap, "title", false, false);
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
										return ;
										    }
				}
				new task1().execute();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		if (sh.contains("sha")) {
			Req.startRequestNetwork(RequestNetworkController.GET, getString(R.string.src2), "apis", _Req_request_listener);
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.view, null);
			}
			
			final RelativeLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final ImageView imageview = _view.findViewById(R.id.imageview);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView time = _view.findViewById(R.id.time);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			if (_data.get((int)(_data.size() - _position) - 1).containsKey("title")) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)(_data.size() - _position) - 1).get("image1").toString())).into(imageview1);
				textview1.setText(_data.get((int)(_data.size() - _position) - 1).get("title").toString());
				time.setText(_data.get((int)(_data.size() - _position) - 1).get("time").toString());
				if (_data.get((int)(_data.size() - _position) - 1).get("vip").toString().equals("true")) {
					imageview.setImageResource(R.drawable.ic__1);
				}
			}
			notifyDataSetChanged();
			
			return _view;
		}
	}
}