package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompteBD {
	
static Connection con =null;
	
	


	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/projet_java_bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			con = DriverManager.getConnection(url,"root","");
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	
	
	
	public static  List<Compte> getComptes(){
		List<Compte> list = new ArrayList<Compte>();
		try {
			String sql = "select * from Compte";
			Connection con =CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Compte compte = new Compte();
				
				compte.setPseudo_nom(resultSet.getString(2));
				compte.setMot_pass(resultSet.getString(4));
				compte.setCode_adh(resultSet.getString(1));
				compte.setTypecompte(resultSet.getInt(6));
				list.add(compte);
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	public static int save(Compte cmpt) {
		
		int st =0;
		try {
			
			String sql = "INSERT INTO Compte (pseudo_nom,mot_pass,code_adh,typecompte) Values (?,?,?,?)";
			Connection con = CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cmpt.getPseudo_nom());
			preparedStatement.setString(2,cmpt.getMot_pass());
			preparedStatement.setString(3,cmpt.getCode_adh());
			preparedStatement.setInt(4,cmpt.getTypecompte());
			
			st = preparedStatement.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
		
	}
	
	
	public static Compte getCompteByEtat(int etat){
		Compte cmpt = new Compte();
		try {
			String sql = "select * from compte WHERE etat=?";
			Connection con = CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setInt(1,1);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				cmpt.setPseudo_nom(resultSet.getString(2));
				cmpt.setMot_pass(resultSet.getString(4));
				cmpt.setCode_adh(resultSet.getString(1));
				cmpt.setTypecompte(resultSet.getInt(6));
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cmpt;
	}
	
	
	public static int personneModif(Compte cmp,String code) {
		int st=0,st2=0,st3=0;
		try {
			String sql = "UPDATE compte SET pseudo_nom=? WHERE code_adh=?";
			String sq2 = "UPDATE adherent SET tel=? WHERE code_adh=?";
			String sq3 = "UPDATE adherent SET adresse=? WHERE code_adh=?";
			
			Connection con = CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			PreparedStatement preparedStatement2 = (PreparedStatement) con.prepareStatement(sq2);
			PreparedStatement preparedStatement3 = (PreparedStatement) con.prepareStatement(sq3);
			
			preparedStatement.setString(1,cmp.getPseudo_nom());
			preparedStatement.setString(2,code);
			preparedStatement2.setString(1,cmp.getTelephone());
			preparedStatement2.setString(2,code);
			preparedStatement3.setString(1,cmp.getAdresse());
			preparedStatement3.setString(2,code);
			
			st = preparedStatement.executeUpdate();
			st2 = preparedStatement2.executeUpdate();
			st3 = preparedStatement3.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st*st2*st3;
	}
	
	public static int motpassModif(Compte cmp,int etat) {
		int st=0;
		try {
			String sql = "UPDATE compte SET mot_pass=? WHERE etat=?";
			Connection con = CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cmp.getMot_pass());
			preparedStatement.setInt(2,etat);
			st = preparedStatement.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

}