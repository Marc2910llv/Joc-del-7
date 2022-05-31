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
    private int nombre; //ATRIBUTO DEL NOMBRE
    private final ArrayList<Carta> cartasAsignadas; //Cartas asignadas a un jugador, se utiliza arraylist ya que iremos añadiendo las cartas de uno en uno

    //CONSTRUCTOR
    public Jugador() {
        cartasAsignadas = new ArrayList<>();
    }

    public void AsignarCarta(Carta carta) { //Metodo para asignar cartas
        cartasAsignadas.add(carta);
    }

    //Metodo que devuelve numero de cartas
    public int getNumCartas() {
        return cartasAsignadas.size();
    }

    //Metodo que devuelve el nombre de un jugador
    public int getNombre() {
        return nombre;
    }

    // Metodo para extraer una carta conociendo la informacion de la tabla y escribirla en caso de que sea posible
    public void sacarCarta(Taula taula) {
        boolean sacado = false;
        for (int i = 0; i < cartasAsignadas.size() && !sacado; i++) {
            if (taula.CartaColocable(cartasAsignadas.get(i))) {
                taula.escribir(cartasAsignadas.get(i));
                cartasAsignadas.remove(i);
                sacado = true;
            }
        }
    }

    //METODO QUE DEVUELVE EL PRIMER 6 DE LAS CARTAS DEL JUGADOR, EN CASO DE NO HABER DEVUELVE NULL
    @Override
    public String toString() {
        String s = "";
        s += nombre;
        for (int i = 0; i < cartasAsignadas.size(); i++) {
            s += cartasAsignadas.get(i).toString() + " ";

        }
        return s;
    }
}
