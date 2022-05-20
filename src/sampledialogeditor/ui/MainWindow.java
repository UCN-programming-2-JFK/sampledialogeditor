package sampledialogeditor.ui;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import sampledialogeditor.model.User;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private UserEditorDialog userEditorDialog;
	private JButton editUserButton, createUserButton;
	private JLabel userInfoLabel;
	
	public MainWindow() {
		createLayout();
		userEditorDialog = new UserEditorDialog(this);
	}

	private void createLayout() {
		
		//size, location and close operation
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//layout and controls
		getContentPane().setLayout(new GridLayout(0,1));	//single column layout
		
		getContentPane().add(createUserButton = new JButton("Create user"));
		createUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateUserDialog();
			}
		});

		getContentPane().add(editUserButton = new JButton("Edit user"));
		editUserButton.setEnabled(false);
		editUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showEditUserDialog();
			}
		});
		
		getContentPane().add(userInfoLabel = new JLabel("", SwingConstants.CENTER));
	}

	private void showEditUserDialog() {
		userEditorDialog.setUser(user);
		userEditorDialog.setVisible(true);
		displayUser();
	}

	private void showCreateUserDialog() {
		userEditorDialog.setUser(null);
		userEditorDialog.setVisible(true);
		User newUser = userEditorDialog.getUser();
		if (newUser != null) {
			user = newUser;
			displayUser();
			editUserButton.setEnabled(true);
		}
	}

	public void displayUser() {
		this.userInfoLabel.setText(user.toString());
	}
}