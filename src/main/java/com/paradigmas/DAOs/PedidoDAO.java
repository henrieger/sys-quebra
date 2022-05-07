package com.paradigmas.DAOs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.paradigmas.Models.Pedido;

public class PedidoDAO {
	
public static Pedido ler_pedido() throws IOException
	{
	    Gson gson = new Gson();
	    Reader reader = Files.newBufferedReader(Paths.get("pedido.json"));
	    Pedido pedido = gson.fromJson(reader, Pedido.class);
	    
	    reader.close();
	    
	    return pedido;
	}

	public static void salva_pedido(Pedido pedido) throws JsonSyntaxException, IOException
	{
	    String caminho = "./src/main/resources/data/pedido.json";
	    Gson gson = new Gson();
	    gson.toJson(pedido, new FileWriter(caminho));
	           
	}
	
	
	public static void gera_pedido(Pedido pedido) throws IOException
	{
		try
        {
        File file=new File("./src/main/resources/data/Pedido.txt");
        FileWriter fw=new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(pedido.toString());
        bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	           
	}
	
	
	
	
}
