/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch8threads;

import java.awt.Color;
import static java.awt.Color.RED;
import static java.awt.Color.blue;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class MovingBalls extends JPanel implements Runnable{

   public ArrayList<Ball>Balls=new ArrayList<Ball>(5);
   public Tank BlueTank=new Tank("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\Ch8Threads\\TankBlueS.jpg");
   public Tank BlueTank2=new Tank("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\Ch8Threads\\InkedTankBlue.jpg");
    public MovingBalls()
    {
        setSize(900,900);
        setBackground(Color.red);
        BlueTank.pos.x=450;
        BlueTank.pos.y=650;
        BlueTank2.pos.x=450;
        BlueTank2.pos.y=0;
        this.addKeyListener(new keylist());
         
    }
    private class keylist implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("Test");
            if (e.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                
                BlueTank.mover();
                
            }
            if (e.getKeyCode()==KeyEvent.VK_LEFT)
            {
                
                BlueTank.movel();
                //repaint();
            }
            if (e.getKeyCode()==KeyEvent.VK_SPACE)
            {
                
                BlueTank.fireBullet();
            }
             if (e.getKeyCode()==KeyEvent.VK_2)
            {
                
                BlueTank2.mover();
                
            }
            if (e.getKeyCode()==KeyEvent.VK_1)
            {
                
                BlueTank2.movel();
                //repaint();
            }
            if (e.getKeyCode()==KeyEvent.VK_ALT)
            {
                
                BlueTank2.fireBullet();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }
    }
    public void  paintComponent(Graphics g)
    {
        g.clearRect(0, 0, 900, 900);
        
        
        try
        {
        BufferedImage imgtank = ImageIO.read(new File(BlueTank.ImagePath));
        
        BufferedImage imgrocket = ImageIO.read(new File(BlueTank.Rocket.imgPath));
        g.drawImage(imgtank, BlueTank.pos.x, BlueTank.pos.y,null);
        g.drawImage(imgrocket, BlueTank.Rocket.pos.x, BlueTank.Rocket.pos.y,null);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        
       try {
           BufferedImage imgtank = ImageIO.read(new File(BlueTank2.ImagePath));
           AffineTransform tx = new AffineTransform();
            tx.rotate(3.15, imgtank.getWidth() / 2, imgtank.getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
            imgtank = op.filter(imgtank, null);
            
            BufferedImage imgrocket = ImageIO.read(new File(BlueTank2.Rocket2.imgPath));
            AffineTransform TX = new AffineTransform();
             TX.rotate(3.15, imgrocket.getWidth() / 2, imgrocket.getHeight() / 2);
              AffineTransformOp OP = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
                imgrocket = OP.filter(imgrocket, null);
             
             g.drawImage(imgtank, BlueTank2.pos.x, BlueTank2.pos.y,null);
        g.drawImage(imgrocket, BlueTank2.Rocket2.pos.x, BlueTank2.Rocket2.pos.y,null);
       } catch (IOException ex) {
           Logger.getLogger(MovingBalls.class.getName()).log(Level.SEVERE, null, ex);
       }
        for (Ball OneBall: Balls)
        
        {
            g.setColor(OneBall.CurrentColor);
            g.fillOval(OneBall.x,OneBall.y, 50, 50);
            if (BlueTank.Rocket.pos.distance(OneBall.x, OneBall.y)<=50&&OneBall.CurrentColor!=red)
            {
                System.out.println("Hit Occurs1" + OneBall.CurrentColor.toString());
                //Balls.remove(OneBall);
                BlueTank.removeroc(BlueTank.Rocket);
                //BlueTank2.removetan();
                break;
            }
            if (BlueTank.Rocket.pos.distance(OneBall.x, OneBall.y)<=50&&OneBall.CurrentColor==red)
            {
                System.out.println("Hit Occurs2" + OneBall.CurrentColor.toString());
                //Balls.remove(OneBall);
                BlueTank.removeroc(BlueTank.Rocket);
                BlueTank2.removetan();
                JOptionPane.showMessageDialog(null,"blue tank wins ", "winner!!",JOptionPane.DEFAULT_OPTION);
                //BlueTank2.removetan();
                break;
            }
             if (BlueTank2.Rocket2.pos.distance(OneBall.x, OneBall.y)<=70&&OneBall.CurrentColor!=blue)
            {
                System.out.println("Hit Occurs3" + OneBall.CurrentColor.toString());
                //Balls.remove(OneBall);
                BlueTank2.removeroc(BlueTank2.Rocket2);
                
                //BlueTank2.removetan();
                break;
            }
               if (BlueTank2.Rocket2.pos.distance(OneBall.x, OneBall.y)<=70&&OneBall.CurrentColor==blue)
            {
                System.out.println("Hit Occurs4" + OneBall.CurrentColor.toString());
                //Balls.remove(OneBall);
                BlueTank2.removeroc(BlueTank2.Rocket2);
                BlueTank.removetan();
                JOptionPane.showMessageDialog(null,"red dot tank wins ", "winner!!",JOptionPane.DEFAULT_OPTION);                
                break;
            }
//             
//                if (BlueTank.Rocket.pos.distance(BlueTank2.pos.x, BlueTank2.pos.y)<=50)
//            {
//                //System.out.println("Hit Occurs" + OneBall.CurrentColor.toString());
//                //Balls.remove(OneBall);
//                BlueTank.removeroc(BlueTank.Rocket);
//                BlueTank2.removetan();
//                System.out.println("Hit tank");            }
        }
        
    }
    @Override
    public void run() {
    try
    {
    while(true)
    {
        for (Ball OneBall: Balls)
        {
            OneBall.move(this.getWidth());
        }    
        
        //y+=10;
        Thread.sleep(50);
        repaint();
    }
    }
    catch (InterruptedException e)
    {}
    }
    
}
