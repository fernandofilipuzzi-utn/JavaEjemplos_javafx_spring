package ar.com.hdcm.catalogoLibros.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import ar.com.hdcm.catalogoLibros.modelo.ConfigFactory;
import ar.com.hdcm.catalogoLibros.modelo.Entrada;
import ar.com.hdcm.catalogoLibros.modelo.Libro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;

@Controller
public class CtrCatalogoLibrosPanelFx  implements Initializable
{	
	private static final Logger logger = LoggerFactory.getLogger(CtrCatalogoLibrosPanelFx.class);
	
	ConfigFactory config=new ConfigFactory();
	
	@FXML
	private BorderPane bpFrame;
	
	//filtro
	
	@FXML
	private TextArea taParametros;
	
	@FXML
	private CheckBox cbPorNombreFichero;
	
	@FXML
	private CheckBox cbPorTitulo;
	
	@FXML
	private CheckBox cbPorContenido;
	
	@FXML
	private Button btnBuscar;
	
	//
	@FXML
	private TextField tfFiltroContenido;
	
	@FXML
	private Button btnFiltroContenido;
	
	//
	
	@FXML
	private TableView tvEntradas;
	ObservableList<Entrada> tvEntradaData;
	
	@FXML
	private Button btnCerrar;
	
	@FXML
	private MenuItem miNuevo;
	@FXML
	private MenuItem miAbrir;
	
	@FXML
	private MenuItem miImportarEntradas;
	
	@FXML
	private MenuItem miExportarEntradas;
	
	@FXML
	private MenuItem miCerrar;
	
	@FXML
	private MenuItem miAcercaDe;
	
	//
	@FXML
	private MenuItem miActualizarEntradas;
	
	// Edición ficha del libro libro
	@FXML
	private TextArea taTitulo;
	
	@FXML
	private TextField tfAutor;
	
	@FXML
	private TextField tfEditorial;
	
	@FXML
	private TextField tfISBN10;
	
	@FXML
	private TextField tfISBN13;
	
	@FXML
	private Hyperlink hyDoc;
	
	@FXML
	private Button btnCambiarHyperlink;
	
	@FXML
	private Button btnConfirmar;
	
	@FXML
	private Button btnEliminar;
	
	//
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		tvEntradas.setPlaceholder(new Label("Iniciar Captura."));
		
		TableColumn<Entrada, String> nombreCol = new TableColumn<>("Nombre");
		nombreCol.setCellValueFactory((e) -> new SimpleStringProperty(e.getValue().getNombre()));
		TableColumn<Entrada, String> tipoDocCol = new TableColumn<>("Tipo de documento");
		tipoDocCol.setCellValueFactory((e) -> new SimpleStringProperty(e.getValue().getTipoFichero()));
		TableColumn<Entrada, String> urlCol = new TableColumn<>("URL");
		urlCol.setCellValueFactory((e) -> new SimpleStringProperty(e.getValue().getUrl()));
		
		//set columnas
		tvEntradas.getColumns().addAll(nombreCol, tipoDocCol,urlCol);
		
		//set modelo
		tvEntradaData = FXCollections.observableArrayList();
		tvEntradas.itemsProperty().setValue(tvEntradaData);
		
		//
		
