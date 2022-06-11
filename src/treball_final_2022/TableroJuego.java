/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treball_final_2022;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class TableroJuego extends JLabel {

    JLabel casilla;

    public TableroJuego() {
        casilla = new JLabel();
    }

    public JLabel crearCasilla() {
        casilla = new JLabel();
        casilla.setBorder(javax.swing.BorderFactory.createLineBorder((new Color(0,110,0)),2));
        casilla.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        casilla.setBackground(new Color(0, 82, 0));
        casilla.setOpaque(true);
        return casilla;
    }
    
 

}
//    Carta[][] Tauler;
//
//    public TableroJuego() {
//        Tauler = new Carta[4][13];
//    }
//
//    public Carta[][] crearTablero() {
//
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 13; j++) {
//                Tauler[i][j].setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
//                Tauler[i][j].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
//                Tauler[i][j].setBackground(new Color(0, 82, 0));
//                Tauler[i][j].setOpaque(true);
//            }
//        }
//        return Tauler;
//    }
//
//    public void introducirCarta(Carta c, int i, int j) {
//        Tauler[i][j] = c;
//    }

