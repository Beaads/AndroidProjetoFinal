package com.beatriz.projetofinalandroid.model;

import java.io.Serializable;

public class CheckList implements Serializable {

    private String data;
    private String hora;
    private String saidaRetorno;
    private String placa;
    private String motorista;
    private String kmVeiculo;
    private String tracao;
    private String rodoar;
    private String calibragemPneus;
    private String estepe;
    private String freioDianteiro;
    private String freioTraseiro;
    private String amortecedor;
    private String molas;
    private String cambioOleo;
    private String direcaoOleo;
    private String limpezaRadiadorAgua;
    private String oleoMotor;
    private String retrovisor;
    private String paraBrisa;
    private String paraChoqueDianteiro;
    private String paraChoqueTraseiro;
    private String estofamento;
    private String cortinas;
    private String cintoDeSeguranca;
    private String freioDeEstacionamento;

    public CheckList(String data, String hora, String saidaRetorno, String placa,
                     String motorista, String kmVeiculo, String tracao, String rodoar,
                     String calibragemPneus, String estepe, String freioDianteiro,
                     String freioTraseiro, String amortecedor, String molas, String cambioOleo,
                     String direcaoOleo, String limpezaRadiadorAgua, String oleoMotor,
                     String retrovisor, String paraBrisa, String paraChoqueDianteiro,
                     String paraChoqueTraseiro, String estofamento, String cortinas,
                     String cintoDeSeguranca, String freioDeEstacionamento) {

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

    public CheckList() {
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getSaidaRetorno() {
        return saidaRetorno;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMotorista() {
        return motorista;
    }

    public String getKmVeiculo() {
        return kmVeiculo;
    }

    public String getTracao() {
        return tracao;
    }

    public String getRodoar() {
        return rodoar;
    }

    public String getCalibragemPneus() {
        return calibragemPneus;
    }

    public String getEstepe() {
        return estepe;
    }

    public String getFreioDianteiro() {
        return freioDianteiro;
    }

    public String getFreioTraseiro() {
        return freioTraseiro;
    }

    public String getAmortecedor() {
        return amortecedor;
    }

    public String getMolas() {
        return molas;
    }

    public String getCambioOleo() {
        return cambioOleo;
    }

    public String getDirecaoOleo() {
        return direcaoOleo;
    }

    public String getLimpezaRadiadorAgua() {
        return limpezaRadiadorAgua;
    }

    public String getOleoMotor() {
        return oleoMotor;
    }

    public String getRetrovisor() {
        return retrovisor;
    }

    public String getParaBrisa() {
        return paraBrisa;
    }

    public String getParaChoqueDianteiro() {
        return paraChoqueDianteiro;
    }

    public String getParaChoqueTraseiro() {
        return paraChoqueTraseiro;
    }

    public String getEstofamento() {
        return estofamento;
    }

    public String getCortinas() {
        return cortinas;
    }

    public String getCintoDeSeguranca() {
        return cintoDeSeguranca;
    }

    public String getFreioDeEstacionamento() {
        return freioDeEstacionamento;
    }
}



