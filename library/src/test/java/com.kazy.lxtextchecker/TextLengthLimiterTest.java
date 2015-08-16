package com.kazy.lxtextchecker;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.text.Editable;
import android.text.SpannableStringBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class TextLengthLimiterTest {


    @Test
    public void called_onExceedLimitOfTextLength() {
        final int limit = 10;
        TextLengthLimiter limiter = new TextLengthLimiter(limit) {
            @Override
            public void onExceedLimitOfTextLength(String truncatedText) {
                assertTrue(true);
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM_LGTM_LGTM");
        limiter.afterTextChanged(editable);
    }

    @Test
    public void does_not_call() {
        final int limit = 10;
        TextLengthLimiter limiter = new TextLengthLimiter(limit) {
            @Override
            public void onExceedLimitOfTextLength(String truncatedText) {
                fail();
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM");
        limiter.beforeTextChanged(editable.toString(), 0, 0, 0);
        limiter.onTextChanged(editable.toString(), 0, 0, 0);
        limiter.afterTextChanged(editable);
    }

    @Test
    public void valid_truncatedText() {
        final int limit = 10;
        TextLengthLimiter limiter = new TextLengthLimiter(limit) {
            @Override
            public void onExceedLimitOfTextLength(String truncatedText) {
                assertEquals("LGTM_LGTM_", truncatedText);
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM_LGTM_LGTM");
        limiter.afterTextChanged(editable);

    }

}
