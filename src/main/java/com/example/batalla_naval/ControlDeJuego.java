package com.example.batalla_naval;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.Optional;

public class ControlDeJuego {
    private ArrayList<Barco> barcos;
    private int contadorEspania=4;
    private int contadorRumania=4;
    long comienzoDisparo=0;
    ArrayList<Boolean> tiempos=new ArrayList<>();
    MediaPlayer mediaPlayer;

    public ControlDeJuego(ArrayList<Barco> barco){
        this.barcos=barco;
    }

    public void tempos(){
        boolean tempo1=false, tempo2=false, tempo3=false, tempo4=false, tempo5=false, tempo6=false, tempo7=false, tempo8=false;
        tiempos.add(tempo1);
        tiempos.add(tempo2);
        tiempos.add(tempo3);
        tiempos.add(tempo4);
        tiempos.add(tempo5);
        tiempos.add(tempo6);
        tiempos.add(tempo7);
        tiempos.add(tempo8);
        Media media1 = new Media(HelloApplication.class.getResource("musicaFondo.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(media1);
        mediaPlayer.play();
    }

    public void sonar(){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Media media=new Media(HelloApplication.class.getResource("rifle.mp3").toExternalForm());
                MediaPlayer disparo=new MediaPlayer(media);
                // Actualizar coordenadas de los objetos
                for (Barco objeto : barcos) {
                    objeto.getImagen().setX(objeto.getImagen().getTranslateX() + objeto.getImagen().getX());
                    objeto.getImagen().setY(objeto.getImagen().getTranslateY() + objeto.getImagen().getY());
                    objeto.getImagen().setTranslateX(0);
                    objeto.getImagen().setTranslateY(0);
                }

                // Detectar colisiones
                for (int i = 0; i < barcos.size(); i++) {
                    Barco objeto1 = barcos.get(i);

                    if (barcos.get(i).getVida()<=0) {
                        barcos.get(i).BarcoMovimiento.stop();
                        tiempos.remove(i);
                        if (barcos.get(i).getPais().equals("Espania"))
                            contadorEspania--;
                        else
                            contadorRumania--;
                        barcos.remove(i);
                        continue;
                    }

                    // Definir radio de búsqueda para objeto1
                    for (int j = 0; j < barcos.size(); j++) {
                        if (i == j) {
                            continue;
                        }

                        Barco objeto2 = barcos.get(j);

                        double radio1 = barcos.get(j).getSonar();

                        // Obtener coordenadas de objeto2
                        double x2 = objeto2.getImagen().getX();
                        double y2 = objeto2.getImagen().getY();

                        // Calcular distancia entre objetos
                        double distancia = Math.sqrt(Math.pow(x2 - objeto1.getImagen().getX(), 2) + Math.pow(y2 - objeto1.getImagen().getY(), 2));

                        if ((!barcos.get(j).getPais().equals(barcos.get(i).getPais()) && tiempos.get(i) == false)) {
                            if (distancia <= radio1) {
                                disparo.play();
                                int random=(int)(Math.random()*4+1);
                                comienzoDisparo= System.currentTimeMillis();
                                tiempos.set(i, true);
                                if (random==1){
                                    System.out.println("El barco " +barcos.get(j).getTipo()+ " " +barcos.get(j).getPais()+ " fallo el disparo al barco " +barcos.get(i).getTipo()+ " " +barcos.get(i).getPais());
                                }else if (random==2){
                                    barcos.get(i).setVida(barcos.get(i).getVida() - barcos.get(j).getPotencia());
                                    System.out.println("Barco " +barcos.get(j).getTipo()+ " " +barcos.get(j).getPais()+ " ha hecho un daño de " + barcos.get(j).getPotencia() + " al barco " +barcos.get(i).getTipo()+ " " +barcos.get(i).getPais()+ " y ahora tiene " + barcos.get(i).getVida());
                                } else if (random==3) {
                                    barcos.get(i).setVida((int) (barcos.get(i).getVida() - barcos.get(j).getPotencia()*1.5f));
                                    System.out.println("Barco " +barcos.get(j).getTipo()+ " " +barcos.get(j).getPais()+ " ha hecho un daño de " + barcos.get(j).getPotencia()*1.5f + " al barco " +barcos.get(i).getTipo()+ " " +barcos.get(i).getPais()+ " y ahora tiene " + barcos.get(i).getVida());
                                }else {
                                    barcos.get(i).setVida((int) (barcos.get(i).getVida() - barcos.get(j).getPotencia()*0.5f));
                                    System.out.println("Barco " +barcos.get(j).getTipo()+ " " +barcos.get(j).getPais()+ " ha hecho un daño de " + barcos.get(j).getPotencia()*0.5f + " al barco " +barcos.get(i).getTipo()+ " " +barcos.get(i).getPais()+ " y ahora tiene " + barcos.get(i).getVida());
                                }
                            }
                        }
                        long FinDisparo=System.currentTimeMillis();

                        if (FinDisparo-comienzoDisparo>barcos.get(i).getCadencia()*100)
                            tiempos.set(i, false);
                    }
                }
                if (contadorEspania==0){
                    stop();
                    mediaPlayer.setMute(true);

                    media = new Media(HelloApplication.class.getResource("Rumania.mp3").toExternalForm());
                    MediaPlayer Rumania=new MediaPlayer(media);
                    Rumania.play();

                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "La victoria ha sido de rumania", ButtonType.OK);
                    alert.setTitle("Victoria de Rumania");
                    alert.setContentText("Ha ganado Rumania");
                    alert.show();
                } else if (contadorRumania==0) {
                    stop();
                    mediaPlayer.setMute(true);

                    media = new Media(HelloApplication.class.getResource("Españita.mp3").toExternalForm());
                    MediaPlayer España=new MediaPlayer(media);
                    España.play();

                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "La victoria ha sido de españa", ButtonType.OK);
                    alert.setTitle("Victoria de España");
                    alert.setContentText("Ha ganado España");
                    alert.show();
                }
            }
        };
        animationTimer.start();
    }
}
