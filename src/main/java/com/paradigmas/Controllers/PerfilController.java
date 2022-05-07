package com.paradigmas.Controllers;

import com.paradigmas.DAOs.DisciplinaDAO;
import com.paradigmas.DAOs.HistoricoDAO;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.scene.control.Label;

public class PerfilController {
    private Label nome;
    private Label materias;
    private Label aprovacao;
    private Label reprovacaoNota;
    private Label reprovacaoFalta;

    public PerfilController() {}
    public PerfilController(Label nome, Label materias, Label aprovacao, Label reprovacaoNota, Label reprovacaoFalta) {
        this.nome = nome;
        this.materias = materias;
        this.aprovacao = aprovacao;
        this.reprovacaoNota = reprovacaoNota;
        this.reprovacaoFalta = reprovacaoFalta;
    }
    
    public void initialize()
    {
        try {
            nome.setText(HistoricoDAO.ler_historico().getNome());

            materias.setText("Matérias cursadas no último período: " + getAprovadasUltimoSemestre());
            aprovacao.setText("Aprovação no último período: " + formatDouble(getAprovacaoUltimoSemestre()) + "%");
            reprovacaoNota.setText("Reprovações por nota no último período: " + formatDouble(getReprovamentoNota()) + "%");
            reprovacaoFalta.setText("Reprovações por falta no último período: " + formatDouble(getReprovamentoFrequencia()) + "%");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }

    public Double getReprovamentoNota() throws Exception
    {
        int aprovadas = DisciplinaDAO.contaCursadasUltimoSemestre();
        int reprovadas_nota =  DisciplinaDAO.contaDisciplinasReprovadasNota();
        System.out.println("reprovadas"+ aprovadas);
        System.out.println("reprovadas_nota"+ reprovadas_nota);
        double total = ((double)reprovadas_nota/(double)aprovadas) * 100;


        return total ;
    }

    public Double getReprovamentoFrequencia() throws Exception
    {
        int reprovadas = DisciplinaDAO.contaDisciplinasReprovadasFrequencia();
        int aprovadas = DisciplinaDAO.contaCursadasUltimoSemestre();
        double total = ((double)reprovadas/(double)aprovadas) * 100;


        return total ;
    }


    public int getAprovadasUltimoSemestre() throws Exception
    {
        int aprovadas = DisciplinaDAO.contaCursadasUltimoSemestre();

        return aprovadas ;
    }

    public Double getAprovacaoUltimoSemestre() throws Exception
    {
        int aprovadas = DisciplinaDAO.contaDisciplinasAprovadasUltimoSemestre();
        int cursadas = DisciplinaDAO.contaDisciplinasUltimoSemestre();
        
        
        
        
        double total = ((double)aprovadas/(double)cursadas) * 100;
        
        
        return total;
    }

    public String formatDouble(Double value)
    {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_EVEN).toString();
    }
}
