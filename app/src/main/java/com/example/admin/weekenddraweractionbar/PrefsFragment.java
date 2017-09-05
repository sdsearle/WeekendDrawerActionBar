package com.example.admin.weekenddraweractionbar;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by admin on 9/5/2017.
 */

public class PrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);
    }
}
