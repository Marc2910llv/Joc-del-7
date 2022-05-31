/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treball_final_2022;

/**
 *
 * @author Carlos Lozano, Marc Llobera
 */
public class Carta {

    private final Pal pal;
    private final int num;

    // CONSTRUCTOR
    Carta(Pal pal, int i) {
        this.pal = pal;
        this.num = i;
    }

    @Override
    public String toString() {
        if (num <= 10) {
            if (num == 1) {
                return "[Carta: As, Pal:" + pal + "]";
            }
            return "[Carta: " + num + ", Pal:" + pal + "]";
        } else {
            switch (num) {
                case 11:
                    return "[Carta: J, Pal:" + pal + "]";
                case 12:
                    return "[Carta: Q, Pal:" + pal + "]";
                case 13:
                    return "[Carta: K, Pal:" + pal + "]";
            }
        }
        return "ERROR";
    }

    public int getNum() {
        return num;
    }

    public Pal getPal() {
        return pal;
    }
}
