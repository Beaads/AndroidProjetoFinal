package com.beatriz.projetofinalandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    Button btnSalvar;
    RadioGroup RadioSaidaretorno;
    RadioGroup RadioTracaoOKNOK;
    RadioGroup RadioRodoarOKNOK;
    RadioGroup RadioCalibragemPneusOKNOK;
    RadioGroup RadioEstepeOKNOK;
    RadioGroup RadioFreioDianteiroOKNOK;
    RadioGroup RadioFreioTraseiroOKNOK;
    RadioGroup RadioAmortecedorOKNOK;
    RadioGroup RadioMolasOKNOK;
    RadioGroup RadioCambioOleoOKNOK;
    RadioGroup RadioDirecaoOleoOKNOK;
    RadioGroup RadioLimpezaRadiadorAguaOKNOK;
    RadioGroup RadioOleoMotorOKNOK;
    RadioGroup RadioRetrovisorOKNOK;
    RadioGroup RadioParaBrisaOKNOK;
    RadioGroup RadioParaChoqueDianteiroOKNOK;
    RadioGroup RadioParaChoqueTraseiroOKNOK;
    RadioGroup RadioestofamentoOKNOK;
    RadioGroup RadiocortinasOKNOK;
    RadioGroup RadiocintoDeSegurancaOKNOK;
    RadioGroup RadiofreioDeEstacionamentoOKNOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CheckList");
        BotaoSalvaChecklist();
    }


    //criar checklist salvar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CheckList checkListCriado = criaCheckList();
        Observable<CheckList> observable = (Observable<CheckList>) restClient.getRetrofit().create(CheckListService.class).salva(checkListCriado);
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
        btnSalvar = findViewById(R.id.btnSalvar);
        RadioSaidaretorno = findViewById(R.id.saidaretorno);
        RadioTracaoOKNOK = findViewById(R.id.TracaoOKNOK);
        RadioRodoarOKNOK = findViewById(R.id.RodoarOKNOK);
        RadioCalibragemPneusOKNOK = findViewById(R.id.CalibragemPneusOKNOK);
        RadioEstepeOKNOK = findViewById(R.id.EstepeOKNOK);
        RadioFreioDianteiroOKNOK = findViewById(R.id.FreioDianteiroOKNOK);
        RadioFreioTraseiroOKNOK = findViewById(R.id.FreioTraseiroOKNOK);
        RadioAmortecedorOKNOK = findViewById(R.id.AmortecedorOKNOK);
        RadioMolasOKNOK = findViewById(R.id.MolasOKNOK);
        RadioCambioOleoOKNOK = findViewById(R.id.CambioOleoOKNOK);
        RadioDirecaoOleoOKNOK = findViewById(R.id.DirecaoOleoOKNOK);
        RadioLimpezaRadiadorAguaOKNOK = findViewById(R.id.LimpezaRadiadorAguaOKNOK);
        RadioOleoMotorOKNOK = findViewById(R.id.OleoMotorOKNOK);
        RadioRetrovisorOKNOK = findViewById(R.id.RetrovisorOKNOK);
        RadioParaBrisaOKNOK = findViewById(R.id.paraBrisaOKNOK);
        RadioParaChoqueDianteiroOKNOK = findViewById(R.id.paraChoqueDianteiroOKNOK);
        RadioParaChoqueTraseiroOKNOK = findViewById(R.id.paraChoqueTraseiroOKNOK);
        RadioestofamentoOKNOK = findViewById(R.id.estofamentoOKNOK);
        RadiocortinasOKNOK = findViewById(R.id.cortinasOKNOK);
        RadiocintoDeSegurancaOKNOK = findViewById(R.id.cintoDeSegurancaOKNOK);
        RadiofreioDeEstacionamentoOKNOK = findViewById(R.id.freioDeEstacionamentoOKNOK);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    RadioButton selSaidaRetorno = findViewById(R.id.retorno);
                    RadioButton selTracao = findViewById(R.id.TracaoNOK);

                    if(RadioSaidaretorno.getCheckedRadioButtonId() == -1 || RadioTracaoOKNOK.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(MainActivity.this, "Erro ao salvar, selecione todas as opções", Toast.LENGTH_SHORT).show();
                        selTracao.setError(null);
                        if (RadioSaidaretorno.getCheckedRadioButtonId() == -1) {
                            selSaidaRetorno.setError("*");
                        }
                        if (RadioTracaoOKNOK.getCheckedRadioButtonId() == -1) {
                            selTracao.setError("*");

                        }
                        return;
                    }
                    selSaidaRetorno.setError(null);
                    selTracao.setError(null);

                }
            });

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
        RadioButton selSaidaRetorno = findViewById(RadioSaidaretorno.getCheckedRadioButtonId());

//        RadioButton selSaidaRetorno = findViewById(RadioSaidaretornoId);
//
//        boolean selectedSaidaRetorno = text.equals("Saída") ? true : false;

        return new CheckList(data.getText().toString(),
                hora.getText().toString(), placa.getText().toString(),
                saidaRetorno.callOnClick(), motorista.getText().toString(),
                kmVeiculo.getText().toString() /*selSaidaRetorno.getText().toString()*/);
    }
}
