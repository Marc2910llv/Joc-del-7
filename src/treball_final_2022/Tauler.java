/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treball_final_2022;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class Tauler {

    //ATRIBUTS
    private final Carta[][] tauler;
    private final int files = 4;
    private final int columnes = 13;

    //CONSTRUCTOR
    public Tauler() {
        tauler = new Carta[files][columnes];
    }

    public void escriure(Carta carta) {
        int fila = 0, columna;
        switch (carta.getPal()) {
            case CORS:
                fila = 0;
                break;
            case DIAMANTS:
                fila = 1;
                break;
            case TREBOLS:
                fila = 2;
                break;
            case PIQUES:
                fila = 3;
                break;
        }
        columna = carta.getNum() - 1;
        tauler[fila][columna] = carta;
    }

    public boolean cartaColocable(Carta carta) {
        int fila = 0, columna;
        switch (carta.getPal()) {
            case CORS:
                fila = 0;
                break;
            case DIAMANTS:
                fila = 1;
                break;
            case TREBOLS:
                fila = 2;
                break;
            case PIQUES:
                fila = 3;
                break;
        }
        columna = carta.getNum() - 1;

        if (columna == 0) {
            return (tauler[fila][columna + 1].getNum() == 2);
        } else {
            if (columna == 12) {
                return (tauler[fila][columna - 1].getNum() == 12);
            } else {
                return (tauler[fila][columna + 1].getNum() < carta.getNum()) || (tauler[fila][columna - 1].getNum() > carta.getNum()) || (carta.getNum() == 7);
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int n = 0; n < files; n++) {
            s += Pal.values()[n];
            for (int m = 0; m < columnes; m++) {
                if (tauler[n][m] == null) {
                    s += "[     ]";
                } else {
                    s += tauler[n][m].toString();
                }
            }
            s += "\n";
        }
        return s;
    }

}
