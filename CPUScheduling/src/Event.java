import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;


class  fcfsComparator implements Comparator<ProcessInfo>{ 
    
   
    public int compare(ProcessInfo p1, ProcessInfo p2) { 
        if (p1.arrivalTime >=p2.arrivalTime) 
            return 1;
        else if (p1.arrivalTime < p2.arrivalTime) 
            return -1;
        	return 0; 
        	
        	
       
        	
    
}
};

class  PriorityComparator implements Comparator<ProcessInfo>{ 
    
	   
    public int compare(ProcessInfo p1, ProcessInfo p2) { 
        if (p1.priority > p2.priority) 
            return 1; 
        else if (p1.priority < p2.priority) 
            return -1;
        	return 0;             
        } 
}

class  BurstComparator implements Comparator<ProcessInfo>{ 
    
	   
    public int compare(ProcessInfo p1, ProcessInfo p2) { 
        if (p1.burstTime >p2.burstTime) 
            return 1; 
        else if (p1.burstTime < p2.burstTime) 
            return -1;
         return 0;            
        } 
}






public class Event {
	

	
	
	private static  int size;
	private   PriorityQueue<ProcessInfo> fcfsq = new PriorityQueue<ProcessInfo>(10, new fcfsComparator());
	private   PriorityQueue<ProcessInfo> pq = new PriorityQueue<ProcessInfo>(10, new PriorityComparator());//Priority Ready Queue
	private   Queue<ProcessInfo> priorityq=new LinkedList<>();//Priority Running Queue
	private   PriorityQueue<ProcessInfo> bq = new PriorityQueue<ProcessInfo>(10, new BurstComparator());
	
	private   Queue<ProcessInfo> burstq=new LinkedList<>();
	public static   Vector<ProcessInfo> finalV =new Vector<>(size);
	
	
	Event()
	{
		finalV.removeAllElements();
	}
	
	
	public  void setSize(int i)
	{
		size=i;
	}
	
		
	private void fcfs(Vector v)
		{
		
		int count=0;
		for(int i=0;i<size;i++)
		{
			
			ProcessInfo p=new ProcessInfo();
			p.processName=((Vector)v.elementAt(i)).elementAt(0).toString();
			p.arrivalTime=(Double) ((Vector)v.elementAt(i)).elementAt(1);
			p.burstTime=(Double) ((Vector)v.elementAt(i)).elementAt(2);
			p.originalBurstTime=(Double) ((Vector)v.elementAt(i)).elementAt(2);
			if(((Vector)v.elementAt(i)).elementAt(3)!=null)
			p.priority=(Long) ((Vector)v.elementAt(i)).elementAt(3);
			p.timestamp=count+1;
			fcfsq.add(p);
		
			
			
			
			
			
			
		}
		
		
	
		
	
	
		}
	
	
	
	public void priority(Vector v)
	{
		fcfs(v);//Pass the processInfo Vector to fcfs to sort process according to arrivalTime In fcfsq
		ProcessInfo p=new ProcessInfo();
		double now=fcfsq.peek().arrivalTime; 
		pq.add(fcfsq.peek()); //pq is the ready queue take all ready processes and sort them according to priority
		fcfsq.remove();    
		while(!pq.isEmpty()||!fcfsq.isEmpty())//break when there is no ready process or arrival process    
		{
			
			while(!fcfsq.isEmpty())//but process in ready queue
			{
			p=fcfsq.peek();
			if(p.arrivalTime<=now)  //take from fcfsq all process whose arrivalTime<=now but in ready queue
			{
				pq.add(p); 
				fcfsq.remove(); 
				
			}
			else
				break;
			}
			
			if (pq.isEmpty()) {now=p.arrivalTime;} //Case Of Gap Between Processes
			else {
			priorityq.add(pq.peek()); //Put First One In Ready Queue In RunningQueue 
			now=now+pq.peek().burstTime; //now equal finishtime of the running process
			pq.remove();  
				}
		}
		
		while(!priorityq.isEmpty()) //Add Running Queue in finalV
		{
			finalV.add(priorityq.peek());
			priorityq.remove();
		}
		
		setTime(); //SetTime Of finalV
		
	}
	
