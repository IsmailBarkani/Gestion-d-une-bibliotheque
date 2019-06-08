package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Adherents.Adherent;
import Adherents.Etudiant;
import Adherents.Personne;
import Adherents.Professeur;
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

public class Inscription {
	
	//Les variables interface graphique
	public int type;
	public static Compte profile;
	
	
	
	@FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField id;

    @FXML
    private  JFXTextField pseudo;

    @FXML
    private JFXTextField tel;

    @FXML
    private JFXTextField departement;

    @FXML
    private JFXTextField metiere;

    @FXML
    private JFXTextField filiere;
    @FXML
    private Label message;
    @FXML 
    private Label message1;
    @FXML
    private Label message11;
    @FXML
    private JFXButton inscrireB;
    
    
    
    
    
    
    
    // Disable enable
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
	
	
	
	
}
