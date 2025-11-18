module org.example.hellohibernatejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens org.example.hellohibernatejavafx;
    exports org.example.hellohibernatejavafx;
    exports org.example.hellohibernatejavafx.game;
    opens org.example.hellohibernatejavafx.game;
    exports org.example.hellohibernatejavafx.utils;
    opens org.example.hellohibernatejavafx.utils;
}