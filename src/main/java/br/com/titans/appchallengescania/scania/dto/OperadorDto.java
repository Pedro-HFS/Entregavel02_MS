package br.com.titans.appchallengescania.scania.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.titans.appchallengescania.scania.model.Operador;

public class OperadorDto {

	private Long id;

	@NotBlank
	@NotNull
	private String nome_operador;

	@NotBlank
	@NotNull
	private String tarefa;

	private String status_tarefa;

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
	
	public void fromOperador(Operador operador) {
		this.nome_operador = operador.getNome_operador();
		this.tarefa = operador.getTarefa();
		this.status_tarefa = operador.getStatus_tarefa();
	}

}
