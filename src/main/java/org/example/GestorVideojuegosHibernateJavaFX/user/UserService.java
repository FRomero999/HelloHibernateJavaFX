package org.example.GestorVideojuegosHibernateJavaFX.user;

import org.example.GestorVideojuegosHibernateJavaFX.game.Game;
import org.example.GestorVideojuegosHibernateJavaFX.utils.DataProvider;
import org.hibernate.Session;

public class UserService {

    public User deleteGameFromUser(User user, Game game) {
        try(Session s = DataProvider.getSessionFactory().openSession()) {
            s.beginTransaction();

            // Recargar datos desde la BD
            User currentUser = s.find(User.class, user.getId());
            Game gameToDelete = s.find(Game.class, game.getId());

            // Buscar y eliminar el juego de la colección y la bbdd
            currentUser.getGames().remove(gameToDelete);
            currentUser.getGames().removeIf(g -> g.getId().equals(game.getId()));
            s.remove(gameToDelete);

            s.getTransaction().commit();

            return currentUser;
        }
    }

    public User createNewGame(Game newGame, User actualUser) {
        try(Session s = DataProvider.getSessionFactory().openSession()) {
            actualUser.addGame(newGame);
            s.beginTransaction();
            s.merge(actualUser);
            s.getTransaction().commit();
            return s.find(User.class, actualUser.getId());
        }

    }
}
