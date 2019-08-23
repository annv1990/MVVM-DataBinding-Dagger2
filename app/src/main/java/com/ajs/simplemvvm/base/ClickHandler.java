package com.ajs.simplemvvm.base;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.ajs.simplemvvm.MVVMApplication;

public class ClickHandler {

    Context context;

    public ClickHandler(Context context) {
        this.context = context;
    }

    /**
     * Demonstrating updating bind data
     * Profile name, number of posts and profile image
     * will be updated on Fab click
     */
    public void onProfileFabClicked(View view) {

    }

    public boolean onProfileImageLongPressed(View view) {
        Toast.makeText(MVVMApplication.getInstance(), "Profile image long pressed!", Toast.LENGTH_LONG).show();
        return false;
    }

    public void onFollowersClicked(View view) {
        Toast.makeText(context, "Followers is clicked!", Toast.LENGTH_SHORT).show();
    }

    public void onFollowingClicked(View view) {
        Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show();
    }

    public void onPostsClicked(View view) {
        Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show();
    }
}
