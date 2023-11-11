/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Windows 11
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.* ;
import static main.GamePanel.heigth_sceen;
import static main.GamePanel.width_sceen;

public class main extends JFrame{
    
    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame window = new main() ;
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new GamePanel()) ;
        window.pack();
        window.setTitle("Planning war");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
       
        
    }



}
