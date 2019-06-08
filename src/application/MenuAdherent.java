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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Adherents.Adherent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuAdherent  implements Initializable{
	
	int by;
	private Livre livreEmpr;
	private Magazine magazineEmpr;
	private Dictionnaire dictionnaireEmpr;
	private  Compte cmpt;
	private Adherent adherent;
	private Historique historiqueSelection;

	
	 @FXML
	 private Pane emprunter;
	 @FXML
	private Pane documents;
	 @FXML
	 private Pane parametres;
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
	    private JFXButton emprunterB;
	    @FXML
	    private JFXButton retourB;
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
		@FXML
	    private JFXButton emprunterBD;
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
	    private Label echecemprunterm;
	    @FXML
	    private Label succeeprunterm;
		@FXML
		private Label totalemagazine;
		@FXML
	    private TextField cherchem;
		@FXML
	    private JFXButton emprunterBM;
	    //Fenetre Historique////
	    @FXML
	    private Pane historique;

	    @FXML
	    private Label totalelivre;

	    @FXML
	    private Label totalemagazin;

	    @FXML
	    private Label totalDictionnaire;

	    @FXML
	    private TableView<Historique> tableuHistorique;

	    @FXML
	    private TableColumn<Historique,Integer> enrgH;

	    @FXML
	    private TableColumn<Historique,String> isbnH;

	    @FXML
	    private TableColumn<Historique,String> titreH;

	    @FXML
	    private TableColumn<Historique,String> typeH;

	    @FXML
	    private TableColumn<Historique,String> dateEH;

	    @FXML
	    private TableColumn<Historique,String> dateRH;
	    @FXML
	    private TableColumn<Historique,Integer> num_enrgH;

	    @FXML
	    private TextField cherchehistorique;

	    
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
	    
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Menu Principale////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	    
	    static Connection con = null;
	    @Override
		public void initialize(URL location, ResourceBundle resources){
			cmpt=CompteBD.getCompteByEtat(1);
		
	    	tableLivre();
	    	tableMagazine();
	    	tableDictionnaire();
	    	title.setText(cmpt.getPseudo_nom());
	    	
		}
		
	    
	    
	    
	    

	    public void document(ActionEvent e)throws IOException,SQLException {
//	    	succeeprunter.setVisible(false);
//			echecemprunter.setVisible(false);
			tableLivre();
			tableMagazine();
			tableDictionnaire();
	    	emprunterB.setDisable(true);
	    	emprunterBM.setDisable(true);
			documents.toFront();
			//emprunter.toBack();
			parametres.toBack();
		}
		
		
		public void emprunter(ActionEvent e)throws IOException,SQLException {
			//emprunter.toFront();
			documents.toBack();
			parametres.toBack();
			//rendre.toBack();
		
		}
		
		public void parametres(ActionEvent e)throws IOException,SQLException{
			parametres.toFront();
			documents.toBack();
			//rendre.toBack();
			//emprunter.toBack();
			pseudomodif.setText(cmpt.getPseudo_nom());

				}
		

		
		public void historique(ActionEvent e)throws IOException,SQLException{
			historique.toFront();
			parametres.toBack();
			documents.toBack();
			tableHistorique();
			//rendre.toBack();
			//emprunter.toBack();
				}

		 public ObservableList<Livre> data = FXCollections.observableArrayList();
		 public ObservableList<Magazine> dataMagazine = FXCollections.observableArrayList();
		 public ObservableList<Dictionnaire> dataDictionnaire = FXCollections.observableArrayList();
		 public ObservableList<Historique> dataHistorique = FXCollections.observableArrayList();
	    
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Livre////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
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

//////////////////////////Chercher/////////////////////////////////////

	   


	    public void ByISBN(ActionEvent e)throws IOException,SQLException{
	    	by=1;
			emprunterB.setDisable(true);

	    }
	    public void ByTitre(ActionEvent e)throws IOException,SQLException{
	    	by=2;
			emprunterB.setDisable(true);

	    }
	    public void ByEdition(ActionEvent e)throws IOException,SQLException{
	    	by=3;
			emprunterB.setDisable(true);

	    }
	    
	    public void BY(ActionEvent e)throws IOException,SQLException{
	    	emprunterB.setDisable(true);
	    	switch(by) {
	    	case 1: ChercheByIsbn(e);break;
	    	case 2: ChercheByEdition(e);break;
	    	case 3:break;
	    	default: break;
	    	}
	    }
	    
	    
	    
	    public void ChercheByIsbn(ActionEvent e)throws IOException,SQLException{
	    	tableulivre.getItems().clear();
	    	emprunterB.setDisable(true);
			String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre where document.isbn=livre.isbn and code_adh is null and document.isbn=? group by document.isbn limit 1";
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
	    	    
	    public void ChercheByEdition(ActionEvent e)throws IOException,SQLException{
	    	tableulivre.getItems().clear();
	    	emprunterB.setDisable(true);			String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre where document.isbn=livre.isbn and code_adh is null and titre=? group by document.isbn limit 1";
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
	
	    public void display(MouseEvent e)throws IOException,SQLException {
			int type;
			String code = cmpt.getCode_adh();
			Livre livreSelection= tableulivre.getSelectionModel().getSelectedItem();
			if(livreSelection==null) {
				emprunterB.setDisable(true);
			}
			else {
				livreEmpr = DocumentBD.getLivreByISBN(livreSelection.getISBN());
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				type=AdherentBD.getTypeByCode(code);
				switch(type) {
				case 0: if(AdherentBD.getEtudiantByCode(code).getNbr_eprunter()<3) {
							emprunterB.setDisable(false);
						}
						else {
							alert.setContentText("Vous avez deja depasser le nombre maximal");
							alert.showAndWait();
						}break;
						
				case 1: if(AdherentBD.getProfesseurByCode(code).getNbr_eprunter()<5) {
							emprunterB.setDisable(false);
						}
						else {
							alert.setContentText("Vous avez deja depasser le nombre maximal");
							alert.showAndWait();
						}break;
						
				default: if(AdherentBD.getPersonneByCode(code).getNbr_eprunter()<1) {
							emprunterB.setDisable(false);
						}
						else {
							alert.setContentText("Vous avez deja depasser le nombre maximal");
							alert.showAndWait();
						}break;
				}}
			}
	    
	    
		public void Emprunter(ActionEvent e)throws IOException,SQLException{
			int test1 =DocumentBD.LivreEmprunter(livreEmpr,cmpt.getCode_adh());
			int test2 = DocumentBD.ajouterHistorique(livreEmpr,cmpt.getCode_adh());
				if(test2*test1!=0) {
					succeeprunter.setText("Vous avez emprunter le document.");
					echecemprunter.setVisible(false);
				}
				else {
					echecemprunter.setText("Désole! il ya un erreur.");
					succeeprunter.setVisible(false);
				}
		    	tableLivre();
		    	emprunterB.setDisable(true);

				
			}
		
	
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Magazin////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
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

//////////////////////////Chercher/////////////////////////////////////


public void magazineBY(ActionEvent e)throws IOException,SQLException{
	emprunterBM.setDisable(true);
	switch(by) {
	case 1: ChercheMagazineByIsbn(e);break;
	case 2: ChercheMagazineByTitre(e);break;
	case 3:break;
	default: break;
	}
}



public void ChercheMagazineByIsbn(ActionEvent e)throws IOException,SQLException{
	tableumagazine.getItems().clear();
	emprunterBM.setDisable(true);
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

public void ChercheMagazineByTitre(ActionEvent e)throws IOException,SQLException{
	tableumagazine.getItems().clear();
	emprunterBM.setDisable(true);			
	String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,periode,date_edit  from Document,magazin where document.isbn=magazin.isbn and code_adh IS NULL and titre=? group by document.isbn";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1,cherche.getText());
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

	public void tableMagazineSelection(MouseEvent e)throws IOException,SQLException {
		int type;
		String code = cmpt.getCode_adh();
		Magazine magazineSelection= tableumagazine.getSelectionModel().getSelectedItem();
		if(magazineSelection==null) {
			emprunterBM.setDisable(true);
		}
		else {
			magazineEmpr = DocumentBD.getMagazineByISBN(magazineSelection.getISBN());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			type=AdherentBD.getTypeByCode(code);
			switch(type) {
			case 0: if(AdherentBD.getEtudiantByCode(code).getNbr_eprunter()<3) {
					emprunterBM.setDisable(false);
					}
					else {
						alert.setContentText("Vous avez deja depasser le nombre maximal");
						alert.showAndWait();
					}break;
		
		case 1: if(AdherentBD.getProfesseurByCode(code).getNbr_eprunter()<5) {
					emprunterBM.setDisable(false);
				}
				else {
					alert.setContentText("Vous avez deja depasser le nombre maximal");
					alert.showAndWait();
				}break;
		
		default: if(AdherentBD.getPersonneByCode(code).getNbr_eprunter()<1) {
					emprunterBM.setDisable(false);
				}
				else {
					alert.setContentText("Vous avez deja depasser le nombre maximal");
					alert.showAndWait();
				}break;
		}}
}


	public void EmprunterMagazine(ActionEvent e)throws IOException,SQLException{
		succeeprunterm.setVisible(false);
		echecemprunterm.setVisible(false);
		int test1 =DocumentBD.magazineEmprunter(magazineEmpr,cmpt.getCode_adh());
		int test2 = DocumentBD.ajouterHistorique(magazineEmpr,cmpt.getCode_adh());
		if(test2*test1!=0) {
			succeeprunterm.setText("Vous avez emprunter le document.");
			echecemprunterm.setVisible(false);
		}
		else {
			echecemprunterm.setText("Désole! il ya un erreur.");
			succeeprunterm.setVisible(false);
		}
		tableMagazine();
		emprunterBM.setDisable(true);
	
	}

	
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Dictionnaire////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
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

//////////////////////////Chercher/////////////////////////////////////


public void dictionnaireBY(ActionEvent e)throws IOException,SQLException{
emprunterBD.setDisable(true);
switch(by) {
case 1: ChercheDictionnaireByIsbn(e);break;
case 2: ChercheDictionnaireByTitre(e);break;
case 3:break;
default: break;
}
}



public void ChercheDictionnaireByIsbn(ActionEvent e)throws IOException,SQLException{
tableudictionnaire.getItems().clear();
emprunterBD.setDisable(true);
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
	emprunterBD.setDisable(true);
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

public void tableDictionnaireSelection(MouseEvent e)throws IOException,SQLException {
int type;
String code = cmpt.getCode_adh();
Dictionnaire dictionnaireSelection= tableudictionnaire.getSelectionModel().getSelectedItem();
if(dictionnaireSelection==null) {
emprunterBD.setDisable(true);
}
else {
dictionnaireEmpr = DocumentBD.getDictionnaireByISBN(dictionnaireSelection.getISBN());
Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Erreur");
type=AdherentBD.getTypeByCode(code);
switch(type) {
case 0: if(AdherentBD.getEtudiantByCode(code).getNbr_eprunter()<3) {
emprunterBD.setDisable(false);
}
else {
alert.setContentText("Vous avez deja depasser le nombre maximal");
alert.showAndWait();
}break;

case 1: if(AdherentBD.getProfesseurByCode(code).getNbr_eprunter()<5) {
emprunterBD.setDisable(false);
}
else {
alert.setContentText("Vous avez deja depasser le nombre maximal");
alert.showAndWait();
}break;

default: if(AdherentBD.getPersonneByCode(code).getNbr_eprunter()<1) {
emprunterBD.setDisable(false);
}
else {
alert.setContentText("Vous avez deja depasser le nombre maximal");
alert.showAndWait();
}break;
}}
}


public void EmprunterDictionnaire(ActionEvent e)throws IOException,SQLException{
	
	int test1 =DocumentBD.dictionnaireEmprunter(dictionnaireEmpr,cmpt.getCode_adh());
	int test2 = DocumentBD.ajouterHistorique(dictionnaireEmpr,cmpt.getCode_adh());
	if(test2*test1!=0) {
	succeeprunterd.setText("Vous avez emprunter le document.");
	echecemprunterd.setVisible(false);
	}
	else {
	echecemprunterd.setText("Désole! il ya un erreur.");
	succeeprunterd.setVisible(false);
	}
	tableDictionnaire();
	emprunterBD.setDisable(true);

}

//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Historique///////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	
	
	
	
	 public void  tableHistorique(){
	    	tableuHistorique.getItems().clear();
	    	retourB.setDisable(true);
			try {
				String sql="SELECT num_enrg,isbn,titre,type,date_debut,date_fin from historique where code_adh=?";
				Connection con = DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				preparedStatement.setString(1,cmpt.getCode_adh());
				ResultSet rs  = preparedStatement.executeQuery();
				while(rs.next()) {
					
					switch(rs.getInt(4)) {
					case 0:dataHistorique.add(new Historique(rs.getInt(1),rs.getString(2),rs.getString(3),"Livre",rs.getString(5),rs.getString(6)));break;
					case 1:dataHistorique.add(new Historique(rs.getInt(1),rs.getString(2),rs.getString(3),"Magazine",rs.getString(5),rs.getString(6)));break;
					default:dataHistorique.add(new Historique(rs.getInt(1),rs.getString(2),rs.getString(3),"Dictionnaire",rs.getString(5),rs.getString(6)));
					}
					
					
				}
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			isbnH.setCellValueFactory(new PropertyValueFactory<Historique,String>("ISBN"));
			titreH.setCellValueFactory(new PropertyValueFactory<Historique,String>("Titre"));
			typeH.setCellValueFactory(new PropertyValueFactory<Historique,String>("Type"));
			dateEH.setCellValueFactory(new PropertyValueFactory<Historique,String>("DateE"));
			dateRH.setCellValueFactory(new PropertyValueFactory<Historique,String>("DateR"));
			num_enrgH.setCellValueFactory(new PropertyValueFactory<Historique,Integer>("num_enrg"));
			totalelivre.setText(DocumentBD.statistiqueLivre(cmpt.getCode_adh()));
			totalemagazine.setText(DocumentBD.statistiqueMagazine(cmpt.getCode_adh()));
			totaledictionnaire.setText(DocumentBD.statistiqueDictionnaire(cmpt.getCode_adh()));
			tableuHistorique.setItems(dataHistorique);
	    }
	
	
		public void selectionHistorique(MouseEvent e)throws IOException,SQLException {
			 historiqueSelection= tableuHistorique.getSelectionModel().getSelectedItem();
			if(historiqueSelection==null) {
				retourB.setDisable(true);
			}
			else {
				if(historiqueSelection.getDateR().equals("non retourner")) {
				retourB.setDisable(false);
				}
				else {
					retourB.setDisable(true);
				}
			}
		
	}
		public void retour(ActionEvent e)throws IOException,SQLException{
			DocumentBD.retour(historiqueSelection.getNum_enrg(),cmpt.getCode_adh());
			tableHistorique();
		}
		
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Modifier les informations de Profile///////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	
	
	//Mot de passe
public void motpassModif(ActionEvent e )throws IOException,ParseException{
		Compte cmp = new Compte();
		if(oldmotpass.getText().equals(cmpt.getMot_pass())) {
				if(newmotpass.getText().equals(confirmer.getText())) {
					
					cmp.setMot_pass(confirmer.getText());
					int statue = CompteBD.motpassModif(cmp,1);
					if(statue>0) {
						motpassmodifsucce.setText("Vous avez modifier votre mot de passe");
						motpassmodifechec.setVisible(false);
					}else {
						motpassmodifechec.setText("Erreur technique");
						motpassmodifsucce.setVisible(false);
					}
				}
			else {
				motpassmodifechec.setText("Mot de pass different.");
				motpassmodifsucce.setVisible(false);
			}
			}
		else {
			motpassmodifechec.setText("Mot de passe incorrect");
			motpassmodifsucce.setVisible(false);
		}
		
	}
	

///////////////Pseudo Nom///////////////////////////////////////////////

	public void personneModif(ActionEvent e )throws IOException,ParseException{

		Compte cmp = new Compte();
		cmp.setPseudo_nom(pseudomodif.getText());
		cmp.setTelephone(telephonemodif.getText());
		cmp.setAdresse(adressemodif.getText());
		int statue = CompteBD.personneModif(cmp,cmpt.getCode_adh());
		if(statue>0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Vous avez modifier les informations! ");
			
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message");
			alert.setContentText("Erreur, essayez encore");
			
			alert.showAndWait();
		}
		
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





