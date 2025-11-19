package org.example.GestorVideojuegosHibernateJavaFX.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer user_id;
    private String image_url;
}
