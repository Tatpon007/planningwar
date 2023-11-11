/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Windows 11
 */
import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.* ;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.* ;
import javax.swing.event.AncestorListener;
public class GamePanel extends JPanel {
    public Image backgourd  =ImageIO.read(getClass().getResource("/map/blackgourd_start.png")) ;
    public int chackwin = 0 ;
    public Image win[] = new Image[2] ;
    public int summontype ;
    public boolean tostart = false ;
    public int turn_ = 0 ;
    public Image turnof[] = new Image[2] ;
    public static String turn[] = new String[2] ;
    public static int originalsize = 16 ;
    public static int scale = 3 ;
    public ArrayList<soldier> sol  = new ArrayList<soldier>() ;
    public static int objectsize_64 = originalsize * scale ; //64 * 64
    public static int max_row = 12 ;
    public static int max_column = 16 ;
    public static int width_sceen = max_column * objectsize_64 ; 
    public static int heigth_sceen = max_row * objectsize_64 ;
    public management_map map ;
    public int matrix_map[][] ;
    private JPanel statusbar_panel = new JPanel() ;
    public int status = 0 ;
    public JButton start = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/map/start.png")))) ;
    public MouseListener chack ;
    JLabel imageback_ground = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/map/statusbar2.png")))) ;
    
    public JButton WalkButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/map/Walk.png")))) ;
    public JButton ActteckButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/map/ACTTACK.png")))) ;
    public JButton Summon_sword = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/map/summond_sword.png")))) ;
    public JButton Summon_bigsword = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/map/summond_big_sword.png")))) ;
    Thread panelgame = new Thread(new Runnable(){
    @Override
    public void run(){
        while(true){
            chackwin = 0 ;
            while(true){
            turn[0] = "red" ;
            turn[1] = "blue" ;
            
            try{
            Thread.sleep(300);
            chackwhowin() ;
            setstatuspanel();
            repaint() ;
        }catch(Exception e) {}
            
        }
        }
        
        } } )  ;
    public void chackwhowin() throws InterruptedException{
        int red = 0 ;
        int blue = 0 ;
        for(int i = 0 ; i < sol.size() ;i++){
            if(sol.get(i).tream.equals("red") && (sol.get(i) instanceof central)){
                red++ ;
            }else if(sol.get(i).tream.equals("blue") && (sol.get(i) instanceof central)){
                blue++ ;
            }
        }
        if(red == 0 ){
            setstatuspanel();
            chackwin = 1 ;
            repaint() ;
            panelgame.sleep(1500) ;
            tostart = false ;
            sol = new ArrayList<soldier>() ;
            add_sol() ;
            start.setVisible(true);
            statusbar_panel.setVisible(false);
            chackwin = 0 ;
            
        

        }else if(blue == 0){
            setstatuspanel();
            chackwin = 2 ;
            repaint() ;
            panelgame.sleep(1500) ;
            tostart = false ;
            sol = new ArrayList<soldier>() ;
            add_sol() ;
            start.setVisible(true);
            statusbar_panel.setVisible(false);
            chackwin = 0 ;
            

        }
    }
    public void add_sol(){
        sol.add(new central(0,0,"red")) ;
        sol.add(new central(10,6,"blue")) ;
    }
    
