package com.github.playground.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.playground.R;

public class ToastUtils {
    private ToastUtils() {
    }

    public static void show(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackground(ContextCompat.getDrawable(context, R.drawable.toast_drawable));
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
