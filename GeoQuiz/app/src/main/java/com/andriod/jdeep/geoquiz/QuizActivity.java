package com.andriod.jdeep.geoquiz;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextClock;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.jdeep.andriod.geoquiz.CheatActivity;

public class QuizActivity extends AppCompatActivity
{
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;


    private Question[] mQuestionBank= new Question[]
            {
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_americas, true),
                    new Question(R.string.question_asia, true)
            };

    private int mCurrentIndex = 0;
    //update question when the next button is clicked
    private void updateQuestion()
    {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    //check if the answer is true or not
    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerISTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;

        if(userPressedTrue == answerISTrue)
        {
            messageResId = R.string.correct_toast;
            updateQuestion();
        }
        else
        {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    @Override

    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        //keeps current state instead on restarting app on orientation change


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //define true button
        mTrueButton = (Button) findViewById(R.id.true_button);
        //set listeners
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        //set false listener
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(false);
            }
        });
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateQuestion();
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        //set next listener
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                mCurrentIndex= (mCurrentIndex + 1)% mQuestionBank.length;
                updateQuestion();
            }
        });
        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if( mCurrentIndex > 0)
                {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }
                else
                {
                    Toast.makeText(QuizActivity.this, R.string.previous_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Cheat Button
            mCheatButton = (Button)findViewById(R.id.cheat_button);
            mCheatButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //start cheat activity
                        Intent i =new Intent(QuizActivity.this, CheatActivity.class);
                        startActivity(i);
                }
            });
        //
        if(savedInstanceState !=null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }
    //overrides state of the app and saves the current state
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}