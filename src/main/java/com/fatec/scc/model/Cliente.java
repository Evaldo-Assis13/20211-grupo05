package com.fatec.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	private String dataCadastro;
	private String dataRevisao;
	private String endereco;

	public Cliente() {
	}

	public Cliente(@NotNull String cpf, @NotNull String nome, @NotNull String email, @NotNull String cep) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		DateTime dataAtual = new DateTime();
		setDataCadastro(dataAtual);
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
	
	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(DateTime dataAtual) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		this.dataCadastro = dataAtual.toString(fmt);
		setDataRevisao();
	}
	
	public String getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		DateTime data = fmt.parseDateTime(getDataCadastro());
		this.dataRevisao = data.plusDays(360).toString(fmt);
	}

	public Integer verificaRevisao() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		DateTime dataAtual = fmt.parseDateTime(new DateTime().toString(fmt));
		DateTime dataDevolucaoPrevista = fmt.parseDateTime(getDataRevisao());
		int dias = Days.daysBetween(dataAtual, dataDevolucaoPrevista).getDays();
		return dias;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente - ID: " + id + ", CPF: " + cpf + ", Nome: " + nome + ", E-mail: " + email + ", CEP: " + cep
				+ ", Data do cadastro: " + dataCadastro + ", Data de revisão: " + dataRevisao + ", Endereço: " + endereco;
	}
	

}