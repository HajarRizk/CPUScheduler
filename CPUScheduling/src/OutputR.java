import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.util.Queue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class OutputR extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutputR frame = new OutputR();
					frame.setVisible(true);
				} catch (Exception e) {
					
	        	e.printStackTrace();
				}
		 	}
		});
	}

	public OutputR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Average waiting time\r\n");
		lblNewLabel.setBounds(10, 21, 127, 14);
		contentPane.add(lblNewLabel);
		CPUSchedularType Type=new	CPUSchedularType();
		double w=Type.wait;		
		JLabel WaitingTime = new JLabel(Double.toString(w));
		WaitingTime.setBounds(200, 16, 100, 30);
		contentPane.add(WaitingTime);
		
		JButton btnNewButton_1 = new JButton("OK");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(321, 21, 54, 23);
		btnNewButton_1.setBackground(Color.CYAN);
		contentPane.add(btnNewButton_1);
		Queue <Color> colors=new LinkedList<>();
		colors.add(Color.MAGENTA);
	    colors.add(Color.BLUE);
	   
        
	 int position =2;
	 double now=0;
     for (int i=0;i<Type.finalV2.size();i++)
     {  if(i==0) {JLabel label_6 = new JLabel(Type.finalV2.elementAt(i).processName);
		label_6.setBounds(position, 87, 40, 14);
		contentPane.add(label_6);
		JButton btnNewButton0 = new JButton(Type.finalV2.elementAt(i).processName);
		btnNewButton0.setBounds(position, 116, 50, 23);
		btnNewButton0.setBackground(colors.peek());
		Color z=colors.peek();
		colors.remove();
		colors.add(z);
		contentPane.add(btnNewButton0);
		JLabel lblNewLabel_1 = new JLabel(Double.toString(Type.finalV2.elementAt(i).startTime));
		lblNewLabel_1.setBounds(position, 151, 30, 14);
		contentPane.add(lblNewLabel_1);
		JLabel label_10 = new JLabel(Double.toString(Type.finalV2.elementAt(i).finishTime));
		label_10.setBounds(position+50, 151, 30, 14);
		contentPane.add(label_10);
		now+=Type.finalV2.elementAt(i).finishTime-Type.finalV2.elementAt(i).startTime;
}
     else {
    	 if (Type.finalV2.elementAt(i).startTime!=Type.finalV2.elementAt(i-1).finishTime) {
    	       
    			JButton btnNewButton = new JButton("");
    			btnNewButton.setBounds(position, 116, 50, 23);
    			btnNewButton.setBackground(Color.BLACK);
    			contentPane.add(btnNewButton);
    			JLabel label_1 = new JLabel(Double.toString(Type.finalV2.elementAt(i).startTime));
    			label_1.setBounds(position+50, 151, 30, 14);
    			contentPane.add(label_1);	 
    			now+=Type.finalV2.elementAt(i).startTime-Type.finalV2.elementAt(i-1).finishTime;
    			position+=50;
    			
    		        JLabel label_6 = new JLabel(Type.finalV2.elementAt(i).processName);
    				label_6.setBounds(position, 87, 40, 14);
    				contentPane.add(label_6);
    				JButton btnNewButton0 = new JButton(Type.finalV2.elementAt(i).processName);
    				btnNewButton0.setBounds(position, 116, 50, 23);
    				btnNewButton0.setBackground(colors.peek());
    				Color z=colors.peek();
    				colors.remove();
    				colors.add(z);
    				contentPane.add(btnNewButton0);
    				JLabel lblNewLabel_1 = new JLabel(Double.toString(Type.finalV2.elementAt(i).startTime));
    				lblNewLabel_1.setBounds(position, 151, 30, 14);
    				contentPane.add(lblNewLabel_1);
    				JLabel label_10 = new JLabel(Double.toString(Type.finalV2.elementAt(i).finishTime));
    				label_10.setBounds(position+50, 151, 30, 14);
    				contentPane.add(label_10);
    				now+=Type.finalV2.elementAt(i).finishTime-Type.finalV2.elementAt(i).startTime;
    	 }
    	 else {
    	 
        JLabel label_7 = new JLabel(Type.finalV2.elementAt(i).processName);
		label_7.setBounds(position, 87, 40, 14);
		contentPane.add(label_7);
		JButton btnNewButton7 = new JButton(Type.finalV2.elementAt(i).processName);
		btnNewButton7.setBounds(position, 116, 50, 23);
		btnNewButton7.setBackground(colors.peek());
		Color z=colors.peek();
		colors.remove();
		colors.add(z);
		contentPane.add(btnNewButton7);
		JLabel lblNewLabel_17 = new JLabel(Double.toString(Type.finalV2.elementAt(i).startTime));
		lblNewLabel_17.setBounds(position, 151, 30, 14);
		contentPane.add(lblNewLabel_17);
		JLabel label_17 = new JLabel(Double.toString(Type.finalV2.elementAt(i).finishTime));
		label_17.setBounds(position+50, 151, 30, 14);
		contentPane.add(label_17);
		now+=Type.finalV2.elementAt(i).finishTime-Type.finalV2.elementAt(i).startTime;
    	 }}position+=50;

     }
     Type.finalV2.removeAllElements();
	}
}
