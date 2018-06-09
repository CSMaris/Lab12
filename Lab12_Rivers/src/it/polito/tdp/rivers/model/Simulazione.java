package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;



public class Simulazione {
	
	//parametri
	
	float k;
	double fmed;
	int numGiorni;
	List<Flow> eventi;
	
	//modello del mondo
		double C;
		
		//output
		double sumC;
		int outDays;
		SimulazioneResults result;
		
		//coda
		private PriorityQueue<Event> queue = new PriorityQueue<>();
		
		double Q;
		double Cin;
		double foutmin;
		float prob;
	
	public Simulazione(float k,double fmed,int numGiorni, List<Flow> eventi) {
		this.k=k;
		this.fmed=fmed;
		this.numGiorni=numGiorni;
		this.eventi=eventi;
		
		 Q=k*fmed*3600*24*30;
		 Cin=Q/2;
		 foutmin=fmed*(0.8);
		 prob=(float) 0.05;
	}
	
	public void init() {
		
		List<Flow> flows=new ArrayList<>(eventi);
		for(Flow f:flows) {
			Event e=new Event(EventType.IN,f.getDay(),f);
			queue.add(e);
		}
		
		C=Cin;
		sumC=0;
		outDays=0;	
	}
	
	public SimulazioneResults run() {
		Event e;
		while ((e = queue.poll()) != null) {
			processEvent(e);
		}
		result=new SimulazioneResults(outDays,(sumC/numGiorni));
		return result;
		
		
	}

	private void processEvent(Event e) {
		switch (e.getTipo()) {
			case IN:
				
				double finuscita=foutmin*3600*24;
				
				if(Math.random() <= prob)
					finuscita+=9*foutmin*3600*24;
				
				/*double surplus=C+e.getFlow().getFlow()*3600*24;
				
				if(surplus>=finuscita)
					C=surplus-finuscita;
				else {
					C=0;
					outDays++;
				}
				
				if(C>Q) {
					C=Q;
				}*/
				
				double surplus=C+e.getFlow().getFlow()*3600*24;
				if(surplus>Q) {
					C=Q;
				}
				else
					C=surplus;
				
				if(C>finuscita)
					C=C-finuscita;
				else {
					C=0;
					outDays++;
				}
					
				
				
				
				sumC+=C;
				
					
				break;
				
		}
		
	}
	
	

}
