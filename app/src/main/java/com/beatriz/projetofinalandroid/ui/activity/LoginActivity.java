package com.beatriz.projetofinalandroid.ui.activity;

import static com.beatriz.projetofinalandroid.ui.activity.ConstantesActivity.CHAVE_CHECKLIST;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.beatriz.projetofinalandroid.R;
import com.beatriz.projetofinalandroid.model.CheckList;
import com.beatriz.projetofinalandroid.model.Usuario;
import com.beatriz.projetofinalandroid.retrofit.RestClient;
import com.beatriz.projetofinalandroid.service.CheckListService;
import com.beatriz.projetofinalandroid.service.UsuarioService;

import java.util.List;

import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        configuraBotaoEntrarNaLista();
        setTitle("Prolog App");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_tela_login);
        configuraBotaoEntrarNaLista();
    }

    public void getUsuarios() {
        Observable<List<Usuario>> observable = RestClient.getRetrofit().create
                (UsuarioService.class).getUsuarios();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Usuario>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "Erro: " +
                                e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Usuario> checkLists) {
                    }
                });
    }

    private void configuraBotaoEntrarNaLista() {
        Button btnEntrar = findViewById(R.id.btnEntrar);
        EditText login = findViewById(R.id.Login);
        EditText senha = findViewById(R.id.Senha);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (login.getText().toString().length() == 0 && senha.getText().toString().length() == 0) {
                    login.setError("*");
                    senha.setError("*");
                    Toast.makeText(LoginActivity.this, "Preencha as informações",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (login.getText().toString().equals("prolog") && senha.getText().toString().equals("1234")) {
                    vaiParaListaCheckList();
                } else {
                    login.setError("*");
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
