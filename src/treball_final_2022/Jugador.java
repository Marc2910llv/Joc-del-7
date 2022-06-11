/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treball_final_2022;

import java.util.ArrayList;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class Jugador {

    //ATRIBUTOS
    private int nombre;
    public final ArrayList<Carta> cartasAsignadas;

    //CONSTRUCTOR
    public Jugador(int nombre) {
        this.nombre = nombre;
        cartasAsignadas = new ArrayList<>();
    }

    public void asignarCarta(Carta carta) {
        cartasAsignadas.add(carta);
    }

    public int getNumCartas() {
        return cartasAsignadas.size();
    }

    public int getNombre() {
        return nombre;
    }

    public void treureCarta(Tauler taula) {
        boolean sacado = false;
        for (int i = 0; i < cartasAsignadas.size() && !sacado; i++) {
            if (taula.colocarCarta(cartasAsignadas.get(i))) {
                cartasAsignadas.remove(i);
                sacado = true;
            }
        }
    }

    @Override
    public String toString() {
        String s = "Jug " + nombre + ": ";
        for (int i = 0; i < cartasAsignadas.size(); i++) {
            s += cartasAsignadas.get(i).toString() + " ";

        }
        return s;
    }
}
