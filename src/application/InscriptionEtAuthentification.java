package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.events.MouseEvent;

import Adherents.Adherent;
import Adherents.Etudiant;
import Adherents.Personne;
import Adherents.Professeur;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InscriptionEtAuthentification  implements Initializable {
	
	//Les variables interface graphique
		public int type;
		public static Compte profile;

	 @FXML
	    private JFXTextField pseudo;

	    @FXML
	    private JFXPasswordField motpass;

	    @FXML
	    private JFXTextField nom;

	    @FXML
	    private JFXTextField prenom;

	    @FXML
	    private JFXTextField tel;

	    @FXML
	    private JFXTextField pseudo1;

	    @FXML
	    private JFXTextField id;

	    @FXML
	    private JFXTextField adresse;

	    @FXML
	    private JFXTextField metiere;

	    @FXML
	    private JFXTextField filiere;

	    @FXML
	    private JFXTextField departement;

	    @FXML
	    private ToggleGroup group;

	    @FXML
	    private JFXButton inscrireB;

	    @FXML
	    private Pane sld;

	    @FXML
	    private JFXButton connectervous;

	    @FXML
	    private JFXButton inscrirevous1;

	    @FXML
	    private Label inscrirelabel;

	    @FXML
	    private Label connecterlabel;
	    @FXML 
	    private Label message1;
	    @FXML
	    private Label message11;
	    @FXML
	    private Label message;
		@FXML
		JFXTextField pseudoc;
		@FXML
		JFXPasswordField motpassc;
		@FXML
		Label messagec;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		
	    connecterlabel.setVisible(false);
	    connectervous.setVisible(false);
		
		}
	
	
	

	
	public void inscriptionSH(ActionEvent e)throws IOException,SQLException{
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.5));
		slide.setNode(sld);
		slide.setToX(535);
		slide.play();
		
		sld.setTranslateX(535);
		
		pseudoc.clear();
		motpassc.clear();
		connectervous.setVisible(true);
		connecterlabel.setVisible(true);
		inscrirevous1.setVisible(false);
		inscrirelabel.setVisible(false);
		slide.setOnFinished((event->{
			
		}));
		
	}
	
	
	
	public void connecterSH(ActionEvent e)throws IOException,SQLException{
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(0.5));
		slide.setNode(sld);
		slide.setToX(0);
		slide.play();
		
		sld.setTranslateX(535);
		
		nom.clear();
		prenom.clear();
		adresse.clear();
		tel.clear();
		departement.clear();
		id.clear();
		pseudo.clear();
		filiere.clear();
		metiere.clear();
		connectervous.setVisible(false);
		connecterlabel.setVisible(false);
		inscrirevous1.setVisible(true);
		inscrirelabel.setVisible(true);
		slide.setOnFinished((event->{
			
		}));
		
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// INSCRIPTION   //////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	public void filiere (ActionEvent e)throws IOException,SQLException{
		inscrireB.setDisable(false);
		type=0;
		filiere.setDisable(false);
		departement.setDisable(true);
		metiere.setDisable(true);
	}

	public void departement(ActionEvent e) throws IOException,SQLException{
		inscrireB.setDisable(false);
		type=1;
		filiere.setDisable(true);
		departement.setDisable(false);
		metiere.setDisable(true);
	}

	public void metiere(ActionEvent e) throws IOException,SQLException{
		inscrireB.setDisable(false);
		type=2;
		filiere.setDisable(true);
		departement.setDisable(true);
		metiere.setDisable(false);
	}

	public void loadAuthen(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml" ));
		Scene s = new Scene(root);
		Stage fenetre = (Stage) ((Node)e.getSource()).getScene().getWindow();
		fenetre.setScene(s);
		fenetre.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	    fenetre.setX((primScreenBounds.getWidth() - fenetre.getWidth()) / 2); 
	    fenetre.setY((primScreenBounds.getHeight() - fenetre.getHeight()) / 4); 
	    fenetre.show();		
	}





	//generer un mot de passe
		public String GenMotPass() {
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(8);
		    for (int i = 0; i < 8; i++) {
		        int randomLimitedInt = 97 + (int) 
		          (random.nextFloat() * (122 - 97 + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String mot_pass = buffer.toString();
		    return mot_pass;
		
		}



		
		
		
		
		
		
		//fonction inscrire
		public void inscrire(ActionEvent e) throws IOException,SQLException{
			message1.setVisible(false);
			message.setVisible(false);
			message11.setVisible(false);
				if(nom.getText().isEmpty() || pseudo.getText().isEmpty() ||prenom.getText().isEmpty() || tel.getText().isEmpty() || id.getText().isEmpty()) {
					MenuAdmins.messageWarning();
					return;
				}
				
				else {
					
					
				if(this.verificationCompte()) {
					message.setVisible(true);
					message.setText("Le nom d'utilisateur est deja utiliser.");
					return;
				}
				
				else {
					//enregistrer les informations
					Adherent adh;
					String motpass;
					int statues=0;
					switch(type) {
					case 0:  /////Etudiant
						      adh=new Etudiant(); 
						     
						      adh.setNom(nom.getText());
						      adh.setCode_adh(id.getText());
						      adh.setPrenom(prenom.getText());
						      adh.setAdresse(adresse.getText());
						      adh.setTel(tel.getText());
						      adh.setTypeAdh(type);
						    	  ((Etudiant) adh).setFiliere(filiere.getText());
						    	  ((Etudiant) adh).setCne(id.getText());
						    	  if(!verificationPreinscri(adh)){
										message.setVisible(true);
										message.setText("Echec, vous devez effectuer un pr-inscription");
										return;
									}
				    
					case 1: //////Professeur
					      adh=new Professeur(); 
					      adh.setNom(nom.getText());
					      adh.setCode_adh(id.getText());
					      adh.setPrenom(prenom.getText());
					      adh.setAdresse(adresse.getText());
					      adh.setTel(tel.getText());
					      adh.setTypeAdh(type);
					    	 ((Professeur) adh).setDepartement(departement.getText());
					    	 ((Professeur) adh).setCin(id.getText());
					     if(!verificationPreinscri(adh)){
								message.setVisible(true);
								message.setText("Echec, vous devez effectuer un pr-inscription");
								return;
							}				      
					      
					      
					      
					case 2:  ////////Personne
					      adh=new Personne(); 
					      adh.setNom(nom.getText());
					      adh.setCode_adh(id.getText());
					      adh.setPrenom(prenom.getText());
					      adh.setAdresse(adresse.getText());
					      adh.setTel(tel.getText());
					      adh.setTypeAdh(type);
					    	  ((Personne) adh).setMetiere(metiere.getText());
					    	  ((Personne) adh).setCin(id.getText());
					    	  
					    	  if(!verificationPreinscri(adh)){
									message.setVisible(true);
									message.setText("Echec, vous devez effectuer un pr-inscription");
									return;
								}
					    	  
					    	  
					default: break;
					}
					
					
					
					
					
					Compte cmpt= new Compte();
					profile = cmpt;
					cmpt= new Compte();
					cmpt.setPseudo_nom(pseudo.getText());
					cmpt.setTypecompte(0);
					motpass=GenMotPass();
					cmpt.setMot_pass(motpass);
					cmpt.setCode_adh(id.getText());
					statues = CompteBD.save(cmpt);
					
					if(statues>0) {
						message1.setVisible(true);
						message11.setVisible(true);
						message1.setText("Vous etes inscrit, votre mot de passe est : ");
						message11.setText(motpass);
					}else {
					message.setVisible(true);
					message.setText("Desolé, il ya un probléme.");
					}
					
				}

				
				
		}
		}		
		
		
		
		
		
		public boolean verificationPreinscri(Adherent adherent)throws IOException,SQLException {
			Adherent adh;
			switch(type) {
			
			case 0: 
				adh= new Etudiant();
				adh= AdherentBD.getEtudiantByCode(id.getText());
				if(adh!=null && adh.getNom().equals(nom.getText()) && adh.getPrenom().equals(prenom.getText()) && adh.getTel().equals(tel.getText())&&adh.getAdresse().equals(adresse.getText())){
					return true;
				}
				else {
					return false;
				}
			case 1: 
				adh= new Professeur();
				adh= AdherentBD.getProfesseurByCode(id.getText());
				if(adh!=null && adh.getNom().equals(nom.getText()) && adh.getPrenom().equals(prenom.getText()) && adh.getTel().equals(tel.getText())&&adh.getAdresse().equals(adresse.getText())){
					return true;
				}
				else {
					return false;
				}
			
			default: 
				adh= new Personne();
				adh= AdherentBD.getPersonneByCode(id.getText());
				if(adh!=null && adh.getNom().equals(nom.getText()) && adh.getPrenom().equals(prenom.getText()) && adh.getTel().equals(tel.getText())&&adh.getAdresse().equals(adresse.getText())){
					return true;
				}
				else {
					return false;
				}
				
			}
		}
		
		
		public  boolean verificationCompte() {
			List<Compte> list =CompteBD.getComptes();//from sql 
			Map<String,String> map = new HashMap<String,String>();
			
			
			//remplir le Map
			for(Compte a:list) {
				map.put(a.getPseudo_nom(),a.getMot_pass());
			}
			
			//tester le mot de passe
			if(map.containsKey(pseudo.getText())) {return true;}
			else return false;
		}
		
		
		
		
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////// Connecter //////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
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





///////////////////Connecter///////////////////////////
	public void Connecter(ActionEvent e)throws IOException,SQLException{
	
	List<Compte> list =CompteBD.getComptes();//from sql 
	Map<String,String> map = new HashMap<String,String>();
	
	//remplir le Map
	for(Compte a:list) {
	map.put(a.getPseudo_nom(),a.getMot_pass());
	}
	
	
	//tester le mot de passe
	if(map.containsKey(pseudoc.getText())) {
	String Mot = map.get(pseudoc.getText());//get password by username ( key) 
	if(Mot.equals(motpassc.getText())){
		//Creer la nouvel fenetre
	enLigne(pseudoc.getText());
	CompteBD.getConnection();
	if(pseudoc.getText().equals("admin1") || pseudoc.getText().equals("admin2")) {
		loadMenuAdm(e);
	}
	else {
	loadMenuAdh(e);
		}
	}
	else {
		messagec.setText("votre nom d'utilisateur ou/et mot de passe est incorrect");
	}
	
	}
	else {
		messagec.setText("votre nom d'utilisateur  est incorrect");
	}
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
