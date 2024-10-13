package com.ulpsoft.tp3.ui.registro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulpsoft.tp3.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding;
    Intent intent = getIntent();
    private ActivityResultLauncher<Intent> arl;
    RegistroViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        String usuario = intent.getStringExtra("usuario");

        if (usuario != null) {
            vm.accion(usuario);
        }

        vm.getUriMutable().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivAvatar.setImageURI(uri);
            }
        });

        vm.getMUsuario().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etMail.setText(string);
            }
        });
        vm.getmPass().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etPassword.setText(string);
            }
        });
        vm.getmApellido().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etApellido.setText(string);
            }
        });
        vm.getmNombre().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                binding.etNombre.setText(string);
            }
        });
        vm.getmDni().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long dni) {
                if (dni != null) {
                    binding.etDni.setText(dni.toString());
                } else {
                    binding.etDni.setText("");
                }
            }
        });
        vm.getUriMutable().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivAvatar.setImageURI(uri);
            }
        });

        abrirGaleria();

        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = binding.etMail.getText().toString();
                String password = binding.etPassword.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                Long dni = null;
                try {
                    dni = Long.parseLong(binding.etDni.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(RegistroActivity.this, "El DNI debe ser numérico", Toast.LENGTH_SHORT).show();

                }

                if (!usuario.isEmpty() && !password.isEmpty() && !apellido.isEmpty() && !nombre.isEmpty() && dni != null){
                    vm.registroUsuario(usuario, password, apellido, nombre, dni);
                } else {
                    Toast.makeText(RegistroActivity.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void abrirGaleria(){
        intent=new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        arl=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                vm.recibirFoto(result);
            }
        });
        binding.btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");  // Asegurarse de que solo muestre imágenes
                arl.launch(intent);  // Lanza el intent para abrir la galería
            }
        });
    }



}