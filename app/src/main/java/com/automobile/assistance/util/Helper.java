package com.automobile.assistance.util;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.automobile.assistance.R;
import com.automobile.assistance.app.Constant;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Helper {

    public static void enableView(View view, boolean enabled) {
        view.setEnabled(enabled);

        if ( view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableView(group.getChildAt(idx), enabled);
            }
        }
    }

    public static void addTextWatcher(TextInputEditText editText, TextInputLayout editTextLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void snackBar(View parent, String message) {
        Snackbar snackbar = Snackbar.make(parent, "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.findViewById(R.id.snackbar_text).setVisibility(View.INVISIBLE);

        View snackBarView = LayoutInflater.from(parent.getContext()).inflate(R.layout.snackbar_layout, null);
        TextView textView = snackBarView.findViewById(R.id.text);
        textView.setText(message);

        layout.setPadding(0,0,0,0);
        layout.addView(snackBarView, 0);
        snackbar.show();
    }

    public static void dialogAlert(Context context, String title, String msg) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public static void dialogAlert(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public static void showDialogError(Context context, Throwable e, String title) {
        String errMsg = "";
        if (e.getMessage().contains("Unable to resolve host")) {
            errMsg = Constant.Message.CANNOT_RESOLVE_HOST_ERROR;
        } else {
            errMsg = Constant.Message.SOMETHING_WENT_WRONG;
        }

        Helper.dialogAlert(context, title, errMsg);
    }

}
