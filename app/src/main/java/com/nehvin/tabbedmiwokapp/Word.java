package com.nehvin.tabbedmiwokapp;

/**
 * Created by Vineet K Jain on 12/29/2016.
 */

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceID = NO_RESOURCE_PROVIDED;
    private int mAudioResourceID = NO_RESOURCE_PROVIDED;
    private static final int NO_RESOURCE_PROVIDED = -1;

    public Word (String defaultTranslation,String miwokTranslation, int audioResourceID)
    {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioResourceID = audioResourceID;
    }

    public Word (String defaultTranslation,String miwokTranslation, int imageResourceID, int audioResourceID)
    {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResourceID = imageResourceID;
        mAudioResourceID = audioResourceID;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageResourceID()
    {
        return mImageResourceID;
    }

    public boolean hasImage()
    {
        return mImageResourceID != NO_RESOURCE_PROVIDED;
    }

    public int getmAudioResourceID()
    {
        return mAudioResourceID;
    }

    public boolean hasAudioFile()
    {
        return mAudioResourceID != NO_RESOURCE_PROVIDED;
    }
}