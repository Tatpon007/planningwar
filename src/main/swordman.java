/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Windows 11
 */
public class swordman extends soldier {
    public static String class_car = "sword man" ;
    public static int acttack = 40 ;
    public static int walk = 4 ;
    public Image image[]  = new Image[12];
    public Image mainimage ; 
    public int index_image = 0 ;
    public Image motion ;
    public int index_motion ;
    public boolean haveacttack = false;
    public int frame = 0 ;
    
    swordman(int x1,int y1,String tream1){
        super(x1,y1,tream1,class_car ,acttack,walk) ;
        setimage(tream1) ;
        mainimage = this.image[0] ;
        index_image = 0 ;
        
    }
    public Image getimage(){
        return mainimage ;
    }
    public void drawmotion(Graphics g){
        if(haveacttack){
        if(index_motion == 1){
        g.drawImage(motion,super.getx(), super.gety() + (side/4)*frame, super.side,super.side,null) ;
        }else if(index_motion == 2){
        g.drawImage(motion,super.getx(), super.gety() - (side/4)*frame, super.side,super.side,null) ;
        }else if(index_motion == 3){
        g.drawImage(motion,super.getx()+(side/4)*frame, super.gety(), super.side,super.side,null) ;
        }else if(index_motion == 4){
        g.drawImage(motion,super.getx()-(side/4)*frame, super.gety(), super.side,super.side,null);
        }
        }
    }
    public void motion(int i,int j){
        if(index_image == 0 && j != 4){
            mainimage = image[6] ;
        }else if(index_image == 2 && j != 4){
            mainimage = image[7] ;
        }else if(index_image == 0){
            mainimage = image[0] ;
        }else if(index_image == 2){
            mainimage = image[2] ;
        }
        switch (i) {
            case 1:
                index_motion = i ;
                frame = j ;
                motion = image[11] ;
                break;
            case 2:
                index_motion = i;
                frame = j ;
                motion = image[10] ;
                break;
            case 3:
                index_motion = i;
                frame = j ;
                motion = image[9] ;
                break;
            case 4:
                index_motion = i ;
                frame = j ;
                motion = image[8] ;
                break;
            default:
                break;
        }
        
    
    }
    public void setimage(String tream1){
        try{
            if("red".equals(tream1)){
            image[0]= ImageIO.read(getClass().getResource("/map/swordman_left.png")) ;
            image[1]= ImageIO.read(getClass().getResource("/map/swordman_left_walk.png")) ;
            image[2]= ImageIO.read(getClass().getResource("/map/swordman_right.png")) ;
            image[3]= ImageIO.read(getClass().getResource("/map/swordman_right_walk.png")) ;
            image[4]= ImageIO.read(getClass().getResource("/map/swordman_left_shoose.png")) ;
            image[5]= ImageIO.read(getClass().getResource("/map/swordman_right_choose.png")) ;
            image[6] = ImageIO.read(getClass().getResource("/map/swordman_nowep_right.png")) ;
            image[7] = ImageIO.read(getClass().getResource("/map/swordman_nowep_left.png")) ;
            }else{
            image[0]= ImageIO.read(getClass().getResource("/map/swordman_blue_right.png")) ;
            image[1]= ImageIO.read(getClass().getResource("/map/swordman_blue_right_walk.png")) ;
            image[2]= ImageIO.read(getClass().getResource("/map/swordman_blue_left.png")) ;
            image[3]= ImageIO.read(getClass().getResource("/map/swordman_blue_left_walk.png")) ;
            image[4]= ImageIO.read(getClass().getResource("/map/swordman_blue_right_choose.png")) ;
            image[5]= ImageIO.read(getClass().getResource("/map/swordman_blue_left_choose.png")) ;
            image[6] = ImageIO.read(getClass().getResource("/map/swordman_blue_nowep_rigth.png")) ;
            image[7] = ImageIO.read(getClass().getResource("/map/swordman_blue_nowep_left.png")) ;
            }
            image[8]= ImageIO.read(getClass().getResource("/map/swordman_acttack_right.png")) ;
            image[9]= ImageIO.read(getClass().getResource("/map/swordman_acttack_left.png")) ;
            image[10]= ImageIO.read(getClass().getResource("/map/swordman_acttack_up.png")) ;
            image[11]= ImageIO.read(getClass().getResource("/map/swordman_acttack_down.png")) ;
                    

        }catch(IOException e) { }
    }
    public void walk(int j,int t){
        if((t == 1 && index_image == 2)|| t == 2){
            if(j%2 == 0){
                mainimage = image[2] ;
                
            }else{
                mainimage = image[3] ;
            }
            index_image = 2 ;
        }else{
            if(j%2 == 0){
                mainimage = image[0] ;
            }else{
                mainimage = image[1] ;
            }
            index_image = 0 ;
        }
    }
    public void choose_me(int i){
        if(i == 1){
            if(index_image == 0){
                mainimage = image[4] ;
            }else{
                mainimage = image[5] ;
            }
        }else{
            if(index_image == 0){
                mainimage = image[0] ;
            }else{
                mainimage = image[2] ;
            }
        }
    }
    public void setevent(boolean event){
        haveacttack = event ;
    }
    
}
