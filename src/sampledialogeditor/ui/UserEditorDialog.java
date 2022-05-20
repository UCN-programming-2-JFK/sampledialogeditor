package sampledialogeditor.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import sampledialogeditor.model.User;

/**
 * @author Jakob Farian Krarup (kr@rup.dk)
 * 
 *  UserEditorDialog is a sample dialog for editing a User object.
 *  The dialog also registers ENTER to accept and ESC to cancel.
 *  
 *  TO CREATE NEW USER OBJECT
 *   - show the dialog and get the dialog's values as a new User object using getUser()
 *   <pre>
 *   	userEditorDialog.setUser(null);
		userEditorDialog.setVisible(true);
		User newUser = userEditorDialog.getUser();
	</pre>
	  If the "Save" button was clicked, 
	  userEditorDialog.getUser() returns a User object
	  with the data from the dialog fields.
	  If the "Cancel" button was clicked, NULL is returned. 
 *   
 *  TO EDIT EXISTING USER OBJECT
 *   - set the user object on the dialog and show the dialog:
 *   <pre>
 *   	userEditorDialog.setUser(user);
		userEditorDialog.setVisible(true);
	</pre>
	  If the "Save" button is clicked.
	  the altered values will be transferred to the User object.
	
	CLEARING THE DIALOG'S FIELDS
	Setting a NULL user will clear the dialog's fields: 
	<pre>userEditorDialog.setUser(null);</pre> 
 */
public class UserEditorDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private User user;
	private JTextField userNameTextField, userEmailTextField;
	private JCheckBox isAdminCheckBox;
	protected JButton cancelButton, saveButton;
	
	public UserEditorDialog(JFrame parent) {
		super(parent, true);
		SwingUtilities.invokeLater(() -> createLayout());
	}

	private void createLayout() {

		LayoutManager gridLayoutManager = new GridLayout(0, 2);
		getContentPane().setLayout(gridLayoutManager);
		getContentPane().add(new JLabel("Name:"));
		getContentPane().add(userNameTextField = new JTextField("", 25));
		getContentPane().add(new JLabel("Email:"));
		getContentPane().add(userEmailTextField = new JTextField("", 25));
		getContentPane().add(new JPanel());
		getContentPane().add(isAdminCheckBox = new JCheckBox("Is admin", false));
		
		getContentPane().add(cancelButton = new JButton("Cancel"));
		cancelButton.addActionListener((a) -> cancel());
		
		getContentPane().add(saveButton = new JButton("Save"));
		saveButton.addActionListener((a)-> save());
		setEnterAndEscapeButtonFunctionality();
		pack();
		setLocationRelativeTo(null);	//centers on screen
	}

	private void saveValuesToUser() {
		if (user == null) {user = new User();}
		user.setName(userNameTextField.getText());
		user.setEmail(userEmailTextField.getText());
		user.setAdmin(isAdminCheckBox.isSelected());
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		showUser(user);
	}

	private void showUser(User user) {
		if(user != null) {
		fillFormFieldsWithUser(user);
		}
		else { emptyFormFields();}
	}

	private void fillFormFieldsWithUser(User user) {
		userNameTextField.setText(user.getName());
		userEmailTextField.setText(user.getEmail());
		isAdminCheckBox.setSelected(user.isAdmin());
	}

	private void emptyFormFields() {
		userNameTextField.setText(null);
		userEmailTextField.setText(null);
		isAdminCheckBox.setSelected(false);
	}
	
	protected void cancel() {
		setUser(null);
		setVisible(false);
	}

	protected void save() {
		saveValuesToUser();
		setVisible(false);
	}
	
	
	// make the ENTER key activate the SAVE button
	// and the ESCAPE key activate the CANCEL button
	private void setEnterAndEscapeButtonFunctionality() {

		//set the ENTER key to "click" the SAVE button
		getRootPane().setDefaultButton(saveButton);

		//declare the ESCAPE key
		KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		
		//register the ESCAPE key to the cancel() method using an ActionMap and an AbstractAction
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(esc, "escapeCancels");
		getRootPane().getActionMap().put("escapeCancels", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
	}
}