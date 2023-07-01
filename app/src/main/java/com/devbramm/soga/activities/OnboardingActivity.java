package com.devbramm.soga.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devbramm.soga.R;
import com.devbramm.soga.activities.adapters.OnboardingAdapter;
import com.devbramm.soga.activities.utilities.SaveState;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button next;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private SaveState saveState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager2);
        next = findViewById(R.id.btn_next_onboarding);
        dotsLayout = findViewById(R.id.ll_dots_layout);
        saveState = new SaveState(this,"ob");

        if (saveState.getState() == 1){
            startActivity(new Intent(OnboardingActivity.this,TermsConditionsActivity.class));
            finish();
        }

        OnboardingAdapter adapter = new OnboardingAdapter(this);
        viewPager.setAdapter(adapter);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    viewPager.setCurrentItem(1, true);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position < 3){
                            viewPager.setCurrentItem(position + 1, true);
                        } else {
                            saveState.setState(1);
                        }
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dotsFunction(0);

    }

    private void dotsFunction(int pos) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0 ; i< dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("-"));
            dots[i].setTextColor(getColor(R.color.purple_200));  //unselected color
            dots[i].setTextSize(40);    //unselected size

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0){
            dots[pos].setTextColor(getColor(R.color.purple_700));   //selected dot color
            dots[pos].setTextSize(40);  //selected dots size
        }
    }
}