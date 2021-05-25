package com.fatec.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Quarto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Long idQuarto;

	@NotNull
	private String categoriaQuarto;

	@NotNull
	private Integer quantidadeDias;

	@NotNull
	private Double valorQuarto;

	public Quarto() {
	}

	public Quarto(@NotNull String categoriaQuarto, Integer quantidadeDias, Double valorQuarto) {
		this.categoriaQuarto = categoriaQuarto;
		this.quantidadeDias = quantidadeDias;
		this.valorQuarto = valorQuarto;
	}

	public Long getIdQuarto() {
		return idQuarto;
	}

	public void setIdQuarto(Long idQuarto) {
		this.idQuarto = idQuarto;
	}

	public String getCategoriaQuarto() {
		return categoriaQuarto;
	}

	public void setCategoriaQuarto(String categoriaQuarto) {
		this.categoriaQuarto = categoriaQuarto;
	}

	public Integer getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(Integer quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public Double getValorQuarto() {
		return valorQuarto;
	}

	public void setValorQuarto(Double valorQuarto) {
		this.valorQuarto = valorQuarto;
	}

	@Override
	public String toString() {
		return "Quarto [idQuarto=" + idQuarto + ", categoriaQuarto=" + categoriaQuarto + ", quantidadeDias="
				+ quantidadeDias + ", valorQuarto=" + valorQuarto + "]";
	}

}
