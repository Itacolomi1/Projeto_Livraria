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
                    .prepareStatement("INSERT INTO hp_db.vendas (cod_venda,cod_usuario, data_venda, total) VALUES (0,?, now(), ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1,Integer.toString(venda.getCod_Usuario()));
            //preparedStatement.setString(2,Helpers.DateToString(venda.getData_Venda()));
            preparedStatement.setString(2, Float.toString(venda.getTotal()));     
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
	}	
	
	public int insert_id(Venda venda) {
		// TODO Auto-generated method stub
		 int retorno =0;
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO hp_db.vendas (cod_venda,cod_usuario, data_venda, total) VALUES (0,?, now(), ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1,Integer.toString(venda.getCod_Usuario()));
            //preparedStatement.setString(2,Helpers.DateToString(venda.getData_Venda()));
            preparedStatement.setString(2, Float.toString(venda.getTotal()));    
            
            preparedStatement.executeUpdate();
            
            retorno = getlastId();
            

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
		return retorno;
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
	
	private int getlastId() {
		int id = 0;
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select max(cod_venda) as id from hp_db.vendas",ResultSet.TYPE_SCROLL_SENSITIVE, 
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
