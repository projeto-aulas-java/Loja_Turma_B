module LojaProdutos {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;
	
	opens br.com.etec.model to javafx.graphics, javafx.fxml;
}
