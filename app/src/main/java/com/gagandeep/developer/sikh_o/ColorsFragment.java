package com.gagandeep.developer.sikh_o;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        if(mediaPlayer!=null)
                            mediaPlayer.release();
                        else
                            mediaPlayer=null;
                        audioManager.abandonAudioFocus(audioFocusChangeListener);
                    }
                }
            };


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.words_list, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("red","ਲਾਲ",R.drawable.color_red,R.raw.color_red));
        colors.add(new Word("green","ਹਰਾ",R.drawable.color_green,R.raw.color_green));
        colors.add(new Word("brown","ਭੂਰਾ",R.drawable.color_brown,R.raw.color_brown));
        colors.add(new Word("gray","ਸਲੇਟੀ",R.drawable.color_gray,R.raw.color_gray));
        colors.add(new Word("black","ਕਾਲਾ",R.drawable.color_black,R.raw.color_black));
        colors.add(new Word("white","ਚਿੱਟਾ",R.drawable.color_white,R.raw.color_white));
        colors.add(new Word("yellow","ਪੀਲਾ",R.drawable.color_mustard_yellow,R.raw.color_yellow));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), colors,R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //.makeText(this, "List VIew", Toast.LENGTH_SHORT).show();
                //Method one (self)
                // Word word =(Word)parent.getItemAtPosition((int)id);
                // int voice = word.getSongResourceId(); /*Add this to MediaPlayer.create(..,here)*/

                //Method two (udacity)
                Word word= colors.get(position);

                {
                    if(mediaPlayer!=null)
                        mediaPlayer.release();
                    else
                        mediaPlayer=null;
                }

                int result = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(getActivity(),word.getSongResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if(mediaPlayer!=null)
                                mediaPlayer.release();
                            else
                                mediaPlayer=null;
                            audioManager.abandonAudioFocus(audioFocusChangeListener);
                        }
                    });
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mediaPlayer!=null)
            mediaPlayer.release();
        else
            mediaPlayer=null;
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }
}
