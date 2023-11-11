/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Windows 11
 */
import java.net.* ;
import javax.swing.* ;
import java.awt.* ;
import java.io.BufferedReader;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.* ;
public class management_map  {
    private blackground map[] ;
    private GamePanel GP ;
    public int matrix_map[][] ;
    public Image image_staus_bar ;
   
    management_map(GamePanel GP){
       this.GP = GP ;
       map = new blackground[10] ;
       getimage() ;
       matrix_map = new int[GP.max_row][GP.max_column] ;
       loadmap() ;
       
       
    }
    public void getimage(){
        try{
            map[0] = new blackground() ;
            Image img = ImageIO.read(getClass().getResource("/map/green2.png")) ;
            map[0].setImage(img);
            map[1] = new blackground() ;
            img = ImageIO.read(getClass().getResource("/map/moutain.png")) ;
            map[1].setImage(img);
            map[2] = new blackground() ;
            img = ImageIO.read(getClass().getResource("/map/canwalk.png")) ;
            map[2].setImage(img);
            map[3] = new blackground() ;
            img = ImageIO.read(getClass().getResource("/map/canactteck.png")) ;
            map[3].setImage(img);
            image_staus_bar = ImageIO.read(getClass().getResource("/map/statusbar.png")) ;
        }catch(IOException e) { }
    }
    public void loadmap() {
        
        try{
            InputStream is = getClass().getResourceAsStream("/map/map_text.txt") ;
            BufferedReader br = new BufferedReader(new InputStreamReader(is)) ;
            for(int i = 0 ; i < matrix_map.length ; i++){
            String line = br.readLine() ;
            String numberline[] =  line.split(" ") ;
            for(int j = 0 ; j < matrix_map[i].length ; j++){
                matrix_map[i][j] = Integer.parseInt(numberline[j]) ;
                
            }
        }
        }catch(IOException e) {System.out.println(1); }
    }
    public void drawblackground(Graphics g){
        for(int i = 0 ; i < GP.max_row ; i++){
            for(int j = 0 ; j <GP.max_column ;j++){
                g.drawImage(map[matrix_map[i][j]].getImage(), j*GP.objectsize_64, i*GP.objectsize_64, GP.objectsize_64, GP.objectsize_64, GP) ;
            }
        }
        g.drawImage(image_staus_bar,0,GamePanel.heigth_sceen,GamePanel.width_sceen,200,GP) ;
    }
    public int[][] getmatrix_map(){
        return matrix_map ;
    }
}
