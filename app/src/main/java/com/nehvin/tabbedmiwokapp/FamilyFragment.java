package com.nehvin.tabbedmiwokapp;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class FamilyFragment extends Fragment {

    MediaPlayer mpPlayer;
    AudioManager mAudioManager;

    MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener(){
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    {
                        mpPlayer.pause();
                        mpPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
                    {
                        releaseMediaPlayer();
                    } else if( focusChange == AudioManager.AUDIOFOCUS_GAIN)
                    {
                        mpPlayer.start();
                    }
                }
            };

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_family, container, false);

        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word(getString(R.string.father),getString(R.string.epe),R.drawable.family_father, R.raw.family_father));
        words.add(new Word(getString(R.string.mother),getString(R.string.eta),R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(getString(R.string.son),getString(R.string.angsi),R.drawable.family_son, R.raw.family_son));
        words.add(new Word(getString(R.string.daughter),getString(R.string.tune),R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(getString(R.string.older_brother),getString(R.string.taachi),R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(getString(R.string.younger_brother),getString(R.string.chalitti),R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(getString(R.string.older_sister),getString(R.string.tete),R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(getString(R.string.younger_sister),getString(R.string.kolliti),R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(getString(R.string.grandmother),getString(R.string.ama),R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(getString(R.string.grand_father),getString(R.string.paapa),R.drawable.family_grandfather, R.raw.family_grandfather));

        // Verify the contents of the array by printing out each array element to the logs
        for (int i = 0; i<words.size();i++) {
            Log.v("FamilyActivity", "Family at index "+ i +": " + words.get(i).getMiwokTranslation() + " : AND : " +words.get(i).getDefaultTranslation());
        }

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView)rootView.findViewById(R.id.activity_family);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordAt = words.get(position);
                Log.v("NumbersActivity", "Numbers at index "+ position +": " + words.get(position).getMiwokTranslation()
                        + " : AND : " +words.get(position).getDefaultTranslation() + " : AND : "
                        + words.get(position).getImageResourceID() + " : AND : "
                        + words.get(position).getmAudioResourceID());
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (wordAt.hasAudioFile() && result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mpPlayer = MediaPlayer.create(getActivity(), words.get(position).getmAudioResourceID());
                    mpPlayer.start();
                    mpPlayer.setOnCompletionListener(mCompletion);
                }
            }
        });



        return rootView;

//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mpPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mpPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mpPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
