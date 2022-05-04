package com.paradigmas.Models;

import java.util.Arrays;

public class Pedido {
    private String  disciplina;
    private String nome;
    private String grr;
    private long telefone;
    private Boolean is_formando;
    private int total_disc_obrigatoria;
    private int total_disc_optativas;
    public String justificativa;
    
    
    public Pedido(String disciplina, String nome,  String grr, Long telefone,  Boolean is_formando,  int total_disc_obrigatoria, int total_disc_optativas, String justificativa)
    {
    	this.disciplina = disciplina;
    	this.nome = nome;
    	this.grr = grr;
    	this.telefone = telefone;
    	this.is_formando = is_formando;
    	this.total_disc_obrigatoria = total_disc_obrigatoria;
    	this.total_disc_optativas = total_disc_optativas;
    	this.justificativa = justificativa;
    }
    


	public String getDisciplina()
    {
    	return this.disciplina;
    	//   Arrays.copyOf(disciplina, disciplina.length);
    	
    }
    
    public String getNome()
    {
    	return this.nome;
    }
    
    public String getGrr()
    {
    	return this.grr;
    }
    
    public long getTelefone()
    {
    	return this.telefone;
    }
    
    public Boolean getIsFormando()
    {
    	return this.is_formando;
    }
    
    public int getTotalObrigatorias()
    {
    	return this.total_disc_obrigatoria;
    }
    
    public int getTotalOptativas()
    {
    	return this.total_disc_optativas;
    }
    
    public String getJustificativa()
    {
    	return this.justificativa;
    }
    
    public void setDisciplina(String  disciplina)
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
    
    public void setTelefone(int telefone)
    {
    	this.telefone = telefone;
    }
    
    public void setIsFormando(Boolean is_formando)
    {
    	this.is_formando = is_formando;
    }
    
    public void setTotalObrigatoria(int total_disc_obrigatoria)
    {
    	this.total_disc_obrigatoria = total_disc_obrigatoria;
    }
    
    public void setTotalOptativas(int total_disc_optativas)
    {
    	this.total_disc_optativas = total_disc_optativas;
    }
    
    public void setJustificativa(String justificativa)
    {
    	this.justificativa = justificativa;
    }
}
