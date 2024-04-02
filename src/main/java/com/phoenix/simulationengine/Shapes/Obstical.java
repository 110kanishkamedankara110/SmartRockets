/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoenix.simulationengine.Shapes;

import com.phoenix.simulationengine.Sketch.Steering;
import com.phoenix.simulationengine.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author BLACKBOX
 */
public class Obstical extends JComponent {

    public Vector pos = new Vector(0, 0);
    public int width = 300;
    public int height = 50;

    public int leftMargin;
    public int rightMargin;

    public int topMargin;
    public int bottomMargin;

    public Obstical(Vector pos) {
        this.pos = pos;

        leftMargin = ((int) pos.x - (width / 2));
        rightMargin = ((int) pos.x + (width / 2));

        topMargin = ((int) pos.y - (height / 2));
        bottomMargin = ((int) pos.y + (height / 2));

      

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect((int) pos.x - width / 2, (int) pos.y - height / 2, width, height);
    }

}
