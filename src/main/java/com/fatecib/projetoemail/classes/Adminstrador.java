/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.classes;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author harlock
 */
public class Adminstrador {
    private int id;
    private String nome;
    private String senha;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() throws NoSuchAlgorithmException {
       MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(senha.getBytes(),0,senha.length());
       this.senha = new BigInteger(1,m.digest()).toString(16);
      return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
