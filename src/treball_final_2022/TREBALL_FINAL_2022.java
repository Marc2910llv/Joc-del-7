package treball_final_2022;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
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
    CasillaCarta[][] casillas;
    Carta[][] cartas ;
    public static void main(String[] args) {
        try {
            new TREBALL_FINAL_2022().interfici();
        } catch (IOException ex) {
            Logger.getLogger(TREBALL_FINAL_2022.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void interfici() throws IOException {
        setTitle("Pràctica Prog II - Joc del 7");
        //  setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 680);
        setResizable(false);
        setDefaultCloseOperation(TREBALL_FINAL_2022.EXIT_ON_CLOSE);
        contenedor = getContentPane();

        /*----------------------------------------------------------------------
        --TABLERO DE JUEGO
        ----------------------------------------------------------------------*/
        //////////////////////////////JUGADORS IA///////////////////////////////
        JLabel cartesJugador1 = actualitzarMaJugadorIA(0, "fondo_casella");
        JLabel cartesJugador2 = actualitzarMaJugadorIA(0, "fondo_casella");
        JLabel cartesJugador3 = actualitzarMaJugadorIA(0, "fondo_casella");

        //agrupam les baralles dins un panell
        JPanel taulerJugadorsIA = new JPanel();
        taulerJugadorsIA.setBackground(colorTauler);
        taulerJugadorsIA.setLayout(new GridLayout(1, 5, 135, 0));
        //cream un panell auxiliar que ajuda a mantenir el tamany de la finestra
        //a més de la colocació de les casilles de la IA correctament
        JLabel aux = new JLabel();
        aux.setIcon(new ImageIcon(ImageIO.read(
                new File("Cartes/card_back_blue.png"))
                .getScaledInstance(Carta.tamanyCartes[0] + 20,
                        Carta.tamanyCartes[1], Image.SCALE_DEFAULT)));
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
        JPanel taulerBaralla = new JPanel();
        taulerBaralla.setBackground(colorTauler);
        taulerBaralla.setLayout(new GridLayout(4, 13));
        casillas = new CasillaCarta[4][13];
        cartas = new Carta[4][13];
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
                // if (t.colocarCarta(cartas[i][j])) {
                casillas[i][j].add(cartas[i][j].carta);
                // }
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
        GridBagConstraints restricciones = new GridBagConstraints();
        JPanel taulerUsuari = new JPanel();
        taulerUsuari.setBackground(colorTauler);
        taulerUsuari.setLayout(new GridBagLayout());

        Carta[] cartasUsuario = new Carta[13];
        cartasUsuario[0] = new Carta(true);

        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.anchor = GridBagConstraints.PAGE_END;
        restricciones.insets = new Insets(0, 19, 0, 0);
        restricciones.gridx = 0;
        restricciones.gridy = 1;
        restricciones.ipady = 5;
        restricciones.ipadx = 0;
        restricciones.weightx = 0.1;
        restricciones.weighty = 0;
        taulerUsuari.add(cartasUsuario[0].carta, restricciones);

        for (int j = 1; j < 13; j++) {
            cartasUsuario[j] = new Carta(false);
            restricciones.gridx = restricciones.gridx + 1;
            taulerUsuari.add(cartasUsuario[j].carta, restricciones);
        }

        JTextArea auxx = new JTextArea("0");
        auxx.setLayout(new FlowLayout(FlowLayout.CENTER));
        auxx.setForeground(Color.WHITE);
        auxx.setFont(new Font("Arial", Font.PLAIN, 35));
        auxx.setOpaque(false);
        auxx.setEditable(false);

        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.ipady = 0;
        restricciones.ipadx = 0;
        restricciones.weighty = 1.0;
        restricciones.insets = new Insets(0, 39, 0, 0);
        restricciones.gridwidth = 2;
        taulerUsuari.add(auxx, restricciones);
        ////////////////////////////////////////////////////////////////////////

        /*----------------------------------------------------------------------
        --MENU INFERIOR
        ----------------------------------------------------------------------*/
        JPanel menuBotons = new JPanel();

        menuBotons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        JButton mescla = new JButton(" Mescla ");
        mescla.addActionListener(gestorEventos);
        mescla.setBorder(new RoundedBorder(6));
        menuBotons.add(mescla);

        JButton juga = new JButton("  Juga  ");
        juga.addActionListener(gestorEventos);
        juga.setBorder(new RoundedBorder(6));
        menuBotons.add(juga);

        JButton reinicia = new JButton("Reinicia");
        reinicia.addActionListener(gestorEventos);
        reinicia.setBorder(new RoundedBorder(6));
        menuBotons.add(reinicia);

        JTextArea texteMissatge = new JTextArea();
        texteMissatge.setEditable(false);
        texteMissatge.setText("             ");

        JPanel menuTotal = new JPanel();
        menuTotal.setLayout(new GridLayout(2, 1));
        menuTotal.add(menuBotons);
        menuTotal.add(texteMissatge);

        /*----------------------------------------------------------------------
        --DISTRIBUCIÓ
        ----------------------------------------------------------------------*/
        JSplitPane separadorIA = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorIA.setBackground(colorTauler);
        JSplitPane separadorTablero = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorTablero.setBackground(colorTauler);
        JSplitPane separadorMenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separadorMenu.setBackground(colorTauler);

        separadorIA.setTopComponent(taulerJugadorsIA);

        separadorIA.setBottomComponent(taulerBaralla);

        separadorIA.setDividerSize(0);
        separadorTablero.setTopComponent(taulerBaralla);

        separadorTablero.setBottomComponent(taulerUsuari);

        separadorTablero.setDividerSize(0);
        separadorMenu.setTopComponent(taulerUsuari);

        separadorMenu.setBottomComponent(menuTotal);

        separadorMenu.setDividerSize(1);

        contenedor.add(separadorIA, BorderLayout.NORTH);

        contenedor.add(separadorTablero, BorderLayout.CENTER);

        contenedor.add(separadorMenu, BorderLayout.SOUTH);

        //pack();
        setVisible(true);
    }

    /*----------------------------------------------------------------------
        --MÉTODOS
        ----------------------------------------------------------------------*/
    private void mezclarCartas() throws Baralla.NohihaCartes {
        Baralla b = new Baralla();
        b.mescla();
        casillas = new CasillaCarta[4][13];
        cartas = new Carta[4][13];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                casillas[i][j].remove(cartas[i][j].getCarta());
                casillas[i][j].add(b.agafaCarta().getCarta());
            }
        }
    }

    private JLabel actualitzarMaJugadorIA(int cartesRestants, String x) {
        JLabel aux = new JLabel();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("Cartes/" + x + ".png"));
            if (bufferedImage != null) {
                Image imatge = bufferedImage.getScaledInstance(Carta.tamanyCartes[0] + 20, Carta.tamanyCartes[1], Image.SCALE_DEFAULT);
                aux.setIcon(new ImageIcon(imatge));
            }
            aux.setLayout(new FlowLayout(FlowLayout.LEFT, 33, 25));
            JTextArea text_Jugador = new JTextArea(String.valueOf(cartesRestants));
            text_Jugador.setForeground(Color.WHITE);
            text_Jugador.setFont(new Font("Arial", Font.PLAIN, 30));
            text_Jugador.setOpaque(false);
            text_Jugador.setEditable(false);
            aux.add(text_Jugador);
        } catch (IOException ex) {
            Logger.getLogger(TREBALL_FINAL_2022.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }

    /*----------------------------------------------------------------------
        --GESTIÓ D'EVENTS
        ----------------------------------------------------------------------*/
    ActionListener gestorEventos = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evento) {
            switch (evento.getActionCommand()) {
                case "Mescla": {
                    try {
                        mezclarCartas();
                    } catch (Baralla.NohihaCartes ex) {
                        Logger.getLogger(TREBALL_FINAL_2022.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //  repaint();
                break;

                case "Juga":
                    //posar cartes Usuari abaix i cont usuari = 13;
                    //posar cartes blaves i cont = 13 a jugIA;
                    //cambiar menu a pausa i reinicia
                    //cambiar text
                    //cridar metode jugarPartida:
                    //jugador pot pitjar carta per colocar (crear mètode (boolean porTocar)
                    //cambia torn jugador a menu i aixi fins que un guanya...
                    //repaint();
                    break;
                case "Reinicia":
                    //torna a començar tot
                    break;
            }
        }
    };

    private void inici() {
        acabat = false;
        Baralla bar = new Baralla();
        System.out.println("BARALLA FORA MESCLAR: ");
        System.out.println(bar.toString() + "\n");
        bar.mescla();
        for (int i = 0; i < 4; i++) {
            jugadors[i] = new Jugador(i + 1);
            for (int k = 0; k < numCartes; k++) {
                repartirCarta(jugadors[i], bar);
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

    private void repartirCarta(Jugador jug, Baralla bara) {
        try {
            jug.asignarCarta(bara.agafaCarta());
        } catch (Baralla.NohihaCartes ex) {
            System.out.println("NO HAY MÁS CARTAS");
        }
    }

}
