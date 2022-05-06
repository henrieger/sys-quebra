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
            perfilReprovacaoNota.setText("Reprovações por nota no último período: " + getAprovacaoUltimoSemestre() + "%");
            perfilReprovacaoFalta.setText("Reprovações por falta no último período: " + getAprovacaoUltimoSemestre() + "%");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }

    public int getReprovamentoNota() throws Exception
    {
        int reprovadas = DisciplinaDAO.contaDisciplinasReprovadas();
        int reprovadas_nota =  DisciplinaDAO.contaDisciplinasReprovadasNota();
        int result = reprovadas_nota % reprovadas;


        return result ;
    }

    public int getReprovamentoFrequencia() throws Exception
    {
        int reprovadas = DisciplinaDAO.contaDisciplinasReprovadasFrequencia();
        int reprovadas_nota =  DisciplinaDAO.contaDisciplinasReprovadasNota();
        int result = reprovadas_nota % reprovadas;


        return result ;
    }


    public int getAprovadasUltimoSemestre() throws Exception
    {
        int aprovadas = DisciplinaDAO.contaCursadasUltimoSemestre();

        return aprovadas ;
    }

    public int getAprovacaoUltimoSemestre() throws Exception
    {
        int aprovadas = DisciplinaDAO.contaDisciplinasAprovadasUltimoSemestre();
        int cursadas = DisciplinaDAO.contaDisciplinasUltimoSemestre();
        int result = aprovadas % cursadas;


        return result ;
    }
}
