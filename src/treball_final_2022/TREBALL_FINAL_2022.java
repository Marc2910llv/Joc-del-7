package treball_final_2022;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class TREBALL_FINAL_2022 extends JFrame {

    boolean acabat;
    private final int numCartes = 13;
    Jugador[] jugadors = new Jugador[4];
    private Container contenidor;

    public static void main(String[] args) {
        new TREBALL_FINAL_2022().inici();
    }

    private void interfici() {
        setTitle("Pràctica Prog II - Joc del 7");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(800, 800);
        setDefaultCloseOperation(TREBALL_FINAL_2022.EXIT_ON_CLOSE);
        contenidor = getContentPane();

        /*----------------------------------------------------------------------
        --TABLERO DE JUEGO
        ----------------------------------------------------------------------*/
        //JUGADORS IA
        JFrame[] cartesJugador = new JFrame[3];
        ImatgeCarta infoJugadors = new ImatgeCarta();
        try {
            infoJugadors.image = ImageIO.read(new File("Cartes/card_back_blue.png"));
        } catch (IOException ex) {
            Logger.getLogger(TREBALL_FINAL_2022.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (JFrame cartesJugador1 : cartesJugador) {
            cartesJugador1.setSize(50, 80);
            cartesJugador1.setContentPane(infoJugadors);
        }

        //BARALLA
        //Ordre: CORS, DIAMANTS, TREBOLS, PIQUES
        JPanel taulerBaralla = new JPanel();
        taulerBaralla.setSize(800, 600);
        taulerBaralla.setLayout(new GridLayout(4, 13));

        String[] pals = {"hearts", "diamonds", "clubs", "spades"};
        ImatgeCarta[][] imatgesBaralla = new ImatgeCarta[4][13];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                try {
                    imatgesBaralla[i][j].image = ImageIO.read(new File("Cartes/" + j + "_of_" + pals[i] + ".png"));
                    taulerBaralla.add(imatgesBaralla[i][j]);
                } catch (IOException ex) {
                    Logger.getLogger(TREBALL_FINAL_2022.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //JUGADOR USUARI
        JPanel barallaUsuari = new JPanel();
        taulerBaralla.setSize(800, 50);
        taulerBaralla.setLayout(new GridLayout(1, 14));

        JLabel cartesRestantsUsuari = new JLabel();
        cartesRestantsUsuari.setFont(new Font("SansSerif", Font.PLAIN, 30));
        cartesRestantsUsuari.setText("13");
        barallaUsuari.add(cartesRestantsUsuari);

        ImatgeCarta[] imatgesBarallaUsuari = new ImatgeCarta[13];
        for (int i = 0; i < 13; i++) {
            barallaUsuari.add(imatgesBarallaUsuari[i]);
        }

        /*----------------------------------------------------------------------
        --MENU INFERIOR
        ----------------------------------------------------------------------*/
    }

    private void inici() {
        acabat = false;
        Baralla bar = new Baralla();
        System.out.println("BARALLA FORA MESCLAR: ");
        System.out.println(bar.toString()+"\n");
        bar.mescla();
        for (int i = 0; i < 4; i++) {
            jugadors[i] = new Jugador(i + 1);
            for (int k = 0; k < numCartes; k++) {
                RepartirCartes(jugadors[i], bar);
            }
        }
        //imprimim els jugadors amb les cartes corresponents
        for (int i = 0; i < 4; i++) {
            System.out.println(jugadors[i].toString());
        }
        Tauler t = new Tauler();
        System.out.println("");
        System.out.println(t.toString());
        System.out.println("");
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
