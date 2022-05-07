package com.paradigmas.Controllers;

import com.paradigmas.DAOs.DisciplinaDAO;
import com.paradigmas.DAOs.HistoricoDAO;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PerfilController implements Initializable {

    @FXML
    private Label perfilNome;

    @FXML
    private Label perfilMaterias;

    @FXML
    private Label perfilAprovacao;

    @FXML
    private Label perfilReprovacaoNota;

    @FXML
    private Label perfilReprovacaoFalta;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            perfilNome.setText(HistoricoDAO.ler_historico().getNome());

            perfilMaterias.setText("Matérias cursadas no último período: " + getAprovadasUltimoSemestre());
            perfilAprovacao.setText("Aprovação no último período: " + getAprovacaoUltimoSemestre() + "%");
            perfilReprovacaoNota.setText("Reprovações por nota no último período: " + getReprovamentoNota() + "%");
            perfilReprovacaoFalta.setText("Reprovações por falta no último período: " + getReprovamentoFrequencia() + "%");
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
}
