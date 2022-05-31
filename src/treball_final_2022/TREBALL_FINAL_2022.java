package treball_final_2022;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class TREBALL_FINAL_2022 {

    boolean acabat;
    private final int numJugadors = 4;
    Jugador[] jugadors = new Jugador[4];

    public static void main(String[] args) {
        new TREBALL_FINAL_2022().inici();
    }

    private void inici() {
        acabat = false;
        Baralla bar = new Baralla();
        System.out.println(bar.toString() + "\n");
        bar.mescla();
        System.out.println(bar.toString());
        for (int i = 0; i < 4; i++) {
            jugadors[i] = new Jugador(i + 1);
            for (int k = 0; k < 13; k++) {
                RepartirCartes(jugadors[i], bar);
            }
        }
        for (int k = 0; k < 4; k++) {
            System.out.println(jugadors[k].toString());
        }
        Tauler t = new Tauler();
        System.out.println(t.toString());
        while (!acabat) {
            for (int k = 0; (k < 4) && !acabat; k++) {
                jugadors[k].treureCarta(t);
                if (jugadors[k].getNumCartas() == 0) {
                    acabat = true;
                }
                System.out.println(jugadors[k].toString());
            }
            System.out.println(t.toString());
        }
    }

    private void RepartirCartes(Jugador jug, Baralla bara) {
        try {
            jug.asignarCarta(bara.agafaCarta());
        } catch (Baralla.NohihaCartes ex) {
            System.out.println("NO HAY MÁS CARTAS");
        }
    }

}
