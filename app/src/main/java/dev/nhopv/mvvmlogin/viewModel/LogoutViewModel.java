package dev.nhopv.mvvmlogin.viewModel;

import android.content.SharedPreferences;
import android.view.View;

import androidx.lifecycle.ViewModel;

import dev.nhopv.mvvmlogin.Interface.LogoutCallBacks;

import static android.content.Context.MODE_PRIVATE;

public class LogoutViewModel extends ViewModel {
    private LogoutCallBacks logoutCallBacks;

    public LogoutViewModel(LogoutCallBacks logoutCallBacks) {
        this.logoutCallBacks = logoutCallBacks;
    }

    public void onLogoutClicked(View view) {
        logoutCallBacks.onLogout("Đăng xuất thành công");
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
