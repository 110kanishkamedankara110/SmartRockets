/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phoenix.simulationengine.util;

import java.util.Random;

/**
 *
 * @author BLACKBOX
 */
public class Dna {

    private final Vector genes[] = new Vector[500];
    float muattionRate = 0.05f;
    Random random = new Random();

    public Dna() {

        for (int i = 0; i < genes.length; i++) {
            if (i == 1) {
                genes[i] = new Vector(-1 + (1 - (-1)) * random.nextDouble(), -1);
            } else {
                genes[i] = new Vector(-1 + (1 - (-1)) * random.nextDouble(), -1 + (1 - (-1)) * random.nextDouble());
            }
            genes[i].setMag(0.1);
        }
    }

    public Vector[] getGenes() {
        return genes;
    }

    public Dna crossover(Dna partner) {
        Dna child = new Dna();

        int midpoint = Math.abs(this.genes.length / 2);
        for (int i = 0; i < this.genes.length; i++) {
            child.genes[i] = (i > midpoint) ? this.genes[i] : partner.genes[i];
            child.genes[i].setMag(0.1);

        }
        return child;
    }

    public void mutate() {
        for (int i = 0; i < genes.length; i++) {
            if (Math.random() < muattionRate) {
                genes[i] = new Vector(-1 + (1 - (-1)) * random.nextDouble(), -1 + (1 - (-1)) * random.nextDouble());
                genes[i].setMag(0.1);
            }
        }
    }
}
