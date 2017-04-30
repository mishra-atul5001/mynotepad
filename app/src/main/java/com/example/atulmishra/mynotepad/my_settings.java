package com.example.atulmishra.mynotepad;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

/**
 * Created by Atul Mishra on 30-04-17.
 */

public class my_settings extends PreferenceActivity {

    @SuppressWarnings("deprecation")



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
