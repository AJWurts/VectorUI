package wurts.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wurts.MainApp;
import wurts.model.Vector;

public class VectorEditSceneController {
	
	
		@FXML
		TextField inputI;
		
		@FXML
		TextField inputJ;
		
		@FXML
		TextField inputK;
		
		private Stage dialogStage;
	    private Vector vector;
	    private boolean okClicked = false;
	    private MainApp mainApp;
	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }

	    public void setMainApp(MainApp mainApp) {
	    	this.mainApp = mainApp;
	    }
	    
	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the person to be edited in the dialog.
	     * 
	     * @param person
	     */
	    public void setVector(Vector vector) {
	    	this.vector = vector;
		    inputI.setText(String.valueOf(vector.getI()));
		    inputJ.setText(String.valueOf(vector.getJ()));
		    inputK.setText(String.valueOf(vector.getK()));
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleAccept() {
        	vector.setI(eval(inputI.getText()));
        	vector.setJ(eval(inputJ.getText()));
        	vector.setK(eval(inputK.getText()));

            dialogStage.close();
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     * 
	     * @return true if the input is valid
	     */
	    public static double eval(final String str) {
	        return new Object() {
	            int pos = -1, ch;

	            void nextChar() {
	                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	            }

	            boolean eat(int charToEat) {
	                while (ch == ' ') nextChar();
	                if (ch == charToEat) {
	                    nextChar();
	                    return true;
	                }
	                return false;
	            }

	            double parse() {
	                nextChar();
	                double x = parseExpression();
	                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	                return x;
	            }

	            // Grammar:
	            // expression = term | expression `+` term | expression `-` term
	            // term = factor | term `*` factor | term `/` factor
	            // factor = `+` factor | `-` factor | `(` expression `)`
	            //        | number | functionName factor | factor `^` factor

	            double parseExpression() {
	                double x = parseTerm();
	                for (;;) {
	                    if      (eat('+')) x += parseTerm(); // addition
	                    else if (eat('-')) x -= parseTerm(); // subtraction
	                    else return x;
	                }
	            }

	            double parseTerm() {
	                double x = parseFactor();
	                for (;;) {
	                    if      (eat('*')) x *= parseFactor(); // multiplication
	                    else if (eat('/')) x /= parseFactor(); // division
	                    else return x;
	                }
	            }

	            double parseFactor() {
	                if (eat('+')) return parseFactor(); // unary plus
	                if (eat('-')) return -parseFactor(); // unary minus

	                double x;
	                int startPos = this.pos;
	                if (eat('(')) { // parentheses
	                    x = parseExpression();
	                    eat(')');
	                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                    x = Double.parseDouble(str.substring(startPos, this.pos));
	                } else if (ch >= 'a' && ch <= 'z') { // functions
	                    while (ch >= 'a' && ch <= 'z') nextChar();
	                    String func = str.substring(startPos, this.pos);
	                    x = parseFactor();
	                    if (func.equals("sqrt")) x = Math.sqrt(x);
	                    else if (func.equals("sin")) x = Math.sin(x);
	                    else if (func.equals("cos")) x = Math.cos(x);
	                    else if (func.equals("tan")) x = Math.tan(x);
	                    else if (func.equals("pi"))  x = Math.PI * x;
	                    else throw new RuntimeException("Unknown function: " + func);
	                } else {
	                    throw new RuntimeException("Unexpected: " + (char)ch);
	                }

	                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	                return x;
	            }
	        }.parse();
	    }
}
