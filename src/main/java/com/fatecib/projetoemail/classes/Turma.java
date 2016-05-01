/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.classes;

import java.sql.Date;

/**
 *
 * @author harlock
 */
public class Turma {
    private String numero;
    private int idCurso;
    private String nomeCurso;
    private String urlCSV;
    private String dataInicio;
    private String dataFim;
    private String perido;
    private int idTur;
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getUrlCSV() {
        return urlCSV;
    }

    public void setUrlCSV(String urlCSV) {
        this.urlCSV = urlCSV;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {

        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {

        this.dataFim = dataFim;
    }

    public String getPerido() {
        return perido;
    }

    public void setPerido(String perido) {
        this.perido = perido;
    }

    public int getIdTur() {
        return idTur;
    }

    public void setIdTur(int idTur) {
        this.idTur = idTur;
    }
    
    
}
