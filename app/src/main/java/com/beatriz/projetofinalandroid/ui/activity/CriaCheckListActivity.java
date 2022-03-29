package com.beatriz.projetofinalandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
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

    public CheckList check = new CheckList();
    private CompositeSubscription subscription = new CompositeSubscription();

    private Button btnSalvar;
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
    private RadioGroup radioestofamentoOKNOK;
    private RadioGroup radiocortinasOKNOK;
    private RadioGroup radiocintoDeSegurancaOKNOK;
    private RadioGroup radiofreioDeEstacionamentoOKNOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_checklist);
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
            if (checkRecebido.getTracao().equals("OK")) {
                selTracaoOk.setChecked(true);
            } else {
                selTracaoNOk.setChecked(true);
            }

            RadioButton selRodoarOk = findViewById(R.id.RodoarOK);
            RadioButton selRodoarNOk = findViewById(R.id.RodoarNOK);
            selRodoarOk.setEnabled(false);
            selRodoarNOk.setEnabled(false);
            if (checkRecebido.getRodoar().equals("OK")) {
                selRodoarOk.setChecked(true);
            } else {
                selRodoarNOk.setChecked(true);
            }

            RadioButton selCalibragemOk = findViewById(R.id.CalibragemPneusOK);
            RadioButton selCalibragemNOk = findViewById(R.id.CalibragemPneusNOK);
            selCalibragemOk.setEnabled(false);
            selCalibragemNOk.setEnabled(false);
            if (checkRecebido.getCalibragemPneus().equals("OK")) {
                selCalibragemOk.setChecked(true);
            } else {
                selCalibragemNOk.setChecked(true);
            }

            RadioButton selEstepeOk = findViewById(R.id.EstepeOK);
            RadioButton selEstepeNOk = findViewById(R.id.EstepeNOK);
            selEstepeOk.setEnabled(false);
            selEstepeNOk.setEnabled(false);
            if (checkRecebido.getEstepe().equals("OK")) {
                selEstepeOk.setChecked(true);
            } else {
                selEstepeNOk.setChecked(true);
            }

            RadioButton selFreioDianteiroOk = findViewById(R.id.FreioDianteiroOK);
            RadioButton selFreioDianteiroNOk = findViewById(R.id.FreioDianteiroNOK);
            selFreioDianteiroOk.setEnabled(false);
            selFreioDianteiroNOk.setEnabled(false);
            if (checkRecebido.getFreioDianteiro().equals("OK")) {
                selFreioDianteiroOk.setChecked(true);
            } else {
                selFreioDianteiroNOk.setChecked(true);
            }

            RadioButton selFreioTraseiroOk = findViewById(R.id.FreioTraseiroOK);
            RadioButton selFreioTraseiroNOk = findViewById(R.id.FreioTraseiroNOK);
            selFreioTraseiroOk.setEnabled(false);
            selFreioTraseiroNOk.setEnabled(false);
            if (checkRecebido.getFreioTraseiro().equals("OK")) {
                selFreioTraseiroOk.setChecked(true);
            } else {
                selFreioTraseiroNOk.setChecked(true);
            }

            RadioButton selAmortecedorOK = findViewById(R.id.AmortcedorOK);
            RadioButton selAmortecedorNOk = findViewById(R.id.AmortecedorNOK);
            selAmortecedorOK.setEnabled(false);
            selAmortecedorNOk.setEnabled(false);
            if (checkRecebido.getAmortecedor().equals("OK")) {
                selAmortecedorOK.setChecked(true);
            } else {
                selAmortecedorNOk.setChecked(true);
            }

            RadioButton selMolasOk = findViewById(R.id.MolasOK);
            RadioButton selMolasNOk = findViewById(R.id.MolasNOK);
            selMolasOk.setEnabled(false);
            selMolasNOk.setEnabled(false);
            if (checkRecebido.getMolas().equals("OK")) {
                selMolasOk.setChecked(true);
            } else {
                selMolasNOk.setChecked(true);
            }

            RadioButton selCambioOLeoOk = findViewById(R.id.CambioOleoOK);
            RadioButton selCambioOLeoNOk = findViewById(R.id.CambioOleoNOK);
            selCambioOLeoOk.setEnabled(false);
            selCambioOLeoNOk.setEnabled(false);
            if (checkRecebido.getCambioOleo().equals("OK")) {
                selCambioOLeoOk.setChecked(true);
            } else {
                selCambioOLeoNOk.setChecked(true);
            }

            RadioButton selDirecaoOleoOk = findViewById(R.id.DirecaoOleoOK);
            RadioButton selDirecaoOleoNOk = findViewById(R.id.DirecaoOleoNOK);
            selDirecaoOleoOk.setEnabled(false);
            selDirecaoOleoNOk.setEnabled(false);
            if (checkRecebido.getDirecaoOleo().equals("OK")) {
                selDirecaoOleoOk.setChecked(true);
            } else {
                selDirecaoOleoNOk.setChecked(true);
            }

            RadioButton selLimpezaRadiadorAguaOk = findViewById(R.id.LimpezaRadiadorAguaOK);
            RadioButton selLimpezaRadiadorAguaNOk = findViewById(R.id.LimpezaRadiadorAguaNOK);
            selLimpezaRadiadorAguaOk.setEnabled(false);
            selLimpezaRadiadorAguaNOk.setEnabled(false);
            if (checkRecebido.getLimpezaRadiadorAgua().equals("OK")) {
                selLimpezaRadiadorAguaOk.setChecked(true);
            } else {
                selLimpezaRadiadorAguaNOk.setChecked(true);
            }

            RadioButton selOleoMotorOk = findViewById(R.id.OleoMotorOK);
            RadioButton selOleoMotorNOk = findViewById(R.id.OleoMotorNOK);
            selOleoMotorOk.setEnabled(false);
            selOleoMotorNOk.setEnabled(false);
            if (checkRecebido.getOleoMotor().equals("OK")) {
                selOleoMotorOk.setChecked(true);
            } else {
                selOleoMotorNOk.setChecked(true);
            }

            RadioButton selRetrovisorOk = findViewById(R.id.RetrovisorOK);
            RadioButton selRetrovisorNOk = findViewById(R.id.RetrovisorNOK);
            selRetrovisorOk.setEnabled(false);
            selRetrovisorNOk.setEnabled(false);
            if (checkRecebido.getRetrovisor().equals("OK")) {
                selRetrovisorOk.setChecked(true);
            } else {
                selRetrovisorNOk.setChecked(true);
            }

            RadioButton selParaBrisaOk = findViewById(R.id.paraBrisaOK);
            RadioButton selParaBrisaNOk = findViewById(R.id.paraBrisaNOK);
            selParaBrisaOk.setEnabled(false);
            selParaBrisaNOk.setEnabled(false);
            if (checkRecebido.getParaBrisa().equals("OK")) {
                selParaBrisaOk.setChecked(true);
            } else {
                selParaBrisaNOk.setChecked(true);
            }

            RadioButton selParaChoqueDianteiroOk = findViewById(R.id.paraChoqueDianteiroOK);
            RadioButton selParaChoqueDianteiroNOk = findViewById(R.id.paraChoqueDianteiroNOK);
            selParaChoqueDianteiroOk.setEnabled(false);
            selParaChoqueDianteiroNOk.setEnabled(false);
            if (checkRecebido.getParaChoqueDianteiro().equals("OK")) {
                selParaChoqueDianteiroOk.setChecked(true);
            } else {
                selParaChoqueDianteiroNOk.setChecked(true);
            }

            RadioButton selParaChoqueTraseiroOk = findViewById(R.id.paraChoqueTraseiroOK);
            RadioButton selParaChoqueTraseiroNOk = findViewById(R.id.paraChoqueTraseiroNOK);
            selParaChoqueTraseiroOk.setEnabled(false);
            selParaChoqueTraseiroNOk.setEnabled(false);
            if (checkRecebido.getParaChoqueTraseiro().equals("OK")) {
                selParaChoqueTraseiroOk.setChecked(true);
            } else {
                selParaChoqueTraseiroNOk.setChecked(true);
            }

            RadioButton selEstofamentoOk = findViewById(R.id.estofamentoOK);
            RadioButton selEstofamentoNOk = findViewById(R.id.estofamentoNOK);
            selEstofamentoOk.setEnabled(false);
            selEstofamentoNOk.setEnabled(false);
            if (checkRecebido.getEstofamento().equals("OK")) {
                selEstofamentoOk.setChecked(true);
            } else {
                selEstofamentoNOk.setChecked(true);
            }

            RadioButton selCortinasOk = findViewById(R.id.cortinasOK);
            RadioButton selCortinasNOk = findViewById(R.id.cortinasNOK);
            selCortinasOk.setEnabled(false);
            selCortinasNOk.setEnabled(false);
            if (checkRecebido.getCortinas().equals("OK")) {
                selCortinasOk.setChecked(true);
            } else {
                selCortinasNOk.setChecked(true);
            }

            RadioButton selCintoDeSegurancaOk = findViewById(R.id.cintoDeSegurancaOK);
            RadioButton selCintoDeSegurancaNOk = findViewById(R.id.cintoDeSegurancaNOK);
            selCintoDeSegurancaOk.setEnabled(false);
            selCintoDeSegurancaNOk.setEnabled(false);
            if (checkRecebido.getCintoDeSeguranca().equals("OK")) {
                selCintoDeSegurancaOk.setChecked(true);
            } else {
                selCintoDeSegurancaNOk.setChecked(true);
            }

            RadioButton selFreioDeEstacionamentoOk = findViewById(R.id.freioDeEstacionamentoOK);
            RadioButton selFreioDeEstacionamentoNOk = findViewById(R.id.freioDeEstacionamentoNOK);
            selFreioDeEstacionamentoOk.setEnabled(false);
            selFreioDeEstacionamentoNOk.setEnabled(false);
            if (checkRecebido.getFreioDeEstacionamento().equals("OK")) {
                selFreioDeEstacionamentoOk.setChecked(true);
            } else {
                selFreioDeEstacionamentoNOk.setChecked(true);
            }
            return;
        }
        setTitle("Novo CheckList");
    }

    public void adicionaCheck() {
        CheckList checkListCriado = criaCheckList();
        Observable<CheckList> observable = RestClient.getRetrofit()
                .create(CheckListService.class).salva(checkListCriado);
        subscription.add(observable
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
                }));
    }

    public void abrirDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Excluir Checklist");
        dialog.setMessage("Tem certeza que quer excluir este Checklist?");

        dialog.setCancelable(false);

        dialog.setIcon(android.R.drawable.ic_menu_delete);

        dialog.setPositiveButton("Sim",
                (dialogInterface, i) -> deleta()).setNegativeButton("Não", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        if (check.getId() >= 1) {
            getMenuInflater().inflate(R.menu.menu_remover, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.menu_remover, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        abrirDialog();
        return super.onOptionsItemSelected(item);
    }

    public void deleta() {
        Observable<Void> observable = RestClient.getRetrofit()
                .create(CheckListService.class).delete(check.getId());
        subscription.remove(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(CriaCheckListActivity.this, "Erro ao deletar " +
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Void unused) {
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        subscription = null;
        super.onDestroy();
    }

    private void BotaoSalvaChecklist() {
        btnSalvar = findViewById(R.id.btnSalvar);
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
        radioestofamentoOKNOK = findViewById(R.id.estofamentoOKNOK);
        radiocortinasOKNOK = findViewById(R.id.cortinasOKNOK);
        radiocintoDeSegurancaOKNOK = findViewById(R.id.cintoDeSegurancaOKNOK);
        radiofreioDeEstacionamentoOKNOK = findViewById(R.id.freioDeEstacionamentoOKNOK);

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
                        radioestofamentoOKNOK.getCheckedRadioButtonId() == -1 ||
                        radiocortinasOKNOK.getCheckedRadioButtonId() == -1 ||
                        radiocintoDeSegurancaOKNOK.getCheckedRadioButtonId() == -1 ||
                        radiofreioDeEstacionamentoOKNOK.getCheckedRadioButtonId() == -1) {

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
                    if (radioestofamentoOKNOK.getCheckedRadioButtonId() == -1) {
                        selEstofamento.setError("*");
                    }
                    if (radiocortinasOKNOK.getCheckedRadioButtonId() == -1) {
                        selCortinas.setError("*");
                    }
                    if (radiocintoDeSegurancaOKNOK.getCheckedRadioButtonId() == -1) {
                        selCintoDeSeguranca.setError("*");
                    }
                    if (radiofreioDeEstacionamentoOKNOK.getCheckedRadioButtonId() == -1) {
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
                (radioSaidaretorno.getCheckedRadioButtonId());
        EditText placa = findViewById
                (R.id.placa);
        EditText motorista = findViewById
                (R.id.motorista);
        EditText kmVeiculo = findViewById
                (R.id.kmveiculo);
        RadioButton selTracao = findViewById
                (radioTracaoOKNOK.getCheckedRadioButtonId());
        RadioButton selRodoar = findViewById
                (radioRodoarOKNOK.getCheckedRadioButtonId());
        RadioButton selCalibragemPneus = findViewById
                (radioCalibragemPneusOKNOK.getCheckedRadioButtonId());
        RadioButton selEstepe = findViewById
                (radioEstepeOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioDianteiro = findViewById
                (radioFreioDianteiroOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioTraseiro = findViewById
                (radioFreioTraseiroOKNOK.getCheckedRadioButtonId());
        RadioButton selAmortecedor = findViewById
                (radioAmortecedorOKNOK.getCheckedRadioButtonId());
        RadioButton selMolas = findViewById
                (radioMolasOKNOK.getCheckedRadioButtonId());
        RadioButton selCambioOleo = findViewById
                (radioCambioOleoOKNOK.getCheckedRadioButtonId());
        RadioButton selDirecaoOleo = findViewById
                (radioDirecaoOleoOKNOK.getCheckedRadioButtonId());
        RadioButton selLimpezaRadiadorAgua = findViewById
                (radioLimpezaRadiadorAguaOKNOK.getCheckedRadioButtonId());
        RadioButton selOleoMotor = findViewById
                (radioOleoMotorOKNOK.getCheckedRadioButtonId());
        RadioButton selRetrovisor = findViewById
                (radioRetrovisorOKNOK.getCheckedRadioButtonId());
        RadioButton selParaBrisa = findViewById
                (radioParaBrisaOKNOK.getCheckedRadioButtonId());
        RadioButton selParaChoqueDianteiro = findViewById
                (radioParaChoqueDianteiroOKNOK.getCheckedRadioButtonId());
        RadioButton selParaChoqueTraseiro = findViewById
                (radioParaChoqueTraseiroOKNOK.getCheckedRadioButtonId());
        RadioButton selEstofamento = findViewById
                (radioestofamentoOKNOK.getCheckedRadioButtonId());
        RadioButton selCortinas = findViewById
                (radiocortinasOKNOK.getCheckedRadioButtonId());
        RadioButton selCintoDeSeguranca = findViewById
                (radiocintoDeSegurancaOKNOK.getCheckedRadioButtonId());
        RadioButton selFreioDeEstacionamento = findViewById
                (radiofreioDeEstacionamentoOKNOK.getCheckedRadioButtonId());

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


