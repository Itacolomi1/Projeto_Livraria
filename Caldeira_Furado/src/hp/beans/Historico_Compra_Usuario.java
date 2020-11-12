package hp.beans;

import java.util.Date;

public class Historico_Compra_Usuario {

	private int Cod_Usuario;
	private int Cod_Produto;
	private int Cod_Venda;
	private String Descricao;
	private float Valor;
	private int Quantidade;
	private Date Data_Venda;
	
	
	public int getCod_Usuario() {
		return this.Cod_Usuario;
	}
	public void setCod_Usuario(int id) {
		this.Cod_Usuario = id;
	}
	
	public int getCod_Produto() {
		return this.Cod_Produto;
	}
	public void setCod_Produto(int cod_produto) {
		this.Cod_Produto = cod_produto;
	}
	
	public int getCod_Venda() {
		return this.Cod_Venda;
	}
	public void setCod_Venda(int cod_venda) {
		this.Cod_Venda = cod_venda;
	}
	
	public String getDescricao() {
		return this.Descricao;
	}
	
	public void setDescricao(String descricao) {
		this.Descricao = descricao;
	}
	
	public float getValor() {
		return this.Valor;
	}
	
	public void setValor(float valor) {
		this.Valor = valor;
	}
	
	public int getQuantidade() {
		return this.Quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.Quantidade = quantidade;
	}
	
	public Date getData_Venda() {
		return this.Data_Venda;
	}
	public void setData_Venda(Date cod_venda) {
		this.Data_Venda = cod_venda;
	}
	
}
