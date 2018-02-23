package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

//import userInterfaceTestbed.UserInterfaceTestbed;
import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @modified S.M.Gouse Sair
 * 
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * 
 */

public class UserInterface {
	
	/**********************************************************************************************
	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_DoubleCalculator = new Label("Double Calculator");
	private Label label_Operand1 = new Label("First operand");
	private Label label_Operand1ErrorTerm = new Label("Error Operand1");
	private TextField text_Operand1 = new TextField();
	private TextField text_Operand1ErrorTerm = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private Label label_Operand2ErrorTerm = new Label("Error Operand2");
	private TextField text_Operand2 = new TextField();
	private TextField text_Operand2ErrorTerm = new TextField();
	private Label label_Result = new Label("Result");
	private Label label_ResultErrorTerm = new Label("Error Result");
	private TextField text_Result = new TextField();
	private TextField text_ResultErrorTerm = new TextField();
	
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("\u2212");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_sqrt = new Button("\u221A");				// The divide symbol: \u221A
	private Label label_Op1_err = new Label(""); 				// For operand1 label
	private Label term1_err = new Label("");						// For operand1 error Term
	private Label Label_Op2_err = new Label("");				// For operand2 label
	private Label term2_err = new Label("");
	private Label label_ResultErr = new Label("");
	private Label result_error = new Label();
	private Text MVPart1_Err = new Text();
	private Text MVPart2_Err = new Text();
	private Text MVPart3_Err = new Text();
	private Text MVPart4_Err = new Text();
	private TextFlow errMeasuredValue1;
	private TextFlow errMeasuredValue2;
	private Text ETPart1_Err = new Text();
	private Text ETPart2_Err = new Text();
	private Text ETPart3_Err = new Text();
	private Text ETPart4_Err = new Text();
	private TextFlow errMeasuredValue3;
	private TextFlow errMeasuredValue4;

	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	
	/**********************************************************************************************
	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_DoubleCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 24, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 50);
		
		setupLabelUI(label_Operand1ErrorTerm, "Arial", 24, Calculator.WINDOW_WIDTH-120, Pos.BASELINE_RIGHT, 10, 50);
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 24, Calculator.WINDOW_WIDTH-370, Pos.BASELINE_LEFT, 10, 80, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the second operand when the user presses the enter (return) key
		
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		setupTextUI(text_Operand1ErrorTerm, "Arial", 24, 190, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 120, 80, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {set_Err_Operand1(); });
		
		text_Operand1ErrorTerm.setOnAction((event) -> { text_Operand2ErrorTerm.requestFocus(); });

		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_Op1_err, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 145);
		label_Op1_err.setTextFill(Color.RED);
		
		
		setupLabelUI(term1_err, "Arial", 18, Calculator.WINDOW_WIDTH, Pos.BASELINE_LEFT, 530, 150); // Label initialization for errorterm1
		term1_err.setTextFill(Color.RED);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 24, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 175);
		
		setupLabelUI(label_Operand2ErrorTerm, "Arial", 24, Calculator.WINDOW_WIDTH-120, Pos.BASELINE_RIGHT, 10, 175);
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 24, Calculator.WINDOW_WIDTH-370, Pos.BASELINE_LEFT, 10, 205, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		
		setupTextUI(text_Operand2ErrorTerm, "Arial", 24, 190, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 120, 205, true);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {set_Err_Operand2(); });
		text_Operand2ErrorTerm.setOnAction((event) -> { text_ResultErrorTerm.requestFocus(); });

		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(Label_Op2_err, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 272);
		Label_Op2_err.setTextFill(Color.RED);
		
		setupLabelUI(term2_err, "Arial", 18, Calculator.WINDOW_WIDTH, Pos.BASELINE_LEFT, 530, 272);
		term2_err.setTextFill(Color.RED);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 24, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 300);
		
		setupLabelUI(label_ResultErrorTerm, "Arial", 24, Calculator.WINDOW_WIDTH-160, Pos.BASELINE_RIGHT, 10, 300);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 24, Calculator.WINDOW_WIDTH-370, Pos.BASELINE_LEFT, 10, 330, false);
		
		setupTextUI(text_ResultErrorTerm, "Arial", 24, 190, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 120, 330, false);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_ResultErr, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 360); // Label initialization for Operand 1 
		label_ResultErr.setTextFill(Color.RED);
		
		setupLabelUI(result_error, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 360); // Label initialization for Operand 1 
		result_error.setTextFill(Color.RED);
		
		
		
		/* This block Establishes the two moving parts of the error message for Operand1 and Operand2 */
		
			
		MVPart1_Err.setFill(Color.BLACK);
	    MVPart1_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    MVPart2_Err.setFill(Color.RED);
	    MVPart2_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue1 = new TextFlow(MVPart1_Err, MVPart2_Err);
		errMeasuredValue1.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue1.setLayoutX(24);  
		errMeasuredValue1.setLayoutY(120);
		
