package wurts;


import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wurts.model.Vector;
import wurts.view.MainSceneController;
import wurts.view.MenuBarController;
import wurts.view.VectorEditSceneController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Vector> vectors = FXCollections.observableArrayList();
    
    public MainApp() {
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Vectors!");
        initRootLayout();
        
        showMainScene();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            MenuBarController menuController = loader.getController();
            menuController.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        	e.printStackTrace();
            System.out.println("Opening Root Layout Fucked up");
        }
    }

    public void showMainScene() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/MainScene.fxml"));
    		AnchorPane mainScene = (AnchorPane) loader.load();
    		
    		MainSceneController controller = loader.getController();
    		controller.setMainApp(this);
    		
    		rootLayout.setCenter(mainScene);
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    		System.out.println("Opening Main Scene Fucked up");
    	}
    }
    
    public boolean showVectorEditDialog(Vector vector) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/VectorEditScene.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Edit Vector");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		VectorEditSceneController controller = loader.getController();
    		controller.setMainApp(this);
    		controller.setDialogStage(dialogStage);
    		controller.setVector(vector); 
    		
    		dialogStage.showAndWait();
    		
    		return controller.isOkClicked();
    	} catch (IOException e) {
    		System.out.println("Opening Edit View Fucked up");
    		return false;
    	}
    }
    
    public void showAboutScene() {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/VectorEditScene.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Vector");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			VectorEditSceneController controller = loader.getController();
			controller.setMainApp(this);
			controller.setDialogStage(dialogStage);
			
    	} catch (Exception e) {
    		System.out.println("Error Loading About Scene");
    	}
    }
    
    public void addNewVector() {
    	vectors.add(new Vector(0d,0d,0d));
    }
    
    public void addVector(Vector vector) {
    	vectors.add(vector);
    }
    
    public ObservableList<Vector> getVectors() {
    	return vectors;
    }
    
    public void setRunner(ObservableList<Vector> vectors) {
 	   this.vectors = vectors;
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
  
  
}