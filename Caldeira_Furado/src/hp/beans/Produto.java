package hp.beans;

public class Produto {
	private int Cod_Produto;
	private String Cod_Livro;
	private String Descricao;
	private float Valor;
	
	public int getCod_Produto() {
		return this.Cod_Produto;
	}
	public void setCod_Produto(int cod_produto) {
		this.Cod_Produto = cod_produto;
	}
	
	public String getCod_Livro() {
		return this.Cod_Livro;
	}
	
	public void setCod_Livro(String cod_livro) {
		this.Cod_Livro = cod_livro;
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

}
