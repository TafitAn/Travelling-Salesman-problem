package com.example.traveling;


import javax.swing.*;
import java.awt.*;

public class Sommet extends JLabel{
  Ic ic;
  String label;
  boolean selected;
  public Sommet(){
    setIcon(new Ic());
  }
  public Sommet(int x,int y){
    ic=new Ic();
    setIcon(ic);
    setBounds(x,y,ic.getIconWidth(),ic.getIconHeight());
  }
  public Sommet(int x,int y,String c){
    ic=new Ic();
    setIcon(ic);
    setLabel(c);
    setBounds(x,y,ic.getIconWidth(),ic.getIconHeight());
  }
  public void setSelected(boolean b){
    selected=b;
  }
  public boolean getSelected(){
    return selected;
  }
  public void setLabel(String c){
    label=c;
    ic.repaindre();
  }
  public int getWidth(){
    return ic.getIconWidth();
  }
  public int getHeight(){
    return ic.getIconHeight();
  }
  
  private class Ic implements Icon{
    public Ic(){
    }
    public void paintIcon(Component c,Graphics g, int x,int y){
      char car[]=new char[label.length()];
      label.getChars(0,label.length(),car,0);
      g.setColor(new Color(0,0,0));
      g.fillOval(x,y,getIconWidth(),getIconHeight());
      g.setColor(new Color(255,255,255));
      g.drawOval(x+2,y+2,getIconWidth()-5,getIconHeight()-5);
      if (getSelected()) g.setColor(new Color(127,127,127));else g.setColor(new Color(8, 124, 250));
      g.fillOval(x+3,y+3,getIconWidth()-6,getIconHeight()-6);
      g.setColor(new Color(255,255,255));
      g.drawChars(car,0,2,x+8,y+19);
    }
    public int getIconWidth(){
      return 30;
    }
    public int getIconHeight(){
      return 30;
    }
    public void repaindre(){
      repaint();
    }
  }
}

