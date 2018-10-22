package br.com.bluedot.redevalor.repository;

import br.com.bluedot.redevalor.data.ResponseError;

/**
 * Created by gadal on 26/10/17.
 */

public interface RepositoryCallback<T> {
    void onSuccess(T response);
    void onError(ResponseError error);
}
