package application;

public class Compte {
	private String pseudo_nom;
	private String mot_pass;
	private String Code_adh;
	private String telephone;
	private String adresse;
	private int typecompte;
	private int etat;
	
	
	
	
	
	
	
	public Compte(String pseudo_nom, String mot_pass) {
		super();
		this.pseudo_nom = pseudo_nom;
		this.mot_pass = mot_pass;
	}
	
	public Compte() {
		super();
	}
	
	
	
	
	public String getPseudo_nom() {
		return pseudo_nom;
	}
	public void setPseudo_nom(String pseudo_nom) {
		this.pseudo_nom = pseudo_nom;
	}
	public String getMot_pass() {
		return mot_pass;
	}
	public void setMot_pass(String mot_pass) {
		this.mot_pass = mot_pass;
	}

	public int getTypecompte() {
		return typecompte;
	}

	public void setTypecompte(int typecompte) {
		this.typecompte = typecompte;
	}

	public String getCode_adh() {
		return Code_adh;
	}

	public void setCode_adh(String code_adh) {
		Code_adh = code_adh;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	
	

}

