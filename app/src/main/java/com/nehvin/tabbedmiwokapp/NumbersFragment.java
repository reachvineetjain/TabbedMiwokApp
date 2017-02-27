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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    MediaPlayer mpPlayer;

    private AudioManager mAudioManager;

    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mpPlayer.pause();
                        mpPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        releaseMediaPlayer();
                    }  else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mpPlayer.start();
                    }
                }
            };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_numbers, container, false);

//        getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.one),getString(R.string.lutti),R.drawable.number_one,
                R.raw.number_one));
        words.add(new Word(getString(R.string.two),getString(R.string.otiiko),R.drawable.number_two,
                R.raw.number_two));
        words.add(new Word(getString(R.string.three),getString(R.string.tolookosu),R.drawable.number_three,
                R.raw.number_three));
        words.add(new Word(getString(R.string.four),getString(R.string.oyyisa),R.drawable.number_four,
                R.raw.number_four));
        words.add(new Word(getString(R.string.five),getString(R.string.massokka),R.drawable.number_five,
                R.raw.number_five));
        words.add(new Word(getString(R.string.six),getString(R.string.temmokka),R.drawable.number_six,
                R.raw.number_six));
        words.add(new Word(getString(R.string.seven),getString(R.string.kenekaku),R.drawable.number_seven,
                R.raw.number_seven));
        words.add(new Word(getString(R.string.eight),getString(R.string.kawinta),R.drawable.number_eight,
                R.raw.number_eight));
        words.add(new Word(getString(R.string.nine),getString(R.string.woe),R.drawable.number_nine,
                R.raw.number_nine));
        words.add(new Word(getString(R.string.ten),getString(R.string.naaacha),R.drawable.number_ten,
                R.raw.number_ten));

        // Verify the contents of the array by printing out each array element to the logs
        for (int i = 0; i<words.size();i++) {
            Log.v("NumbersActivity", "Numbers at index "+ i +": " + words.get(i).getMiwokTranslation()
                    + " : AND : " +words.get(i).getDefaultTranslation());
        }

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView)rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordAt = words.get(position);
                Log.v("NumbersActivity", "Numbers at index " + position + ": " + words.get(position).getMiwokTranslation()
                        + " : AND : " + words.get(position).getDefaultTranslation() + " : AND : "
                        + words.get(position).getImageResourceID() + " : AND : "
                        + words.get(position).getmAudioResourceID());
                releaseMediaPlayer();
                if (wordAt.hasAudioFile()) {
                    int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                    {
                        mpPlayer = MediaPlayer.create(getActivity(), wordAt.getmAudioResourceID());
                        mpPlayer.start();
                        mpPlayer.setOnCompletionListener(mCompletionListener);
                    }
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
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
