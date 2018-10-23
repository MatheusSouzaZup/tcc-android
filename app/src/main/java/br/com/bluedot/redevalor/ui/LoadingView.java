package br.com.bluedot.redevalor.ui;

import br.com.bluedot.redevalor.data.ResponseError;

public interface LoadingView {
    void showLoading();
    void hideLoading(boolean success);
    void notifyError(ResponseError error);
    void showDialogUpdateApplication(ResponseError error);
}