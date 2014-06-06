/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankbetanew;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Артем
 */
public class MyPanel extends javax.swing.JPanel {

    public int x = 20;
    public int y = 20;
    public int WIDTH = 1190, HEIGHT = 570;
    public int a;
    public Set<Integer> keys;
    public int restart=0;
    public String color="yellow", color2="purple",originalorient="bottom", originalorient2="top", bullet="image/bullet.png",orient,orient2;
    public int xtank2=1100,ytank2=470,xtank1=50,ytank1=50,originalx=50,originaly=50,originalx2=1100,originaly2=470;
    public int counterBullet1=0,counterBullet2=0,onemayForBullet1=0,onemayForBullet2=0;
    //Создаем экземпляры класса
    Tank tank1 = new Tank(color,originalorient,originalx,originaly);
    Tank tank2 = new Tank(color2,originalorient2,originalx2,originaly2);
   ////////////////////////////////////////
   
   private BufferedImage bodyImage,bodyImage2 , shot , shot2 ,image5;
   
    Timer t;
    ///////Параметры панели ////////////////
    public MyPanel() {
        initComponents();
        keys = new HashSet<>();
        this.setBounds(x, y, WIDTH, HEIGHT);
        t = new Timer(25, new ActionListenerImpl(this));
        t.start();
        this.addKeyListener(new NewJPanelKeyListener(this));
        this.setFocusable(true);
        this.requestFocusInWindow();
        initComponents();
        this.setBackground(Color.black);
    }
    
    
   ///////////////////////////////////////////////////////////////// 
      public class NewJPanelKeyListener implements KeyListener {

        private final JPanel panel;

        NewJPanelKeyListener(MyPanel panel) {
            this.panel = panel;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            a = e.getKeyCode();
            
            if ( a==38 ) { keys.remove(37);keys.remove(40);keys.remove(39); }
            if ( a==37 ) { keys.remove(38);keys.remove(40);keys.remove(39); }
            if ( a==39 ) { keys.remove(38);keys.remove(40);keys.remove(37); }
            if ( a==40 ) { keys.remove(38);keys.remove(37);keys.remove(39); }
            if ( a==87 ) { keys.remove(68);keys.remove(65);keys.remove(83); }
            if ( a==83 ) { keys.remove(68);keys.remove(65);keys.remove(87); }
            if ( a==65 ) { keys.remove(68);keys.remove(87);keys.remove(83); }
            if ( a==68 ) { keys.remove(87);keys.remove(65);keys.remove(83); }
           
            keys.add(a);
            if (a==10) {
            restart=1;
            tank1.rotate(originalorient);
            tank2.rotate(originalorient2);
            tank1.restart();
            tank2.restart();
            }
            System.out.println(a);           
          }
          @Override
           public void keyTyped(KeyEvent e) {
           }

           @Override
            public void keyReleased(KeyEvent e) {
             keys.remove(e.getKeyCode());
           }
       }
 ////////////////////////////////////////////////////////////
      
      
      
      @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        
            try {
                
                if (restart==1) {
                if ( keys.contains(38) ) { orient2="top";    tank2.move(orient2);tank2.rotate(orient2); }
                if ( keys.contains(37) ) { orient2="left";   tank2.move(orient2);tank2.rotate(orient2); }
                if ( keys.contains(39) ) { orient2="right";  tank2.move(orient2);tank2.rotate(orient2); }
                if ( keys.contains(40) ) { orient2="bottom"; tank2.move(orient2);tank2.rotate(orient2); }
                if ( keys.contains(17) && onemayForBullet2==1 ) {tank2.coordinatesofBullet(orient2);onemayForBullet2=0;counterBullet2=0;}
                
                
                
                if ( keys.contains(87) ) { orient="top";    tank1.move(orient); tank1.rotate(orient); }
                if ( keys.contains(83) ) { orient="bottom"; tank1.move(orient); tank1.rotate(orient); }
                if ( keys.contains(65) ) { orient="left";   tank1.move(orient); tank1.rotate(orient); }
                if ( keys.contains(68) ) { orient="right";  tank1.move(orient); tank1.rotate(orient); }
                if ( keys.contains(32) && onemayForBullet1==1 ) {tank1.coordinatesofBullet(orient);onemayForBullet1=0;counterBullet1=0;}
              
               
              //  shotx=tank1.getbulletX();
                
                if (counterBullet2>30) {onemayForBullet2=1;}
                if (counterBullet1>30) {onemayForBullet1=1;}
                
               tank1.shot();
               tank2.shot();
               
               
            if(tank1.getbulletY()>=tank2.getY() && tank1.getbulletY()<=tank2.getY()+50 && tank1.getbulletX()>=tank2.getX() && tank1.getbulletX()<=tank2.getX()+50) {
               tank2.rotate("explosion");
               
               tank1.coordinatesofBullet("f");
            //   y4=1000; x4=-100;
               
               restart=0;
               
           }  //попадание в танк 2 после чего танк 2 исчезает и если он выпустил пулю то она тоже исчезает
           if(tank2.getbulletY()>=tank1.getY() && tank2.getbulletY()<=tank1.getY()+50 && tank2.getbulletX()>=tank1.getX() && tank2.getbulletX()<=tank1.getX()+50) {
               tank1.rotate("explosion");
               restart=0;           
               tank2.coordinatesofBullet("f");
               
           }       //попадание в танк 1 после чего танк 1 исчезает и если он выпустил пулю то она тоже исчезает
           
              
          
                }
                if (restart==1){
                shot = ImageIO.read(new File(bullet));
                g.drawImage(shot, tank1.getbulletX(), tank1.getbulletY(), 4, 4, null);
                shot2 = ImageIO.read(new File(bullet));
                g.drawImage(shot2, tank2.getbulletX(), tank2.getbulletY(), 4, 4, null);
              
                }
               
                bodyImage = ImageIO.read(new File(tank1.getBody()));
                g.drawImage(bodyImage, tank1.getX(), tank1.getY(), 50, 50, null);
                bodyImage2 = ImageIO.read(new File(tank2.getBody()));
                g.drawImage(bodyImage2, tank2.getX(), tank2.getY(), 50, 50, null);
               
            } catch (IOException ex) {
                Logger.getLogger(MyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        
        
    }
      
    
    
    
    
    
    
    
    
    public class ActionListenerImpl implements ActionListener {

        private final MyPanel panel;

        public ActionListenerImpl(MyPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.repaint();
            ++counterBullet1;
            ++counterBullet2;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
