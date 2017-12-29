/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch8threads;

import java.awt.Point;

/**
 *
 * @author ASUS
 */
public class Tank {
    public Point pos=new Point();
    public String ImagePath;
    public String ImagePath2;

    public Bullet Rocket=new Bullet();
     public Bullet2 Rocket2=new Bullet2();
    public int speed;
    public Tank (String ImagePath)
    {
        speed=5;
        this.ImagePath=ImagePath;
        
        Rocket.imgPath="C:\\Users\\lenovo\\Documents\\NetBeansProjects\\Ch8Threads\\rocket.gif";
       Rocket2.imgPath="C:\\Users\\lenovo\\Documents\\NetBeansProjects\\Ch8Threads\\rocket.gif";
    }

    void mover() {
        this.pos.x+=speed;
    }
      void movel() {
        this.pos.x-=speed;
    }

    public void fireBullet() {
        Rocket.pos.x=this.pos.x+60;
        Rocket.pos.y=this.pos.y;
        Rocket2.pos.x=this.pos.x-40;
        Rocket2.pos.y=this.pos.y;
        Thread t1=new Thread (Rocket);
        Thread t2 =new Thread(Rocket2);
        t1.start();
        t2.start();
    }
    public void removeroc(Bullet i){
        
        i.pos.x=-100;
        i.pos.y=-100;
    }
    public void removeroc(Bullet2 i){
        
        i.pos.x=-200;
        i.pos.y=-200;
    }
     public void removetan(){
        
        this.pos.x=-300;
        this.pos.y=-300;
    }
}
