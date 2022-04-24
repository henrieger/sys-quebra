package com.paradigmas.Models;

public class Matricula {

    public enum Situacao {
        APROVADO,
        REPROVADO,
        REPROVADO_NOTA,
        REPROVADO_FREQUENCIA
    };

    private String grr;
    private String cod_disciplina;
    private Double media_final;
    private int ano;
    private Situacao situacao;
    private int frequencia;
    private int periodo;

    public Matricula(String grr, String cod_disciplina, Double media_final, int ano, Situacao situacao, int frequencia, int periodo)
    {
        this.grr = grr;
        this.cod_disciplina = cod_disciplina;
        this.media_final = media_final;
        this.ano = ano;
        this.situacao = situacao;
        this.frequencia = frequencia;
        this.periodo = periodo;
    }
}
