package sampledialogeditor.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import sampledialogeditor.model.User;


/*	UserEditorDialog is a sample dialog for editing an object.
 *  To create new objects just instantiate and show the dialog 
 *  from another window and get the values of the   
 *  
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