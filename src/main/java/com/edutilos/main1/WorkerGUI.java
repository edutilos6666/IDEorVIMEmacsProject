package com.edutilos.main1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class WorkerGUI extends JFrame {

	private JPanel contentPane;
	private JTable tableWorkers;
	private DefaultTableModel modelWorkers; 
    private WorkerDAO dao ; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerGUI frame = new WorkerGUI();
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
	public WorkerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("All Workers");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(135, 21, 282, 14);
		contentPane.add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 46, 522, 197);
		contentPane.add(scrollPane);
		
		tableWorkers = new JTable();
		scrollPane.setViewportView(tableWorkers);
		
		JButton btnNew = new JButton("New");
	
		btnNew.setBounds(51, 265, 96, 23);
		contentPane.add(btnNew);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(187, 265, 96, 23);
		contentPane.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(333, 265, 96, 23);
		contentPane.add(btnRemove);
		
		JButton btnDetails = new JButton("Details");
		btnDetails.setBounds(477, 265, 96, 23);
		contentPane.add(btnDetails);
		
		
		prepareDAO(); 
		setTableModel(); 
		
		
		btnNew.addActionListener(l->  {
			WorkerSaveGUI gui = new WorkerSaveGUI(dao, modelWorkers); 
			gui.setVisible(true);
		});
		
		btnUpdate.addActionListener(l-> {
			ListSelectionModel selectionModel = tableWorkers.getSelectionModel();
			int index = selectionModel.getMinSelectionIndex();
			System.out.println(index);
			if(index == -1) return ; 
			WorkerUpdateGUI gui = new WorkerUpdateGUI(dao , modelWorkers, index); 
			gui.setVisible(true);
		});
		
		
		btnRemove.addActionListener(l-> {
			ListSelectionModel selectionModel = tableWorkers.getSelectionModel();
			int index = selectionModel.getMinSelectionIndex();
			System.out.println(index);
			if(index == -1) return ; 
			
			Vector<Object> selectedRow = (Vector<Object>)modelWorkers.getDataVector().get(index);
			Worker selectedWorker = WorkerUtils.convertRowToWorker(selectedRow);
			int sizeBefore = dao.findAll().size(); 
			dao.remove(selectedWorker.getId());
			int sizeAfter = dao.findAll().size(); 
			System.out.println(sizeBefore + " , "+ sizeAfter);
			if((sizeBefore-sizeAfter) == 1) {
			modelWorkers.removeRow(index);
			modelWorkers.fireTableRowsDeleted(index, index);
		//	modelWorkers.fireTableDataChanged();
			}
		});
		
		btnDetails.addActionListener(l-> {
			ListSelectionModel selectionModel = tableWorkers.getSelectionModel();
			int index = selectionModel.getMinSelectionIndex();
			System.out.println(index);
			if(index == -1) return ; 
			
			Vector<Object> selectedRow = (Vector<Object>)modelWorkers.getDataVector().get(index);
			Worker selectedWorker = WorkerUtils.convertRowToWorker(selectedRow);
			WorkerDetailsGUI gui = new WorkerDetailsGUI(selectedWorker); 
			gui.setVisible(true);
		});
	}
	
	
	void prepareDAO() {
	//	dao = new WorkerDAOImpl();
		dao = new WorkerDAOJdbcImpl(); 
//		dao.save(new Worker(1, "foo", 10 , 100.0));
//		dao.save(new Worker(2, "bar", 20 , 200.0));
//		dao.save(new Worker(3, "bim", 30 , 300.0));
		
	}
	
	void setTableModel() {
		Vector<Object> headers = new Vector<>(); 
		headers.add("Id"); 
		headers.add("Name"); 
		headers.add("Age"); 
		headers.add("Wage"); 
		Vector<Vector<Object>> data = WorkerUtils.convertListToData(dao.findAll());
		modelWorkers = new DefaultTableModel(data, headers);
		tableWorkers.setModel(modelWorkers);
		tableWorkers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	
}
