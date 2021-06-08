package com.fatec.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@CPF(message = "CPF inválido")
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistência
	private String cpf;
	@NotNull
	@Size(min = 1, max = 100, message = "Nome deve ser preenchido")
	private String nome;
	@Email(message = "E-mail inválido.")
	private String email;
	@NotNull
	@Size(min = 8, max = 8, message = "O CEP deve ter 8 dígitos, não deve conter pontos, nem hífen.")
	private String cep;
	private String endereco;

	public Cliente() {
	}

	public Cliente(@NotNull String cpf, @NotNull String nome, @NotNull String email, @NotNull String cep) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", cep=" + cep
				+ ", endereco=" + endereco + "]";
	}
}