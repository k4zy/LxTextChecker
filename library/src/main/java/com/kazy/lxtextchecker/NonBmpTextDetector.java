package com.kazy.lxtextchecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class NonBmpTextDetector extends TextChangeListener {

    private static final Pattern NON_BMP_PATTERN = Pattern.compile("([^\\u0000-\\uFFFF])");

    @Override
    public void onTextChanged(String originalText, int length) {
        Matcher matcher = NON_BMP_PATTERN.matcher(originalText);
        if (matcher.find()) {
            String nonBmpCharacter = matcher.group(1);
            String filteredText = originalText.replace(nonBmpCharacter, "");
            onDetectNonBmpCharacter(filteredText, nonBmpCharacter);
        }
    }

    public abstract void onDetectNonBmpCharacter(String filteredText, String nonBmpCharacter);

}
