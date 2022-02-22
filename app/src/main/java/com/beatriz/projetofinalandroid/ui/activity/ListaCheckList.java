package com.beatriz.projetofinalandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.beatriz.projetofinalandroid.R;
import com.beatriz.projetofinalandroid.Service.CheckListService;
import com.beatriz.projetofinalandroid.model.CheckList;
import com.beatriz.projetofinalandroid.retrofit.RestClient;
import com.beatriz.projetofinalandroid.ui.recyclerview.adapter.Adapter;
import com.beatriz.projetofinalandroid.ui.recyclerview.adapter.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListaCheckList extends AppCompatActivity {

    RestClient restClient = new RestClient();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_checklist);
        setTitle("CheckLists");
        getTodosCheckList();
        botaoNovoChecklist();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.lista_checklist);
        getTodosCheckList();
        botaoNovoChecklist();
    }

    public void getTodosCheckList() {
        Observable<List<CheckList>> observable = restClient.getRetrofit().create
                (CheckListService.class).getChecklists();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CheckList>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ListaCheckList.this, "Erro: " +
                                e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<CheckList> checkLists) {
                        configuraRecyclerView(checkLists);
                    }
                });
    }

    private void botaoNovoChecklist() {
        FloatingActionButton botaoNovoChecklist = findViewById(R.id.lista_insere_checklist);
        botaoNovoChecklist.setOnClickListener(view -> vaiParaActivityMainActivity());
    }

    private void vaiParaActivityMainActivity() {
        Intent iniciaActivityMain =
                new Intent(ListaCheckList.this,
                        MainActivity.class);
        startActivityIfNeeded(iniciaActivityMain, 1);
    }

    private void configuraRecyclerView(List<CheckList> todosCheck) {
        RecyclerView listaCheck = findViewById(R.id.lista_checklist_recyclerview);
        configuraAdapter(todosCheck, listaCheck);
    }

    private void configuraAdapter(List<CheckList> todosCheck, RecyclerView listaChecks) {
        adapter = new Adapter(this, todosCheck);
        listaChecks.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(CheckList checkList, int position) {

                Intent abreMaincomCheck = new Intent(ListaCheckList.this, MainActivity.class);
                abreMaincomCheck.putExtra("checklist", checkList);
                abreMaincomCheck.putExtra("posicao", position);
                startActivityIfNeeded(abreMaincomCheck, 2);
            }
        });
    }
}

