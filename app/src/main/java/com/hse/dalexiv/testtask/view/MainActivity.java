package com.hse.dalexiv.testtask.view;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hse.dalexiv.testtask.MVP;
import com.hse.dalexiv.testtask.R;
import com.hse.dalexiv.testtask.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements MVP.RequiredInputViewOps,
        MVP.RequiredOutputViewOps, MVP.ProvidedInputViewOps {

    private Presenter mPresenter;
    private InputFragment mInputFragment;
    private OutputFragment mOutputFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mPresenter = Presenter.getInstance(this);

        if (savedInstanceState == null) {
            mInputFragment = new InputFragment();
            mOutputFragment = new OutputFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.topFragment, mInputFragment, "top");
            fragmentTransaction.add(R.id.downFragment, mOutputFragment, "down");
            fragmentTransaction.commit();
        }
        else {
            mInputFragment = (InputFragment) getSupportFragmentManager().findFragmentByTag("top");
            mOutputFragment = (OutputFragment) getSupportFragmentManager().findFragmentByTag("down");
        }
    }

    @Override
    public void onUrlChanged(String text) {
        mPresenter.onUrlChanged(text);
    }

    @Override
    public void onClick(String url) {
        mPresenter.onClick(url);
    }

    @Override
    public void showURLTextHint(String error) {
        mInputFragment.showURLTextHint(error);
    }

    @Override
    public void setButtonState(boolean isEnabled) {
        mInputFragment.setButtonState(isEnabled);
    }

    @Override
    public void displayError(String errorText) {
        mOutputFragment.displayError(errorText);
    }

    @Override
    public void displayHtml(String text) {
        mOutputFragment.displayHtml(text);
    }
}
