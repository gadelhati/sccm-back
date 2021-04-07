package br.com.fattoria.sccm.main;

public class TelefoneTest {
	
	private int id;
	private int ddd;
	private int numero;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "TelefoneTest [id=" + id + ", ddd=" + ddd + ", numero=" + numero + "]";
	}
	
	

}
