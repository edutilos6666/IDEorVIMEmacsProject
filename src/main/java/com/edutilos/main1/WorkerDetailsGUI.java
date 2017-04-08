package com.edutilos.main1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class WorkerDetailsGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fieldId;
	private JTextField fieldName;
	private JTextField fieldAge;
	private JTextField fieldWage;
	private Worker worker ; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					WorkerDetailsGUI frame = new WorkerDetailsGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public WorkerDetailsGUI(Worker worker) {
		this.worker = worker ; 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Worker Details");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(105, 25, 314, 14);
		contentPane.add(lblTitle);
		
		JLabel lblId = new JLabel("Id: ");
		lblId.setBounds(72, 62, 67, 14);
		contentPane.add(lblId);
		
		fieldId = new JTextField();
		fieldId.setEditable(false);
		fieldId.setColumns(10);
		fieldId.setBounds(268, 59, 180, 20);
		contentPane.add(fieldId);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(72, 100, 67, 14);
		contentPane.add(lblName);
		
		fieldName = new JTextField();
		fieldName.setEditable(false);
		fieldName.setColumns(10);
		fieldName.setBounds(268, 97, 180, 20);
		contentPane.add(fieldName);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(72, 143, 67, 14);
		contentPane.add(lblAge);
		
		fieldAge = new JTextField();
		fieldAge.setEditable(false);
		fieldAge.setColumns(10);
		fieldAge.setBounds(268, 140, 180, 20);
		contentPane.add(fieldAge);
		
		JLabel lblWage = new JLabel("Wage: ");
		lblWage.setBounds(72, 189, 67, 14);
		contentPane.add(lblWage);
		
		fieldWage = new JTextField();
		fieldWage.setEditable(false);
		fieldWage.setColumns(10);
		fieldWage.setBounds(268, 186, 180, 20);
		contentPane.add(fieldWage);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(371, 256, 89, 23);
		contentPane.add(btnClose);
		populateFields();
		
		
		btnClose.addActionListener(l-> {
			this.setVisible(false);
		});
	}

	
	private void populateFields() {
		fieldId.setText(String.valueOf(worker.getId())); 
		fieldName.setText(worker.getName());
		fieldAge.setText(String.valueOf(worker.getAge()));
		fieldWage.setText(String.valueOf(worker.getWage()));
	}
	
}
