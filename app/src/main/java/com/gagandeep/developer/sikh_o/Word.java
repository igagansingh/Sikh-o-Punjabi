package com.gagandeep.developer.sikh_o;

/**
 * Created by gagandeep on 9/26/2016.
 */
public class Word {
    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private static final int  NO_IMAGE_PROVIDED=-1;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private int mSongResourceId = -1;

    public Word(String defaultTranslation,String miwokTranslation,int songResourceId)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mSongResourceId = songResourceId;
    }

    public Word(String defaultTranslation,String miwokTranslation,int imageResourceId,int songResourceId)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mImageResourceId = imageResourceId;
        mSongResourceId = songResourceId;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public  String getDefaultTranslation()
    {
        return  mDefaultTranslation;
    }
    public int getImageResourceId()
    {
        return mImageResourceId;
    }
    public boolean hasImage()
    {
        if(mImageResourceId == NO_IMAGE_PROVIDED)
            return false;
        else
            return true;
    }
    public int getSongResourceId()
    {
        return mSongResourceId;
    }
}
