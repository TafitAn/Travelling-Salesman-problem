package com.example.traveling;


import javax.swing.*;
import java.awt.*;

public class ArcArbre extends JLabel{
  Dimension ScreenSize=Toolkit.getDefaultToolkit().getScreenSize();
  int OrigineX,OrigineY,DestinationX,DestinationY;
  int org,dest;
  ic icon;
  int Valeur;
  public ArcArbre(int orgX, int orgY, int destX, int destY){
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
      g.setColor(new Color(179, 11, 0));
      g.drawLine(OrigineX+10,OrigineY+10,DestinationX+10,DestinationY+10);      
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
