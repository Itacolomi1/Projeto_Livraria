package hp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import hp.beans.Produto_Venda;
import hp.beans.Usuario;
import hp.beans.Historico_Compra_Usuario;
import hp.model.DbUtil;

public class Produto_VendaDao implements Dao<Produto_Venda>{

	private Connection connection;
	
	public Produto_VendaDao() {
		
		this.connection = DbUtil.getConnection(); 
	}
	@Override
	public void insert(Produto_Venda produto_venda) {
		// TODO Auto-generated method stub
		try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO hp_db.produtos_vendas (cod_produto, cod_venda, quantidade) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, Integer.toString(produto_venda.getCod_Produto()));
            preparedStatement.setString(2,Integer.toString(produto_venda.getCod_Venda()));
            preparedStatement.setString(3,Integer.toString(produto_venda.getQuantidade()));     
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
	}

	@Override
	public void update(Produto_Venda obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Produto_Venda obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produto_Venda find(Produto_Venda obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produto_Venda> findAll(Produto_Venda obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto_Venda preencherEntidade(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Historico_Compra_Usuario preencheHistorico(ResultSet rs) {		
		Historico_Compra_Usuario historico = new Historico_Compra_Usuario();
		
		
		
		return historico;
	}
	
	public ArrayList<Historico_Compra_Usuario> comprarUsuario(Usuario usuario){
		ArrayList<Historico_Compra_Usuario>  retorno = null;
		Historico_Compra_Usuario historico = null;
		
		try {
			PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM hp_db.historico_compras WHERE cod_usuario = ?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                            ResultSet.CONCUR_UPDATABLE);
			
			preparedStatement.setString(1,  Integer.toString(usuario.getCod_Usuario()));
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {				
				historico = preencheHistorico(rs);
				retorno.add(historico);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		
		return retorno;
		
	}

}
