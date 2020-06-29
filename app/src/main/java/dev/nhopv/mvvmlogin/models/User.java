package dev.nhopv.mvvmlogin.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;

import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class User extends BaseObservable {

    private String uEmail, uPassword;

    public User() {
    }

    public User(String uEmail, String uPassword) {
        this.uEmail = uEmail;
        this.uPassword = uPassword;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public int isValidData(Context context) {
        // password > 6 ký tự gồm 1 ký tự đặc biệt, và ít nhất 1 chữ số.
        final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$";

        if (TextUtils.isEmpty(getuEmail())) {
            return 0;
        } else if (TextUtils.isEmpty(getuPassword())) {
            return 1;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getuEmail()).matches()) {
            return 2;
        } else if (!Pattern.compile(PASSWORD_PATTERN).matcher(getuPassword()).matches()) {
            return 3;
        } else {
            return -1;
        }
    }
}
