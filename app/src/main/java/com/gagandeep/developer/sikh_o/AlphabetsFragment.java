package com.gagandeep.developer.sikh_o;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gagandeep on 9/28/2016.
 */
public class AlphabetsFragment extends Fragment {
    public AlphabetsFragment(){

    }
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.alphabets_fragment, container, false);
        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Alphabets> alphabets = new ArrayList<>();
        alphabets.add(new Alphabets("ਓੂੜਾ","Oo'rah",R.drawable.oorah,R.raw.zero,R.drawable.oorah_72));
        alphabets.add(new Alphabets("ਐੜਾ","Ai'rhaa",R.drawable.airhaa,R.raw.zero,R.drawable.airhaa_72));
        alphabets.add(new Alphabets("ਈੜੀ","Ee'rhee",R.drawable.eerhee,R.raw.zero,R.drawable.eerhee_72));
        alphabets.add(new Alphabets("ਸੱਸਾ","sas'saa",R.drawable.sassaa,R.raw.zero,R.drawable.sassaa_72));
        alphabets.add(new Alphabets("ਹਾਹਾ","haa'haa",R.drawable.haahaa,R.raw.zero,R.drawable.haahaa_72));
        alphabets.add(new Alphabets("ਕੱਕਾ","Kak'kaa",R.drawable.kakkaa,R.raw.zero,R.drawable.kakkaa_72));
        alphabets.add(new Alphabets("ਖੱਖਾ","khakh'khaa",R.drawable.khakhkha,R.raw.zero,R.drawable.khakhkha_72));
        alphabets.add(new Alphabets("ਗੱਗਾ","gag'gaa",R.drawable.gaggaa,R.raw.zero,R.drawable.gaggaa_72));
        alphabets.add(new Alphabets("ਘੱਘਾ","ghag'ghaa",R.drawable.ghagghaa,R.raw.zero,R.drawable.ghagghaa_72));
        alphabets.add(new Alphabets("ਙੰਙਾ","Ngan'ngaa",R.drawable.nganngaa,R.raw.zero,R.drawable.nganngaa_72));
        alphabets.add(new Alphabets("ਚੱਚਾ","chach'chaa",R.drawable.chachchaa,R.raw.zero,R.drawable.chachchaa_72));
        alphabets.add(new Alphabets("ਛੱਛਾ","chhachh'chhaa",R.drawable.chhachhchhaa,R.raw.zero,R.drawable.chhachhchhaa_72));
        alphabets.add(new Alphabets("ਜੱਜਾ","jaj'jaa",R.drawable.jajjaa,R.raw.zero,R.drawable.jajjaa_72));
        alphabets.add(new Alphabets("ਝੱਝਾ","jhaj'jhaa",R.drawable.jajjhaa,R.raw.zero,R.drawable.jajjhaa_72));
        alphabets.add(new Alphabets("ਞੰਞਾ","Njan'njaa",R.drawable.abcde,R.raw.zero,R.drawable.abcde_72));
        alphabets.add(new Alphabets("ਟੈਂਕਾ","tain'kaa",R.drawable.tainkaa,R.raw.zero,R.drawable.tainkaa_72));
        alphabets.add(new Alphabets("ਠੱਠਾ","thath'thaa",R.drawable.thaththaa,R.raw.zero,R.drawable.thaththaa_72));
        alphabets.add(new Alphabets("ਡੱਡਾ","ddad'daa",R.drawable.ddaddaa,R.raw.zero,R.drawable.ddaddaa_72));
        alphabets.add(new Alphabets("ਢੱਢਾ","dhad'daa",R.drawable.dhaddaa,R.raw.zero,R.drawable.dhaddaa_72));
        alphabets.add(new Alphabets("ਣਾਣਾ","nhaa'nhaa",R.drawable.nhaanhaa,R.raw.zero,R.drawable.nhaanhaa_72));
        alphabets.add(new Alphabets("ਤੱਤਾ","tat'taa",R.drawable.tattaa,R.raw.zero,R.drawable.tattaa_72));
        alphabets.add(new Alphabets("ਥੱਥਾ","thath'thaa",R.drawable.thaththaa2,R.raw.zero,R.drawable.thaththaa2_72));
        alphabets.add(new Alphabets("ਦੱਦਾ","dad'daa",R.drawable.daddaa,R.raw.zero,R.drawable.daddaa_72));
        alphabets.add(new Alphabets("ਧੱਧਾ","dhad'daa",R.drawable.dhaddaa2,R.raw.zero,R.drawable.dhaddaa2_72));
        alphabets.add(new Alphabets("ਨੱਨਾ","nan'naa",R.drawable.nannaa,R.raw.zero,R.drawable.nannaa_72));
        alphabets.add(new Alphabets("ਪੱਪਾ","pap'paa",R.drawable.pappaa,R.raw.zero,R.drawable.pappaa_72));
        alphabets.add(new Alphabets("ਫੱਫਾ","phaph'phaa",R.drawable.phaphphaa,R.raw.zero,R.drawable.phaphphaa_72));
        alphabets.add(new Alphabets("ਬੱਬਾ","bab'baa",R.drawable.babbaa,R.raw.zero,R.drawable.babbaa_72));
        alphabets.add(new Alphabets("ਭੱਭਾ","bhab'baa",R.drawable.bhabbaa,R.raw.zero,R.drawable.bhabbaa_72));
        alphabets.add(new Alphabets("ਮੱਮਾ","mam'maa",R.drawable.mammaa,R.raw.zero,R.drawable.mammaa_72));
        alphabets.add(new Alphabets("ਯੱਯਾ","yay'yaa",R.drawable.yayyaa,R.raw.zero,R.drawable.yayyaa_72));
        alphabets.add(new Alphabets("ਰਾਰਾ","ra'raa",R.drawable.rarraa,R.raw.zero,R.drawable.rarraa_72));
        alphabets.add(new Alphabets("ਲੱਲਾ","lal'laa",R.drawable.lallaa,R.raw.zero,R.drawable.rarraa_72));
        alphabets.add(new Alphabets("ਵੱਵਾ","vav'vaa",R.drawable.vavvaa,R.raw.zero,R.drawable.vavvaa_72));
        alphabets.add(new Alphabets("ੜਾੜਾ","rhar'rhaa",R.drawable.rharrhaa,R.raw.zero,R.drawable.rharrhaa_72));
        alphabets.add(new Alphabets("ਸ਼ੱਸ਼ਾ","shash'shaa",R.drawable.shashshaa,R.raw.zero,R.drawable.shashshaa_72));
        alphabets.add(new Alphabets("ਖ਼ੱਖ਼ਾ","kha'khaa",R.drawable.khakhaa,R.raw.zero,R.drawable.khakhaa_72));
        alphabets.add(new Alphabets("ਗ਼ੱਗ਼ਾ","gag'gaa",R.drawable.gaggaa2,R.raw.zero,R.drawable.gaggaa2_72));
        alphabets.add(new Alphabets("ਜ਼ੱਜ਼ਾ","Zaz'zaa",R.drawable.zazzaa,R.raw.zero,R.drawable.zazzaa_72));
        alphabets.add(new Alphabets("ਫ਼ੱਫ਼ਾ","faf'faa",R.drawable.faffaa,R.raw.zero,R.drawable.faffaa_72));
        alphabets.add(new Alphabets("ਲ਼ੱਲ਼ਾ","lal'laa",R.drawable.lallaa2,R.raw.zero,R.drawable.lallaa2_72));

        AlphabetsAdapter adapter = new AlphabetsAdapter(getActivity(), alphabets);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Alphabets alphabetClicked = alphabets.get(i);
                Intent intent = new Intent(getActivity(),DrawingAlphabet.class);
                intent.putExtra("alphabet", (Serializable) alphabetClicked);
                startActivity(intent);
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
