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
public class NumbersFragment extends Fragment{
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
    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);
        //TextView textView = new TextView(getActivity());
        //textView.setText(R.string.hello_blank_fragment);

        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        //words.add("one");
        words.add(new Word("one", "ਇੱਕ",R.drawable.number_one,R.raw.one));
        words.add(new Word("two", "ਦੋ",R.drawable.number_two,R.raw.two));
        words.add(new Word("three", "ਤਿੰਨ",R.drawable.number_three,R.raw.three));
        words.add(new Word("four", "ਚਾਰ",R.drawable.number_four,R.raw.four));
        words.add(new Word("five", "ਪੰਜ",R.drawable.number_five,R.raw.five));
        words.add(new Word("six", "ਛੇ",R.drawable.number_six,R.raw.six));
        words.add(new Word("seven", "ਸੱਤ",R.drawable.number_seven,R.raw.seven));
        words.add(new Word("eight", "ਅੱਠ",R.drawable.number_eight,R.raw.eight));
        words.add(new Word("nine", "ਨੌਂ",R.drawable.number_nine,R.raw.nine));
        words.add(new Word("ten", "ਦਸ",R.drawable.number_ten,R.raw.zero));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words,R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //.makeText(this, "List VIew", Toast.LENGTH_SHORT).show();
                //Method one (self implemented)
                // Word word =(Word)parent.getItemAtPosition((int)id);
                // int voice = word.getSongResourceId(); /*Add this to MediaPlayer.create(..,here)*/

                //Method two ( by udacity)
                Word word= words.get(position);
                {
                    if(mediaPlayer!=null)
                        mediaPlayer.release();
                    else
                        mediaPlayer=null;
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
