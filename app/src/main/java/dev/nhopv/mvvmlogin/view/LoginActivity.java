package dev.nhopv.mvvmlogin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import dev.nhopv.mvvmlogin.Interface.LoginResultCallbacks;
import dev.nhopv.mvvmlogin.R;
import dev.nhopv.mvvmlogin.databinding.ActivityLoginBinding;
import dev.nhopv.mvvmlogin.viewModel.LoginViewModel;
import dev.nhopv.mvvmlogin.viewModel.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity implements LoginResultCallbacks, Observer {
    private Toast toast = null;
    ActivityLoginBinding activityLoginBinding;
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this, getApplicationContext());
        activityLoginBinding.setViewModel(ViewModelProviders.of(
                this,
                new LoginViewModelFactory(this, getApplicationContext())
                ).get(LoginViewModel.class)
        );
    }
    @Override
    public void onSuccess(String mes) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, mes, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void onError(String mes) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, mes, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}