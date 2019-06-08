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
import Documents.Magazine;
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




public class DetMag implements Initializable{

	

	  @FXML
	    private TableView<Magazine> tableumagazineDet;

	    @FXML
	    private TableColumn<Document,String> isbndetm;

	    @FXML
	    private TableColumn<Document,String> titredetm;

	    @FXML
	    private TableColumn<Document,String> editeurdetm;

	    @FXML
	    private TableColumn<Document,Integer> annedetm;

	    @FXML
	    private TableColumn<Magazine,Integer> periodedetm;

	    @FXML
	    private TableColumn<Magazine,String> editiondetm;

	    @FXML
	    private TableColumn<Document,Integer> nbrdispodetm;

	    @FXML
	    private TableColumn<Document,String> auteur1detm;

	    @FXML
	    private TableColumn<Document,String> auteur2detm;

	    @FXML
	    private TableColumn<Document,String> auteur3detm;

	    @FXML
	    private TableColumn<Document,String> auteur4detm;
   
    
    
	 public ObservableList<Magazine> data = FXCollections.observableArrayList();

    
    
	public void initialize(URL location, ResourceBundle resources){
	
    	tableMagazine();
    	
	}
	public void tableMagazine() {
		try {
			String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl,periode,date_edit,auteur1,auteur2,auteur3,auteur4  from Document,magazin,liste_auteur where document.isbn=magazin.isbn and document.isbn=liste_auteur.isbn and code_adh IS NULL group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
				data.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
			
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		isbndetm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titredetm.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurdetm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annedetm.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		periodedetm.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("Periode"));
		editiondetm.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
		nbrdispodetm.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		auteur1detm.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur1"));
		auteur2detm.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur2"));
		auteur3detm.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur3"));
		auteur4detm.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur4"));
		
		tableumagazineDet.setItems(data);
	}
	
	
}
