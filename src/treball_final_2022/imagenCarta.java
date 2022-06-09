/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treball_final_2022;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author carlo
 */
public class imagenCarta extends JPanel {
      String image;

    public imagenCarta(String img) {
        this.setSize(55, 80);
        image = img;
    }
      @Override
    public void paint(Graphics g) {
        Dimension height = getSize();
        ImageIcon icon = new ImageIcon(getClass().getResource("Cartes/" + image + ".png"));
        Image imagen = icon.getImage();
        Image newImage = imagen.getScaledInstance(55, 80, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        g.drawImage(icon.getImage(), 0, 0, height.width, height.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }
}

