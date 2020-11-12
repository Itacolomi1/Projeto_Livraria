package hp.beans;

import java.util.Date;

public class Venda {
	
	private int Cod_Venda;
	private int Cod_Usuario;
	private Date Data_Venda;
	private float Total;
	
	public int getCod_Venda() {
		return this.Cod_Venda;
	}
	public void setCod_Venda(int cod_venda) {
		this.Cod_Venda = cod_venda;
	}
	
	public int getCod_Usuario() {
		return this.Cod_Usuario;
	}
	public void setCod_Usuario(int cod_usuario) {
		this.Cod_Usuario = cod_usuario;
	}
	
	public Date getData_Venda() {
		return this.Data_Venda;
	}
	public void setData_Venda(Date cod_venda) {
		this.Data_Venda = cod_venda;
	}
	
	public float getTotal() {
		return this.Total;
	}
	public void setTotal(int total) {
		this.Total = total;
	}
}
