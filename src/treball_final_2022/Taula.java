/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treball_final_2022;

/**
 *
 * @author carlos
 */
public class Taula {
    //ATRIBUTOS
    private Carta[][] tablero;
    private final int filas = 4;
    private final int columnas = 12;

    //CONSTRUCTOR
    public Taula() {

        //Declaramos matrizz de 4 filas y 12 columnas que será nuestra tabla
        tablero = new Carta[filas][columnas];
    }

    //metodo que escribe la carta en el tablero
    public void escribir(Carta carta) {
        //Dependiendo del Pal de la carta y de su numero obtenemos las coordenadas en las que hay que escribir la carta
        int fila = 0, columna = 0;
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
        tablero[fila][columna] = carta; //ESCRIBIMOS CARTA
    }

    //METODO BOOLEANO QUE DEVUELVE SI UNA CARTA ES COLOCABLE
    public boolean CartaColocable(Carta carta) {
        int fila = 0, columna = 0;
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

        //TRATAMIENTO PARA DETECTAR SI UNA POSICION ES VALIDA DEPENDIENDO DE SU COLUMNA
        if (columna == 0) {
            return (tablero[fila][columna + 1] != null) || carta.getNum() == 6;
        } else {
            if (columna == 11) {
                return (tablero[fila][columna - 1] != null) || carta.getNum() == 6;
            } else {
                return (tablero[fila][columna + 1] != null) || (tablero[fila][columna - 1] != null) || (carta.getNum() == 6); //DEVOLVEMOS SI LA CARTA ES COLOCABLE
            }
        }
    }

    @Override
    //METODO TOSTRING
    public String toString() {
        String s = "";
        for (int n = 0; n < filas; n++) {
            s += Pal.values()[n]; //Visualizamos los tipos enumerados al inicio de la fila
            for (int m = 0; m < columnas; m++) {
                if (tablero[n][m] == null) {
                    s += "[     ]";
                } else {
                    s += tablero[n][m].toString(); //Añadimos a la cadena la informacion de la carta
                }
            }
            s += "\n";
        }
        return s;
    }

}
