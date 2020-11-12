package hp.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import hp.beans.Venda;
import java.sql.Connection;
import hp.model.DbUtil;
import java.util.Date;
import hp.helpers.Helpers;


public class VendaDao implements Dao<Venda> {

	private Connection connection;
	
	public VendaDao() {
		
		this.connection = DbUtil.getConnection(); 
	}
		
	@Override
	public void insert(Venda venda) {
		// TODO Auto-generated method stub
		
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO hp_db.vendas (cod_usuario, data_venda, total) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1,Integer.toString(venda.getCod_Usuario()));
            preparedStatement.setString(2,Helpers.DateToString(venda.getData_Venda()));
            preparedStatement.setString(3, Float.toString(venda.getTotal()));     
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
	}

	@Override
	public void update(Venda obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Venda obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Venda find(Venda obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Venda> findAll(Venda obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venda preencherEntidade(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
