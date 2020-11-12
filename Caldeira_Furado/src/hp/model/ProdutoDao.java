package hp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import hp.model.DbUtil;

import hp.beans.Produto;

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
                    .prepareStatement("INSERT INTO hp_db.produtos (descricao, valor) VALUES (?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setString(2, Float.toString(produto.getValor()));                 
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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
	public Produto find(Produto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produto> findAll(Produto obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto preencherEntidade(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
