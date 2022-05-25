package br.com.titans.appchallengescania.scania.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Operador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome_operador;

	@Column(nullable = false)
	private String tarefa;

	private String status_tarefa;

	public Operador() {

	}

	public Operador(Long id, String nome_operador, String tarefa, String status_tarefa) {
		super();
		this.id = id;
		this.nome_operador = nome_operador;
		this.tarefa = tarefa;
		this.status_tarefa = status_tarefa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_operador() {
		return nome_operador;
	}

	public void setNome_operador(String nome_operador) {
		this.nome_operador = nome_operador;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public String getStatus_tarefa() {
		return status_tarefa;
	}

	public void setStatus_tarefa(String status_tarefa) {
		this.status_tarefa = status_tarefa;
	}

}
