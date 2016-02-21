package com.hse.dalexiv.testtask;

/**
 * Created by dalexiv on 17.02.16.
 */

/**
 * Defines basic structure for the application
 */
public class MVP {
    public interface ProvidedInputViewOps {
        void onUrlChanged(String text);

        void onClick(String url);

        void disableButtonIfRunning();
    }

    public interface RequiredInputViewOps {
        void showURLTextHint(String error);

        void setButtonState(boolean isEnabled);
    }

    public interface RequiredOutputViewOps {
        void displayHtml(String text);

        void displayError(String errorText);
    }

    public interface ProvidedPresenterOps extends RequiredOutputViewOps {

    }

    public interface RequiredPresenterOps extends ProvidedInputViewOps {

    }

    public interface ProvidedModelOps {
        void getWebPage(String url);
    }
}
