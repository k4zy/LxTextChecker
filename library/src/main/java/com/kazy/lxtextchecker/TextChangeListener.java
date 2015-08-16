package com.kazy.lxtextchecker;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class TextChangeListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    public abstract void onTextChanged(String text, int length);

    @Override
    public final void afterTextChanged(Editable editable) {
        String text = editable.toString();
        int length = text.length();
        onTextChanged(text, length);
    }
}

