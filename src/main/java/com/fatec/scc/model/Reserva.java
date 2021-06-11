package com.fatec.scc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cpf;
	private String descricao;
	private boolean concluido;

	public Reserva() {
	}

	public Reserva(String cpf, String descricao) {
		this.cpf = cpf;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", cpf=" + cpf + ", descricao=" + descricao + ", concluido=" + concluido + "]";
	}

}