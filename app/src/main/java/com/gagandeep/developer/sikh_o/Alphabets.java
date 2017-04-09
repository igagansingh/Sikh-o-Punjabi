package com.gagandeep.developer.sikh_o;

import java.io.Serializable;

/**
 * Created by gagandeep on 9/30/2016.
 */
public class Alphabets implements Serializable {

    private String punjabiPronunciation;
    private String englishPronunciation;
    private int imageResourceId = -1;
    private int image70ResourceId = -1;
    private int songResourceId = -1;
    public Alphabets(String punjabiPronunciation,String englishPronunciation,int imageResourceId,int songResourceId,int image70ResourceId)
    {
        this.punjabiPronunciation = punjabiPronunciation;
        this.englishPronunciation = englishPronunciation;
        this.imageResourceId = imageResourceId;
        this.songResourceId = songResourceId;
        this.image70ResourceId = image70ResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getSongResourceId() {
        return songResourceId;
    }

    public String getEnglishPronunciation() {
        return englishPronunciation;
    }

    public String getPunjabiPronunciation() {
        return punjabiPronunciation;
    }

    public int getImage70ResourceId() {
        return image70ResourceId;
    }
}
