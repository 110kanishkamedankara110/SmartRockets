package com.phoenix.simulationengine.Shapes;

import com.phoenix.simulationengine.Sketch.Steering;
import com.phoenix.simulationengine.util.Dna;
import com.phoenix.simulationengine.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Vehical extends JComponent {

    Vector pos = new Vector(0, 0);
    Vector acc = new Vector(0, 0);
    Vector vel = new Vector(0, 0);
    JPanel f;
    int count = 0;
    int points = 100;
    boolean dead = false;
    Dna dna = new Dna();
    private static final int TRIANGLE_LENGTH = 30;
    private static final int TRIANGLE_WIDTH = 10;

    boolean hit = false;

    public Vehical(JPanel frame) {
        this.f = frame;
        pos.x = f.getWidth() / 2;
        pos.y = f.getHeight() - 20;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!dead) {
//            if (pos.x > f.getWidth()) {
//                pos.x = f.getWidth() - 10;
//                hit = true;
//                points /= 2;
//            }
            if (pos.y > f.getHeight()) {
                pos.y = f.getHeight();
                hit = true;
                points = 0;
            }
//            if (pos.x < 0) {
//                pos.x = 0;
//                hit = true;
//                points /= 2;
//            }
            if (pos.y < 0) {
                pos.y = 0;
                hit = true;
            }

            if (pos.x > Steering.getObstical().leftMargin && pos.x < Steering.getObstical().rightMargin && pos.y < Steering.getObstical().bottomMargin && pos.y > Steering.getObstical().topMargin) {
                hit = true;
                points = 0;
            }

            if (pos.x > Steering.getG().leftMargin && pos.x < Steering.getG().rightMargin && pos.y < Steering.getG().bottomMargin && pos.y > Steering.getG().topMargin) {
                hit = true;
                points *= 10 / (count + 1);
            }

            Graphics2D g2d = (Graphics2D) g;
            AffineTransform oldTransform = g2d.getTransform();

            g2d.translate(pos.x, pos.y);

            double angle = Math.atan2(vel.y, vel.x);
            g2d.rotate(angle);
            int[] xPoints = {0, 0, TRIANGLE_LENGTH};
            int[] yPoints = {-TRIANGLE_WIDTH / 2, TRIANGLE_WIDTH / 2, 0};
            g2d.setColor(new Color(255, 255, 255, 150));
            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.setColor(new Color(0,0,0,200));
            g2d.drawPolygon(xPoints, yPoints, 3);
            g2d.setTransform(oldTransform);
        }

    }

    public void update() {
        if (!hit) {
            applyForce(dna.getGenes()[count]);
            acc.limit(0.3);
            vel.add(acc);
            pos.add(vel);
            acc.mult(0);
        }

        if (count >= dna.getGenes().length - 1) {
            Vector goal = Steering.getG().pos;
            points /= ((Vector.dist(goal, pos) + 1) / 100);
            dead = true;
        } else {
            count++;
        }

        repaint();

    }

    public void setDna(Dna dna) {
        this.dna = dna;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void applyForce(Vector v) {
        acc.add(v);
    }

    public Dna getDna() {
        return dna;
    }

}
