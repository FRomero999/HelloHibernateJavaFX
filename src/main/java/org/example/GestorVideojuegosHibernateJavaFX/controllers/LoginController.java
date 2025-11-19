package org.example.GestorVideojuegosHibernateJavaFX.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.example.GestorVideojuegosHibernateJavaFX.utils.JavaFXUtil;

public class LoginController {
    @javafx.fxml.FXML
    private TextField txtContraseña;
    @javafx.fxml.FXML
    private TextField txtCorreo;
    @javafx.fxml.FXML
    private Label info;

    @javafx.fxml.FXML
    public void entrar(ActionEvent actionEvent) {
        JavaFXUtil.setScene("/org/example/GestorVideojuegosHibernateJavaFX/main-view.fxml");
    }

    @javafx.fxml.FXML
    public void Salir(ActionEvent actionEvent) {
        System.exit(0);
    }
}
