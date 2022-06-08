package treball_final_2022;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

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
        new TREBALL_FINAL_2022().interfici();
    }

    private void interfici() {
        setTitle("Pràctica Prog II - Joc del 7");
        setSize(800, 800);
        setDefaultCloseOperation(TREBALL_FINAL_2022.EXIT_ON_CLOSE);
        contenidor = getContentPane();

        /*----------------------------------------------------------------------
        --TABLERO DE JUEGO
        ----------------------------------------------------------------------*/
        JPanel tablero = new JPanel();
        //JUGADORS IA
        JLabel cartesJugadors1 = new JLabel();
        JLabel cartesJugadors2 = new JLabel();
        JLabel cartesJugadors3 = new JLabel();

        JTextField jugador3 = new JTextField("13");
        cartesJugadors3.setIcon(new ImageIcon("Cartes/card_back_blue.png"));

        //BARALLA
        //Ordre: CORS, DIAMANTS, TREBOLS, PIQUES
        JLabel taulerBaralla = new JLabel();
        taulerBaralla.setLayout(new GridLayout(4, 13));

        String[] pals = {"hearts", "diamonds", "clubs", "spades"};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                taulerBaralla.setIcon(new ImageIcon("Cartes/" + j + "_of_" + pals[i] + ".png"));
            }
        }

        //JUGADOR USUARI
        JLabel barallaUsuari = new JLabel();

        barallaUsuari.setFont(new Font("SansSerif", Font.PLAIN, 30));
        barallaUsuari.setText("13");

        for (int i = 0; i < 13; i++) {
            barallaUsuari.setIcon(new ImageIcon("Cartes/card_back_blue.png"));
        }

        /*----------------------------------------------------------------------
        --MENU INFERIOR
        ----------------------------------------------------------------------*/
        JPanel menuInferior = new JPanel();
        menuInferior.setLayout(new GridLayout(2, 1));
        JPanel menuInferior1 = new JPanel();
        menuInferior1.setLayout(new GridLayout(1, 3));

        JButton mescla = new JButton("Mescla");
        menuInferior1.add(mescla);
        JButton juga = new JButton("Juga");
        menuInferior1.add(juga);
        JButton reinicia = new JButton("Reinicia");
        menuInferior1.add(reinicia);

        JTextField texteMissatge = new JTextField();
        texteMissatge.setText("");

        menuInferior.add(menuInferior1);
        menuInferior.add(texteMissatge);

        /*----------------------------------------------------------------------
        --DISTRIBUCIÓ
        ----------------------------------------------------------------------*/
        JSplitPane separacio_IA_baralla = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane separacio_baralla_usuari = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane separacio_usuari_menu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        separacio_IA_baralla.setTopComponent(IA);
        separacio_IA_baralla.setBottomComponent(taulerBaralla);

        separacio_baralla_usuari.setTopComponent(taulerBaralla);
        separacio_baralla_usuari.setBottomComponent(barallaUsuari);

        separacio_usuari_menu.setTopComponent(barallaUsuari);
        separacio_usuari_menu.setBottomComponent(menuInferior);

        contenidor.add(separacio_IA_baralla, BorderLayout.NORTH);
        contenidor.add(separacio_baralla_usuari, BorderLayout.CENTER);
        contenidor.add(separacio_usuari_menu, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void inici() {
        acabat = false;
        Baralla bar = new Baralla();
        System.out.println("BARALLA FORA MESCLAR: ");
        System.out.println(bar.toString() + "\n");
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

    }

}
