package ar.com.hdcm.catalogoLibros.prueba;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class TableViewEjemplo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception 
  {
	  
	  ObservableList<Person> data = FXCollections.observableArrayList(
			  new Person("Claudine", "Zillmann", "Design"),
			  new Person("Joel", "Ferreira", "Pro Gamer"),
			  new Person("Alexander", "Jorde", "Junior Developer"),
			  new Person("Holger", "Merk", "Senior Architect")
			  );

    TableView<Person> table = new TableView<>();
    table.itemsProperty().setValue(data);
    
    TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
    firstNameCol.setCellValueFactory((e) -> new SimpleStringProperty(e.
    										getValue().getFirstName()));
    
    TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
    lastNameCol.setCellValueFactory((e) -> new SimpleStringProperty(e.
			getValue().getLastName()));
    
    TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
    
    TableColumn<Person, String> jobColumn = new TableColumn<>("Job");
    jobColumn.setCellValueFactory((e) -> new SimpleStringProperty(e.
			getValue().getJob()));
    
    table.getColumns().addAll(nameColumn, jobColumn);
    nameColumn.getColumns().addAll(firstNameCol, lastNameCol);
    
    
    
    //table.setEditable(true);

    VBox myPane = new VBox();
    myPane.setAlignment(Pos.CENTER);
    myPane.setPadding(new Insets(12));
    myPane.setSpacing(12);
    myPane.getChildren().addAll(table);

     Scene myScene = new Scene(myPane);
     primaryStage.setScene(myScene);
     primaryStage.show();
     
     data.add(new Person("Carlos", "Merk", "Senior Architect"));
  }

  public static void main(String[] args) {
   launch(args);
  }
}
