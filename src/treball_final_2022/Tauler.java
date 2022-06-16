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
    public final Carta[][] taulerCartes;
    private final int files = 4;
    private final int columnes = 13;

    //CONSTRUCTOR
    public Tauler() {
        taulerCartes = new Carta[files][columnes];
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
            taulerCartes[fila][columna] = carta;
            return true;
        }
        if (columna == 0) {
            if (taulerCartes[fila][columna + 1] != null) {
                if (taulerCartes[fila][columna + 1].getNum() == 2) {
                    taulerCartes[fila][columna] = carta;
                    return true;
                }
            }
            return false;
        }
        if (columna == 12) {
            if (taulerCartes[fila][columna - 1] != null) {
                if (taulerCartes[fila][columna - 1].getNum() == 12) {
                    taulerCartes[fila][columna] = carta;
                    return true;
                }
            }
            return false;
        }
        if (taulerCartes[fila][columna - 1] != null) {
            if (taulerCartes[fila][columna - 1].getNum() < carta.getNum()) {
                taulerCartes[fila][columna] = carta;
                return true;
            }
        } else if (taulerCartes[fila][columna + 1] != null) {
            if (taulerCartes[fila][columna + 1].getNum() > carta.getNum()) {
                taulerCartes[fila][columna] = carta;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "";
        for (int n = 0; n < files; n++) {
            s += Pal.values()[n];
            for (int m = 0; m < columnes; m++) {
                if (taulerCartes[n][m].getPal() != null) {
                    s += "[     ]";
                } else {
                    s += taulerCartes[n][m].toString();
                }
            }
            s += "\n";
        }
        return s;
    }

}