	public  void fcfsV(Vector v)
	{
		fcfs(v);
		while(!fcfsq.isEmpty())
		{
			finalV.add(fcfsq.peek());
		    fcfsq.remove();
		}
		
		
		setTime();
	}
	
	
	public void sjf(Vector v)
	{
		fcfs(v); //Make Queue Of FirstCome 
		ProcessInfo p=new ProcessInfo();
		double now=fcfsq.peek().arrivalTime; //Take First Arrival Process
		bq.add(fcfsq.peek()); 
		fcfsq.remove();    
		while(!bq.isEmpty()||!fcfsq.isEmpty())    
		{
			
			Boolean stop=false;
			 
			while(!stop && !fcfsq.isEmpty())
			{
			p=fcfsq.peek();
			if(p.arrivalTime<=now)  
			{
				bq.add(p); 
				fcfsq.remove(); 
				
			}
			
			if(p.arrivalTime>now)
				stop=true;
			}
			if (bq.isEmpty()) now=p.arrivalTime;
			else {
			burstq.add(bq.peek()); 
			now=now+bq.peek().burstTime; 
			bq.remove();  
			
		}}
		
		while(!burstq.isEmpty())
		{
			finalV.add(burstq.peek());
			burstq.remove();
		}
		setTime();
	}
	
