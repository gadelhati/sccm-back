package br.com.fattoria.sccm.main;

public class EnderecoTest {
	
	private int id;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String complemento;
	private int numero;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "EnderecoTest [id=" + id + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cep=" + cep
				+ ", cidade=" + cidade + ", complemento=" + complemento + ", numero=" + numero + "]";
	}
	
	

}
