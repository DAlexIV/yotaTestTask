package com.hse.dalexiv.testtask.view;

/**
 * Created by dalexiv on 19.02.16.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hse.dalexiv.testtask.MVP;
import com.hse.dalexiv.testtask.R;

/**
 * Fragment for displaying text or some errors
 */
public class OutputFragment extends Fragment implements MVP.RequiredOutputViewOps {
    private static final String WEBPAGE_TEXT = "webtext";
    private static final String COLOR = "color";

    TextView mTextView;

    // Required empty public constructor
    public OutputFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_output, container, false);

        // Initializing text view
        mTextView = (TextView) view.findViewById(R.id.textView);
        return view;
    }

    @Override
    public void displayError(String errorText) {
        mTextView.setText(errorText);
        mTextView.setTextColor(Color.RED);

    }


    @Override
    public void displayHtml(String text) {
        mTextView.setText(text);
        mTextView.setTextColor(Color.GRAY);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Saving text and text color
            mTextView.setText(savedInstanceState.getString(WEBPAGE_TEXT));
            mTextView.setTextColor(savedInstanceState.getInt(COLOR));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Restoring text and its' color
        outState.putString(WEBPAGE_TEXT, mTextView.getText().toString());
        outState.putInt(COLOR, mTextView.getCurrentTextColor());

    }
}

