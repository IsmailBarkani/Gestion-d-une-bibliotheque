package application;

public class Historique {

		private int num_enrg;
		private String ISBN;
		private String titre;
		private String  type;
		private String code_adh;
		private String dateE;
		private String dateR;
		
		
		
		
		



		public Historique(int num_enrg, String iSBN, String titre, String type, String dateE, String dateR) {
			super();
			this.num_enrg = num_enrg;
			ISBN = iSBN;
			this.titre = titre;
			this.type = type;
			this.dateE = dateE;
			this.dateR = dateR;
		}
		public Historique() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getNum_enrg() {
			return num_enrg;
		}
		public void setNum_enrg(int num_enrg) {
			this.num_enrg = num_enrg;
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
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCode_adh() {
			return code_adh;
		}
		public void setCode_adh(String code_adh) {
			this.code_adh = code_adh;
		}
		public String getDateE() {
			return dateE;
		}
		public void setDateE(String dateE) {
			this.dateE = dateE;
		}
		public String getDateR() {
			return dateR;
		}
		public void setDateR(String dateR) {
			this.dateR = dateR;
		}
		
		
}
