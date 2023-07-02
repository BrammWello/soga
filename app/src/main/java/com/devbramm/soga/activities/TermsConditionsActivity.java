package com.devbramm.soga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.devbramm.soga.R;

public class TermsConditionsActivity extends AppCompatActivity {

    String fullText = "By using this app, you agree to the terms and conditions as set out in our web page.";
    String clickableText = "terms and conditions";
    SpannableString spannableString = new SpannableString(fullText);
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        textView = findViewById(R.id.tv_terms_and_conditions);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle the click event here, e.g., open a website
                Uri uri = Uri.parse("https://www.devbramm.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };

        int clickableTextColor = getResources().getColor(R.color.teal_700); // Get the color value
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(clickableTextColor);
        spannableString.setSpan(clickableSpan, fullText.indexOf(clickableText), fullText.indexOf(clickableText) + clickableText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, fullText.indexOf(clickableText), fullText.indexOf(clickableText) + clickableText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance()); // Make the link clickable
    }

}