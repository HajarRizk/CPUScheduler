import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class CPUSchedularType extends JFrame {
    public static int s;
	public static	Vector<ProcessInfo> finalV2 =new Vector<>(s);
	public static double wait;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CPUSchedularType frame = new CPUSchedularType();
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

	public CPUSchedularType() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton fcfs = new JButton("FCFS");
		
		fcfs.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Event event1=new Event();
			    event1.setSize(CPUScheduler.size);
				event1.fcfsV(CPUScheduler.processInfo);
				s=event1.finalV.size();
			    finalV2=event1.finalV;
          	 double x=event1.getWaitingTime();
              
                wait=x;
				dispose();
				OutputR  frame2=new OutputR();
				frame2.setVisible(true);
		
			}
			
		});
		fcfs.setFont(new Font("Arial", Font.PLAIN, 20));
		fcfs.setBounds(75, 35, 250, 36);
		contentPane.add(fcfs);
		JButton sjf = new JButton("SJF");
		sjf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event event3=new Event();
				event3.setSize(CPUScheduler.size);
				event3.sjf(CPUScheduler.processInfo);
		
				double z=event3.getWaitingTime();
				s=event3.finalV.size();
			    finalV2=event3.finalV;
				wait=z;
				dispose();
				OutputR  frame2=new OutputR();
				frame2.setVisible(true);
			}
		});
		sjf.setFont(new Font("Arial", Font.PLAIN, 20));
		sjf.setBounds(75, 92, 250, 36);
		contentPane.add(sjf);
		
		JButton priority = new JButton("Priority");
		priority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event event2=new Event();
				event2.setSize(CPUScheduler.size);
				event2.priority(CPUScheduler.processInfo);
				double y=event2.getWaitingTime();
				wait=y;
				System.out.println(y);
				s=event2.finalV.size();
			    finalV2=event2.finalV;
				dispose();
				OutputR  frame2=new OutputR();
				frame2.setVisible(true);
			}
		});
		priority.setFont(new Font("Arial", Font.PLAIN, 20));
		priority.setBounds(75, 154, 250, 36);
		contentPane.add(priority);
		
		JButton roundRobin = new JButton("RoundRobin");
		roundRobin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qTime;
				qTime=JOptionPane.showInputDialog("Please Enter Time Quantum");//Take Time Quantum
				Event event4=new Event();//Make New Event
				event4.setSize(CPUScheduler.size);//Set Number Of Process
				event4.roundRobin(CPUScheduler.processInfo,Double.parseDouble(qTime));//Call RoundRobin Function In Event
            double k=event4.getWaitingTime(); //Get Waiting Time
      		wait=k;
      		s=event4.finalV.size();
		    finalV2=event4.finalV;
				dispose();
				OutputR  frame2=new OutputR();//Open Frame Of The Output
				frame2.setVisible(true);
					
			}
		});
		roundRobin.setFont(new Font("Arial", Font.PLAIN, 20));
		roundRobin.setBounds(75, 219, 250, 36);
		contentPane.add(roundRobin);
		
		JButton sjfPreemtive = new JButton("SJFPreemptive");
		sjfPreemtive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event event5=new Event();
				event5.setSize(CPUScheduler.size);
				event5.sjfPreemptive(CPUScheduler.processInfo);
                double h=event5.getWaitingTime();
				wait=h;
				s=event5.finalV.size();
			    finalV2=event5.finalV;
				dispose();
				OutputR  frame2=new OutputR();
				frame2.setVisible(true);
			}
		});
		sjfPreemtive.setFont(new Font("Arial", Font.PLAIN, 20));
		sjfPreemtive.setBounds(75, 290, 250, 36);
		contentPane.add(sjfPreemtive);
		
		JButton priorityPreemtive = new JButton("PriorityPreemptive");
		priorityPreemtive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event event6=new Event();
				event6.setSize(CPUScheduler.size);
				event6.priorityPreemptive(CPUScheduler.processInfo);
				double za=event6.getWaitingTime();
	      		wait=za;
	      		s=event6.finalV.size();
			    finalV2=event6.finalV;
				dispose();
				OutputR  frame2=new OutputR();
				frame2.setVisible(true);
			}
		});
		priorityPreemtive.setFont(new Font("Arial", Font.PLAIN, 20));
		priorityPreemtive.setBounds(75, 358, 250, 36);
		contentPane.add(priorityPreemtive);
	}
}
