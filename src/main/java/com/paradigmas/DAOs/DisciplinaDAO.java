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
    	
    	return DisciplinaDAO.disciplinas;
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
                Disciplina.Tipo tipo = ((disp.get(Header.TIPO_DISCIPLINA.value)).equals("ATIVA")) ? Disciplina.Tipo.OBRIGATORIA : Disciplina.Tipo.OPTATIVA;
                Disciplina.Situacao situacao = ((disp.get(Header.TIPO_DISCIPLINA.value)).equals("Obrigatoria")) ? Disciplina.Situacao.ATIVA : Disciplina.Situacao.DESATIVADA;
    
                disciplina.add(new Disciplina(cod_disciplina, nome, periodo, ch_total, tipo, situacao));
            }
    
            return disciplina;
        }

        return new ArrayList<>();
    }
    
    private Disciplina getDisciplinaPorCodigo(String cod) throws IOException
    {
    	List<Disciplina> disciplina = DisciplinaDAO.getDisciplinas();
    	
    	for (Disciplina disc : disciplina)
    	{
    		if(disc.getCod_disciplina().equals(cod))
    			return disc;
    	}
    	
    	return null;
    }
    
    public List<Disciplina> getDisciplinasAntesBarreira() throws IOException
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
    
    public List<Disciplina> getDisciplinasFaltantes(Aluno aluno) throws IOException
    {
    	
    	List<Disciplina> disc_cursadas = new ArrayList<Disciplina>();
    	Disciplina disc;
    	
    	for (Matricula mat : aluno.getMatricula())
    	{
    		disc = this.getDisciplinaPorCodigo(mat.getCod_disciplina());
    		if(disc != null)
    			disc_cursadas.add(disc);
    	}
    	
    	List<Disciplina> disc_total = DisciplinaDAO.getDisciplinas();
    	
    	disc_total.removeAll(disc_cursadas);
    	
    	return disc_total;
    }
}
