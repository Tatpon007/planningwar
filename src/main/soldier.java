/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.* ;
import java.awt.* ;
import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Windows 11
 */
public class soldier {
    private String class_char ;
    private int x = 0;
    private int y = 0;
    public int side = 16 * 3 ;
    private boolean priority = false ;
    private int position[] = {0,0} ;
    public int HP = 100 ; 
    private int attack  ;
    private int walk ;
    private boolean ability_walk = true ;
    public String tream = "" ;
    private boolean ability_acttack = true ;
    public int getwalk(){
        return walk ;
    }
    public int getHp(){
        return HP ;
    }
    public void downHP(int damange){
        this.HP -= damange ;
    }
    public int acttaek(soldier r){
        r.downHP(attack) ;
        return r.getHp() ;
    }
    soldier(int x,int y,String tream){
        this.tream = tream ;
        setxy(x*side,y*side) ;
    }
    soldier(int x,int y,String tream ,String class_car ,int attack,int walk){
        this.class_char = class_car ;
        this.attack = attack ;
        this.walk = walk ;
        this.tream = tream ;
        setxy(x*side,y*side) ;
    }
    soldier(int x,int y,String tream ,int HP){
        this.tream = tream ;
        setxy(x*side,y*side) ;
        this.HP = HP ;
    }
    public void set_abilitty_acttack(boolean ability_acttack){
        this.ability_acttack = ability_acttack ;
    }
    public boolean get_abilitty_acttack(){
        return ability_acttack ;
    }
    public void set_abilitty_walk(boolean ability_walk){
        this.ability_walk = ability_walk ;
    }
    public boolean get_abilitty_walk(){
        return ability_walk ;
    }
    public int getx(){
        return x ;
        
    }
    public int gety(){
        return y ;
    }
    
    public void setxy(int x , int y){
        this.x = x ;
        this.y = y ;
        this.position[0] = this.y/side ;
        this.position[1] = this.x/side ;
    }
    public int[] getposition(){
        return position ;
    }
    public void setpriority(boolean priority){
        this.priority = priority ;
    }
    public boolean getpriority(){
        return priority ;
    }
    public void showstatus(Graphics g) {
        g.setColor(Color.BLACK);
        int fontSize = 20;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g.setFont(f);
        g.fillRect(50, 0, 200, 20);
        g.setColor(Color.red);
        g.fillRect(50,0,HP*2,20) ;
        g.setColor(Color.WHITE) ;
        g.drawString(Integer.toString(HP)+"/100", 120, 15);
        g.drawString("HP", 0, 20);
        g.drawString("Acttect : " + attack , 0,40) ;
        g.drawString("walk : " + walk +" block" , 0,60) ;
        g.drawString("Class : "+ class_char, 0, 80);
    
    }
    public Image getimage(){
        Image image = null ;
        return image ;
    }
    public void walk(int j,int t){
        ;
    }
    public void choose_me(int i){
        ;
    }
    public void setimage() {
        
}
    public void motion(int i,int j){
        
    }
    public void drawmotion(Graphics g){
        
    }
    public void setevent(boolean event){
        
    }
    public int getcoins(){
    return 0 ;
}
public void setcoins(int coins){
    
}
public boolean get_ability_summon(){
    return true ;
}
public void set_ability_summon(boolean sbility_summon){
    
}
}
