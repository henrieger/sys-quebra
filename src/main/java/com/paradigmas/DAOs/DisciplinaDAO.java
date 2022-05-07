package com.paradigmas.DAOs;

import com.paradigmas.Models.Disciplina;
import com.paradigmas.Models.Aluno;
import com.paradigmas.Models.Matricula;
import com.paradigmas.Lib.CsvReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class DisciplinaDAO {
	
	private static List<Disciplina> disciplinas = null;
	
    private static String disciplinas_path = "./src/main/resources/data/disciplinas.csv";
    private static String delimiter = ",";

    private enum Header {
        COD_DISCIPLINA(0),
        NOME_DISCIPLINA(1),
        PERIODO_IDEAL(2),
        TIPO_DISCIPLINA(3),
        CH_TOTAL(4),
        SITUACAO_VERSAO(5);

        public final int value;
        Header(int opValue) {
            this.value = opValue;
        }
    }
    
    public static List<Disciplina> getDisciplinas() throws IOException
    {
    	if(DisciplinaDAO.disciplinas == null)
    		DisciplinaDAO.disciplinas = DisciplinaDAO.ler_disciplinas();
    	
    	return new ArrayList<>(DisciplinaDAO.disciplinas);
    }

    private static List<Disciplina> ler_disciplinas() throws IOException
    {
        List<Disciplina> disciplina = new ArrayList<>();

        List<List<String>> records = CsvReader.ler_csv(disciplinas_path, delimiter);

        if (!records.isEmpty())
        {
            for (List<String> disp : records) {
                String cod_disciplina = disp.get(Header.COD_DISCIPLINA.value);
                String nome = disp.get(Header.NOME_DISCIPLINA.value);
                int periodo = Integer.parseInt(((disp.get(Header.PERIODO_IDEAL.value)).isEmpty()) ? "-1" : disp.get(Header.PERIODO_IDEAL.value));
                int ch_total = Integer.parseInt(disp.get(Header.CH_TOTAL.value));
                Disciplina.Tipo tipo = ((disp.get(Header.TIPO_DISCIPLINA.value)).equals("Obrigatoria")) ? Disciplina.Tipo.OBRIGATORIA : Disciplina.Tipo.OPTATIVA;
                Disciplina.Situacao situacao = ((disp.get(Header.TIPO_DISCIPLINA.value)).equals("ATIVA")) ? Disciplina.Situacao.ATIVA : Disciplina.Situacao.DESATIVADA;
    
                disciplina.add(new Disciplina(cod_disciplina, nome, periodo, ch_total, tipo, situacao));
            }
    
            return disciplina;
        }

        return new ArrayList<>();
    }
    
    private static Disciplina getDisciplinaPorCodigo(String cod) throws IOException
    {
    	List<Disciplina> disciplina = DisciplinaDAO.getDisciplinas();
    	
    	for (Disciplina disc : disciplina)
    	{
    		if(disc.getCod_disciplina().equals(cod))
    			return disc;
    	}
    	
    	return null;
    }
    
    public static List<Disciplina> getDisciplinasAntesBarreira() throws IOException
    {
    	List<Disciplina> disciplina = DisciplinaDAO.getDisciplinas();
    	
    	List<Disciplina> disc_ant_barr = new ArrayList<Disciplina>();
    	
    	for (Disciplina disc : disciplina)
    	{
    		if(disc.getPeriodo() <= 3 && disc.getPeriodo() > 0)
    			disc_ant_barr.add(disc);
    	}
    	
    	return disc_ant_barr;
    }
    
    public static List<Disciplina> getDisciplinasFaltantes(Aluno aluno) throws IOException
    {
    	
    	List<Disciplina> disc_cursadas = new ArrayList<Disciplina>();
    	Disciplina disc;
    	
    	for (Matricula mat : aluno.getMatricula())
    	{
    		disc = DisciplinaDAO.getDisciplinaPorCodigo(mat.getCod_disciplina());
    		if(disc != null)
    			disc_cursadas.add(disc);
    	}
    	
    	List<Disciplina> disc_total = DisciplinaDAO.getDisciplinas();
    	
    	disc_total.removeAll(disc_cursadas);
    	
    	return disc_total;
    }
    
    public static int ultimoAno() throws Exception
    {	
    	Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int ano = 0;
        
        for(Matricula disciplinas: historico)
        {
        	int ultimo_ano = disciplinas.getAno();
        	if(ano < ultimo_ano && (disciplinas.getSituacao().equals(Matricula.Situacao.REPROVADO_NOTA) || disciplinas.getSituacao().equals(Matricula.Situacao.REPROVADO_FREQUENCIA) || disciplinas.getSituacao().equals(Matricula.Situacao.APROVADO)))
        	{
        		ano = ultimo_ano;
        		
        	}
        	
       }
     
        return ano;
        
    }
    
    public static int ultimoPeriodo() throws Exception
    {
    	Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int ultimo = 0;
        int ano = ultimoAno();
        
        for(Matricula disciplinas: historico)
        {
        	int ultimo_ano = disciplinas.getAno();
        	int periodo = disciplinas.getPeriodo();
        	
            if(ultimo <= periodo && ano == ultimo_ano && (disciplinas.getSituacao().equals(Matricula.Situacao.REPROVADO_NOTA) || disciplinas.getSituacao().equals(Matricula.Situacao.REPROVADO_FREQUENCIA) || disciplinas.getSituacao().equals(Matricula.Situacao.APROVADO)))
            {
            	ultimo = periodo;
            }
       }       
        
        return ultimo;
        
    }
    
    
    public static int contaDisciplinasReprovadas() throws Exception{ 
        Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int Reprovadas = 0;
        int periodo = DisciplinaDAO.ultimoPeriodo();
    	int ano = DisciplinaDAO.ultimoAno();
        for(Matricula disciplinas: historico){
        	
             if(disciplinas.getSituacao().equals(Matricula.Situacao.REPROVADO) && disciplinas.getPeriodo() == periodo && disciplinas.getAno() == ano)
			{
            	 Reprovadas++;
            }
        }
        return Reprovadas;
    }
    
    public static int contaDisciplinasReprovadasNota() throws Exception{ 
        Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int reprovadasNota = 0;
        int periodo = DisciplinaDAO.ultimoPeriodo();
    	int ano = DisciplinaDAO.ultimoAno();
    	
        for(Matricula disciplinas: historico){
             if(disciplinas.getSituacao().equals(Matricula.Situacao.REPROVADO_NOTA)  && disciplinas.getPeriodo() == periodo && disciplinas.getAno() == ano){
            	 reprovadasNota++;
            }
        }
        return reprovadasNota;
    }
    
    public static int contaDisciplinasReprovadasFrequencia() throws Exception{ 
        Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int reprovadasFrequencia = 0;
        int periodo = DisciplinaDAO.ultimoPeriodo();
    	int ano = DisciplinaDAO.ultimoAno();
        for(Matricula disciplinas: historico){
             if(disciplinas.getSituacao().equals(Matricula.Situacao.REPROVADO_FREQUENCIA)  && disciplinas.getPeriodo() == periodo && disciplinas.getAno() == ano){
            	 reprovadasFrequencia++;
            }
        }
        return reprovadasFrequencia;
    } 
    
    public static int contaCursadasUltimoSemestre() throws Exception{
    	int periodo = DisciplinaDAO.ultimoPeriodo();
    	int ano = DisciplinaDAO.ultimoAno();
    	
        Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int cursadas = 0;
        
        for(Matricula disciplinas: historico){
             if(disciplinas.getPeriodo() == periodo && disciplinas.getAno() == ano  ){
            	 cursadas++;
            }
        }
        return cursadas;
    } 

    public static int contaDisciplinasAprovadasUltimoSemestre() throws Exception{

    	
        Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int Aprovadas = 0;
        int periodo = DisciplinaDAO.ultimoPeriodo();
        int ano = DisciplinaDAO.ultimoAno();
        
        for(Matricula disciplinas: historico){
        	
             if(disciplinas.getSituacao().equals(Matricula.Situacao.APROVADO) && disciplinas.getPeriodo() == periodo && disciplinas.getAno() == ano ){
            	 Aprovadas++;
            }
        }
        return Aprovadas;
    }
    
    public static int contaDisciplinasUltimoSemestre() throws Exception{
    	int periodo = DisciplinaDAO.ultimoPeriodo();
    	int ano = DisciplinaDAO.ultimoAno();
        Aluno historico_aluno = HistoricoDAO.ler_historico();
        List<Matricula> historico = historico_aluno.getMatricula();
        int cursadas = 0;
        
        for(Matricula disciplinas: historico){
        	
             if(disciplinas.getPeriodo() == periodo && disciplinas.getAno() == ano){
            	 cursadas++;
            }
        }
        return cursadas;
    }
    
    
   
    
}
