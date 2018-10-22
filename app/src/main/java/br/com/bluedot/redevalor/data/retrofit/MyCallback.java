package br.com.bluedot.redevalor.data.retrofit;

import br.com.bluedot.redevalor.data.ResponseError;
import br.com.bluedot.redevalor.repository.RepositoryCallback;
import br.com.bluedot.redevalor.ui.dialog.MyDialog;
import br.com.bluedot.redevalor.utils.AuthPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gadal on 30/10/17.
 */

public abstract class MyCallback<T> implements Callback<T>, RepositoryCallback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
            return;
        }

        ResponseError responseError = ResponseError.getExceptionError();
        if (response.code() == 401) {
            responseError = ResponseError.getResponseError(response.errorBody(), response.code());

            if (responseError == null || responseError.getCode() == null) {
                if (AuthPreferences.isLogged()) {
                    MyDialog.showLostSessionDialog();
                    return;
                }

                responseError = ResponseError.getExceptionError();
            }

        } else if (response.code() == 403) {
//            MyDialog.showUpdateVersionDialog();
            return;

        } else if (response.code() == 422) {
            responseError = ResponseError.getResponseError(response.errorBody(), response.code());

            if (responseError.getCode() != null && responseError.getCode().equals(ResponseError.SYSTEM_MAINTENANCE)) {
                MyDialog.showMaintenanceDialog(responseError.getText());
                return;
            }
        }

        onError(responseError);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(ResponseError.getNetworkError());
    }
}
