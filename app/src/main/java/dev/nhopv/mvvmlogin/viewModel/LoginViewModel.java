package dev.nhopv.mvvmlogin.viewModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;

import androidx.lifecycle.ViewModel;

import dev.nhopv.mvvmlogin.Interface.LoginResultCallbacks;
import dev.nhopv.mvvmlogin.models.User;


public class LoginViewModel extends ViewModel {
    private User user;
    private LoginResultCallbacks loginResultCallbacks;
    private final static String SP_EMAIL = "email";
    private final static String SP_PASSWORD = "password";
    private boolean isReAc;
    private Context context;

    public LoginViewModel(LoginResultCallbacks loginResultCallbacks, Context context) {
        this.loginResultCallbacks = loginResultCallbacks;
        this.context = context;
        this.user = new User();
        user.setuEmail(getSpEmail());
        user.setuPassword(getSpPassword());
    }

    public TextWatcher getEmailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setuEmail(s.toString());
            }
        };
    }

    public TextWatcher getPasswordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setuPassword(s.toString());
            }
        };
    }

    public void onCheckedChange(CompoundButton button, boolean check) {
        isReAc = check;
    }

    public void onLoginClicked(View view) {
        int isErrorCode = user.isValidData(context);
        if (isErrorCode == 0) {
            loginResultCallbacks.onError("Bạn vui lòng nhập địa chỉ email");
        } else if (isErrorCode == 1) {
            loginResultCallbacks.onError("Bạn vui lòng nhập mật khẩu");
        } else if (isErrorCode == 2) {
            loginResultCallbacks.onError("Email không đúng định dạng");
        } else if (isErrorCode == 3) {
            loginResultCallbacks.onError("Mật khẩu > 6 ký tự gồm ít nhất 1 tự đặc biệt, 1 chữ số");
        } else {
            loginResultCallbacks.onSuccess("Đăng nhập thành công");
            SharedPreferences preferences = context.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if (isReAc) {
                editor.putString(SP_EMAIL, user.getuEmail());
                editor.putString(SP_PASSWORD, user.getuPassword());
            } else {
                editor.clear();
            }
            editor.apply();
        }
    }

    public String getSpEmail() {
        SharedPreferences preferences = context.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        return preferences.getString(SP_EMAIL, "");
    }

    public String getSpPassword() {
        SharedPreferences preferences = context.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        return preferences.getString(SP_PASSWORD, "");
    }
}
