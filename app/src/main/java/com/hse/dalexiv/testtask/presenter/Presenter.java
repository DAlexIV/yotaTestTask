package com.hse.dalexiv.testtask.presenter;

import android.os.AsyncTask;

import com.hse.dalexiv.testtask.MVP;
import com.hse.dalexiv.testtask.model.Model;
import com.hse.dalexiv.testtask.utils.UtilMethods;
import com.hse.dalexiv.testtask.view.MainActivity;

import java.lang.ref.WeakReference;

/**
 * Created by dalexiv on 17.02.16.
 */
public class Presenter implements MVP.ProvidedPresenterOps, MVP.RequiredPresenterOps {
    // Common presenter implementation
    private static volatile Presenter instance;
    protected WeakReference<MainActivity> view;
    protected Model model;

    private Presenter(WeakReference<MainActivity> view) {
        this.view = view;
        this.model = Model.getInstance(this);
    }

    public static Presenter getInstance(MainActivity view) {
        Presenter localInstance = instance;
        if (localInstance == null) {
            synchronized (Model.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Presenter(new WeakReference<>(view));
                }
            }
        } else {
            localInstance.view = new WeakReference<>(view);
        }
        return localInstance;
    }


    public MainActivity getView() {
        return view.get();
    }

    // End of presenter implementation

    AsyncTask<String, Void, Void> urlDownloader;

    @Override
    public void onUrlChanged(String text) {
        boolean generalValidation = UtilMethods.validateUrl(text);
        if (generalValidation)
            getView().showURLTextHint("");
        else {
            if (UtilMethods.validateHttpUrl(text))
                getView().showURLTextHint("Please, enter correct URL");
            else
                getView().showURLTextHint("URL should start from http");
        }
        getView().setButtonState(generalValidation);
    }

    @Override
    public void onClick(String url) {
        urlDownloader = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                model.getWebPage(params[0]);
                return null;
            }
        };
        urlDownloader.execute(url);
        getView().setButtonState(false);
    }

    @Override
    public void displayHtml(final String text) {
        getView().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getView().displayHtml(text);
                getView().setButtonState(true);
            }
        });

    }

    @Override
    public void displayError(final String errorText) {
        getView().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getView().displayError(errorText);
                getView().setButtonState(true);
            }
        });


    }
}
