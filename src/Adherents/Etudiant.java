package Adherents;

public class Etudiant extends Adherent {
	private String cne;
	private String filiere;
	
	
	
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String code_adh, String nom, String prenom, String adresse, String tel, int typeAdh,
			int nbr_eprunter, String filiere) {
		super(code_adh, nom, prenom, adresse, tel, typeAdh, nbr_eprunter);
		this.filiere = filiere;
	}

	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	
	
	
	
}
