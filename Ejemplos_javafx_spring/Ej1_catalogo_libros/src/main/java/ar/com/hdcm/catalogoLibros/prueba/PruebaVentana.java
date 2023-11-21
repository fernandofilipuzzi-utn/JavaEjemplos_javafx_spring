package ar.com.hdcm.catalogoLibros.prueba;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PruebaVentana extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button button = new Button("Hello World");
		
		StackPane myPane = new StackPane();
		myPane.getChildren().add(button);

		Scene myScene = new Scene(myPane);
		
		primaryStage.setScene(myScene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}