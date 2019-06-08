package Documents;

public class Dictionnaire extends Document {
	private String langue;
	private String tome;
	
	
	public Dictionnaire(String iSBN, String titre, String editeur, int annee, int nombreExemplaire, String langue,
			String tome) {
		super(iSBN, titre, editeur, annee, nombreExemplaire);
		this.langue = langue;
		this.tome = tome;
	}

	
	public Dictionnaire(String iSBN, String titre, String editeur, int annee, int nombreExemplaire, String langue,
			String tome,String auteur1,String auteur2,String auteur3,String auteur4) {
		super( iSBN, titre, editeur, annee, nombreExemplaire, auteur1,auteur2, auteur3,auteur4);
		this.langue = langue;
		this.tome = tome;
		
	}
	

	public Dictionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Dictionnaire(String iSBN, String titre, String editeur, int annee, int nombreExemplaire) {
		super(iSBN, titre, editeur, annee, nombreExemplaire);
		// TODO Auto-generated constructor stub
	}


	public String getLangue() {
		return langue;
	}


	public void setLangue(String langue) {
		this.langue = langue;
	}


	public String getTome() {
		return tome;
	}


	public void setTome(String tome) {
		this.tome = tome;
	}
	
	
	
	
	

}