	public void roundRobin(Vector v,double qTime)
	{	fcfs(v); //Make Queue Of FirstCome fcfsq
		Queue<ProcessInfo> readyQueue=new LinkedList<>();
		Queue<ProcessInfo> runningQueue=new LinkedList<>();
		readyQueue.add(fcfsq.peek());//But First Arrival Process In Ready Queue
		double now=fcfsq.peek().arrivalTime; //now=ArrivalTime Of First Process
		fcfsq.remove();//After Adding It To Ready Queue Remove It From fcfsq
		while(!readyQueue.isEmpty()||!fcfsq.isEmpty()) 
		{	
			ProcessInfo p=new ProcessInfo();
			if (!readyQueue.isEmpty())
			{	p=readyQueue.peek(); //Take First Process From Ready Queue To Running Queue
			    runningQueue.add(p); 
			    p.burstTime=p.burstTime-qTime;//Update BurstTime Of Running Process
			   
			    readyQueue.remove();//Remove Process From Ready Queue After Adding It To Running Queue
		    }
			now=now+qTime;//UpDate Now
			while(!fcfsq.isEmpty()&&fcfsq.peek().arrivalTime<=now)//Add All Processes Which There ArrivalTime<Now  To Ready Queue
			{
				readyQueue.add(fcfsq.peek());
				fcfsq.remove();
			}
			if(p.burstTime>0)//If The Running Process BurstTime>0 Add It To Ready Queue Again
			{
				ProcessInfo p2=new ProcessInfo();
				p2.processName=p.processName;
				p2.arrivalTime=p.arrivalTime;
				p2.originalBurstTime=p.originalBurstTime;
				p2.burstTime=p.burstTime;
				p2.finishTime=p.finishTime;
				readyQueue.add(p2);
			}	
		}
		
		while(!runningQueue.isEmpty())//Add The Processes To finalV'Output'
		{
			finalV.add(runningQueue.peek());
			runningQueue.remove();
		}
		setTime2(qTime);
		setWaitingTimeRB();	
	}
	

	
	private void setTime()
	{
		finalV.elementAt(0).startTime=finalV.elementAt(0).arrivalTime;
		finalV.elementAt(0).finishTime=finalV.elementAt(0).startTime+finalV.elementAt(0).burstTime;
		finalV.elementAt(0).waitingTime=finalV.elementAt(0).startTime-finalV.elementAt(0).arrivalTime;
		
		
		
		for(int i=1;i<finalV.size();i++)
		{
			double now=finalV.elementAt(i-1).finishTime;
			if(finalV.elementAt(i).arrivalTime<=now)
			{
				finalV.elementAt(i).startTime=finalV.elementAt(i-1).finishTime;
				finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime;
				finalV.elementAt(i).waitingTime=finalV.elementAt(i).startTime-finalV.elementAt(i).arrivalTime;
			}
			else
			{
				finalV.elementAt(i).startTime=finalV.elementAt(i).arrivalTime;
				finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime;
				finalV.elementAt(i).waitingTime=finalV.elementAt(i).startTime-finalV.elementAt(i).arrivalTime;
			}
				
				
			
				
				
		}
		
		
	}
	public void sjfPreemptive(Vector v) 
	{
	 

		double now;
		double pnow =0;
	    fcfs(v);
		
		Queue<ProcessInfo> runningQueue=new LinkedList<>();
		
		now=fcfsq.peek().arrivalTime;
		bq.add(fcfsq.peek());
		fcfsq.remove();
		
		
		while(!fcfsq.isEmpty())
		{	
			
			pnow=now;
			
			while(!fcfsq.isEmpty()&&fcfsq.peek().arrivalTime<=now)
			{
				bq.add(fcfsq.peek());
				fcfsq.remove();
			} 
			
			if(fcfsq.isEmpty())
				break;
				
			
			if(!fcfsq.isEmpty())
				now=fcfsq.peek().arrivalTime;
			ProcessInfo p=new ProcessInfo();
			if(!bq.isEmpty())
			{
			p=bq.peek();
			p.burstNow=p.burstTime;
			p.burstTime=p.burstTime-(now-pnow);
			bq.remove();
			runningQueue.add(p);
			}
			while(p.burstTime<0&&!bq.isEmpty())
			{
				if(!bq.isEmpty())
				{
				pnow=p.burstTime+now;
				p=bq.peek();
				p.burstNow=p.burstTime;
				p.burstTime=p.burstTime-(now-pnow);
				bq.remove();
				runningQueue.add(p);
				}
				
			}
							
			if(p.burstTime>0)
			{
				ProcessInfo p2=new ProcessInfo();
				p2.processName=p.processName;
				p2.arrivalTime=p.arrivalTime;
				p2.burstTime=p.burstTime;
				p2.burstNow=p.burstTime;
				p2.originalBurstTime=p.originalBurstTime;
				bq.add(p2);
			}
						
			
		}
		
		while(!bq.isEmpty())
		{
			bq.peek().exit=1;
			runningQueue.add(bq.peek());
			bq.remove();
			
		}
		
		while(!runningQueue.isEmpty())
		{
			finalV.add(runningQueue.peek());
			runningQueue.remove();
		}
		
	
	
		setTime3();
		setWaitingTimeRB();	
	}
	
