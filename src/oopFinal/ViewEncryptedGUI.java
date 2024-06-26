package oopFinal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ViewEncryptedGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	
	//the current message being displayed in this menu
	private EncryptedMessage messageShown;
	private int messageShownIndex;
	
	//the encryption method that was used to encypt messageShown
	private Encryptor currentEncryptor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEncryptedGUI frame = new ViewEncryptedGUI(new HuffmanMessage(""), new HuffmanEncryptor(), 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewEncryptedGUI(EncryptedMessage m, Encryptor en, int index) {
		
		//set the encryptor to be used for decrypting
		currentEncryptor = en;
		
		//set the message that will be shown by the GUI
		messageShown = m;
		
		messageShownIndex = index;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(44, 30, 54, 16);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setBounds(110, 25, 228, 26);
		textName.setEditable(false);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(78, 90, 285, 118);
		contentPane.add(textArea);
		
		JButton btnDeencrypt = new JButton("De-Encrypt!");
		btnDeencrypt.setBounds(164, 220, 117, 29);
		contentPane.add(btnDeencrypt);
		btnDeencrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deencryptMessage();
				contentPane.setVisible(false);
				dispose();
				new ViewMessageGUI().setVisible(true);
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(73, 284, 117, 29);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				new ViewMessageGUI().setVisible(true);
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(252, 284, 117, 29);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageManager.removeEncryptedMessage(messageShownIndex);
				contentPane.setVisible(false);
				dispose();
				new ViewMessageGUI().setVisible(true);
			}
		});
		
		showMessageText(textArea);
	}
	
	void showMessageText(JTextArea textArea) {
		textName.setText(messageShown.getName());
		textArea.setText(messageShown.getMessageText());
	}
	
	void deencryptMessage() {
		//decrypte the message
		UnencryptedMessage m = currentEncryptor.decrypt(messageShown);
		
		//add the decrypted message to the list of unencrypted messages
		MessageManager.addUnencryptedMessage(m);
		
		//int messageIndex = MessageManager.getAllEncryptedMessages().indexOf(messageShown);
		MessageManager.removeEncryptedMessage(messageShownIndex);
	}

}
