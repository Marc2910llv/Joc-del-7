package treball_final_2022;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class TREBALL_FINAL_2022 extends JFrame {

    private Container contenedor;
    private final Color colorTauler = new Color(0, 110, 0);

    private boolean acabat;
    private final int numCartes = 13;
    private final Jugador[] jugadors = new Jugador[4];
    Tauler tauler;
    Baralla baralla;

    public static void main(String[] args) {
        try {
            new TREBALL_FINAL_2022().interfici();
        } catch (IOException ex) {
            Logger.getLogger(TREBALL_FINAL_2022.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void interfici() throws IOException {
        setTitle("Pràctica Prog II - Joc del 7");
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 800);
        setResizable(true);
        setDefaultCloseOperation(TREBALL_FINAL_2022.EXIT_ON_CLOSE);
        contenedor = getContentPane();

        FlowLayout centrado = new FlowLayout(FlowLayout.CENTER, 0, 20);

        /*----------------------------------------------------------------------
        --TABLERO DE JUEGO
        ----------------------------------------------------------------------*/
        //////////////////////////////JUGADORS IA///////////////////////////////
        CasillaCarta cartesJugador1 = actualitzarMaJugador(0, null);
        cartesJugador1.setLayout(centrado);
        CasillaCarta cartesJugador2 = actualitzarMaJugador(0, null);
        cartesJugador2.setLayout(centrado);
        CasillaCarta cartesJugador3 = actualitzarMaJugador(0, null);
        cartesJugador3.setLayout(centrado);

        //agrupam les baralles dins un panell
        JPanel taulerJugadorsIA = new JPanel();
        taulerJugadorsIA.setBackground(colorTauler);
        taulerJugadorsIA.setLayout(new GridLayout(1, 5, 135, 0));
        //cream un panell auxiliar que ajuda a mantenir el tamany de la finestra
        //a més de la colocació de les casilles de la IA correctament
        JLabel aux = new JLabel();
        aux.setIcon(new ImageIcon(ImageIO.read(new File("Cartes/card_back_blue.png")).getScaledInstance(Carta.tamanyCartes[0] + 20, Carta.tamanyCartes[1], Image.SCALE_DEFAULT)));
        aux.setVisible(false);

        taulerJugadorsIA.add(aux);
        taulerJugadorsIA.add(cartesJugador1);
        taulerJugadorsIA.add(cartesJugador2);
        taulerJugadorsIA.add(cartesJugador3);
        taulerJugadorsIA.add(new JLabel());
        ////////////////////////////////////////////////////////////////////////

        ///////////////////////////TAULER - BARALLA/////////////////////////////
        //Ordre: TREBOLS, DIAMANTS, CORS, PIQUES
        //inicialitzam tauler amb la baralla
        CasillaCarta[][] casillas = new CasillaCarta[4][13];
        JPanel taulerBaralla = new JPanel();
        taulerBaralla.setBackground(colorTauler);
        taulerBaralla.setLayout(new GridLayout(4, 13));
        Carta[][] cartas = new Carta[4][13];

        //inicialitzam cartes
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cartas[i][j] = new Carta(Pal.values()[i], j + 1);
            }
        }

        //mostram el tauler inicial
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                casillas[i][j] = new CasillaCarta();
                taulerBaralla.add(casillas[i][j]);
            }
        }
        //añadir cartas a las casillas del tablero
        Tauler t = new Tauler();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (t.colocarCarta(cartas[i][j])) {
                    casillas[i][j].add(cartas[i][j].carta);
                }
            }
        }

        //MEZCLAR cartas[][] y añadir otra vez
        //NO BORRAR NI TOCAR, després vorem si mhos va be
        //obtener baraja de cada jugador después de mezclar
//        Carta[] BarajaUsuario = new Carta[13];
//        Carta[] BarajaJug1 = new Carta[13];
//        Carta[] BarajaJug2 = new Carta[13];
//        Carta[] BarajaJug3 = new Carta[13];
//        Carta aux2 ;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 13; j++) {
//              aux2 = new Carta(cartas[i][j].getPal(), cartas[i][j].getNum());
//              
//                if (i == 0) {
//                    BarajaUsuario[j] = new Carta(Pal.values()[i],j+1);
//                    BarajaUsuario[j].add(aux2);
//                } else if (i == 1) {
//                    BarajaJug1[j] = new Carta(Pal.values()[i],j+1);
//                    BarajaJug1[j].add(aux2);
//                } else if (i == 2) {
//                    BarajaJug2[j] = new Carta(Pal.values()[i],j+1);
//                    BarajaJug2[j].add(aux2);
//                } else if (i == 3) {
//                    BarajaJug3[j] = new Carta(Pal.values()[i],j+1);
//                    BarajaJug3[j].add(aux2);
//                }
//            }
//        }
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////JUGADOR USUARI//////////////////////////////
        JPanel TaulerUsuari = new JPanel();
        TaulerUsuari.setBackground(Color.BLACK);
        TaulerUsuari.setLayout(new GridLayout(1, 13));
        TaulerUsuari.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 2));
        Carta[][] cartasUsuario = new Carta[1][13];
        CasillaCarta[][] casillasUsuario = new CasillaCarta[1][13];

        //NO BORRAR
        // en teoria sobren ses casillas; 
