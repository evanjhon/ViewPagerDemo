package com.zpq.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {
	private MyViewPager pager;
	private List<Image> images;
	private ImageAdapter adapter;
	
	private static int[] sDrawables = {R.drawable.t1, R.drawable.t2, R.drawable.t3,
		R.drawable.t4};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		pager = (MyViewPager) findViewById(R.id.my_pager);
		System.out.println("pager:"+pager);
		images = new ArrayList<Image>();
		init();
		pager.setAdapter(new ImageAdapter(images));
	}
	
	private void init(){
		for(int i = 0; i < sDrawables.length; i++){
			BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(sDrawables[i]);
			Bitmap bm = bd.getBitmap();
			Image image = new Image();
			image.setBitmap(bm);
			images.add(image);
		}
	}
	
	public class ImageAdapter extends PagerAdapter{
		private List<Image> list;
		public ImageAdapter(List<Image> list){
			this.list = list;
		}
		
		@Override
		public View instantiateItem(ViewGroup container, int position) {
			Image image = list.get(position);
			
			MyPhotoView photoView = null;
			if(null != image){
				System.out.println("context:"+container.getContext());
				photoView = new MyPhotoView(container.getContext());
				photoView.setImage(image.getBitmap());
			}
			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public int getCount() {
			return images.size();
		}
		
	}
}
