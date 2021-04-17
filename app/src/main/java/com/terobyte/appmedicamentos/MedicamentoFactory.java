package com.terobyte.appmedicamentos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.terobyte.appmedicamentos.models.MedicamentosViewModel;

public class MedicamentoFactory  extends ViewModelProvider.NewInstanceFactory{
    @NonNull
    private final Application application;

    public MedicamentoFactory(@NonNull Application application) {
        this.application = application;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == MedicamentosViewModel.class){
            return (T) new MedicamentosViewModel(application);
        }
        return null;

    }

}
