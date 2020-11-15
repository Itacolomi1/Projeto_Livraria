package hp.beans;

import java.util.Date;

public class Forum {
	
	private int Cod_Filho;	
	private int Cod_Pai;
	private int Cod_Usuario;
	private String Descricao;
	private Date Data;
	
	public int getCod_Filho() {
		return this.Cod_Filho;
	}
	public void setCod_Filho(int cod_filho) {
		this.Cod_Filho = cod_filho;
	}
	
	public int getCod_Pai() {
		return this.Cod_Pai;
	}
	public void setCod_Pai(int cod_pai) {
		this.Cod_Pai = cod_pai;
	}
	
	public int getCod_Usuario() {
		return this.Cod_Usuario;
	}
	public void setCod_Usuario(int cod_user) {
		this.Cod_Usuario = cod_user;
	}
	
	public String getDescricao() {
		return this.Descricao;
	}
	public void setDescricao(String conteudo) {
		this.Descricao = conteudo;
	}
	
	public Date getData() {
		return this.Data;
	}
	public void setData(Date data) {
		this.Data = data;
	}
}
