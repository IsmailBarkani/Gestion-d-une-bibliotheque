package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Adherents.Adherent;
import Adherents.Professeur;
import Adherents.Etudiant;
import Adherents.Personne;
import Documents.Document;
import Documents.Livre;
import Documents.Magazine;
import Documents.Dictionnaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuAdmins  implements Initializable{
	
	int by;
	int typemodif;
	private Livre livre;
	private Magazine magazine;
	private Dictionnaire dictionnaire;
	private  Compte cmpt;

	
		@FXML
		private Pane documents;
		@FXML
		private Pane ajouterdocument;
		@FXML
		private Pane modifdocument;
		@FXML
		private Pane adherent;
		@FXML
		private Pane ajouteradherent;
		@FXML
		private JFXTextField pseudomodif;
		@FXML
		private JFXTextField telephonemodif;
		@FXML
		private JFXTextField adressemodif;
		
		@FXML
		private Label title;
	    @FXML
	    private Label pseudomodifechec;

	    @FXML
	    private Label pseudomodifsucce;

	    @FXML
	    private JFXPasswordField oldmotpass;

	    @FXML
	    private JFXPasswordField newmotpass;

	    @FXML
	    private JFXPasswordField confirmer;

	    @FXML
	    private Label motpassmodifechec;

	    @FXML
	    private Label motpassmodifsucce;
	    @FXML 
	    private Label test;
	 
	 // les FXML de tableu livre
	    @FXML
	    private TableView<Livre> tableulivre;

	    @FXML
	    private TableColumn<Document,String> isbn;

	    @FXML
	    private TableColumn<Document,String> titre;

	    @FXML
	    private TableColumn<Document,String>editeur;

	    @FXML
	    private TableColumn<Document,Integer> anne;

	    @FXML
	    private TableColumn<Livre,Integer> nbrpage;

	    @FXML
	    private TableColumn<Livre,String> type;

	    @FXML
	    private TableColumn<Livre,String> tome;

	    @FXML
	    private TableColumn<Document,Integer> nbrdispo;
		@FXML
	    private TextField cherche;
	    @FXML
	    private Label echecemprunter;
	    @FXML
	    private Label succeeprunter;
		 // les FXML de tableu Dictionnaire
	    @FXML
	    private TableView<Dictionnaire> tableudictionnaire;

	    @FXML
	    private TableColumn<Document,String> isbnd;

	    @FXML
	    private TableColumn<Document,String> titred;

	    @FXML
	    private TableColumn<Document,String>editeurd;

	    @FXML
	    private TableColumn<Document,Integer> anned;

	    @FXML
	    private TableColumn<Dictionnaire,String> langue;

	    @FXML
	    private TableColumn<Dictionnaire,String> tomed;

	    @FXML
	    private TableColumn<Document,Integer> nbrdispod;
	    @FXML
	    private Label echecemprunterd;
	    @FXML
	    private Label succeeprunterd;
		@FXML
		private Label totaledictionnaire;
		@FXML
	    private TextField cherched;

		 // les FXML de tableu magazine
	    @FXML
	    private TableView<Magazine> tableumagazine;

	    @FXML
	    private TableColumn<Document,String> isbnm;

	    @FXML
	    private TableColumn<Document,String> titrem;

	    @FXML
	    private TableColumn<Document,String>editeurm;

	    @FXML
	    private TableColumn<Document,Integer> annem;

	    @FXML
	    private TableColumn<Magazine,Integer> periode;

	    @FXML
	    private TableColumn<Magazine,String> dateedition;

	    @FXML
	    private TableColumn<Document,Integer> nbrdispom;
		@FXML
	    private TextField cherchem;
		// Ajoiter un livre
	    @FXML
	    private JFXTextField aisbnl;

	    @FXML
	    private JFXTextField atitrel;

	    @FXML
	    private JFXTextField aediteurl;

	    @FXML
	    private JFXTextField aanneel;

	    @FXML
	    private JFXTextField anbr_page;

	    @FXML
	    private JFXTextField atomel;

	    @FXML
	    private JFXTextField atypel;

	    @FXML
	    private JFXTextField aauteur1;

	    @FXML
	    private JFXTextField aauteur2;

	    @FXML
	    private JFXTextField aateur3;

	    @FXML
	    private JFXTextField aauteur4;

	    @FXML
	    private JFXTextField anbr_exmpll;

	    @FXML
	    private JFXTextField aisbnm;

	    @FXML
	    private JFXTextField atitrem;

	    @FXML
	    private JFXTextField aediteurm;

	    @FXML
	    private JFXTextField aanneem;

	    @FXML
	    private JFXTextField aperiodem;

	    @FXML
	    private JFXTextField aeditionm;

	    @FXML
	    private JFXTextField aauteur1m;

	    @FXML
	    private JFXTextField aauteur2m;

	    @FXML
	    private JFXTextField aauteur3m;

	    @FXML
	    private JFXTextField aauteur4m;

	    @FXML
	    private JFXTextField anbr_exmplm;

	    @FXML
	    private JFXTextField aisbnd;

	    @FXML
	    private JFXTextField atitred;

	    @FXML
	    private JFXTextField aediteurd;

	    @FXML
	    private JFXTextField aanneed;

	    @FXML
	    private JFXTextField alangued;

	    @FXML
	    private JFXTextField atomed;

	    @FXML
	    private JFXTextField aauteur1d;

	    @FXML
	    private JFXTextField aauteur2d;

	    @FXML
	    private JFXTextField aauteur3d;

	    @FXML
	    private JFXTextField aauteur4d;

	    @FXML
	    private JFXTextField anbr_exmpld;
	    @FXML
	    private JFXTextField misbnl;

	    @FXML
	    private JFXTextField mtitrel;

	    @FXML
	    private JFXTextField mediteurl;

	    @FXML
	    private JFXTextField manneel;

	    @FXML
	    private JFXTextField mnbr_page;

	    @FXML
	    private JFXTextField mtomel;

	    @FXML
	    private JFXTextField mtypel;

	    @FXML
	    private JFXTextField mauteur1l;

	    @FXML
	    private JFXTextField mauteur2l;

	    @FXML
	    private JFXTextField mateur3l;

	    @FXML
	    private JFXTextField mauteur4l;

	    @FXML
	    private JFXTextField mnbr_exmpll;

	    @FXML
	    private TextField mcherche;
	    @FXML
	    private JFXButton modifierLB;
	    @FXML
	    private JFXButton chercherLB;
	    @FXML
	    private JFXButton supprimerLB;
	    //modifier magazine
	    @FXML
	    private JFXTextField misbnm;

	    @FXML
	    private JFXTextField mtitrem;

	    @FXML
	    private JFXTextField mediteurm;

	    @FXML
	    private JFXTextField manneem;

	    @FXML
	    private JFXTextField mperiodem;

	    @FXML
	    private JFXTextField meditionm;

	    @FXML
	    private JFXButton modifierMB;

	    @FXML
	    private JFXTextField mauteur1m;

	    @FXML
	    private JFXTextField mauteur2m;

	    @FXML
	    private JFXTextField mateur3m;

	    @FXML
	    private JFXTextField mauteur4m;

	    @FXML
	    private JFXTextField mnbr_exmplm;

	    @FXML
	    private JFXButton supprimerMB;

	    @FXML
	    private JFXButton chercherMB;

	    @FXML
	    private TextField mcherchem;
//Modifeir un dictionnaire
	    @FXML
	    private JFXTextField misbnd;

	    @FXML
	    private JFXTextField mtitred;

	    @FXML
	    private JFXTextField mediteurd;

	    @FXML
	    private JFXTextField manneed;

	    @FXML
	    private JFXTextField mlangued;

	    @FXML
	    private JFXTextField mtomed;

	    @FXML
	    private JFXButton modifierDB;

	    @FXML
	    private JFXTextField mauteur1d;

	    @FXML
	    private JFXTextField mauteur2d;

	    @FXML
	    private JFXTextField mateur3d;

	    @FXML
	    private JFXTextField mauteur4d;

	    @FXML
	    private JFXTextField mnbr_exmpld;

	    @FXML
	    private JFXButton supprimerDB;

	    @FXML
	    private JFXButton chercherDB;

	    @FXML
	    private TextField mcherched;
	    @FXML
	    private JFXButton dchercherLB;
	    @FXML
	    private JFXButton dchercherDB;
	    @FXML
	    private JFXButton dchercherMB;
	    ////Adherent
	    //Professeur

	    @FXML
	    private JFXButton chercherPB;

	    @FXML
	    private TextField cherchep;

	    @FXML
	    private TableView<Professeur> tableuprofesseur;

	    @FXML
	    private TableColumn<Professeur,String> cinp;

	    @FXML
	    private TableColumn<Professeur,String> nomp;

	    @FXML
	    private TableColumn<Professeur,String> prenomp;

	    @FXML
	    private TableColumn<Professeur,String> telp;

	    @FXML
	    private TableColumn<Professeur,String> adressep;

	    @FXML
	    private TableColumn<Professeur,String> departementp;

	    @FXML
	    private TableColumn<Professeur,String> comptep;

	    @FXML
	    private TableColumn<Professeur,Integer> emprunterp;
	    
	    ///Etudiant
	    @FXML
	    private JFXButton dchercherEB;

	    @FXML
	    private TextField cherchee;

	    @FXML
	    private TableView<Etudiant> tableuetudiant;

	    @FXML
	    private TableColumn<Etudiant,String> cnee;

	    @FXML
	    private TableColumn<Etudiant,String> nome;

	    @FXML
	    private TableColumn<Etudiant,String> prenome;

	    @FXML
	    private TableColumn<Etudiant,String> adressee;

	    @FXML
	    private TableColumn<Etudiant,String>tele;

	    @FXML
	    private TableColumn<Etudiant,String> filieree;

	    @FXML
	    private TableColumn<Etudiant,Integer> empruntere;
	    @FXML
	    private JFXButton chercherXB;

	    @FXML
	    private TextField cherchex;

	    @FXML
	    private TableView<Personne> tableupersonne;

	    @FXML
	    private TableColumn<Personne,String> cinx;

	    @FXML
	    private TableColumn<Personne,String>nomx;

	    @FXML
	    private TableColumn<Personne,String>prenomx;

	    @FXML
	    private TableColumn<Personne,String> adressex;

	    @FXML
	    private TableColumn<Personne,String>telx;

	    @FXML
	    private TableColumn<Personne,String> metierx;

	    @FXML
	    private TableColumn<Personne,Integer> emprunterx;


	    @FXML
	    private JFXTextField acode_adhp;

	    @FXML
	    private JFXTextField anomp;

	    @FXML
	    private JFXTextField aprenomp;

	    @FXML
	    private JFXTextField aadressep;

	    @FXML
	    private JFXTextField atelp;

	    @FXML
	    private JFXTextField adepartementp;
	    @FXML
	    private JFXTextField acode_adhe;

	    @FXML
	    private JFXTextField anome;

	    @FXML
	    private JFXTextField aprenome;

	    @FXML
	    private JFXTextField aadressee;

	    @FXML
	    private JFXTextField atele;

	    @FXML
	    private JFXTextField afilieree;
	    @FXML
	    private JFXTextField acode_adhx;

	    @FXML
	    private JFXTextField anomex;

	    @FXML
	    private JFXTextField aprenomx;

	    @FXML
	    private JFXTextField aadressex;

	    @FXML
	    private JFXTextField atelx;

	    @FXML
	    private JFXTextField ametierx;

	    @FXML
	    private Pane modifadherent;

	    @FXML
	    private JFXTextField mcode_ahdp;

	    @FXML
	    private JFXTextField mnomp;

	    @FXML
	    private JFXTextField mprenomp;

	    @FXML
	    private JFXTextField madressep;

	    @FXML
	    private JFXTextField mtelp;

	    @FXML
	    private JFXButton mmodifierPB;

	    @FXML
	    private JFXTextField mdepartementp;

	    @FXML
	    private JFXTextField memprunterp;

	    @FXML
	    private JFXButton msupprimerPB;

	    @FXML
	    private JFXButton mchercherPB;

	    @FXML
	    private TextField mcherchep;
	    @FXML
	    private JFXTextField mcode_ahde;

	    @FXML
	    private JFXTextField mnome;

	    @FXML
	    private JFXTextField mprenome;

	    @FXML
	    private JFXTextField madressee;

	    @FXML
	    private JFXTextField mtele;

	    @FXML
	    private JFXButton mmodifierEB;

	    @FXML
	    private JFXTextField mfilieree;

	    @FXML
	    private JFXTextField mempruntere;

	    @FXML
	    private JFXButton msupprimerEB;

	    @FXML
	    private JFXButton mchercherEB;

	    @FXML
	    private TextField mcherchee;


	    @FXML
	    private JFXTextField mcode_ahdex;

	    @FXML
	    private JFXTextField mnomex;

	    @FXML
	    private JFXTextField mprenomex;

	    @FXML
	    private JFXTextField madresseex;

	    @FXML
	    private JFXTextField mtelex;

	    @FXML
	    private JFXButton mmodifierEXB;

	    @FXML
	    private JFXTextField mmetierex;

	    @FXML
	    private JFXTextField memprunterex;

	    @FXML
	    private JFXButton msupprimerEXB;

	    @FXML
	    private JFXButton mchercherEXB;

	    @FXML
	    private TextField mchercheex;
	    
	    
	    
		public static Connection getConnection() {
			Connection con=null;
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/projet_bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			con = DriverManager.getConnection(url,"root","");
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			return con;
		}
	    
		
		
		public static void messageWarning() {
							Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Message");
				alert.setContentText("Il faut remplire les champs qui sont obligatoire (*)");
				
				alert.showAndWait();

		}
		
		
		
		public static void messageResultatVide() {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Message");
			alert.setContentText("Aucune resultat n'a trouvé");
			
			alert.showAndWait();

				}
		
		
		
		
		
		
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Menu Principale////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	    
	    static Connection con = null;
	    @Override
		public void initialize(URL location, ResourceBundle resources){

	    cmpt=CompteBD.getCompteByEtat(1);
	    	tableProfesseur();
	    	tableEtudiant();
	    	tableLivre();
	    	tableMagazine();
	    	tableDictionnaire();
	    	title.setText(cmpt.getPseudo_nom());
	    	
		}
		
	    
	    
	    public void document(ActionEvent e)throws IOException,SQLException {
	    	clearModifDictionnaire();
	    	clearModifLivre();
	    	clearModifMagazine();
	    	
	    	clearProfesseur();
	    	clearEtudiant();
	    	clearPersonne();
	    	tableLivre();
	    	tableMagazine();
	    	tableDictionnaire();
	    	
			documents.toFront();
			modifdocument.toBack();
			ajouterdocument.toBack();
		}

		
		public void ajouterdocument(ActionEvent e)throws IOException,SQLException{
			clearModifDictionnaire();
			clearModifLivre();
	    	clearModifMagazine();
	    	clearProfesseur();
	    	clearEtudiant();
	    	clearPersonne();
			ajouterdocument.toFront();
			modifdocument.toBack();
			documents.toBack();
				
		}
		
		public void modifdocument(ActionEvent e)throws IOException,SQLException{
			clearModifDictionnaire();
			clearModifLivre();
	    	clearModifMagazine();
	    	tableProfesseur();
	    	tableEtudiant();
	    	tablePersonne();
	    	
			modifdocument.toFront();
			ajouterdocument.toBack();
			documents.toBack();
				
		}
		
		public void adherent(ActionEvent e)throws IOException,SQLException{
			clearModifDictionnaire();
			clearModifLivre();
	    	clearModifMagazine();
	    	tableProfesseur();
	    	tableEtudiant();
	    	tablePersonne();
	    	
			adherent.toFront();
			modifdocument.toBack();
			ajouterdocument.toBack();
			documents.toBack();
				
		}
		public void ajouteradherent(ActionEvent e)throws IOException,SQLException{
			clearModifDictionnaire();
			clearModifLivre();
	    	clearModifMagazine();
	    	clearProfesseur();
	    	clearEtudiant();
	    	clearPersonne();
	    	
	    	ajouteradherent.toFront();
	    	adherent.toBack();
			modifdocument.toBack();
			ajouterdocument.toBack();
			documents.toBack();
				
		}
		
		public void modifadherent(ActionEvent e)throws IOException,SQLException {
	    	clearModifDictionnaire();
	    	clearModifLivre();
	    	clearModifMagazine();
	    	clearProfesseur();
	    	clearEtudiant();
	    	clearPersonne();
	    	
	    	tableLivre();
	    	tableMagazine();
	    	tableDictionnaire();
	    	
	    	modifadherent.toFront();
			documents.toBack();
			modifdocument.toBack();
			ajouterdocument.toBack();
		}
		 public ObservableList<Livre> data = FXCollections.observableArrayList();
		 public ObservableList<Magazine> dataMagazine = FXCollections.observableArrayList();
		 public ObservableList<Dictionnaire> dataDictionnaire = FXCollections.observableArrayList();
		 public ObservableList<Historique> dataHistorique = FXCollections.observableArrayList();
		 public ObservableList<Professeur> dataProfesseur = FXCollections.observableArrayList();
		 public ObservableList<Etudiant> dataEtudiant = FXCollections.observableArrayList();
		 public ObservableList<Personne> dataPersonne = FXCollections.observableArrayList();


		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////Livre////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
	    public void  tableLivre(){
	    	
	    	tableulivre.getItems().clear();
			try {
				String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre where document.isbn=livre.isbn and code_adh IS NULL group by document.isbn";
				Connection con = DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs  = preparedStatement.executeQuery();
				while(rs.next()) {
					data.add(new Livre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8)));
				
				
				}
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			isbn.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titre.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeur.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anne.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			nbrpage.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("Nbrpage"));
			type.setCellValueFactory(new PropertyValueFactory<Livre,String>("Type"));
			tome.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
			nbrdispo.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
			
			tableulivre.setItems(data);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
