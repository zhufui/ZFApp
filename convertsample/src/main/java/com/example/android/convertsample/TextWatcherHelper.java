package com.example.android.convertsample;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by zf on 17-6-20.
 */
public class TextWatcherHelper {
    public static TextWatcher getTextWatcher(final ITextWatcher iTextWatcher) {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                iTextWatcher.onTextChanged(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        return textWatcher;
    }

}
