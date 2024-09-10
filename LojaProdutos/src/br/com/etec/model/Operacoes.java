package br.com.etec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	private void acessarConta(ActionEvent event) {
		
		String nomeUsuario;
		nomeUsuario = txfUsuario.getText();
		
		String senhaUsuario;
		senhaUsuario = psfUsuario.getText();
		
		if(nomeUsuario.isEmpty() || senhaUsuario.isEmpty()) {
			
			if(nomeUsuario.isEmpty()) {
			mostrarMensagem(Alert.AlertType.WARNING, 
					"FALTANDO DADOS", "INFORMAR O USUÁRIO");
			} // if
			else {
				if(senhaUsuario.isEmpty()){
					mostrarMensagem(Alert.AlertType.WARNING, 
							"FALTANDO DADOS", "INFORMAR A SENHA");
				}
				}
			
		} // if
	
		else {
			if(nomeUsuario.equals("admin") && senhaUsuario.equals("123456")) {
				mostrarMensagem(Alert.AlertType.CONFIRMATION, 
						"ACESSO PERMITIDO", "Logado no sistema.");
			}
			else {
				mostrarMensagem(Alert.AlertType.ERROR, 
						"ERRO DE ACESSO", "Usuário ou senha errada.");
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
	@FXML
    private boolean verificarUsuarioSenha(String usuario, String senha) throws SQLException {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean usuarioValido = false;

        try {
            conexao = Conexao.conectar();
            String sql = "SELECT * FROM tabelalogin WHERE usuario = ? AND senha = ?";
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
            Conexao.fechar(conexao);
        }

        return usuarioValido;
    }
	
	
}