///////////////////////////////////////////////////////////////////////////
//////////////////////////Chercher/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

	    public void ByISBN(ActionEvent e)throws IOException,SQLException{
	    	cherche.setDisable(false);
	    	dchercherLB.setDisable(false);
	    	cherchem.setDisable(false);
	    	dchercherMB.setDisable(false);
	    	cherched.setDisable(false);
	    	dchercherDB.setDisable(false);
	    	by=1;

	    }
	    public void ByTitre(ActionEvent e)throws IOException,SQLException{
	    	cherche.setDisable(false);
	    	dchercherLB.setDisable(false);
	    	cherchem.setDisable(false);
	    	dchercherMB.setDisable(false);
	    	cherched.setDisable(false);
	    	dchercherDB.setDisable(false);
	    	by=2;

	    }
	    public void ByAuteur(ActionEvent e)throws IOException,SQLException{
	    	cherche.setDisable(false);
	    	dchercherLB.setDisable(false);
	    	cherchem.setDisable(false);
	    	dchercherMB.setDisable(false);
	    	cherched.setDisable(false);
	    	dchercherDB.setDisable(false);
	    	by=3;

	    }
	    
	    public void BY(ActionEvent e)throws IOException,SQLException{
	    	switch(by) {
	    	case 1: ChercheByIsbn(e);break;
	    	case 2: ChercheByTitre(e);break;
	    	case 3:	ChercheByAuteur(e);break;
	    	default: break;
	    	}
	    }
	    
	    
	    
	    public void ChercheByIsbn(ActionEvent e)throws IOException,SQLException{
	    	tableulivre.getItems().clear();
	    	
			String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre where document.isbn=livre.isbn and code_adh is null and document.isbn=? group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherche.getText());
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			data.add(new Livre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8)));

				}
			con.close();
			
			isbn.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titre.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeur.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anne.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			nbrpage.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("Nbrpage"));
			type.setCellValueFactory(new PropertyValueFactory<Livre,String>("type"));
			tome.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
			nbrdispo.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));

			
			tableulivre.setItems(data);
	    	
	    }
	    	    
	    public void ChercheByTitre(ActionEvent e)throws IOException,SQLException{
	    	tableulivre.getItems().clear();
	    	
	    	String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre where document.isbn=livre.isbn and code_adh is null and titre=? group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherche.getText());
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			data.add(new Livre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8)));
			
		}
			con.close();
			isbn.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titre.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeur.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anne.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			nbrpage.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("Nbrpage"));
			type.setCellValueFactory(new PropertyValueFactory<Livre,String>("type"));
			tome.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
			nbrdispo.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
			tableulivre.setItems(data);
	    	
	    }
	
	    public void ChercheByAuteur(ActionEvent e)throws IOException,SQLException{
	    	tableulivre.getItems().clear();
	    	
	    	String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre,liste_auteur WHERE document.isbn=livre.isbn and document.isbn=liste_auteur.isbn and code_adh is null and (auteur1=? OR auteur2=? OR auteur3=? or auteur4=?)   group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherche.getText());
			preparedStatement.setString(2,cherche.getText());
			preparedStatement.setString(3,cherche.getText());
			preparedStatement.setString(4,cherche.getText());

			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			data.add(new Livre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8)));
			
		}
			con.close();
			isbn.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titre.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeur.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anne.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			nbrpage.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("Nbrpage"));
			type.setCellValueFactory(new PropertyValueFactory<Livre,String>("type"));
			tome.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
			nbrdispo.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
			tableulivre.setItems(data);
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
////////////////////////////////////////////////////////////////////
/////////////////////////Ajouter/////////////////////////////////////
////////////////////////////////////////////////////////////////////
	    
	    
	    public void ajouterLivre(ActionEvent e)throws IOException,SQLException{
	    	Document livreAjoute = new Livre();
	    	
	    	ArrayList<String> auteur = new ArrayList<String>();
	    	int st;
	    	
	    	if(!aisbnl.getText().isEmpty()) {
	    		livreAjoute.setISBN(aisbnl.getText());
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	if(!atitrel.getText().isEmpty()) {
	    		livreAjoute.setTitre(atitrel.getText());
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	
	    	if(!aediteurl.getText().isEmpty()) {
	    		livreAjoute.setEditeur(aediteurl.getText());
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	
	    	
	    	if(!aanneel.getText().isEmpty()) {
		    	livreAjoute.setAnnee(Integer.parseInt(aanneel.getText()));
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	
	    	
	    	if(!anbr_page.getText().isEmpty()) {
		    	((Livre) livreAjoute).setNbrpage(Integer.parseInt(anbr_page.getText()));
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	
	    	
	    	if(!anbr_exmpll.getText().isEmpty()) {
		    	((Livre) livreAjoute).setNombreExemplaire(Integer.parseInt(anbr_exmpll.getText()));
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	
	    	
	    	if(!atomel.getText().isEmpty()) {
	    	((Livre) livreAjoute).setTome(atomel.getText());
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	
	    	
	    	
	    	if(!atypel.getText().isEmpty()) {
	    	((Livre) livreAjoute).setType(atypel.getText());
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	
	    	
	    	if(!aauteur1.getText().isEmpty()) {
	    		auteur.add(aauteur1.getText());
	    	}else {
				MenuAdmins.messageWarning();
				return;
			}
	    	
	    	auteur.add(aauteur2.getText());
	    	auteur.add(aateur3.getText());
	    	auteur.add(aauteur4.getText());
	    	
	    	
	    	
	    	livreAjoute.setAuteur(auteur);
	    	st=DocumentBD.saveLivre(livreAjoute);
	    	if(st>0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("Vous operarion est valid! Merci.");
				
				alert.showAndWait();
				clearModifLivre();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message");
				alert.setContentText("Desolé! il a un probleme");
				
				alert.showAndWait();
			}
	    	
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
/////////////////////////////////////////////////////////////////////////////////	    
////////////////////////////////Modifier/////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	    public void modifByISBN(ActionEvent e)throws IOException,SQLException{
	    	chercherLB.setDisable(false);
			chercherMB.setDisable(false);
			chercherDB.setDisable(false);
	    	mcherche.setDisable(false);
	    	mcherchem.setDisable(false);
	    	mcherched.setDisable(false);
			typemodif=0;
		}
		
		
		public void modifByNum(ActionEvent e)throws IOException,SQLException{
			chercherLB.setDisable(false);
			chercherMB.setDisable(false);
			chercherDB.setDisable(false);
	    	mcherche.setDisable(false);
	    	mcherchem.setDisable(false);
	    	mcherched.setDisable(false);
			typemodif=1;
		}
		
		
		
		public void getLivreByISBN(ActionEvent e)throws IOException,SQLException{
			modifierLB.setDisable(false);
			supprimerLB.setDisable(false);
			String isbn;
			if(typemodif==1) {
				isbn=DocumentBD.getIsbnBynum(Integer.parseInt(mcherche.getText()));
			}
			else {
			isbn=mcherche.getText();
			}
			Livre livre = DocumentBD.getLivreByISBNN(isbn);
			ArrayList<String> auteur = DocumentBD.getAuteurByISBN(isbn);
			if(livre==null) {
				MenuAdmins.messageResultatVide();
				}
			else {
			misbnl.setText(livre.getISBN());
			mtitrel.setText(livre.getTitre());
			mediteurl.setText(livre.getEditeur());
			manneel.setText(Integer.toString(livre.getAnnee()));
			mnbr_page.setText(Integer.toString(livre.getNbrpage()));
			mtomel.setText(livre.getTome());
			mtypel.setText(livre.getType());
			mauteur1l.setText(auteur.get(0));
			mauteur2l.setText(auteur.get(1));
			mateur3l.setText(auteur.get(2));
			mauteur4l.setText(auteur.get(3));
			mnbr_exmpll.setText(Integer.toString(livre.getNombreExemplaire()));
			}
			
		}
			

	  public void modifierLivre(ActionEvent e)throws IOException,SQLException{
		  int st;
		  Livre livre = new Livre();
		  ArrayList<String> auteur = new ArrayList<String>();

		 if(!misbnl.getText().isEmpty()) {
			 livre.setISBN(misbnl.getText());
		 }else{
				MenuAdmins.messageWarning();
				return;
				}
		 
		 
		 if(!mtitrel.getText().isEmpty()) {
			 livre.setTitre(mtitrel.getText());
		 }else {
				MenuAdmins.messageWarning();
				return;
				}
		 
		 
		 if(!mediteurl.getText().isEmpty()) {
			 livre.setEditeur(mediteurl.getText());
		 }else {
				MenuAdmins.messageWarning();
				return;
				}
		 
		 
		 if(!mtypel.getText().isEmpty()) {
			 livre.setType(mtypel.getText());
		 }else {
				MenuAdmins.messageWarning();
				return;
				}
		 
		 
		   if(!mtomel.getText().isEmpty()) {
			   livre.setTome(mtomel.getText());
		   }else {
				MenuAdmins.messageWarning();
				return;
				}
		   
		   
		 if(!mnbr_page.getText().isEmpty()) {
			 livre.setNbrpage(Integer.parseInt(mnbr_page.getText()));
		 }else {
				MenuAdmins.messageWarning();
				return;
				}
		 
		 
		 if(!manneel.getText().isEmpty()) {
			 livre.setAnnee(Integer.parseInt(manneel.getText()));
		 }else {
				MenuAdmins.messageWarning();
				return;
				}
		 
		 if(!mauteur1l.getText().isEmpty()) {
			 auteur.add(mauteur1l.getText());
		 }else {
				MenuAdmins.messageWarning();
				return;
				}
		 
		  auteur.add(mauteur2l.getText());
		  auteur.add(mateur3l.getText());
		  auteur.add(mauteur4l.getText());
		  
		  DocumentBD.modifAuteurByIsbn(misbnl.getText(),auteur);
		  st=DocumentBD.updateLivre(livre);
			if(st>0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("Vous operarion est valid! Merci.");
				
				alert.showAndWait();
				clearModifLivre();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message");
				alert.setContentText("Desolé! il a un probleme");
				
				alert.showAndWait();
				
			}	
		  
		  
	  }
	  
	  public void supprimerLivre(ActionEvent e)throws IOException,SQLException{
		  int st=0;
		  if(typemodif==0 || Integer.parseInt(mnbr_exmpll.getText())<2) {
			  st=DocumentBD.supprimerLivre(misbnl.getText());
		  }
		  else {
			  st=DocumentBD.supprimerLivreOccurance(Integer.parseInt(mcherche.getText()));
		  }
			if(st>0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("Vous operarion est valid! Merci.");
				
				alert.showAndWait();
				clearModifLivre();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message");
				alert.setContentText("Desolé! il a un probleme");
				
				alert.showAndWait();
			}	
		  
	  }
	  public void clearModifLivre() {
		 //desible
		  mcherche.setDisable(true);
		  chercherLB.setDisable(true);
		  supprimerLB.setDisable(true);
		  modifierLB.setDisable(true);
		 
		 //clear
		 mcherche.clear();
		 misbnl.clear();
		 mtitrel.clear();
		 mediteurl.clear();
		 manneel.clear();
		 mnbr_page.clear();
		 mtomel.clear();
		 mtypel.clear();
		 mauteur1l.clear();
		 mauteur2l.clear();
		 mateur3l.clear();
		 mauteur4l.clear();
		 mnbr_exmpll.clear();
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////Magazin////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

	public void  tableMagazine(){

		tableumagazine.getItems().clear();
		try {
		String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,periode,date_edit  from Document,magazin where document.isbn=magazin.isbn and code_adh IS NULL group by document.isbn";
		Connection con = DocumentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs  = preparedStatement.executeQuery();
		while(rs.next()) {
		dataMagazine.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
	}
		
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		isbnm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titrem.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annem.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		periode.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("periode"));
		dateedition.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
		nbrdispom.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
		tableumagazine.setItems(dataMagazine);
		}
	
	
	
	
	
	
	
	
	
	
///////////////////////////////////////////////////////////////////////
//////////////////////////Chercher/////////////////////////////////////
//////////////////////////////////////////////////////////////////////

public void magazineBY(ActionEvent e)throws IOException,SQLException{
	switch(by) {
	case 1: chercheMagazineByIsbn(e);break;
	case 2: chercheMagazineByTitre(e);break;
	case 3:	chercheMagazineByAuteur(e);break;
	default: break;
	}
}



public void chercheMagazineByIsbn(ActionEvent e)throws IOException,SQLException{
	tableumagazine.getItems().clear();
	
	String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,periode,date_edit  from Document,magazin where document.isbn=magazin.isbn and code_adh IS NULL and document.isbn=? group by document.isbn";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1,cherchem.getText());
	ResultSet rs  = preparedStatement.executeQuery();
	while(rs.next()) {
	dataMagazine.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
	
	}
	con.close();
	isbnm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
	titrem.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
	editeurm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
	annem.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
	periode.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("periode"));
	dateedition.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
	nbrdispom.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
	
	tableumagazine.setItems(dataMagazine);
	
	}

public void chercheMagazineByTitre(ActionEvent e)throws IOException,SQLException{
	tableumagazine.getItems().clear();
	
	String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,periode,date_edit  from Document,magazin where document.isbn=magazin.isbn and code_adh IS NULL and titre=? group by document.isbn";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1,cherchem.getText());
	ResultSet rs  = preparedStatement.executeQuery();
	while(rs.next()) {
		dataMagazine.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
		
		}
		con.close();
		isbnm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titrem.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annem.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		periode.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("periode"));
		dateedition.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
		nbrdispom.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
		tableumagazine.setItems(dataMagazine);

}

public void chercheMagazineByAuteur(ActionEvent e)throws IOException,SQLException{
	tableumagazine.getItems().clear();
	
	String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl,periode,date_edit  from Document,magazin,liste_auteur WHERE document.isbn=magazin.isbn and document.isbn=liste_auteur.isbn and code_adh is null and (auteur1=? OR auteur2=? OR auteur3=? or auteur4=?)   group by document.isbn";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	
	preparedStatement.setString(1,cherchem.getText());
	preparedStatement.setString(2,cherchem.getText());
	preparedStatement.setString(3,cherchem.getText());
	preparedStatement.setString(4,cherchem.getText());
	
	ResultSet rs  = preparedStatement.executeQuery();
	while(rs.next()) {
		dataMagazine.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
		
		}
		con.close();
		isbnm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titrem.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annem.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		periode.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("periode"));
		dateedition.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
		nbrdispom.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
		tableumagazine.setItems(dataMagazine);

}






////////////////////////////////////////////////////////////////////
/////////////////////////Ajouter/////////////////////////////////////
////////////////////////////////////////////////////////////////////
public void ajouterMagazine(ActionEvent e)throws IOException,SQLException{

	Magazine magazineAjoute = new Magazine();
	ArrayList<String> auteur = new ArrayList<String>();
	int st;
	
	if(!aisbnm.getText().isEmpty()) {
		magazineAjoute.setISBN(aisbnm.getText());
	}else {
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	if(!atitrem.getText().isEmpty()) {
		magazineAjoute.setTitre(atitrem.getText());
	}else {
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	
	if(!aediteurm.getText().isEmpty()) {
		magazineAjoute.setEditeur(aediteurm.getText());
	} else {
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	
	if(!aeditionm.getText().isEmpty()) {
		magazineAjoute.setDate_edit(aeditionm.getText());
	} else {
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	if(!aanneem.getText().isEmpty()) {
		magazineAjoute.setAnnee(Integer.parseInt(aanneem.getText()));
	}else {
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	if(!anbr_exmplm.getText().isEmpty()) {
		magazineAjoute.setNombreExemplaire(Integer.parseInt(anbr_exmplm.getText()));
	}
	else {
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	if(!aperiodem.getText().isEmpty()) {
		magazineAjoute.setPeriode(Integer.parseInt(aperiodem.getText()));
	} else {
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	
	if(!aauteur1m.getText().isEmpty()) {
		auteur.add(aauteur1m.getText());
	} else{
			MenuAdmins.messageWarning();
			return;
			}
	 
	
	auteur.add(aauteur2m.getText());
	auteur.add(aauteur3m.getText());
	auteur.add(aauteur4m.getText());
	magazineAjoute.setAuteur(auteur);

	st=DocumentBD.saveMagazine(magazineAjoute);
	if(st>0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Vous operarion est valid! Merci.");
		
		alert.showAndWait();
		clearModifMagazine();
	}else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Message");
		alert.setContentText("Desolé! il a un probleme");
		
		alert.showAndWait();
	}
	
}









/////////////////////////////////////////////////////////////////////////////////	    
////////////////////////////////Modifier/////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

		public void getMagazineByISBN(ActionEvent e)throws IOException,SQLException{
		modifierMB.setDisable(false);
		supprimerMB.setDisable(false);
		String isbn;
		if(typemodif==1) {
		isbn=DocumentBD.getIsbnBynum(Integer.parseInt(mcherchem.getText()));
		}
		else {
		isbn=mcherchem.getText();
		}
		Magazine magazine = DocumentBD.getMagazineByISBNN(isbn);
		ArrayList<String> auteur = DocumentBD.getAuteurByISBN(isbn);
		if(magazine==null) {
			MenuAdmins.messageResultatVide();
			}
		else {
		misbnm.setText(magazine.getISBN());
		mtitrem.setText(magazine.getTitre());
		mediteurm.setText(magazine.getEditeur());
		manneem.setText(Integer.toString(magazine.getAnnee()));
		mperiodem.setText(Integer.toString(magazine.getPeriode()));
		meditionm.setText(magazine.getDate_edit());
		mauteur1m.setText(auteur.get(0));
		mauteur2m.setText(auteur.get(1));
		mateur3m.setText(auteur.get(2));
		mauteur4m.setText(auteur.get(3));
		mnbr_exmplm.setText(Integer.toString(magazine.getNombreExemplaire()));
		
		}
		}
		
		
		public void modifierMagazine(ActionEvent e)throws IOException,SQLException{
		int st=0;
		Magazine magazineAjoute = new Magazine();
		ArrayList<String> auteur = new ArrayList<String>();
		
		if(!misbnm.getText().isEmpty()) {
			magazineAjoute.setISBN(misbnm.getText());
		} else {
			MenuAdmins.messageWarning();
			return;
			}
		 
		
		if(!mtitrem.getText().isEmpty()) {
			magazineAjoute.setTitre(mtitrem.getText());
		}else {
			MenuAdmins.messageWarning();
			return;
			}
		 
		
		
		if(!mediteurm.getText().isEmpty()) {
			magazineAjoute.setEditeur(mediteurm.getText());
		} else {
			MenuAdmins.messageWarning();
			return;
			}
		 
		
		
		if(!meditionm.getText().isEmpty()) {
			magazineAjoute.setDate_edit(meditionm.getText());
		} else {
			MenuAdmins.messageWarning();
			return;
			}
		
		if(!manneem.getText().isEmpty()) {
			magazineAjoute.setAnnee(Integer.parseInt(manneem.getText()));
		}else {
			MenuAdmins.messageWarning();
			return;
			}
		 
		
		if(!mperiodem.getText().isEmpty()) {
			magazineAjoute.setPeriode(Integer.parseInt(mperiodem.getText()));
		}else {
			MenuAdmins.messageWarning();
			return;
			}
		
		
		if(!mnbr_exmplm.getText().isEmpty()) {
			magazineAjoute.setNombreExemplaire(Integer.parseInt(mnbr_exmplm.getText()));
		}else {
			MenuAdmins.messageWarning();
			return;
			}
		if(!mauteur1m.getText().isEmpty()) {
				auteur.add(mauteur1m.getText())	;}
		else {
			MenuAdmins.messageWarning();
			return;
			}
		auteur.add(mauteur2m.getText());
		auteur.add(mateur3m.getText());
		auteur.add(mauteur4m.getText());
		
		DocumentBD.modifAuteurByIsbn(misbnm.getText(),auteur);
		st=DocumentBD.updateMagazine(magazineAjoute);
		if(st>0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Vous operarion est valid! Merci.");
		
		alert.showAndWait();
		}else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Message");
		alert.setContentText("Desolé! il a un probleme");
		
		alert.showAndWait();
		}	
		
		
		}
		
		public void supprimerMagazine(ActionEvent e)throws IOException,SQLException{
		int st=0;
		if(typemodif==0 || Integer.parseInt(mnbr_exmplm.getText())<2) {
		st=DocumentBD.supprimerMagazine(misbnm.getText());
		}
		else {
		st=DocumentBD.supprimerMagazineOccurance(Integer.parseInt(mcherchem.getText()));
		}
		if(st>0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Vous operarion est valid! Merci.");
		
		alert.showAndWait();
		clearModifMagazine();
		}else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Message");
		alert.setContentText("Desolé! il a un probleme");
		
		alert.showAndWait();
		}	
		
		}
		public void clearModifMagazine() {
		//desible
		mcherchem.setDisable(true);
		chercherMB.setDisable(true);
		supprimerMB.setDisable(true);
		modifierMB.setDisable(true);
		
		//clear
		mcherchem.clear();
		misbnm.clear();
		mtitrem.clear();
		mediteurm.clear();
		manneem.clear();
		mperiodem.clear();
		meditionm.clear();
		mauteur1m.clear();
		mauteur2m.clear();
		mateur3m.clear();
		mauteur4m.clear();
		mnbr_exmplm.clear();
		}

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////Dictionnaire////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
		public void  tableDictionnaire(){
		
		tableudictionnaire.getItems().clear();
		try {
		String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,langue_dic,tome  from Document,dictionnaire where document.isbn=dictionnaire.isbn and code_adh IS NULL group by document.isbn";
		Connection con = DocumentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs  = preparedStatement.executeQuery();
		while(rs.next()) {
		dataDictionnaire.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
		}
		
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		isbnd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titred.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		anned.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		langue.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
		tomed.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
		nbrdispod.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
		tableudictionnaire.setItems(dataDictionnaire);
		}
		
		
		
		
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////
		//////////////////////////Chercher/////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		
		
		public void dictionnaireBY(ActionEvent e)throws IOException,SQLException{
		switch(by) {
		case 1: ChercheDictionnaireByIsbn(e);break;
		case 2: ChercheDictionnaireByTitre(e);break;
		case 3:	ChercheDictionnaireByAuteur(e);break;
		default: break;
		}
		}
		
		
		
		public void ChercheDictionnaireByIsbn(ActionEvent e)throws IOException,SQLException{
		tableudictionnaire.getItems().clear();
		
		String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,langue_dic,tome  from Document,dictionnaire where document.isbn=dictionnaire.isbn and code_adh IS NULL and document.isbn=? group by document.isbn";
		Connection con = DocumentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setString(1,cherched.getText());
		ResultSet rs  = preparedStatement.executeQuery();
		while(rs.next()) {
		dataDictionnaire.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
		}
		con.close();
		isbnd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titred.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		anned.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		langue.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
		tomed.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
		nbrdispod.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
		tableudictionnaire.setItems(dataDictionnaire);
		
		}
		
		public void ChercheDictionnaireByTitre(ActionEvent e)throws IOException,SQLException{
			tableudictionnaire.getItems().clear();
			
			String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,langue_dic,tome  from Document,dictionnaire where document.isbn=dictionnaire.isbn and code_adh IS NULL and document.titre=? group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherched.getText());
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			dataDictionnaire.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
			}
			con.close();
			isbnd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titred.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeurd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anned.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			langue.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
			tomed.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
			nbrdispod.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
			tableudictionnaire.setItems(dataDictionnaire);
		}
		
		
		public void ChercheDictionnaireByAuteur(ActionEvent e)throws IOException,SQLException{
			tableudictionnaire.getItems().clear();
			
			String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl,langue_dic,tome  from Document,dictionnaire,liste_auteur WHERE document.isbn=dictionnaire.isbn and document.isbn=liste_auteur.isbn and code_adh is null and (auteur1=? OR auteur2=? OR auteur3=? or auteur4=?)   group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		
			preparedStatement.setString(1,cherched.getText());
			preparedStatement.setString(2,cherched.getText());
			preparedStatement.setString(3,cherched.getText());
			preparedStatement.setString(4,cherched.getText());
			
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			dataDictionnaire.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
			}
			con.close();
			isbnd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titred.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeurd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anned.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			langue.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
			tomed.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
			nbrdispod.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
			tableudictionnaire.setItems(dataDictionnaire);
		}
		
		
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////
		/////////////////////////Ajouter/////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		
		
		public void remplireDictionnaire(ActionEvent e)throws IOException,SQLException{
			Dictionnaire dictionnaireeAjoute = new Dictionnaire();
			ArrayList<String> auteur = new ArrayList<String>();
			int st;
			
			if(!aisbnd.getText().isEmpty()) {
				dictionnaireeAjoute.setISBN(aisbnd.getText());
			} else {
				MenuAdmins.messageWarning();
				return;
				}
			 
			
			if(!atitred.getText().isEmpty()) {
				dictionnaireeAjoute.setTitre(atitred.getText());
			}else {
				MenuAdmins.messageWarning();
				return;
				}
			 
			
			
			if(!aediteurd.getText().isEmpty()) {
				dictionnaireeAjoute.setEditeur(aediteurd.getText());
			} else {
				MenuAdmins.messageWarning();
				return;
				}
			 
			
			
			if(!alangued.getText().isEmpty()) {
				dictionnaireeAjoute.setLangue(alangued.getText());
			} else {
				MenuAdmins.messageWarning();
				return;
				}
			
			if(!aanneed.getText().isEmpty()) {
				dictionnaireeAjoute.setAnnee(Integer.parseInt(aanneed.getText()));
			}else {
				MenuAdmins.messageWarning();
				return;
				}
			 
			
			if(!atomed.getText().isEmpty()) {
				dictionnaireeAjoute.setTome(atomed.getText());
			}else {
				MenuAdmins.messageWarning();
				return;
				}
			
			if(!anbr_exmpld.getText().isEmpty()) {
				dictionnaireeAjoute.setNombreExemplaire(Integer.parseInt(anbr_exmpld.getText()));
			}else {
				MenuAdmins.messageWarning();
				return;
				}
			if(!aauteur1d.getText().isEmpty()) {
				auteur.add(aauteur1d.getText());			
				}else {
				MenuAdmins.messageWarning();
				return;
				}
			
					auteur.add(aauteur2d.getText());
					auteur.add(aauteur3d.getText());
					auteur.add(aauteur4d.getText());
					dictionnaireeAjoute.setAuteur(auteur);
		
				st=DocumentBD.saveDictionnaire(dictionnaireeAjoute);
				if(st>0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Vous operarion est valid! Merci.");
					
					alert.showAndWait();
					clearModifDictionnaire();
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Message");
					alert.setContentText("Desolé! il a un probleme");
					
					alert.showAndWait();
				}	
			
		}
		
		
		
		
		
		
		
		
		
		/////////////////////////////////////////////////////////////////////////////////	    
		////////////////////////////////Modifier/////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		
		
				public void getDictionnaireByISBN(ActionEvent e)throws IOException,SQLException{
				modifierDB.setDisable(false);
				supprimerDB.setDisable(false);
				String isbn;
				if(typemodif==1) {
				isbn=DocumentBD.getIsbnBynum(Integer.parseInt(mcherched.getText()));
				}
				else {
				isbn=mcherched.getText();
				}
				Dictionnaire dictionnaire = DocumentBD.getDictionnaireByISBNN(isbn);
				ArrayList<String> auteur = DocumentBD.getAuteurByISBN(isbn);
				if(dictionnaire==null) {
					MenuAdmins.messageResultatVide();
					}
				else {
				misbnd.setText(dictionnaire.getISBN());
				mtitred.setText(dictionnaire.getTitre());
				mediteurd.setText(dictionnaire.getEditeur());
				manneed.setText(Integer.toString(dictionnaire.getAnnee()));
				mlangued.setText(dictionnaire.getLangue());
				mtomed.setText(dictionnaire.getTome());
				mauteur1d.setText(auteur.get(0));
				mauteur2d.setText(auteur.get(1));
				mateur3d.setText(auteur.get(2));
				mauteur4d.setText(auteur.get(3));
				mnbr_exmpld.setText(Integer.toString(dictionnaire.getNombreExemplaire()));
				
				}
				}
				
				
				public void modifierDictionnaire(ActionEvent e)throws IOException,SQLException{
				int st;
				Dictionnaire dictionnaireeAjoute = new Dictionnaire();
				ArrayList<String> auteur = new ArrayList<String>();
				
				if(!misbnd.getText().isEmpty()) {
					dictionnaireeAjoute.setISBN(misbnd.getText());
				} else {
					MenuAdmins.messageWarning();
					return;
					}
				 
				
				if(!mtitred.getText().isEmpty()) {
					dictionnaireeAjoute.setTitre(mtitred.getText());
				}else {
					MenuAdmins.messageWarning();
					return;
					}
				 
				
				
				if(!mediteurd.getText().isEmpty()) {
					dictionnaireeAjoute.setEditeur(mediteurd.getText());
				} else {
					MenuAdmins.messageWarning();
					return;
					}
				 
				
				
				if(!mlangued.getText().isEmpty()) {
					dictionnaireeAjoute.setLangue(mlangued.getText());
				} else {
					MenuAdmins.messageWarning();
					return;
					}
				
				if(!manneed.getText().isEmpty()) {
					dictionnaireeAjoute.setAnnee(Integer.parseInt(manneed.getText()));
				}else {
					MenuAdmins.messageWarning();
					return;
					}
				 
				
				if(!mtomed.getText().isEmpty()) {
					dictionnaireeAjoute.setTome(mtomed.getText());
				}else {
					MenuAdmins.messageWarning();
					return;
					}
				
				if(!mnbr_exmpld.getText().isEmpty()) {
					dictionnaireeAjoute.setNombreExemplaire(Integer.parseInt(mnbr_exmpld.getText()));
				}else {
					MenuAdmins.messageWarning();
					return;
					}
				if(!mauteur1d.getText().isEmpty()) {
					auteur.add(mauteur1d.getText());			
					}else {
					MenuAdmins.messageWarning();
					return;
					}
				auteur.add(mauteur2d.getText());
				auteur.add(mateur3d.getText());
				auteur.add(mauteur4d.getText());
				
				DocumentBD.modifAuteurByIsbn(misbnd.getText(),auteur);
				st=DocumentBD.updateDictionnaire(dictionnaireeAjoute);
				if(st>0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("Vous operarion est valid! Merci.");
				
				alert.showAndWait();
				clearModifDictionnaire();
				}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message");
				alert.setContentText("Desolé! il a un probleme");
				
				alert.showAndWait();
				}	
				
				
				}
				
				public void supprimerDictionnaire(ActionEvent e)throws IOException,SQLException{
				int st=0;
				if(typemodif==0 || Integer.parseInt(mnbr_exmpld.getText())<2) {
				st=DocumentBD.supprimerDictionnaire(misbnd.getText());
				}
				else {
				st=DocumentBD.supprimerOccurance(Integer.parseInt(mcherched.getText()));
				}
				if(st>0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("Vous operarion est valid! Merci.");
				
				alert.showAndWait();
				}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Message");
				alert.setContentText("Desolé! il a un probleme");
				
				alert.showAndWait();
				}	
				
				}
				public void clearModifDictionnaire() {
				//desible
				mcherched.setDisable(true);
				chercherDB.setDisable(true);
				supprimerDB.setDisable(true);
				modifierDB.setDisable(true);
				
				//clear
				mcherched.clear();
				misbnd.clear();
				mtitred.clear();
				mediteurd.clear();
				manneed.clear();
				mlangued.clear();
				mtomed.clear();
				mauteur1d.clear();
				mauteur2d.clear();
				mateur3d.clear();
				mauteur4d.clear();
				mnbr_exmpld.clear();
				}

	
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Gestion des adherent///////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
		
		
		
		
		
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////Professeur////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
		
public void  tableProfesseur(){
	    	
	    	tableuprofesseur.getItems().clear();
			try {
				String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,departement from adherent,professeur where code_adh=cin";
				Connection con = AdherentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs  = preparedStatement.executeQuery();
				while(rs.next()) {
					dataProfesseur.add(new Professeur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
				}
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			cinp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("code_adh"));
			nomp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("nom"));
			prenomp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("prenom"));
			adressep.setCellValueFactory(new PropertyValueFactory<Professeur,String>("adresse"));
			telp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("tel"));
			departementp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("departement"));
			emprunterp.setCellValueFactory(new PropertyValueFactory<Professeur,Integer>("nbr_eprunter"));
			
			tableuprofesseur.setItems(dataProfesseur);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
////////////////////////////////////////////////////////////////////
/////////////////////////Ajouter/////////////////////////////////////
////////////////////////////////////////////////////////////////////


		public void ajouterProfesseur(ActionEvent e)throws IOException,SQLException{
		Adherent profAjoute = new Professeur();
		
		boolean st;

		
		if(!acode_adhp.getText().isEmpty()) {
		profAjoute.setCode_adh(acode_adhp.getText());
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		
		
		if(!anomp.getText().isEmpty()) {
		profAjoute.setNom(anomp.getText());;
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		
		
		if(!aprenomp.getText().isEmpty()) {
		profAjoute.setPrenom(aprenomp.getText());
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		
		
		if(!atelp.getText().isEmpty()) {
		profAjoute.setTel(atelp.getText());
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		
		profAjoute.setAdresse(aadressep.getText());

		if(!adepartementp.getText().isEmpty()) {
			((Professeur) profAjoute).setDepartement(adepartementp.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
		
		
		profAjoute.setTypeAdh(1);

		
		st=AdherentBD.save(profAjoute);
		if(st) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Vous operarion est valid! Merci.");
		alert.showAndWait();
		clearProfesseur();
		}else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Message");
		alert.setContentText("Desolé! il a un probleme");
		
		alert.showAndWait();
		}
		
		
		}



///////////////////////////////////////////////////////////////////////////
//////////////////////////Chercher/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

	    public void ByCin(ActionEvent e)throws IOException,SQLException{
	    	cherchep.setDisable(false);
	    	chercherPB.setDisable(false);
	    	cherchee.setDisable(false);
	    	dchercherEB.setDisable(false);
	    	cherchex.setDisable(false);
	    	chercherXB.setDisable(false);
	    	by=1;

	    }
	    public void ByNom(ActionEvent e)throws IOException,SQLException{
	    	cherchep.setDisable(false);
	    	chercherPB.setDisable(false);
	    	cherchee.setDisable(false);
	    	dchercherEB.setDisable(false);
	    	cherchex.setDisable(false);
	    	chercherXB.setDisable(false);
	    	by=2;
	    }
	    
	    public void BYCherche(ActionEvent e)throws IOException,SQLException{
	    	switch(by) {
	    	case 1: ChercheByCIN(e);break;
	    	case 2: ChercheByNom(e);break;
	    	default: break;
	    	}
	    }
	    
	    
	    
	    public void ChercheByCIN(ActionEvent e)throws IOException,SQLException{
	    	tableuprofesseur.getItems().clear();
			try {
				String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,departement from adherent,professeur where code_adh=cin and cin=?";
				Connection con = AdherentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				preparedStatement.setString(1,cherchep.getText());
				ResultSet rs  = preparedStatement.executeQuery();
				
				while(rs.next()) {
					dataProfesseur.add(new Professeur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
				}
				con.close();
			}catch(SQLException event) {
				event.printStackTrace();
			}
			cinp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("code_adh"));
			nomp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("nom"));
			prenomp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("prenom"));
			adressep.setCellValueFactory(new PropertyValueFactory<Professeur,String>("adresse"));
			telp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("tel"));
			departementp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("departement"));
			emprunterp.setCellValueFactory(new PropertyValueFactory<Professeur,Integer>("nbr_eprunter"));
			
			tableuprofesseur.setItems(dataProfesseur);
	    	
	    }
	    	    
	    public void ChercheByNom(ActionEvent e)throws IOException,SQLException{
	    	tableuprofesseur.getItems().clear();
			try {
				String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,departement from adherent,professeur where code_adh=cin and nom=?";
				Connection con = AdherentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				preparedStatement.setString(1,cherchep.getText());
				ResultSet rs  = preparedStatement.executeQuery();
				
				while(rs.next()) {
					dataProfesseur.add(new Professeur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
				}
				con.close();
			}catch(SQLException event) {
				event.printStackTrace();
			}
			cinp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("code_adh"));
			nomp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("nom"));
			prenomp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("prenom"));
			adressep.setCellValueFactory(new PropertyValueFactory<Professeur,String>("adresse"));
			telp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("tel"));
			departementp.setCellValueFactory(new PropertyValueFactory<Professeur,String>("departement"));
			emprunterp.setCellValueFactory(new PropertyValueFactory<Professeur,Integer>("nbr_eprunter"));
			
			tableuprofesseur.setItems(dataProfesseur);
	    	
	    }

	
		
		
		
///////////////////////////////////////////////////////////////////////////
//////////////////////////Modifier/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
		
	    public void getProfesseurByCIN(ActionEvent e)throws IOException,SQLException{
			String code_adh;
			code_adh=mcherchep.getText();
			
			Professeur professeur = AdherentBD.getProfesseurByCode(code_adh);
			if(professeur==null) {
				MenuAdmins.messageResultatVide();
				}
				else {
					mmodifierPB.setDisable(false);
					msupprimerPB.setDisable(false);
					mcode_ahdp.setText(professeur.getCode_adh());
					mnomp.setText(professeur.getNom());
					mprenomp.setText(professeur.getPrenom());
					madressep.setText(professeur.getAdresse());
					mtelp.setText(professeur.getTel());
					mdepartementp.setText(professeur.getDepartement());
					memprunterp.setText(Integer.toString(professeur.getNbr_eprunter()));
				}
			}
	    
	    
	    public void modifirProfesseur(ActionEvent e)throws IOException,SQLException{
			Adherent adherent = new Professeur();
			
			if(!mnomp.getText().isEmpty()) {
				adherent.setNom(mnomp.getText());
				}else {
				MenuAdmins.messageWarning();
				return;
				}
			
			
			if(!mprenomp.getText().isEmpty()) {
				adherent.setPrenom(mprenomp.getText());
				}else {
				MenuAdmins.messageWarning();
				return;
				}
			
			
			adherent.setAdresse(madressep.getText());
		
			
			
			if(!mtelp.getText().isEmpty()) {
				adherent.setTel(mtelp.getText());
				}else {
				MenuAdmins.messageWarning();
				return;
				}
			
		
			if(!mdepartementp.getText().isEmpty()) {
				((Professeur)adherent).setDepartement(mdepartementp.getText());
				}else {
				MenuAdmins.messageWarning();
				return;
				}
			
			adherent.setTypeAdh(1);
			adherent.setCode_adh(mcode_ahdp.getText());
			
			int st=AdherentBD.updateAdherent(adherent);
			if(st!=0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Vous operarion est valid! Merci.");
			alert.showAndWait();
			clearProfesseur();
			}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message");
			alert.setContentText("Desolé! il a un probleme");
			alert.showAndWait();
			}	
			
			
			}
			
			public void supprimerProfesseur(ActionEvent e)throws IOException,SQLException{
			
			Adherent adherent = new Professeur();
			adherent.setCode_adh(mcode_ahdp.getText());
			adherent.setTypeAdh(1);
			boolean st=AdherentBD.supprimerAdherent(adherent);
			
			if(st) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Vous operarion est valid! Merci.");
			alert.showAndWait();
			clearProfesseur();
			}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message");
			alert.setContentText("Desolé! il a un probleme");
			
			alert.showAndWait();
			}	
			
			}
			
			
			public void clearProfesseur() {
			//desible
			msupprimerPB.setDisable(true);
			mmodifierPB.setDisable(true);
			
			//clear
			mcherchep.clear();
			mcode_ahdp.clear();
			mnomp.clear();
			mprenomp.clear();
			mtelp.clear();
			madressep.clear();
			mdepartementp.clear();
			memprunterp.clear();
			}

		
	    
	    
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////Etudiant////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

		public void  tableEtudiant(){
		
		tableuetudiant.getItems().clear();
		try {
		String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,filiere from adherent,etudiant where code_adh=cne";
		Connection con = AdherentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs  = preparedStatement.executeQuery();
		while(rs.next()) {
		dataEtudiant.add(new Etudiant(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
		}
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		cnee.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("code_adh"));
		nome.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
		prenome.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
		adressee.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("adresse"));
		tele.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("tel"));
		filieree.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("filiere"));
		empruntere.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("nbr_eprunter"));
		
		tableuetudiant.setItems(dataEtudiant);
		}




