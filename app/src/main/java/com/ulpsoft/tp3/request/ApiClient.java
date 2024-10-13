package com.ulpsoft.tp3.request;

import android.content.Context;
import android.util.Log;

import com.ulpsoft.tp3.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ApiClient {
    private static final String FILE_NAME = "usuario.dat";

    public static boolean guardarUsuario(Context context, Usuario usuario) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(usuario);
            oos.flush();
            Log.d("ApiClient", "Usuario guardado: " + usuario.getMail());
            return true;

        } catch (IOException e) {
            Log.e("ApiClient", "Error al guardar el usuario", e);
            return false;
        }
    }

    // Metodo para recuperar el usuario guardado desde un archivo
    public static Usuario registrado(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);  // Ubicación del archivo de usuario
        if (!file.exists()) {
            Log.d("ApiClient", "No se encontró un archivo de usuario.");
            return null;  // Retorna null si no existe el archivo
        }

        try (
             FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            Usuario usuario = (Usuario) ois.readObject();  // Deserializamos el objeto Usuario
            Log.d("ApiClient", "Usuario recuperado: " + usuario.getMail());
            return usuario;

        } catch (IOException | ClassNotFoundException e) {
            Log.e("ApiClient", "Error al recuperar el usuario", e);
            return null;
        }
    }

    public static Usuario login(Context context, String mail, String password) {
        Usuario usuario = registrado(context);
        if (usuario != null) {
            if (mail.equals(usuario.getMail()) && password.equals(usuario.getPassword())) {
                Log.d("ApiClient", "Login exitoso: " + usuario.getMail());
                return usuario;
            } else {
                Log.d("ApiClient", "Login fallido. Mail o contraseña incorrectos.");
            }
        } else {
            Log.d("ApiClient", "No se encontró ningún usuario registrado.");
        }
        return null;
    }
}
