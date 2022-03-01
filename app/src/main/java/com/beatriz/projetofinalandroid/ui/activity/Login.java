package com.beatriz.projetofinalandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.beatriz.projetofinalandroid.R;

public class Login extends AppCompatActivity {

    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        BotaoEntraNaLista();
        setTitle("Prolog App");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.tela_login);
        BotaoEntraNaLista();
    }

    private void BotaoEntraNaLista() {
        btnEntrar = findViewById(R.id.btnEntrar);
        EditText Login = findViewById(R.id.Login);
        EditText Senha = findViewById(R.id.Senha);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText selLogin = findViewById(R.id.Login);
                EditText selSenha = findViewById(R.id.Senha);

                if (Login.getText().toString().length() == 0 && Senha.getText().toString().length() == 0) {
                    selLogin.setError("*");
                    selSenha.setError("*");
                    Toast.makeText(Login.this, "Preencha as informações",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Login.getText().toString().equals("prolog") && Senha.getText().toString().equals("1234")) {
                    vaiParaListaCheckList();
                } else {
                    selLogin.setError("*");
                    selSenha.setError("*");
                    Toast.makeText(Login.this, "Login e/ou senha incorretos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void vaiParaListaCheckList() {
        Intent iniciaListaCheckList =
                new Intent(Login.this,
                        ListaCheckList.class);
        startActivityIfNeeded(iniciaListaCheckList, 2);
    }
}
