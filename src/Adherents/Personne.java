package Adherents;

public class Personne extends Adherent {

	private String cin;
	private String metier;
	
	
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(String code_adh, String nom, String prenom, String adresse, String tel, int typeAdh,
			int nbr_eprunter, String metier) {
		super(code_adh, nom, prenom, adresse, tel, typeAdh, nbr_eprunter);
		this.cin = cin;
		this.metier = metier;
	}

	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getMetiere() {
		return metier;
	}
	public void setMetiere(String metier) {
		this.metier = metier;
	}
	
	
	
	
}
