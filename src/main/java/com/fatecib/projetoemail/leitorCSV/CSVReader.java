/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.leitorCSV;

/**
 *
 * @author harlock
 */

import com.fatecib.projetoemail.classes.Aluno;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public ArrayList<Aluno> listacsv(String urlcsv) throws Exception {

        String csvFile = urlcsv;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            ArrayList<Aluno> lista = new ArrayList<Aluno>();
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                int i = 0;
                Aluno al = new Aluno();
                // use comma as separator
                String[] email = line.split(cvsSplitBy);
                al.setNome(email[0]);
                al.setEmail(email[1]);
                lista.add(i++,al);
                

            }
            return lista;

        } catch (FileNotFoundException e) {
            throw new Exception("Algo deu errado ao inserir os emails!");
        } 

    }

}
//public class CSVReader {
//    
//    public ArrayList listacsv(String urlcsv)throws Exception{
//         ArrayList lista = new ArrayList();
//        try {
//			
//			CsvReader alunos = new CsvReader(urlcsv);
//		
//			alunos.readHeaders();
//
//			while (alunos.readRecord())
//			{
//                                String[][] dados = new String[1][2];
//				dados[0][0]= alunos.get("NOME");
//                                dados[0][1] = alunos.get("EMAIL");
//                                lista.add(dados);
//                                
//			}
//			alunos.close();
//			return lista;
//		} catch (FileNotFoundException e) {
//			throw new Exception("Não foi selecionando nehum arquivo.");
//		} 
//    }
//    
//}
