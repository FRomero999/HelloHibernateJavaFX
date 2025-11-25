package org.example.GestorVideojuegosHibernateJavaFX.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.GestorVideojuegosHibernateJavaFX.user.User;

import java.io.Serializable;

@Entity
@Table(name="games")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String platform;
    private Integer year;
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String image_url;

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", user=" + user.getEmail() +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
