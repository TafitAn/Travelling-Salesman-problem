package com.example.traveling;


class Cout {
  int Valeur;
  boolean hachure,enleve;
  public Cout(){
    enleve=false;
    hachure=true;
    Valeur=0;
  }
  public void setHachure(boolean b){
    hachure=b;
  }
  public boolean getHachure(){
    return hachure;
  }
  public void setEnleve(boolean b){
    enleve=b;
  }
  public boolean getEnleve(){
    return enleve;
  }
  public void setValue(int v){
    Valeur=v;
    setHachure(false);
  }
  public int getValue(){
    return Valeur;
  }
}
