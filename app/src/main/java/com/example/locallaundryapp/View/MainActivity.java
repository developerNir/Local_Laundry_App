package com.example.locallaundryapp.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.locallaundryapp.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button choose_language, get_started;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "language_settings";
    private static final String LANGUAGE_KEY = "language";
    private static final String ITEM_KEY = "item";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLanguage();
        setContentView(R.layout.activity_main);

        choose_language = findViewById(R.id.choose_language);
        get_started = findViewById(R.id.get_start);
        choose_language.setOnClickListener(v -> showLanguageDialog());
        get_started.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, loginActivity.class));
        });

    }

    private void showLanguageDialog() {
        final String[] languages = {"English", "Bangla"};
        int currentItem = sharedPreferences.getInt(ITEM_KEY, 0);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(getString(R.string.Choose_language));

        alertDialog.setSingleChoiceItems(languages, currentItem, (dialog, position) -> {
            if (position == 0) {
                setLanguage("en", 0); // Using standard language codes
            } else if (position == 1) {
                setLanguage("bn", 1);
            }
            dialog.dismiss();
        });

        alertDialog.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.dismiss());
        alertDialog.create().show();
    }

    private void setLanguage(String languageCode, int item) {
        String currentLang = sharedPreferences.getString(LANGUAGE_KEY, "en");

        // Only change if language is different
        if (!currentLang.equals(languageCode)) {
            sharedPreferences.edit()
                    .putString(LANGUAGE_KEY, languageCode)
                    .putInt(ITEM_KEY, item)
                    .apply();

            applyLanguage(languageCode);

            // Recreate activity to apply language
            recreate();
        }
    }

    private void applyLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void loadLanguage() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String language = sharedPreferences.getString(LANGUAGE_KEY, "en");
        int item = sharedPreferences.getInt(ITEM_KEY, 0);

        // Apply language without recreation on load
        applyLanguage(language);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // Apply saved language to context
        SharedPreferences prefs = newBase.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String lang = prefs.getString(LANGUAGE_KEY, "en");

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources res = newBase.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
            Context context = newBase.createConfigurationContext(config);
            super.attachBaseContext(context);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
            super.attachBaseContext(newBase);
        }
    }



}