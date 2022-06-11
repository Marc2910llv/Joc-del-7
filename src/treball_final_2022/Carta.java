/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treball_final_2022;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class Carta extends JLabel {

    JLabel carta;

    // CONSTRUCTOR
    public Carta() {
        carta = new JLabel();
    }

    public JLabel crearCarta(Image im) {
        carta = new JLabel();
        carta.setIcon(new ImageIcon(im));
        carta.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        return carta;
    }

    public JLabel getCarta() {
        return carta;
    }

}
