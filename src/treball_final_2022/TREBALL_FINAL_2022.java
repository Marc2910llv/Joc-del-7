package treball_final_2022;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class TREBALL_FINAL_2022 extends JFrame {

    private Container contenedor;
    private final Color colorTauler = new Color(0, 110, 0);
    private int[] tamanyCartes = {65, 100};

    private boolean acabat;
    private final int numCartes = 13;
    private final Jugador[] jugadors = new Jugador[4];

    public static void main(String[] args) {
        try {
            new TREBALL_FINAL_2022().interfici();
        } catch (IOException ex) {
            Logger.getLogger(TREBALL_FINAL_2022.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void interfici() throws IOException {
        setTitle("Pràctica Prog II - Joc del 7");
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 1000);
        setResizable(false);
        setDefaultCloseOperation(TREBALL_FINAL_2022.EXIT_ON_CLOSE);
        contenedor = getContentPane();

        /*----------------------------------------------------------------------
        --TABLERO DE JUEGO
        ----------------------------------------------------------------------*/
        //BARALLA
        //Ordre: CORS, DIAMANTS, TREBOLS, PIQUES
        BufferedImage buf;
        Image imagge;
        Carta[][] cartas = new Carta[4][13];
        String[] pals = {"hearts", "diamonds", "clubs", "spades"};
        JPanel TaulerBaralla = new JPanel(); 
        TaulerBaralla.setBackground(colorTauler);
        TaulerBaralla.setLayout(new GridLayout(4, 13));
        //rellenamos tablero con cartas;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cartas[i][j] = new Carta();
                buf = ImageIO.read(new File("Cartes/" + (j + 1) + "_of_" + pals[i] + ".png")); //
                imagge = buf.getScaledInstance(tamanyCartes[0], tamanyCartes[1], Image.SCALE_DEFAULT);
                TaulerBaralla.add(cartas[i][j].crearCarta(imagge));
            }
        }

        //JUGADORS IA
        BufferedImage bufferedImage = ImageIO.read(new File("Cartes/card_back_blue.png"));
        Image image = bufferedImage.getScaledInstance(tamanyCartes[0], tamanyCartes[1], Image.SCALE_DEFAULT);
        //iniciam els contenidors de les baralles dels jugadors IA
        JLabel cartesJugadors1 = new JLabel();
        cartesJugadors1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        JLabel cartesJugadors2 = new JLabel();
        cartesJugadors2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        JLabel cartesJugadors3 = new JLabel();
        cartesJugadors3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
//
//        //      JTextArea text_Jugador1 = new JTextArea("0");
////       text_Jugador1.setForeground(Color.WHITE);
////        text_Jugador1.setFont(new Font("Arial", Font.PLAIN, 55));
////       text_Jugador1.setOpaque(false);
////        text_Jugador1.setEditable(false);
//        cartesJugadors1.setIcon(new ImageIcon(image));
        cartesJugadors1.setForeground(Color.WHITE);
        cartesJugadors1.setBackground(new Color(0, 82, 0));
        cartesJugadors1.setText(" 0 ");
        cartesJugadors1.setFont(new Font("Arial", Font.PLAIN, 55));
        cartesJugadors1.setOpaque(true);
//        //cartesJugadors1.add(text_Jugador1);
//
////        JTextArea text_Jugador2 = new JTextArea("0");
////        text_Jugador2.setForeground(Color.WHITE);
////        text_Jugador2.setFont(new Font("Arial", Font.PLAIN, 55));
////        text_Jugador2.setOpaque(false);
////        text_Jugador2.setEditable(false);
////        cartesJugadors2.setIcon(new ImageIcon(image));
        cartesJugadors2.setForeground(Color.WHITE);
        cartesJugadors2.setBackground(new Color(0, 82, 0));
        cartesJugadors2.setText(" 0 ");
        cartesJugadors2.setFont(new Font("Arial", Font.PLAIN, 55));
        cartesJugadors2.setOpaque(true);
//        //cartesJugadors2.add(text_Jugador2);
//
////        JTextArea text_Jugador3 = new JTextArea("0");
////        text_Jugador3.setForeground(Color.WHITE);
////        text_Jugador3.setFont(new Font("Arial", Font.PLAIN, 55));
////        text_Jugador3.setOpaque(false);
////        text_Jugador3.setEditable(false);
////        cartesJugadors3.setIcon(new ImageIcon(image));
        cartesJugadors3.setForeground(Color.WHITE);
        cartesJugadors3.setBackground(new Color(0, 82, 0));
        cartesJugadors3.setText(" 0 ");
        cartesJugadors3.setFont(new Font("Arial", Font.PLAIN, 55));
        cartesJugadors3.setOpaque(true);
//        //cartesJugadors3.add(text_Jugador3);

//        //agrupam les baralles dins un panell
        JPanel jugadorsIA = new JPanel();
        jugadorsIA.setBackground(colorTauler);
        jugadorsIA.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 5));

        jugadorsIA.add(cartesJugadors1);
        jugadorsIA.add(cartesJugadors2);
        jugadorsIA.add(cartesJugadors3);

        //JUGADOR USUARI
        JLabel barallaUsuari = new JLabel();

        barallaUsuari.setFont(new Font("SansSerif", Font.PLAIN, 30));
        barallaUsuari.setText("13");

        for (int i = 0; i < 13; i++) {
            barallaUsuari.setIcon(new ImageIcon("Cartes/card_back_blue.png"));
        }

        JPanel tauler = new JPanel();
        tauler.setBackground(colorTauler);
        tauler.setLayout(new GridLayout(1, 3));

        tauler.add(jugadorsIA);

        /*----------------------------------------------------------------------
        --MENU INFERIOR
        ----------------------------------------------------------------------*/
        JPanel menuBotons = new JPanel();
        menuBotons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        JButton mescla = new JButton(" Mescla ");
        mescla.setBorder(new RoundedBorder(6));
        menuBotons.add(mescla);
        JButton juga = new JButton("  Juga  ");
        juga.setBorder(new RoundedBorder(6));
        menuBotons.add(juga);
        JButton reinicia = new JButton("Reinicia");
        reinicia.setBorder(new RoundedBorder(6));
        menuBotons.add(reinicia);

        JTextArea texteMissatge = new JTextArea();
        texteMissatge.setEditable(false);
        texteMissatge.setText("             ");


        /*----------------------------------------------------------------------
        --DISTRIBUCIÓ
        ----------------------------------------------------------------------*/
        JSplitPane TableroBaraja = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorTablero = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorMenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        TableroBaraja.setTopComponent(tauler);
        TableroBaraja.setBottomComponent(TaulerBaralla);
        separadorTablero.setTopComponent(TaulerBaralla);
        separadorTablero.setBottomComponent(menuBotons);
        separadorMenu.setTopComponent(menuBotons);
        separadorMenu.setBottomComponent(texteMissatge);

        contenedor.add(TableroBaraja, BorderLayout.NORTH);
        contenedor.add(separadorTablero, BorderLayout.CENTER);
        contenedor.add(separadorMenu, BorderLayout.SOUTH);

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

}
