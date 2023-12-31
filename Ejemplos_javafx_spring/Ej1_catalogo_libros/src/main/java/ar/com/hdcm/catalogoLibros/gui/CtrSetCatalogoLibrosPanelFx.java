/*
 * autor: filipuzzi, fernando rafael
 * versión: 20231121
 **/

package ar.com.hdcm.catalogoLibros.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

@Controller
public class CtrSetCatalogoLibrosPanelFx  implements Initializable
{	
	private static final Logger logger = LoggerFactory.getLogger(CtrSetCatalogoLibrosPanelFx.class);
		
	public String result="OK";
	@FXML
	public TextField tfNombreCatalogo;
	
	@FXML
	public TextArea taDescripcion;
	
	@FXML
	public Hyperlink hyDirBaseCatalogo;
	
	@FXML
	private Button btnCambiarHyperlink;
	
	@FXML
	private Button btnCerrar;
	
	@FXML
	private Button btnAceptar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
	}
	
	@FXML
    void btnCambiarHyperlink_onClicked() throws IOException
    {
		try
		{
			DirectoryChooser fc= new DirectoryChooser();
			fc.setTitle("Seleccionar enlace");
			
			//String wd=this.getClass().getClassLoader().getResource("./").getPath();
			String wd = System.getProperty("user.dir");
				
			fc.setInitialDirectory(new File(wd));
			
			File dirBaseCatalogo=fc.showDialog(null);
			
			if(dirBaseCatalogo!=null)
			{
				hyDirBaseCatalogo.setText(dirBaseCatalogo.getAbsolutePath());
			}
		}
		catch(Exception ex)
		{
			// taDescripcion.setText(ex.getMessage()+ex.getStackTrace().toString());
		}
    }
	
	@FXML
    void btnCerrar_onAction(ActionEvent event) throws IOException
    {
		result="CANCEL";
		Stage stage = (Stage) btnCerrar.getScene().getWindow();
		stage.close();
    }
	
	@FXML
    void btnAceptar_onAction(ActionEvent event) throws IOException
    {
		result="OK";
		Stage stage = (Stage) btnAceptar.getScene().getWindow();
		stage.close();
    }
}