		tvEntradas.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Entrada>) change -> {
			System.err.println("selectionModel: " + change.getList().size());
			
			if(change!=null && change.getList().size()>0)
			{
				selectedItem=change.getList().get(0);
				
				if(selectedItem!=null)
				{
					Libro ficha=selectedItem.getFicha();
					if(ficha!=null)
					{
						taTitulo.setText( selectedItem.getFicha().getTitulo());
						tfAutor.setText( selectedItem.getFicha().getAutor());
						tfEditorial.setText( selectedItem.getFicha().getEditorial());
						tfISBN10.setText( selectedItem.getFicha().getISBN10());
						tfISBN13.setText( selectedItem.getFicha().getISBN13());
					}
					else
					{
						taTitulo.setText("");
						tfAutor.setText("");
						tfEditorial.setText("");
						tfISBN10.setText("");
						tfISBN13.setText("");
						hyDoc.setText("");
					}
					hyDoc.setText(selectedItem.getNombre());
				}
			}
			else
			{
				taTitulo.setText("");
				tfAutor.setText("");
				tfEditorial.setText("");
				tfISBN10.setText("");
				tfISBN13.setText("");
				hyDoc.setText("");
			}
			
		});
	}
	Entrada selectedItem=null;
	
	@FXML
    void miNuevo_onAction(ActionEvent event) throws IOException
    {		
	    Stage dialog = new Stage();

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SetCatalogoLibrosPanelFx.fxml"));

	    Parent root = fxmlLoader.load();

	    CtrSetCatalogoLibrosPanelFx dialogController = fxmlLoader.<CtrSetCatalogoLibrosPanelFx>getController();

	    Scene scene = new Scene(root);
	    dialog.setScene(scene);

	    scene.getStylesheets().add("/SetCatalogoLibrosPanelFx.css");

	    dialog.initModality(Modality.APPLICATION_MODAL);
	    dialog.sizeToScene();
	    dialog.setResizable(false);

	    Stage stage = (Stage) scene.getWindow();
	    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

	        @Override
	        public void handle(WindowEvent event) {

	        }
	    });

	    dialog.showAndWait();

	    if (dialogController.result.equals("OK")) {
	        String docDirPath = dialogController.hyDirBaseCatalogo.getText();

	        config.CrearNuevoCatalogo();
	        config.getCatalogo().setDocDirPath(docDirPath);
	    }
    }
	
	@FXML
    void miAbrir_onAction(ActionEvent event)
    {
		MenuItem mi=(MenuItem) event.getSource();
			
		FileChooser fc = new FileChooser();
		
		fc.setTitle("Abrir fichero de catalogo.");
		fc.getExtensionFilters().addAll(new ExtensionFilter ("All Files", "*.cat"));
		
		File fileCatalogo=fc.showOpenDialog(mi.getParentPopup().getScene().getWindow());
				
		if(fileCatalogo!=null)
		{
			try 
			{
				config.AbrirCatalogo(fileCatalogo);
				config.setFileCatalog(fileCatalogo.getAbsolutePath());
			}
			catch (ClassNotFoundException | IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		tvEntradaData.clear();
		if(config!=null && config.getCatalogo()!=null)
			tvEntradaData.addAll(config.getCatalogo().getEntradas());
    }
	
	@FXML
    void miGuardar_onAction(ActionEvent event)
    {
		String fileCatalogName=config.getFileCatalog();
				
		if(fileCatalogName!=null && fileCatalogName.trim().equals("")==false)
		{
			File fileCatalog=new File(fileCatalogName);		
			try 
			{
				if(fileCatalog!=null)
				{
					config.GuardarCatalogo(fileCatalog);
					config.setFileCatalog(fileCatalog.getAbsolutePath());
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			miGuardarComo_onAction(event);
		}
    }
	
	@FXML
    void miGuardarComo_onAction(ActionEvent event)
    {
		FileChooser fc = new FileChooser();
		
		fc.setTitle("Guardar fichero de catalogo.");
		fc.getExtensionFilters().addAll(new ExtensionFilter ("All Files", "*.cat"));
		
		File fileCatalogo=fc.showSaveDialog(null);
		
		try 
		{
			if(fileCatalogo!=null)
			{
				config.GuardarCatalogo(fileCatalogo);
				config.setFileCatalog(fileCatalogo.getAbsolutePath());
			}
		} 
		catch (IOException ex) 
		{
			System.out.println( ex.toString()+ex.getMessage()+ex.getStackTrace().toString() );
		}
    }
		
	@FXML
    void btnCambiarHyperlink_onMouseClicked()
    {		
		if(tvEntradas.getSelectionModel().getSelectedItem()!=null)
		{
			FileChooser fc = new FileChooser();
			fc.setTitle("Seleccionar enlace");
			
			Entrada selectedItem=(Entrada)tvEntradas.getSelectionModel().getSelectedItem();
			
			fc.setInitialFileName(selectedItem.getNombre());
			
			File  doc=new File(config.getCatalogo().getDocDirPath()) ;
			fc.setInitialDirectory(doc);
		
			File fileCatalogo=fc.showOpenDialog(null);
			
			if(fileCatalogo!=null)
			{
				
				selectedItem.setNombre( fileCatalogo.getName() );
				selectedItem.setUrl( fc.getInitialDirectory() + fileCatalogo.separator + fileCatalogo.getName());
				
				hyDoc.setText(selectedItem.getNombre());				
			}
			
			tvEntradas.refresh();
		}
    }
		
	@FXML
    void miActualizarEntradas_onAction(ActionEvent event)
    {		
		if(config!=null &&  config.getCatalogo()!=null)
		{
			config.getCatalogo().ActualizarDocsFromDir();	
			tvEntradaData.clear();
			tvEntradaData.addAll(config.getCatalogo().getEntradas());
		}
    }
	
	@FXML
    void hyDoc_onMouseClicked()
    {
		try 
		{
			if(config.getCatalogo()!=null)
			{
				String docPath=config.getCatalogo().getDocDirPath() + "/" + selectedItem.getNombre();
			
				String cmds[] = new String[] {"cmd", "/c", docPath};
			
			
			    Runtime.getRuntime().exec(cmds);
			}
			else
			{
				System.out.println("Error!");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
	
	@FXML
    void btnConfirmar_onMouseClicked()
    {
		if(tvEntradas.getSelectionModel().getSelectedItem()!=null)
		{
			Entrada selectedItem=(Entrada)tvEntradas.getSelectionModel().getSelectedItem();
			
			Libro ficha=selectedItem.getFicha();
			
			if(ficha!=null)
			{
				ficha.setTitulo( taTitulo.getText() );
				ficha.setAutor( tfAutor.getText() );
				ficha.setEditorial( tfEditorial.getText() );
				ficha.setISBN10( tfISBN10.getText() );
				ficha.setISBN13( tfISBN13.getText() );
				
				ficha.setActualizado(true);
				
				tvEntradas.refresh();
			}
		}
    }
	
	@FXML
    void btnEliminar_onMouseClicked()
    {
		//if(selectedItem!=null)
		//{
		if(tvEntradas.getSelectionModel().getSelectedItem()!=null)
		{
			Entrada selectedItem=(Entrada)tvEntradas.getSelectionModel().getSelectedItem();
			
			config.getCatalogo().getEntradas().remove(selectedItem);
			
			taTitulo.clear();
			tfAutor.clear();
			tfEditorial.clear();
			tfISBN10.clear();
			tfISBN13.clear();
			hyDoc.setText("");
			
			tvEntradas.refresh();
			
			
			if(tvEntradaData!=null && selectedItem!=null)
			{
				//tvEntradaData.clear();
				tvEntradaData.remove(selectedItem);
			}
		}
    }
	
	@FXML
    void btnBuscar_onMouseClicked()
    {
		FilteredList<Entrada> filteredList = new FilteredList<>(tvEntradaData);
		tvEntradas.setItems(filteredList);
		
		
		String frase=taParametros.getText();
		// separando, normalizando y descartando las palabras
    	List<String> sets=new ArrayList<String>();
    	String [] palabras = frase.split(" ");
    	
    	for(int n=0; palabras!=null && n<palabras.length; n++)
    	{
    		if(palabras[n]!=null)
    		{
    			palabras[n]=palabras[n].trim().toUpperCase();
    			if( palabras[n].length()>0)
    			{
    				sets.add(palabras[n]);
    			}
    		}
    	}
    	
		filteredList.setPredicate(
				
		    new Predicate<Entrada>()
		    {
		        public boolean test(Entrada t)
		        {
		        	
		        	boolean inPorNombreFichero=false;
		        	boolean inTitulo=false;
		        	boolean inContenido=false;
		        	
		        	//
		        	boolean isTodo=true;
		        	isTodo=!(cbPorNombreFichero.isSelected() || cbPorTitulo.isSelected() || cbPorContenido.isSelected());
		        	//
		        	
		        	if(isTodo==false)
		        	{
			        
		        		if(cbPorNombreFichero.isSelected()==true)
			        	{
			        		for(int n=0; n<sets.size() && inPorNombreFichero==false; n++)
				        	{
				        		if(t.getNombre()!=null)
				        		{
				        			inPorNombreFichero=t.getNombre().toUpperCase().contains( sets.get(n)  );
				        		}
				        	}
			        	}
			        	
			        	if(cbPorTitulo.isSelected()==true)
			        	{
				        	for(int n=0; n<sets.size() && inTitulo==false; n++)
				        	{
				        		if(t.getFicha()!=null && t.getFicha().getTitulo()!=null )
				        		{
				        			inTitulo=t.getFicha().getTitulo().toUpperCase().contains( sets.get(n) );
				        		}
				        	}
			        	}
			        	
			        	if(cbPorContenido.isSelected()==true)
			        	{
				        	for(int n=0; n<sets.size() && inContenido==false; n++)
				        	{
				        		config.getCatalogo().BusquedaEnDocumento(t, sets.get(n) );
				        		inContenido= t.tieneCoincidencias() || inContenido;
				        	}
			        	}
			        	
		        	}
		        	
		        	return isTodo || inPorNombreFichero || inTitulo || inContenido;
		        }
		    }
		);
    }
	
	@FXML
    void miImportarEntradas_onAction(ActionEvent event) 
	{
		MenuItem mi=(MenuItem) event.getSource();
		
		FileChooser fc = new FileChooser();
		
		fc.setTitle("Importar entradas csv");
		fc.getExtensionFilters().addAll(new ExtensionFilter ("All Files", "*.csv"));
		
		File fileImportacionEntradas=fc.showOpenDialog(null);//mi.getParentPopup().getScene().getWindow());
		
		if(fileImportacionEntradas!=null)
		{
			try 
			{
				config.ImportarEntradas(fileImportacionEntradas);
			}
			catch (ClassNotFoundException | IOException e) 
			{
				e.printStackTrace();
			}
		}		
	}
		
	@FXML
    void miExportarEntradas_onAction(ActionEvent event) 
	{
		FileChooser fc = new FileChooser();
		
		fc.setTitle("Exportar entradas.");
		fc.getExtensionFilters().addAll(new ExtensionFilter ("All Files", "*.csv"));
		
		File fileExportacionEntradas=fc.showSaveDialog(null);
		
		try 
		{
			config.ExportarEntradas(fileExportacionEntradas);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@FXML
    void btnCerrar_onMouseClicked(ActionEvent event)
    {
		System.exit(0);
    }
	
	//
	
	@FXML
    void miAyuda_onAction(ActionEvent event) throws IOException
    {
		Runtime.getRuntime().exec("hh.exe ayuda/catalogolibros_ayuda.chm");
    }
	
	@FXML
    void miAcercaDe_onAction(ActionEvent event) throws IOException
    {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AcercaDePanelFx.fxml"));
        Parent parent = fxmlLoader.load();
        CtrAcercaDePanelFx dialogController = fxmlLoader.<CtrAcercaDePanelFx>getController();
        
        Scene scene = new Scene(parent, 300, 200);
        scene.getStylesheets().add("/AcercaDePanelFx.css");
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();		
    }
}