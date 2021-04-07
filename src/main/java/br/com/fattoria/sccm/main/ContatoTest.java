package br.com.fattoria.sccm.main;

import java.util.ArrayList;
import java.util.List;

public class ContatoTest {

	private int id;
	private String nome;
	private String email;
	private EnderecoTest endereco;
	private List<TelefoneTest> telefones = new ArrayList<TelefoneTest>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EnderecoTest getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoTest endereco) {
		this.endereco = endereco;
	}
	
	public List<TelefoneTest> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<TelefoneTest> telefones) {
		this.telefones = telefones;
	}
	@Override
	public String toString() {
		return "ContatoTest [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco
				+ ", telefones=" + telefones + "]";
	}
	
	
	
}
