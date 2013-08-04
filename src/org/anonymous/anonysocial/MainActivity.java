package org.anonymous.anonysocial;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private String[] mLeftMenuItens;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private CharSequence mTitle;
	private SparseIntArray mPositionViewMap;

	@SuppressLint("UseSparseArrays")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_left_menu_drawer);
		
		mTitle = "";
		mPositionViewMap = new SparseIntArray(5);
		mPositionViewMap.put(0, R.layout.activity_main);
		mPositionViewMap.put(1, R.layout.item1);
		mPositionViewMap.put(2, R.layout.item2);
		mPositionViewMap.put(3, R.layout.item3);
		mPositionViewMap.put(4, R.layout.item4);
		
		mLeftMenuItens = getResources().getStringArray(R.array.main_left_menu_itens);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mLeftMenuItens));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		getActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_HOME | getActionBar().DISPLAY_HOME_AS_UP);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			if(mDrawerLayout.isDrawerOpen(mDrawerList))
				mDrawerLayout.closeDrawer(mDrawerList);
			else
				mDrawerLayout.openDrawer(mDrawerList);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			selectItem(position);
		}
		
		private void selectItem(int position) {
			// Create a new Fragment and specify which view to show based on position clicked
			Fragment fragment = new MainFragment();
			Bundle args = new Bundle();
			args.putInt(MainFragment.ARG_FRAGMENT, mPositionViewMap.get(position));
			fragment.setArguments(args);
			
			// Insert the Fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commit();
			
			// Highlight the selected item, update the title of actionbar, and close the drawer
			mDrawerList.setItemChecked(position, true);
			//setTitle(mLeftMenuItens[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
		
		private void setTitle(CharSequence newTitle) {
			mTitle = newTitle;
			getActionBar().setTitle(newTitle);
		}
		
	}

}
