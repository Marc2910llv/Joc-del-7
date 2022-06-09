package treball_final_2022;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;
import java.io.File;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
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
    panelBaraja tablero = new panelBaraja();

    public static void main(String[] args) {
        new TREBALL_FINAL_2022().interfici();
    }

    private void interfici() {
        setTitle("Pràctica Prog II - Joc del 7");
        setSize(800, 800);
        setResizable(false);
        setDefaultCloseOperation(TREBALL_FINAL_2022.EXIT_ON_CLOSE);
        contenidor = getContentPane();

        /*----------------------------------------------------------------------
        --TABLERO DE JUEGO
        ----------------------------------------------------------------------*/
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
      
        JPanel menuBotons = new JPanel();
        menuBotons.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        JButton mescla = new JButton(" Mescla ");
        mescla.setBorder(new RoundedBorder(6));
        mescla.setSelected(false);
        menuBotons.add(mescla);
        JButton juga = new JButton("  Juga  ");
        juga.setBorder(new RoundedBorder(6));
        menuBotons.add(juga);
        JButton reinicia = new JButton("Reinicia");
        reinicia.setBorder(new RoundedBorder(6));
        menuBotons.add(reinicia);
        
        JTextArea texteMissatge = new JTextArea();
        texteMissatge.setEditable(false);
        texteMissatge.setText("             puto");
        

        /*----------------------------------------------------------------------
        --DISTRIBUCIÓ
        ----------------------------------------------------------------------*/
        JSplitPane separadorTablero = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separadorMenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        separadorTablero.setTopComponent(tablero);
        separadorTablero.setBottomComponent(menuBotons);
        separadorMenu.setTopComponent(menuBotons);
        separadorMenu.setBottomComponent(texteMissatge);
        

        contenidor.add(separadorTablero, BorderLayout.CENTER);
        contenidor.add(separadorMenu, BorderLayout.SOUTH);

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
   
    
    private class panelBaraja extends JPanel {

        public panelBaraja() {

        }
        
         @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //PINTAR TABLERO
            //hacemos un rectangulo relleno que ocupe todo el JPanel naranja
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0,110,0));
            Rectangle2D rectangulo = new Rectangle2D.Double(0, 0, 800, 800);
            g.drawRect(0, 0, 55, 80);
            g2d.fill(rectangulo);
        }
    }
    
   

}
