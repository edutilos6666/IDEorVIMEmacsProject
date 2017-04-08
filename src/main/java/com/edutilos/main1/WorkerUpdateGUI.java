package com.edutilos.main1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class WorkerUpdateGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fieldId;
	private JTextField fieldName;
	private JTextField fieldAge;
	private JTextField fieldWage;
	
	private WorkerDAO dao ; 
	private DefaultTableModel modelWorkers ; 
	int selectedIndex ; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					WorkerUpdateGUI frame = new WorkerUpdateGUI();
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
	public WorkerUpdateGUI(WorkerDAO dao , DefaultTableModel modelWorkers , int selectedIndex) {
		this.dao = dao; 
		this.modelWorkers = modelWorkers; 
		this.selectedIndex = selectedIndex ; 
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 549, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Update Selected Worker ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(112, 11, 305, 14);
		contentPane.add(lblTitle);
		
		JLabel lblId = new JLabel("Id: ");
		lblId.setBounds(72, 56, 67, 14);
		contentPane.add(lblId);
		
		fieldId = new JTextField();
		fieldId.setEditable(false);
		fieldId.setColumns(10);
		fieldId.setBounds(268, 53, 180, 20);
		contentPane.add(fieldId);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(72, 94, 67, 14);
		contentPane.add(lblName);
		
		fieldName = new JTextField();
		fieldName.setColumns(10);
		fieldName.setBounds(268, 91, 180, 20);
		contentPane.add(fieldName);
		
		JLabel lblAge = new JLabel("Age: ");
		lblAge.setBounds(72, 137, 67, 14);
		contentPane.add(lblAge);
		
		fieldAge = new JTextField();
		fieldAge.setColumns(10);
		fieldAge.setBounds(268, 134, 180, 20);
		contentPane.add(fieldAge);
		
		JLabel lblWage = new JLabel("Wage: ");
		lblWage.setBounds(72, 183, 67, 14);
		contentPane.add(lblWage);
		
		fieldWage = new JTextField();
		fieldWage.setColumns(10);
		fieldWage.setBounds(268, 180, 180, 20);
		contentPane.add(fieldWage);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(268, 250, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(371, 250, 89, 23);
		contentPane.add(btnCancel);
		
		
		
		populateFields();
		btnUpdate.addActionListener(l-> {
			String name = fieldName.getText(), 
					ageStr = fieldAge.getText(), 
					wageStr = fieldWage.getText(); 
		
			List<String> fields = Arrays.asList("0", name, ageStr, wageStr);
			List<Object> verify = WorkerUtils.verifyFiels(fields); 
			boolean status = (boolean)verify.get(0); 
			String errorMsg = verify.get(1).toString();
			if(status) {
				Worker w = WorkerUtils.convertRowToWorker((Vector<Object>)modelWorkers.getDataVector().get(selectedIndex));
				long id = w.getId();
				Vector<Object> newWorkerData = new Vector<>(); 
				newWorkerData.add(id); 
				newWorkerData.add(name);
				newWorkerData.add(ageStr);
				newWorkerData.add(wageStr);
				Worker newW = WorkerUtils.convertRowToWorker(newWorkerData); 
				dao.update(id, newW);
				modelWorkers.removeRow(selectedIndex);
				modelWorkers.insertRow(selectedIndex, newWorkerData);
				modelWorkers.fireTableDataChanged();
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, errorMsg);
			}
		
		});
		
		
          btnCancel.addActionListener(l-> {
        	  this.setVisible(false);
          });		
	}
	
	private void populateFields()  {
		Worker w = WorkerUtils.convertRowToWorker((Vector<Object>)modelWorkers.getDataVector().get(selectedIndex));
		long id = w.getId();
		Worker ww = dao.findById(id); 
		fieldId.setText(String.valueOf(ww.getId())); 
		fieldName.setText(ww.getName()); 
		fieldAge.setText(String.valueOf(ww.getAge())); 
		fieldWage.setText(String.valueOf(ww.getWage())); 
	}

}
