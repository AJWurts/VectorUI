package wurts.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import wurts.MainApp;
import wurts.model.Vector;

public class MainSceneController {

	MainApp mainApp;
	Vector v1Actual;
	Vector v2Actual;

	public MainSceneController() {
	}

	@FXML
	private void initialize() {
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		vectorTable.setItems(mainApp.getVectors());
		vectorColumn.setCellValueFactory(cellData -> cellData.getValue().getStringProperty());
	}
	
	public void alertPopOut(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("Alert!");
		alert.setHeaderText("Error: 404");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	public void updateTable() {
		vectorTable.getColumns().get(0).setVisible(false);
		vectorTable.getColumns().get(0).setVisible(true);
	}
	
	@FXML
	TextField addResult;
	@FXML
	TextField subResult;
	@FXML
	TextField dotResult;
	@FXML
	TextField crossResult;
	@FXML
	TextField v1MulResult;
	@FXML
	TextField v1DivResult;
	@FXML
	TextField v2MulResult;
	@FXML
	TextField v2DivResult;
	@FXML
	TextField v1MagResult;
	@FXML
	TextField v2MagResult;
	@FXML
	TextField v1UnitResult;
	@FXML
	TextField v2UnitResult;
	@FXML
	TextField v1Mult;
	@FXML
	TextField v2Mult;
	@FXML
	TextField v1Div;
	@FXML
	TextField v2Div;
	@FXML
	TextField v1;
	@FXML
	TextField v2;
	
	@FXML
	TableView<Vector> vectorTable;
	@FXML
	TableColumn<Vector, String> vectorColumn;

	@FXML
	public void handleSetV1() {
		v1Actual = vectorTable.getSelectionModel().getSelectedItem();
		if (v1Actual != null) {
			v1.setText(v1Actual.toString());
			if (v2Actual != null) {
				updateData();
			}
		} else alertPopOut("No Vector Selected");
	}

	@FXML
	public void handleSetV2() {
		v2Actual = vectorTable.getSelectionModel().getSelectedItem();
		if (v2Actual != null) {
			v2.setText(v2Actual.toString());
			if (v1Actual != null) {
				updateData();
			}
		} else {
			alertPopOut("No Vector Selected");
		}
	}

	@FXML
	public void handleAddV() {
		mainApp.addNewVector();
	}

	@FXML
	public void handleRemV() {
		int selectedIndex = vectorTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			vectorTable.getItems().remove(selectedIndex);
		} else alertPopOut("No Vector Selected, Please Choose A Vector");
	}

	@FXML
	public void handleEditV() {
		Vector vector = vectorTable.getSelectionModel().getSelectedItem();
		if (vector != null) {
			mainApp.showVectorEditDialog(vector);
		} else alertPopOut("No Vector Selected, Please Choose A Vector");
		updateTable();
	}

	@FXML
	public void handleGraph() {

	}

	@FXML
	public void handleSaveAddedV() {
		if (v1Actual != null && v2Actual != null) {
			mainApp.addVector(v1Actual.add(v2Actual));
		} else alertPopOut("Vector 1 and 2 are not selected yet");
	}

	@FXML
	public void handleSaveSubV() {
		if (v1Actual != null && v2Actual != null) {
			mainApp.addVector(v1Actual.sub(v2Actual));
		} else alertPopOut("Vector 1 and 2 are not selected yet");
	}

	@FXML
	public void handleSaveCrossV() {
		if (v1Actual != null && v2Actual != null) {
			mainApp.addVector(v1Actual.cross(v2Actual));
		} else alertPopOut("Vector 1 and 2 are not selected yet");
		
	}

	@FXML
	public void handleEnterMultV1() {
		try {
			v1MulResult.setText(
					v1Actual.mul(
							Double.valueOf(v1Mult.getText()))
					.toString());
		} catch (Exception e) {
			alertPopOut("No Number Entered");
		}
		
	}

	@FXML
	public void handleEnterMultV2() {
		try {
			v2MulResult.setText(
					v2Actual.mul(
							Double.valueOf(v2Mult.getText()))
					.toString());
		} catch (Exception e) {
			alertPopOut("No Number Entered");
		}
	}

	@FXML
	public void handleEnterDivV1() {
		try {
			v1DivResult.setText(
					v1Actual.div(
							Double.valueOf(v1Div.getText()))
					.toString());
		} catch (Exception e) {
			alertPopOut("No Number Entered");
		}
	}

	@FXML
	public void handleEnterDivV2() {
		try {
			v2DivResult.setText(
					v2Actual.div(
							Double.valueOf(v2Div.getText()))
					.toString());
		} catch (Exception e) {
			alertPopOut("No Number Entered");
		}
	}

	@FXML
	public void handleSaveUnitV1() {
		if (v1Actual != null) {
			mainApp.addVector(v1Actual.toUnit());
		} else alertPopOut("Vector 1 not selected yet");
	}

	@FXML
	public void handleSaveUnit2() {
		if (v2Actual != null) {
			mainApp.addVector(v2Actual.toUnit());
		} else alertPopOut("Vector 2 not selected yet");
		
	}
	
	public void updateData() {
		addResult.setText(v1Actual.add(v2Actual).toString());
		subResult.setText(v1Actual.sub(v2Actual).toString());
		dotResult.setText(Double.toString(v1Actual.dot(v2Actual)));
		crossResult.setText(v1Actual.cross(v2Actual).toString());
		
		v1MagResult.setText(Double.toString(v1Actual.magnitude()));
		v2MagResult.setText(Double.toString(v2Actual.magnitude()));
		v1UnitResult.setText(v1Actual.toUnit().toString());
		v2UnitResult.setText(v2Actual.toUnit().toString());
	}
}
