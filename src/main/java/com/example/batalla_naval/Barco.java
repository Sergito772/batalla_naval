package com.example.batalla_naval;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Barco {
    private int vida;
    private int sonar;
    private int posX;
    private int posY;
    private int cadencia;
    private int potencia;
    private float velocidadX;
    private float velocidadY;
    private int altura;
    private int ancho;
    private String pais;
    private ImageView imagen;
    private String Tipo;
    Timeline BarcoMovimiento;

    public Barco(String Tipo, int vida, int sonar, int posX, int posY, float velocidad, int altura, int ancho, String pais, int cadencia, int potencia, ImageView imagen) {
        this.Tipo=Tipo;
        this.vida = vida;
        this.sonar = sonar;
        this.posX=posX;
        this.posY=posY;
        this.velocidadX = velocidad;
        this.velocidadY = velocidad;
        this.altura=altura;
        this.ancho=ancho;
        this.cadencia = cadencia;
        this.potencia = potencia;
        this.imagen=imagen;
        getImagen().setX(posX);
        getImagen().setX(posY);
        this.pais=pais;
    }

    public Barco(Barco barco){
        this.vida=new Barco(barco).getVida();
        this.sonar=new Barco(barco).getSonar();
        this.posX=new Barco(barco).getPosX();
        this.posY=new Barco(barco).getPosY();
        this.velocidadX=new Barco(barco).getVelocidad();
        this.velocidadY=new Barco(barco).getVelocidad();
        this.cadencia=new Barco(barco).getCadencia();
        this.potencia=new Barco(barco).getPotencia();
        this.imagen=new Barco(barco).getImagen();
    }

    public Barco() {
        this.vida=0;
        this.sonar=0;
        this.posX=0;
        this.posY=0;
        this.velocidadX=0;
        this.velocidadY=0;
        this.cadencia=0;
        this.potencia=0;
        this.imagen=null;
    }

    public String getTipo() {
        return Tipo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getSonar() {
        return sonar;
    }

    public void setSonar(int sonar) {
        this.sonar = sonar;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public float getVelocidad() {
        return velocidadX;
    }

    public void setVelocidad(int velocidad) {
        this.velocidadX = velocidad;
    }

    public int getCadencia() {
        return cadencia;
    }

    public void setCadencia(int cadencia) {
        this.cadencia = cadencia;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void timeline(){
        float velocidadPositiva=velocidadX;
        float velocidadNegativa=velocidadX*-1;
        int aleatoriop=(int)(Math.random()*2+1);
        if (aleatoriop==1) {
            if (getTipo().equals("Destructor") || getTipo().equals("Acorazado"))
                velocidadY = velocidadNegativa/4;
            else
                velocidadY = velocidadNegativa;
        }

        if (getPais().equals("Rumania"))
            if (getTipo().equals("Destructor") || getTipo().equals("Acorazado"))
                velocidadX=velocidadNegativa*0.85f;
            else
                velocidadX=velocidadNegativa;

        getImagen().setRotate(45);

        BarcoMovimiento=new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae)->{
                    posX+=velocidadX;
                    posY+=velocidadY;
                    getImagen().setX(posX);
                    getImagen().setY(posY);

                    if (posX>=1000) {
                        if (getTipo().equals("Destructor") || getTipo().equals("Acorazado"))
                            velocidadX = velocidadNegativa/2;
                        else
                            velocidadX = velocidadNegativa;

                        int aleatorio=(int)(Math.random()*2+1);
                        if (aleatorio==1) {
                            getImagen().setRotate(315);
                        }else{
                            if (getTipo().equals("Destructor") || getTipo().equals("Acorazado"))
                                velocidadY = velocidadPositiva*0.85f;
                            else
                                velocidadY = velocidadPositiva;
                            getImagen().setRotate(225);
                        }
                    }else if (posX<=180) {
                        velocidadX=velocidadPositiva;

                        int aleatorio=(int)(Math.random()*2+1);
                        if (aleatorio==1) {
                            velocidadY = velocidadPositiva;
                            getImagen().setRotate(315);
                        }else {
                            velocidadY = velocidadNegativa;
                            getImagen().setRotate(45);
                        }
                    }

                    if (posY>=590) {
                        if (getTipo().equals("Destructor") || getTipo().equals("Acorazado"))
                            velocidadY = velocidadNegativa/4;
                        else
                            velocidadY = velocidadNegativa;

                        int aleatorio=(int)(Math.random()*2+1);
                        if (aleatorio==1) {
                            velocidadX = velocidadPositiva;
                            getImagen().setRotate(45);
                        }else {
                            if (getTipo().equals("Destructor") || getTipo().equals("Acorazado"))
                                velocidadX = velocidadNegativa*0.85f;
                            else
                                velocidadX = velocidadNegativa;
                            getImagen().setRotate(135);
                        }
                    }else if (posY<=70) {
                        velocidadY=velocidadPositiva;

                        int aleatorio=(int)(Math.random()*2+1);
                        if (aleatorio==1) {
                            velocidadX = velocidadPositiva;
                            getImagen().setRotate(315);
                        }else {
                            if (getTipo().equals("Destructor") || getTipo().equals("Acorazado"))
                                velocidadX = velocidadNegativa*0.85f;
                            else
                                velocidadX = velocidadNegativa;
                            getImagen().setRotate(225);
                        }
                    }
                })
        );

        BarcoMovimiento.setCycleCount(Timeline.INDEFINITE);
        BarcoMovimiento.play();
    }
}

