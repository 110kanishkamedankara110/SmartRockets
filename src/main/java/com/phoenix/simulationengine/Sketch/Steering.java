package com.phoenix.simulationengine.Sketch;

import com.phoenix.simulationengine.Shapes.Goal;
import com.phoenix.simulationengine.Shapes.Obstical;
import com.phoenix.simulationengine.Shapes.Vehical;
import com.phoenix.simulationengine.util.Dna;
import com.phoenix.simulationengine.util.Sketch;
import com.phoenix.simulationengine.util.Vector;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class Steering implements Sketch {

    Vehical v[];
    private static Obstical o;
    private static Goal g;
    Random random = new Random();
    List<Dna> dnaPool;

    int life = 500;

    @Override
    public void setup(JPanel frame) {
        v = new Vehical[100];
        dnaPool = new LinkedList();
        for (int i = 0; i < v.length; i++) {
            v[i] = new Vehical(frame);
            frame.add(v[i]);
        }
        g = new Goal(new Vector(frame.getWidth() / 2, 70));
        frame.add(g);

        o = new Obstical(new Vector(frame.getWidth() / 2, 180));
        frame.add(o);
    }

    @Override
    public void update(JPanel frame) {

        if (life == 0) {

            for (int i = 0; i < v.length; i++) {
                int p = v[i].getPoints();
                if (p != 0) {
                    for (int j = 0; j < p; j++) {
                        dnaPool.add(v[i].getDna());
                    }
                }

            }

            v = new Vehical[100];
            for (int i = 0; i < v.length; i++) {

                Dna parent1 = dnaPool.get(random.nextInt(dnaPool.size()));
                Dna parent2 = null;
                while ((parent2 = dnaPool.get(random.nextInt(dnaPool.size()))) != parent1) {
                    break;
                }

                Dna newDna = parent1.crossover(parent2);
                newDna.mutate();

                v[i] = new Vehical(frame);
                v[i].setDna(newDna);

                frame.add(v[i]);
            }
            life = 500;
            dnaPool = new LinkedList();
        }
        for (int i = 0; i < v.length; i++) {
            v[i].update();
        }
        life--;

    }

    public static Obstical getObstical() {
        return o;
    }

    public static Goal getG() {
        return g;
    }

}
