package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Authentification {
	@FXML
	JFXTextField pseudo;
	@FXML
	JFXPasswordField motpass;
	@FXML
	Label message;
	@FXML
	
	

	

	///////////////////Acces aux fenetre///////////////////////////
	
	public void loadMenuAdh(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("MenuAdherent.fxml" ));
		Scene s = new Scene(root);
		Stage fenetre = (Stage) ((Node)e.getSource()).getScene().getWindow();
		fenetre.setScene(s);
		fenetre.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        fenetre.setX((primScreenBounds.getWidth() - fenetre.getWidth()) / 2); 
        fenetre.setY((primScreenBounds.getHeight() - fenetre.getHeight()) / 4);  
		fenetre.show();		
	}
	
	
	private void loadMenuAdm(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("MenuAdmins.fxml" ));
		Scene s = new Scene(root);
		Stage fenetre = (Stage) ((Node)e.getSource()).getScene().getWindow();
		fenetre.setScene(s);
		fenetre.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        fenetre.setX((primScreenBounds.getWidth() - fenetre.getWidth()) / 2); 
        fenetre.setY((primScreenBounds.getHeight() - fenetre.getHeight()) / 4);  
		fenetre.show();		
	}
	
	
	private void loadInsc(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("ins.fxml" ));
		Scene s = new Scene(root);
		Stage fenetre = (Stage) ((Node)e.getSource()).getScene().getWindow();
		fenetre.setScene(s);
		fenetre.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        fenetre.setX((primScreenBounds.getWidth() - fenetre.getWidth()) / 2); 
        fenetre.setY((primScreenBounds.getHeight() - fenetre.getHeight()) / 4);  
        
		fenetre.show();	
	}
	
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////Connecter///////////////////////////
	public void Connecter(ActionEvent e)throws IOException,SQLException{
	
	List<Compte> list =CompteBD.getComptes();//from sql 
	Map<String,String> map = new HashMap<String,String>();
	
	//remplir le Map
	for(Compte a:list) {
		map.put(a.getPseudo_nom(),a.getMot_pass());
	}
	
	
	//tester le mot de passe
	if(map.containsKey(pseudo.getText())) {
		String Mot = map.get(pseudo.getText());//get password by username ( key) 
		if(Mot.equals(motpass.getText())){
			//Creer la nouvel fenetre
		enLigne(pseudo.getText());
		CompteBD.getConnection();
		if(pseudo.getText().equals("admin1") || pseudo.getText().equals("admin2")) {
			loadMenuAdm(e);
		}
		else {
		loadMenuAdh(e);
			}
		}
		else {
			message.setText("votre nom d'utilisateur ou/et mot de passe est incorrect");
		}
		
		}
	else {
			message.setText("votre nom d'utilisateur  est incorrect");
		}
}
	
	
	
	public void inscrire(ActionEvent e)throws IOException,SQLException{
		CompteBD.getConnection();
		loadInsc(e);
			}	
		
	

	
	public  void enLigne(String pseud) {
		
		//int st=0;
		try {
			String sql = "UPDATE compte SET etat=? WHERE pseudo_nom=?";
			Connection con = CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setInt(1,1);
			preparedStatement.setString(2,pseud);
			preparedStatement.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//return st;
	}
	 
	
	public static void deconnection() {
		//int st=0;
		try {
			String sql = "UPDATE compte SET etat=? WHERE etat=?";
			Connection con = CompteBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setInt(1,0);
			preparedStatement.setInt(2,1);
			preparedStatement.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//return st;
	}


	
}
