package org.example.GestorVideojuegosHibernateJavaFX.user;

import jakarta.persistence.*;
import lombok.Data;
import org.example.GestorVideojuegosHibernateJavaFX.game.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;

    @Column(name="is_admin")
    private Boolean isAdmin;

    @OneToMany(cascade={CascadeType.ALL}, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Game> games = new ArrayList<>();

    public void addGame(Game g){
        g.setUser(this);
        this.games.add(g);
    }
}
