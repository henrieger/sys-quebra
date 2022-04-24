package com.paradigmas.Controllers;

import java.io.IOException;

import com.paradigmas.DAOs.HistoricoDAO;

public class HistoricoController {
	
	public static boolean importarHistorico(String path) throws IOException
	{
		if(!path.endsWith(".csv")) return false;
		return HistoricoDAO.importar_historico(path);
	}
}
