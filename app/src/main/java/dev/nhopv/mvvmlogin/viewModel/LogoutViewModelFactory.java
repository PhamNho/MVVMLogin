package dev.nhopv.mvvmlogin.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dev.nhopv.mvvmlogin.Interface.LogoutCallBacks;

public class LogoutViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LogoutCallBacks logoutCallBacks;

    public LogoutViewModelFactory(LogoutCallBacks logoutCallBacks) {
        this.logoutCallBacks = logoutCallBacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LogoutViewModel(logoutCallBacks);
    }
}
