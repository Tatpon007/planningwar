/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Windows 11
 */
public class StatusGraphic extends JPanel {
    soldier  eventsoldier ;
    StatusGraphic(soldier sol){
        this.eventsoldier = sol ;
    }
    @Override
    public void paintComponent(Graphics g){
        eventsoldier.showstatus(g);
    }
}
