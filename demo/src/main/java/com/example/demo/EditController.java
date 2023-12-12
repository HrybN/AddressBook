package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {

    @FXML
    private TextField txtPIP;
    @FXML
    private TextField txtPHONE;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;


    private Person person;

    public Person getPerson() {
        return person;
    }


    public void setPerson(Person person){
        this.person = person;
        txtPIP.setText(person.getPIP());
        txtPHONE.setText(person.getPHONE());
    }
    @FXML
    private void handleOkButtonAction() {
        if (!txtPIP.getText().isEmpty() && !txtPHONE.getText().isEmpty()) {
            if (person == null) {
                person = new Person(txtPIP.getText(), txtPHONE.getText());
            } else {
                person.setPIP(txtPIP.getText());
                person.setPHONE(txtPHONE.getText());
            }
            closeDialog();
        } else {
            System.out.println("Please enter both name and phone.");
        }
    }

    @FXML
    private void handleCancelButtonAction() {
        closeDialog();
    }

    private void closeDialog() {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }
}
