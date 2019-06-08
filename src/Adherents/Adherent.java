package Adherents;




public abstract class Adherent {
	private String code_adh;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	private int typeAdh;
	private int nbr_eprunter;
	
	
	
	
	
	
	



	public Adherent(String code_adh, String nom, String prenom, String adresse, String tel, int typeAdh,
			int nbr_eprunter) {
		super();
		this.code_adh = code_adh;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.typeAdh = typeAdh;
		this.nbr_eprunter = nbr_eprunter;
	}



	public Adherent() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public int getTypeAdh() {
		return typeAdh;
	}



	public void setTypeAdh(int typeAdh) {
		this.typeAdh = typeAdh;
	}



	public String getCode_adh() {
		return code_adh;
	}



	public void setCode_adh(String code_adh) {
		this.code_adh = code_adh;
	}



	public int getNbr_eprunter() {
		return nbr_eprunter;
	}



	public void setNbr_eprunter(int nbr_eprunter) {
		this.nbr_eprunter = nbr_eprunter;
	}
	
	
	
	
}
