package com.beatriz.projetofinalandroid.model;

import java.io.Serializable;

public class CheckList implements Serializable {

    //cabeçalho
    private int id;
    private String data;
    private String hora;
    private boolean saidaRetorno;
    private String placa;
    private String motorista;
    private double kmVeiculo;

    //infos booleanas
    //Pneus do veiculo
    private boolean tracao;
    private boolean rodoar;
    private boolean calibragemPneus;
    private boolean estepe;

    //Freios e suspensao
    private boolean freioDianteiro;
    private boolean freioTraseiro;
    private boolean amortecedor;
    private boolean molas;

    //motor
    private boolean cambioOleo;
    private boolean direcaoOleo;
    private boolean limpezaRadiadorAgua;
    private boolean oleoMotor;

    //parte externa
    private boolean retrovisor;
    private boolean paraBrisa;
    private boolean paraChoqueDianteiro;
    private boolean paraChoqueTraseiro;

    //parte interna
    private boolean estofamento;
    private boolean cortinas;
    private boolean cintoDeSeguranca;
    private boolean freioDeEstacionamento;

    public CheckList(String toString, String data, String hora, boolean saidaRetorno, String placa, String motorista) {
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public boolean isSaidaRetorno() {
        return saidaRetorno;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMotorista() {
        return motorista;
    }

    public double getKmVeiculo() {
        return kmVeiculo;
    }

    public boolean isTracao() {
        return tracao;
    }

    public boolean isRodoar() {
        return rodoar;
    }

    public boolean isCalibragemPneus() {
        return calibragemPneus;
    }

    public boolean isEstepe() {
        return estepe;
    }

    public boolean isFreioDianteiro() {
        return freioDianteiro;
    }

    public boolean isFreioTraseiro() {
        return freioTraseiro;
    }

    public boolean isAmortecedor() {
        return amortecedor;
    }

    public boolean isMolas() {
        return molas;
    }

    public boolean isCambioOleo() {
        return cambioOleo;
    }

    public boolean isDirecaoOleo() {
        return direcaoOleo;
    }

    public boolean isLimpezaRadiadorAgua() {
        return limpezaRadiadorAgua;
    }

    public boolean isOleoMotor() {
        return oleoMotor;
    }

    public boolean isRetrovisor() {
        return retrovisor;
    }

    public boolean isParaBrisa() {
        return paraBrisa;
    }

    public boolean isParaChoqueDianteiro() {
        return paraChoqueDianteiro;
    }

    public boolean isParaChoqueTraseiro() {
        return paraChoqueTraseiro;
    }

    public boolean isEstofamento() {
        return estofamento;
    }

    public boolean isCortinas() {
        return cortinas;
    }

    public boolean isCintoDeSeguranca() {
        return cintoDeSeguranca;
    }

    public boolean isFreioDeEstacionamento() {
        return freioDeEstacionamento;
    }

    public CheckList(int id, String data, String hora, boolean saidaRetorno, String placa, String motorista,
                     double kmVeiculo, boolean tracao, boolean rodoar, boolean calibragemPneus, boolean estepe,
                     boolean freioDianteiro, boolean freioTraseiro, boolean amortecedor, boolean molas, boolean cambioOleo,
                     boolean direcaoOleo, boolean limpezaRadiadorAgua, boolean oleoMotor, boolean retrovisor,
                     boolean paraBrisa, boolean paraChoqueDianteiro, boolean paraChoqueTraseiro, boolean estofamento,
                     boolean cortinas, boolean cintoDeSeguranca, boolean freioDeEstacionamento) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.saidaRetorno = saidaRetorno;
        this.placa = placa;
        this.motorista = motorista;
        this.kmVeiculo = kmVeiculo;
        this.tracao = tracao;
        this.rodoar = rodoar;
        this.calibragemPneus = calibragemPneus;
        this.estepe = estepe;
        this.freioDianteiro = freioDianteiro;
        this.freioTraseiro = freioTraseiro;
        this.amortecedor = amortecedor;
        this.molas = molas;
        this.cambioOleo = cambioOleo;
        this.direcaoOleo = direcaoOleo;
        this.limpezaRadiadorAgua = limpezaRadiadorAgua;
        this.oleoMotor = oleoMotor;
        this.retrovisor = retrovisor;
        this.paraBrisa = paraBrisa;
        this.paraChoqueDianteiro = paraChoqueDianteiro;
        this.paraChoqueTraseiro = paraChoqueTraseiro;
        this.estofamento = estofamento;
        this.cortinas = cortinas;
        this.cintoDeSeguranca = cintoDeSeguranca;
        this.freioDeEstacionamento = freioDeEstacionamento;


    }
}


