package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class LevelHiragana extends Activity {
    private static final String KEY_INDEX = "index";

    private ImageButton audio;
    private ImageButton next;
    private Button check;
    private ImageView img;
    private EditText enterText;

    private Hiragana [] myHiraganaSet = new Hiragana[] {
            new Hiragana("a"), new Hiragana("i"), new Hiragana("u"), new Hiragana("e"), new Hiragana("o")/*,
            new Hiragana("ka"), new Hiragana("ki"), new Hiragana("ku"), new Hiragana("ke"), new Hiragana("ko"),
            new Hiragana("sa"), new Hiragana("si"), new Hiragana("su"), new Hiragana("se"), new Hiragana("so"),
            new Hiragana("ta"), new Hiragana("chi"), new Hiragana("tsu"), new Hiragana("te"), new Hiragana("to"),
            new Hiragana("na"), new Hiragana("ni"), new Hiragana("nu"), new Hiragana("ne"), new Hiragana("no"),
            new Hiragana("ha"), new Hiragana("hi"), new Hiragana("fu"), new Hiragana("he"), new Hiragana("ho"),
            new Hiragana("ma"), new Hiragana("mi"), new Hiragana("mu"), new Hiragana("me"), new Hiragana("mo"),
            new Hiragana("ya"), new Hiragana("yu"), new Hiragana("yo"),
            new Hiragana("ra"), new Hiragana("ri"), new Hiragana("ru"), new Hiragana("re"), new Hiragana("ro"),
            new Hiragana("wa"), new Hiragana("wo"), new Hiragana("n")*/
    };

    private int myCurrentIndex = 0;

    private void updateCharacter(){
        String character = myHiraganaSet[myCurrentIndex].getMyImgName();
        String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);
    }

    private void checkAnswer (String userEnterAnswer){
        String answer = myHiraganaSet[myCurrentIndex].getMyAnswer();
        int messageResId = 0;

        if(answer.equals(userEnterAnswer))
            messageResId = R.string.correct_toast;

        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_hiragana);

        audio = (ImageButton) findViewById(R.id.btnAudio);
        next = (ImageButton) findViewById(R.id.btnNext);
        check = (Button) findViewById(R.id.btnCheck);
        img = (ImageView) findViewById(R.id.imageView);
        enterText = (EditText) findViewById(R.id.editText);

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LevelHiragana.this,"Audio Button Clicked!", Toast.LENGTH_LONG).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCurrentIndex = (myCurrentIndex + 1) % myHiraganaSet.length;
                updateCharacter();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(enterText.getText().toString());
            }
        });

        if (savedInstanceState != null) {
            myCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateCharacter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_one, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}