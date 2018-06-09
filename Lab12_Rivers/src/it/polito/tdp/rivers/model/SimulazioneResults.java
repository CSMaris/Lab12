package it.polito.tdp.rivers.model;

public class SimulazioneResults {
	private int outdays;
	private double avgC;
	
     public SimulazioneResults(int outdays, double avgC) {
		
		this.outdays = outdays;
		this.avgC = avgC;
	   }
	
	
	public int getOutdays() {
		return outdays;
	}

	public double getAvgC() {
		return avgC;
	}

	
	

}
