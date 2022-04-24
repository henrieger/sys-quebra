package com.paradigmas;

import java.io.File;

import java.io.IOException;
import com.paradigmas.Controllers.HistoricoController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

public class PrimaryController {
	
	private void alert(String message, boolean erro)
	{
		AlertType at = (erro) ? AlertType.ERROR : AlertType.CONFIRMATION;
		Alert alert = new Alert(at);
		alert.setTitle((erro) ? "Erro": "Sucesso");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
    @FXML
    private void switchToSecondary() throws IOException {
    	
    	FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(null);
		
		if (file != null)  {
			
			if(HistoricoController.importarHistorico(file.getAbsolutePath())) {
				alert("Histórico importado!", false);
			}else {
				alert("Não foi possível carregar o arquivo!", true);
			}
			
		} else {
			alert("Não foi possível carregar o arquivo!", true);	
		}
        App.setRoot("secondary");
    }
}
