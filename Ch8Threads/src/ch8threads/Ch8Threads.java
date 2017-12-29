/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch8threads;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author lenovo
 */
public class Ch8Threads {
    public static void main(String[] args) {
        // TODO code application logic here
       
        
      
        JFrame jf=new JFrame();
        jf.setSize(900, 900);
        
        MovingBalls mb=new MovingBalls();
        mb.Balls.add(new Ball());
        mb.Balls.add(new Ball(250,240,20,Color.ORANGE));
        mb.Balls.add(new Ball(300,300,15,Color.red));
        mb.Balls.add(new Ball(350,350,10,Color.CYAN));
        mb.Balls.add(new Ball(450,450,10,Color.blue));
       
        
        
        jf.add(mb,BorderLayout.CENTER);
        mb.setFocusable(true);
        Thread t1=new Thread(mb);
        t1.start();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        
    
        
        
        
    }
}
