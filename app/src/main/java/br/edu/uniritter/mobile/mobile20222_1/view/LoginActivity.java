package br.edu.uniritter.mobile.mobile20222_1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import br.edu.uniritter.mobile.mobile20222_1.R;
import br.edu.uniritter.mobile.mobile20222_1.model.User;
import br.edu.uniritter.mobile.mobile20222_1.presenter.LoginPresenter;
import br.edu.uniritter.mobile.mobile20222_1.presenter.LoginPresenterContract;
import br.edu.uniritter.mobile.mobile20222_1.repository.UserRepository;

public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {

    private LoginPresenterContract.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "onCreate: antes do getInstance" );
        UserRepository.getInstance(this, null);
        Log.e("TAG", "onCreate: depois do getInstance "+UserRepository.getInstance(this, null).getUsers().size());

        setContentView(R.layout.activity_login);

        this.presenter = new LoginPresenter(this);
        this.presenter.loadLoginSharedPreferences();


        findViewById(R.id.buttonLogin).setOnClickListener(
                view -> presenter.checkLogin(
                        ((TextView) findViewById(R.id.edLogin)).getText().toString(),
                        ((TextView) findViewById(R.id.edPassword)).getText().toString()
                )
        );
    }

    @Override
    public void message(String msg) {
        Snackbar.make(this,findViewById(R.id.edPassword),
                       msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }


}