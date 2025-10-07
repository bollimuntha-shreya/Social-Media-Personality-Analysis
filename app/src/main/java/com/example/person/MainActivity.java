package com.example.person;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button submit;
    EditText linkedin, insta, twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        submit = findViewById(R.id.submit);
        linkedin = findViewById(R.id.linkedin);
        insta = findViewById(R.id.instagram);
        twitter = findViewById(R.id.twitter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValid() || !isInputValid()) {
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }

                    Intent intent = new Intent(MainActivity.this, OpenQuiz.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isInputValid() {
        String linkedinUrl = linkedin.getText().toString().trim();
        String instaUrl = insta.getText().toString().trim();
        String twitterUrl = twitter.getText().toString().trim();

        if (linkedinUrl.isEmpty() || !isValidLinkedInUrl(linkedinUrl)) {
            linkedin.setError("Please enter a valid LinkedIn URL");
            return false;
        }

        if (instaUrl.isEmpty() || !isValidInstagramUrl(instaUrl)) {
            insta.setError("Please enter a valid Instagram URL");
            return false;
        }

        if (twitterUrl.isEmpty() || !isValidTwitterUrl(twitterUrl)) {
            twitter.setError("Please enter a valid Twitter URL");
            return false;
        }

        return true;
    }

    private boolean isValidLinkedInUrl(String url) {
        String linkedinPattern = "^https?://www.linkedin.com/.+";
        return Pattern.matches(linkedinPattern, url);
    }

    private boolean isValidInstagramUrl(String url) {
        String instagramPattern = "^[a-zA-Z0-9_.]+$";
        return Pattern.matches(instagramPattern, url);
    }

    private boolean isValidTwitterUrl(String url) {
        String twitterPattern = "^https?://twitter.com/.+";
        return Pattern.matches(twitterPattern, url);
    }
}
