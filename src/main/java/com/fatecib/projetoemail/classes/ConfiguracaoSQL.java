/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.classes;

/**
 *
 * @author harlock
 */
public class ConfiguracaoSQL {
    private String usuario;
    private String EMAILHOST;
    private String EMAILSMTP;
    private String EMAILPROVEDOR;
    private int PORTASMTP;
    private String SENHA;
    private String ASSINATURA;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEMAILHOST() {
        return EMAILHOST;
    }

    public void setEMAILHOST(String EMAILHOST) {
        this.EMAILHOST = EMAILHOST;
    }

    public String getEMAILSMTP() {
        return EMAILSMTP;
    }

    public void setEMAILSMTP(String EMAILSMTP) {
        this.EMAILSMTP = EMAILSMTP;
    }

    public String getEMAILPROVEDOR() {
        return EMAILPROVEDOR;
    }

    public void setEMAILPROVEDOR(String EMAILPROVEDOR) {
        this.EMAILPROVEDOR = EMAILPROVEDOR;
    }

    public int getPORTASMTP() {
        return PORTASMTP;
    }

    public void setPORTASMTP(int PORTASMTP) {
        this.PORTASMTP = PORTASMTP;
    }

    public String getSENHA() {
        return SENHA;
    }

    public void setSENHA(String SENHA) {
        this.SENHA = SENHA;
    }

    public String getASSINATURA() {
        return ASSINATURA;
    }

    public void setASSINATURA(String ASSINATURA) {
        this.ASSINATURA = ASSINATURA;
    }
    
    
}