////////////////////////////////////////////////////////////////////
/////////////////////////Ajouter/////////////////////////////////////
////////////////////////////////////////////////////////////////////


			public void ajouterEtudiant(ActionEvent e)throws IOException,SQLException{
			Adherent etudiantAjoute = new Etudiant();
			
			boolean st;
			
			
			if(!acode_adhe.getText().isEmpty()) {
			etudiantAjoute.setCode_adh(acode_adhe.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			
			if(!anome.getText().isEmpty()) {
			etudiantAjoute.setNom(anome.getText());;
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			
			if(!aprenome.getText().isEmpty()) {
			etudiantAjoute.setPrenom(aprenome.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			
			if(!atele.getText().isEmpty()) {
			etudiantAjoute.setTel(atele.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			etudiantAjoute.setAdresse(aadressee.getText());
			
			if(!afilieree.getText().isEmpty()) {
			((Etudiant) etudiantAjoute).setFiliere(afilieree.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			etudiantAjoute.setTypeAdh(0);
			
			
			st=AdherentBD.save(etudiantAjoute);
			if(st) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Vous operarion est valid! Merci.");
			alert.showAndWait();
			clearEtudiant();
			}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message");
			alert.setContentText("Desolé! il a un probleme");
			
			alert.showAndWait();
			}
			
			
			}





///////////////////////////////////////////////////////////////////////////
//////////////////////////Chercher/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

		
			
			public void BYEtudiant(ActionEvent e)throws IOException,SQLException{
			switch(by) {
			case 1: ChercheEtudiantByCNE(e);break;
			case 2: ChercheEtudiantByNom(e);break;
			default: break;
			}
			}
			
			
			
			public void ChercheEtudiantByCNE(ActionEvent e)throws IOException,SQLException{
				tableuetudiant.getItems().clear();
				try {
				String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,filiere from adherent,etudiant where code_adh=cne and cne=?";
				Connection con = AdherentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				preparedStatement.setString(1,cherchee.getText());
				ResultSet rs  = preparedStatement.executeQuery();
				while(rs.next()) {
				dataEtudiant.add(new Etudiant(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
				}
				con.close();
				}catch(SQLException event) {
				event.printStackTrace();
				}
				cnee.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("code_adh"));
				nome.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
				prenome.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
				adressee.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("adresse"));
				tele.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("tel"));
				filieree.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("filiere"));
				empruntere.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("nbr_eprunter"));
				
				tableuetudiant.setItems(dataEtudiant);
			
			}
			
			public void ChercheEtudiantByNom(ActionEvent e)throws IOException,SQLException{
				tableuetudiant.getItems().clear();
				try {
				String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,filiere from adherent,etudiant where code_adh=cne and nom=?";
				Connection con = AdherentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				preparedStatement.setString(1,cherchee.getText());
				ResultSet rs  = preparedStatement.executeQuery();
				while(rs.next()) {
				dataEtudiant.add(new Etudiant(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
				}
				con.close();
				}catch(SQLException event) {
				event.printStackTrace();
				}
				cnee.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("code_adh"));
				nome.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
				prenome.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
				adressee.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("adresse"));
				tele.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("tel"));
				filieree.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("filiere"));
				empruntere.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("nbr_eprunter"));
				
				tableuetudiant.setItems(dataEtudiant);
			
			}
			
			
			
			
///////////////////////////////////////////////////////////////////////////
//////////////////////////Modifier/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

					public void getEtudiantByCNE(ActionEvent e)throws IOException,SQLException{
					
					String code_adh;
					code_adh=mcherchee.getText();
					
					Etudiant etudiant = AdherentBD.getEtudiantByCode(code_adh);
					if(etudiant==null) {
						MenuAdmins.messageResultatVide();
						}
						else {
					mmodifierEB.setDisable(false);
					msupprimerEB.setDisable(false);
					mcode_ahde.setText(etudiant.getCode_adh());
					mnome.setText(etudiant.getNom());
					mprenome.setText(etudiant.getPrenom());
					madressee.setText(etudiant.getAdresse());
					mtele.setText(etudiant.getTel());
					mfilieree.setText(etudiant.getFiliere());
					memprunterp.setText(Integer.toString(etudiant.getNbr_eprunter()));	
						}
					
					}
					
					
					
					
					public void modifirEtudiant(ActionEvent e)throws IOException,SQLException{
					Adherent adherent = new Etudiant();
					
					if(!mnome.getText().isEmpty()) {
					adherent.setNom(mnome.getText());
					}else {
					MenuAdmins.messageWarning();
					return;
					}
					
					
					if(!mprenome.getText().isEmpty()) {
					adherent.setPrenom(mprenome.getText());
					}else {
					MenuAdmins.messageWarning();
					return;
					}
					
					
					adherent.setAdresse(madressee.getText());
					
					
					
					if(!mtele.getText().isEmpty()) {
					adherent.setTel(mtele.getText());
					}else {
					MenuAdmins.messageWarning();
					return;
					}
					
					
					if(!mfilieree.getText().isEmpty()) {
					((Etudiant)adherent).setFiliere(mfilieree.getText());
					}else {
					MenuAdmins.messageWarning();
					return;
					}
					
					adherent.setTypeAdh(0);
					adherent.setCode_adh(mcode_ahde.getText());
					
					int st=AdherentBD.updateAdherent(adherent);
					if(st!=0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Vous operarion est valid! Merci.");
					alert.showAndWait();
					clearEtudiant();
					}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Message");
					alert.setContentText("Desolé! il a un probleme");
					alert.showAndWait();
					}	
					
					
					}
					
					public void supprimerEtudiant(ActionEvent e)throws IOException,SQLException{
					
					Adherent adherent = new Etudiant();
					adherent.setCode_adh(mcode_ahde.getText());
					adherent.setTypeAdh(0);
					boolean st=AdherentBD.supprimerAdherent(adherent);
					
					if(st) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Vous operarion est valid! Merci.");
					alert.showAndWait();
					clearEtudiant();
					}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Message");
					alert.setContentText("Desolé! il a un probleme");
					
					alert.showAndWait();
					}	
					
					}
					
					
					public void clearEtudiant() {
					//desible
					msupprimerEB.setDisable(true);
					mmodifierEB.setDisable(true);
					
					//clear
					mcherchee.clear();
					mcode_ahde.clear();
					mnome.clear();
					mprenome.clear();
					mtele.clear();
					madressee.clear();
					mfilieree.clear();
					mempruntere.clear();
					}