	public void priorityPreemptive(Vector v)   
	{

		double now;
		double pnow =0;
	    fcfs(v);
		
		Queue<ProcessInfo> runningQueue=new LinkedList<>();
		
		now=fcfsq.peek().arrivalTime;
		pq.add(fcfsq.peek());
		fcfsq.remove();
		
		
		while(!fcfsq.isEmpty())
		{	
			
			pnow=now;
			
			while(!fcfsq.isEmpty()&&fcfsq.peek().arrivalTime<=now)
			{
				pq.add(fcfsq.peek());
				fcfsq.remove();
			} 
			
			if(fcfsq.isEmpty())
				break;
				
			
			if(!fcfsq.isEmpty())
				now=fcfsq.peek().arrivalTime;
			
			ProcessInfo p=new ProcessInfo();
			if(!pq.isEmpty())
			{
			p=pq.peek();
			p.burstNow=p.burstTime;
			p.burstTime=p.burstTime-(now-pnow);
			pq.remove();
			runningQueue.add(p);
			}
			while(p.burstTime<0&&!pq.isEmpty())
			{
				if(!pq.isEmpty())
				{
					
				pnow=p.burstTime+now;
				p=pq.peek();
				p.burstNow=p.burstTime;
				p.burstTime=p.burstTime-(now-pnow);
				pq.remove();
				runningQueue.add(p);
				}
				
			}
							
			if(p.burstTime>0)
			{
				ProcessInfo p2=new ProcessInfo();
				p2.processName=p.processName;
				p2.burstNow=p.burstTime;
				p2.arrivalTime=p.arrivalTime;
				p2.burstTime=p.burstTime;
				p2.originalBurstTime=p.originalBurstTime;
				p2.priority=p.priority;
				pq.add(p2);
			}
						
			
		}
		
		while(!pq.isEmpty())
		{
			pq.peek().exit=1;
			runningQueue.add(pq.peek());
			pq.remove();
			
		}
		
		while(!runningQueue.isEmpty())
		{
			finalV.add(runningQueue.peek());
			runningQueue.remove();
		}
		
		setTime3();
		setWaitingTimeRB();
	}
	
