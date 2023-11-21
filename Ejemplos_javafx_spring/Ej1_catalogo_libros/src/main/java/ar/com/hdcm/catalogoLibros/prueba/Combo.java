package ar.com.hdcm.catalogoLibros.prueba;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Combo extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception 
  {

    ComboBox<Integer> comboBox = new ComboBox<>();
    ObservableList<Integer> data = 
                           FXCollections.observableArrayList();
    data.addAll(1,2,3);
    comboBox.itemsProperty().setValue(data);
  
    comboBox.setEditable(true);
    comboBox.converterProperty().setValue(new StringConverter<Integer>() 
    {
      @Override
      public String toString(Integer date) {
        if(date == null) {
          return null;
        }
        return date.toString();
      }

      @Override
      public Integer fromString(String s) {
        try {
          return new Integer(s);
        } catch (NumberFormatException e) {
        		//TODO:
        }
        return 0;
      }
    });


    TextField inputField = new TextField();

    inputField.setPromptText("insert new data type");
    inputField.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			data.add( new Integer(inputField.getText()) );	
		}
	});

    VBox box = new VBox(6, inputField, comboBox);

   ///box.setAlignment(Pos.CENTER);

    Scene myScene = new Scene(box);
    primaryStage.setScene(myScene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
