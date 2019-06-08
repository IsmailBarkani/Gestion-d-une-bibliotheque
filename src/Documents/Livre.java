package Documents;

import java.util.List;

public class Livre extends Document{
	private int nbrpage;
	private String type;
	private String tome;
	
	
	
	
	
	
	
	




	public Livre( String iSBN, String titre, String editeur, int annee,
			 int nbrpage, String type, String tome,int nombreExemplaire) {
		super( iSBN, titre, editeur, annee, nombreExemplaire);
		this.nbrpage = nbrpage;
		this.type = type;
		this.tome = tome;
	}
	
	public Livre( String iSBN, String titre, String editeur, int annee,
			 int nbrpage, String type, String tome,int nombreExemplaire,String auteur1,String auteur2,String auteur3,String auteur4) {
		
		super( iSBN, titre, editeur, annee, nombreExemplaire, auteur1,auteur2, auteur3,auteur4);
		this.nbrpage = nbrpage;
		this.type = type;
		this.tome = tome;
	}

	public Livre() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getNbrpage() {
		return nbrpage;
	}



	public void setNbrpage(int nbrpage) {
		this.nbrpage = nbrpage;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getTome() {
		return tome;
	}



	public void setTome(String tome) {
		this.tome = tome;
	}
	
	@Override
	public int getNumeroEnrg() {
		return numeroEnrg;
	}
	@Override
	public void setNumeroEnrg(int numeroEnrg) {
		this.numeroEnrg = numeroEnrg;
	}

}
