package com.example.matchgamesample.counter;

import android.view.View;
import android.widget.TextView;

import com.example.matchgamesample.engine.GameEvent;
import com.example.matchgamesample.engine.GameEventListener;
import com.example.matchgamesample.engine.GameObject;

public class ScoreCounter extends GameObject implements GameEventListener {
    private static final int POINTS_GAINED_PER_FRUIT = 10;

    private final TextView mText;
    private int mPoints;
    private boolean mPointsHaveChanged;

    public ScoreCounter(View view, int viewResId){
        mText = (TextView) view.findViewById(viewResId);
    }

    @Override
    public void startGame() {
        mPoints = 0;
        mText.setText(String.valueOf(mPoints));
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onDraw() {
        if (mPointsHaveChanged) {
            mText.setText(String.valueOf(mPoints));
            mPointsHaveChanged = false;
        }
    }

    @Override
    public void onGameEvent(GameEvent gameEvents) {
        if (gameEvents == GameEvent.SCORE) {
            mPoints += POINTS_GAINED_PER_FRUIT;
            mPointsHaveChanged = true;
        }
    }
}
