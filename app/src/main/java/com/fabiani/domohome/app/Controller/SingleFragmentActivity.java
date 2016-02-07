package com.fabiani.domohome.app.controller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import com.fabiani.domohome.app.R;

public abstract class SingleFragmentActivity extends Activity {
	protected abstract Fragment createFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if (fragment==null) {
			fragment=createFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment)
					.commit();
		}
	}
}
