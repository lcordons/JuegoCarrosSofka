/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.aplicacion.juego;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Puestos implements Serializable {

    @Id
    private int id;
    private String nombreJugador;
    private int vecesGanadoPrimero;
    private int vecesGanadoSegundo;
    private int vecesGanadoTercero;

    public Puestos() {
    }

    public Puestos(int id, String nombre, int vecesPrimero, int vecesSegundo, int vecesTercero) {
        this.id = id;
        this.nombreJugador = nombre;
        this.vecesGanadoPrimero = vecesGanadoPrimero;
        this.vecesGanadoSegundo = vecesGanadoSegundo;
        this.vecesGanadoTercero = vecesGanadoTercero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombreJugador;
    }

    public void setNombre(String nombre) {
        this.nombreJugador = nombre;
    }

    public int getVecesPrimero() {
        return vecesGanadoPrimero;
    }

    public void setVecesPrimero(int vecesPrimero) {
        this.vecesGanadoPrimero = vecesPrimero;
    }

    public int getVecesSegundo() {
        return vecesGanadoSegundo;
    }

    public void setVecesSegundo(int vecesSegundo) {
        this.vecesGanadoSegundo = vecesSegundo;
    }

    public int getVecesTercero() {
        return vecesGanadoTercero;
    }

    public void setVecesTercero(int vecesTercero) {
        this.vecesGanadoTercero = vecesTercero;
    }

}
