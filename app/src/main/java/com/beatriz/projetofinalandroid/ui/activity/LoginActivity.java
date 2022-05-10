package com.beatriz.projetofinalandroid.ui.activity;

import static com.beatriz.projetofinalandroid.ui.activity.ConstantesActivity.CHAVE_CHECKLIST;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.beatriz.projetofinalandroid.R;

public class LoginActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        configuraBotaoEntrarNaLista();
        setTitle("Prolog App");
        context = this;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_tela_login);
        configuraBotaoEntrarNaLista();
    }

    private void configuraBotaoEntrarNaLista() {
        EditText usuario = findViewById(R.id.Login);
        EditText senha = findViewById(R.id.Senha);

        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usuario.getText().toString().length() == 0 && senha.getText().toString().length() == 0) {
                    usuario.setError("*");
                    senha.setError("*");
                    Toast.makeText(LoginActivity.this, "Preencha as informações",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (usuario.getText().toString().equals("prolog") && senha.getText().toString().equals("1234")) {
                    vaiParaListaCheckList();
                } else {
                    usuario.setError("*");
                    senha.setError("*");
                    Toast.makeText(LoginActivity.this, "Login e/ou senha incorretos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void vaiParaListaCheckList() {
        Intent iniciaListaCheckList =
                new Intent(LoginActivity.this,
                        ListaCheckListActivity.class);
        startActivityIfNeeded(iniciaListaCheckList, CHAVE_CHECKLIST);
    }
}
