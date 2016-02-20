package com.hse.dalexiv.testtask.model;

import com.hse.dalexiv.testtask.MVP;
import com.hse.dalexiv.testtask.presenter.Presenter;
import com.hse.dalexiv.testtask.utils.UtilMethods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.UnknownHostException;

/**
 * Created by dalexiv on 17.02.16.
 */

public class Model implements MVP.ProvidedModelOps {
    // Common model implementation
    private static volatile Model instance;
    protected WeakReference<Presenter> presenter;


    private Model(WeakReference<Presenter> presenter) {
        this.presenter = presenter;
    }

    public static Model getInstance(Presenter presenter) {
        Model localInstance = instance;
        if (localInstance == null) {
            synchronized (Model.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Model(new WeakReference<>(presenter));
                }
            }
        }
        return localInstance;
    }

    public Presenter getPresenter() {
        return presenter.get();
    }


    // End of common model implementation

    @Override
    public void getWebPage(String url) {
        try {
            String page = UtilMethods.downloadUrl(url);
            if (page.isEmpty())
                page = "Nothing was returned";
            getPresenter().displayHtml(page);
        }
        catch (FileNotFoundException ex) {
            getPresenter().displayError("Host exist, but the given url is not found");
        }
        catch(UnknownHostException ex) {
            getPresenter().displayError("Host doesn't exist");
        }
        catch (Exception ex) {
            getPresenter().displayError(ex.getClass().getName().toString() + ": " + ex.getMessage());
        }
    }
}
