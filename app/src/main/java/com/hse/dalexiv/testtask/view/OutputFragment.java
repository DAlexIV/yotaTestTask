package com.hse.dalexiv.testtask.view;

/**
 * Created by dalexiv on 19.02.16.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hse.dalexiv.testtask.MVP;
import com.hse.dalexiv.testtask.R;



public class OutputFragment extends Fragment implements MVP.RequiredOutputViewOps {
    private static final String WEBPAGE_TEXT = "webtext";
    private static final String COLOR = "color";
    TextView mTextView;
    public OutputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(WEBPAGE_TEXT));
            mTextView.setTextColor(savedInstanceState.getInt(COLOR));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_output, container, false);
        mTextView = (TextView) view.findViewById(R.id.textView);
        return view;
    }


    @Override
    public void displayError(String errorText) {
        mTextView.setText(errorText);
        mTextView.setTextColor(Color.RED);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(WEBPAGE_TEXT, mTextView.getText().toString());
        outState.putInt(COLOR, mTextView.getCurrentTextColor());

    }

    @Override
    public void displayHtml(String text) {
        mTextView.setText(text);
        mTextView.setTextColor(Color.GRAY);
    }
}

