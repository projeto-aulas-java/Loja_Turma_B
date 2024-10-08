package br.com.etec.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ClasseTelaPrincipal {

	@FXML
	private MenuItem mniFechar;
	@FXML
	private Stage acpTelaPrincipal;
	
	@FXML
	public void fecharTela(ActionEvent event) {
		//acpTelaPrincipal = (Stage)((Node)event.getSource()).getScene().getWindow();
		acpTelaPrincipal = (Stage) mniFechar.getParentPopup().getOwnerWindow();
		
		acpTelaPrincipal.close();
	}
	
}
