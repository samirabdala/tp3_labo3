package com.ulpsoft.tp3.ui.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ulpsoft.tp3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        permisos();

        binding.btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = binding.etPassword.getText().toString();
                String usu = binding.etUsuario.getText().toString();
                vm.inicioSesion(pass,usu);
            }
        });

        binding.btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.registro();
            }
        });


    }

    private void permisos() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }
    }


}