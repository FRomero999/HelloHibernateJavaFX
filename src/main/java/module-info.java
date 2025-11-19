module org.example.GestorVideojuegosHibernateJavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens org.example.GestorVideojuegosHibernateJavaFX.user;
    opens org.example.GestorVideojuegosHibernateJavaFX;
    exports org.example.GestorVideojuegosHibernateJavaFX;
    exports org.example.GestorVideojuegosHibernateJavaFX.game;
    opens org.example.GestorVideojuegosHibernateJavaFX.game;
    exports org.example.GestorVideojuegosHibernateJavaFX.utils;
    opens org.example.GestorVideojuegosHibernateJavaFX.utils;
    exports org.example.GestorVideojuegosHibernateJavaFX.controllers;
    opens org.example.GestorVideojuegosHibernateJavaFX.controllers;
}