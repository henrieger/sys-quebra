package com.paradigmas.Controllers;

import java.io.IOException;
import java.util.List;

import com.paradigmas.DAOs.DisciplinaDAO;
import com.paradigmas.Models.Aluno;
import com.paradigmas.Models.Disciplina;
import com.paradigmas.Models.Matricula;

public class DisciplinaController {
	
	public void verificarDisciplina(Aluno aluno, Disciplina disciplina, List<Disciplina> pedidos) throws Exception
	{
		if(aluno.getIra() < 0.8)
		{
			if(aluno.percAprovacao() < 0.5 && pedidos.size() >= 3)
			{
				throw new Exception("Desempenho C será aceito apenas 3 matérias.");
			}
			if(aluno.percAprovacao() >= 0.5 && aluno.percAprovacao() <= 0.66 && pedidos.size() >= 4)
			{
				throw new Exception("Desempenho B será aceito apenas 4 matérias.");
			}
			if(aluno.percAprovacao() > 0.66 && pedidos.size() >= 5)
			{
				throw new Exception("Desempenho A será aceito apenas 5 matérias.");
			}
		}
		
		if(disciplina.getTipo() == Disciplina.Tipo.OPTATIVA)
			throw new Exception("Não é feita quebra de pré-requisito para optivativas.");
	}
	
	public List<Disciplina> getDisciplinasAntesBarreira(Aluno aluno) throws IOException
	{
		List<Disciplina> disc_barreira = DisciplinaDAO.getDisciplinasAntesBarreira();
		System.out.println(disc_barreira.size());
		for (Matricula m : aluno.getMatricula())
		{
			disc_barreira.removeIf(obj -> obj.getCod_disciplina().equals(m.getCod_disciplina()));
		}
		return disc_barreira;
	}
	
	public List<Disciplina> getDisciplinasFaltantes(Aluno aluno) throws IOException
	{
		List<Disciplina>disc_faltantes = DisciplinaDAO.getDisciplinas();
		
		List<Disciplina> disc_barreira = DisciplinaDAO.getDisciplinasAntesBarreira();
		disc_faltantes.removeAll(disc_barreira);
		
		for (Matricula m : aluno.getMatricula())
		{
			disc_faltantes.removeIf(obj -> obj.getCod_disciplina().equals(m.getCod_disciplina()));
		}
		
		return disc_faltantes;
	}
}
