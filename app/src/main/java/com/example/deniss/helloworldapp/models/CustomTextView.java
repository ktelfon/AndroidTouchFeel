package com.example.deniss.helloworldapp.models;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by Deniss on 03-Mar-16.
 */
public class CustomTextView extends TextView {

    private int customId;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, int customId) {
        super(context);
        this.customId = customId;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }
}
