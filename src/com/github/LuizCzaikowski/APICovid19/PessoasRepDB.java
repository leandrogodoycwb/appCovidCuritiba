package com.github.LuizCzaikowski.APICovid19;

import casoschecker.FactoryJBDC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saulo Jr
 */
public class PessoasRepDB implements RepositoryInterface<Integer, Pessoas> {

    @Override
    public List<Pessoas> all() {
        List<Pessoas> lista = new ArrayList<>();

        try (Connection connec = FactoryJBDC.conexao()) {
            String sql = "SELECT * FROM Pessoas";
            Statement statement = connec.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            while (resultado.next()) {
                Pessoas p = new Pessoas(
                        resultado.getLong("Posicao"),
                        resultado.getString("DataInclusao"),
                        resultado.getString("Classificacao"),
                        resultado.getInt("Idade"),
                        resultado.getString("Sexo"),
                        resultado.getString("Encerramento"),
                        resultado.getString("DataObito")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoasRepDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void Media() {
        try (Connection connec = FactoryJBDC.conexao()) {
            String sql = "select avg(idade) as 'Idade' from Pessoas where Encerramento like '�BITO CONF'";
            Statement st = connec.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println("A média da idade dos óbitos era " + rs.getFloat("Idade"));
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void Moda(Pessoas entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    public void Insert(PessoasRep entidade) {
        try (Connection connec = FactoryJBDC.conexao()) {
            for(Pessoas p : entidade.lista){
            String sql = "INSERT INTO Pessoas (Posicao, DataInclusao, Classidicacao, Idade, Sexo, Encerramento, DataObito) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connec.prepareStatement(sql);
            ps.setLong(1, p.getPosicao());
            ps.setString(2, p.getDataInclude());
            ps.setString(3, p.getClassificacao());
            ps.setInt(4, p.getIdade());
            ps.setString(5, p.getSexo());
            ps.setString(6, p.getEncerramento());
            ps.setString(7, p.getDataObito().isEmpty() ? "" : p.getDataObito());
            ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DezDesc() {
   try (Connection connec = FactoryJBDC.conexao()) {
            String sql = "select DataObito, count(DataObito) as cnt from Pessoas where DataObito <> '' group by DataObito  order by cnt desc limit 10;";
            Statement st = connec.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println("No dia " + rs.getString("DataObito") + " foram confirmados " + rs.getInt("cnt") + " óbitos");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }

}
