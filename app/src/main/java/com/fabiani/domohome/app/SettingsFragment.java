package com.fabiani.domohome.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.*;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsFragment extends PreferenceFragment {
	static final String TAG = "SettingsFragment";
	static final String IP_KEY="ip";
	static final String PASSWORD_OPEN_KEY="passwordopen";
	static final String EXTRA_IP_IS_VALID ="com.fabiani.domohome.app.extra_ip_is_valid";
	static final String EXTRA_PASSWORD_OPEN_IS_VALID ="com.fabiani.domohome.app.extra_passord_open_is_valid";
	static String sAddressInput;
	static String sPasswordOpenInput;
	static boolean isIpValid=false;
	static boolean isPassordOpenValid=false;
	private EditTextPreference mPasswordOpenEditTextPreference;
	private Pattern mIpPattern = Patterns.IP_ADDRESS;
	private Matcher mIpMatcher;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		isIpValid = getArguments().getBoolean(EXTRA_IP_IS_VALID);
		isPassordOpenValid=getArguments().getBoolean(EXTRA_PASSWORD_OPEN_IS_VALID);
		EditTextPreference mIpEditTextPreference = (EditTextPreference) findPreference("IP_KEY");
		mIpEditTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				sAddressInput = newValue.toString();
				Dashboard.sIp = sAddressInput;
				SharedPreferences.Editor editor = PreferenceManager.
						getDefaultSharedPreferences(getActivity()).edit();
				editor.putString(IP_KEY, sAddressInput);
				editor.apply();
				if (isIpValid())
					isIpValid = true;
				else {
					Toast.makeText(getActivity(), R.string.connector_ip_error, Toast.LENGTH_SHORT).show();
					isIpValid = false;
				}
				editor.putBoolean(EXTRA_IP_IS_VALID, isIpValid);
				editor.apply();
				return true;
			}
		});
		mIpEditTextPreference.setText(Dashboard.sIp);

		mPasswordOpenEditTextPreference = (EditTextPreference) findPreference("PASSWORD_OPEN_KEY");
		final SharedPreferences.Editor editor = PreferenceManager.
				getDefaultSharedPreferences(getActivity()).edit();
		mPasswordOpenEditTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				sPasswordOpenInput = newValue.toString();
				try {
					Dashboard.sPasswordOpen = Integer.parseInt(sPasswordOpenInput);
					editor.putInt(PASSWORD_OPEN_KEY, Integer.parseInt(sPasswordOpenInput));
					isPassordOpenValid = true;
				} catch (NumberFormatException e) {
					isPassordOpenValid = false;
					Toast.makeText(getActivity(), R.string.commandgridgragment_valid_password, Toast.LENGTH_SHORT).show();
				}finally {
					editor.putBoolean(EXTRA_PASSWORD_OPEN_IS_VALID,isPassordOpenValid);
					editor.apply();
				}
				return true;
			}
		});
		if (Dashboard.sPasswordOpen == 0)
			mPasswordOpenEditTextPreference.setText("");
		 else
			mPasswordOpenEditTextPreference.setText(Integer.toString(Dashboard.sPasswordOpen));
	}

	public  boolean isIpValid() {
		mIpMatcher = mIpPattern.matcher(sAddressInput);
        return mIpMatcher.matches() ? true : false;
	}

	public static SettingsFragment newInstance(){
		Bundle args=new Bundle();
		args.putBoolean(EXTRA_IP_IS_VALID, isIpValid);
		args.putBoolean(EXTRA_PASSWORD_OPEN_IS_VALID,isPassordOpenValid);
		SettingsFragment fragment=new SettingsFragment();
		fragment.setArguments(args);
		return fragment;
	}
}