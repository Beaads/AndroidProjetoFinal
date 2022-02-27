package com.beatriz.projetofinalandroid.ui.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    public CheckList check = new CheckList();

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
        BotaoSalvaChecklist();

        Intent dadosRecebidos = getIntent();

        if (dadosRecebidos.hasExtra("checklist") && dadosRecebidos.hasExtra("posicao")) {
            setTitle("Visualizar CheckList");
            CheckList checkRecebido = (CheckList) dadosRecebidos.getSerializableExtra("checklist");
            check = checkRecebido;

            Button btnSalvar = findViewById(R.id.btnSalvar);
            btnSalvar.setVisibility(View.INVISIBLE);

            TextView data = findViewById(R.id.data);
            data.setText(checkRecebido.getData());
            data.setEnabled(false);

            TextView placa = findViewById(R.id.placa);
            placa.setText(checkRecebido.getPlaca());
            placa.setEnabled(false);

            TextView hora = findViewById(R.id.hora);
            hora.setText(checkRecebido.getHora());
            hora.setEnabled(false);

            TextView motorista = findViewById(R.id.motorista);
            motorista.setText(checkRecebido.getMotorista());
            motorista.setEnabled(false);

            TextView kmVeiculo = findViewById(R.id.kmveiculo);
            kmVeiculo.setText(checkRecebido.getKmVeiculo());
            kmVeiculo.setEnabled(false);

            RadioButton selSaidaRetorno = findViewById(R.id.retorno);
            RadioButton selSaidaRetorno1 = findViewById(R.id.saida);
            selSaidaRetorno.setEnabled(false);
            selSaidaRetorno1.setEnabled(false);
            if (checkRecebido.getSaidaRetorno().equals("Retorno")) {
                selSaidaRetorno.setChecked(true);
            } else {
                selSaidaRetorno1.setChecked(true);
            }

            RadioButton selTracaoOk = findViewById(R.id.TracaoOK);
            RadioButton selTracaoNOk = findViewById(R.id.TracaoNOK);
            selTracaoOk.setEnabled(false);
            selTracaoNOk.setEnabled(false);
            if (checkRecebido.getTracao().equals("OK")) selTracaoOk.setChecked(true);
            else selTracaoNOk.setChecked(true);

            RadioButton selRodoarOk = findViewById(R.id.RodoarOK);
            RadioButton selRodoarNOk = findViewById(R.id.RodoarNOK);
            selRodoarOk.setEnabled(false);
            selRodoarNOk.setEnabled(false);
            if (checkRecebido.getRodoar().equals("OK")) selRodoarOk.setChecked(true);
            else selRodoarNOk.setChecked(true);

            RadioButton selCalibragemOk = findViewById(R.id.CalibragemPneusOK);
            RadioButton selCalibragemNOk = findViewById(R.id.CalibragemPneusNOK);
            selCalibragemOk.setEnabled(false);
            selCalibragemNOk.setEnabled(false);
            if (checkRecebido.getCalibragemPneus().equals("OK")) selCalibragemOk.setChecked(true);
            else selCalibragemNOk.setChecked(true);

            RadioButton selEstepeOk = findViewById(R.id.EstepeOK);
            RadioButton selEstepeNOk = findViewById(R.id.EstepeNOK);
            selEstepeOk.setEnabled(false);
            selEstepeNOk.setEnabled(false);
            if (checkRecebido.getEstepe().equals("OK")) selEstepeOk.setChecked(true);
            else selEstepeNOk.setChecked(true);

            RadioButton selFreioDianteiroOk = findViewById(R.id.FreioDianteiroOK);
            RadioButton selFreioDianteiroNOk = findViewById(R.id.FreioDianteiroNOK);
            selFreioDianteiroOk.setEnabled(false);
            selFreioDianteiroNOk.setEnabled(false);
            if (checkRecebido.getFreioDianteiro().equals("OK"))
                selFreioDianteiroOk.setChecked(true);
            else selFreioDianteiroNOk.setChecked(true);

            RadioButton selFreioTraseiroOk = findViewById(R.id.FreioTraseiroOK);
            RadioButton selFreioTraseiroNOk = findViewById(R.id.FreioTraseiroNOK);
            selFreioTraseiroOk.setEnabled(false);
            selFreioTraseiroNOk.setEnabled(false);
            if (checkRecebido.getFreioTraseiro().equals("OK"))
                selFreioTraseiroOk.setChecked(true);
            else selFreioTraseiroNOk.setChecked(true);

            RadioButton selAmortecedorOK = findViewById(R.id.AmortcedorOK);
            RadioButton selAmortecedorNOk = findViewById(R.id.AmortecedorNOK);
            selAmortecedorOK.setEnabled(false);
            selAmortecedorNOk.setEnabled(false);
            if (checkRecebido.getAmortecedor().equals("OK"))
                selAmortecedorOK.setChecked(true);
            else selAmortecedorNOk.setChecked(true);

            RadioButton selMolasOk = findViewById(R.id.MolasOK);
            RadioButton selMolasNOk = findViewById(R.id.MolasNOK);
            selMolasOk.setEnabled(false);
            selMolasNOk.setEnabled(false);
            if (checkRecebido.getMolas().equals("OK"))
                selMolasOk.setChecked(true);
            else selMolasNOk.setChecked(true);

            RadioButton selCambioOLeoOk = findViewById(R.id.CambioOleoOK);
            RadioButton selCambioOLeoNOk = findViewById(R.id.CambioOleoNOK);
            selCambioOLeoOk.setEnabled(false);
            selCambioOLeoNOk.setEnabled(false);
            if (checkRecebido.getCambioOleo().equals("OK"))
                selCambioOLeoOk.setChecked(true);
            else selCambioOLeoNOk.setChecked(true);

            RadioButton selDirecaoOleoOk = findViewById(R.id.DirecaoOleoOK);
            RadioButton selDirecaoOleoNOk = findViewById(R.id.DirecaoOleoNOK);
            selDirecaoOleoOk.setEnabled(false);
            selDirecaoOleoNOk.setEnabled(false);
            if (checkRecebido.getDirecaoOleo().equals("OK"))
                selDirecaoOleoOk.setChecked(true);
            else selDirecaoOleoNOk.setChecked(true);

            RadioButton selLimpezaRadiadorAguaOk = findViewById(R.id.LimpezaRadiadorAguaOK);
            RadioButton selLimpezaRadiadorAguaNOk = findViewById(R.id.LimpezaRadiadorAguaNOK);
            selLimpezaRadiadorAguaOk.setEnabled(false);
            selLimpezaRadiadorAguaNOk.setEnabled(false);
            if (checkRecebido.getLimpezaRadiadorAgua().equals("OK"))
                selLimpezaRadiadorAguaOk.setChecked(true);
            else selLimpezaRadiadorAguaNOk.setChecked(true);

            RadioButton selOleoMotorOk = findViewById(R.id.OleoMotorOK);
            RadioButton selOleoMotorNOk = findViewById(R.id.OleoMotorNOK);
            selOleoMotorOk.setEnabled(false);
            selOleoMotorNOk.setEnabled(false);
            if (checkRecebido.getOleoMotor().equals("OK"))
                selOleoMotorOk.setChecked(true);
            else selOleoMotorNOk.setChecked(true);

            RadioButton selRetrovisorOk = findViewById(R.id.RetrovisorOK);
            RadioButton selRetrovisorNOk = findViewById(R.id.RetrovisorNOK);
            selRetrovisorOk.setEnabled(false);
            selRetrovisorNOk.setEnabled(false);
            if (checkRecebido.getRetrovisor().equals("OK"))
                selRetrovisorOk.setChecked(true);
            else selRetrovisorNOk.setChecked(true);

            RadioButton selParaBrisaOk = findViewById(R.id.paraBrisaOK);
            RadioButton selParaBrisaNOk = findViewById(R.id.paraBrisaNOK);
            selParaBrisaOk.setEnabled(false);
            selParaBrisaNOk.setEnabled(false);
            if (checkRecebido.getParaBrisa().equals("OK"))
                selParaBrisaOk.setChecked(true);
            else selParaBrisaNOk.setChecked(true);

            RadioButton selParaChoqueDianteiroOk = findViewById(R.id.paraChoqueDianteiroOK);
            RadioButton selParaChoqueDianteiroNOk = findViewById(R.id.paraChoqueDianteiroNOK);
            selParaChoqueDianteiroOk.setEnabled(false);
            selParaChoqueDianteiroNOk.setEnabled(false);
            if (checkRecebido.getParaChoqueDianteiro().equals("OK"))
                selParaChoqueDianteiroOk.setChecked(true);
            else selParaChoqueDianteiroNOk.setChecked(true);

            RadioButton selParaChoqueTraseiroOk = findViewById(R.id.paraChoqueTraseiroOK);
            RadioButton selParaChoqueTraseiroNOk = findViewById(R.id.paraChoqueTraseiroNOK);
            selParaChoqueTraseiroOk.setEnabled(false);
            selParaChoqueTraseiroNOk.setEnabled(false);
            if (checkRecebido.getParaChoqueTraseiro().equals("OK"))
                selParaChoqueTraseiroOk.setChecked(true);
            else selParaChoqueTraseiroNOk.setChecked(true);

            RadioButton selEstofamentoOk = findViewById(R.id.estofamentoOK);
            RadioButton selEstofamentoNOk = findViewById(R.id.estofamentoNOK);
            selEstofamentoOk.setEnabled(false);
            selEstofamentoNOk.setEnabled(false);
            if (checkRecebido.getEstofamento().equals("OK"))
                selEstofamentoOk.setChecked(true);
            else selEstofamentoNOk.setChecked(true);

            RadioButton selCortinasOk = findViewById(R.id.cortinasOK);
            RadioButton selCortinasNOk = findViewById(R.id.cortinasNOK);
            selCortinasOk.setEnabled(false);
            selCortinasNOk.setEnabled(false);
            if (checkRecebido.getCortinas().equals("OK"))
                selCortinasOk.setChecked(true);
            else selCortinasNOk.setChecked(true);

            RadioButton selCintoDeSegurancaOk = findViewById(R.id.cintoDeSegurancaOK);
            RadioButton selCintoDeSegurancaNOk = findViewById(R.id.cintoDeSegurancaNOK);
            selCintoDeSegurancaOk.setEnabled(false);
            selCintoDeSegurancaNOk.setEnabled(false);
            if (checkRecebido.getCintoDeSeguranca().equals("OK"))
                selCintoDeSegurancaOk.setChecked(true);
            else selCintoDeSegurancaNOk.setChecked(true);

            RadioButton selFreioDeEstacionamentoOk = findViewById(R.id.freioDeEstacionamentoOK);
            RadioButton selFreioDeEstacionamentoNOk = findViewById(R.id.freioDeEstacionamentoNOK);
            selFreioDeEstacionamentoOk.setEnabled(false);
            selFreioDeEstacionamentoNOk.setEnabled(false);
            if (checkRecebido.getFreioDeEstacionamento().equals("OK"))
                selFreioDeEstacionamentoOk.setChecked(true);
            else selFreioDeEstacionamentoNOk.setChecked(true);

            return;

        }
        setTitle("Novo CheckList");

}

    public void adicionaCheck() {
        if (criaCheckList().getId() == 0) {
            CheckList checkListCriado = criaCheckList();
            Observable<CheckList> observable = restClient.getRetrofit()
                    .create(CheckListService.class).salva(checkListCriado);
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
                            Toast.makeText(MainActivity.this, "Erro ao criar " +
                                    e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(CheckList checklist) {
                        }
                    });
        }
    }

    private void BotaoSalvaChecklist() {
        btnSalvar = findViewById(R.id.btnSalvar);
        EditText Placa = findViewById(R.id.placa);
        EditText Data = findViewById(R.id.data);
        EditText Hora = findViewById(R.id.hora);
        EditText Motorista = findViewById(R.id.motorista);
        EditText KmVeiculo = findViewById(R.id.kmveiculo);
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

                EditText selData = findViewById(R.id.data);
                EditText selPlaca = findViewById(R.id.placa);
                EditText selHora = findViewById(R.id.hora);
                EditText selMotorista = findViewById(R.id.motorista);
                EditText selKmVeiculo = findViewById(R.id.kmveiculo);
                RadioButton selSaidaRetorno = findViewById(R.id.retorno);
                RadioButton selTracao = findViewById(R.id.TracaoNOK);
                RadioButton selRodoar = findViewById(R.id.RodoarNOK);
                RadioButton selCalibragemPneus = findViewById(R.id.CalibragemPneusNOK);
                RadioButton selEstepe = findViewById(R.id.EstepeNOK);
                RadioButton selFreioDianteiro = findViewById(R.id.FreioDianteiroNOK);
                RadioButton selFreioTraseiro = findViewById(R.id.FreioTraseiroNOK);
                RadioButton selAmortecedor = findViewById(R.id.AmortecedorNOK);
                RadioButton selMolas = findViewById(R.id.MolasNOK);
                RadioButton selCambioOleo = findViewById(R.id.CambioOleoNOK);
                RadioButton selDirecaoOleo = findViewById(R.id.DirecaoOleoNOK);
                RadioButton selLimpezaRadiadorAgua = findViewById(R.id.LimpezaRadiadorAguaNOK);
                RadioButton selOleoMotor = findViewById(R.id.OleoMotorNOK);
                RadioButton selRetrovisor = findViewById(R.id.RetrovisorNOK);
                RadioButton selParaBrisa = findViewById(R.id.paraBrisaNOK);
                RadioButton selParaChoqueDianteiro = findViewById(R.id.paraChoqueDianteiroNOK);
                RadioButton selParaChoqueTraseiro = findViewById(R.id.paraChoqueTraseiroNOK);
                RadioButton selEstofamento = findViewById(R.id.estofamentoNOK);
                RadioButton selCortinas = findViewById(R.id.cortinasNOK);
                RadioButton selCintoDeSeguranca = findViewById(R.id.cintoDeSegurancaNOK);
                RadioButton selFreioDeEstacionamento = findViewById(R.id.freioDeEstacionamentoNOK);

                selSaidaRetorno.setError(null);
                selTracao.setError(null);
                selRodoar.setError(null);
                selCalibragemPneus.setError(null);
                selEstepe.setError(null);
                selFreioDianteiro.setError(null);
                selFreioTraseiro.setError(null);
                selAmortecedor.setError(null);
                selMolas.setError(null);
                selCambioOleo.setError(null);
                selDirecaoOleo.setError(null);
                selLimpezaRadiadorAgua.setError(null);
                selOleoMotor.setError(null);
                selRetrovisor.setError(null);
                selParaBrisa.setError(null);
                selParaChoqueDianteiro.setError(null);
                selParaChoqueTraseiro.setError(null);
                selEstofamento.setError(null);
                selCortinas.setError(null);
                selCintoDeSeguranca.setError(null);
                selFreioDeEstacionamento.setError(null);

                if (    Data.getText().toString().length() == 0 ||
                        Placa.getText().toString().length() == 0 ||
                        Hora.getText().toString().length() == 0 ||
                        Motorista.getText().toString().length() == 0 ||
                        KmVeiculo.getText().toString().length() == 0 ||
                        RadioSaidaretorno.getCheckedRadioButtonId() == -1 ||
                        RadioTracaoOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioRodoarOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioCalibragemPneusOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioEstepeOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioFreioDianteiroOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioFreioTraseiroOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioAmortecedorOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioMolasOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioCambioOleoOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioDirecaoOleoOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioLimpezaRadiadorAguaOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioOleoMotorOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioRetrovisorOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioParaBrisaOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioParaChoqueDianteiroOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioParaChoqueTraseiroOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadioestofamentoOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadiocortinasOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadiocintoDeSegurancaOKNOK.getCheckedRadioButtonId() == -1 ||
                        RadiofreioDeEstacionamentoOKNOK.getCheckedRadioButtonId() == -1) {

                    Toast.makeText(MainActivity.this, "Erro, responda " +
                            "todos os campos.", Toast.LENGTH_SHORT).show();

                    if(Data.getText().toString().length() == 0){
                        selData.setError("Preencha este campo");
                    }
                    if(Placa.getText().toString().length() == 0){
                        selPlaca.setError("Preencha este campo");
                    }
                    if(Hora.getText().toString().length() == 0){
                        selHora.setError("Preencha este campo");
                    }
                    if(Motorista.getText().toString().length() == 0){
                        selMotorista.setError("Preencha este campo");
                    }
                    if(KmVeiculo.getText().toString().length() == 0){
                        selKmVeiculo.setError("Preencha este campo");
                    }
                    if (RadioSaidaretorno.getCheckedRadioButtonId() == -1) {
                        selSaidaRetorno.setError("*");
                    }
                    if (RadioTracaoOKNOK.getCheckedRadioButtonId() == -1) {
                        selTracao.setError("*");
                    }
                    if (RadioRodoarOKNOK.getCheckedRadioButtonId() == -1) {
                        selRodoar.setError("*");
                    }
                    if (RadioCalibragemPneusOKNOK.getCheckedRadioButtonId() == -1) {
                        selCalibragemPneus.setError("*");
                    }
                    if (RadioEstepeOKNOK.getCheckedRadioButtonId() == -1) {
                        selEstepe.setError("*");
                    }
                    if (RadioFreioDianteiroOKNOK.getCheckedRadioButtonId() == -1) {
                        selFreioDianteiro.setError("*");
                    }
                    if (RadioFreioTraseiroOKNOK.getCheckedRadioButtonId() == -1) {
                        selFreioTraseiro.setError("*");
                    }
                    if (RadioAmortecedorOKNOK.getCheckedRadioButtonId() == -1) {
                        selAmortecedor.setError("*");
                    }
                    if (RadioMolasOKNOK.getCheckedRadioButtonId() == -1) {
                        selMolas.setError("*");
                    }
                    if (RadioCambioOleoOKNOK.getCheckedRadioButtonId() == -1) {
                        selCambioOleo.setError("*");
                    }
                    if (RadioDirecaoOleoOKNOK.getCheckedRadioButtonId() == -1) {
                        selDirecaoOleo.setError("*");
                    }
                    if (RadioLimpezaRadiadorAguaOKNOK.getCheckedRadioButtonId() == -1) {
                        selLimpezaRadiadorAgua.setError("*");
                    }
                    if (RadioOleoMotorOKNOK.getCheckedRadioButtonId() == -1) {
                        selOleoMotor.setError("*");
                    }
                    if (RadioRetrovisorOKNOK.getCheckedRadioButtonId() == -1) {
                        selRetrovisor.setError("*");
                    }
                    if (RadioParaBrisaOKNOK.getCheckedRadioButtonId() == -1) {
                        selParaBrisa.setError("*");
                    }
                    if (RadioParaChoqueDianteiroOKNOK.getCheckedRadioButtonId() == -1) {
                        selParaChoqueDianteiro.setError("*");
                    }
                    if (RadioParaChoqueTraseiroOKNOK.getCheckedRadioButtonId() == -1) {
                        selParaChoqueTraseiro.setError("*");
                    }
                    if (RadioestofamentoOKNOK.getCheckedRadioButtonId() == -1) {
                        selEstofamento.setError("*");
                    }
                    if (RadiocortinasOKNOK.getCheckedRadioButtonId() == -1) {
                        selCortinas.setError("*");
                    }
                    if (RadiocintoDeSegurancaOKNOK.getCheckedRadioButtonId() == -1) {
                        selCintoDeSeguranca.setError("*");
                    }
                    if (RadiofreioDeEstacionamentoOKNOK.getCheckedRadioButtonId() == -1) {
                        selFreioDeEstacionamento.setError("*");
                    }
                    return;
                }
                adicionaCheck();
            }
        });
    }

    @NonNull
    private CheckList criaCheckList() {

        EditText data = findViewById
                (R.id.data);
        EditText hora = findViewById
                (R.id.hora);
        RadioButton selSaidaRetorno = findViewById
                (RadioSaidaretorno.getCheckedRadioButtonId());
        EditText placa = findViewById
                (R.id.placa);
        EditText motorista = findViewById
                (R.id.motorista);
        EditText kmVeiculo = findViewById
                (R.id.kmveiculo);
        RadioButton selTracao = findViewById
                (RadioTracaoOKNOK.getCheckedRadioButtonId());
        RadioButton selRodoar = findViewById
                (RadioRodoarOKNOK.getCheckedRadioButtonId());
        RadioButton selCalibragemPneus = findViewById
                (RadioCalibragemPneusOKNOK.getCheckedRadioButtonId());
        RadioButton selEstepe = findViewById
                (RadioEstepeOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioDianteiro = findViewById
                (RadioFreioDianteiroOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioTraseiro = findViewById
                (RadioFreioTraseiroOKNOK.getCheckedRadioButtonId());
        RadioButton selAmortecedor = findViewById
                (RadioAmortecedorOKNOK.getCheckedRadioButtonId());
        RadioButton selMolas = findViewById
                (RadioMolasOKNOK.getCheckedRadioButtonId());
        RadioButton selCambioOleo = findViewById
                (RadioCambioOleoOKNOK.getCheckedRadioButtonId());
        RadioButton selDirecaoOleo = findViewById
                (RadioDirecaoOleoOKNOK.getCheckedRadioButtonId());
        RadioButton selLimpezaRadiadorAgua = findViewById
                (RadioLimpezaRadiadorAguaOKNOK.getCheckedRadioButtonId());
        RadioButton selOleoMotor = findViewById
                (RadioOleoMotorOKNOK.getCheckedRadioButtonId());
        RadioButton selRetrovisor = findViewById
                (RadioRetrovisorOKNOK.getCheckedRadioButtonId());
        RadioButton selParaBrisa = findViewById
                (RadioParaBrisaOKNOK.getCheckedRadioButtonId());
        RadioButton selParaChoqueDianteiro = findViewById
                (RadioParaChoqueDianteiroOKNOK.getCheckedRadioButtonId());
        RadioButton selParaChoqueTraseiro = findViewById
                (RadioParaChoqueTraseiroOKNOK.getCheckedRadioButtonId());
        RadioButton selEstofamento = findViewById
                (RadioestofamentoOKNOK.getCheckedRadioButtonId());
        RadioButton selCortinas = findViewById
                (RadiocortinasOKNOK.getCheckedRadioButtonId());
        RadioButton selCintoDeSeguranca = findViewById
                (RadiocintoDeSegurancaOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioDeEstacionamento = findViewById
                (RadiofreioDeEstacionamentoOKNOK.getCheckedRadioButtonId());

        return new CheckList(data.getText().toString(),
                hora.getText().toString(), selSaidaRetorno.getText().toString(),
                placa.getText().toString(), motorista.getText().toString(),
                kmVeiculo.getText().toString(), selTracao.getText().toString(),
                selRodoar.getText().toString(), selCalibragemPneus.getText().toString(),
                selEstepe.getText().toString(), selFreioDianteiro.getText().toString(),
                selFreioTraseiro.getText().toString(), selAmortecedor.getText().toString(),
                selMolas.getText().toString(), selCambioOleo.getText().toString(),
                selDirecaoOleo.getText().toString(), selLimpezaRadiadorAgua.getText().toString(),
                selOleoMotor.getText().toString(), selRetrovisor.getText().toString(),
                selParaBrisa.getText().toString(), selParaChoqueDianteiro.getText().toString(),
                selParaChoqueTraseiro.getText().toString(), selEstofamento.getText().toString(),
                selCortinas.getText().toString(), selCintoDeSeguranca.getText().toString(),
                selFreioDeEstacionamento.getText().toString());
    }
}


