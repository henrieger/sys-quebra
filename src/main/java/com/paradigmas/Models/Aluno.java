package com.paradigmas.Models;

import java.util.List;


public class Aluno {
    private String grr;
    private String nome;
    private double ira;
    private List<Matricula> matricula;
    
    public List<Matricula> getMatricula()
    {
    	return this.matricula;
    }
    
    private double calculaIra()
    {
    	double media = 0;
    	for (Matricula m : this.matricula)
    	{
    		media += (m.media_finalProperty().doubleValue() * m.getFrequencia()) / (m.getCh_total() * 100);
    	}
    	media /= this.matricula.size();
    	
    	return media;
    }

    public Aluno(String grr, String nome, List<Matricula> matricula)
    {
        this.grr = grr;
        this.nome = nome;
        this.matricula = matricula;
        this.ira = this.calculaIra();
    }
    
    public double percAprovacao()
    {
    	double aprov = 0;
    	double tot = 0;
    	for (Matricula m : this.matricula)
    	{
    		if(m.getSituacao() != Matricula.Situacao.MATRICULA)
    		{
    			if(m.getSituacao() == Matricula.Situacao.APROVADO)
    				aprov += 1;
    			tot += 1;
    		}
    	}
    	
    	return aprov / tot;
    }

    public String getNome() { return nome; }

	public double getIra() {
		return ira;
	}
}
