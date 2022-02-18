package com.beatriz.projetofinalandroid.ui.activity;

import static com.beatriz.projetofinalandroid.ui.activity.CheckListActivityConstantes.CHAVE_CHECKLIST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

    private int posicaoRecebida;

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

        if(dadosRecebidos.hasExtra(CHAVE_CHECKLIST) && dadosRecebidos.hasExtra("posicao")) {
        setTitle("Visualizar CheckList");
        CheckList checkRecebido = (CheckList) dadosRecebidos.getSerializableExtra(CHAVE_CHECKLIST);
        check = checkRecebido;
       // posicaoRecebida = dadosRecebidos.getIntExtra("position", -1);
        TextView data = findViewById(R.id.data);
        data.setText(checkRecebido.getData());
        data.setEnabled(false);

        TextView hora = findViewById(R.id.hora);
        hora.setText(checkRecebido.getHora());
        hora.setEnabled(false);

        TextView placa = findViewById(R.id.placa);
        placa.setText(checkRecebido.getPlaca());
        placa.setEnabled(false);

        RadioSaidaretorno = findViewById(R.id.saidaretorno);
        RadioButton selSaidaRetorno = findViewById(R.id.retorno);
        RadioButton selSaidaRetorno1 = findViewById(R.id.saida);
        if (RadioSaidaretorno.getCheckedRadioButtonId() == -1) {
            selSaidaRetorno.setEnabled(false);
            selSaidaRetorno.setChecked(true);
            } selSaidaRetorno1.setEnabled(false);


//        RadioGroup saidaRetorno = findViewById(R.id.saidaretorno);
//        saidaRetorno.getCheckedRadioButtonId();
//        saidaRetorno.setEnabled(false);
//
//        RadioButton retornosaida = findViewById(R.id.retorno);
//        retornosaida.setText(checkRecebido.getSaidaRetorno());
//        retornosaida.setChecked(true);
//        retornosaida.setEnabled(false);
//
//        RadioButton saida = findViewById(R.id.saida);
//        saida.setText(checkRecebido.getSaidaRetorno());
//        saida.setChecked(true);
//        saida.setEnabled(false);


        TextView motorista = findViewById(R.id.motorista);
        motorista.setText(checkRecebido.getMotorista());
        motorista.setEnabled(false);

        TextView kmVeiculo = findViewById(R.id.kmveiculo);
        kmVeiculo.setText(checkRecebido.getKmVeiculo());
        kmVeiculo.setEnabled(false);

        RadioButton tracaoOK = findViewById(R.id.TracaoOK);
        tracaoOK.setEnabled(false);

        RadioButton tracaoNOK = findViewById(R.id.TracaoNOK);
        tracaoNOK.setEnabled(false);

        RadioButton rodoarOK = findViewById(R.id.RodoarOK);
        rodoarOK.setEnabled(false);

        RadioButton rodoarNOK = findViewById(R.id.RodoarNOK);
        rodoarNOK.setEnabled(false);

        RadioButton calibragempneusOk = findViewById(R.id.CalibragemPneusOK);
        calibragempneusOk.setEnabled(false);

        RadioButton calibragempneusNOK = findViewById(R.id.CalibragemPneusNOK);
        calibragempneusNOK.setEnabled(false);

        RadioButton estepeOk = findViewById(R.id.EstepeOK);
        estepeOk.setEnabled(false);

        RadioButton estepeNOk = findViewById(R.id.EstepeNOK);
        estepeNOk.setEnabled(false);

        return;
       }
       setTitle("CheckList");
    }

    public void adicionaCheck() {
        if(criaCheckList().getId() == 0) {
        CheckList checkListCriado = criaCheckList();
        Observable<CheckList> observable = (Observable<CheckList>) restClient.getRetrofit()
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
                        Toast.makeText(MainActivity.this, "Sucesso ao criar",
                                Toast.LENGTH_SHORT).show();
                    }
        });
    }
        if (check.getId() != 0) {
            CheckList checkListCriado = criaCheckList();
            Observable<CheckList> observable = restClient.getRetrofit().create
                    (CheckListService.class).getCheckListPorId(check.getId());
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(MainActivity.this, "Erro: " +
                                    e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(CheckList checkList) {
                            Toast.makeText(MainActivity.this, "Sucesso",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
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

                if (RadioSaidaretorno.getCheckedRadioButtonId() == -1 ||
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

                    Toast.makeText(MainActivity.this, "Erro ao salvar, selecione " +
                            "todas as opções", Toast.LENGTH_SHORT).show();

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
                vaiParaListaCheckList();
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


