package com.fatec.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Long idReserva;
	
	@CPF(message = "CPF inválido")
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistência
	private String cpf;
	
	@NotNull
	@Size(min = 1, message = "O número do quarto deve ser preenchido corretamente")
	private String numQuarto;
	
	@NotNull
	@Size(min = 10, max = 10, message = "A data deve ser preenchida corretamente")
	private String dataEntrada;

	@NotNull
	@Size(min = 10, max = 10, message = "A data deve ser preenchida corretamente")
	private String dataSaida;
	
	public Reserva() {
	}

	public Reserva(@NotNull String cpf, @NotNull String numQuarto, @NotNull String dataEntrada, @NotNull String dataSaida) {
		this.cpf = cpf;
		this.numQuarto = numQuarto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(String numQuarto) {
		this.numQuarto = numQuarto;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", cpf=" + cpf + ", numQuarto=" + numQuarto + ", dataEntrada="
				+ dataEntrada + ", dataSaida=" + dataSaida + "]";
	}

}
