package org.anonymous.anonysocial;

import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
	
	public static final String ARG_FRAGMENT = "arg_fragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = null;
		try {
			v = inflater.inflate(getArguments().getInt(ARG_FRAGMENT), null);
		} catch(InflateException exception) {
			return super.onCreateView(inflater, container, savedInstanceState);
		}
		
		return v;
		
	}

}
