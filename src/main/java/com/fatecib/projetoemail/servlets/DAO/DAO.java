/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.servlets.DAO;

/**
 *
 * @author harlock
 */
import com.fatecib.projetoemail.classes.Adminstrador;
import com.fatecib.projetoemail.classes.Aluno;
import com.fatecib.projetoemail.classes.ConfiguracaoSQL;
import com.fatecib.projetoemail.classes.Cursos;
import com.fatecib.projetoemail.classes.Email;
import com.fatecib.projetoemail.classes.InfoSis;
import com.fatecib.projetoemail.classes.Turma;
import com.fatecib.projetoemail.conexao.Conexao;
import com.fatecib.projetoemail.leitorCSV.CSVReader;
import com.fatecib.projetoemail.servlets.EnviarEmailServlet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//
///**
// *
// * @author harlock
// */

public class DAO {

    // Pega conexão
    private Connection conectar = null;
    // Classe para email
    Email email;
    // Clasee para turma
    Turma turma;
    // Classe para Cusos
    Cursos cursos;
    // Array para listar curso
    String[][] lista;
    //Envia o email via API
    // Retorna Conexão;
    ConfiguracaoSQL c;

    public DAO() {
        conectar = Conexao.getConexao();
    }

    public void adicionarTurma(Turma turma) throws SQLException, Exception {
        try {
            String comando = "INSERT INTO TURMAS (NUMERO, FK_CUR, DATA_INICIO, DATA_FIM,periodo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setInt(1, Integer.parseInt(turma.getNumero()));
            ps.setInt(2, turma.getIdCurso());

            String data[] = turma.getDataInicio().split("/");
            String nova = data[2] + "-" + data[1] + "-" + data[0];
            turma.setDataInicio(nova);

            String dataF[] = turma.getDataFim().split("/");
            String novaF = dataF[2] + "-" + dataF[1] + "-" + dataF[0];
            turma.setDataFim(novaF);

            ps.setString(3, turma.getDataInicio());
            ps.setString(4, turma.getDataFim());
            ps.setString(5, turma.getPerido());
            ps.executeUpdate();
            ps.close();
//        comando = "SELECT ID_TUR FROM TURMAS WHERE NUMERO = ?";
//        ps = conectar.prepareStatement(comando);
//        ps.setInt(1, Integer.parseInt(turma.getNumero()));
//        ResultSet rs = ps.executeQuery();
//        rs.next();

//            adicionaAluno(turma.getUrlCSV(), rs.getInt(1));
//            rs.close();
        } catch (Exception ex) {
            throw new Exception("Erro ao adicionar essa turma");
        }
    }

