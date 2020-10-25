package hp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hp.beans.Usuario;
import hp.model.DbUtil;

public class UsuarioDao implements Dao<Usuario>{
	
	private Connection connection;
	
	public UsuarioDao() {
		this.connection = DbUtil.getConnection(); 
	}

	@Override
	public void insert(Usuario usuario) {
		// TODO Auto-generated method stub
		
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO hp_db.usuarios (nome, email, senha) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());     
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void update(Usuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario find(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Usuario> findAll(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
