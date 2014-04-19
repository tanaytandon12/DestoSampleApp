package yanat.destosampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class ViewActivity extends ActionBarActivity {
	
	private static int NUM_PAGES = 5;
	private ViewPager viewPager;
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);

		viewPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		Log.d("tag","onCreate in ViewActivity");
		viewPager.setAdapter(mPagerAdapter);
		viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int pos) {

					}
				});
	}

	private class MyPagerAdapter extends FragmentStatePagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Log.d("tag","getItem");
			return SlideFragment.create(arg0,getApplicationContext());
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "CELL" + (position + 1);
		}

	}
}
