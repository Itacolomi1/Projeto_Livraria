package hp.beans;

public class Usuario {
	
	private int Cod_Usuario;
	private String Nome;
	private String Email;
	private String Senha;
	
	public int getCod_Usuario() {
		return this.Cod_Usuario;
	}
	public void setCod_Usuario(int id) {
		this.Cod_Usuario = id;
	}
	
	public String getNome() {
		return this.Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	
	public String getEmail() {
		return this.Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	
	public String getSenha() {
		return this.Senha;
	}
	public void setSenha(String senha) {
		this.Senha = senha;
	}
}
