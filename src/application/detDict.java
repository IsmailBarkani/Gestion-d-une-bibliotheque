package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Documents.Document;
import Documents.Livre;
import Documents.Dictionnaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;




public class detDict implements Initializable{

	

	  @FXML
	    private TableView<Dictionnaire> tableudictionnaireDet;

	    @FXML
	    private TableColumn<Document, String> isbndetd;

	    @FXML
	    private TableColumn<Document, String> titredetd;

	    @FXML
	    private TableColumn<Document, String> editeurdetd;

	    @FXML
	    private TableColumn<Document, Integer> annedet;

	    @FXML
	    private TableColumn<Dictionnaire, String> languedetd;

	    @FXML
	    private TableColumn<Dictionnaire, String> tomedetd;

	    @FXML
	    private TableColumn<Document, Integer> nbrdispodetd;

	    @FXML
	    private TableColumn<Document, String> auteur1detd;

	    @FXML
	    private TableColumn<Document, String> auteur2detd;

	    @FXML
	    private TableColumn<Document, String> auteur3detd;

	    @FXML
	    private TableColumn<Document, String> auteur4detd;
   
    
    
	 public ObservableList<Dictionnaire> data = FXCollections.observableArrayList();

    
    
	public void initialize(URL location, ResourceBundle resources){
	
    	tableDictionnaire();
    	
	}
	public void tableDictionnaire() {
		try {
			String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl,langue_dic,tome,auteur1,auteur2,auteur3,auteur4  from Document,dictionnaire,liste_auteur where document.isbn=dictionnaire.isbn and document.isbn=liste_auteur.isbn and code_adh IS NULL group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
				data.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
			
			
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		isbndetd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titredetd.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurdetd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annedet.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		languedetd.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
		tomedetd.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
		nbrdispodetd.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		auteur1detd.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur1"));
		auteur2detd.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur2"));
		auteur3detd.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur3"));
		auteur4detd.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur4"));
		
		tableudictionnaireDet.setItems(data);
	}
	
	
}
