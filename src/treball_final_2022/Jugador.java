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
    public final ArrayList<Carta> cartasAsignadas;

    //CONSTRUCTOR
    public Jugador() {
        cartasAsignadas = new ArrayList<>();
    }

    public void asignarCarta(Carta carta) {
        cartasAsignadas.add(carta);
    }

    public int getNumCartas() {
        return cartasAsignadas.size();
    }

    public Carta[] getArrayCartasAsignadas() {
        Carta[] aux = new Carta[cartasAsignadas.size()];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = cartasAsignadas.get(i);
        }
        return aux;
    }

    public Carta treureCarta(Tauler taula) {
        for (int i = 0; i < cartasAsignadas.size(); i++) {
            if (taula.colocarCarta(cartasAsignadas.get(i))) {
                Carta c = cartasAsignadas.get(i);
                cartasAsignadas.remove(i);
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < cartasAsignadas.size(); i++) {
            s += cartasAsignadas.get(i).toString() + " ";

        }
        return s;
    }
}
