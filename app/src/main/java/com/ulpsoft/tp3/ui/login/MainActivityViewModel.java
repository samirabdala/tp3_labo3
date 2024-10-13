package com.ulpsoft.tp3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ulpsoft.tp3.model.Usuario;
import com.ulpsoft.tp3.request.ApiClient;
import com.ulpsoft.tp3.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {

    private final Context contexto;
    public ApiClient ac;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        contexto = application.getApplicationContext();
        ac = new ApiClient();
    }

    public void inicioSesion(String pass, String usuario) {
        Usuario usu = null;

        if (pass == null || usuario == null || pass.isEmpty() || usuario.isEmpty()) {
            Toast.makeText(contexto, "Debe completar ambos campos", Toast.LENGTH_SHORT).show();
            return;
        } else {
            usu = ac.login(contexto, usuario, pass);
        }

        if (usu != null) {

            Log.d("MainActivityViewModel", "Usuario encontrado: " + usu.getMail());

            Intent intent = new Intent(contexto, RegistroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario", usu.getMail());
            contexto.startActivity(intent);
        } else {
            Toast.makeText(contexto, "Usuario no registrado", Toast.LENGTH_SHORT).show();
        }
    }


    public void registro(){
        Intent intent = new Intent(contexto, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(intent);
    }


}