///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
////////////////////////////Personne////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

		public void  tablePersonne(){
		
		tableupersonne.getItems().clear();
		try {
		String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,metier from adherent,personne where code_adh=cin";
		Connection con = AdherentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs  = preparedStatement.executeQuery();
		while(rs.next()) {
		dataPersonne.add(new Personne(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
		}
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		cinx.setCellValueFactory(new PropertyValueFactory<Personne,String>("code_adh"));
		nomx.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
		prenomx.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
		adressex.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
		telx.setCellValueFactory(new PropertyValueFactory<Personne,String>("tel"));
		metierx.setCellValueFactory(new PropertyValueFactory<Personne,String>("metiere"));
		emprunterx.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("nbr_eprunter"));
		
		tableupersonne.setItems(dataPersonne);
		}
		
		
		
		
		
////////////////////////////////////////////////////////////////////
/////////////////////////Ajouter/////////////////////////////////////
////////////////////////////////////////////////////////////////////


			public void ajouterPersonne(ActionEvent e)throws IOException,SQLException{
			Adherent personneAjoute = new Personne();
			
			boolean st;
			
			
			if(!acode_adhx.getText().isEmpty()) {
			personneAjoute.setCode_adh(acode_adhx.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			
			if(!anomex.getText().isEmpty()) {
			personneAjoute.setNom(anomex.getText());;
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			
			if(!aprenomx.getText().isEmpty()) {
				personneAjoute.setPrenom(aprenomx.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			
			if(!atelx.getText().isEmpty()) {
				personneAjoute.setTel(atelx.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			personneAjoute.setAdresse(aadressex.getText());
			
			if(!ametierx.getText().isEmpty()) {
			((Personne) personneAjoute).setMetiere(ametierx.getText());
			}else {
			MenuAdmins.messageWarning();
			return;
			}
			
			
			personneAjoute.setTypeAdh(2);
			
			
			st=AdherentBD.save(personneAjoute);
			if(st) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Vous operarion est valid! Merci.");
			alert.showAndWait();
			clearPersonne();
			}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message");
			alert.setContentText("Desolé! il a un probleme");
			
			alert.showAndWait();
			}
			
			
			}
		
		
///////////////////////////////////////////////////////////////////////////
//////////////////////////Modifier/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

		public void getPersonneByCIN(ActionEvent e)throws IOException,SQLException{
		String code_adh;
		code_adh=mchercheex.getText();
		
		Personne personne = AdherentBD.getPersonneByCode(code_adh);
		
		if(personne==null) {
		MenuAdmins.messageResultatVide();
		}
		else {
			mmodifierEXB.setDisable(false);
			msupprimerEXB.setDisable(false);
			mcode_ahdex.setText(personne.getCode_adh());
			mnomex.setText(personne.getNom());
			mprenomex.setText(personne.getPrenom());
			madresseex.setText(personne.getAdresse());
			mtelex.setText(personne.getTel());
			mmetierex.setText(personne.getMetiere());
			memprunterex.setText(Integer.toString(personne.getNbr_eprunter()));

		}
		
		}
		
		
		public void modifirPersonne(ActionEvent e)throws IOException,SQLException{
		Adherent adherent = new Personne();
		
		if(!mnomex.getText().isEmpty()) {
		adherent.setNom(mnomex.getText());
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		
		if(!mprenomex.getText().isEmpty()) {
		adherent.setPrenom(mprenomex.getText());
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		
		adherent.setAdresse(madresseex.getText());
		
		
		
		if(!mtelex.getText().isEmpty()) {
		adherent.setTel(mtelex.getText());
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		
		if(!mmetierex.getText().isEmpty()) {
		((Personne)adherent).setMetiere(mmetierex.getText());
		}else {
		MenuAdmins.messageWarning();
		return;
		}
		
		adherent.setTypeAdh(2);
		adherent.setCode_adh(mcode_ahdex.getText());
		
		int st=AdherentBD.updateAdherent(adherent);
		if(st!=0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Vous operarion est valid! Merci.");
		alert.showAndWait();
		clearPersonne();
		}else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Message");
		alert.setContentText("Desolé! il a un probleme");
		alert.showAndWait();
		}	
		
		
		}
		
		public void supprimerPersonne(ActionEvent e)throws IOException,SQLException{
		
		Adherent adherent = new Personne();
		adherent.setCode_adh(mcode_ahdex.getText());
		adherent.setTypeAdh(2);
		boolean st=AdherentBD.supprimerAdherent(adherent);
		
		if(st) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Vous operarion est valid! Merci.");
		clearPersonne();
		alert.showAndWait();
		}else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Message");
		alert.setContentText("Desolé! il a un probleme");
		alert.showAndWait();
		clearPersonne();
		}	
		
		}
		
		
		public void clearPersonne() {
		//desible
		msupprimerEXB.setDisable(true);
		mmodifierEXB.setDisable(true);
		
		//clear
		mchercheex.clear();
		mcode_ahdex.clear();
		mnomex.clear();
		mprenomex.clear();
		mtelex.clear();
		madresseex.clear();
		mmetierex.clear();
		memprunterex.clear();

		
		}

		
		///////////////////////////////////////////////////////////////////////////
		//////////////////////////Chercher/////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		
		
		
		public void BYPersonne(ActionEvent e)throws IOException,SQLException{
		switch(by) {
		case 1: CherchePersonneByCNE(e);break;
		case 2: CherchePersonneByNom(e);break;
		default: break;
		}
		}
		
		
		
		public void CherchePersonneByCNE(ActionEvent e)throws IOException,SQLException{
			tableupersonne.getItems().clear();
			try {
			String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,metier from adherent,personne where code_adh=cin and cin=?";
			Connection con = AdherentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherchex.getText());
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			dataPersonne.add(new Personne(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
			}
			con.close();
			}catch(SQLException event) {
			event.printStackTrace();
			}
			cinx.setCellValueFactory(new PropertyValueFactory<Personne,String>("code_adh"));
			nomx.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
			prenomx.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
			adressex.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
			telx.setCellValueFactory(new PropertyValueFactory<Personne,String>("tel"));
			metierx.setCellValueFactory(new PropertyValueFactory<Personne,String>("metier"));
			emprunterx.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("nbr_eprunter"));
			
			tableupersonne.setItems(dataPersonne);
		
		}
		
		public void CherchePersonneByNom(ActionEvent e)throws IOException,SQLException{
			tableupersonne.getItems().clear();
			try {
			String sql="SELECT adherent.code_adh,nom,prenom,adresse,tel,typeAdherent,nbr_emprunter,metier from adherent,personne where code_adh=cin and nom=?";
			Connection con = AdherentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherchex.getText());
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			dataPersonne.add(new Personne(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));		
			}
			con.close();
			}catch(SQLException event) {
			event.printStackTrace();
			}
			cinx.setCellValueFactory(new PropertyValueFactory<Personne,String>("code_adh"));
			nomx.setCellValueFactory(new PropertyValueFactory<Personne,String>("nom"));
			prenomx.setCellValueFactory(new PropertyValueFactory<Personne,String>("prenom"));
			adressex.setCellValueFactory(new PropertyValueFactory<Personne,String>("adresse"));
			telx.setCellValueFactory(new PropertyValueFactory<Personne,String>("tel"));
			metierx.setCellValueFactory(new PropertyValueFactory<Personne,String>("metier"));
			emprunterx.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("nbr_eprunter"));
			
			tableupersonne.setItems(dataPersonne);
		}
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Modifier les informations de Profile///////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	

	
	
		private void  detDictionnaire(ActionEvent e) throws IOException{
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("DetDict.fxml" ));
				Scene scene = new Scene(root,865,347);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();	
			} catch(Exception event) {
				event.printStackTrace();
			}
		}
		
		
	    public void afficheDetDictionnaire(ActionEvent e)throws IOException,SQLException {
			DocumentBD.getConnection();
			detDictionnaire(e);
	    }
	    
	    private void  detLivre(ActionEvent e) throws IOException{
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("Detl.fxml" ));
				Scene scene = new Scene(root,865,347);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();	
			} catch(Exception event) {
				event.printStackTrace();
			}
		}
		
		
	    public void afficheDetLivre(ActionEvent e)throws IOException,SQLException {
			DocumentBD.getConnection();
			detLivre(e);
	    }
	    

		
		
		private void  detMagazine(ActionEvent e) throws IOException{
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("DetMag.fxml" ));
				Scene scene = new Scene(root,865,347);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();	
			} catch(Exception event) {
				event.printStackTrace();
			}
		}
		
		
	    public void afficheMagazine(ActionEvent e)throws IOException,SQLException {
			DocumentBD.getConnection();
			detMagazine(e);
	    }
	    
	
	
	
	
	
	
	
	
	
	
	
	
	public void deconnecter(ActionEvent e)throws IOException,SQLException{
		CompteBD.getConnection();
		InscriptionEtAuthentification.deconnection();
		loadAuthen(e);
			}
	
	public void loadAuthen(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("InscriptionEtAuthentification.fxml" ));
		Scene s = new Scene(root);
		Stage fenetre = (Stage) ((Node)e.getSource()).getScene().getWindow();
		fenetre.setScene(s);
		fenetre.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        fenetre.setX((primScreenBounds.getWidth() - fenetre.getWidth()) / 2); 
        fenetre.setY((primScreenBounds.getHeight() - fenetre.getHeight()) / 4); 
        fenetre.show();		
	}


}

