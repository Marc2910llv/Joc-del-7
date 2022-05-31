package treball_final_2022;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class TREBALL_FINAL_2022 {

    int numJugadors = 4;
    Jugador aleatori = new Jugador();
    Jugador[] jugadors = new Jugador[3];

    public static void main(String[] args) {
        new TREBALL_FINAL_2022().inicio();
    }

    private void iniciarJugadors() {

    }

    private void inicio() {
        //repartirCartas entre los 4 jugadores
        //mezclar cada baraja de cdaa uno
        Baralla bar = new Baralla(); //Instanciamos baraja
        System.out.println(bar.toString() + "\n"); //Visualizamos baraja
        bar.mescla();
        System.out.println(bar.toString());//Visualizamos baraja mezclada
        for (int k = 0; k < 48; k++) {
            RepartirCartas(jugadors[k % numJugadors], bar); //BUCLE en el que llamamos método Repartir Cartas para cada jugador ciclicamente
        }
    }

    private void RepartirCartas(Jugador jug, Baralla bara) {
        try {
            jug.AsignarCarta(bara.agafaCarta());
        } catch (Baralla.NohihaCartes ex) {
            System.out.println("NO HAY MÁS CARTAS");
        }
    }

}
