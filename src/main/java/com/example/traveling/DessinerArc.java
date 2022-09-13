package com.example.traveling;

import javax.swing.*;
import java.awt.*;

public class DessinerArc extends JLabel{
  Dimension ScreenSize=Toolkit.getDefaultToolkit().getScreenSize();
  int OrigineX,OrigineY,DestinationX,DestinationY;
  int org,dest;
  ic icon;
  int Valeur;
  public DessinerArc(int orgX, int orgY, int destX, int destY){
    setBounds(0,0,ScreenSize.width,ScreenSize.height);
    OrigineX=orgX;
    OrigineY=orgY;
    DestinationX=destX;
    DestinationY=destY;
    icon=new ic();
    setIcon(icon);
    
    icon.repaindre();
  }
  public void setValeur(int o){
	  Valeur=o;
	  icon.repaindre();
  }
  public int getValeur(){
	  return Valeur;
  }
  public void setOrigine(int o){
	  org=o;
  }
  public void setDestination(int d){
    dest=d;
  }
  public int getOrigine(){
    return org;
  }
  public int getDestination(){
    return dest;
  }
  public void setOrigineX(int x){
    OrigineX=x;
    icon.repaindre();
  }
  public void setOrigineY(int y){
    OrigineY=y;
    icon.repaindre();
  }
  public void setDestinationX(int x){
    DestinationX=x;
    icon.repaindre();
  }
  public void setDestinationY(int y){
    DestinationY=y;
    icon.repaindre();
  }
 
  class ic implements Icon {
    public ic(){
    }
    public void paintIcon(Component c,Graphics g,int x,int y){
      String val=Valeur+"";
      char car[]=new char[val.length()];
      val.getChars(0,val.length(),car,0);
      g.setColor(new Color(0,0,0));
      g.drawLine(OrigineX+10,OrigineY+10,DestinationX+10,DestinationY+10);
      int xx= Math.abs((OrigineX-DestinationX)/2);
      int yy= Math.abs((OrigineY-DestinationY)/2);
      if (OrigineX>DestinationX) xx=OrigineX-xx; else xx=OrigineX+xx;
      if (OrigineY>DestinationY) yy=OrigineY-yy; else yy=OrigineY+yy;

	  int orig_x[] = { 0, -10, -8, -10, 0};
	  int orig_y[] = { 0,  4,   0,  -4, 0};
	  int new_x[]  = new int[orig_x.length];
	  int new_y[]  = new int[orig_x.length];
	  int a=OrigineX + 10;
	  int b=OrigineY + 10;

	  int x1 = a,
	      y1 = b,
		  x2 = DestinationX + 10,
		  y2 = DestinationY + 10;

	   double d = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	   double cosa = 1, sina = 0;
	   if (d != 0) {
		   cosa = (x2-x1) / d;
		   sina = (y2-y1) / d;
	   }
	   int r = 25;
	   int b_x = x2 - (int)(0.5+ r/2*cosa);
	   int b_y = y2 - (int)(0.5+ r/2*sina);
	   for (int i=0; i<new_x.length; i++){
		   new_x[i] = (int)(0.5+ cosa*orig_x[i] - sina*orig_y[i]) +b_x ;
		   new_y[i] = (int)(0.5+ sina*orig_x[i] + cosa*orig_y[i]) +b_y ;
	   }
	   Polygon poly = new Polygon(new_x, new_y, new_x.length);
	   g.fillPolygon(poly);
       g.setColor(new Color(179, 11, 0));
       g.drawChars(car,0,val.length(),xx,yy);
    }
    
    public int getIconWidth(){
      return ScreenSize.width;
    }
    public int getIconHeight(){
      return ScreenSize.height;
    }
    public void repaindre(){
      repaint();
    }
  }
}