    public void GamePanelStart(){
        
        tostart = true ;
        start.setVisible(false);
        if(turn_ == 0) {
        
        this.addMouseListener(chack);
        map = new management_map(this) ;
        matrix_map = map.getmatrix_map() ;
        statusbar_panel.setLayout(null) ;
        imageback_ground.setBounds(0, 0, width_sceen, 200);
        statusbar_panel.add(imageback_ground) ;
        statusbar_panel.setBounds(0, heigth_sceen, width_sceen, 200);
        this.add(statusbar_panel) ;
        sol.add(new central(0,0,"red")) ;
        sol.add(new swordman(0,3,"red")) ;
        sol.add(new bigswordman(0,6,"blue")) ;
        sol.add(new bigswordman(0,1,"blue")) ;
        sol.add(new central(10,6,"blue")) ;
        panelgame.start();
        }
        turn_ = 0 ;
        
      
        
        
    }
    public void StartPanel(){
        tostart = false ;
        chackwin = 0 ;
        if(turn_ != 0){
            start.setVisible(true);
        }
        repaint() ;
        start.setBounds(width_sceen/2-50, (heigth_sceen+200)/2 -25, 100, 50);
        this.add(start);
        
    }
    GamePanel() throws IOException{
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tostart = true ;
                statusbar_panel.setVisible(true);
                GamePanelStart() ;
            }
        });
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width_sceen,heigth_sceen+200)) ;
        this.setBackground(Color.BLACK) ;
        StartPanel() ;
        try{
        win[0] = ImageIO.read(getClass().getResource("/map/win_redtream.png")) ;
        win[1] = ImageIO.read(getClass().getResource("/map/win_bluetream.png")) ;
        turnof[0] = ImageIO.read(getClass().getResource("/map/TURNRED.png")) ;
        turnof[1] = ImageIO.read(getClass().getResource("/map/TURNBLUE.png")) ;
    }catch(IOException e){
        
    }
        chack = new MouseListener (){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() ;
                int y = e.getY() ;
                int row = y/objectsize_64 ;
                int column = x/objectsize_64 ;
                System.out.println("x : "+e.getX() + " y : " +e.getY()) ;
                int chack = chack_if_in_event() ;
                if(chack >= 0){
                    
                    if(sol.get(chack).getposition()[0] == row && sol.get(chack).getposition()[1] == column){
                           sol.get(chack).setpriority(false);
                           setstatuspanel() ;
                           sol.get(chack).choose_me(0) ;
                           status = 0 ;
                           setmap(status) ;
                    }else{
                        boolean avaliable_towalk = false ;
                        if(status == 1){
                        for(int i = 0 ; i < waywalk.size() ;i++){
                            if(waywalk.get(i).get(0) == row && waywalk.get(i).get(1) == column){
                                avaliable_towalk = true ;
                                break ;
                            }
                        }
                        if(avaliable_towalk){
                            sol.get(chack).set_abilitty_walk(false) ;
                            try{
                            walk(sol.get(chack).getwalk(),sol.get(chack),row,column) ;
                            }catch(InterruptedException ex) {}  
                            finally{sol.get(chack).setpriority(false);
                            setstatuspanel() ;
                            status = 0 ;
                            setmap(status) ;
//                          sol.get(chack).setxy(column*objectsize_64, row*objectsize_64);
                            }
                        }
                        }else if(status == 2){
                           for(int i = 0 ; i < way_acttack.size() ;i++){
                            if(way_acttack.get(i).get(0) == row && way_acttack.get(i).get(1) == column){
                                avaliable_towalk = true ;
                                break ;
                            }
                        }
                        if(avaliable_towalk){
                            sol.get(chack).set_abilitty_acttack(false) ;
                               try {
                                   motion_acttack(sol.get(chack),row,column) ;
                               } catch (InterruptedException ex) {
                               }
                            finally{
                                   sol.get(chack).setpriority(false);
                                   setstatuspanel() ;
                                   status = 0 ;
                                   setmap(status) ;
                                   for(int i = 0 ;i < sol.size() ;i++){
                                if(sol.get(i).getposition()[0] == row && sol.get(i).getposition()[1] == column && !sol.get(i).tream.equals(turn[turn_%2])){
                                    if(sol.get(chack).acttaek(sol.get(i)) <= 0){
                                        sol.remove(i);
                                        break ;
                                    }
                                   
                                }
                            }
                            }
                            
//                          sol.get(chack).setxy(column*objectsize_64, row*objectsize_64);
                        }
                        }else{
                            for(int i = 0 ; i < waysummond.size() ;i++){
                            if(waysummond.get(i).get(0) == row && waysummond.get(i).get(1) == column){
                                avaliable_towalk = true ;
                                break ;
                            }
                        }
                        if(avaliable_towalk){
                            if(summontype == 1){
                                sol.add(new swordman(column,row,turn[turn_%2])) ;
                                sol.get(chack).setcoins(sol.get(chack).getcoins()-1);
                                sol.get(chack).set_ability_summon(false);
                            }else if(summontype == 2){
                                sol.add(new bigswordman(column,row,turn[turn_%2])) ;
                                sol.get(chack).setcoins(sol.get(chack).getcoins()-3);
                                sol.get(chack).set_ability_summon(false);
                            }
//                            sol.get(chack).set_abilitty_walk(false) ;
//                            try{
//                            walk(sol.get(chack).getwalk(),sol.get(chack),row,column) ;
//                            }catch(InterruptedException ex) {}  
//                            finally{
                            sol.get(chack).setpriority(false);
                            setstatuspanel() ;
                            status = 0 ;
                            setmap(status) ;
//                          sol.get(chack).setxy(column*objectsize_64, row*objectsize_64);
//                            }
                        }
                        }
                        
                    }
                }
                else{
                    for(int i = 0 ; i < sol.size() ; i++){
                        if(sol.get(i).getposition()[0] == row && sol.get(i).getposition()[1] == column){
                           sol.get(i).setpriority(true);
                           setstatuspanel() ;
                           
                        }
                    }
                }
            }
                
         
            
    
            public void mouseEntered(MouseEvent e) {
       
            }


            public void mouseExited(MouseEvent e) {
       
            }


            public void mousePressed(MouseEvent e) {
       
            }


            public void mouseReleased(MouseEvent e) {
       
            }
        
        } ;
         
        
    }
    
    
    public void motion_acttack(soldier s,int row,int column) throws InterruptedException{
       Thread acttack = new Thread(new Runnable(){
           @Override
           public void run() {
               
               try{
                   System.out.println(s.getposition()[0]+" "+s.getposition()[1]) ;
                   System.out.println(row+" "+column);
                   for(int i = 1 ; i <= 4 ;i++ ){
                    
                   if(s.getposition()[0] - row < 0){
                           Thread.sleep(200);
                           System.out.println("1");
                           s.motion(1,i);
                           s.setevent(true);
                           repaint() ;
                   }
                   else if(s.getposition()[0] - row > 0){
                           Thread.sleep(200) ;
                           s.motion(2,i);
                           s.setevent(true);
                           repaint() ;
                   }
                   else if(s.getposition()[1] - column < 0){
                            Thread.sleep(200);
                           s.motion(3,i);
                           s.setevent(true);
                           repaint() ;
                   }
                   else if(s.getposition()[1] - column > 0){
                       Thread.sleep(200);
                           s.motion(4,i);
                           s.setevent(true);
                           repaint() ;
                    
                   }
                   }
               }catch(Exception e) {}
           
                   s.setevent(false);
                   repaint() ;
               
           }}) ;
       
       
       acttack.start() ;
       panelgame.wait();
       panelgame.notify();
    }
       
    public void setstatuspanel(){
        statusbar_panel.removeAll();
        int chack = chack_if_in_event() ;
        if(chack >= 0){
            sol.get(chack).choose_me(1) ;
            if(sol.get(chack).get_abilitty_walk() && sol.get(chack).tream.equals(turn[turn_%2]) && !(sol.get(chack) instanceof central)){
            statusbar_panel.add(WalkButton) ;
            WalkButton.setBounds(100, 20, 80, 50) ;
            WalkButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  status = 1 ;
                  setmap(1) ;
                }
            });
            
            
            }
            if(sol.get(chack).get_abilitty_acttack() && (sol.get(chack).tream).equals(turn[turn_%2]) && !(sol.get(chack) instanceof central)){
                statusbar_panel.add(ActteckButton) ;
                ActteckButton.setBounds(200, 20, 80, 50);
                ActteckButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       status = 2 ;
                       setmap(status) ;
                    }
                });
            }
            if((sol.get(chack) instanceof central) && sol.get(chack).get_ability_summon() && (sol.get(chack).tream).equals(turn[turn_%2])){
                if(sol.get(chack).getcoins() >= 1){
                statusbar_panel.add(Summon_sword) ;
                Summon_sword.setBounds(200, 20, 100, 50);
                Summon_sword.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        summontype = 1 ;
                        status = 3 ;
                        setmap(status) ;
                    }
                });
                }
                if(sol.get(chack).getcoins() >= 2){
                statusbar_panel.add(Summon_bigsword) ;
                Summon_bigsword.setBounds(50, 20, 100, 50);
                Summon_bigsword.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        summontype = 2 ;
                        status = 3 ;
                        setmap(status) ;
                    }
                });
                }
            }
           
            StatusGraphic stgp = new StatusGraphic(sol.get(chack)) ;
            stgp.setBounds(80,80,500,200) ;
            statusbar_panel.add(stgp) ;
            
        }else{
            JButton endturn = null ;
            try {
                endturn = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/map/Endturn.png"))));
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            endturn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    turn_++ ;
                    for(int i = 0 ;i < sol.size() ;i++){
                        if(!(sol.get(i).tream.equals(turn[turn_%2]))|| turn_ == 1) 
                            sol.get(i).setcoins(sol.get(i).getcoins()+1);
                        sol.get(i).set_ability_summon(true);
                        sol.get(i).set_abilitty_walk(true);
                        sol.get(i).set_abilitty_acttack(true);
                    }
                }
            });
            JLabel Tream = new JLabel(new ImageIcon(turnof[turn_%2])) ;
            Tream.setBounds(320, 0, 100, 50);
            endturn.setBounds(320,40, 100, 50);
            statusbar_panel.add(endturn) ;
            statusbar_panel.add(Tream) ;
            
        }
        statusbar_panel.add(imageback_ground) ;
            
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(tostart){
        map.drawblackground(g);
        for(int i = 0 ; i < sol.size() ; i++){
            g.setColor(Color.red);
            if(sol.get(i).getpriority()){
                g.setColor(Color.blue);
            }
            sol.get(i).drawmotion(g);
            g.drawImage(sol.get(i).getimage(),sol.get(i).getx(), sol.get(i).gety(),objectsize_64, objectsize_64,this) ;
            if(chackwin == 1){
             g.drawImage(win[1],0, 200,width_sceen, 300,this) ;
            }else if(chackwin ==  2){
            g.drawImage(win[0],0,200,width_sceen,300,this) ;
        }
        }
       
        }else{
            g.drawImage(backgourd, 0, 0, width_sceen, heigth_sceen+200, this) ;
        }
        
        
            
    }
    public int chack_if_in_event(){
        for(int i = 0 ; i < sol.size() ; i++){
                    if(sol.get(i).getpriority()){
                        return i ;
                    }
        }
        waywalk = new ArrayList<>() ;
        return -1 ;
    }
    int min = 5 ;
    ArrayList<Integer> wewalk ;
    public void  findway(ArrayList<Integer> way,int row ,int column ,int end_row ,int end_column , int num){
        if(num < 5){
            if(row > 0){
                if(row -1 == end_row && column == end_column){
                    if(num < min){
                        way.add(num,1) ;
                        min = num ;
                        System.out.println(way) ;
                        wewalk = (ArrayList<Integer>)way.clone() ;
                        way.remove(num) ;
                    }
                }else if(matrix_map[row-1][column] != 1){
                    way.add(num,1) ;
                    findway(way ,row-1,column ,end_row,end_column,num+1) ;
                    way.remove(num) ;
                }
            }
            if(row < max_row - 1){
                if(row + 1 == end_row && column == end_column){
                    if(num < min){
                        way.add(num,2) ;
                        min = num ;
                        wewalk = (ArrayList<Integer>)way.clone() ;
                        way.remove(num) ;
                    }
                }else if(matrix_map[row+1][column] != 1){
                    way.add(num,2) ;
                    findway(way,row+1,column ,end_row,end_column,num+1) ;
                    way.remove(num) ;
                }
            }
           if(column > 0){
                if(row  == end_row && column -1 == end_column){
                    if(num < min){
                        way.add(num,3) ;
                        min = num ;
                        wewalk = (ArrayList<Integer>)way.clone() ;
                        way.remove(num) ;
                    }
                }else if(matrix_map[row][column-1] != 1){
                    way.add(3) ;
                    findway(way ,row,column-1 ,end_row,end_column,num+1) ;
                    way.remove(num) ;
                }
            }
           if(column < max_column -1){
                if(row  == end_row && column +1 == end_column){
                    if(num < min){
                        way.add(num,4) ;
                        min = num ;
                        wewalk = (ArrayList<Integer>)way.clone() ;
                        way.remove(num) ;
                    }
                    

                }else if(matrix_map[row][column+1] != 1){
                    way.add(num,4) ;
                    findway(way  ,row,column+1 ,end_row,end_column,num+1) ;
                    way.remove(num) ;
                }
           }
        }
    }
    public void walk(int avaliable_canwalk ,soldier people_start ,int row_end ,int column_end) throws InterruptedException {
        ArrayList<Integer> way = new ArrayList<>() ;
        min = 5 ;
        findway(way,people_start.getposition()[0] ,people_start.getposition()[1] ,row_end ,column_end,0) ;
        
        Thread walkto = new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i = 0 ; i < wewalk.size() ;i++){
                    try{
                        if(wewalk.get(i) == 1){
                            for(int j = 1 ; j <= 4 ;j++){
                                Thread.sleep(100);
                                people_start.setxy(people_start.getx(),people_start.gety()-objectsize_64/4) ;
                                people_start.walk(j,1);
                                repaint() ;
                            }
                        }else if(wewalk.get(i) == 2){
                            for(int j = 1 ; j <= 4 ;j++){
                                Thread.sleep(100);
                                people_start.setxy(people_start.getx(),people_start.gety()+ objectsize_64/4) ;
                                repaint() ;
                                people_start.walk(j,1);
                                repaint() ;
                            }

                        }else if(wewalk.get(i) == 3){
                            for(int j = 1 ; j <= 4 ;j++){
                                Thread.sleep(100);
                                people_start.setxy(people_start.getx()- objectsize_64/4,people_start.gety()) ;
                                repaint() ;
                                people_start.walk(j,2);
                                repaint() ;
                            }
                        }else if(wewalk.get(i) == 4){
                            for(int j = 1 ; j <= 4 ;j++){
                                Thread.sleep(100);
                                people_start.setxy(people_start.getx()+ objectsize_64/4,people_start.gety()) ;
                                repaint() ;
                                people_start.walk(j,3);
                                repaint() ;
                            }
                        }
                    }catch(Exception e){System.out.print("fasdfj");}
                    
                    
                    
                }
            }
        });
        walkto.start();
        panelgame.wait(); 
        panelgame.notify();
        System.out.println(wewalk) ;
    }
   ArrayList<ArrayList<Integer>> waywalk = new ArrayList<>() ;
   public void findcanwalk(int index_rows,int index_columns,int num){
       if(num != 0){
        if(index_rows > 0 && matrix_map[index_rows-1][index_columns] != 1){
            ArrayList<Integer> walk = new ArrayList<>() ;
            walk.add(index_rows-1) ;
            walk.add(index_columns) ;
            if(!waywalk.contains(walk)){
                waywalk.add(walk) ;
            }
            findcanwalk(index_rows-1,index_columns,num-1) ;
        }
        if(index_rows < max_row-1 && matrix_map[index_rows+1][index_columns] != 1){
            ArrayList<Integer> walk = new ArrayList<>() ;
            walk.add(index_rows+1) ;
            walk.add(index_columns) ;
            if(!waywalk.contains(walk)){
                waywalk.add(walk) ;
            }
            findcanwalk(index_rows+1,index_columns,num-1) ;
        }
        if(index_columns > 0 && matrix_map[index_rows][index_columns-1] != 1){
            ArrayList<Integer> walk = new ArrayList<>() ;
            walk.add(index_rows) ;
            walk.add(index_columns-1) ;
            if(!waywalk.contains(walk)){
                waywalk.add(walk) ;
            }
            findcanwalk(index_rows,index_columns-1,num-1) ;
        }
        if(index_rows < max_column-1 && matrix_map[index_rows][index_columns+1] != 1){
            ArrayList<Integer> walk = new ArrayList<>() ;
            walk.add(index_rows) ;
            walk.add(index_columns+1) ;
            if(!waywalk.contains(walk)){
                waywalk.add(walk) ;
            }
            findcanwalk(index_rows,index_columns+1,num-1) ;
        }
    }
       
       
   }
   public ArrayList<ArrayList<Integer>>way_acttack;
   public void findwhereActtack(int positionx ,int positiony){
       if(positionx > 0 && map.matrix_map[positionx-1][positiony] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx-1) ;
           way.add(positiony) ;
           way_acttack.add(way) ;
       }
       if(positiony > 0 && map.matrix_map[positionx][positiony-1] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx) ;
           way.add(positiony-1) ;
           way_acttack.add(way) ;
       }
       if(positionx < map.matrix_map[0].length-1 && map.matrix_map[positionx+1][positiony] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx+1) ;
           way.add(positiony) ;
           way_acttack.add(way) ;
       }
       if(positiony < map.matrix_map.length-1 && map.matrix_map[positionx][positiony+1] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx) ;
           way.add(positiony+1) ;
           way_acttack.add(way) ;
       }
   }
   public ArrayList<ArrayList<Integer>> waysummond ;
   public void findwaysummond(int positionx,int positiony){
       if(positionx > 0 && map.matrix_map[positionx-1][positiony] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx-1) ;
           way.add(positiony) ;
           waysummond.add(way) ;
       }
       if(positiony > 0 && map.matrix_map[positionx][positiony-1] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx) ;
           way.add(positiony-1) ;
           waysummond.add(way) ;
       }
       if(positionx < map.matrix_map[0].length-1 && map.matrix_map[positionx+1][positiony] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx+1) ;
           way.add(positiony) ;
           waysummond.add(way) ;
       }
       if(positiony < map.matrix_map.length-1 && map.matrix_map[positionx][positiony+1] != 1){
           ArrayList<Integer> way = new ArrayList<>() ;
           way.add(positionx) ;
           way.add(positiony+1) ;
           waysummond.add(way) ;
       }
   }
   public void setmap(int status){
        int chack = chack_if_in_event() ;
//            System.out.print(chack);
        way_acttack = new ArrayList<>() ;
        waysummond = new ArrayList<>() ;
        if(status != 0 ){
            switch (status) {
                case 1:
                    findcanwalk(sol.get(chack).getposition()[0],sol.get(chack).getposition()[1],sol.get(chack).getwalk()) ;
                    break;
                case 2:
                    findwhereActtack(sol.get(chack).getposition()[0],sol.get(chack).getposition()[1]) ;
                    break;
                case 3:
                    findwaysummond(sol.get(chack).getposition()[0],sol.get(chack).getposition()[1]) ;
                    break;
                default:
                    break;
            }
                ArrayList<ArrayList<Integer>> sol_position = new ArrayList<>() ;
                for(int i = 0 ; i < sol.size( ) ;i++){
                    ArrayList<Integer> walk = new ArrayList<>() ;
                    walk.add(sol.get(i).getposition()[0]) ;
                    walk.add(sol.get(i).getposition()[1]) ;
                    sol_position.add(walk) ;
                }
            switch (status) {
                case 1:
                    for(int i = 0 ; i < waywalk.size() ;i++){
                        if(!sol_position.contains(waywalk.get(i))){
                            map.matrix_map[waywalk.get(i).get(0)][waywalk.get(i).get(1)] = 2 ;
                        }else{
                            waywalk.remove(i) ;
                            i-- ;
                        }
                    }   break;
                case 2:
                    for(int i = 0 ; i < way_acttack.size() ;i++){
                        map.matrix_map[way_acttack.get(i).get(0)][way_acttack.get(i).get(1)] = 3 ;
                    }
                    break;
                default:
                    for(int i = 0 ; i < waysummond.size() ;i++){
                        if(!sol_position.contains(waysummond.get(i))){
                            map.matrix_map[waysummond.get(i).get(0)][waysummond.get(i).get(1)] = 2 ;
                        }else{
                            waysummond.remove(i) ;
                            i-- ;
                        }
                    }   break;
            }
                
                
            }else{
                map.loadmap();
            }
        
   }
}
