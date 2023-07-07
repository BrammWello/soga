package com.devbramm.soga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.devbramm.soga.R;

public class ProfileChangeStartActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;
    private ImageView ivProfilePicture;
    private ProgressBar pbProgressBar;
    private EditText etProfileName;
    private EditText etProfileAbout;
    private EditText etProfileStatus;
    private Button btnStartSoga;
    private int ImageSelected = 0;
    private int NameSelected = 0;
    private int AboutSelected = 0;
    private int StatusSelected = 0;
    private boolean nextBtnShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change_start);

        ivProfilePicture = findViewById(R.id.iv_profile_image_start);
        pbProgressBar = findViewById(R.id.pb_profile_page);
        etProfileName = findViewById(R.id.et_name_profile);
        etProfileAbout = findViewById(R.id.et_about_profile);
        etProfileStatus = findViewById(R.id.et_status_profile);
        btnStartSoga = findViewById(R.id.btn_start_soga);

        findViewById(R.id.btn_change_profile_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);

                // You will need to handle the result in onActivityResult() method
            }
        });

        btnStartSoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileChangeStartActivity.this, HomeActivity.class));
            }
        });

        updateUI();

    }

    private void updateUI() {
        etProfileName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String name = etProfileName.getText().toString().trim();
                    if (!name.isEmpty()) {
                        NameSelected = 1;
                    } else {
                        NameSelected = 0;
                    }

                    progressBarUpdater();
                }


            }
        });

        etProfileAbout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String about = etProfileAbout.getText().toString().trim();
                    if (!about.isEmpty()) {
                        AboutSelected = 1;
                    } else {
                        AboutSelected = 0;
                    }

                    progressBarUpdater();
                }
            }
        });


        etProfileStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if the user has entered at least one character
                if (s.length() > 0) {
                    StatusSelected = 1;
                    progressBarUpdater();
                } else {
                    StatusSelected = 0;
                    progressBarUpdater();
                }

                // Update the progress bar
                progressBarUpdater();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri imageUri = data.getData();

                // Load the selected image into ImageView
                ivProfilePicture.setImageURI(imageUri);
                ImageSelected = 1;
                progressBarUpdater();
            }
        }
    }

    private void progressBarUpdater() {
        int progress = ImageSelected + NameSelected + AboutSelected + StatusSelected;
        // Calculate the target progress value
        int targetProgress = progress * 25;

        //Toast.makeText(this, "" + targetProgress, Toast.LENGTH_SHORT).show();
        // Create an ObjectAnimator to animate the progress bar
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(pbProgressBar, "progress", pbProgressBar.getProgress(), targetProgress);
        progressAnimator.setDuration(1000); // Animation duration in milliseconds

        // Add an AnimatorListenerAdapter to handle animation completion
        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                if (targetProgress == 100 && !nextBtnShown) {
                    nextBtnShown = true;
                    // Fade out the progress bar
                    pbProgressBar.animate().alpha(0).setDuration(500).start();

                    // Fade in the button
                    btnStartSoga.setAlpha(0);
                    btnStartSoga.setVisibility(View.VISIBLE);
                    btnStartSoga.animate().alpha(1).setDuration(500).start();
                }
            }
        });

        // Start the animation
        progressAnimator.start();
    }

}