//        for (int i = 0; i < BarajaJug1.length; i++) {
//            casillasUsuario[i] = new CasillaCarta();
//            TaulerUsuari.add(casillasUsuario[i]);
//            casillasUsuario[i].add(BarajaJug1[i].getCarta());
//        }

//        for (int i = 0; i < 13; i++) {
//            casillasUsuario[i] = new CasillaCarta();
//            TaulerUsuari.add(casillasUsuario[i]);
//        }

//NECESITAM SES CASILLAS PER PODER AFEGIR /LLEVAR CARTES i no me va be XD
//SI COMENTES SA 176 I DESCOMENTES 174,175 SE VEU
        TaulerUsuari.setSize(800,800);
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 13; j++) {
                casillasUsuario[i][j] = new CasillaCarta();
                cartasUsuario[i][j] = new Carta(Pal.values()[i + 1], j + 1);
//                casillasUsuario[i][j].add(cartas[i][j].carta);
//                TaulerUsuari.add(casillasUsuario[i][j]);
                TaulerUsuari.add(cartas[i][j].carta);
                

            }
        }

        ////////////////////////////////////////////////////////////////////////

        /*----------------------------------------------------------------------
        --MENU INFERIOR
        ----------------------------------------------------------------------*/
        JPanel menuBotons = new JPanel();

        menuBotons.setLayout(
                new FlowLayout(FlowLayout.CENTER, 10, 3));
        JButton mescla = new JButton(" Mescla ");

        mescla.setBorder(
                new RoundedBorder(6));
        menuBotons.add(mescla);
        JButton juga = new JButton("  Juga  ");

        juga.setBorder(
                new RoundedBorder(6));
        menuBotons.add(juga);
        JButton reinicia = new JButton("Reinicia");

        reinicia.setBorder(
                new RoundedBorder(6));
        menuBotons.add(reinicia);

        JTextArea texteMissatge = new JTextArea();

        texteMissatge.setEditable(
                false);
        texteMissatge.setText(
                "             ");

        JPanel menuTotal = new JPanel();

        menuTotal.setLayout(
                new GridLayout(2, 1));
        menuTotal.add(menuBotons);

        menuTotal.add(texteMissatge);
        /*----------------------------------------------------------------------
        --DISTRIBUCIÓ
        ----------------------------------------------------------------------*/
        JSplitPane separadorIA = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorTablero = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorMenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        separadorIA.setTopComponent(taulerJugadorsIA);

        separadorIA.setBottomComponent(taulerBaralla);

        separadorIA.setDividerSize(
                0);
        separadorTablero.setTopComponent(taulerBaralla);

        separadorTablero.setBottomComponent(TaulerUsuari);

        separadorTablero.setDividerSize(
                6);
        separadorMenu.setTopComponent(TaulerUsuari);

        separadorMenu.setBottomComponent(menuTotal);

        separadorMenu.setDividerSize(
                0);

        contenedor.add(separadorIA, BorderLayout.NORTH);

        contenedor.add(separadorTablero, BorderLayout.CENTER);

        contenedor.add(separadorMenu, BorderLayout.SOUTH);

        setVisible(
                true);
    }

    private CasillaCarta actualitzarMaJugador(int cartesRestants, BufferedImage bufferedImage) {
        CasillaCarta aux = new CasillaCarta();
        if (bufferedImage != null) {
            Image imatge = bufferedImage.getScaledInstance(Carta.tamanyCartes[0] + 20, Carta.tamanyCartes[1], Image.SCALE_DEFAULT);
            aux.setIcon(new ImageIcon(imatge));
        }
        JTextArea text_Jugador = new JTextArea(String.valueOf(cartesRestants));
        text_Jugador.setForeground(Color.WHITE);
        text_Jugador.setFont(new Font("Arial", Font.PLAIN, 55));
        text_Jugador.setOpaque(false);
        text_Jugador.setEditable(false);
        aux.add(text_Jugador);
        return aux;
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
