module org.example.hellohibernatejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hellohibernatejavafx to javafx.fxml;
    exports org.example.hellohibernatejavafx;
}