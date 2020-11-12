package hp.beans;

public class Produto_Venda {
	private int Cod_Produto;
	private int Cod_Venda;
	private int Quantidade;
	
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
	
	public int getQuantidade() {
		return this.Quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.Quantidade = quantidade;
	}
	
}
