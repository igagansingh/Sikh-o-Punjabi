package com.gagandeep.developer.sikh_o;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrawingAlphabet extends AppCompatActivity {
    Alphabets alphabet;
    ImageView imageView;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private Button listen, draw;
    private TextView textView, textView2;
    private DrawingView customCanvas;

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        if (mediaPlayer != null)
                            mediaPlayer.release();
                        else
                            mediaPlayer = null;
                        audioManager.abandonAudioFocus(audioFocusChangeListener);
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawing_alphabet);

        Toast.makeText(this, "TRY DRAWING ON THE LETTER", Toast.LENGTH_LONG).show();

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView =(TextView) findViewById(R.id.textView);
        textView.setText("");
        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText("");
        alphabet = (Alphabets) getIntent().getSerializableExtra("alphabet");
        imageView.setImageResource(alphabet.getImage70ResourceId());

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        customCanvas = (DrawingView) findViewById(R.id.signature_canvas);

        listen =(Button) findViewById(R.id.button);
        draw = (Button) findViewById(R.id.button2);

        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.clearAnimation();
                {
                    if (mediaPlayer != null)
                        mediaPlayer.release();
                    else
                        mediaPlayer = null;
                }
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(DrawingAlphabet.this,alphabet.getSongResourceId());
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


        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              customCanvas.clear();
            if(draw.getText().equals("CHECK")) {
                int c = 0, d = 0, e = 0, f = 0;
                Bitmap cDraw = customCanvas.getBitmap();
                Bitmap image = imageView.getDrawingCache();
                int pixels[] = new int[image.getHeight() * image.getWidth()];
                int pixels2[] = new int[customCanvas.getHeight() * customCanvas.getWidth()];
                image.getPixels(pixels, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
                cDraw.getPixels(pixels2, 0, cDraw.getWidth(), 0, 0, cDraw.getWidth(), cDraw.getHeight());
                for (int i = 0; i < pixels.length; i++) {
                    if (Color.red(pixels[i]) == 255 && Color.green(pixels[i]) == 255 && Color.blue(pixels[i]) == 255 && Color.alpha(pixels[i]) == 255) {
                        c++;
                        if (Color.red(pixels2[i]) == 255 && Color.alpha(pixels2[i]) != 0) {
                            f++;
                        }
                    } else {
                        d++;
                        if (Color.red(pixels2[i]) == 255) {
                            e++;
                        }
                    }
                }
                draw.setText("RETRY");
                int x=0,y=0;
                if((((float)e/(float)d)*10) >= 6.0f){
                    textView.setText(getResources().getText(R.string.success));
                    textView2.setText(getResources().getText(R.string.try_next));
                    textView.setTextColor(getResources().getColor(R.color.success));
                    textView2.setTextColor(getResources().getColor(R.color.success));
                    /*for (int i = 0; i < pixels2.length; i++) {
                        if (!(Color.red(pixels[i]) == 255 && Color.green(pixels[i]) == 255 && Color.blue(pixels[i]) == 255 && Color.alpha(pixels[i]) == 255)) {
                            if (Color.red(pixels2[i]) == 255) {
                                x++;
                                pixels2[i] = Color.GREEN;
                            }
                        }
                    }
                    for (int i = 0; i < pixels2.length; i++) {
                        if (!(Color.red(pixels[i]) == 255 && Color.green(pixels[i]) == 255 && Color.blue(pixels[i]) == 255 && Color.alpha(pixels[i]) == 255)) {
                            if (Color.green(pixels2[i]) == 255) {
                                y++;
                            }
                        }
                    }*/
                }
                else if((((float)e/(float)d)*10) >= 5.0f && (((float)e/(float)d)*10) < 6.0f){
                    textView.setText(getResources().getText(R.string.close));
                    textView2.setText(getResources().getText(R.string.try_again));
                    textView.setTextColor(getResources().getColor(R.color.fail));
                    textView2.setTextColor(getResources().getColor(R.color.fail));
                    blink();
                }
                else if((((float)e/(float)d)*10) >= 3.0f && (((float)e/(float)d)*10) < 5.0f){
                    textView.setText(getResources().getText(R.string.keep_trying));
                    textView2.setText(getResources().getText(R.string.try_again));
                    textView.setTextColor(getResources().getColor(R.color.fail));
                    textView2.setTextColor(getResources().getColor(R.color.fail));
                    blink();
                }
                else if((((float)e/(float)d)*10) == 0.0f){
                    textView.setText(getResources().getText(R.string.nothing));
                    textView2.setText(getResources().getText(R.string.try_again));
                    textView.setTextColor(getResources().getColor(R.color.hard_fail));
                    textView2.setTextColor(getResources().getColor(R.color.hard_fail));
                    blink();
                }
                else if((((float)e/(float)d)*10) > 0.0f && (((float)e/(float)d)*10) < 3.0f){
                    textView.setText(getResources().getText(R.string.fail));
                    textView2.setText(getResources().getText(R.string.try_again));
                    textView.setTextColor(getResources().getColor(R.color.fail));
                    textView2.setTextColor(getResources().getColor(R.color.fail));
                    blink();
                }
                /*cDraw.setPixels(pixels2, 0, cDraw.getWidth(), 0, 0, cDraw.getWidth(), cDraw.getHeight());*/
                Log.e("TAG", c + ":" + d + ":" + e + ":" + f +":" + ((float)e/(float)d) +":" + x+":" +y);
                Toast.makeText(DrawingAlphabet.this, (((float)e/(float)d)*100)+" Accuracy ", Toast.LENGTH_LONG).show();
            }
            else{
                recreate();
            }
            }
        });
    }
    private  void blink(){
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        draw.startAnimation(animation);
    }
    @Override
    public void onStop() {
        super.onStop();
        imageView.setDrawingCacheEnabled(false);
        if(mediaPlayer!=null)
            mediaPlayer.release();
        else
            mediaPlayer=null;
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
