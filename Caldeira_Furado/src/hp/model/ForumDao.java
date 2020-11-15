package hp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import hp.beans.Forum;
import hp.beans.Historico_Compra_Usuario;
import hp.beans.Usuario;
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
                    .prepareStatement("INSERT INTO hp_db.forum (cod_filho, descricao, cod_pai, cod_usuario, data_post) VALUES (0, ?, ?, ?, now())");
            
            // Parameters start with 1
            preparedStatement.setString(1,forum.getDescricao());
            preparedStatement.setString(2,Integer.toString(0));
            preparedStatement.setString(3,Integer.toString(forum.getCod_Usuario()));               
            
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
		ArrayList<Forum>  retorno = new ArrayList<Forum>();
				
		try {
			PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM hp_db.forum WHERE cod_pai = 0",ResultSet.TYPE_SCROLL_SENSITIVE, 
                            ResultSet.CONCUR_UPDATABLE);
			
			
			ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {	
				Forum historico = new Forum();
				historico = preencherEntidade(rs);
				retorno.add(historico);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		
		return retorno;
	}


	@Override
	public Forum preencherEntidade(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
        Forum forum = new Forum();
		
		forum.setCod_Filho(rs.getInt("cod_filho"));
		forum.setDescricao(rs.getString("descricao"));
		forum.setCod_Pai(rs.getInt("cod_pai"));
		forum.setCod_Usuario(rs.getInt("cod_usuario"));
		forum.setData(rs.getDate("data_post"));		
		
		return forum;
	}

}
