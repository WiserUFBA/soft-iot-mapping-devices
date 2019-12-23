package br.ufba.dcc.wiser.soft_iot.entities;

public class Actuator {
	private String id;
	private String type;
	
	public Actuator(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
