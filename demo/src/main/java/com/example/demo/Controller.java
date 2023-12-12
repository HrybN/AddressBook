package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private TableView<Person> tableAddressBook;

    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPHONE;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label labelCount;


    @FXML
    public void initialize() throws IOException {
        columnPIP.setCellValueFactory(cellData -> cellData.getValue().pipProperty());
        columnPHONE.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressBookImpl.getPersonList().addListener((ListChangeListener<Person>) c -> updateCountLabel());
        tableAddressBook.setItems(addressBookImpl.getPersonList());
        updateCountLabel();
    }

    private void updateCountLabel(){
        labelCount.setText("Кількість записів: "+ addressBookImpl.getPersonList().size());
    }

    @FXML
    public void openEditDialogForEdit(ActionEvent event) {
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/modality.fxml"));
                Parent root = loader.load();
                EditController editController = loader.getController();
                editController.setPerson(selectedPerson);
                Stage stage = new Stage();
                stage.setTitle("Редагувати контакт");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.showAndWait();
                tableAddressBook.setItems(addressBookImpl.getPersonList());
                updateCountLabel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please select a contact to edit.");
        }
    }

    @FXML
    public void openEditDialog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/modality.fxml"));
            Parent root = loader.load();

            EditController editController = loader.getController();
            editController.setPerson(new Person("", ""));
            Stage stage = new Stage();
            stage.setTitle("Додати контакт");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            Person newPerson = editController.getPerson();


            if (newPerson != null) {
                addressBookImpl.add(newPerson);
                tableAddressBook.setItems(addressBookImpl.getPersonList());
                updateCountLabel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteSelectedContact() {
        Person personToDelete = tableAddressBook.getSelectionModel().getSelectedItem();

        if (personToDelete != null) {
            addressBookImpl.delete(personToDelete);
            tableAddressBook.setItems(addressBookImpl.getPersonList());
            updateCountLabel();
        } else {
            System.out.println("Please select a contact to delete.");
        }
    }



    @FXML
    void handleAddButton(ActionEvent event) {
        openEditDialog(event);
    }

    @FXML
    private void search() {
        String query = txtSearch.getText().toLowerCase();
        ObservableList<Person> searchResults = FXCollections.observableArrayList();

        for (Person person : addressBookImpl.getPersonList()) {
            if (person.getPIP().toLowerCase().contains(query) || person.getPHONE().toLowerCase().contains(query)) {
                searchResults.add(person);
            }
        }

        tableAddressBook.setItems(searchResults);

    }
}
