/**
 * 
 */
/**
 * 
 */
module BancoDeDados {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
    requires javafx.fxml;
    opens fx to javafx.graphics, javafx.fxml;
    exports fx;
}
