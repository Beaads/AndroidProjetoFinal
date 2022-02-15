package com.beatriz.projetofinalandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.beatriz.projetofinalandroid.R;
import com.beatriz.projetofinalandroid.retrofit.RestClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaCheckList extends AppCompatActivity {

    RestClient restClient = new RestClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_checklist);
        setTitle("Adicionar Novo CheckList");
        botaoNovoChecklist();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        botaoNovoChecklist();
}


    private void botaoNovoChecklist() {
        FloatingActionButton botaoNovoChecklist = findViewById(R.id.lista_funcionarios_insere_checklist);
        botaoNovoChecklist.setOnClickListener(view -> vaiParaActivityMainActivity());
    }

    private void vaiParaActivityMainActivity() {
        Intent iniciaActivityMain =
                new Intent(ListaCheckList.this,
                        MainActivity.class);
        startActivityIfNeeded(iniciaActivityMain, 1);
    }
}