package Adherents;

public class Professeur extends Adherent{

		private String cin;
		private String departement;
		
		
		public Professeur() {
			super();
			// TODO Auto-generated constructor stub
		}
	
		
		
		public Professeur(String code_adh, String nom, String prenom, String adresse, String tel, int typeAdh,
				int nbr_eprunter, String departement) {
			super(code_adh, nom, prenom, adresse, tel, typeAdh, nbr_eprunter);
			this.cin = cin;
			this.departement = departement;
		}



		public String getCin() {
			return cin;
		}
		public void setCin(String cin) {
			this.cin = cin;
		}
		public String getDepartement() {
			return departement;
		}
		public void setDepartement(String departement) {
			this.departement = departement;
		}
		
		
		
		
}
