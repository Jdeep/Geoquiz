package com.andriod.jdeep.geoquiz;

/**
 * Created by Jdeep on 6/24/2016.
 */
public class Question {
    //holds the resource id of the sting resource
    private int mTextResId;
    //
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
    public int getTextResId()
    {
        return mTextResId;
    }
    public void setTextResId(int textResId)
    {
        mTextResId = textResId;
    }
    public boolean isAnswerTrue()
    {
        return mAnswerTrue;
    }
    public void setAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue = answerTrue;
    }

}
