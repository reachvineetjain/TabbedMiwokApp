package com.nehvin.tabbedmiwokapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ColorsFragment())
                .commit();
    }

//    MediaPlayer mpPlayer;
//
//    AudioManager mAudioManger;
//
//    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
//            new AudioManager.OnAudioFocusChangeListener(){
//            @Override
//            public void onAudioFocusChange(int focusChange)
//            {
//                if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
//                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
//                {
//                    mpPlayer.pause();
//                    mpPlayer.seekTo(0);
//                }
//                else
//                {
//                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS)
//                    {
//                        releaseMediaPlayer();
//                    }
//                    else
//                    {
//                        if(focusChange == AudioManager.AUDIOFOCUS_GAIN)
//                        {
//                            mpPlayer.start();
//                        }
//                    }
//                }
//            }
//        };
//
//    MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener(){
//        @Override
//        public void onCompletion(MediaPlayer mp){
//            releaseMediaPlayer();
//        }
//    };

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_colors);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mAudioManger = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//
//        final ArrayList<Word> words = new ArrayList<Word>();
//        words.add(new Word(getString(R.string.red),getString(R.string.wetetti),R.drawable.color_red,
//                R.raw.color_red));
//        words.add(new Word(getString(R.string.green),getString(R.string.chokokki),R.drawable.color_green,
//                R.raw.color_green));
//        words.add(new Word(getString(R.string.brown),getString(R.string.takaakki),R.drawable.color_brown,
//                R.raw.color_brown));
//        words.add(new Word(getString(R.string.gray),getString(R.string.topoppi),R.drawable.color_gray,
//                R.raw.color_gray));
//        words.add(new Word(getString(R.string.black),getString(R.string.kululli),R.drawable.color_black,
//                R.raw.color_black));
//        words.add(new Word(getString(R.string.white),getString(R.string.kelelli),R.drawable.color_white,
//                R.raw.color_white));
//        words.add(new Word(getString(R.string.dusty_yellow),getString(R.string.topiise),
//                R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
//        words.add(new Word(getString(R.string.mustard_yellow),getString(R.string.chiwiite),
//                R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
//
//        // Verify the contents of the array by printing out each array element to the logs
//        for (int i = 0; i<words.size();i++) {
//            Log.v("ColorsActivity", "Color at index "+ i +": " + words.get(i).getMiwokTranslation() + " : AND : " +words.get(i).getDefaultTranslation());
//        }
//
//        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
//        // adapter knows how to create layouts for each item in the list, using the
//        // simple_list_item_1.xml layout resource defined in the Android framework.
//        // This list item layout contains a single {@link TextView}, which the adapter will set to
//        // display a single word.
//        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);
//
//        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
//        // There should be a {@link ListView} with the view ID called list, which is declared in the
//        // activity_numbers.xml layout file.
//        ListView listView = (ListView)findViewById(R.id.activity_colors);
//
//        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
//        // {@link ListView} will display list items for each word in the list of words.
//        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
//        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
//        listView.setAdapter(itemsAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Word wordAt = words.get(position);
//                Log.v("NumbersActivity", "Numbers at index "+ position +": " + words.get(position).getMiwokTranslation()
//                        + " : AND : " +words.get(position).getDefaultTranslation() + " : AND : "
//                        + words.get(position).getImageResourceID() + " : AND : "
//                        + words.get(position).getmAudioResourceID());
//                releaseMediaPlayer();
//                int result = mAudioManger.requestAudioFocus(mOnAudioFocusChangeListener,
//                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//                if (wordAt.hasAudioFile() && result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    mpPlayer = MediaPlayer.create(ColorsActivity.this, words.get(position).getmAudioResourceID());
//                    mpPlayer.start();
//                    mpPlayer.setOnCompletionListener(mCompletion);
//                }
//            }
//        });
//    }

//    /**
//     * Clean up the media player by releasing its resources.
//     */
//    private void releaseMediaPlayer() {
//        // If the media player is not null, then it may be currently playing a sound.
//        if (mpPlayer != null) {
//            // Regardless of the current state of the media player, release its resources
//            // because we no longer need it.
//            mpPlayer.release();
//
//            // Set the media player back to null. For our code, we've decided that
//            // setting the media player to null is an easy way to tell that the media player
//            // is not configured to play an audio file at the moment.
//            mpPlayer = null;
//            mAudioManger.abandonAudioFocus(mOnAudioFocusChangeListener);
//        }
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        releaseMediaPlayer();
//        Log.v("NumbersActivity.class","Media player released");
////        Toast.makeText(this, "Media Player Released", Toast.LENGTH_SHORT);
//    }
}