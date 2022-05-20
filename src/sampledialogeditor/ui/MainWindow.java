package sampledialogeditor.ui;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import sampledialogeditor.model.User;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private User user;
	private UserEditorDialog userEditorDialog;
	private JButton editUserButton;
	private JLabel userInfoLabel;
	
	public MainWindow() {

		getContentPane().setLayout(new GridLayout(0,1));

		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton newUserButton = new JButton("New user");
		newUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showNewUserDialog();
			}
		});
		getContentPane().add(newUserButton);

		editUserButton = new JButton("Edit user");
		editUserButton.setEnabled(false);
		editUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showUserEditorDialog();
			}
		});
		getContentPane().add(editUserButton);
		
		
		userInfoLabel = new JLabel("", SwingConstants.CENTER);
		getContentPane().add(userInfoLabel);
		
		userEditorDialog = new UserEditorDialog(this);
		setLocationRelativeTo(null);
	}

	private void showUserEditorDialog() {
		userEditorDialog.setUser(user);
		userEditorDialog.setVisible(true);
		displayUser();
	}

	private void showNewUserDialog() {
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