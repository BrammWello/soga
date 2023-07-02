package com.devbramm.soga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devbramm.soga.R;
import com.devbramm.soga.activities.utilities.SaveState;

public class TermsConditionsActivity extends AppCompatActivity {

    String fullText = "By using this app, you agree to the terms and conditions as set out in our web page.";
    String clickableText = "terms and conditions";
    SpannableString spannableString = new SpannableString(fullText);
    TextView textView;
    Button btnAgreeTerms;

    private SaveState saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        textView = findViewById(R.id.tv_terms_and_conditions);
        btnAgreeTerms = findViewById(R.id.btn_agree_terms);

        //check to see if user already accepted the terms and conditions
        saveState = new SaveState(this,"ob");
        if (saveState.getState() > 1){
            startActivity(new Intent(TermsConditionsActivity.this,PhoneVerificationActivity.class));
            finish();
        }

        btnAgreeTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveState.setState(2);
                startActivity(new Intent(TermsConditionsActivity.this, PhoneVerificationActivity.class));
            }
        });

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle the click event here, e.g., open a website
                Uri uri = Uri.parse("https://www.devbramm.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Remove the underline
            }
        };

        int clickableTextColor = getResources().getColor(R.color.teal_700); // Get the color value
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(clickableTextColor);
        spannableString.setSpan(clickableSpan, fullText.indexOf(clickableText), fullText.indexOf(clickableText) + clickableText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, fullText.indexOf(clickableText), fullText.indexOf(clickableText) + clickableText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance()); // Make the link clickable
        textView.setHighlightColor(Color.TRANSPARENT); // Remove the highlight color on click
    }

}