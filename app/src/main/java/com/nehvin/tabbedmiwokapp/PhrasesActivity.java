package com.nehvin.tabbedmiwokapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PhrasesFragment())
                .commit();
    }


//    MediaPlayer mpPlayer;
//    AudioManager mAudioManager;
//    AudioManager.OnAudioFocusChangeListener mOnFocusChangeListener =
//            new AudioManager.OnAudioFocusChangeListener(){
//                @Override
//                public void onAudioFocusChange(int focusChange) {
//                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
//                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
//                    {
//                        mpPlayer.pause();
//                        mpPlayer.seekTo(0);
//                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
//                    {
//                        releaseMediaPlayer();
//                    } else if( focusChange == AudioManager.AUDIOFOCUS_GAIN)
//                    {
//                        mpPlayer.start();
//                    }
//                }
//            };
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
//        setContentView(R.layout.activity_phrases);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//
//        final ArrayList<Word> words = new ArrayList<Word>();
//        words.add(new Word(getString(R.string.where_are_you_going),getString(R.string.minto_wuksus),
//                R.raw.phrase_where_are_you_going));
//        words.add(new Word(getString(R.string.what_is_your_name),getString(R.string.tinnә_oyaase_nә),
//                R.raw.phrase_what_is_your_name));
//        words.add(new Word(getString(R.string.my_name_is),getString(R.string.oyaaset),
//                R.raw.phrase_my_name_is));
//        words.add(new Word(getString(R.string.how_are_you_feeling),getString(R.string.michәksәs),
//                R.raw.phrase_how_are_you_feeling));
//        words.add(new Word(getString(R.string.im_feeling_good),getString(R.string.kuchi_achit),
//                R.raw.phrase_im_feeling_good));
//        words.add(new Word(getString(R.string.are_you_coming),getString(R.string.eenes_aa),
//                R.raw.phrase_are_you_coming));
//        words.add(new Word(getString(R.string.yes_im_coming),getString(R.string.hәә_әәnәm),
//                R.raw.phrase_yes_im_coming));
//        words.add(new Word(getString(R.string.im_coming),getString(R.string.eenәm),
//                R.raw.phrase_im_coming));
//        words.add(new Word(getString(R.string.lets_go),getString(R.string.yoowutis),
//                R.raw.phrase_lets_go));
//        words.add(new Word(getString(R.string.come_here),getString(R.string.enni_nem),
//                R.raw.phrase_come_here));
//
//        // Verify the contents of the array by printing out each array element to the logs
//        for (int i = 0; i<words.size();i++) {
//            Log.v("PhraesActivity", "Phrases at index "+ i +": " + words.get(i).getMiwokTranslation() + " : AND : " +words.get(i).getDefaultTranslation());
//        }
//
//        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
//        // adapter knows how to create layouts for each item in the list, using the
//        // simple_list_item_1.xml layout resource defined in the Android framework.
//        // This list item layout contains a single {@link TextView}, which the adapter will set to
//        // display a single word.
//        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);
//
//        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
//        // There should be a {@link ListView} with the view ID called list, which is declared in the
//        // activity_numbers.xml layout file.
//        ListView listView = (ListView)findViewById(R.id.activity_phrases);
//
//        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
//        // {@link ListView} will display list items for each word in the list of words.
//        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
//        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
//        listView.setAdapter(itemsAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Word wordAt = words.get(position);
//                Log.v("NumbersActivity", "Numbers at index "+ position +": " + words.get(position).getMiwokTranslation()
//                        + " : AND : " +words.get(position).getDefaultTranslation() + " : AND : "
//                        + words.get(position).getImageResourceID() + " : AND : "
//                        + words.get(position).getmAudioResourceID());
//                int result = mAudioManager.requestAudioFocus(mOnFocusChangeListener,
//                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//                releaseMediaPlayer();
//                if (wordAt.hasAudioFile() && result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    mpPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(position).getmAudioResourceID());
//                    mpPlayer.start();
//                    mpPlayer.setOnCompletionListener(mCompletion);
//                }
//            }
//        });
//
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
//            mAudioManager.abandonAudioFocus(mOnFocusChangeListener);
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
