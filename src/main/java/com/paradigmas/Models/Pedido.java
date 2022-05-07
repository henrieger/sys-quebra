package com.paradigmas.Models;

import java.util.Arrays;
import java.util.List;

public class Pedido {
    private List<Disciplina> disciplina;
    private String nome;
    private String grr;
    
    
    public Pedido(List<Disciplina> disciplina, String nome,  String grr)
    {
    	this.disciplina = disciplina;
    	this.nome = nome;
    	this.grr = grr;
    }
    
	public List<Disciplina> getDisciplina()
    {
    	return this.disciplina;
    	
    }
    
    public String getNome()
    {
    	return this.nome;
    }
    
    public String getGrr()
    {
    	return this.grr;
    }
    
    public void setDisciplina(List<Disciplina>  disciplina)
    {
    	this.disciplina = disciplina;
    }
    
    public void setNome(String nome)
    {
    	this.nome = nome;
    }
    
    public void setGrr(String grr)
    {
    	this.grr = grr;
    }
  
}
