package com.hse.dalexiv.testtask.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hse.dalexiv.testtask.MVP;
import com.hse.dalexiv.testtask.R;

public class InputFragment extends Fragment implements MVP.RequiredInputViewOps {
    private static final String INPUT_TEXT = "text";
    private static final String IS_BUTTON_LOCKED = "lock";

    TextInputLayout mTextInputLayout;
    EditText mEditText;
    MVP.ProvidedInputViewOps mActivityCallback;
    Button mButton;

    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        mTextInputLayout = (TextInputLayout) view.findViewById(R.id.urlInput);
        mEditText = (EditText) view.findViewById(R.id.editTextUrl);
        mButton = (Button) view.findViewById(R.id.buttonGo);
        mButton.setEnabled(false);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityCallback.onClick(mEditText.getText().toString());
            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mActivityCallback.onUrlChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivityCallback = (MVP.ProvidedInputViewOps) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void showURLTextHint(String error) {
        mTextInputLayout.setError(error);
    }

    @Override
    public void setButtonState(boolean isEnabled) {
        mButton.setEnabled(isEnabled);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INPUT_TEXT, mEditText.getText().toString());
        outState.putBoolean(IS_BUTTON_LOCKED, mEditText.isEnabled());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mEditText.setText(savedInstanceState.getString(INPUT_TEXT));
            mButton.setEnabled(savedInstanceState.getBoolean(IS_BUTTON_LOCKED));
        }
    }
}
