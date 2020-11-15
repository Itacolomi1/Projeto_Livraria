package hp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import hp.model.DbUtil;

import hp.beans.Produto;
import hp.beans.Usuario;

public class ProdutoDao implements Dao<Produto>{

	private Connection connection;
	
	public ProdutoDao() {		
		this.connection = DbUtil.getConnection();
	}
	
	@Override
	public void insert(Produto produto) {
		// TODO Auto-generated method stub
		
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO hp_db.produtos (cod_livro,descricao, valor) VALUES (?,?,?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, produto.getCod_Livro());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setString(3, Float.toString(produto.getValor()));                 
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
		
	}
	
	public int insert_id(Produto produto) {
		// TODO Auto-generated method stub
		int retorno = 0;
		Produto exist = find(produto); 
		
		
		if(exist == null) {			
			
			try {
	            PreparedStatement preparedStatement = connection
	                    .prepareStatement("INSERT INTO hp_db.produtos (cod_livro,descricao, valor) VALUES (?,?,?)");
	            
	            // Parameters start with 1
	            preparedStatement.setString(1, produto.getCod_Livro());
	            preparedStatement.setString(2, produto.getDescricao());
	            preparedStatement.setString(3, Float.toString(produto.getValor()));                 
	            
	            preparedStatement.executeUpdate();
	            
	            retorno = getlastId();
	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
			
			return retorno;
			
		}else {			
			retorno = exist.getCod_Produto();
			return retorno;
		}			
		
	}

	@Override
	public void update(Produto obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Produto obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produto find(Produto produto) {
		// TODO Auto-generated method stub
		
		Produto retorno = null;
		try {
			PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from hp_db.produtos WHERE cod_livro= ?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                            ResultSet.CONCUR_UPDATABLE);
			
			preparedStatement.setString(1, produto.getCod_Livro());
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.first()) {
				retorno = preencherEntidade(rs);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		return retorno;	
	}

	@Override
	public ArrayList<Produto> findAll(Produto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto preencherEntidade(ResultSet rs) throws SQLException {
		
		
		Produto produto = new Produto();
		produto.setCod_Produto(rs.getInt("cod_produto"));
			
		
		return produto;
	}
	
	
	private int getlastId() {
		int id = 0;
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select max(cod_produto) as id from hp_db.produtos",ResultSet.TYPE_SCROLL_SENSITIVE, 
                            ResultSet.CONCUR_UPDATABLE);        
            
            ResultSet rs = preparedStatement.executeQuery();
			if(rs.first()) {				
				id = rs.getInt("id");
			}

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
		return id;
		
	}
	

}
