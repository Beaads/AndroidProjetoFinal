package com.beatriz.projetofinalandroid.ui.activity;

import androidx.annotation.IdRes;
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
import com.beatriz.projetofinalandroid.service.CheckListService;
import com.beatriz.projetofinalandroid.model.CheckList;
import com.beatriz.projetofinalandroid.retrofit.RestClient;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CriaCheckListActivity extends AppCompatActivity {
    private CompositeSubscription subscription = new CompositeSubscription();

    private RadioGroup radioSaidaretorno;
    private RadioGroup radioTracaoOKNOK;
    private RadioGroup radioRodoarOKNOK;
    private RadioGroup radioCalibragemPneusOKNOK;
    private RadioGroup radioEstepeOKNOK;
    private RadioGroup radioFreioDianteiroOKNOK;
    private RadioGroup radioFreioTraseiroOKNOK;
    private RadioGroup radioAmortecedorOKNOK;
    private RadioGroup radioMolasOKNOK;
    private RadioGroup radioCambioOleoOKNOK;
    private RadioGroup radioDirecaoOleoOKNOK;
    private RadioGroup radioLimpezaRadiadorAguaOKNOK;
    private RadioGroup radioOleoMotorOKNOK;
    private RadioGroup radioRetrovisorOKNOK;
    private RadioGroup radioParaBrisaOKNOK;
    private RadioGroup radioParaChoqueDianteiroOKNOK;
    private RadioGroup radioParaChoqueTraseiroOKNOK;
    private RadioGroup radioEstofamentoOKNOK;
    private RadioGroup radioCortinasOKNOK;
    private RadioGroup radioCintoDeSegurancaOKNOK;
    private RadioGroup radioFreioDeEstacionamentoOKNOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_checklist);
        setTitle("Novo CheckList");
        configuraBotaoSalvaChecklist();

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra("checklist") && dadosRecebidos.hasExtra("posicao")) {
            setTitle("Visualizar CheckList");
            CheckList checkRecebido = (CheckList) dadosRecebidos.getSerializableExtra("checklist");
            configuraRadioButtons(checkRecebido);
        }
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        subscription = null;
        super.onDestroy();
    }

    public void adicionaCheck() {
        CheckList checkListCriado = criaCheckList();
        Observable<CheckList> observable = RestClient.getRetrofit()
                .create(CheckListService.class).salva(checkListCriado);
        subscription.add(
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
                                Toast.makeText(CriaCheckListActivity.this, "Erro ao criar " +
                                        e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(CheckList checklist) {
                            }
                        })
        );
    }

    private void configuraRadioButtons(CheckList checkRecebido) {
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

        setupRadioButton(R.id.retorno, R.id.saida, checkRecebido.getSaidaRetorno(), "Retorno");
        setupRadioButton(R.id.TracaoOK, R.id.TracaoNOK, checkRecebido.getTracao(), "OK");
        setupRadioButton(R.id.RodoarOK, R.id.RodoarNOK, checkRecebido.getRodoar(), "OK");
        setupRadioButton(R.id.CalibragemPneusOK, R.id.CalibragemPneusNOK, checkRecebido.getCalibragemPneus(), "OK");
        setupRadioButton(R.id.EstepeOK, R.id.EstepeNOK, checkRecebido.getEstepe(), "OK");
        setupRadioButton(R.id.FreioDianteiroOK, R.id.FreioDianteiroNOK, checkRecebido.getFreioDianteiro(), "OK");
        setupRadioButton(R.id.FreioTraseiroOK, R.id.FreioTraseiroNOK, checkRecebido.getFreioTraseiro(), "OK");
        setupRadioButton(R.id.AmortcedorOK, R.id.AmortecedorNOK, checkRecebido.getAmortecedor(), "OK");
        setupRadioButton(R.id.MolasOK, R.id.MolasNOK, checkRecebido.getMolas(), "OK");
        setupRadioButton(R.id.CambioOleoOK, R.id.CambioOleoNOK, checkRecebido.getCambioOleo(), "OK");
        setupRadioButton(R.id.DirecaoOleoOK, R.id.DirecaoOleoNOK, checkRecebido.getDirecaoOleo(), "OK");
        setupRadioButton(R.id.LimpezaRadiadorAguaOK, R.id.LimpezaRadiadorAguaNOK, checkRecebido.getLimpezaRadiadorAgua(), "OK");
        setupRadioButton(R.id.OleoMotorOK, R.id.OleoMotorNOK, checkRecebido.getOleoMotor(), "OK");
        setupRadioButton(R.id.RetrovisorOK, R.id.RetrovisorNOK, checkRecebido.getRetrovisor(), "OK");
        setupRadioButton(R.id.paraBrisaOK, R.id.paraBrisaNOK, checkRecebido.getParaBrisa(), "OK");
        setupRadioButton(R.id.paraChoqueDianteiroOK, R.id.paraChoqueDianteiroNOK, checkRecebido.getParaChoqueDianteiro(), "OK");
        setupRadioButton(R.id.paraChoqueTraseiroOK, R.id.paraChoqueTraseiroNOK, checkRecebido.getParaChoqueTraseiro(), "OK");
        setupRadioButton(R.id.estofamentoOK, R.id.estofamentoNOK, checkRecebido.getEstofamento(), "OK");
        setupRadioButton(R.id.cortinasOK, R.id.cortinasNOK, checkRecebido.getCortinas(), "OK");
        setupRadioButton(R.id.cintoDeSegurancaOK, R.id.cintoDeSegurancaNOK, checkRecebido.getCintoDeSeguranca(), "OK");
        setupRadioButton(R.id.freioDeEstacionamentoOK, R.id.freioDeEstacionamentoNOK, checkRecebido.getFreioDeEstacionamento(), "OK");
    }

    private void setupRadioButton(@IdRes int radio1ResId, @IdRes int radio2ResId, String resultado, String valorEsperado) {
        RadioButton selSaidaRetorno = findViewById(radio1ResId);
        RadioButton selSaidaRetorno1 = findViewById(radio2ResId);
        selSaidaRetorno.setEnabled(false);
        selSaidaRetorno1.setEnabled(false);
        if (resultado.equals(valorEsperado)) {
            selSaidaRetorno.setChecked(true);
        } else {
            selSaidaRetorno1.setChecked(true);
        }
    }

    private void configuraBotaoSalvaChecklist() {
        Button btnSalvar = findViewById(R.id.btnSalvar);
        EditText Placa = findViewById(R.id.placa);
        EditText Data = findViewById(R.id.data);
        EditText Hora = findViewById(R.id.hora);
        EditText Motorista = findViewById(R.id.motorista);
        EditText KmVeiculo = findViewById(R.id.kmveiculo);
        radioSaidaretorno = findViewById(R.id.saidaretorno);
        radioTracaoOKNOK = findViewById(R.id.TracaoOKNOK);
        radioRodoarOKNOK = findViewById(R.id.RodoarOKNOK);
        radioCalibragemPneusOKNOK = findViewById(R.id.CalibragemPneusOKNOK);
        radioEstepeOKNOK = findViewById(R.id.EstepeOKNOK);
        radioFreioDianteiroOKNOK = findViewById(R.id.FreioDianteiroOKNOK);
        radioFreioTraseiroOKNOK = findViewById(R.id.FreioTraseiroOKNOK);
        radioAmortecedorOKNOK = findViewById(R.id.AmortecedorOKNOK);
        radioMolasOKNOK = findViewById(R.id.MolasOKNOK);
        radioCambioOleoOKNOK = findViewById(R.id.CambioOleoOKNOK);
        radioDirecaoOleoOKNOK = findViewById(R.id.DirecaoOleoOKNOK);
        radioLimpezaRadiadorAguaOKNOK = findViewById(R.id.LimpezaRadiadorAguaOKNOK);
        radioOleoMotorOKNOK = findViewById(R.id.OleoMotorOKNOK);
        radioRetrovisorOKNOK = findViewById(R.id.RetrovisorOKNOK);
        radioParaBrisaOKNOK = findViewById(R.id.paraBrisaOKNOK);
        radioParaChoqueDianteiroOKNOK = findViewById(R.id.paraChoqueDianteiroOKNOK);
        radioParaChoqueTraseiroOKNOK = findViewById(R.id.paraChoqueTraseiroOKNOK);
        radioEstofamentoOKNOK = findViewById(R.id.estofamentoOKNOK);
        radioCortinasOKNOK = findViewById(R.id.cortinasOKNOK);
        radioCintoDeSegurancaOKNOK = findViewById(R.id.cintoDeSegurancaOKNOK);
        radioFreioDeEstacionamentoOKNOK = findViewById(R.id.freioDeEstacionamentoOKNOK);

        btnSalvar.setOnClickListener(view -> {
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

            if (Data.getText().toString().length() == 0 ||
                    Placa.getText().toString().length() == 0 ||
                    Hora.getText().toString().length() == 0 ||
                    Motorista.getText().toString().length() == 0 ||
                    KmVeiculo.getText().toString().length() == 0 ||
                    radioSaidaretorno.getCheckedRadioButtonId() == -1 ||
                    radioTracaoOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioRodoarOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioCalibragemPneusOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioEstepeOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioFreioDianteiroOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioFreioTraseiroOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioAmortecedorOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioMolasOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioCambioOleoOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioDirecaoOleoOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioLimpezaRadiadorAguaOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioOleoMotorOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioRetrovisorOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioParaBrisaOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioParaChoqueDianteiroOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioParaChoqueTraseiroOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioEstofamentoOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioCortinasOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioCintoDeSegurancaOKNOK.getCheckedRadioButtonId() == -1 ||
                    radioFreioDeEstacionamentoOKNOK.getCheckedRadioButtonId() == -1) {

                Toast.makeText(CriaCheckListActivity.this, "Erro, responda " +
                        "todos os campos.", Toast.LENGTH_SHORT).show();

                if (Data.getText().toString().length() == 0) {
                    Data.setError("Preencha este campo");
                }
                if (Placa.getText().toString().length() == 0) {
                    Placa.setError("Preencha este campo");
                }
                if (Hora.getText().toString().length() == 0) {
                    Hora.setError("Preencha este campo");
                }
                if (Motorista.getText().toString().length() == 0) {
                    Motorista.setError("Preencha este campo");
                }
                if (KmVeiculo.getText().toString().length() == 0) {
                    KmVeiculo.setError("Preencha este campo");
                }
                if (radioSaidaretorno.getCheckedRadioButtonId() == -1) {
                    selSaidaRetorno.setError("*");
                }
                if (radioTracaoOKNOK.getCheckedRadioButtonId() == -1) {
                    selTracao.setError("*");
                }
                if (radioRodoarOKNOK.getCheckedRadioButtonId() == -1) {
                    selRodoar.setError("*");
                }
                if (radioCalibragemPneusOKNOK.getCheckedRadioButtonId() == -1) {
                    selCalibragemPneus.setError("*");
                }
                if (radioEstepeOKNOK.getCheckedRadioButtonId() == -1) {
                    selEstepe.setError("*");
                }
                if (radioFreioDianteiroOKNOK.getCheckedRadioButtonId() == -1) {
                    selFreioDianteiro.setError("*");
                }
                if (radioFreioTraseiroOKNOK.getCheckedRadioButtonId() == -1) {
                    selFreioTraseiro.setError("*");
                }
                if (radioAmortecedorOKNOK.getCheckedRadioButtonId() == -1) {
                    selAmortecedor.setError("*");
                }
                if (radioMolasOKNOK.getCheckedRadioButtonId() == -1) {
                    selMolas.setError("*");
                }
                if (radioCambioOleoOKNOK.getCheckedRadioButtonId() == -1) {
                    selCambioOleo.setError("*");
                }
                if (radioDirecaoOleoOKNOK.getCheckedRadioButtonId() == -1) {
                    selDirecaoOleo.setError("*");
                }
                if (radioLimpezaRadiadorAguaOKNOK.getCheckedRadioButtonId() == -1) {
                    selLimpezaRadiadorAgua.setError("*");
                }
                if (radioOleoMotorOKNOK.getCheckedRadioButtonId() == -1) {
                    selOleoMotor.setError("*");
                }
                if (radioRetrovisorOKNOK.getCheckedRadioButtonId() == -1) {
                    selRetrovisor.setError("*");
                }
                if (radioParaBrisaOKNOK.getCheckedRadioButtonId() == -1) {
                    selParaBrisa.setError("*");
                }
                if (radioParaChoqueDianteiroOKNOK.getCheckedRadioButtonId() == -1) {
                    selParaChoqueDianteiro.setError("*");
                }
                if (radioParaChoqueTraseiroOKNOK.getCheckedRadioButtonId() == -1) {
                    selParaChoqueTraseiro.setError("*");
                }
                if (radioEstofamentoOKNOK.getCheckedRadioButtonId() == -1) {
                    selEstofamento.setError("*");
                }
                if (radioCortinasOKNOK.getCheckedRadioButtonId() == -1) {
                    selCortinas.setError("*");
                }
                if (radioCintoDeSegurancaOKNOK.getCheckedRadioButtonId() == -1) {
                    selCintoDeSeguranca.setError("*");
                }
                if (radioFreioDeEstacionamentoOKNOK.getCheckedRadioButtonId() == -1) {
                    selFreioDeEstacionamento.setError("*");
                }
                return;
            }
            adicionaCheck();
        });
    }

    @NonNull
    private CheckList criaCheckList() {
        EditText data = findViewById(R.id.data);
        EditText hora = findViewById(R.id.hora);
        RadioButton selSaidaRetorno = findViewById(radioSaidaretorno.getCheckedRadioButtonId());
        EditText placa = findViewById(R.id.placa);
        EditText motorista = findViewById(R.id.motorista);
        EditText kmVeiculo = findViewById(R.id.kmveiculo);
        RadioButton selTracao = findViewById(radioTracaoOKNOK.getCheckedRadioButtonId());
        RadioButton selRodoar = findViewById(radioRodoarOKNOK.getCheckedRadioButtonId());
        RadioButton selCalibragemPneus = findViewById(radioCalibragemPneusOKNOK.getCheckedRadioButtonId());
        RadioButton selEstepe = findViewById(radioEstepeOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioDianteiro = findViewById(radioFreioDianteiroOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioTraseiro = findViewById(radioFreioTraseiroOKNOK.getCheckedRadioButtonId());
        RadioButton selAmortecedor = findViewById(radioAmortecedorOKNOK.getCheckedRadioButtonId());
        RadioButton selMolas = findViewById(radioMolasOKNOK.getCheckedRadioButtonId());
        RadioButton selCambioOleo = findViewById(radioCambioOleoOKNOK.getCheckedRadioButtonId());
        RadioButton selDirecaoOleo = findViewById(radioDirecaoOleoOKNOK.getCheckedRadioButtonId());
        RadioButton selLimpezaRadiadorAgua = findViewById(radioLimpezaRadiadorAguaOKNOK.getCheckedRadioButtonId());
        RadioButton selOleoMotor = findViewById(radioOleoMotorOKNOK.getCheckedRadioButtonId());
        RadioButton selRetrovisor = findViewById(radioRetrovisorOKNOK.getCheckedRadioButtonId());
        RadioButton selParaBrisa = findViewById(radioParaBrisaOKNOK.getCheckedRadioButtonId());
        RadioButton selParaChoqueDianteiro = findViewById(radioParaChoqueDianteiroOKNOK.getCheckedRadioButtonId());
        RadioButton selParaChoqueTraseiro = findViewById(radioParaChoqueTraseiroOKNOK.getCheckedRadioButtonId());
        RadioButton selEstofamento = findViewById(radioEstofamentoOKNOK.getCheckedRadioButtonId());
        RadioButton selCortinas = findViewById(radioCortinasOKNOK.getCheckedRadioButtonId());
        RadioButton selCintoDeSeguranca = findViewById(radioCintoDeSegurancaOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioDeEstacionamento = findViewById(radioFreioDeEstacionamentoOKNOK.getCheckedRadioButtonId());

        return new CheckList(data.getText().toString(),
                hora.getText().toString(),
                selSaidaRetorno.getText().toString(),
                placa.getText().toString(),
                motorista.getText().toString(),
                kmVeiculo.getText().toString(),
                selTracao.getText().toString(),
                selRodoar.getText().toString(),
                selCalibragemPneus.getText().toString(),
                selEstepe.getText().toString(),
                selFreioDianteiro.getText().toString(),
                selFreioTraseiro.getText().toString(),
                selAmortecedor.getText().toString(),
                selMolas.getText().toString(),
                selCambioOleo.getText().toString(),
                selDirecaoOleo.getText().toString(),
                selLimpezaRadiadorAgua.getText().toString(),
                selOleoMotor.getText().toString(),
                selRetrovisor.getText().toString(),
                selParaBrisa.getText().toString(),
                selParaChoqueDianteiro.getText().toString(),
                selParaChoqueTraseiro.getText().toString(),
                selEstofamento.getText().toString(),
                selCortinas.getText().toString(),
                selCintoDeSeguranca.getText().toString(),
                selFreioDeEstacionamento.getText().toString());
    }
}


