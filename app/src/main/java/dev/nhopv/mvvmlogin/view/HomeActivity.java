package dev.nhopv.mvvmlogin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import dev.nhopv.mvvmlogin.Interface.LogoutCallBacks;
import dev.nhopv.mvvmlogin.R;
import dev.nhopv.mvvmlogin.databinding.ActivityHomeBinding;
import dev.nhopv.mvvmlogin.viewModel.LoginViewModel;
import dev.nhopv.mvvmlogin.viewModel.LoginViewModelFactory;
import dev.nhopv.mvvmlogin.viewModel.LogoutViewModel;
import dev.nhopv.mvvmlogin.viewModel.LogoutViewModelFactory;

public class HomeActivity extends AppCompatActivity implements LogoutCallBacks {
    private Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setViewModel(ViewModelProviders.of(
                this,
                new LogoutViewModelFactory(this)
                ).get(LogoutViewModel.class)
        );
    }

    @Override
    public void onLogout(String mes) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, mes, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        finish();
    }
}