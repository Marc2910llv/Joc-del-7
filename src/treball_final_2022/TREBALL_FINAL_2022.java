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
    private JButton mescla, juga, reinicia, passa, tornJugador;
    private final JLabel[] cartesJugadorIA = new JLabel[3];
    private final JPanel taulerUsuari = new JPanel();
    private final JPanel taulerBaralla = new JPanel();
    private JPanel menuBotons;
    private final JPanel menuTotal = new JPanel();
    private final JSplitPane separadorIA = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JSplitPane separadorTablero = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JSplitPane separadorMenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JTextArea texteMissatge = new JTextArea();

    private final Jugador[] jugadorsIA = new Jugador[3];
    private final Jugador jugadorUsuari = new Jugador();
    private Carta[] cartesUsuari;
    private boolean acabat;
    private final int numCartes = 13;
    public Tauler tauler;
    private Baralla baralla;
    private int tornIA = 0;

    public static void main(String[] args) throws IOException {
        new TREBALL_FINAL_2022().interfici();
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
        cartesJugadorIA[0] = new JLabel();
        actualitzarMaJugadorIA(0, "fondo_casella", cartesJugadorIA[0]);
        jugadorsIA[0] = new Jugador();
        cartesJugadorIA[1] = new JLabel();
        actualitzarMaJugadorIA(0, "fondo_casella", cartesJugadorIA[1]);
        jugadorsIA[1] = new Jugador();
        cartesJugadorIA[2] = new JLabel();
        actualitzarMaJugadorIA(0, "fondo_casella", cartesJugadorIA[2]);
        jugadorsIA[2] = new Jugador();

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
        taulerJugadorsIA.add(cartesJugadorIA[0]);
        taulerJugadorsIA.add(cartesJugadorIA[1]);
        taulerJugadorsIA.add(cartesJugadorIA[2]);
        taulerJugadorsIA.add(new JLabel());

        ///////////////////////////TAULER - BARALLA/////////////////////////////
        taulerBaralla.setBackground(colorTauler);
        taulerBaralla.setLayout(new GridLayout(4, 13));
        tauler = new Tauler();
        baralla = new Baralla();
        mostrarTaulerMesclat(false);

        ////////////////////////////JUGADOR USUARI//////////////////////////////
        Carta[] v = new Carta[13];
        v[0] = new Carta(true);
        for (int i = 1; i < v.length; i++) {
            v[i] = new Carta(false);
        }
        taulerUsuari.setBackground(colorTauler);
        taulerUsuari.setLayout(new GridBagLayout());
        actualitzarMaJugadorUsuari(0, v);
        ////////////////////////////////////////////////////////////////////////

        /*----------------------------------------------------------------------
        --MENU INFERIOR
        ----------------------------------------------------------------------*/

 /*-----------------------------------------------------------------------------
        --GESTIÓ D'EVENTS
        ----------------------------------------------------------------------*/
        ActionListener gestorEventos = (ActionEvent evento) -> {
            switch (evento.getActionCommand()) {
                case "Mescla": {
                    mostrarTaulerMesclat(true);
                    juga.setEnabled(true);
                    reinicia.setEnabled(true);
                    break;
                }
                case "Juga": {
                    tauler = new Tauler();
                    iniciJoc();
                    mostrarMenuCorresponent(passa, null);
                    break;
                }
                case "Reinicia": {
                    try {
                        new TREBALL_FINAL_2022().interfici();
                    } catch (IOException ex) {
                        Logger.getLogger(TREBALL_FINAL_2022.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "Passa": {
                    actualitzarText("Has passat");
                    mostrarMenuCorresponent(tornJugador, null);
                    break;
                }
                case "Torn Jugador": {
                    Carta posada = jugadorsIA[tornIA].treureCarta(tauler);
                    actualitzarMaJugadorIA(jugadorsIA[tornIA].getNumCartas(),
                            "card_back_blue", cartesJugadorIA[tornIA]);
                    if (jugadorsIA[tornIA].getNumCartas() == 0) {
                        acabat = true;
                    }
                    tornIA++;
                    if (posada != null) {
                        actualitzarText("El Jugador " + tornIA + " ha posat el " + posada.toString());
                    } else {
                        actualitzarText("El Jugador" + tornIA + " passa");
                    }
                    if (tornIA == 3) {
                        tornIA = 0;
                        mostrarMenuCorresponent(passa, null);
                    }
                    actualitzarTauler(tauler.tauler);
                    break;
                }

            }
        };

        menuBotons = new JPanel();

        mescla = new JButton("Mescla");
        mescla.addActionListener(gestorEventos);
        mescla.setBorder(new RoundedBorder(6));

        juga = new JButton("Juga");
        juga.addActionListener(gestorEventos);
        juga.setBorder(new RoundedBorder(6));
        juga.setEnabled(false);

        reinicia = new JButton("Reinicia");
        reinicia.addActionListener(gestorEventos);
        reinicia.setBorder(new RoundedBorder(6));
        reinicia.setEnabled(false);

        passa = new JButton("Passa");
        passa.addActionListener(gestorEventos);
        passa.setBorder(new RoundedBorder(6));

        tornJugador = new JButton("Torn Jugador");
        tornJugador.addActionListener(gestorEventos);
        tornJugador.setBorder(new RoundedBorder(6));

        mostrarMenuCorresponent(mescla, juga);

        texteMissatge.setFocusable(false);
        texteMissatge.setText("Abans de jugar cal mesclar la baralla");

        menuTotal.setLayout(new GridLayout(2, 1));
        menuTotal.add(menuBotons);
        menuTotal.add(texteMissatge);

        /*----------------------------------------------------------------------
        --DISTRIBUCIÓ
        ----------------------------------------------------------------------*/
        separadorIA.setBackground(colorTauler);
        separadorTablero.setBackground(colorTauler);
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

        setVisible(true);
    }

    /*----------------------------------------------------------------------
        --MÈTODES
        ----------------------------------------------------------------------*/
    private void actualitzarText(String a) {
        texteMissatge.setText(a);
        contenedor.repaint();
    }

    private void actualitzarTauler(Carta[][] c) {
        taulerBaralla.removeAll();
        separadorIA.setBottomComponent(taulerBaralla);
        separadorTablero.setTopComponent(taulerBaralla);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (c[i][j] == null) {
                    taulerBaralla.add(new CasillaCarta());
                } else {
                    taulerBaralla.add(new CasillaCarta(c[i][j].carta));
                }
            }
        }

        contenedor.repaint();
    }

    private void mostrarTaulerMesclat(boolean mezclado) {
        taulerBaralla.removeAll();
        separadorIA.setBottomComponent(taulerBaralla);
        separadorTablero.setTopComponent(taulerBaralla);
        if (mezclado) {
            baralla.mescla();
            Carta[] cartesBaralla = baralla.getB();
            int index = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    taulerBaralla.add(new CasillaCarta(cartesBaralla[index].carta));
                    index++;
                }
            }
            actualitzarText("La baralla està mesclada");
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 13; j++) {
                    taulerBaralla.add(new CasillaCarta(new Carta(Pal.values()[i],
                            j + 1).carta));
                }
            }
        }
        contenedor.repaint();
    }

    private void actualitzarMaJugadorIA(int cartesRestants, String x, JLabel aux) {
        aux.removeAll();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("Cartes/" + x
                    + ".png"));
            if (bufferedImage != null) {
                Image imatge = bufferedImage.getScaledInstance(
                        Carta.tamanyCartes[0] + 20, Carta.tamanyCartes[1],
                        Image.SCALE_DEFAULT);
                aux.setIcon(new ImageIcon(imatge));
            }
            aux.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            JTextArea text_Jugador = new JTextArea(String.valueOf(cartesRestants));
            text_Jugador.setForeground(Color.WHITE);
            text_Jugador.setFont(new Font("Arial", Font.CENTER_BASELINE, 55));
            text_Jugador.setOpaque(false);
            text_Jugador.setEditable(false);
            aux.add(text_Jugador);
        } catch (IOException ex) {
            Logger.getLogger(TREBALL_FINAL_2022.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        contenedor.repaint();
    }

    private void actualitzarMaJugadorUsuari(int cartesRestants, Carta[] c) {
        taulerUsuari.removeAll();
        separadorTablero.setBottomComponent(taulerUsuari);
        separadorMenu.setTopComponent(taulerUsuari);

        GridBagConstraints restricciones = new GridBagConstraints();

        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.anchor = GridBagConstraints.PAGE_END;
        restricciones.insets = new Insets(0, 8, 0, 0);
        restricciones.gridx = 0;
        restricciones.gridy = 1;
        restricciones.ipady = 5;
        restricciones.ipadx = 0;
        restricciones.weightx = 0.1;
        restricciones.weighty = 0;

        taulerUsuari.add(c[0].carta, restricciones);

        for (int i = 1; i < 13; i++) {
            restricciones.gridx = restricciones.gridx + 1;
            taulerUsuari.add(c[i].carta, restricciones);
        }

        JTextArea auxx = new JTextArea(String.valueOf(cartesRestants));
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
        restricciones.insets = new Insets(0, 28, 0, 0);
        restricciones.gridwidth = 2;
        taulerUsuari.add(auxx, restricciones);
        contenedor.repaint();
    }

    private void iniciJoc() {
        acabat = false;
        actualitzarTauler(tauler.tauler);
        for (int i = 0; i < numCartes; i++) {
            jugadorUsuari.asignarCarta(baralla.agafaCarta());
        }
        cartesUsuari = jugadorUsuari.getArrayCartasAsignadas();
        actualitzarMaJugadorUsuari(jugadorUsuari.getNumCartas(), cartesUsuari);
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < numCartes; k++) {
                jugadorsIA[i].asignarCarta(baralla.agafaCarta());
            }
            actualitzarMaJugadorIA(jugadorsIA[i].getNumCartas(), "card_back_blue",
                    cartesJugadorIA[i]);
        }
        contenedor.repaint();
        actualitzarText(
                "Les cartes estàn repartides, és el teu torn, posa un 7 si el tens");
    }

    private void mostrarMenuCorresponent(JButton jb1, JButton jb2) {
        menuBotons.removeAll();
        menuBotons = new JPanel();
        menuBotons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        if (jb2 != null) {
            menuBotons.add(jb1);
            menuBotons.add(jb2);
        } else {
            menuBotons.add(jb1);
        }
        menuBotons.add(reinicia);
        menuTotal.removeAll();
        menuTotal.add(menuBotons);
        menuTotal.add(texteMissatge);
        separadorMenu.setBottomComponent(menuTotal);
        contenedor.repaint();
    }
}
