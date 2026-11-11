package ConexaoDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import Dto.ProdutosDTO;
import ConexaoDAO.conectaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java. sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prepare;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto(ProdutosDTO produto){
        conectaDAO conecta = new conectaDAO();
        conn = conecta.connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try{
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, produto.getNome());
            prepare.setInt(2, produto.getValor());
            prepare.setString(3, produto.getStatus());
            
            prepare.execute();
            return true;
            
        }catch(SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar no banco: " + erro.getMessage());
            return false;
            
        }finally{
            try{ if(prepare != null) prepare.close(); 
        }catch(Exception e){}
            try{ if(conn != null) conn.close(); } catch (Exception e) {}
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = "SELECT * FROM produtos";
        conectaDAO conecta = new conectaDAO();
        conn = conecta.connectDB();
        
        try{
            prepare = conn.prepareStatement(sql);
            resultset = prepare.executeQuery();
            
            listagem.clear();
            
            while (resultset.next()){
                
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
    }finally {
        try{ if (resultset != null) resultset.close();} catch(Exception e) {}
        try{ if (prepare != null) prepare.close();} catch(Exception e) {}
        try{ if(conn != null) conn.close();}catch(Exception e){}
        
         }
          return listagem;
    }
    
  
}
    
    
    
    
        


