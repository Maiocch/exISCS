package it.exercise.iscs.model;

public class FootbalTeam {
	
	private int id;
	private String name;
	private int golF;
	private int golA;
	
	public FootbalTeam() {
	}
	
	public FootbalTeam(int id, String name, int golF, int golA) {
		this.id = id;
		this.name = name;
		this.golF = golF;
		this.golA = golA;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGolF() {
		return golF;
	}
	public void setGolF(int golF) {
		this.golF = golF;
	}
	public int getGolA() {
		return golA;
	}
	public void setGolA(int golA) {
		this.golA = golA;
	}
	
	public int getDiffGolFA() {
		return  golF-golA;
	}

	@Override
	public String toString() {
		return "FootbalTeam [id=" + id + ", name=" + name + ", golF=" + golF + ", golA=" + golA + "]";
	}

	
}