    public void adicionarCursos(Cursos curso) throws SQLException, java.lang.Exception {
        String comando = "insert into CURSOS(NOME)VALUES(?)";
        PreparedStatement ps = conectar.prepareStatement(comando);
        ps.setString(1, curso.getNome());
        ps.executeUpdate();
        ps.close();
        try {
            
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao adicionar um curso " + e);
        }
    }
    public void updateCurso(Cursos c) throws java.lang.Exception{
        try {
            String comando = "UPDATE CURSOS SET NOME = ? WHERE ID_CUR = ?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setString(1, c.getNome());
            ps.setInt(2, c.getIdCurso());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao excluir um curso " + e);
        }
    }
    public Cursos retornaUmCurso(int id) throws java.lang.Exception{
        try {
            String comando = "SELECT * FROM CURSOS WHERE ID_CUR = ?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Cursos c = new Cursos();
            c.setIdCurso(rs.getInt("ID_CUR"));
            c.setNome(rs.getString("NOME"));
            return c;
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao adicionar um curso " + e);
        }
    }
    public void deletaCurso(int id) throws java.lang.Exception {
        try {
            String comando = "DELETE FROM CURSOS WHERE ID_CUR=?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao excluir um curso " + e);
        }
    }

    private void adicionaAluno(String urlCSV, int turma) throws SQLException, Exception {
        CSVReader leia = new CSVReader();
        ArrayList lista = leia.listacsv(urlCSV);
        String comando = "INSERT INTO ALUNOS(NOME,EMAIL,FK_TURMA)VALUES(?,?,?)";
        PreparedStatement ps = conectar.prepareStatement(comando);
        for (int i = 0; i < lista.size(); i++) {
            String[][] temp = (String[][]) lista.get(i);
            ps.setString(1, temp[0][0]);
            ps.setString(2, temp[0][1]);
            ps.setInt(3, turma);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    public void updateAluno(Aluno al) throws Exception {
        try {
            String comando = "UPDATE ALUNOS SET NOME = ? , EMAIL = ? WHERE ID_ALU = ?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setString(1, al.getNome());
            ps.setString(2, al.getEmail());
            ps.setInt(3, al.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao buscar as informações desse aluno");
        }
    }

    public void excluirAluno(Aluno al) throws java.lang.Exception {

        try {
            String comando = "DELETE FROM ALUNOS WHERE ID_ALU=?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setInt(1, al.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw Exception("Algo deu errado ao excluir esse aluno");
        }
    }


    @SuppressWarnings("empty-statement")
    public void emailEnviar(Email email) throws SQLException {
        try {
             String comando = "INSERT INTO EMAILENVIADOS (TITULO, CORPO, urlAnexo) VALUES (?, ?, ?)";
             PreparedStatement ps = conectar.prepareStatement(comando);
             ps.setString(1, email.getTitulo());
             ps.setString(2, email.getConteudo());
             ps.setString(3, email.getUrlAnexo());
             ps.executeUpdate();
             ps.close();
             comando = "SELECT ID_EM FROM EMAILENVIADOS WHERE TITULO = ? ORDER BY ID_EM DESC";
             ps = conectar.prepareStatement(comando);
             ps.setString(1, email.getTitulo());
             ResultSet rs = ps.executeQuery();
             rs.next();
             insereEmailRel(email, rs.getInt(1));
             Enviaremail2 enviar =  new Enviaremail2();
             int is[] = email.getDestinoTurma();
             for(int i = 0; i < is.length; i++){ 
             enviar.setEnviarAlunos(retornaAlunos(is[i]), email, retornaConf());
             }
             

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Algo deu errado!" + e);
        }

    }
    private void insereEmailRel(Email email, int id) throws java.lang.Exception{
        try {
            String comando ="INSERT INTO relEmail (fk_id_em, fk_turma) VALUES (?, ?)";
            PreparedStatement ps = conectar.prepareStatement(comando);
            int em[] = email.getDestinoTurma();
        for (int i = 0; i < email.getDestinoTurma().length; i++) {
            ps.setInt(1, id);
            ps.setInt(2, em[i]);
            // ...
            ps.addBatch();
            }
            ps.executeBatch(); 
            ps.close();

        } catch (Exception e) {
            throw new Exception("Algo deu errado na hora de criar a relação de email"+e);
        }
    }

    public ArrayList<Turma> listarturmas() throws SQLException, Exception {
        try {
            ArrayList<Turma> lista = new ArrayList<Turma>();
            String comando = "SELECT T.NUMERO,T.DATA_INICIO,T.DATA_FIM,T.PERIODO, C.NOME, T.Id_tur FROM TURMAS AS T INNER JOIN CURSOS AS C ON T.FK_CUR=C.ID_CUR ORDER BY T.ID_TUR DESC";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                turma = new Turma();
                turma.setNumero(rs.getString("NUMERO"));
                turma.setNomeCurso(rs.getString("NOME"));
                turma.setIdTur(rs.getInt("Id_tur"));
                turma.setPerido(rs.getString("periodo"));
                String dataI = rs.getString("data_inicio");
                String dataII[] = dataI.split("-");
                dataI = dataII[2] + "/" + dataII[1] + "/" + dataII[0];
                turma.setDataInicio(dataI);
                String dataF = rs.getString("data_fim");
                String dataFF[] = dataF.split("-");
                dataF = dataFF[2] + "/" + dataFF[1] + "/" + dataFF[0];
                turma.setDataFim(dataF);
                lista.add(i++, turma);
            }
            rs.close();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro ao retornar turmas");
        }

    }

    public ArrayList<Turma> listarturmasFiltro(int idCurso) throws SQLException {

        ArrayList<Turma> lista = new ArrayList<Turma>();
        String comando = "SELECT T.NUMERO, C.NOME FROM TURMAS AS T INNER JOIN CURSOS AS C ON T.FK_CUR=C.ID_CUR where C.ID_CUR = ? ORDER BY T.id_tur DESC";
        PreparedStatement ps = conectar.prepareStatement(comando);
        ps.setInt(1, idCurso);
        ResultSet rs = ps.executeQuery();
        int i = 0;
        while (rs.next()) {
            turma = new Turma();
            turma.setNumero(rs.getString("NUMERO"));
            turma.setNomeCurso(rs.getString("NOME"));
            lista.add(i++, turma);
        }
        rs.close();
        return lista;
    }

    public ArrayList<Cursos> listarCursos() throws SQLException {
        String comando = "SELECT (SELECT COUNT(ID_CUR) from CURSOS) AS ARRAY ,NOME,ID_CUR FROM CURSOS";
        PreparedStatement ps = conectar.prepareStatement(comando);
        ResultSet rs = ps.executeQuery();
        int i = 0;
        ArrayList<Cursos> lista = new ArrayList<Cursos>();
        while (rs.next()) {
            cursos = new Cursos();
            cursos.setIdCurso(rs.getInt("ID_CUR"));
            cursos.setNome(rs.getString("NOME"));
            lista.add(i++, cursos);
        }
        rs.close();
        return lista;
    }

    public ArrayList<Email> listarEmail() throws SQLException, java.lang.Exception {
        try {
            ArrayList<Email> lista = new ArrayList<Email>();
            String comando = "SELECT * FROM EMAILENVIADOS";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {                
                Email em = new Email();
                em.setTitulo(rs.getString("TITULO"));
                em.setConteudo(rs.getString("CORPO"));
                //
                
                em.setId_em(rs.getInt("ID_EM"));
                em.setData(rs.getString("DATETIME"));
                //
                em.setTurmasEnviadas(retornaListaDeTurmasDeEmailsEnviados(em.getId_em()));
                lista.add(i++, em);
            }
                    
            return lista;
        } catch (Exception e) {
            
            throw new Exception("Algo deu errado ao listar os emails"+e);
        }
    }
    private ArrayList<Turma> retornaListaDeTurmasDeEmailsEnviados(int i) throws java.lang.Exception{
        try {
            ArrayList<Turma> lista = new ArrayList<Turma>();
            String comando = "SELECT re.fk_id_em, re.fk_turma FROM EMAILENVIADOS EM INNER JOIN relEmail re ON re.fk_id_em = EM.ID_EM INNER JOIN ALUNOS AL ON AL.FK_TURMA = re.fk_turma WHERE re.fk_id_em = ? ";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            int cout=0;
            while (rs.next()) {                
                retornaUmaTurma(Integer.parseInt(rs.getString("fk_turma")));
                lista.add(cout++, turma);
            }
            return lista;
        } catch (Exception e) {
             throw new Exception("Algo deu errado ao listar as turmas dos emails enviados"+e);
        }
    }

    //Pega Configuração do software
    public void salvaConf(ConfiguracaoSQL c) throws SQLException {
        String comando = "UPDATE mail.CONFIGURACAO "
                + "SET USUARIO = ?, EMAILHOST = ?, EMAILSMTP = ?, "
                + "EMAILPROVEDOR = ?, PORTASMTP = ?, SENHA = ?, ASSINATURA = ? "
                + "WHERE CONFIGURACAO.ID_CONF = 1";
        PreparedStatement ps = conectar.prepareStatement(comando);
        int i = 1;
        ps.setString(i, c.getUsuario());
        ps.setString(i++, c.getSENHA());
        ps.setString(i++, c.getEMAILHOST());
        ps.setString(i++, c.getEMAILSMTP());
        ps.setInt(i++, c.getPORTASMTP());
        ps.setString(i++, c.getASSINATURA());
        ps.executeUpdate();
        ps.close();

    }

    public ConfiguracaoSQL retornaConf() throws SQLException {
        c = new ConfiguracaoSQL();
        String comando = "SELECT * FROM CONFIGURACAO where ID_CONF = 1";
        PreparedStatement ps = conectar.prepareStatement(comando);
        ResultSet rs = ps.executeQuery();
        rs.next();
        c.setUsuario(rs.getString("USUARIO"));
        c.setSENHA(rs.getString("SENHA"));
        c.setEMAILHOST(rs.getString("EMAILHOST"));
        c.setEMAILSMTP(rs.getString("EMAILSMTP"));
        c.setASSINATURA(rs.getString("ASSINATURA"));
        c.setPORTASMTP(rs.getInt("PORTASMTP"));
        return c;

    }

    public Adminstrador retornaAdminstrador(Adminstrador adm) throws SQLException, Exception {
        try {
            String comando = "SELECT * FROM ADMINSTRADOR WHERE senh = ?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setString(1, adm.getSenha());
            ResultSet rs = ps.executeQuery();
            Adminstrador ad = new Adminstrador();
            while (rs.next()) {
                ad.setId(rs.getInt("id"));
                ad.setNome(rs.getString("nome"));
                ad.setEmail(rs.getString("email"));
            }
            return ad;
        } catch (Exception e) {
            throw new Exception("Seu usuário não está cadastrado ou não foi encontrado");
        }

    }

    public InfoSis infoSistema() throws Exception {
        try {
            String comando = "SELECT count(id_alu) from ALUNOS";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ResultSet rs = ps.executeQuery();
            InfoSis in = new InfoSis();
            if (rs.next()) {
                in.setAlunos(rs.getInt(1));
                rs.close();
            }
            comando = "SELECT count(id_tur) from TURMAS";
            ps = conectar.prepareStatement(comando);
            ps.executeQuery();
            rs = ps.executeQuery();
            if (rs.next()) {
                in.setTurmas(rs.getInt(1));
                rs.close();
            }
            comando = "SELECT count(id_em) from EMAILENVIADOS";
            ps = conectar.prepareStatement(comando);
            ps.executeQuery();
            rs = ps.executeQuery();
            if (rs.next()) {
                in.setEmails(rs.getInt(1));
                rs.close();
            }
            comando = "SELECT count(id_cur) from CURSOS";
            ps = conectar.prepareStatement(comando);
            ps.executeQuery();
            rs = ps.executeQuery();
            if (rs.next()) {
                in.setCursos(rs.getInt(1));
                rs.close();
            }

            return in;
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao retornar as informações do sistema");
        }
    }

    public Turma retornaUmaTurma(int id) throws Exception {
        try {
            String comando = "SELECT T.NUMERO,T.DATA_INICIO,T.DATA_FIM,T.PERIODO, C.NOME,C.ID_CUR, T.Id_tur FROM TURMAS AS T INNER JOIN CURSOS AS C ON T.FK_CUR=C.ID_CUR where T.ID_TUR = ?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            turma = new Turma();
            turma.setIdCurso(rs.getInt("ID_CUR"));
            turma.setNumero(rs.getString("NUMERO"));
            turma.setNomeCurso(rs.getString("NOME"));
            turma.setIdTur(rs.getInt("Id_tur"));
            turma.setPerido(rs.getString("periodo"));
            String dataI = rs.getString("data_inicio");
            String dataII[] = dataI.split("-");
            dataI = dataII[2] + "/" + dataII[1] + "/" + dataII[0];
            turma.setDataInicio(dataI);
            String dataF = rs.getString("data_fim");
            String dataFF[] = dataF.split("-");
            dataF = dataFF[2] + "/" + dataFF[1] + "/" + dataFF[0];
            turma.setDataFim(dataF);
            rs.close();
            return turma;

        } catch (Exception e) {
            throw new Exception("Erro ao retornar a turma");
        }
    }

    public void updateUmaTurma(Turma t) throws Exception {
        try {
            String comando = "UPDATE INTO TURMAS (NUMERO, FK_CUR, DATA_INICIO, DATA_FIM, periodo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conectar.prepareStatement(comando);

            String data[] = t.getDataInicio().split("/");
            String nova = data[2] + "-" + data[1] + "-" + data[0];
            t.setDataInicio(nova);

            String dataF[] = t.getDataFim().split("/");
            String novaF = dataF[2] + "-" + dataF[1] + "-" + dataF[0];
            t.setDataFim(novaF);

            ps.setString(1, t.getNumero());
            ps.setInt(2, t.getIdCurso());
            ps.setString(3, t.getDataInicio());
            ps.setString(4, t.getDataFim());
            ps.setString(5, t.getPerido());
            ps.executeQuery();
            ps.close();
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao fazer o update dessa turma, cheque os dados e tente novamente.");
        }

    }

    public ArrayList<Aluno> retornaAlunos(int id) throws Exception {
        try {
            ArrayList<Aluno> lista = new ArrayList<Aluno>();
            String comando = "SELECT * FROM ALUNOS WHERE FK_TURMA = ?";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Aluno al = new Aluno();
            int i = 0;
            while (rs.next()) {
                al.setId(rs.getInt("ID_ALU"));
                al.setNome(rs.getString("NOME"));
                al.setEmail(rs.getString("EMAIL"));
                lista.add(i++, al);
            }
            rs.close();
            return lista;

        } catch (Exception e) {
            throw new Exception("Algo deu errado ao retornar os emails dessa turma");
        }
    }

    public void cadastrarAluno(Aluno al, int id) throws Exception {
        try {
            String comando = "INSERT INTO ALUNOS (NOME, EMAIL, FK_TURMA)VALUES (?, ?, ?);";
            PreparedStatement ps = conectar.prepareStatement(comando);
            ps.setString(1, al.getNome());
            ps.setString(2, al.getEmail());
            ps.setInt(3, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao cadastrar esse aluno.");
        }
    }

    private Exception Exception(String algo_deu_errado_ao_excluir_esse_aluno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
