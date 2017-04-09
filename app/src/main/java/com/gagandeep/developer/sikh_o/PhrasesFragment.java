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

import java.util.ArrayList;

/**
 * Created by gagandeep on 9/28/2016.
 */
public class PhrasesFragment extends Fragment {
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

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);
        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("Where are you going?","ਤੁਸੀਂ ਕਿੱਥੇ ਜਾ ਰਿਹਾ ਹੈ?",R.raw.zero));
        phrases.add(new Word("What is your name?","ਤੁਹਾਡਾ ਨਾਮ ਕੀ ਹੈ?",R.raw.zero));
        phrases.add(new Word("My name is...","ਮੇਰਾ ਨਾਮ ਹੈ...",R.raw.zero));
        phrases.add(new Word("How are you feeling?","ਤੁਸੀਂ ਕਿੱਦਾਂ ਮਹਿਸੂਸ ਕਰ ਰਹੇ ਹੋ?",R.raw.zero));
        phrases.add(new Word("I’m feeling good.","ਮੈਨੂੰ ਚੰਗਾ ਮਹਿਸੂਸ ਕਰ ਰਿਹਾ ਹੈ.",R.raw.zero));
        phrases.add(new Word("Are you coming?","ਤੁਹਾਨੂੰ ਆਉਣ ਰਹੇ ਹੋ?",R.raw.zero));
        phrases.add(new Word("Yes, I’m coming.","ਜੀ, ਮੈਨੂੰ ਆ ਰਿਹਾ.",R.raw.zero));
        phrases.add(new Word("I’m coming.","",R.raw.zero));
        phrases.add(new Word("Let’s go.","",R.raw.zero));
        phrases.add(new Word("Come here.","",R.raw.zero));

        WordAdapter adapter = new WordAdapter(getActivity(),phrases,R.color.category_phrases);
        ListView list = (ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //.makeText(this, "List VIew", Toast.LENGTH_SHORT).show();

                //Method one (self)
                // Word word =(Word)parent.getItemAtPosition((int)id);
                // int voice = word.getSongResourceId(); /*Add this to MediaPlayer.create(..,here)*/

                //Method two (udacity)
                Word word= phrases.get(position);
                {
                    if(mediaPlayer!=null) {
                        mediaPlayer.release();
                    }
                    else {
                        mediaPlayer=null;
                    }
                    audioManager.abandonAudioFocus(audioFocusChangeListener);
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
