package hp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import hp.beans.Forum;
import hp.helpers.Helpers;

public class ForumDao implements Dao <Forum> {
	
private Connection connection;
	
	public ForumDao() {
		
		this.connection = DbUtil.getConnection(); 
	}

	@Override
	public void insert(Forum forum) {
		// TODO Auto-generated method stub

		
	}
	public void insertTopico(Forum forum) {
		// TODO Auto-generated method stub
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO hp_db.forum (cod_filho, descricao, cod_usuario, data_post) VALUES (0, ?, ?, now())");
            
            // Parameters start with 1
            preparedStatement.setString(1,forum.getDescricao());            
            preparedStatement.setString(2,Integer.toString(forum.getCod_Usuario()));               
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
		
	}

	@Override
	public void update(Forum forum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Forum forum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Forum find(Forum forum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Forum> findAll(Forum forum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Forum preencherEntidade(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
