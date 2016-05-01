/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.classes;

import java.util.ArrayList;

/**
 *
 * @author harlock
 */
public class Email {
    private String titulo;
    private String conteudo;
    private String urlAnexo;
    private int curso;
    private int destino[];
    private int id_em;
    private ArrayList<Turma> turmasEnviadas;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    public ArrayList<Turma> getTurmasEnviadas() {
        return turmasEnviadas;
    }

    public void setTurmasEnviadas(ArrayList<Turma> turmasEnviadas) {
        this.turmasEnviadas = turmasEnviadas;
    }


    
    public int getId_em() {
        return id_em;
    }

    public void setId_em(int id_em) {
        this.id_em = id_em;
    }
    
    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int[] getDestinoTurma() {
        return destino;
    }

    public void setDestino(int[] destino) {
        this.destino = destino;
    }

    public String getUrlAnexo() {
        return urlAnexo;
    }

    public void setUrlAnexo(String urlAnexo) {
        this.urlAnexo = urlAnexo;
    }
    
    
}
