/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tankbetanew;

/**
 *
 * @author Артем
 */


public class Tank  {
     private String  bodyImage="image/1.png",orient, color,directionbullet="jk",originalorient;
     private int x,y,bulletx,bullety,originalx,originaly;
        
   
public Tank (String color, String rotation, int x, int y)
   {
    this.color=color;
    this.originalorient=rotation;
    this.x=x;
    this.y=y;
    this.originalx=x;
    this.originaly=y;
    bulletx=100;
    bullety=100;
    
   }
  
     public static void  main()
     {
         
         
     }
     
    public  void  rotate (String orient)
    {
       this.orient=orient;
      if (color.contains("purple"))
      switch (orient)
        {
          case  "top"       : bodyImage="image/Tank2.png";break;
          case  "bottom"    : bodyImage="image/Tank2bottom.png";break;
          case  "left"      : bodyImage="image/Tank2left.png";break; 
          case  "right"     : bodyImage="image/Tank2right.png";break; 
          case  "explosion" : bodyImage="image/d.png";break;   
          default:;
        }  
      
      if (color.contains("yellow"))      
          switch (orient)
        {
          case  "top"    : bodyImage="image/Tank.png";break;
          case  "bottom" : bodyImage="image/Tankbottom.png";break;
          case  "left"   : bodyImage="image/Tankleft.png";break; 
          case  "right"  : bodyImage="image/Tankright.png";break;  
          case  "explosion" : bodyImage="image/d.png";break;  
          default:;
        }  
      
        //Изменяем bodyImage
    }
    
    public String getOrient()
    {
        return orient;
    }
    public String getBody()
    {
        return bodyImage;
    }
   public void move(String direction)
    {
        switch (direction)
        {
            case "top"    :this.y-=5;break;
            case "bottom" :this.y+=5;break;
            case "left"   :this.x-=5;break;
            case "right"  :this.x+=5;break;
            default:;    
        }
    }
   
   
   public void coordinatesofBullet(String orient)
   {
       directionbullet=orient;
       switch (orient)
       {
           case "top"    :bulletx=this.x+23;bullety=this.y   ;break;
           case "bottom" :bulletx=this.x+23;bullety=this.y+50;break;
           case "right"  :bulletx=this.x+46;bullety=this.y+23;break;
           case "left"   :bulletx=this.x-4; bullety=this.y+23 ;break;
           default:bulletx=-100;bullety=-100;
       }
   }
    
   public void shot()
   {
       switch(directionbullet)
       {
           case "top"    :this.bullety-=40   ;break;
           case "bottom" :this.bullety+=40   ;break;
           case "right"  :this.bulletx+=40   ;break;
           case "left"   :this.bulletx-=40   ;break; 
           default:bulletx=-100;bullety=-100;
       }
   }
   
   public void restart ()
   {
       
       this.x=originalx;
       this.y=originaly;
   }
   
   public int getbulletX()
   {
       
       return bulletx;
   }
   
   public int getbulletY()
   {
       
       return bullety;
   }
   
   
    public int getX()
    {
      return x;  
    }
    public int getY()
    {
        return y;
    }
}
