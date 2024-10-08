package br.com.etec.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Operacoes {
	@FXML
	private TextField txfUsuario;
	@FXML
	private PasswordField psfUsuario;
	@FXML
	private Button btnAcessar;
	@FXML
	private Button btnFechar;
	@FXML
	private Stage acpPalco;

	
	@FXML
	private void acessarConta(ActionEvent event) throws SQLException, IOException {
		
		String nomeUsuario;
		nomeUsuario = txfUsuario.getText();
		
		String senhaUsuario;
		senhaUsuario = psfUsuario.getText();
		
		if(nomeUsuario.isEmpty() || senhaUsuario.isEmpty()) {
			
			if(nomeUsuario.isEmpty()) {
			mostrarMensagem(Alert.AlertType.WARNING, 
					"FALTANDO DADOS", "INFORMAR O USUÁRIO");
			txfUsuario.clear();
			psfUsuario.clear();
			} // if
			else {
				if(senhaUsuario.isEmpty()){
					mostrarMensagem(Alert.AlertType.WARNING, 
							"FALTANDO DADOS", "INFORMAR A SENHA");
					txfUsuario.clear();
					psfUsuario.clear();
				}
				}
			
		} // if
	
		else {
			if(verificarUsuarioSenha(nomeUsuario, senhaUsuario)) {
				mostrarMensagem(Alert.AlertType.CONFIRMATION, 
						"ACESSO PERMITIDO", "Logado no sistema.");
				acessarTelaPrincipal(event);
			}
			else {
				mostrarMensagem(Alert.AlertType.ERROR, 
						"ERRO DE ACESSO", "Usuário ou senha errada.");
				txfUsuario.clear();
				psfUsuario.clear();
			}
		}
	} // acessarConta
	
	//----------------------------------------------------------------
	private void mostrarMensagem(Alert.AlertType tipo, String titulo, String mensagem) {
		Alert alerta = new Alert(tipo);
		alerta.setTitle(titulo);
		alerta.setHeaderText(null);
		alerta.setContentText(mensagem);
		alerta.showAndWait();
	}
	
	//----------------------------------------------------------------
	@FXML
	private void fecharTelaLogin(ActionEvent event) {
		acpPalco = (Stage) btnFechar.getScene().getWindow();
		acpPalco.close();	
	}
	
	//----------------------------------------------------------------

	 private boolean verificarUsuarioSenha(String usuario, String senha) throws SQLException {
	        Connection conexao = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        boolean usuarioValido = false;

	        try {
	            conexao = ClasseConexao.conectar();
	            String sql = "SELECT * FROM tabelasenhas WHERE usuario = ? AND senha = ?";
	            stmt = conexao.prepareStatement(sql);
	            stmt.setString(1, usuario);
	            stmt.setString(2, senha);
	            rs = stmt.executeQuery();

	            if (rs.next()) {
	                usuarioValido = true;
	            }
	        } finally {
	            if (rs != null) {
	                rs.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            ClasseConexao.fechar(conexao);
	        }

	        return usuarioValido;
	    }
	 
	 //-------------------------------------------------
	 public void  acessarTelaPrincipal(ActionEvent event) throws IOException{
		 
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/br/com/etec/view/telaPrincipal.fxml"));
			
			Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/br/com/etec/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			primaryStage.show();
		 
	 }
	 
	
}
