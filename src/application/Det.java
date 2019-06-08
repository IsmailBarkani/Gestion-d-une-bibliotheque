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




public class Det implements Initializable{

	

    @FXML
    private TableView<Livre> tableulivreDet;

    @FXML
    private TableColumn<Document, String> isbndet;

    @FXML
    private TableColumn<Document, String> titredet;

    @FXML
    private TableColumn<Document, String> editeurdet;

    @FXML
    private TableColumn<Document, Integer> annedet;

    @FXML
    private TableColumn<Livre, Integer> nbrpagedet;

    @FXML
    private TableColumn<Livre, String> typedet;

    @FXML
    private TableColumn<Livre, String> tomedet;

    @FXML
    private TableColumn<Document, Integer> nbrdispodet;

    @FXML
    private TableColumn<Document, String> auteur1detl;

    @FXML
    private TableColumn<Document, String> auteur2detl;

    @FXML
    private TableColumn<Document, String> auteur3detl;

    @FXML
    private TableColumn<Document, String> auteur4detl;
   
    
    
	 public ObservableList<Livre> data = FXCollections.observableArrayList();

    
    
	public void initialize(URL location, ResourceBundle resources){
	
    	tableLivre();
    	
	}
	public void tableLivre() {
		try {
			String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl,auteur1,auteur2,auteur3,auteur4  from Document,Livre,liste_auteur where document.isbn=livre.isbn and document.isbn=liste_auteur.isbn and code_adh IS NULL group by document.isbn";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
				data.add(new Livre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
			
			
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		isbndet.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titredet.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurdet.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annedet.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		nbrpagedet.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("Nbrpage"));
		typedet.setCellValueFactory(new PropertyValueFactory<Livre,String>("Type"));
		tomedet.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
		nbrdispodet.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		auteur1detl.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur1"));
		auteur2detl.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur2"));
		auteur3detl.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur3"));
		auteur4detl.setCellValueFactory(new PropertyValueFactory<Document,String>("auteur4"));
		
		tableulivreDet.setItems(data);
	}
	
	
}
