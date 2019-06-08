package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminsBD {

	
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
	



	public static Admins getAdminsByCode(int code) throws IOException,SQLException{
		Admins admins=new Admins();
		String sql = "select nom,prenom,tel,adresse from admins where id=?";
		Connection con =AdminsBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setInt(1,code);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
		admins.setNom(resultSet.getString(1));
		admins.setPrenom(resultSet.getString(2));
		admins.setTel(resultSet.getString(3));
		admins.setAdresse(resultSet.getString(4));
		}
		else {
			return null;
		}
		con.close();
	return admins;
	}
}
