/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import util.units.unitTypes.DirectionType;

/**
 *
 * @author ovatman
 */
public class MagneticVariation {
    private double variation;
    private DirectionType dir;

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }

    public DirectionType getDir() {
        return dir;
    }

    public void setDir(DirectionType dir) {
        this.dir = dir;
    }
    
    
}