package wurts.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import wurts.MainApp;

public class MenuBarController {
	
	
	MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
       
    }
	
	public MenuBarController(){}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	public void handleClose() {
		Platform.exit();
	}
}
