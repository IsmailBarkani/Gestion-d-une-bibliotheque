package Documents;

import java.util.List;

public abstract class Document {
	protected int numeroEnrg;
	private int type_doc;
	private  String ISBN;
	private String titre;
	private List<String> auteur;
	private String editeur;
	private int annee;
	private int nombreExemplaire;
	private String auteur1;
	private String auteur2;
	private String auteur3;
	private String auteur4;
	
	

	public Document(String iSBN, String titre, String editeur, int annee,int nombreExemplaire) {
		super();
		ISBN = iSBN;
		this.titre = titre;
		this.editeur = editeur;
		this.annee = annee;
		this.nombreExemplaire = nombreExemplaire;
	}
	
	public Document(String iSBN, String titre, String editeur, int annee,int nombreExemplaire,String auteur1,String auteur2,String auteur3,String auteur4) {
		super();
		ISBN = iSBN;
		this.titre = titre;
		this.editeur = editeur;
		this.annee = annee;
		this.nombreExemplaire = nombreExemplaire;
		this.auteur1 = auteur1;
		this.auteur2 = auteur2;
		this.auteur3 = auteur3;
		this.auteur4 = auteur4;
	}
	
	
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNumeroEnrg() {
		return numeroEnrg;
	}
	public void setNumeroEnrg(int numeroEnrg) {
		this.numeroEnrg = numeroEnrg;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<String> getAuteur() {
		return auteur;
	}
	public void setAuteur(List<String> auteur) {
		this.auteur = auteur;
	}
	public String getEditeur() {
		return editeur;
	}
	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNombreExemplaire() {
		return nombreExemplaire;
	}
	public void setNombreExemplaire(int nombreExemplaire) {
		this.nombreExemplaire = nombreExemplaire;
	}
	public int getType_doc() {
		return type_doc;
	}
	public void setType_doc(int type_doc) {
		this.type_doc = type_doc;
	}

	public String getAuteur1() {
		return auteur1;
	}

	public void setAuteur1(String auteur1) {
		this.auteur1 = auteur1;
	}

	public String getAuteur2() {
		return auteur2;
	}

	public void setAuteur2(String auteur2) {
		this.auteur2 = auteur2;
	}

	public String getAuteur3() {
		return auteur3;
	}

	public void setAuteur3(String auteur3) {
		this.auteur3 = auteur3;
	}

	public String getAuteur4() {
		return auteur4;
	}

	public void setAuteur4(String auteur4) {
		this.auteur4 = auteur4;
	}
	
	
	
	

}
