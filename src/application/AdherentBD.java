package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Adherents.Adherent;
import Adherents.Etudiant;
import Adherents.Personne;
import Adherents.Professeur;

public class AdherentBD {

	public static Connection getConnection() {
		Connection con=null;;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/projet_java_bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		con = DriverManager.getConnection(url,"root","");
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	
	
	public static  List<String> getAdhrents(int type){
		List<String> list = new ArrayList<String>();
		try {
			String sql = "select code_adh from Adherent where typeAdherent=?";
			
			Connection con =AdherentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setInt(1,type);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String id;
				id=resultSet.getString(1);
				list.add(id);
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
public static boolean saveEtudiant(Adherent adh) {
		
		int statue1 =0,statue2=0;
		try {
			Connection con = AdherentBD.getConnection();
			String sql1 ="INSERT INTO etudiant (cne,filiere) Values (?,?)";
			String sql2 ="INSERT INTO professeur (cin,departement) Values (?,?)";
			String sql3 ="INSERT INTO personne (cin,metier) Values (?,?)";
			PreparedStatement preparedStatementHieritage;
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql1);
					preparedStatementHieritage.setString(1,adh.getCode_adh());
					preparedStatementHieritage.setString(2,((Etudiant) adh).getFiliere());
					statue2 = preparedStatementHieritage.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return (statue1 > 0 && statue2 >0);
	}


public static boolean saveProfesseur(Adherent adh) {
	
	int statue1 =0,statue2=0;
	try {
		Connection con = AdherentBD.getConnection();
		String sql2 ="INSERT INTO professeur (cin,departement) Values (?,?)";
		PreparedStatement preparedStatementHieritage;
				preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql2);
				preparedStatementHieritage.setString(1,adh.getCode_adh());
				preparedStatementHieritage.setString(2,((Professeur) adh).getDepartement());
				statue2 = preparedStatementHieritage.executeUpdate();
		con.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return (statue1 > 0 && statue2 >0);
}


public static boolean savePersonne(Adherent adh) {
	
	int statue1 =0,statue2=0;
	try {
		Connection con = AdherentBD.getConnection();
		String sql3 ="INSERT INTO personne (cin,metier) Values (?,?)";
		PreparedStatement preparedStatementHieritage;
				preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql3);
				preparedStatementHieritage.setString(1,adh.getCode_adh());
				preparedStatementHieritage.setString(2,((Personne) adh).getMetiere());
				statue2 = preparedStatementHieritage.executeUpdate();
		con.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return (statue1 > 0 && statue2 >0);
}
	//
	public static boolean save(Adherent adh) {
		
		int statue1 =0,statue2=0;
		try {
			
			String sql = "INSERT INTO adherent (code_adh,nom,prenom,adresse,tel,typeAdherent) Values (?,?,?,?,?,?)";
			Connection con = AdherentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,adh.getCode_adh());
			preparedStatement.setString(2,adh.getNom());
			preparedStatement.setString(3,adh.getPrenom());
			preparedStatement.setString(4,adh.getAdresse());
			preparedStatement.setString(5,adh.getTel());
			preparedStatement.setInt(6,adh.getTypeAdh());
			statue1 = preparedStatement.executeUpdate();
			//Les cas possible
			String sql1 ="INSERT INTO etudiant (cne,filiere) Values (?,?)";
			String sql2 ="INSERT INTO professeur (cin,departement) Values (?,?)";
			String sql3 ="INSERT INTO personne (cin,metier) Values (?,?)";
			PreparedStatement preparedStatementHieritage;
			switch(adh.getTypeAdh()) {
			case 0: 
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql1);
					preparedStatementHieritage.setString(1,adh.getCode_adh());
					preparedStatementHieritage.setString(2,((Etudiant) adh).getFiliere());
					statue2 = preparedStatementHieritage.executeUpdate();break;
			case 1:
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql2);
					preparedStatementHieritage.setString(1,adh.getCode_adh());
					preparedStatementHieritage.setString(2,((Professeur) adh).getDepartement());
					statue2 = preparedStatementHieritage.executeUpdate();break;
			
			case 2:
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql3);
					preparedStatementHieritage.setString(1,adh.getCode_adh());
					preparedStatementHieritage.setString(2,((Personne) adh).getMetiere());
					statue2 = preparedStatementHieritage.executeUpdate();break;
		    
					default: break;
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return (statue1 > 0 && statue2 >0);
	}
	
	
	
	public static int getTypeByCode(String code)throws IOException,SQLException{
			int nbr;
			String sql = "select typeadherent from adherent where code_adh=?";
			Connection con =AdherentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,code);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				nbr =  resultSet.getInt(1);
				   //do other stuff
				} else {
					nbr=0;
				}
			con.close();
			return nbr;
		
	}
	
	public static Etudiant getEtudiantByCode(String code) throws IOException,SQLException{
		Etudiant adherent=new Etudiant();
		String sql = "select code_adh,nom,prenom,nbr_emprunter,adresse,tel,typeAdherent,filiere from adherent,etudiant where code_adh=cne and code_adh=?";
		Connection con =AdherentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setString(1,code);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
		adherent.setCode_adh(resultSet.getString(1));
		adherent.setNom(resultSet.getString(2));
		adherent.setPrenom(resultSet.getString(3));
		adherent.setNbr_eprunter(resultSet.getInt(4));
		adherent.setAdresse(resultSet.getString(5));
		adherent.setTel(resultSet.getString(6));
		adherent.setTypeAdh(resultSet.getInt(7));
		adherent.setFiliere(resultSet.getString(8));
		}else {
			return null;
		}
		
		con.close();
	return adherent;
	}
	
	public static Professeur getProfesseurByCode(String code) throws IOException,SQLException{
		Professeur adherent=new Professeur();
		String sql = "select code_adh,nom,prenom,nbr_emprunter,adresse,tel,typeAdherent,departement from adherent,professeur where code_adh=cin and code_adh=?";
		Connection con =AdherentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setString(1,code);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
		adherent.setCode_adh(resultSet.getString(1));
		adherent.setCin(resultSet.getString(1));
		adherent.setNom(resultSet.getString(2));
		adherent.setPrenom(resultSet.getString(3));
		adherent.setNbr_eprunter(resultSet.getInt(4));
		adherent.setAdresse(resultSet.getString(5));
		adherent.setTel(resultSet.getString(6));
		adherent.setTypeAdh(resultSet.getInt(7));
		adherent.setDepartement(resultSet.getString(8));
		}
		else {
			return null;
		}
		con.close();
	return adherent;
	}
	
	
	public static Personne getPersonneByCode(String code) throws IOException,SQLException{
		Personne adherent=new Personne();
		String sql = "select code_adh,nom,prenom,nbr_emprunter,adresse,tel,typeAdherent,metier from adherent,personne where code_adh=cin and code_adh=?";
		Connection con =AdherentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setString(1,code);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
		adherent.setCode_adh(resultSet.getString(1));
		adherent.setNom(resultSet.getString(2));
		adherent.setPrenom(resultSet.getString(3));
		adherent.setNbr_eprunter(resultSet.getInt(4));
		adherent.setAdresse(resultSet.getString(5));
		adherent.setTel(resultSet.getString(6));
		adherent.setTypeAdh(resultSet.getInt(7));
		adherent.setMetiere(resultSet.getString(8));
		}
		else {
			return null;
		}
		con.close();
	return adherent;
	}
	
	
	public static Adherent getAdherentByCode(String code) throws IOException,SQLException{
		Etudiant adherent=new Etudiant();
		String sql = "select code_adh,nom,prenom,nbr_emprunter,adresse,tel,typeAdherent from adherent code_adh=?";
		Connection con =AdherentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setString(1,code);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
		adherent.setCode_adh(resultSet.getString(1));
		adherent.setNom(resultSet.getString(2));
		adherent.setPrenom(resultSet.getString(3));
		adherent.setNbr_eprunter(resultSet.getInt(4));
		adherent.setAdresse(resultSet.getString(5));
		adherent.setTel(resultSet.getString(6));
		adherent.setTypeAdh(resultSet.getInt(7));
		}
		
		con.close();
	return adherent;
	}
	
	
	public static int updateAdherent(Adherent adh) throws IOException,SQLException {
		int statue1 =0,statue2=0;
		try {
			
			String sql = "UPDATE adherent SET nom=?,prenom=?,adresse=?,tel=?  where code_adh=?";
			Connection con = AdherentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		
			preparedStatement.setString(1,adh.getNom());
			preparedStatement.setString(2,adh.getPrenom());
			preparedStatement.setString(3,adh.getAdresse());
			preparedStatement.setString(4,adh.getTel());
			preparedStatement.setString(5,adh.getCode_adh());

			
			statue1=preparedStatement.executeUpdate();

//			//Les cas possible
			String sql1 ="UPDATE etudiant SET filiere=? where cne=?";
			String sql2 ="UPDATE professeur SET departement=? where cin=?";
			String sql3 ="UPDATE personne SET metier=? where cin=?";
			PreparedStatement preparedStatementHieritage;
			switch(adh.getTypeAdh()) {
			case 0: 
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql1);
					preparedStatementHieritage.setString(1,((Etudiant) adh).getFiliere());
					preparedStatementHieritage.setString(2,adh.getCode_adh());
					statue2 = preparedStatementHieritage.executeUpdate();break;
			case 1:
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql2);
					preparedStatementHieritage.setString(1,((Professeur) adh).getDepartement());
					preparedStatementHieritage.setString(2,adh.getCode_adh());
					statue2 = preparedStatementHieritage.executeUpdate();break;
			
			case 2:
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql3);
					preparedStatementHieritage.setString(1,((Personne) adh).getMetiere());
					preparedStatementHieritage.setString(2,adh.getCode_adh());
					statue2 = preparedStatementHieritage.executeUpdate();break;
		    
					default: break;
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return statue1*statue2;
	}
	
	
	
	public static boolean supprimerAdherent(Adherent adh) {
		int statue1 =0,statue2=0,statue3=0;
		try {
			
			String sql = "DELETE FROM adherent where code_adh=?";
			Connection con = AdherentBD.getConnection();
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,adh.getCode_adh());
			statue1 = preparedStatement.executeUpdate();
			
			//Les cas possible
			String sql1 ="DELETE FROM etudiant where cne=?";
			String sqlc ="DELETE FROM compte where code_adh=?";
			String sql2 ="DELETE FROM professeur where cin=?";
			String sql3 ="DELETE FROM personne where cin=?";
			

			PreparedStatement preparedStatement1 = (PreparedStatement) con.prepareStatement(sqlc);
			preparedStatement1.setString(1,adh.getCode_adh());
		    preparedStatement1.executeUpdate();

			PreparedStatement preparedStatementHieritage;
			
			switch(adh.getTypeAdh()) {
			case 0: 
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql1);
					preparedStatementHieritage.setString(1,adh.getCode_adh());
					statue2 = preparedStatementHieritage.executeUpdate();break;
			case 1:
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql2);
					preparedStatementHieritage.setString(1,adh.getCode_adh());
					statue2 = preparedStatementHieritage.executeUpdate();break;
			
			case 2:
					preparedStatementHieritage = (PreparedStatement) con.prepareStatement(sql3);
					preparedStatementHieritage.setString(1,adh.getCode_adh());
					statue2 = preparedStatementHieritage.executeUpdate();break;
		    
					default: break;
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return (statue1 > 0 && statue2 >0);
	}

}
