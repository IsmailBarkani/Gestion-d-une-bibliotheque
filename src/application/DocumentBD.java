package application;

import java.io.IOException;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Adherents.Etudiant;
import Documents.Document;
import Documents.Livre;
import Documents.Magazine;
import Documents.Dictionnaire;

public class DocumentBD {

	public static Connection getConnection() {
		Connection con=null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/projet_java_bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		con = DriverManager.getConnection(url,"root","");
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public static ArrayList<String> getAuteurByISBN(String isbn){
		ArrayList<String> auteur = new ArrayList<String>();
		
		try {
			String sql = "select auteur1,auteur2,auteur3,auteur4 from liste_auteur where isbn=?";
			Connection con =DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,isbn);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {				
				auteur.add(resultSet.getString(1));
				auteur.add(resultSet.getString(2));
				auteur.add(resultSet.getString(3));
				auteur.add(resultSet.getString(4));
			}
			
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return auteur;
	}
	
	public static void modifAuteurByIsbn(String isbn, ArrayList<String> auteur) {
		try {
			String sql1 = "UPDATE liste_auteur set auteur1 =? where isbn =?";
			String sql2 = "UPDATE liste_auteur set auteur2 =? where isbn =?";
			String sql3 = "UPDATE liste_auteur set auteur3 =? where isbn =?";
			String sql4 = "UPDATE liste_auteur set auteur4 =? where isbn =?";
			
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(sql1);
			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
			PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);
			PreparedStatement preparedStatement4 = (PreparedStatement) con.prepareStatement(sql4);

			preparedStatement1.setString(1,auteur.get(0));
			preparedStatement1.setString(2,isbn);
			
			preparedStatement2.setString(1,auteur.get(1));
			preparedStatement2.setString(2,isbn);
			
			preparedStatement3.setString(1,auteur.get(2));
			preparedStatement3.setString(2,isbn);
			
			preparedStatement4.setString(1,auteur.get(3));
			preparedStatement4.setString(2,isbn);

			preparedStatement1.executeUpdate();
			preparedStatement2.executeUpdate();
			preparedStatement3.executeUpdate();
			preparedStatement4.executeUpdate();

			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Livre////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	
	public static int LivreEmprunter(Livre doc, String code_adh) {
		int st2=0,st1=0;
		try {
			String sql1 = "UPDATE document set code_adh =? where num_enrg =?";
			String sql2 = "UPDATE Adherent set nbr_emprunter = nbr_emprunter + 1 where code_adh=?";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(sql1);
			preparedStatement1.setString(1,code_adh);
			preparedStatement1.setInt(2,((Livre) doc).getNumeroEnrg());
			st1 = preparedStatement1.executeUpdate();
			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
			preparedStatement2.setString(1,code_adh);
			st2 = preparedStatement2.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st1*st2;
	}
	
	
	public static Livre getLivreByISBN(String isbn) throws IOException,SQLException{
		Livre doc=new Livre();
		try {
			String sql = "select num_enrg,isbn,titre,editeur,annee,type_doc FROM document WHERE isbn=? and code_adh is null";
			Connection con =DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,isbn);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {				
				doc.setNumeroEnrg(resultSet.getInt(1));
				doc.setISBN(resultSet.getString(2));
				doc.setTitre(resultSet.getString(3));
				doc.setEditeur(resultSet.getString(4));
				doc.setAnnee(resultSet.getInt(5));
				doc.setType_doc(resultSet.getInt(6));
			}
			
			
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	//gHADI NZID HAD LA METHODE KHISISAN L MODIF GHIR BACH MAYATKHARBA9CH,MANBA3D 7AWAL T7AYDO
	public static Livre getLivreByISBNN(String isbn) throws IOException,SQLException{
		Livre doc=new Livre();
		try {
			String sql = "select num_enrg,document.isbn,titre,editeur,annee,nbr_page,tome_livre,type_livre,count(num_enrg) FROM livre,document WHERE document.isbn=livre.isbn and document.isbn=? and code_adh is null";
			Connection con =DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

			preparedStatement.setString(1,isbn);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {				
				doc.setNumeroEnrg(resultSet.getInt(1));
				doc.setISBN(resultSet.getString(2));
				doc.setTitre(resultSet.getString(3));
				doc.setEditeur(resultSet.getString(4));
				doc.setAnnee(resultSet.getInt(5));
				doc.setNbrpage(resultSet.getInt(6));
				doc.setTome(resultSet.getString(7));
				doc.setType(resultSet.getString(8));
				doc.setNombreExemplaire(resultSet.getInt(9));
			}else {
				return null;
			}
	
			
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static String getIsbnBynum(int num) throws IOException,SQLException{
		String isbn=null;
		try {
			String sql = "select isbn  FROM document WHERE num_enrg=? and code_adh is null";
			Connection con =DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

			preparedStatement.setInt(1,num);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {				
				isbn=resultSet.getString(1);
			}
			
			
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isbn;
	}
	public static int updateLivre(Livre livre)throws IOException,SQLException {
		int st1 =0,st2=0;
		try {
			String sql1 = "UPDATE Document set  titre=?,editeur=?, annee=? where isbn=?";
			String sql2 = "UPDATE livre set nbr_page=?, type_livre=?, tome_livre=? where isbn=?";

			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);

			preparedStatement.setString(1,livre.getTitre());
			preparedStatement.setString(2,livre.getEditeur());
			preparedStatement.setInt(3,livre.getAnnee());
			preparedStatement.setString(4,livre.getISBN());
			
			preparedStatement2.setInt(1,livre.getNbrpage());
			preparedStatement2.setString(2,livre.getType());
			preparedStatement2.setString(3,livre.getTome());
			preparedStatement2.setString(4,livre.getISBN());
			
			st1 = preparedStatement.executeUpdate();
			st2 = preparedStatement2.executeUpdate();

			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st1*st2;
		
	}
	
	public static int supprimerLivre(String isbn)throws IOException,SQLException {
		int st1 =0,st2=0,st3=0;
		try {
			String sql1 = "DELETE from Document where isbn=?";
			String sql2 = "DELETE from Livre where isbn=?";
			String sql3 = "DELETE from liste_auteur where isbn=?";

			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
			PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);

			
			preparedStatement.setString(1,isbn);
			preparedStatement2.setString(1,isbn);
			preparedStatement3.setString(1,isbn);
			
			st1 = preparedStatement.executeUpdate();
			st2 = preparedStatement2.executeUpdate();
			st3 = preparedStatement3.executeUpdate();


			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st1*st2*st3;
		
	}
	
	public static int supprimerLivreOccurance(int num)throws IOException,SQLException {
		int st1 =0;
		try {
			String sql1 = "DELETE from Document where num_enrg=?";


			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
		
			preparedStatement.setInt(1,num);
			st1 = preparedStatement.executeUpdate();
		
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st1;
		
	}
	
	public static int saveLivre(Document livre)throws IOException,SQLException {
		int i,st1 =0,st2=0,st3=0;
		try {
			String sql = "INSERT INTO Document (isbn,titre,editeur,annee) Values (?,?,?,?)";
			String sql2 = "INSERT INTO Livre (isbn,nbr_page,type_livre,tome_livre) Values (?,?,?,?)";
			String sql3 = "INSERT INTO liste_auteur (ISBN,auteur1,auteur2,auteur3,auteur4) values (?,?,?,?,?)";

			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
			PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);

			preparedStatement.setString(1,livre.getISBN());
			preparedStatement.setString(2,livre.getTitre());
			preparedStatement.setString(3,livre.getEditeur());
			preparedStatement.setInt(4,livre.getAnnee());
			
			preparedStatement2.setString(1,livre.getISBN());
			preparedStatement2.setInt(2,((Livre) livre).getNbrpage());
			preparedStatement2.setString(3,((Livre) livre).getType());
			preparedStatement2.setString(4,((Livre) livre).getTome());
			
			preparedStatement3.setString(1,livre.getISBN());
			preparedStatement3.setString(2,livre.getAuteur().get(0));
			preparedStatement3.setString(3,livre.getAuteur().get(1));
			preparedStatement3.setString(4,livre.getAuteur().get(2));
			preparedStatement3.setString(5,livre.getAuteur().get(3));
			
			st1 = preparedStatement.executeUpdate();
			for(i=0;i<livre.getNombreExemplaire()-1;i++) {
			st1 *= preparedStatement.executeUpdate();
			}
		st2 = preparedStatement2.executeUpdate();
			st3 = preparedStatement3.executeUpdate();

			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st1*st2*st3;
	}
	
	
	public static String statistiqueLivre(String code){
		int totalLivre=-1;
		try {
			String sql1 = "select count(type) from historique where type=0 and code_adh=?";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
			preparedStatement.setString(1,code);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				totalLivre=resultSet.getInt(1);
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Integer.toString(totalLivre);
	}
	
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Magazine////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

		public static int magazineEmprunter(Magazine doc, String code_adh) {
		int st2=0,st1=0;
		try {
		String sql1 = "UPDATE document set code_adh =? where num_enrg =?";
		String sql2 = "UPDATE Adherent set nbr_emprunter = nbr_emprunter + 1 where code_adh=?";
		Connection con = DocumentBD.getConnection();
		PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(sql1);
		preparedStatement1.setString(1,code_adh);
		preparedStatement1.setInt(2,doc.getNumeroEnrg());
		st1 = preparedStatement1.executeUpdate();
		PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
		preparedStatement2.setString(1,code_adh);
		st2 = preparedStatement2.executeUpdate();
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return st1*st2;
		}
		
		public static Magazine getMagazineByISBN(String isbn) throws IOException,SQLException{
		Magazine doc=new Magazine();
		try {
		String sql = "select num_enrg,isbn,titre,editeur,annee,type_doc FROM document WHERE isbn=? and code_adh is null";
		Connection con =DocumentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setString(1,isbn);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {				
		System.out.println("fdfez");
		doc.setNumeroEnrg(resultSet.getInt(1));
		doc.setISBN(resultSet.getString(2));
		doc.setTitre(resultSet.getString(3));
		doc.setEditeur(resultSet.getString(4));
		doc.setAnnee(resultSet.getInt(5));
		doc.setType_doc(resultSet.getInt(6));
		}
		
		
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return doc;
		}
		
		//Achanger
		public static Magazine getMagazineByISBNN(String isbn) throws IOException,SQLException{
			Magazine doc=new Magazine();
			try {
				String sql = "select num_enrg,document.isbn,titre,editeur,annee,periode,date_edit,count(num_enrg) FROM magazin,document WHERE document.isbn=magazin.isbn and document.isbn=? and code_adh is null";
				Connection con =DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

				preparedStatement.setString(1,isbn);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {				
					doc.setNumeroEnrg(resultSet.getInt(1));
					doc.setISBN(resultSet.getString(2));
					doc.setTitre(resultSet.getString(3));
					doc.setEditeur(resultSet.getString(4));
					doc.setAnnee(resultSet.getInt(5));
					doc.setPeriode(resultSet.getInt(6));
					doc.setDate_edit(resultSet.getString(7));
					doc.setNombreExemplaire(resultSet.getInt(8));
				}else {
					return null;
				}
		
				
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return doc;
		}
		
		
		public static int updateMagazine(Magazine magazine)throws IOException,SQLException {
			int st1 =0,st2=0;
			try {
				String sql1 = "UPDATE Document set  titre=?,editeur=?, annee=? where isbn=?";
				String sql2 = "UPDATE magazin set periode=?, date_edit=? where isbn=?";

				Connection con = DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
				PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);

				preparedStatement.setString(1,magazine.getTitre());
				preparedStatement.setString(2,magazine.getEditeur());
				preparedStatement.setInt(3,magazine.getAnnee());
				preparedStatement.setString(4,magazine.getISBN());
				
				preparedStatement2.setInt(1,magazine.getPeriode());
				preparedStatement2.setString(2,magazine.getDate_edit());
				preparedStatement2.setString(3,magazine.getISBN());
				
				st1 = preparedStatement.executeUpdate();
				st2 = preparedStatement2.executeUpdate();

				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return st1*st2;
			
		}
		
		public static int supprimerMagazine(String isbn)throws IOException,SQLException {
			int st1 =0,st2=0,st3=0;
			try {
				String sql1 = "DELETE from Document where isbn=?";
				String sql2 = "DELETE from magazin where isbn=?";
				String sql3 = "DELETE from liste_auteur where isbn=?";

				Connection con = DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
				PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
				PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);

				
				preparedStatement.setString(1,isbn);
				preparedStatement2.setString(1,isbn);
				preparedStatement3.setString(1,isbn);
				
				st1 = preparedStatement.executeUpdate();
				st2 = preparedStatement2.executeUpdate();
				st3 = preparedStatement3.executeUpdate();


				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return st1*st2*st3;
			
		}
		
		public static int supprimerMagazineOccurance(int num)throws IOException,SQLException {
			int st1 =0;
			try {
				String sql1 = "DELETE from Document where num_enrg=?";


				Connection con = DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
			
				preparedStatement.setInt(1,num);
				st1 = preparedStatement.executeUpdate();
			
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return st1;
			
		}
		
		
		public static int saveMagazine(Magazine magazine)throws IOException,SQLException {
			int i,st1 =0,st2=0,st3=0;
			try {
				System.out.println("here");
				String sql = "INSERT INTO Document (isbn,titre,editeur,annee,type_doc) Values (?,?,?,?,?)";
				String sql2 = "INSERT INTO magazin (isbn,periode,date_edit) Values (?,?,?)";
				String sql3 = "INSERT INTO liste_auteur (ISBN,auteur1,auteur2,auteur3,auteur4) values (?,?,?,?,?)";

				Connection con = DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
				PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);

				preparedStatement.setString(1,magazine.getISBN());
				preparedStatement.setString(2,magazine.getTitre());
				preparedStatement.setString(3,magazine.getEditeur());
				preparedStatement.setInt(4,magazine.getAnnee());
				preparedStatement.setInt(5,1);
				
				preparedStatement2.setString(1,magazine.getISBN());
				preparedStatement2.setInt(2,magazine.getPeriode());
				preparedStatement2.setString(3,magazine.getDate_edit());
				
				preparedStatement3.setString(1,magazine.getISBN());
				preparedStatement3.setString(2,magazine.getAuteur().get(0));
				preparedStatement3.setString(3,magazine.getAuteur().get(1));
				preparedStatement3.setString(4,magazine.getAuteur().get(2));
				preparedStatement3.setString(5,magazine.getAuteur().get(3));
				
				st1 = preparedStatement.executeUpdate();
				for(i=0;i<magazine.getNombreExemplaire()-1;i++) {
				st1 *= preparedStatement.executeUpdate();
				}
			st2 = preparedStatement2.executeUpdate();
				st3 = preparedStatement3.executeUpdate();

				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return st1*st2*st3;
		}
		
		public static String statistiqueMagazine(String code){
		int totalLivre=-1;
		try {
		String sql1 = "select count(type) from historique where type=1 and code_adh=?";
		Connection con = DocumentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
		preparedStatement.setString(1,code);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
		totalLivre=resultSet.getInt(1);
		}
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		return Integer.toString(totalLivre);
		}

		
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Dictionnaire////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////

	public static int dictionnaireEmprunter(Dictionnaire doc, String code_adh) {
	int st2=0,st1=0;
	try {
	String sql1 = "UPDATE document set code_adh =? where num_enrg =?";
	String sql2 = "UPDATE Adherent set nbr_emprunter = nbr_emprunter + 1 where code_adh=?";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(sql1);
	preparedStatement1.setString(1,code_adh);
	preparedStatement1.setInt(2,doc.getNumeroEnrg());
	st1 = preparedStatement1.executeUpdate();
	PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
	preparedStatement2.setString(1,code_adh);
	st2 = preparedStatement2.executeUpdate();
	con.close();
	}catch(SQLException e) {
	e.printStackTrace();
	}
	return st1*st2;
	}
	
	public static Dictionnaire getDictionnaireByISBN(String isbn) throws IOException,SQLException{
	Dictionnaire doc=new Dictionnaire();
	try {
	String sql = "select num_enrg,isbn,titre,editeur,annee,type_doc FROM document WHERE isbn=? and code_adh is null";
	Connection con =DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1,isbn);
	ResultSet resultSet = preparedStatement.executeQuery();
	if(resultSet.next()) {				
	doc.setNumeroEnrg(resultSet.getInt(1));
	doc.setISBN(resultSet.getString(2));
	doc.setTitre(resultSet.getString(3));
	doc.setEditeur(resultSet.getString(4));
	doc.setAnnee(resultSet.getInt(5));
	doc.setType_doc(resultSet.getInt(6));
	}
	
	
	con.close();
	}catch(SQLException e) {
	e.printStackTrace();
	}
	return doc;
	}
	
	public static String statistiqueDictionnaire(String code){
	int totalLivre=-1;
	try {
	String sql1 = "select count(type) from historique where type=2 and code_adh=?";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
	preparedStatement.setString(1,code);
	ResultSet resultSet = preparedStatement.executeQuery();
	if(resultSet.next()) {
	totalLivre=resultSet.getInt(1);
	}
	con.close();
	}catch(SQLException e) {
	e.printStackTrace();
	}
	return Integer.toString(totalLivre);
	}

	//Achanger
			public static Dictionnaire getDictionnaireByISBNN(String isbn) throws IOException,SQLException{
				Dictionnaire doc=new Dictionnaire();
				try {
					String sql = "select num_enrg,document.isbn,titre,editeur,annee,langue_dic,tome,count(num_enrg) FROM dictionnaire,document WHERE document.isbn=dictionnaire.isbn and document.isbn=? and code_adh is null";
					Connection con =DocumentBD.getConnection();
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);

					preparedStatement.setString(1,isbn);
					
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next()) {				
						doc.setNumeroEnrg(resultSet.getInt(1));
						doc.setISBN(resultSet.getString(2));
						doc.setTitre(resultSet.getString(3));
						doc.setEditeur(resultSet.getString(4));
						doc.setAnnee(resultSet.getInt(5));
						doc.setLangue(resultSet.getString(6));
						doc.setTome(resultSet.getString(7));
						doc.setNombreExemplaire(resultSet.getInt(8));
					}else {
						return null;
					}
			
					
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return doc;
			}
			
			
			public static int updateDictionnaire(Dictionnaire dictionnaire)throws IOException,SQLException {
				int st1 =0,st2=0;
				try {
					String sql1 = "UPDATE Document set  titre=?,editeur=?, annee=? where isbn=?";
					String sql2 = "UPDATE dictionnaire set langue_dic=?, tome=? where isbn=?";

					Connection con = DocumentBD.getConnection();
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
					PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);

					preparedStatement.setString(1,dictionnaire.getTitre());
					preparedStatement.setString(2,dictionnaire.getEditeur());
					preparedStatement.setInt(3,dictionnaire.getAnnee());
					preparedStatement.setString(4,dictionnaire.getISBN());
					
					preparedStatement2.setString(1,dictionnaire.getLangue());
					preparedStatement2.setString(2,dictionnaire.getTome());
					preparedStatement2.setString(3,dictionnaire.getISBN());
					
					st1 = preparedStatement.executeUpdate();
					st2 = preparedStatement2.executeUpdate();

					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return st1*st2;
				
			}
			
			public static int supprimerDictionnaire(String isbn)throws IOException,SQLException {
				int st1 =0,st2=0,st3=0;
				try {
					String sql1 = "DELETE from Document where isbn=?";
					String sql2 = "DELETE from dictionnaire where isbn=?";
					String sql3 = "DELETE from liste_auteur where isbn=?";

					Connection con = DocumentBD.getConnection();
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
					PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
					PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);

					
					preparedStatement.setString(1,isbn);
					preparedStatement2.setString(1,isbn);
					preparedStatement3.setString(1,isbn);
					
					st1 = preparedStatement.executeUpdate();
					st2 = preparedStatement2.executeUpdate();
					st3 = preparedStatement3.executeUpdate();


					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return st1*st2*st3;
				
			}
			
			public static int supprimerOccurance(int num)throws IOException,SQLException {
				int st1 =0;
				try {
					String sql1 = "DELETE from Document where num_enrg=?";


					Connection con = DocumentBD.getConnection();
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql1);
				
					preparedStatement.setInt(1,num);
					st1 = preparedStatement.executeUpdate();
				
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return st1;
				
			}

public static int saveDictionnaire(Dictionnaire dictionnaire)throws IOException,SQLException {
	int i,st1 =0,st2=0,st3=0;
	try {
		System.out.println("here");
		String sql = "INSERT INTO Document (isbn,titre,editeur,annee,type_doc) Values (?,?,?,?,?)";
		String sql2 = "INSERT INTO dictionnaire (isbn,langue_dic,tome) Values (?,?,?)";
		String sql3 = "INSERT INTO liste_auteur (ISBN,auteur1,auteur2,auteur3,auteur4) values (?,?,?,?,?)";

		Connection con = DocumentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
		PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);

		preparedStatement.setString(1,dictionnaire.getISBN());
		preparedStatement.setString(2,dictionnaire.getTitre());
		preparedStatement.setString(3,dictionnaire.getEditeur());
		preparedStatement.setInt(4,dictionnaire.getAnnee());
		preparedStatement.setInt(5,2);

		
		preparedStatement2.setString(1,dictionnaire.getISBN());
		preparedStatement2.setString(2,dictionnaire.getLangue());
		preparedStatement2.setString(3,dictionnaire.getTome());
		
		preparedStatement3.setString(1,dictionnaire.getISBN());
		preparedStatement3.setString(2,dictionnaire.getAuteur().get(0));
		preparedStatement3.setString(3,dictionnaire.getAuteur().get(1));
		preparedStatement3.setString(4,dictionnaire.getAuteur().get(2));
		preparedStatement3.setString(5,dictionnaire.getAuteur().get(3));
		
		st1 = preparedStatement.executeUpdate();
		for(i=0;i<dictionnaire.getNombreExemplaire()-1;i++) {
		st1 *= preparedStatement.executeUpdate();
		}
	st2 = preparedStatement2.executeUpdate();
		st3 = preparedStatement3.executeUpdate();

		con.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return st1*st2*st3;
}


//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Historique////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	public static int ajouterHistorique(Document doc, String code_adh) {
		int st1=0;
		DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		try {
			String sql1 = "INSERT INTO  historique (num_enrg,code_adh,ISBN,titre,type,date_debut) values(?,?,?,?,?,?)";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(sql1);
			preparedStatement1.setInt(1,doc.getNumeroEnrg());
			preparedStatement1.setString(2,code_adh);
			preparedStatement1.setString(3,doc.getISBN());
			preparedStatement1.setString(4,doc.getTitre());
			preparedStatement1.setInt(5,doc.getType_doc());
			preparedStatement1.setString(6,format.format(date));
			st1 = preparedStatement1.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st1;
	}
	
	
	
	public static void retour(int num, String code_adh)throws IOException,SQLException{
		DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		try {
			String sql1 = "UPDATE document set code_adh =null where num_enrg =?";
			String sql2 = "UPDATE Adherent set nbr_emprunter = nbr_emprunter -1  where code_adh=?";
			String sql3 = "UPDATE historique set date_fin = ?  where num_enrg=?";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(sql1);
			preparedStatement1.setInt(1,num);
			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
			preparedStatement2.setString(1,code_adh);
			PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sql3);
			preparedStatement3.setString(1,format.format(date));
			preparedStatement3.setInt(2,num);
			preparedStatement1.executeUpdate();
			preparedStatement2.executeUpdate();
			preparedStatement3.executeUpdate();
			con.close();
		}catch(SQLException event) {
			event.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static ArrayList<Livre> getDocByCode(String code) throws IOException,SQLException{
		ArrayList<Livre> list = new ArrayList<Livre>();
		try {
			String sql = "select * from Document where code_adh=?";
			Connection con =CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,code);
			ResultSet resultSet = preparedStatement.executeQuery();
			Livre doc=new Livre();
			while(resultSet.next()) {
				doc.setNumeroEnrg(resultSet.getInt(1));
				doc.setISBN(resultSet.getString(2));
				doc.setTitre(resultSet.getString(3));
				list.add((Livre) doc);
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
//	public static int EmpruntIncr(Document doc, String code_adh) {
//		int st2=0;
//		try {
//			String sql2 = "UPDATE Adherent set nbr_emprunter = nbr_emprunter + 1 where code_adh=?";
//			Connection con = DocumentBD.getConnection();
//			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sql2);
//			preparedStatement2.setString(1,code_adh);
//			st2 = preparedStatement1.executeUpdate();
//			con.close();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return st2;
//	}
	
}
