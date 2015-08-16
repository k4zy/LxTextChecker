package com.kazy.lxtextchecker;

public abstract class TextLengthLimiter extends TextChangeListener {

    private int limit;

    public TextLengthLimiter(int limit) {
        this.limit = limit;
    }

    @Override
    public void onTextChanged(String originalText, int length) {
        if (length > limit) {
            onExceedLimitOfTextLength(originalText.substring(0, limit));
        }
    }

    public abstract void onExceedLimitOfTextLength(String truncatedText);

}
