package com.paradigmas.Models;

import java.util.List;

public class Aluno {
    private String grr;
    private String nome;
    private List<Matricula> matricula;
    
    public List<Matricula> getMatricula()
    {
    	return this.matricula;
    }

    public Aluno(String grr, String nome, List<Matricula> matricula)
    {
        this.grr = grr;
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() { return nome; }
}
