package org.example.GestorVideojuegosHibernateJavaFX.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.example.GestorVideojuegosHibernateJavaFX.game.Game;
import org.example.GestorVideojuegosHibernateJavaFX.game.GameRepository;
import org.example.GestorVideojuegosHibernateJavaFX.services.SessionService;
import org.example.GestorVideojuegosHibernateJavaFX.utils.DataProvider;
import org.example.GestorVideojuegosHibernateJavaFX.utils.JavaFXUtil;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TableColumn<Game,String> cDescripcion;
    @FXML
    private TableColumn<Game,String> cPlataforma;
    @FXML
    private TableColumn<Game,String> cTitulo;
    @FXML
    private TableColumn<Game,String> cAño;
    @FXML
    private TableColumn<Game,String> cUser;
    @FXML
    private TableView<Game> tabla;
    @FXML
    private TableColumn<Game,String> cId;

    SessionService sessionService = new SessionService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println( sessionService.getActiveUser() );

        cId.setCellValueFactory( (row)->{
            return new SimpleStringProperty(row.getValue().getId().toString());
        });
        cTitulo.setCellValueFactory( (row)->{
            String title = row.getValue().getTitle();
            /* ..... */
            return new SimpleStringProperty(title);
        });
        cUser.setCellValueFactory(userColumnValue());
        cAño.setCellValueFactory( (row)->{
            Integer year = row.getValue().getYear();
            Integer antigüedad = LocalDateTime.now().getYear() - year;
            return new SimpleStringProperty(antigüedad.toString()+" años");
        });
        cPlataforma.setCellValueFactory( (row)->{
            return new SimpleStringProperty(row.getValue().getPlatform());
        });
        cDescripcion.setCellValueFactory( (row)->{
            return new SimpleStringProperty(row.getValue().getDescription());
        });

        tabla.getSelectionModel().selectedItemProperty().addListener(showGame());

        GameRepository gameRepository = new GameRepository(DataProvider.getSessionFactory());

        List<Game> games = gameRepository.findAll();
        games.forEach((game)->{
            if(game.getUser_id()== sessionService.getActiveUser().getId()) {
                tabla.getItems().add(game);
            }
        });

    }

    private static Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>> userColumnValue() {
        return (row) -> {
            Integer user = row.getValue().getUser_id();
            if (user == null) {
                return new SimpleStringProperty("-");
            } else {
                return new SimpleStringProperty(user.toString());
            }
        };
    }

    private ChangeListener<Game> showGame() {
        return (obs, old, news) -> {
            if (news != null) {
                JavaFXUtil.showModal(
                        Alert.AlertType.INFORMATION,
                        news.getTitle(),
                        news.getTitle(),
                        news.toString()
                );
            }
        };
    }
}
