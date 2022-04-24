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

    public static enum Versao {
        v2011,
        v2019
    }

    private String cod_disciplina;
    private String nome;
    private int periodo;
    private Versao versao;
    private int ch_total;
    private Tipo tipo;
    private Situacao situacao;


    public Disciplina(String cod_disciplina, String nome, int periodo, Versao versao, int ch_total, Tipo tipo, Situacao situacao)
    {
        this.cod_disciplina = cod_disciplina;
        this.nome = nome;
        this.periodo = periodo;
        this.versao = versao;
        this.ch_total = ch_total;
        this.tipo = tipo;
        this.situacao = situacao;
    }

    public String toString() { 
        return "Name: '" + this.nome + "', cod: '" + this.cod_disciplina + "', versao: '" + this.versao + "'";
    } 
}
