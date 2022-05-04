package com.paradigmas.Controllers;

import java.io.IOException;
import java.util.List;

import com.paradigmas.DAOs.HistoricoDAO;
import com.paradigmas.DAOs.DisciplinaDAO;
import com.paradigmas.Models.Disciplina;

public class HistoricoController {
	
	public static boolean importarHistorico(String path) throws IOException
	{
		
		if(!path.endsWith(".csv")) return false;
		return HistoricoDAO.importar_historico(path);
		
	}
}
