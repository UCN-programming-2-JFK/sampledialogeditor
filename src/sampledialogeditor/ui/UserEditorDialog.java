package sampledialogeditor.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import sampledialogeditor.model.User;


/**
 *  UserEditorDialog is a sample dialog for editing a User object.
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
	private JLabel userNameLabel, userEmailLabel;
	private JTextField userNameTextField, userEmailTextField;
	private JCheckBox isAdminCheckBox;

	public UserEditorDialog(JFrame parent) {
		super(parent, true);
		createLayout();
	}

	private void createLayout() {
		setSize(400, 300);
		LayoutManager gridLayoutManager = new GridLayout(0, 2);
		getContentPane().setLayout(gridLayoutManager);
		userNameLabel = new JLabel("Name:");
		userEmailLabel = new JLabel("Email:");
		userNameTextField = new JTextField("", 25);
		userEmailTextField = new JTextField("", 25);
		
		isAdminCheckBox = new JCheckBox("Is admin", false);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUser(null);
				setVisible(false);
			}
		});
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveValuesToUser();
				setVisible(false);
			}
		});

		getContentPane().add(userNameLabel);
		getContentPane().add(userNameTextField);
		getContentPane().add(userEmailLabel);
		getContentPane().add(userEmailTextField);
		getContentPane().add(new JPanel());
		getContentPane().add(isAdminCheckBox);
		getContentPane().add(cancelButton);
		getContentPane().add(saveButton);
		pack();
		setLocationRelativeTo(null);
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
}