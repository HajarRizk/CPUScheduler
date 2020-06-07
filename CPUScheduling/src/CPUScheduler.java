import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Color;
import javax.swing.UIManager;

public class CPUScheduler {

	private JFrame frame;
	private JTable table;
	private JTextField processName;
	private JTextField arrivalTime;
	private JTextField burstTime;
	private JTextField priority;
	public static  Vector processInfo=new Vector();
	public static int size;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CPUScheduler window = new CPUScheduler();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public CPUScheduler() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 726, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 692, 325);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				try {
				String process=model.getValueAt(table.getSelectedRow(),0).toString();
				String arrival=model.getValueAt(table.getSelectedRow(),1).toString();
				String burst=model.getValueAt(table.getSelectedRow(),2).toString();
				String priorityy=model.getValueAt(table.getSelectedRow(),3).toString();
				
				processName.setText(process);
				arrivalTime.setText(arrival);
				burstTime.setText(burst);
				priority.setText(priorityy);
				}catch(Exception e1){
					
					String process=model.getValueAt(table.getSelectedRow(),0).toString();
					String arrival=model.getValueAt(table.getSelectedRow(),1).toString();
					String burst=model.getValueAt(table.getSelectedRow(),2).toString();
					
					
					processName.setText(process);
					arrivalTime.setText(arrival);
					burstTime.setText(burst);
					
					priority.setText("");
					
					
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ProcessName", "ArrivalTime", "BurstTime", "Priority"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class, Double.class, Long.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblNewLabel = new JLabel("ProcessName");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 373, 153, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ArrivalTime");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 413, 130, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("BurstTime");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 450, 130, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		processName = new JTextField();
		processName.setBounds(201, 381, 146, 19);
		frame.getContentPane().add(processName);
		processName.setColumns(10);
		
		arrivalTime = new JTextField();
		arrivalTime.setBounds(201, 419, 146, 19);
		frame.getContentPane().add(arrivalTime);
		arrivalTime.setColumns(10);
		
		burstTime = new JTextField();
		burstTime.setBounds(201, 458, 146, 19);
		frame.getContentPane().add(burstTime);
		burstTime.setColumns(10);
		
		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				
				try {
					if(Double.parseDouble(burstTime.getText())<=0||Double.parseDouble(arrivalTime.getText())<0||Long.parseLong(priority.getText())<0){
						JOptionPane.showMessageDialog(null, "Please Enter Valid Numbers(positive ArrivalTime&BurstTime bigger than zero&positive Priority) ");
					}else {
					model.addRow(new Object[] {(processName.getText()),Double.parseDouble(arrivalTime.getText()),Double.parseDouble(burstTime.getText()),Long.parseLong(priority.getText())});
					processName.setText("");
					arrivalTime.setText("");
					burstTime.setText("");
					priority.setText("");
				}
				}catch(Exception e1) {
					try {
						if(priority.getText().equals("")){
					model.addRow(new Object[] {(processName.getText()),Double.parseDouble(arrivalTime.getText()),Double.parseDouble(burstTime.getText())});
					processName.setText("");
					arrivalTime.setText("");
					burstTime.setText("");
					priority.setText("");
						}else {
							JOptionPane.showMessageDialog(null, "Please Enter Valid Numbers");
							
						}
					}catch(Exception e2){
						JOptionPane.showMessageDialog(null, "Please Enter Valid Numbers");
					}
				}
				
			}
		});
		add.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		add.setBounds(486, 369, 146, 34);
		frame.getContentPane().add(add);
		
		JButton update = new JButton("UPDATE");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                DefaultTableModel model=(DefaultTableModel)table.getModel();
				
				try{
					if(table.getSelectedRowCount()==1) {
						
				if(Double.parseDouble(burstTime.getText())<=0||Double.parseDouble(arrivalTime.getText())<0||Long.parseLong(priority.getText())<0) {
					JOptionPane.showMessageDialog(null, "Please Enter Valid Numbers(positive ArrivalTime&BurstTime bigger than zero&positive Priority) ");
					
				}else {
					String process=processName.getText();
					String arrival=arrivalTime.getText();
					String burst=burstTime.getText();
					String priorityy=priority.getText();
					model.setValueAt(process, table.getSelectedRow(), 0);
					model.setValueAt(Double.parseDouble(arrival), table.getSelectedRow(), 1);
					model.setValueAt(Double.parseDouble(burst), table.getSelectedRow(), 2);
					model.setValueAt(Long.parseLong(priorityy), table.getSelectedRow(), 3);
					
				}
				}else {
					if(table.getRowCount()==0){
						JOptionPane.showMessageDialog(null, "Table is Empty");
					}else {
						JOptionPane.showMessageDialog(null, "Please select single row");
					}
				}
				}catch(Exception e1) {
					try {
					if(priority.getText().equals("")) {
					if(table.getSelectedRowCount()==1) {
						
						String process=processName.getText();
						String arrival=arrivalTime.getText();
						String burst=burstTime.getText();
						
						model.setValueAt(process, table.getSelectedRow(), 0);
						model.setValueAt(Double.parseDouble(arrival), table.getSelectedRow(), 1);
						model.setValueAt(Double.parseDouble(burst), table.getSelectedRow(), 2);
						
					}else {
						if(table.getRowCount()==0){
							JOptionPane.showMessageDialog(null, "Table is Empty");
						}else {
							JOptionPane.showMessageDialog(null, "Please select single row");
						}
					}
					}else {
						JOptionPane.showMessageDialog(null, "Please Enter Valid Numbers");
					}
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null, "Please Enter Valid Numbers");
						
					}
					}
				}
				
			
		});
		update.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		update.setBounds(486, 423, 146, 34);
		frame.getContentPane().add(update);
		
		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				
				if(table.getSelectedRowCount()==1) {
					model.removeRow(table.getSelectedRow());
				}else {
					if(table.getRowCount()==0){
						JOptionPane.showMessageDialog(null, "Table is Empty");
					}else {
						JOptionPane.showMessageDialog(null, "Please select single row");
					}
				}
				}
			
		});
		delete.setFont(new Font("Tahoma", Font.ITALIC, 13));
		delete.setBounds(486, 486, 146, 34);
		frame.getContentPane().add(delete);
		
		JLabel lblNewLabel_3 = new JLabel("Priority");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 489, 96, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		priority = new JTextField();
		priority.setBounds(201, 497, 146, 19);
		frame.getContentPane().add(priority);
		priority.setColumns(10);
		JButton submit = new JButton("GO");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				processInfo=model.getDataVector(); //Get Data From Table Pass It To Event
				size=model.getRowCount();//Get Number Of Processes
				if(model.getRowCount()==0) //Table is Empty
				JOptionPane.showMessageDialog(null, "Table is Empty");
				else
				{	
					
				CPUSchedularType frame2=new CPUSchedularType();//Open Frame Of SchedularType
				frame2.setVisible(true);
				}
			
				
				
			}
		});
		submit.setForeground(new Color(0, 204, 0));
		submit.setBackground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		submit.setFont(new Font("Tahoma", Font.BOLD, 18));
		submit.setBounds(247, 536, 193, 38);
		frame.getContentPane().add(submit);
	}
}
