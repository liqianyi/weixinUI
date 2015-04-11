package com.example.mainframework04;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;

public class MainActivity extends FragmentActivity
{
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

	/**
	 * 顶部四个按钮
	 */
	private LinearLayout mTabLiaotian;
	private LinearLayout mTabFaxian;
	private LinearLayout mTabTongxunlun;

	private TextView mLiaotian;
	private TextView mFaxian;
	private TextView mTongxunlu;

	private BadgeView mBadgeViewforLiaotian;
	private BadgeView mBadgeViewforFaxian;
	private BadgeView mBadgeViewforTongxunlu;

	private ImageView mTabLine;

	private int currentIndex;
	private int screenWidth;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		initView();

		initTabLine();

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};

		mViewPager.setAdapter(mAdapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{
				resetTextView();
				switch (position)
				{
				case 0:
					mTabLiaotian.removeView(mBadgeViewforLiaotian);
					mBadgeViewforLiaotian.setBadgeCount(5);
					mTabLiaotian.addView(mBadgeViewforLiaotian);
					mLiaotian.setTextColor(getResources().getColor(R.color.green));
					break;
				case 1:
					mFaxian.setTextColor(getResources().getColor(R.color.green));
					
					mTabFaxian.removeView(mBadgeViewforFaxian);
					mBadgeViewforFaxian.setBadgeCount(15);
					mTabFaxian.addView(mBadgeViewforFaxian);
					break;
				case 2:
					mTongxunlu.setTextColor(getResources().getColor(R.color.green));
					
					break;
				}

				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				if (currentIndex == 0 && position == 0)// 0->1
				{
					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
					mTabLine.setLayoutParams(lp);
				} else if (currentIndex == 1 && position == 0) // 1->0
				{
					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (-(1-positionOffset) * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
					mTabLine.setLayoutParams(lp);

				} else if (currentIndex == 1 && position == 1) // 1->2
				{
					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
					mTabLine.setLayoutParams(lp);
				} else if (currentIndex == 2 && position == 1) // 2->1
				{
					LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine
							.getLayoutParams();
					lp.leftMargin = (int) (-(1-positionOffset) * (screenWidth * 1.0 / 3) + currentIndex * (screenWidth / 3));
					mTabLine.setLayoutParams(lp);

				}
				Log.e("xxx", positionOffset + " 0000 " + positionOffsetPixels + " ---- " + position);

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
			}
		});

		mViewPager.setCurrentItem(1);

	}

	private void initTabLine()
	{
		mTabLine = (ImageView) findViewById(R.id.id_tab_line);
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine.getLayoutParams();
		lp.width = screenWidth / 3;
		mTabLine.setLayoutParams(lp);
	}

	/**
	 * 重置颜色
	 */
	protected void resetTextView()
	{
		mLiaotian.setTextColor(getResources().getColor(R.color.black));
		mFaxian.setTextColor(getResources().getColor(R.color.black));
		mTongxunlu.setTextColor(getResources().getColor(R.color.black));
	}

	private void initView()
	{

		mTabLiaotian = (LinearLayout) findViewById(R.id.id_tab_liaotian_ly);
		mTabFaxian = (LinearLayout) findViewById(R.id.id_tab_faxian_ly);
		mTabTongxunlun = (LinearLayout) findViewById(R.id.id_tab_tongxunlu_ly);

		mLiaotian = (TextView) findViewById(R.id.id_liaotian);
		mFaxian = (TextView) findViewById(R.id.id_faxian);
		mTongxunlu = (TextView) findViewById(R.id.id_tongxunlu);

		MainTab01 tab01 = new MainTab01();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);

		mBadgeViewforFaxian = new BadgeView(this);
		mBadgeViewforLiaotian = new BadgeView(this);
		mBadgeViewforTongxunlu = new BadgeView(this);
	}
}
