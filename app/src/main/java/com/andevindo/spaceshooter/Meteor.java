package com.andevindo.spaceshooter;

import static com.andevindo.spaceshooter.GameView.METEOR_DESTROYED;
import static com.andevindo.spaceshooter.GameView.SCORE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created on   : 8/11/2017
 * Developed by : Hendrawan Adi Wijaya
 * Github       : https://github.com/andevindo
 * Website      : http://www.andevindo.com
 */

public class Meteor {
    private Bitmap mBitmap;
    private int mX;
    private int mY;
    private int mMaxX;
    private int mMinX;
    private int mMaxY;
    private int mMinY;

    private int mSpeed;
    private Rect mCollision;
    private int mScreenSizeX;
    private int mScreenSizeY;
    private int mLevel;
    private int mHP;
    private SoundPlayer mSoundPlayer;

    public Meteor(Context context,
                  int screenSizeX,
                  int screenSizeY,
                  int level,
                  SoundPlayer soundPlayer) {
        mScreenSizeX = screenSizeX;
        mScreenSizeY = screenSizeY;
        mLevel = level;
        mSoundPlayer = soundPlayer;

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor_1);
        mBitmap = Bitmap.createScaledBitmap(
                mBitmap,
                mBitmap.getWidth() * 3 / 5,
                mBitmap.getHeight() * 3 / 5,
                false
        );

        mMaxX = screenSizeX - mBitmap.getWidth();
        mMaxY = screenSizeY - mBitmap.getHeight();
        mMinX = 0;
        mMinY = 0;
        mHP = 3;

        int maxSpeed;
        switch (mLevel) {
            case 1:
                maxSpeed = 3;
                break;
            case 2:
                maxSpeed = 4;
                break;
            default:
                maxSpeed = 5;
                break;
        }
        Random random = new Random();
        mSpeed = random.nextInt(maxSpeed) + 1;

        mX = random.nextInt(mMaxX);
        mY = -mBitmap.getHeight();

        mCollision = new Rect(mX, mY, mX + mBitmap.getWidth(), mY + mBitmap.getHeight());
    }

    public void update() {
        mY += 7 * mSpeed;

        mCollision.left = mX;
        mCollision.top = mY;
        mCollision.right = mX + mBitmap.getWidth();
        mCollision.bottom = mY + mBitmap.getHeight();
    }

    public Rect getCollision() {
        return mCollision;
    }

    public void hit() {
        if (--mHP == 0) {
            SCORE += 20;
            METEOR_DESTROYED++;
            destroy();
        } else {
            mSoundPlayer.playExplode();
        }
    }

    public void destroy() {
        mY = mScreenSizeY + 1;
        mSoundPlayer.playCrash();
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }
}
