package org.example.GestorVideojuegosHibernateJavaFX.services;

import org.example.GestorVideojuegosHibernateJavaFX.user.User;

public class SessionService {

    private static User activeUser = null;

    public void login(User user) {
        activeUser = user;
    }

    public boolean isLoggedIn(){
        return activeUser != null;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void logout() {
        activeUser = null;
    }

}
