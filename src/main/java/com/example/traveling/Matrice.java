package com.example.traveling;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Matrice extends JPanel implements KeyListener{
  Cout Couts[][];
  JTextField JTF[][];
  JLabel JLminLi[],JLminCo[], jl;
  int Nombre, M1[][], MajLi[][], MajCo[][];
  Integer entier[][];
  String s[][];
  String[] labLiIni = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
  String[] labCoIni = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
  String[] labLi, labCo;
  String[] labLi2 = {};
  String[] labCo2 = {};
  
  
  public Matrice(int aa){

    Nombre=aa;
    JTF=new JTextField[aa][aa];
    JLminLi= new JLabel[aa];
    JLminCo= new JLabel[aa];
    
    Icon ic=new ImageIcon("hach.gif");
    System.out.println("length labli2:"+labLi2.length);
    
    setLabel(labLiIni, labCoIni);
   
    setLayout(null);
    for (int i=0;i<Nombre;i++){
      jl=new JLabel(labLi[i]);
      jl.setBounds(5,i*40+40,40,40);
      add(jl);
    }
    for (int j=0;j<Nombre;j++){
    	jl=new JLabel(labCo[j]);
        jl.setBounds(j*40+50,0,40,40);
        add(jl);
    }
        
    //matrice i ligne j colonne
    for (int i=0;i<Nombre;i++){
    	for (int j=0;j<Nombre;j++){
    		JTF[i][j]=new JTextField();
    		JTF[i][j].setBounds(j*40+40,i*40+40,40,40);
    		JTF[i][j].addKeyListener(this);
    		if(i==j)JTF[i][j].setEditable(false);
    		add(JTF[i][j]);
    	}
    	JLminLi[i] =new JLabel(" ");
    	JLminLi[i].setBounds(Nombre*40+40,i*40+40,40,40);
    	add(JLminLi[i]);
    }
   
 }
  
  public void setLabel(String l[], String c[]){
	  labLi=l;
	  labCo=c;
 }
  
  //fonction de verification entier
  public boolean verifEntier(String s){
	boolean b=false;
    char cr[]=new char[s.length()];
    s.getChars(0,s.length(),cr,0);
    if (s.length()<1) b=true;
    for (int i=0;i<s.length() && !b;i++)
    if (cr[i]<'0' || cr[i]>'9') b=true;
    return !b;
  }
  public Cout [][] getCouts(){
    return Couts;
  }
  
 //************event Key**********************/
  public void keyPressed(KeyEvent e){
	  Object  x = e.getSource();
	  //System.out.println(x);
  }
  @Override
  public void keyReleased(KeyEvent arg0) {
  	System.out.println(MatPlein(JTF));
  	//Fenetre.JP_Arbre.removeAll();
  	if(MatPlein(JTF)){
  		//MinLi(JTF);
  		/*Fenetre.Arbre();
  		Fenetre.JP_Arbre.repaint();
  		Fenetre.updateMat(JTF);
  		Fenetre.Regret(JTF);
  		Fenetre.Jp.repaint();*/
 	}	
  }
  
  @Override
  public void keyTyped(KeyEvent arg0) {
  	// TODO Auto-generated method stub
   }
  //*****************************************/
  public void setCouts(){
    Couts=new Cout[Nombre][Nombre];
    boolean b=true;
    for (int i=0;i<Nombre && b;i++)
      for (int j=0;j<Nombre && b;j++){
        Couts[i][j]=new Cout();
        if (i!=j){
          b=verifEntier(JTF[i][j].getText());
          if (b){
            Integer ii=new Integer(JTF[i][j].getText());
            Couts[i][j].setValue(ii.intValue());
            }
          else 
            JOptionPane.showMessageDialog(null,"Veuillez entrer une valeur valide","Entrée",JOptionPane.ERROR_MESSAGE);
          }
      }
  }
  public int[][] ConvertJTF(JTextField JTF[][]){
	  M1= new int[JTF.length][JTF.length];
		    for(int i=0;i<JTF.length;i++){
				  for(int j=0;j<JTF.length;j++){
					  if(JTF[i][j].getText().equals("")){						  
						  M1[i][j]=(int)Double.POSITIVE_INFINITY;							  
					  }
					  else
						 M1[i][j]=Integer.parseInt(JTF[i][j].getText());
				  }
			}	 
	  return M1;
  }
  
  public boolean MatPlein(JTextField T[][]){
	  boolean plein=true;
	  for (int i=0;i<Nombre;i++){
		  //System.out.println();
		  for (int j=0;j<Nombre;j++){
			  if(i!=j){
				  //System.out.print(T[i][j].getText()+"\t");
				  if(T[i][j].getText().equals("")) 
	    		  plein=false;
			  }
			  //System.out.println();
		  }
	  }
	  return plein;	  
  }
  
  public void MinLi(JTextField T[][]){
	 for (int i=0;i<Nombre;i++){
		  //int min=Integer.parseInt(JTF[i][i].getText());
		 int min=100000000;
	    for (int j=0;j<Nombre;j++){
	       if(i!=j){
	    	  if(Integer.parseInt(JTF[i][j].getText())<min){
	    		min=Integer.parseInt(JTF[i][j].getText());
	    	  }
	       }
	       String str=(""+min);
	       JLminLi[i].setText(str);
	    }
	 }
  }
  public void MinCo(JTextField TCo[][]){
	  for (int i=0;i<Nombre;i++){
		  //int min=Integer.parseInt(JTF[i][i].getText());
		  int min=100000000;
		  for (int j=0;j<Nombre;j++){
		       if(i!=j){
		    	  if(Integer.parseInt(JTF[j][i].getText())<min){
		    		min=Integer.parseInt(JTF[j][i].getText());
		    	  }
		       }
		       String str=(""+min);
		       //JLminCo[j].setText(str);
		}
	 }
  }
 
  //calcul Racine
  public static int calculMinorant(int M[][]){
	    int M2[][]=M;
		int Min=0;
		for(int i=0; i<M.length; i++){
			Min+=minRow(i, M);	
			updateRow(minRow(i, M), i, M2);
		}		
		for(int j=0; j<M.length; j++){
			Min+=minCol(j, M2);
		}
		return Min;
	}
	
	//le min de la ligne:
	public static int minRow(int l, int M[][]){
		int n=M.length;	
		int mr=1000000;
		for(int j=0; j<n; j++){
				//if((M[l][j]<mr)&&(M[l][j]!=-1))
				if((M[l][j]<mr)&&(j!=l))
					mr=M[l][j];
		}
		if(mr==1000000)
			mr=0;
		return mr;
	}
	//m-a-j de la ligne:
	public static void updateRow(int r, int l, int M[][]){
		int n=M.length;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++){
				if(i!=j)
					if(i==l)
						if(M[i][j]!=-1)
							M[i][j]=M[i][j]-r;			
		}
	}
	 
	//min de la colonne:
	public static int minCol(int c, int M[][]){
		int n=M.length;
		int mc=1000000;
		for(int i=0; i<n; i++){
			if((M[i][c]<mc)&&(i!=c))
					mc=M[i][c];
		}	
		if(mc==1000000)
			mc=0;	
		return mc;
	}
	
	
	public static String[] MajLab(String Lab[], int numLab){
		String nouveauLab[]=new String[Lab.length-1];
		for(int i=0;i<Lab.length;i++){
			if(i<numLab)
				nouveauLab[i] = Lab[i];
			else if(i>numLab)
				nouveauLab[i-1] = Lab[i];				
		}
		return nouveauLab;
	}
}
