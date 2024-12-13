package com.titdevelopercommunity.titsports;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.view.ScaleGestureDetector;

import com.bumptech.glide.Glide; // Glide for loading images

public class FullScreenImageActivity extends AppCompatActivity {

    private ImageView fullScreenImageView;
    private ScaleGestureDetector scaleGestureDetector; // For detecting pinch gestures
    private float scaleFactor = 1.f; // Initial scale factor
    private float lastTouchX = 0, lastTouchY = 0; // Store the last touch position for pan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        fullScreenImageView = findViewById(R.id.fullScreenImageView);

        // Get the image URL from the intent
        String imageUrl = getIntent().getStringExtra("image_url");

        // Load the image into the ImageView using Glide
        Glide.with(this)
                .load(imageUrl)
                .into(fullScreenImageView);

        // Initialize the ScaleGestureDetector
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event); // Pass touch events to the scale detector

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // For panning the image (optional)
                if (scaleFactor > 1) { // Only allow panning when zoomed in
                    float dx = event.getX() - lastTouchX;
                    float dy = event.getY() - lastTouchY;
                    fullScreenImageView.setTranslationX(fullScreenImageView.getTranslationX() + dx);
                    fullScreenImageView.setTranslationY(fullScreenImageView.getTranslationY() + dy);
                }
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                break;
        }

        return true;
    }

    // ScaleListener to detect pinch-to-zoom gestures
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor(); // Get the scale factor from the pinch
            scaleFactor = Math.max(1f, Math.min(scaleFactor, 5f)); // Limit the scale factor between 1 and 5

            // Apply the scale transformation to the ImageView using Matrix
            fullScreenImageView.setScaleX(scaleFactor);
            fullScreenImageView.setScaleY(scaleFactor);

            return true;
        }
    }
}
