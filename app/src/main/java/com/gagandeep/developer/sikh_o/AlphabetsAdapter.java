package com.gagandeep.developer.sikh_o;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by gagandeep on 9/30/2016.
 */
public class AlphabetsAdapter extends ArrayAdapter<Alphabets> {
    public AlphabetsAdapter(Activity context, ArrayList<Alphabets> alphabets){
        super(context,0,alphabets);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;
        if (gridView == null)
        {
            gridView = LayoutInflater.from(getContext()).inflate(R.layout.alphabet_item, parent, false);
        }
        Alphabets currentAlphabet = getItem(position);

        TextView punjabiText = (TextView) gridView.findViewById(R.id.letter_punjabi);
        punjabiText.setText(currentAlphabet.getPunjabiPronunciation());

        TextView englishText = (TextView) gridView.findViewById(R.id.leter_english);
        englishText.setText(currentAlphabet.getEnglishPronunciation());

        ImageView gurmukhiAlphabetImage = (ImageView) gridView.findViewById(R.id.alphabetimage);
        gurmukhiAlphabetImage.setImageResource(currentAlphabet.getImageResourceId());

        return gridView;
    }
}
