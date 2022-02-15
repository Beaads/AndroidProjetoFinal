package com.beatriz.projetofinalandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.beatriz.projetofinalandroid.R;
import com.beatriz.projetofinalandroid.Service.CheckListService;
import com.beatriz.projetofinalandroid.model.CheckList;
import com.beatriz.projetofinalandroid.retrofit.RestClient;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RestClient restClient = new RestClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CheckList");
        BotaoSalvaChecklist();

        RadioButton OK;
        RadioButton NOK;

            OK = findViewById(R.id.TracaoOK);
            NOK = findViewById(R.id.TracaoNOK);

    }

    //criar checklist salvar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CheckList checkListCriado = criaCheckList();
        Observable<CheckList> observable = (Observable<CheckList>) restClient.getRetrofit().create(CheckListService.class).adiciona(checkListCriado);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckList>() {
                    @Override
                    public void onCompleted() {
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Erro ao criar " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(CheckList checklist) {
                        Toast.makeText(MainActivity.this, "Sucesso ao criar", Toast.LENGTH_SHORT).show();
                    }
                });
        return super.onOptionsItemSelected(item);
    }


    private void BotaoSalvaChecklist() {
        Button botaoSalva = findViewById(R.id.btnSalvar);
        botaoSalva.setOnClickListener(view -> vaiParaListaCheckList());
    }

    private void vaiParaListaCheckList() {
        Intent iniciaListaCheckList =
                new Intent(MainActivity.this,
                        ListaCheckList.class);
        startActivityIfNeeded(iniciaListaCheckList, 2);
    }


    @NonNull
    private CheckList criaCheckList() {
        EditText data = findViewById(R.id.data);
        EditText hora = findViewById(R.id.hora);
        EditText placa = findViewById(R.id.placa);
        RadioGroup saidaRetorno = findViewById(R.id.saidaretorno);
        EditText motorista = findViewById(R.id.motorista);
        EditText kmVeiculo = findViewById(R.id.kmveiculo);

        return new CheckList(data.getText().toString(),
                hora.getText().toString(), placa.getText().toString(),
                saidaRetorno.callOnClick(), motorista.getText().toString(),
                kmVeiculo.getText().toString());
    }
}
