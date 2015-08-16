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
public class NonBmpTextDetectorTest {

    @Test
    public void inputs_bmp_nonBmp_character() {
        NonBmpTextDetector detector = new NonBmpTextDetector() {
            @Override
            public void onDetectNonBmpCharacter(String filteredText, String nonBmpCharacter) {
                assertTrue(true);
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM\uD83D\uDE46");
        detector.afterTextChanged(editable);
    }

    @Test
    public void inputs_bmp_character() {
        NonBmpTextDetector detector = new NonBmpTextDetector() {
            @Override
            public void onDetectNonBmpCharacter(String filteredText, String nonBmpCharacter) {
                fail();
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM");
        detector.afterTextChanged(editable);
        assertTrue(true);
    }

    @Test
    public void does_not_call_onDetectNonBmpCharacter() {
        NonBmpTextDetector detector = new NonBmpTextDetector() {
            @Override
            public void onDetectNonBmpCharacter(String filteredText, String nonBmpCharacter) {
                fail();
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM\uD83D\uDE46");
        detector.beforeTextChanged(editable.toString(), 0, 0, 0);
        detector.onTextChanged(editable.toString(), 0, 0, 0);
        assertTrue(true);
    }

    @Test
    public void filteredText_does_not_have_emoji() {
        NonBmpTextDetector detector = new NonBmpTextDetector() {
            @Override
            public void onDetectNonBmpCharacter(String filteredText, String nonBmpCharacter) {
                assertEquals("LGTM", filteredText);
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM\uD83D\uDE46");
        detector.afterTextChanged(editable);
    }

    @Test
    public void nonBmpCharacter_contains_emoji() {
        NonBmpTextDetector detector = new NonBmpTextDetector() {
            @Override
            public void onDetectNonBmpCharacter(String filteredText, String nonBmpCharacter) {
                assertEquals("\uD83D\uDE46", nonBmpCharacter);
            }
        };
        Editable editable = new SpannableStringBuilder("LGTM\uD83D\uDE46");
        detector.afterTextChanged(editable);
    }

}
