package br.com.bluedot.redevalor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.com.bluedot.redevalor.R;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment {

    @BindView(R.id.spFunction)
    Spinner mSpinner;

    public static final String TAG = RegisterFragment.class.getSimpleName();

    public static RegisterFragment newInstance() {

        return new RegisterFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.function_array));
        mSpinner.setAdapter(spinnerCountShoesArrayAdapter);
    }

    @OnClick(R.id.btLogin)
    public void onClickLogin() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
