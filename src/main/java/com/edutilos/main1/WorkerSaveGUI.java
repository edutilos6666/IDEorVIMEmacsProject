package com.edutilos.main1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class WorkerSaveGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fieldId;
	private JTextField fieldName;
	private JTextField fieldAge;
	private JTextField fieldWage;
	private WorkerDAO dao ; 
	private DefaultTableModel modelWorker ; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					WorkerSaveGUI frame = new WorkerSaveGUI();
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
	public WorkerSaveGUI(WorkerDAO dao , DefaultTableModel modelWorker) {
		this.dao = dao ; 
		this.modelWorker = modelWorker ; 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 532, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Add New Worker");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(151, 11, 173, 14);
		contentPane.add(lblTitle);
		
		JLabel lblId = new JLabel("Id: ");
		lblId.setBounds(65, 39, 67, 14);
		contentPane.add(lblId);
		
		fieldId = new JTextField();
		fieldId.setBounds(261, 36, 180, 20);
		contentPane.add(fieldId);
		fieldId.setColumns(10);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(65, 77, 67, 14);
		contentPane.add(lblName);
		
		fieldName = new JTextField();
		fieldName.setColumns(10);
		fieldName.setBounds(261, 74, 180, 20);
		contentPane.add(fieldName);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(65, 120, 67, 14);
		contentPane.add(lblAge);
		
		fieldAge = new JTextField();
		fieldAge.setColumns(10);
		fieldAge.setBounds(261, 117, 180, 20);
		contentPane.add(fieldAge);
		
		JLabel lblWage = new JLabel("Wage: ");
		lblWage.setBounds(65, 166, 67, 14);
		contentPane.add(lblWage);
		
		fieldWage = new JTextField();
		fieldWage.setColumns(10);
		fieldWage.setBounds(261, 163, 180, 20);
		contentPane.add(fieldWage);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(261, 233, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(364, 233, 89, 23);
		contentPane.add(btnCancel);
		
		
		btnSave.addActionListener(l-> {
			String idStr = fieldId.getText(), 
					name = fieldName.getText(), 
					ageStr = fieldAge.getText(), 
					wageStr = fieldWage.getText(); 
			
			List<Object> verify = WorkerUtils.verifyFiels(Arrays.asList(idStr, name, ageStr, wageStr));
			if((boolean)verify.get(0)) {
				int sizeBefore = dao.findAll().size();
				Worker w = new Worker(Long.parseLong(idStr), name, Integer.parseInt(ageStr), Double.parseDouble(wageStr)); 
				dao.save(w);
				int sizeAfter = dao.findAll().size(); 
				if((sizeAfter-sizeBefore) == 1) {
					modelWorker.addRow(new Object[]{idStr, name, ageStr, wageStr});
				}
				
				this.setVisible(false);
			} else {
				String msg = verify.get(1).toString(); 
				 JOptionPane.showMessageDialog(this, msg);
			}
		});
		
		btnCancel.addActionListener(l-> {
			this.setVisible(false);
		});
	}
}
