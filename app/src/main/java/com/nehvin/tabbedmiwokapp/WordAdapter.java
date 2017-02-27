package com.nehvin.tabbedmiwokapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vineet K Jain on 12/30/2016.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int bgndColor;

    public WordAdapter (Activity context, ArrayList<Word> wordList, int bgColor)
    {
        super(context,0, wordList);
        bgndColor = bgColor;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentNumber = getItem(position);
        assert currentNumber != null;

        ImageView imgView = (ImageView) listItemView.findViewById(R.id.img_source);
        if(currentNumber.hasImage())
        {
            imgView.setVisibility(View.VISIBLE);
            imgView.setImageResource(currentNumber.getImageResourceID());
        }
        else
        {
            imgView.setVisibility(View.GONE);
        }

        TextView miTextView = (TextView) listItemView.findViewById(R.id.miwok);
        miTextView.setText(currentNumber.getMiwokTranslation());
//        miTextView.setBackgroundColor(bgndColor);

        TextView enTextView = (TextView) listItemView.findViewById(R.id.eng);
        enTextView.setText(currentNumber.getDefaultTranslation());
//        enTextView.setBackgroundColor(bgndColor);

        return listItemView;
//        return super.getView(position, convertView, parent);
    }
}