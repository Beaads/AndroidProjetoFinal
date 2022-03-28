package com.beatriz.projetofinalandroid.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.beatriz.projetofinalandroid.R;
import com.beatriz.projetofinalandroid.service.CheckListService;
import com.beatriz.projetofinalandroid.model.CheckList;
import com.beatriz.projetofinalandroid.retrofit.RestClient;
import com.beatriz.projetofinalandroid.ui.recyclerview.adapter.CheckListAdapter;
import com.beatriz.projetofinalandroid.ui.recyclerview.adapter.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ListaCheckListActivity extends AppCompatActivity {

    private CheckListAdapter adapter;
    private CompositeSubscription subscription = new CompositeSubscription();
    private final static CheckList cheks = new CheckList();
    private CheckList chek;
    private List<CheckList> checkLists;


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

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        subscription = null;
        super.onDestroy();
    }

//    private void abrirDialog() {
//        new AlertDialog.Builder(this).setMessage("Ops, quer mesmo remover o checklist?")
//                .setPositiveButton("Sim",
//                        (dialogInterface, i) -> deleta())
//                .setNegativeButton("Nao", null).show();
//    }


    public void abrirDialog(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Deletar Checklist");
        dialog.setMessage("Tem certeza que quer deletar este Checklist?");

        dialog.setCancelable(false);

        dialog.setIcon(android.R.drawable.ic_menu_delete);

        dialog.setPositiveButton("Sim",
                (dialogInterface, i) -> deleta()).setNegativeButton("Não", null ).show();

    }

    public void deleta() {
            Observable<CheckList> observable = RestClient.getRetrofit()
                    .create(CheckListService.class).delete(cheks.getId());
            subscription.remove(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckList>() {
                        @Override
                        public void onCompleted() {
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(ListaCheckListActivity.this, "Erro ao deletar " +
                                    e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(CheckList checkList) {

                        }
                    }));
        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.lista_menu, menu);
            MenuItem item = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
            return super.onCreateOptionsMenu(menu);
        }

        public void getTodosCheckList () {
            Observable<List<CheckList>> observable = RestClient.getRetrofit().create
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
                            Toast.makeText(ListaCheckListActivity.this, "Erro: " +
                                    e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(List<CheckList> checkLists) {
                            configuraRecyclerView(checkLists);
                        }
                    });
        }

        private void botaoNovoChecklist () {
            FloatingActionButton botaoNovoChecklist = findViewById(R.id.lista_insere_checklist);
            botaoNovoChecklist.setOnClickListener(view -> vaiParaActivityCriaCheckList());
        }

        private void vaiParaActivityCriaCheckList () {
            Intent iniciaActivityMain =
                    new Intent(ListaCheckListActivity.this,
                            CriaCheckListActivity.class);
            startActivityIfNeeded(iniciaActivityMain, 1);
        }

        private void configuraRecyclerView (List < CheckList > todosCheck) {
            RecyclerView listaCheck = findViewById(R.id.lista_checklist_recyclerview);
            configuraAdapter(todosCheck, listaCheck);
        }

        private void configuraAdapter (List < CheckList > todosCheck, RecyclerView listaChecks){
            adapter = new CheckListAdapter(this, todosCheck);
            listaChecks.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(CheckList checkList, int position) {
                    Intent abreMaincomCheck = new Intent(ListaCheckListActivity.this,
                            CriaCheckListActivity.class);
                    abreMaincomCheck.putExtra("checklist", checkList);
                    abreMaincomCheck.putExtra("posicao", position);
                    startActivityIfNeeded(abreMaincomCheck, 2);
                }
            });
        }

//    @Nullable
//    private CheckList buscaCheckpeloId(CheckList checks) {
//        CheckList checkEncontrado = null;
//        for (CheckList a :
//                cheks) {
//            if (a.getId() == checks.getId()) {
//                return a;
//            }
//        }
//        return null;
//    }
//    public void remove(CheckList check) {
//        CheckList checkDevolvido = buscaCheckpeloId(check);
//        if (checkDevolvido != null) {
//            cheks.remove(checkDevolvido);
//        }
//}
    }


