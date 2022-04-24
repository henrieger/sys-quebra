package com.paradigmas.Models;

import java.util.List;

public class Aluno {
    private String grr;
    private String nome;
    private Disciplina.Versao versao;
    private List<Matricula> matricula;

    public Aluno(String grr, String nome, Disciplina.Versao versao, List<Matricula> matricula)
    {
        this.grr = grr;
        this.nome = nome;
        this.versao = versao;
        this.matricula = matricula;
    }
}
