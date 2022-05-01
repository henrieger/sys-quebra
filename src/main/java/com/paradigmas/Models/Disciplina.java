package com.paradigmas.Models;

public class Disciplina {

    public static enum Situacao {
        ATIVA,
        DESATIVADA
    }

    public static enum Tipo {
        OBRIGATORIA,
        OPTATIVA
    }

    private String cod_disciplina;
    private String nome;
    private int periodo;
    private int ch_total;
    private Tipo tipo;
    private Situacao situacao;


    public Disciplina(String cod_disciplina, String nome, int periodo, int ch_total, Tipo tipo, Situacao situacao)
    {
        this.cod_disciplina = cod_disciplina;
        this.nome = nome;
        this.periodo = periodo;
        this.ch_total = ch_total;
        this.tipo = tipo;
        this.situacao = situacao;
    }


	public String getCod_disciplina() {
		return this.cod_disciplina;
	}


	public int getPeriodo() {
		return periodo;
	}


	public String getNome() {
		return nome;
	}

}