		MVPart3_Err.setFill(Color.BLACK);
	    MVPart3_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    MVPart4_Err.setFill(Color.RED);
	    MVPart4_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue2 = new TextFlow(MVPart3_Err, MVPart4_Err);
		errMeasuredValue2.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue2.setLayoutX(24);  
		errMeasuredValue2.setLayoutY(245);
		
		/*This block Establishes the two moving parts of the error message for the error terms of Operand1 and Operand2*/
		
		ETPart1_Err.setFill(Color.BLACK);
	    ETPart1_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    ETPart2_Err.setFill(Color.RED);
	    ETPart2_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue3 = new TextFlow(ETPart1_Err, ETPart2_Err);
		errMeasuredValue3.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue3.setLayoutX(535);  
		errMeasuredValue3.setLayoutY(120);
		
		ETPart3_Err.setFill(Color.BLACK);
	    ETPart3_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
	    ETPart4_Err.setFill(Color.RED);
	    ETPart4_Err.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue4 = new TextFlow(ETPart3_Err, ETPart4_Err);
		errMeasuredValue4.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue4.setLayoutX(535);  
		errMeasuredValue4.setLayoutY(245);
		
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 425);
		button_Add.setOnAction((event) -> { addOperands(); addOperands_err(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 425);
		button_Sub.setOnAction((event) -> { subOperands(); subOperands_err(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 425);
		button_Mpy.setOnAction((event) -> { mpyOperands(); mpyOperands_err(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 425);
		button_Div.setOnAction((event) -> { divOperands(); divOperands_err(); });
		
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 425);
		button_sqrt.setOnAction((event) -> { sqrtOperands(); });
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_DoubleCalculator, label_Operand1,label_Operand1ErrorTerm, text_Operand1,text_Operand1ErrorTerm, label_Op1_err, term1_err,
				label_Operand2,label_Operand2ErrorTerm, text_Operand2, text_Operand2ErrorTerm, Label_Op2_err, term2_err, label_Result,label_ResultErrorTerm, text_Result, text_ResultErrorTerm, label_ResultErr, 
				button_Add, button_Sub, button_Mpy, button_Div,button_sqrt,errMeasuredValue1,errMeasuredValue2,errMeasuredValue3,errMeasuredValue4);//added button_square root for sqare_root button

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************
	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		MVPart1_Err.setText("");
		MVPart2_Err.setText("");
		label_Op1_err.setText("");
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_ResultErr.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_Op1_err.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				Label_Op2_err.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			label_Op1_err.setText(performGo());
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		MVPart3_Err.setText("");
		MVPart4_Err.setText("");
		Label_Op2_err.setText("");
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_ResultErr.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			Label_Op2_err.setText("");
			if (text_Operand1.getText().length() == 0)
				label_Op1_err.setText("");
		}
		else
			Label_Op2_err.setText(performGo_2());
	}
	
	
	
	private void set_Err_Operand1() {
		ETPart1_Err.setText("");
		ETPart2_Err.setText("");
		term1_err.setText("");
		text_ResultErrorTerm.setText("");								// Any change of an operand probably invalidates
		label_ResultErrorTerm.setText("Error Result");						// the result, so we clear the old result.
		result_error.setText("");
		if (perform.set_Err_Operand1(text_Operand1ErrorTerm.getText())) {	// Set the operand and see if there was an error
			term1_err.setText("");					// If no error, clear this operands error
			if (text_Operand2ErrorTerm.getText().length() == 0)		// If the other operand is empty, clear its error
				term2_err.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			term1_err.setText(performGo_err1());
	}
	
	
	private void set_Err_Operand2() {
		ETPart3_Err.setText("");
		ETPart4_Err.setText("");
		term2_err.setText("");
		text_ResultErrorTerm.setText("");								// Any change of an operand probably invalidates
		label_ResultErrorTerm.setText("Error Result");						// the result, so we clear the old result.
		result_error.setText("");
		if (perform.set_Err_Operand2(text_Operand2ErrorTerm.getText())) {	// Set the operand and see if there was an error
			term2_err.setText("");					// If no error, clear this operands error
			if (text_Operand1ErrorTerm.getText().length() == 0)		// If the other operand is empty, clear its error
				term1_err.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			term2_err.setText(performGo_err2());
	}
	
	
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_Op1_err.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				Label_Op2_err.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			Label_Op2_err.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_Op1_err.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				Label_Op2_err.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			Label_Op2_err.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}
	
	private boolean err_binaryOperandIssues() {
		String errorMessage1 = perform.get_Err_Op1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.get_Err_Op2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			term1_err.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				term2_err.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			term2_err.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.geterr_Operand1Defined()) {						// Check to see if the first operand is defined
			term1_err.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.geterr_Operand2Defined()) {					// Now check the second operand. It is is also
				term2_err.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.geterr_Operand2Defined()) {				// If the first is defined, check the second. Both
			term2_err.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}
	
	
	private boolean Sqrt_binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		perform.setOperand1ErrorMessage("");
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_Op1_err.setText(errorMessage1);			// there's an error message, so display it.
			return true;
		}
		
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_Op1_err.setText("No value found");			// If not, this is an issue for a binary operator
			return true;
		} 
		return false;											// Signal there are no issues with the operands
	}
	
	
	
	
	

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		                              // It is to clear the text for error message at operand2
		
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		Label_Op2_err.setText(""); 
		label_Op1_err.setText("");
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();						// Call the business logic add method
		label_ResultErr.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Sum");								// change the title of the field to "Sum"
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_ResultErr.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}
	
	
	
	
	private void addOperands_err(){
        // It is to clear the text for error message at operand2

			// Check to see if both operands are defined and valid
			if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
			term1_err.setText(""); 
			term2_err.setText("");
			// If the operands are defined and valid, request the business logic method to do the addition and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String answer = perform.error_addition();						// Call the business logic add method
			result_error.setText("");									// Reset any result error messages from before
			if (answer.length() > 0) {								// Check the returned String to see if it is okay
				System.out.println("Error"+answer);
			text_ResultErrorTerm.setText(answer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Sum"
			}
			else {														// Some error occurred while doing the addition.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			result_error.setText(perform.get_Err_ResultErrorMessage());	// Display the error message.
			}
}
	
            

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
		//Label_Op2_err.setText("");                                       // It is to clear the text for error message at operand2
		// Check to see if both operands are defined and valid
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation
				Label_Op2_err.setText(""); 
				label_Op1_err.setText("");
				// If the operands are defined and valid, request the business logic method to do the subtraction and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.subtraction();						// Call the business logic add method
				label_ResultErr.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(theAnswer);							// If okay, display it in the result field and
					label_Result.setText("difference");								// change the title of the field to "Minus"
				}
				else {														// Some error occurred while doing the subtraction.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_ResultErr.setText(perform.get_Err_ResultErrorMessage());	// Display the error message.
				}									// required to do subtraction.
	}
	
	private void subOperands_err(){
        // It is to clear the text for error message at operand2

			// Check to see if both operands are defined and valid
			if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
			term1_err.setText(""); 
			term2_err.setText("");
			// If the operands are defined and valid, request the business logic method to do the addition and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String theAnswer = perform.error_subtraction();						// Call the business logic add method
			result_error.setText("");									// Reset any result error messages from before
			if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_ResultErrorTerm.setText(theAnswer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Sum"
			}
			else {														// Some error occurred while doing the addition.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			result_error.setText(perform.getResultErrorMessage());	// Display the error message.
			}
}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		//Label_Op2_err.setText("");                                      // It is to clear the text for error message at operand2
		// Check to see if both operands are defined and valid
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation
				Label_Op2_err.setText(""); 
				label_Op1_err.setText("");
				// If the operands are defined and valid, request the business logic method to do the multiplication and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.multiplication();						// Call the business logic add method
				label_ResultErr.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(theAnswer);							// If okay, display it in the result field and
					label_Result.setText("Product");								// change the title of the field to "Product"
				}
				else {														// Some error occurred while doing the multiplication.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_ResultErr.setText(perform.getResultErrorMessage());	// Display the error message.
				}										// required to do multiplication.
	}
	
	private void mpyOperands_err(){
        // It is to clear the text for error message at operand2

			// Check to see if both operands are defined and valid
			if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
			term1_err.setText(""); 
			term2_err.setText("");
			// If the operands are defined and valid, request the business logic method to do the addition and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String theAnswer = perform.error_multiplication();						// Call the business logic add method
			result_error.setText("");									// Reset any result error messages from before
			if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_ResultErrorTerm.setText(theAnswer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Sum"
			}
			else {														// Some error occurred while doing the addition.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			result_error.setText(perform.getResultErrorMessage());	// Display the error message.
			}
}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		//Label_Op2_err.setText("");                              // It is to clear the text for error message at operand2 
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		Label_Op2_err.setText(""); 
		label_Op1_err.setText("");
		// If the operands are defined and valid, request the business logic method to do the division and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.division();						// Call the business logic add method
		label_ResultErr.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("quotient");								// change the title of the field to "Quotient"
		}
		else {														// Some error occurred while doing the division.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			Label_Op2_err.setText(perform.getResultErrorMessage());	// Display the error message beside label of operand2.
		}	
		
	}
	
	private void divOperands_err(){
		if (err_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		term1_err.setText(""); 
		term2_err.setText("");
		// If the operands are defined and valid, request the business logic method to do the division and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.error_division();						// Call the business logic add method
		result_error.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_ResultErrorTerm.setText(theAnswer);							// If okay, display it in the result field and
			label_ResultErrorTerm.setText("Error Result");								// change the title of the field to "Quotient"
		}
		else {														// Some error occurred while doing the division.
			text_ResultErrorTerm.setText("");									// Do not display a result if there is an error.				
			label_ResultErrorTerm.setText("Error Result");							// Reset the result label if there is an error.
			term2_err.setText(perform.getResultErrorMessage());	// Display the error message beside label of operand2.
		}	
		
	}
	
	private void sqrtOperands(){
		if (Sqrt_binaryOperandIssues()) 									// If there are issues with the operands, return
			return;														// without doing the computation
		Label_Op2_err.setText(""); 
		label_Op1_err.setText("");
		text_Operand1ErrorTerm.setText("");
		text_Operand2.setText("");
		text_Operand2ErrorTerm.setText("");
		text_ResultErrorTerm.setText("");
		
		
		
				// If the operands are defined and valid, request the business logic method to do the subtraction and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.sqrt();						// Call the business logic add method
				label_ResultErr.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					text_Result.setText(theAnswer);							// If okay, display it in the result field and
					label_Result.setText("root");								// change the title of the field to "Minus"
				}
				else {														// Some error occurred while doing the subtraction.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_Op1_err.setText(perform.getResultErrorMessage());	// Display the error message.
				}									// required to do subtraction.
	}
	
	
	private String performGo() 
	{
		String errMessage = MeasuredValueRecognizer.checkMeasureValue(text_Operand1.getText());
		if (errMessage != "") {
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return MeasuredValueRecognizer.measuredValueErrorMessage;
			String input = MeasuredValueRecognizer.measuredValueInput;
			MVPart1_Err.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			MVPart2_Err.setText("\u21EB");
		}return MeasuredValueRecognizer.measuredValueErrorMessage;
	}
	
	
	private String performGo_err1()
	{
		String errMessage;
		errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand1ErrorTerm.getText());
		if (errMessage != "") {
			String input = ErrorTermRecognizer.errorTermInput;
			if (ErrorTermRecognizer.errorTermIndexofError <= -1) return ErrorTermRecognizer.errorTermErrorMessage;
			ETPart1_Err.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
			ETPart2_Err.setText("\u21EB");
			} return ErrorTermRecognizer.errorTermErrorMessage;
	}
	
	
	private String performGo_err2()
	{
		String errMessage;
		errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand2ErrorTerm.getText());
		if (errMessage != "") {
			String input = ErrorTermRecognizer.errorTermInput;
			if (ErrorTermRecognizer.errorTermIndexofError <= -1) return ErrorTermRecognizer.errorTermErrorMessage;
			ETPart3_Err.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
			ETPart4_Err.setText("\u21EB");
			} return ErrorTermRecognizer.errorTermErrorMessage;
	}
	
	
	private String performGo_2() 
	{
		String errMessage = MeasuredValueRecognizer.checkMeasureValue(text_Operand2.getText());
		if (errMessage != "") {
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return MeasuredValueRecognizer.measuredValueErrorMessage;
			String input = MeasuredValueRecognizer.measuredValueInput;
			MVPart3_Err.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			MVPart4_Err.setText("\u21EB");
		}return MeasuredValueRecognizer.measuredValueErrorMessage;
	}
}