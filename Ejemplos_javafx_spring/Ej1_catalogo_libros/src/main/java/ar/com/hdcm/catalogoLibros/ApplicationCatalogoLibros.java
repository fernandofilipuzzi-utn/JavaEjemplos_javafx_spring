/*
 * autor: filipuzzi, fernando rafael
 * versión: 20231121
 **/

package ar.com.hdcm.catalogoLibros;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


@SpringBootApplication
public class ApplicationCatalogoLibros extends javafx.application.Application  {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;
	private FXMLLoader fxmlLoader;
	
	//
	
	@Override 
	public void init() throws Exception
	{
		springContext = SpringApplication.run(ApplicationCatalogoLibros.class);
		fxmlLoader=new FXMLLoader();
		fxmlLoader.setControllerFactory(springContext::getBean);
	}
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		Scene scene=launchPanel();
	
		stage.setResizable(true);
		stage.setMaximized(false);
		stage.setFullScreen(false);
		
		stage.setTitle("Catalogo libros.");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void stop()
	{
		springContext.stop();
	}
	
	public Scene launchPanel() throws IOException
	{
		fxmlLoader.setLocation(getClass().getResource("/CatalogoLibrosPanelFx.fxml"));
		rootNode=fxmlLoader.load();
		Scene scene=new Scene(rootNode);
		scene.getStylesheets().add("/CatalogoLibrosPanelFx.css");
		
		return scene;
	}
	
	//
	
	public static void main(String[] args) 
	{        
	    launch(args);        
	}
}