	private void setTime2(double qtime)
	{
		double now=finalV.elementAt(0).arrivalTime; //Now Equal Start Time
		finalV.elementAt(0).startTime=finalV.elementAt(0).arrivalTime;//First Process StartTime=ArrivalTime
		
		if(finalV.elementAt(0).burstTime>=0)//burstTime>0 means remaining burstTime was >qTime 
			finalV.elementAt(0).finishTime=finalV.elementAt(0).startTime+qtime; //finishTime=startTime+qTime
		else //burstTime<0 means remaining burstTime was <qTime
			finalV.elementAt(0).finishTime=finalV.elementAt(0).startTime+finalV.elementAt(0).burstTime+qtime;//finishTime=startTime+(burstTime(-ve)+qTime)=startTime+burstTime Before running
		
		//Set Exit Time
		if(finalV.elementAt(0).burstTime==0)  
			finalV.elementAt(0).exit=now+qtime;
		else if(finalV.elementAt(0).burstTime<0)
			finalV.elementAt(0).exit=now+finalV.elementAt(0).burstTime+qtime;
			
		
		for(int i=1;i<finalV.size();i++)
		{
			now=finalV.elementAt(i-1).finishTime;
			
			if(finalV.elementAt(i).arrivalTime<=now) //If there isn't  gap between prev. process & next process
			{
				finalV.elementAt(i).startTime=finalV.elementAt(i-1).finishTime;//StartTime=FinishTime Of Prev. Process
				if(finalV.elementAt(i).burstTime>=0)//burstTime>0 means remaining burstTime was >qTime
				finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+qtime;//finishTime=startTime+qTime
				else //burstTime<0 means remaining burstTime was <qTime
				finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime+qtime;//finishTime=startTime+(burstTime(-ve)+qTime)=startTime+burstTime Before running
				//Set Exit Time
				if(finalV.elementAt(i).burstTime==0)
					finalV.elementAt(i).exit=finalV.elementAt(i).startTime+qtime;
				else if(finalV.elementAt(i).burstTime<0)
					finalV.elementAt(i).exit=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime+qtime;		
			}
			else //If there is  gap between prev. process & next process
			{
				finalV.elementAt(i).startTime=finalV.elementAt(i).arrivalTime;//StartTime=ArrivalTime
				    if(finalV.elementAt(i).burstTime>=0)
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+qtime;
					else 
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime+qtime;
					
				    if(finalV.elementAt(i).burstTime==0)  
						finalV.elementAt(i).exit=finalV.elementAt(i).startTime+qtime;
					else if(finalV.elementAt(i).burstTime<0)
						finalV.elementAt(i).exit=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime+qtime;
				
			}				
		}
		
		
	}
	
	
	
	
	void setTime3() 
	{
		finalV.elementAt(0).startTime=finalV.elementAt(0).arrivalTime;
		if(finalV.elementAt(0).originalBurstTime==finalV.elementAt(0).burstTime)
		{
			finalV.elementAt(0).finishTime=finalV.elementAt(0).startTime+finalV.elementAt(0).burstTime;
			finalV.elementAt(0).exit=finalV.elementAt(0).finishTime;
		}
		else if(finalV.elementAt(0).burstTime<=0)
		{
			finalV.elementAt(0).finishTime=finalV.elementAt(0).startTime+finalV.elementAt(0).burstNow;
			finalV.elementAt(0).exit=finalV.elementAt(0).finishTime;
		}
		else if(finalV.elementAt(0).burstTime>0&&finalV.elementAt(0).exit!=1)
			finalV.elementAt(0).finishTime=finalV.elementAt(0).startTime+(finalV.elementAt(0).burstNow-finalV.elementAt(0).burstTime);
		else if(finalV.elementAt(0).exit==1)
		{
			finalV.elementAt(0).finishTime=finalV.elementAt(0).startTime+finalV.elementAt(0).burstTime;
			finalV.elementAt(0).exit=finalV.elementAt(0).finishTime;
		}	
		for(int i=1;i<finalV.size();i++)
		{
			double now=finalV.elementAt(i-1).finishTime;
			if(finalV.elementAt(i).arrivalTime<=now)
			{
				finalV.elementAt(i).startTime=finalV.elementAt(i-1).finishTime;
				if(finalV.elementAt(i).originalBurstTime==finalV.elementAt(i).burstTime)
				{
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime;
					finalV.elementAt(i).exit=finalV.elementAt(i).finishTime;
					
				}
				else if(finalV.elementAt(i).burstTime<=0)
				{
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstNow;
					finalV.elementAt(i).exit=finalV.elementAt(i).finishTime;
				}
				else if(finalV.elementAt(i).burstTime>0&&finalV.elementAt(i).exit!=1)
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+(finalV.elementAt(i).burstNow-finalV.elementAt(i).burstTime);
				else if(finalV.elementAt(i).exit==1)
				{
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime;
					finalV.elementAt(i).exit=finalV.elementAt(i).finishTime;
				}
								
			}
			else
			{
				finalV.elementAt(i).startTime=finalV.elementAt(i).arrivalTime;
				if(finalV.elementAt(i).originalBurstTime==finalV.elementAt(i).burstTime)
				{
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime;
					finalV.elementAt(i).exit=finalV.elementAt(i).finishTime;
				}
				else if(finalV.elementAt(i).burstTime<=0)
				{
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstNow;
					finalV.elementAt(i).exit=finalV.elementAt(i).finishTime;
				}
				else if(finalV.elementAt(i).burstTime>0&&finalV.elementAt(i).exit!=1)
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+(finalV.elementAt(i).burstNow-finalV.elementAt(i).burstTime);
				else if(finalV.elementAt(i).exit==1)
				{
					finalV.elementAt(i).finishTime=finalV.elementAt(i).startTime+finalV.elementAt(i).burstTime;
					finalV.elementAt(i).exit=finalV.elementAt(i).finishTime;
				}
			
			}
			
		}
	}
	
	public double getWaitingTime()
	{
		double waitingTime=0;
		double avaregeWaitingTime;
		for(int i=0;i<finalV.size();i++)
		{
			if(finalV.elementAt(i).waitingTime!=-1)
			waitingTime=waitingTime+finalV.elementAt(i).waitingTime;
		}
		
		avaregeWaitingTime=waitingTime/size;
		return avaregeWaitingTime;
	}
	
	public void setWaitingTimeRB()
	{
		for(int i=0;i<finalV.size();i++)
		{
			if(finalV.elementAt(i).exit!=-1)
			{
				finalV.elementAt(i).waitingTime=finalV.elementAt(i).exit-finalV.elementAt(i).arrivalTime-finalV.elementAt(i).originalBurstTime;
			}
		}
	}
	 
	
	
	

};
