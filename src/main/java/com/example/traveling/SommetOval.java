package com.example.traveling;

import javax.swing.*;
import java.awt.*;


public class SommetOval extends JLabel{
  Ic ic;
  String label;
  boolean selected;
  public SommetOval(){
    setIcon(new Ic());
  }
  public SommetOval(int x,int y){
    ic=new Ic();
    setIcon(ic);
    setBounds(x,y,ic.getIconWidth(),ic.getIconHeight());
  }
  public SommetOval(int x,int y,String c){
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
      g.setColor(new Color(255, 255, 255));
      g.fillOval(x,y,getIconWidth(),getIconHeight());
      g.setColor(new Color(93, 74, 52));
      g.drawOval(x+2,y+2,getIconWidth()-5,getIconHeight()-5);
      g.fillOval(x+3,y+3,getIconWidth()-6,getIconHeight()-6);
      g.setColor(new Color(255, 255, 255));
      g.drawString(label,x+8,y+19);
    }
    public int getIconWidth(){
      return 70;
    }
    public int getIconHeight(){
      return 30;
    }
    public void repaindre(){
      repaint();
    }
  }
}
