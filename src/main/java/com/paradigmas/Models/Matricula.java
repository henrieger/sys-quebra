package com.paradigmas.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Matricula {

    public enum Situacao {
        APROVADO,
        REPROVADO,
        REPROVADO_NOTA,
        REPROVADO_FREQUENCIA
    };

    private String grr;
    private SimpleStringProperty cod_disciplina;
    private SimpleDoubleProperty media_final;
    private int ano;
    private Situacao situacao;
    private SimpleStringProperty frequencia;
    private int periodo;
    
    

    public Matricula(String grr, String cod_disciplina, Double media_final, int ano, Situacao situacao, int frequencia, int periodo)
    {
        this.grr = grr;
        this.cod_disciplina = new SimpleStringProperty(cod_disciplina);
        this.media_final = new SimpleDoubleProperty(media_final);
        this.ano = ano;
        this.situacao = situacao;
        this.frequencia = new SimpleStringProperty(frequencia+"%");
        this.periodo = periodo;
    }

    public int getAno() {
        return this.ano;
    }

    public int getPeriodo() {
        return this.periodo;
    }

	public String getCod_disciplina() {
		return this.cod_disciplina.toString();
	}

    public SimpleStringProperty cod_disciplinaProperty() {
        return this.cod_disciplina;
    }

    public SimpleDoubleProperty media_finalProperty() {
        return this.media_final;
    }

    public SimpleStringProperty frequenciaProperty() {
        return this.frequencia;
    }

    public int getFrequencia()
    {
        return Integer.parseInt(this.frequencia.getValue().split("%")[0]);
    }
}
