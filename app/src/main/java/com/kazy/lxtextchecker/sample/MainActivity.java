package com.kazy.lxtextchecker.sample;

import com.kazy.lxtextchecker.NonBmpTextDetector;
import com.kazy.lxtextchecker.TextLengthLimiter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.edit_text)
    EditText editText;

    private static final int LIMIT_THE_NUMBER_OF_CHARS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        editText.addTextChangedListener(new NonBmpTextDetector() {
            @Override
            public void onDetectNonBmpCharacter(String filteredText, String nonBmpCharacter) {
                editText.setText(filteredText);
                editText.setSelection(editText.getText().length());
                String message = getString(R.string.invalid_character, nonBmpCharacter);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        editText.addTextChangedListener(new TextLengthLimiter(LIMIT_THE_NUMBER_OF_CHARS) {
            @Override
            public void onExceedLimitOfTextLength(String truncatedText) {
                editText.setText(truncatedText);
                editText.setSelection(LIMIT_THE_NUMBER_OF_CHARS);
                String message = getString(R.string.exceed_characters, LIMIT_THE_NUMBER_OF_CHARS);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
