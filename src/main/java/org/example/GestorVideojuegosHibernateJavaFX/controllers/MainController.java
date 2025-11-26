package org.example.GestorVideojuegosHibernateJavaFX.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.GestorVideojuegosHibernateJavaFX.game.Game;
import org.example.GestorVideojuegosHibernateJavaFX.game.GameRepository;
import org.example.GestorVideojuegosHibernateJavaFX.session.SimpleSessionService;
import org.example.GestorVideojuegosHibernateJavaFX.user.User;
import org.example.GestorVideojuegosHibernateJavaFX.user.UserService;
import org.example.GestorVideojuegosHibernateJavaFX.utils.DataProvider;
import org.example.GestorVideojuegosHibernateJavaFX.utils.JavaFXUtil;

import java.net.URL;
import java.time.LocalDateTime;
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
    private TableView<Game> tabla;
    @FXML
    private TableColumn<Game,String> cId;
    @FXML
    private Label lblUsuario;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnAñadir;

    SimpleSessionService simpleSessionService = new SimpleSessionService();
    GameRepository gameRepository = new GameRepository(DataProvider.getSessionFactory());
    UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblUsuario.setText("Juegos del usuario " + simpleSessionService.getActive().getEmail() );

        cId.setCellValueFactory( (row)->{
            return new SimpleStringProperty(String.valueOf(row.getValue().getId()));
        });
        cTitulo.setCellValueFactory( (row)->{
            String title = row.getValue().getTitle();
            /* ..... */
            return new SimpleStringProperty(title);
        });
        cAño.setCellValueFactory( (row)->{
            if(row.getValue().getYear()==null){
                return new SimpleStringProperty("-");
            }
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

        simpleSessionService.getActive().getGames().forEach(game -> {
            tabla.getItems().add(game);
        });

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

    @FXML
    public void borrar(ActionEvent actionEvent) {

        if(tabla.getSelectionModel().getSelectedItem()==null) return;
        Game selectedGame = tabla.getSelectionModel().getSelectedItem();

        // Actualizar el usuario local
        User user = userService.deleteGameFromUser(simpleSessionService.getActive(), selectedGame);
        simpleSessionService.update(user);

        tabla.getItems().clear();
        simpleSessionService.getActive().getGames().forEach(game -> {
            tabla.getItems().add(game);
        });
    }

    @FXML
    public void añadir(ActionEvent actionEvent) {
        Game newGame = new Game();
        newGame.setTitle("Juego random");
        newGame.setPlatform("random");

        User actualUser = simpleSessionService.getActive();
        User user = userService.createNewGame(newGame, actualUser);
        simpleSessionService.update(user);

        tabla.getItems().clear();
        simpleSessionService.getActive().getGames().forEach(game -> {
            tabla.getItems().add(game);
        });
    }
}
