/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Windows 11
 */
public class central extends soldier {
static int HP = 500 ;
int coisn = 0 ;
String tream ;
public boolean ability_summon = true ;
public Image mainimage ; 
public int getcoins(){
    return coisn ;
}
public void setcoins(int coins){
    this.coisn = coins ;
}
    public central(int x, int y, String tream) {
        super(x, y, tream,HP);
        this.tream = tream ;
        setimage(tream) ;
    }
@Override
    public Image getimage(){
        return mainimage ;
    }
    public void setimage(String tream){
        try{
        if(tream.equals("red")){
            mainimage = ImageIO.read(getClass().getResource("/map/castle_red.png")) ;
        }else{ 
            mainimage = ImageIO.read(getClass().getResource("/map/castle_blue.png")) ;
        }
        }catch(IOException e) { }
    }
 @Override
 public void showstatus(Graphics g) {
        g.setColor(Color.BLACK);
        int fontSize = 20;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g.setFont(f);
        g.fillRect(50, 0, 250, 20);
        g.setColor(Color.red);
        g.fillRect(50,0,super.HP/2,20) ;
        g.setColor(Color.WHITE) ;
        g.drawString(Integer.toString(super.HP)+"/500", 120, 15);
        g.drawString("HP", 0, 20);
        g.drawString("castle of tream " +  tream,0,40) ; 
        g.drawString("coin of castle  "  + coisn,0,60) ;
        g.drawString("coin will add after endturn +1 ",0,80) ;
        g.drawString("2 coin to Big swordman ,1 coin to swordman", 0, 100);
    
    }
 public boolean get_ability_summon(){
    return ability_summon ;
}
public void set_ability_summon(boolean ability_summon){
    this.ability_summon = ability_summon ;
    
}
}
