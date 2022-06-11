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
    
    
    public boolean colocarCarta(Carta carta) {
        int fila = 0, columna;
        switch (carta.getPal()) {
            case TREBOLS:
                fila = 0;
                break;
            case DIAMANTS:
                fila = 1;
                break;
            case CORS:
                fila = 2;
                break;
            case PIQUES:
                fila = 3;
                break;
        }
        columna = carta.getNum() - 1;

        if (carta.getNum() == 7) {
            return true;
        }
        if (columna == 0) {
            if (tauler[fila][columna + 1] != null) {
                if (tauler[fila][columna + 1].getNum() == 2) {
                    tauler[fila][columna] = carta;
                }
            }
            return false;
        } 
        if (columna == 12) {
            if (tauler[fila][columna - 1] != null) {
                if (tauler[fila][columna - 1].getNum() == 12) {
                    tauler[fila][columna] = carta;
                }
            }
            return false;
        } 
        if (tauler[fila][columna - 1] != null) {
            if (tauler[fila][columna - 1].getNum() < carta.getNum()) {
                tauler[fila][columna] = carta;
            }
        } else if (tauler[fila][columna + 1] != null) {
            if (tauler[fila][columna + 1].getNum() > carta.getNum()) {
                tauler[fila][columna] = carta;
            }
        }
        return false;
    }

    public Carta[][] getTauler() {
        return tauler;
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
