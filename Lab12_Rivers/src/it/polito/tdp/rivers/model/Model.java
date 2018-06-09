package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private List<River> rivers;
	private Simulazione sim;

	public Model() {
		this.dao = new RiversDAO();
		rivers=new ArrayList<>(dao.getAllRivers());
		
	}
	
	
	public LocalDate getMAX(int id)
	{
		return dao.getMaxDateRiver(id);
	}
	
	public LocalDate getMIN(int id)
	{
		return dao.getMinDateRiver(id);
	}
	
	public double getAVG(int id)
	{
		return dao.getAvgRiver(id);
	}
	
	public int getCOUNT(int id)
	{
		return dao.getTotRiver(id);
	}
	
	public List<River> getRivers(){
		return rivers;
	}
	public List<Flow> getFlows(int id){
		return dao.getFlows(id);
	}
	
	public SimulazioneResults getResults(float k, double fmed, int numGiorni, List<Flow> flows) {
		
		sim=new Simulazione(k,fmed,numGiorni,flows);
		sim.init();
		
		return sim.run();
	}
	
	

}
