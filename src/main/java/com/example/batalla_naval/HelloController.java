package com.example.batalla_naval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private AnchorPane pane;
    ArrayList<Barco> barcos=new ArrayList<>();
    int rotacion=45;

    public void initialize(){
        Image imagen1=new Image(getClass().getResourceAsStream("Destructor_españa.png"));
        Image imagen2=new Image(getClass().getResourceAsStream("acorazado_españa.png"));
        Image imagen3=new Image(getClass().getResourceAsStream("lanchaEspaña.png"));
        Image imagen4=new Image(getClass().getResourceAsStream("submarinoEspaña.png"));
        Image imagen5=new Image(getClass().getResourceAsStream("Destructor_rumania.png"));
        Image imagen6=new Image(getClass().getResourceAsStream("acorazado_rumania.png"));
        Image imagen7=new Image(getClass().getResourceAsStream("lanchaRumania.png"));
        Image imagen8=new Image(getClass().getResourceAsStream("submarinoRumania.png"));

        Barco barco=new Barco("Destructor", 80, 75, 300, 100, 1.3f, 40, 50, "Espania", 30, 50, new ImageView(imagen1));
        Barco barco1=new Barco("Lancha", 20, 60, 300, 200, 2, 40, 50, "Espania", 10, 40, new ImageView(imagen3));
        Barco barco4=new Barco("Acorazado",120, 100, 300, 300, 1.1f, 40, 50, "Espania", 40, 50, new ImageView(imagen2));
        Barco barco5=new Barco("Submarino",30, 80, 300, 400, 1, 40, 50, "Espania", 40, 50, new ImageView(imagen4));
        Barco barco6=new Barco("Destructor",80, 75, 800, 100, 1.3f, 40, 50, "Rumania", 30, 50, new ImageView(imagen5));
        Barco barco7=new Barco("Lancha",20, 60, 800, 200, 2, 40, 50, "Rumania", 10, 40, new ImageView(imagen7));
        Barco barco2=new Barco("Acorazado",120, 100, 800, 300, 1.1f, 40, 50, "Rumania", 40, 50, new ImageView(imagen6));
        Barco barco3=new Barco("Submarino",30, 80, 800, 400, 1, 40, 50, "Rumania", 40, 50, new ImageView(imagen8));
        barcos.add(barco);
        barcos.add(barco1);
        barcos.add(barco2);
        barcos.add(barco3);
        barcos.add(barco4);
        barcos.add(barco5);
        barcos.add(barco6);
        barcos.add(barco7);

        for (int i=0; i<barcos.size(); i++){
            barcos.get(i).getImagen().setFitWidth(barcos.get(i).getAncho());
            barcos.get(i).getImagen().setFitHeight(barcos.get(i).getAltura());

            pane.getChildren().add(barcos.get(i).getImagen());

            barcos.get(i).timeline();
        }

        ControlDeJuego control=new ControlDeJuego(barcos);
        control.tempos();
        control.sonar();
    }

}