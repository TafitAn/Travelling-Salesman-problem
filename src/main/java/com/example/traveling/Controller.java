package com.example.traveling;

import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    @FXML
    private AnchorPane arbPane;

    @FXML
    private AnchorPane circuitPane;

    @FXML
    private AnchorPane dataPane;

    @FXML
    private AnchorPane playAnchor;

    @FXML
    private Button playButton;

    @FXML
    private TextField playTextfield;

    @FXML
    private Button runbutton;
    @FXML
    private Button exitButton;

    @FXML
    private Button minusButton;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private AnchorPane alertAnchor;


    @FXML
    private AnchorPane anchorMat;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane arbreScroll;

    @FXML
    private Button nextButton;
    @FXML
    private ImageView boussol;

    @FXML
    private ImageView Arb;

    @FXML
    private ImageView def;

    @FXML
    private Button newButton;



    Stage stage;
    static int infini;
    int liparasite, colparasite, borne_arc;
    int numMat, MatInitiale[][][], Mat2[][][], MatReg[][][], NouvelleM[][][], MatApSupprPar[][][], racine[], MatApSupprPar2[][][], MatATraiter[][][];
    int nb_solution;
    static int reste_mat[][]={};
    static Arc arc[];
    static Arc nonarc[];
    static Arc arc_choisi[];
    static Arc reste_arc[]={}, reste_nonarc[]={};
    static String TabSommet[] ;
    String parasite[], sommet_continu;

    private JLabel image = new JLabel("");
    private JToggleButton DemarrePVC = new JToggleButton("Démarrer");
    private panneauImage pnlImage = new panneauImage();
    private JPanel panneauSud = new JPanel();
    private JPanel panneauCentre = new JPanel();

    String labIni[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    DessinerArc arcD[]=new DessinerArc[500];
    DessinerArc reste_arcD[]=new DessinerArc[500];
    ArcArbre arc_arbre[]= new ArcArbre[500];

    JButton Next, Voir;
    JLabel solution;
    Matrice Mat ;
    Panel_Mat Jp;
    JPanel JP_Hamilton, JP_Arbre;
    double Pi = 3.14159265358979323846264338327950288419717;
    Sommet som[]=new Sommet[500];
    SommetOval somOv[][] = new SommetOval[100][500];
    String cars[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int nb_next_clique = 0;
    String labLi2[]={}, labCol2[]={};
    int MatInit[][];

    String labLi[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String labCol[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    boolean existe = false;
    boolean deuxieme_sol=false;

    @FXML
    void handleButton(ActionEvent e) throws Throwable {

        if (e.getSource() == backButton){

            dataPane.setVisible(true);
            circuitPane.setVisible(false);
            arbPane.setVisible(false);
            backButton.setVisible(false);
            nextButton.setVisible(true);
            runbutton.setVisible(false);
            Arb.setVisible(false);
            boussol.setVisible(false);
        }
        if (e.getSource() == nextButton){

            dataPane.setVisible(false);
            def.setVisible(false);
            circuitPane.setVisible(true);
            Arb.setVisible(true);
            arbPane.setVisible(true);
            backButton.setVisible(true);
            nextButton.setVisible(false);
            runbutton.setVisible(true);
            Jp.remove(Mat);
            boussol.setVisible(true);
        }
        if(e.getSource() == newButton){
            Mat = null;
            stage = (Stage)(newButton.getScene().getWindow());
            changeScene(stage,"home.fxml");
        }
        if(e.getSource() == minusButton){

            stage = (Stage)anchorpane.getScene().getWindow();
            stage.setIconified(true);
        }
        if(e.getSource() == exitButton){

            stage = (Stage)(exitButton.getScene().getWindow());
            stage.close();
            System.exit(0);
        }
        if(e.getSource() == playButton){

            String s = playTextfield.getText();
            Jp.removeAll();
            JP_Arbre.removeAll();
            JP_Hamilton.removeAll();
            boolean cond = verifEntier(s);

            if(cond == false){
                alertAnchor.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> alertAnchor.setVisible(false));
                pause.play();
                playTextfield.clear();

            }else {
                processing(Integer.parseInt(s));
                def.setVisible(true);
                newButton.setVisible(true);
            }
        }
        if(e.getSource() == runbutton) {

            arbPane.setVisible(true);
            circuitPane.setVisible(true);
            boussol.setVisible(true);
            dataPane.setVisible(false);
            Arb.setVisible(true);
            backButton.setVisible(true);
            def.setVisible(false);
            newButton.setVisible(true);
            doingIt();
        }
    }

    private void changeScene(Stage stage, String s) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource(s));
            this.stage.setScene(new Scene(root));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         infini =  (int)Double.POSITIVE_INFINITY ;
        Jp = new Panel_Mat();
        Jp.setSize(500,500);
        JP_Arbre = new JPanel();
        Next = new JButton();
        Voir = new JButton();
        solution = new JLabel();


        JP_Hamilton = new JPanel();
        JP_Hamilton.setLayout(null);
        JP_Hamilton.setBackground(new Color(221, 221, 221));
        JP_Hamilton.setSize(480, 555);
        JP_Hamilton.setVisible(true);

        JP_Arbre.setLayout(null);

        JP_Arbre.setBackground(new Color(221, 221, 221));
        JP_Arbre.setSize(950, 700);
        JP_Arbre.setVisible(true);

        final SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
        anchorMat.getChildren().add(swingNode);

        final SwingNode swingNode1 = new SwingNode();
        createAndSetSwingContent1(swingNode1);
        circuitPane.getChildren().add(swingNode1);

        final SwingNode swingNode2 = new SwingNode();
        createAndSetSwingContent2(swingNode2);
        arbreScroll.getChildren().add(swingNode2);

    }

    private void createAndSetSwingContent(final SwingNode swingNode) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(Jp);
            }
        });
    }
    private void createAndSetSwingContent1(final SwingNode swingNode1) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode1.setContent(JP_Hamilton);
            }
        });
    }
    private void createAndSetSwingContent2(final SwingNode swingNode2) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode2.setContent(JP_Arbre);
            }
        });
    }


    public void processing(int i) throws Throwable {
        Mat = null;
        Graphe_Hamilton(i);
        Mat = new Matrice(i);
        Mat.setVisible(true);
        numMat = Mat.JTF.length;
        MatInitiale = new int[numMat][numMat][numMat];
        Mat2=new int[numMat][numMat][numMat];
        MatReg=new int[numMat][numMat][numMat];
        NouvelleM = new int[numMat][numMat-1][numMat-1];
        MatApSupprPar=new int[numMat][numMat-1][numMat-1];
        MatApSupprPar2=new int[numMat][numMat][numMat];
        MatATraiter=new int[numMat][numMat][numMat];
        arc=new Arc[numMat];
        nonarc=new Arc[numMat];
        arc_choisi=new Arc[numMat];
        racine = new int[numMat];
        TabSommet = new String[numMat];
        parasite =new String[numMat];
        sommet_continu = "";
        Jp.add(Mat, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));
        Jp.repaint();
        Jp.setVisible(true);
        Jp.setSize(350, 350);

        playAnchor.setVisible(false);
        dataPane.setVisible(true);
    }
    public void doingIt(){
        nb_solution=1;
        nb_next_clique++;

        //Starting loop
        while(numMat>0){
            System.out.println("\n ========> loop number : "+numMat);
            int indice_mat;
            indice_mat=numMat-1;

            if(numMat == Mat.JTF.length){
                for (int i=0;i<Mat.JTF.length;i++){
                    for (int j=0;j<Mat.JTF.length;j++){
                        if(Mat.JTF[i][j].getText().equals("") || i==j)
                            MatInitiale[indice_mat][i][j] = infini;
                        else {
                            MatInitiale[indice_mat][i][j] = Integer.parseInt(Mat.JTF[i][j].getText());
                        }
                    }
                }
            }
            else if(numMat<Mat.JTF.length){
                if(numMat==1)
                    MatInitiale[indice_mat]= NouvelleM[indice_mat+1];
                else if((arc_choisi[indice_mat+1].debSommet.equals(nonarc[indice_mat+1].debSommet))&&
                        (arc_choisi[indice_mat+1].finSommet.equals(nonarc[indice_mat+1].finSommet)))
                    MatInitiale[indice_mat]=MatATraiter[indice_mat+1];
                else
                    MatInitiale[indice_mat]=MatApSupprPar[indice_mat+1];
            }
            System.out.println("Matrice initiale");
            for (int i=0;i<MatInitiale[indice_mat].length;i++){
                for (int j=0;j<MatInitiale[indice_mat].length;j++){
                    System.out.print(MatInitiale[indice_mat][i][j]+"\t\t");
                }
                System.out.println();
            }

            //Cost reduction
            Mat2[indice_mat]=updateMat(MatInitiale[indice_mat]);//même dimension à matrice initiale
            System.out.println("Matrice des couts reduits");
            for (int i=0;i<Mat2[indice_mat].length;i++){
                for (int j=0;j<Mat2[indice_mat].length;j++){
                    System.out.print(Mat2[indice_mat][i][j]+"\t\t");
                }
                System.out.println();
            }
            if(numMat == Mat.JTF.length)
                racine[indice_mat] = calculMinorant(MatInitiale[indice_mat]);
            else if(numMat<Mat.JTF.length){
                racine[indice_mat] = arc_choisi[indice_mat+1].borneArc;
            }

            //regret
            MatReg[indice_mat]=regret(Mat2[indice_mat]);//même dimension à matrice initiale
            System.out.println("Matrice des regrets");
            for (int i=0;i<MatReg[indice_mat].length;i++){
                for (int j=0;j<MatReg[indice_mat].length;j++){
                    if(Mat2[indice_mat][i][j]==0)
                        System.out.print(MatReg[indice_mat][i][j]+"\t\t");
                    else
                        System.out.print("-"+"\t\t");
                }
                System.out.println();
            }
            //evaluation des sommets
            int Max1= MaxRegret(MatReg[indice_mat]);//regret maximum
            int NumLiMax1 = LigneMaxRegret(MatReg[indice_mat], Max1);//indice ligne regret max
            int NumColMax1 = ColMaxRegret(MatReg[indice_mat], Max1);//indice col regret max
            System.out.println("lignerg: "+NumLiMax1+",  colrg:"+NumColMax1);
            String sommet_deb_nonarc = "["+labLi[NumLiMax1];
            String sommet_fin_nonarc = labCol[NumColMax1]+"]";
            int borne_nonarc;
            if(Max1 == infini)
                borne_nonarc = infini;
            else
                borne_nonarc = racine[indice_mat]+Max1;
            nonarc[indice_mat] = new Arc(sommet_deb_nonarc,sommet_fin_nonarc, borne_nonarc);


            //nouvelle matrice apres suppression ligne regret max et col regret max
            if(MatInitiale[indice_mat].length>1){
                System.out.println("rg: "+labLi[NumLiMax1]+labCol[NumColMax1]);
                NouvelleM[indice_mat] = MajM(Mat2[indice_mat], NumLiMax1, NumColMax1);//dimension: matrice initiale-1
                labLi2 = MajLab(labLi, NumLiMax1);//mise à jour nom ligne
                labCol2 = MajLab(labCol, NumColMax1);//mise à jour nom col

                //interdiction arc_parasite jusqu'à 3*3
                if(MatInitiale[indice_mat].length>2){
                    String sommet_deb_par="";
                    String sommet_fin_par="";
                    int nb_arc = 0;
                    for(int i=0;i<TabSommet.length;i++){
                        if(TabSommet[i]!=null)
                            nb_arc++;
                    }

                    if(nb_arc == 0){//entrer le 1er sommet dans le tableau
                        TabSommet[0] = labLi[NumLiMax1]+labCol[NumColMax1];
                        sommet_deb_par = ""+labCol[NumColMax1];
                        sommet_fin_par = ""+labLi[NumLiMax1];
                    }
                    else{
                        TabSommet[nb_arc] = labLi[NumLiMax1]+labCol[NumColMax1];
                        //trouver l'arc cont
                        sommet_continu=TabSommet[nb_arc];
                        for(int i=0;i<TabSommet.length;i++){
                            if(TabSommet[i]!=null){
                                if((""+TabSommet[i].charAt(1)).equals(labLi[NumLiMax1])){
                                    sommet_continu = TabSommet[i]+sommet_continu;
                                }
                                else
                                    sommet_continu = ""+sommet_continu;

                                if((""+TabSommet[i].charAt(0)).equals(labCol[NumColMax1]))
                                    sommet_continu = sommet_continu+TabSommet[i];
                                else
                                    sommet_continu = sommet_continu+"";
                            }
                        }
                        for(int j=0;j<TabSommet.length;j++){
                            if(TabSommet[j]!=null){
                                if((""+sommet_continu.charAt(0)).trim().equals(""+TabSommet[j].charAt(1))){
                                    sommet_continu = TabSommet[j]+sommet_continu;
                                }
                                else{
                                    sommet_continu = ""+sommet_continu;
                                }
                                if((""+sommet_continu.charAt(sommet_continu.length()-1)).trim().equals(""+TabSommet[j].charAt(0))){
                                    sommet_continu = sommet_continu+TabSommet[j];
                                }
                                else{
                                    sommet_continu = sommet_continu+"";
                                }
                            }
                        }
                        System.out.println("sommet continu: "+sommet_continu);
                        if(sommet_continu.length()!=0){
                            sommet_deb_par = ""+sommet_continu.charAt(sommet_continu.length()-1);
                            sommet_fin_par = ""+sommet_continu.charAt(0);
                        }
                        else{
                            sommet_deb_par = ""+labCol[NumColMax1];
                            sommet_fin_par = ""+labLi[NumLiMax1];
                        }
                    }
                    System.out.println("parasite: "+sommet_deb_par+sommet_fin_par);
                    liparasite = 0;
                    colparasite = 0;
                    for(int i=0;i<labLi2.length;i++){
                        if(labLi2[i].equals(sommet_deb_par))
                            liparasite=i;
                    }
                    for(int j=0;j<labCol2.length;j++){
                        if(labCol2[j].equals(sommet_fin_par))
                            colparasite=j;
                    }

                    MatApSupprPar[indice_mat]= SupprimerParasite(NouvelleM[indice_mat], liparasite, colparasite);//dim-1

                    String sommet_deb_arc = labLi[NumLiMax1];
                    String sommet_fin_arc = labCol[NumColMax1];

                    borne_arc = racine[indice_mat]+calculMinorant(MatApSupprPar[indice_mat]);
                    System.out.println("nonarc: "+nonarc[indice_mat].debSommet+nonarc[indice_mat].debSommet+",  borne arc: "+nonarc[indice_mat].borneArc);
                    System.out.println("arc: "+sommet_deb_arc+sommet_fin_arc+",  borne arc: "+borne_arc);
                    arc[indice_mat] = new Arc(sommet_deb_arc,sommet_fin_arc, borne_arc);

                    if(nonarc[indice_mat].borneArc<arc[indice_mat].borneArc){
                        arc_choisi[indice_mat]=new Arc(nonarc[indice_mat].debSommet, nonarc[indice_mat].finSommet, nonarc[indice_mat].borneArc);
                    }
                    else{
                        arc_choisi[indice_mat]=new Arc(arc[indice_mat].debSommet, arc[indice_mat].finSommet, arc[indice_mat].borneArc);
                    }

                    if((arc_choisi[indice_mat].debSommet.equals(nonarc[indice_mat].debSommet))&&
                            (arc_choisi[indice_mat].finSommet.equals(nonarc[indice_mat].finSommet))){
                        existe=true;
                        System.out.println("n° étape : "+numMat);
                        TabSommet[nb_arc]=null;
                        sommet_deb_par = ""+nonarc[indice_mat].debSommet.charAt(1);
                        sommet_fin_par = ""+nonarc[indice_mat].finSommet.charAt(0);
                        int nb_parasite = 0;
                        //interdiction de l'arc (x,y)
                        System.out.println("parasite: "+sommet_deb_par+sommet_fin_par);
                        liparasite = 0;
                        colparasite = 0;
                        for(int i=0;i<labLi.length;i++){
                            if(sommet_deb_par.equals(labLi[i]))
                                liparasite=i;
                        }
                        for(int j=0;j<labCol.length;j++){
                            if(sommet_fin_par.equals(labCol[j]))
                                colparasite=j;
                        }
                        labLi2=labLi;
                        labCol2=labCol;
                        System.out.println("même parasite? "+labLi[liparasite]+labCol[colparasite]);
                        MatApSupprPar2[indice_mat]= SupprimerParasite(Mat2[indice_mat], liparasite, colparasite);//dim-1
                        MatATraiter[indice_mat]=updateMat(MatApSupprPar2[indice_mat]);
                    }
                }//fin if numMat>2
                else if(MatInitiale[indice_mat].length==2){
                    borne_arc = racine[indice_mat]+calculMinorant(MatApSupprPar[indice_mat]);
                    arc[indice_mat] = new Arc(labLi[NumLiMax1],labCol[NumColMax1], borne_arc);
                    if(nonarc[indice_mat].borneArc<arc[indice_mat].borneArc){
                        arc_choisi[indice_mat]=new Arc(nonarc[indice_mat].debSommet, nonarc[indice_mat].finSommet, nonarc[indice_mat].borneArc);
                    }
                    else{
                        arc_choisi[indice_mat]=new Arc(arc[indice_mat].debSommet, arc[indice_mat].finSommet, arc[indice_mat].borneArc);
                    }
                    TabSommet[indice_mat]=arc_choisi[indice_mat].debSommet+arc_choisi[indice_mat].finSommet;
                }
                if(nonarc[indice_mat].borneArc==arc[indice_mat].borneArc)
                    nb_solution++;
            }//fin if numMat>1
            else{

                borne_arc = racine[indice_mat]+calculMinorant(Mat2[indice_mat]);
                arc[indice_mat] = new Arc(labLi[0],labCol[0], borne_arc);
                if(nonarc[indice_mat].borneArc<arc[indice_mat].borneArc){
                    arc_choisi[indice_mat]=new Arc(nonarc[indice_mat].debSommet, nonarc[indice_mat].finSommet, nonarc[indice_mat].borneArc);
                }
                else{
                    arc_choisi[indice_mat]=new Arc(arc[indice_mat].debSommet, arc[indice_mat].finSommet, arc[indice_mat].borneArc);
                }
                TabSommet[indice_mat]=arc_choisi[indice_mat].debSommet+arc_choisi[indice_mat].finSommet;
            }
            if((arc_choisi[indice_mat].debSommet.length()==1)&&(arc_choisi[indice_mat].finSommet.length()==1)){
                String sommet_dep = ""+arc_choisi[indice_mat].debSommet;
                String sommet_arr = ""+arc_choisi[indice_mat].finSommet;
                int indiceDep=0, indiceArr=0;
                for(int i=0; i<labIni.length;i++){
                    if(sommet_dep.equals(labIni[i]))
                        indiceDep = i;
                    else if(sommet_arr.equals(labIni[i]))
                        indiceArr = i;
                }
                if(som.length!=0){
                    System.out.println("arc a dessiner "+indice_mat+"  :"+sommet_dep+sommet_arr);
                    arcD[indice_mat]= new DessinerArc(som[indiceDep].getLocation().x+10, som[indiceDep].getLocation().y+10,som[indiceArr].getLocation().x+10, som[indiceArr].getLocation().y+10);
                    if(indiceDep!=indiceArr){
                        System.out.println("val_arc "+sommet_dep+sommet_arr+" :"+Mat.JTF[indiceDep][indiceArr].getText());
                        arcD[indice_mat].setValeur(Integer.parseInt(Mat.JTF[indiceDep][indiceArr].getText()));
                    }
                    JP_Hamilton.add(arcD[indice_mat]);
                    JP_Hamilton.repaint();
                }
            }

            if((numMat==1)&&(existe)){
                System.out.println("MatApSupprPar: "+MatApSupprPar[0].length);
                System.out.println("Mat2: "+Mat2[0].length);

                if(MatInitiale[0].length>2){
                    reste_mat=new int[MatApSupprPar[0].length][MatApSupprPar[0].length];
                    for(int i=0; i<MatApSupprPar[0].length;i++){
                        for(int j=0;j<MatApSupprPar[0].length;j++){
                            reste_mat[i][j]=MatApSupprPar[0][i][j];
                            System.out.print(MatApSupprPar[0][i][j]+"\t\t");
                        }
                        System.out.println("");
                    }
                }
                else if(MatInitiale[0].length==2){
                    reste_mat=new int[NouvelleM[0].length][NouvelleM[0].length];
                    for(int i=0; i<NouvelleM[0].length;i++){
                        for(int j=0;j<NouvelleM[0].length;j++){
                            reste_mat[i][j]=NouvelleM[0][i][j];
                        }
                        System.out.println("");
                    }
                }
                else{
                    reste_mat=new int[Mat2[0].length][Mat2[0].length];
                    for(int i=0; i<Mat2[0].length;i++){
                        for(int j=0;j<Mat2[0].length;j++){
                            reste_mat[i][j]=Mat2[0][i][j];
                            System.out.print(Mat2[0][i][j]+"\t\t");
                        }
                        System.out.println("");
                    }
                }
            }
            labLi = labLi2;
            labCol = labCol2;

            numMat--;
        }//fin while

        //Traitement du reste de la matrice
        for(int i=0; i<reste_mat.length;i++){
            for(int j=0;j<reste_mat.length;j++){
                System.out.print(reste_mat[i][j]+"\t\t");
            }
            System.out.println();
        }
        for(int i=0;i<labLi.length;i++)
            System.out.print(labLi[i]);
        for(int i=0;i<labCol.length;i++)
            System.out.print(labCol[i]);
        System.out.println();

        if(reste_mat.length!=0){
            reste_arc=new Arc[reste_mat.length];
            reste_nonarc=new Arc[reste_mat.length];
            if(reste_mat.length==1){
                reste_arc[0] = new Arc(labLi[0],labCol[0],arc_choisi[0].borneArc) ;
                reste_nonarc[0] = new Arc("["+labLi[0], labCol[0]+"]", infini);
                int indiceDep=0, indiceArr=0;
                for(int i=0; i<labIni.length;i++){
                    if(labLi[0].equals(labIni[i]))
                        indiceDep = i;
                    else if(labCol[0].equals(labIni[i]))
                        indiceArr = i;
                }
                if(som.length!=0){
                    reste_arcD[0]= new DessinerArc(som[indiceDep].getLocation().x+10, som[indiceDep].getLocation().y+10,som[indiceArr].getLocation().x+10, som[indiceArr].getLocation().y+10);
                    if(indiceDep!=indiceArr){
                        reste_arcD[0].setValeur(Integer.parseInt(Mat.JTF[indiceDep][indiceArr].getText()));
                    }
                    JP_Hamilton.add(reste_arcD[0]);
                    JP_Hamilton.repaint();
                }
            }
        }

        Jp.repaint();
        JP_Arbre.removeAll();
        int niv;
        if(reste_mat.length!=0){
            niv=Mat.JTF.length+reste_mat.length;
        }
        else
            niv = Mat.JTF.length;
        Feuille(niv, deuxieme_sol);
        JP_Arbre.repaint();
        int i=arc_choisi.length;
        while(i>0){
            System.out.print(arc_choisi[i-1].debSommet+arc_choisi[i-1].finSommet+"\t");
            i--;
        }
        System.out.println();
    }
    public boolean verifEntier(String s){
        boolean b=false;
        char cr[]=new char[s.length()];
        s.getChars(0,s.length(),cr,0);
        if (s.length()<1) b=true;
        for (int i=0;i<s.length() && !b;i++)
            if (cr[i]<'0' || cr[i]>'9') b=true;
        return !b;
    }
    public void Graphe_Hamilton(int i) {
        float angle=(float)((2*Pi)/i);
        int rayon=120;
        float ang=0;
        JP_Hamilton.removeAll();
        for(int a=0;a<i;a++){
            String lab[]= new String[i];
            lab[a]=""+(a);
            lab[a]=" "+cars[a];
            som[a]= new Sommet((int)(160-rayon*Math.sin(ang)),(int)(140-rayon*Math.cos(ang)),lab[a]);
            JP_Hamilton.add(som[a]);
            ang+=angle;
        }
    }
    public void Feuille(int niv, boolean deux_sol) {
        JP_Arbre.removeAll();
        System.out.println("niveau: " + niv);
        String borne;
        String s = "", sg = "", sd = "", s_choisi;
        int indice_arc = arc_choisi.length;
        int indice_reste_arc = 0;
        String bg, bd;

        for (int y = 20, x = 200, i = 0; i < niv + 1; i++) {
            if (i == 0) {
                borne = "" + calculMinorant(Mat.ConvertJTF(Mat.JTF));
                s = "R=" + borne;
                somOv[i][0] = new SommetOval(x, y, s);
                JP_Arbre.add(somOv[i][0]);
                JP_Arbre.repaint();
                y += 60;
            } else if (indice_arc - 1 >= 0) {
                s_choisi = arc_choisi[indice_arc - 1].debSommet + arc_choisi[indice_arc - 1].finSommet;

                if (nonarc[indice_arc - 1].borneArc != (int) Double.POSITIVE_INFINITY)
                    bg = "" + nonarc[indice_arc - 1].borneArc;
                else
                    bg = "oo";
                if (arc[indice_arc - 1].borneArc != (int) Double.POSITIVE_INFINITY)
                    bd = "" + arc[indice_arc - 1].borneArc;
                else
                    bd = "oo";
                sg = "" + nonarc[indice_arc - 1].debSommet + nonarc[indice_arc - 1].finSommet + "=" + bg;
                if (indice_arc < Mat.JTF.length) {
                    if (deux_sol) {
                        if (nonarc[indice_arc].borneArc <= arc[indice_arc].borneArc)
                            x = x - 120 - 60;
                        else/**/
                            x -= 60;
                    } else {
                        if (nonarc[indice_arc].borneArc < arc[indice_arc].borneArc)
                            x = x - 120 - 60;
                        else/**/
                            x -= 60;
                    }
                } else
                    x -= 60;

                somOv[i][0] = new SommetOval(x, y, sg);
                arc_arbre[i] = new ArcArbre(x + 80, y - 50, x + 10, y + 10);
                JP_Arbre.add(somOv[i][0]);
                JP_Arbre.add(arc_arbre[i]);
                x += 120;

                sd = "" + arc[indice_arc - 1].debSommet + arc[indice_arc - 1].finSommet + "=" + bd;
                somOv[i][1] = new SommetOval(x, y, sd);
                arc_arbre[i] = new ArcArbre(x - 40, y - 50, x + 35, y + 10);
                JP_Arbre.add(somOv[i][1]);
                JP_Arbre.add(arc_arbre[i]);
                JP_Arbre.repaint();
                y += 60;
                indice_arc--;
            } else if ((reste_mat.length != 0) && (indice_reste_arc < reste_mat.length)) {
                if ((reste_nonarc.length != 0) && (reste_arc.length != 0)) {
                    if (reste_nonarc[indice_reste_arc].borneArc != (int) Double.POSITIVE_INFINITY)
                        bg = "" + reste_nonarc[indice_reste_arc].borneArc;
                    else
                        bg = "oo";
                    sg = "" + reste_nonarc[indice_reste_arc].debSommet + reste_nonarc[indice_reste_arc].finSommet + "=" + bg;
                    x -= 60;
                    somOv[i][0] = new SommetOval(x, y, sg);
                    arc_arbre[i] = new ArcArbre(x + 80, y - 50, x + 10, y + 10);
                    JP_Arbre.add(somOv[i][0]);
                    JP_Arbre.add(arc_arbre[i]);

                    x += 120;
                    if (reste_arc[indice_reste_arc].borneArc != (int) Double.POSITIVE_INFINITY)
                        bd = "" + reste_arc[indice_reste_arc].borneArc;
                    else
                        bd = "oo";
                    sd = "" + reste_arc[indice_reste_arc].debSommet + reste_arc[indice_reste_arc].finSommet + "=" + bd;

                    somOv[i][1] = new SommetOval(x, y, sd);
                    arc_arbre[i] = new ArcArbre(x - 40, y - 50, x + 35, y + 10);
                    JP_Arbre.add(somOv[i][1]);
                    JP_Arbre.add(arc_arbre[i]);
                    JP_Arbre.repaint();
                    y += 60;
                }
                indice_reste_arc++;
            }
        }
    }

    public int calculMinorant(int M[][]){
        int M2[][] = new int[M.length][M.length];
        for(int i=0;i<M.length;i++)
            for(int j=0;j<M.length;j++)
                M2[i][j] = M[i][j];

        int Min=0;
        for(int i=0; i<M2.length; i++){
            Min+=minRow(i, M2);
            updateRow(minRow(i, M2), i, M2);
        }
        for(int j=0; j<M2.length; j++){
            Min+=minCol(j, M2);
        }
        return Min;
    }

    //le min de la ligne:
    public int minRow(int l, int M[][]){
        int n=M.length;
        int mr=1000000;
        for(int j=0; j<n; j++){
            if((M[l][j]<mr)&&(M[l][j]!=-1))
                mr=M[l][j];
        }
        if(mr==1000000)
            mr=0;
        return mr;
    }
    //m-a-j de la ligne:
    public void updateRow(int r, int l, int M[][]){
        int n=M.length;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++){
                if(i==l)
                    if(M[i][j]!=-1)
                        M[i][j]=M[i][j]-r;
            }
    }

    //min de la colonne:
    public int minCol(int c, int M[][]){
        int n=M.length;
        int mc=(int)Double.POSITIVE_INFINITY;
        for(int i=0; i<n; i++){
            //if((M[i][c]<mc)&&(M[i][c]!=-1))
            if(M[i][c]<mc)
                mc=M[i][c];
        }
        if(mc==(int)Double.POSITIVE_INFINITY)
            mc=0;
        return mc;
    }

    //réduire M en enlevant les mins des lignes puis des cols
    //pour avoir des 0 ds chaque ligne et chaque colonne:
    public int[][] updateMat(int M[][])	{
        int M2[][]=new int[M.length][M.length];

        for(int i=0; i<M.length; i++)
            for(int j=0; j<M.length; j++){
                if(M[i][j]>1000000000)
                    M2[i][j] = (int)Double.POSITIVE_INFINITY;
                else
                    M2[i][j]= M[i][j]-minRow(i, M);
            }

        int M3[][]=new int[M.length][M.length];
        for(int k=0; k<M.length; k++){
            for(int l=0; l<M.length; l++){
                if(M2[l][k]>1000000000)
                    M3[l][k] = (int)Double.POSITIVE_INFINITY;
                else
                    M3[l][k]=M2[l][k]-minCol(k, M2);
            }
        }
        return M3;
    }

    //choix de l'arête:
    public Arete choixArete(int M[][], ArrayList<Arete> Sp){
        Arete art=new Arete();
        Arete art2=new Arete();
        Arete artf=new Arete();
        int n=M.length;
        artf.l=-1; artf.c=-1;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(M[i][j]!=-1){
                    art.l=i; art.c=j;
                    art2.c=i; art2.l=j;
                    if(!Sp.contains(art))
                        if((!Sp.contains(art2))&&(art.l!=art.c)){
                            artf.l=i;	artf.c=j;
                        }
                }
        if((artf.l==-1)||(artf.c==-1))
            return null;
        return artf;
    }
    // avec Arete est l'element choisit de M
		/*public Arete(){
			this.l=-1;
			this.c=-1;
		}*/

    //m-a-j de la ligne et la colonne de l'élément choisi pour la sol droite
    public int[][] updateMatLC(int l, int c, int M[][])	{
        int n=M.length;
        int M2[][]=M;
        if((l!=-1)||(c!=-1)){
            for(int i=0; i<n; i++)
                M2[l][i]=-1;
            for(int j=0; j<n; j++)
                M2[j][c]=-1;
        }
        M2[c][l]=-1; //
        return M2;
    }

    //m-a-j de l'élément choisi pour l'exploration gauche:
    public int[][] updateMatCase(int l, int c, int M[][]){
        int M2[][]=M;
        M2=M;
        if((l!=-1)||(c!=-1))
            M2[l][c]=-1;
        M2[c][l]=-1;
        return M2;
    }
    //
    //calcul de regret
    public int minRowRg(int l, int M[][], int j_prime){
        int n=M.length;
        int mr=(int)Double.POSITIVE_INFINITY;
        for(int j=0; j<n; j++){
            if((M[l][j]<mr)&&(M[l][j]!=-1))
                if(j!=j_prime)
                    mr=M[l][j];
        }
        return mr;
    }
    public int minColRg(int c, int M[][], int i_prime){
        int n=M.length;
        int mc=(int)Double.POSITIVE_INFINITY;
        for(int i=0; i<n; i++){
            if((M[i][c]<mc)&&(M[i][c]!=-1))
                if(i!= i_prime)
                    mc=M[i][c];
        }
        return mc;
    }

    public int[][] regret(int M[][]){
        int rg[][]=new int[M.length][M.length];
        int nb=0;
        int i_prime, j_prime;
        int infini = (int)Double.POSITIVE_INFINITY;
        for(int i=0; i<M.length; i++){
            for(int j=0; j<M.length; j++){
                if(M[i][j]==0){
                    i_prime=i;
                    j_prime=j;
                    nb=nb+1;
                    //System.out.println("minli: "+minRowRg(i, M, j_prime)+", minco: "+minColRg(j, M, i_prime));
                    if((minRowRg(i, M, j_prime) == infini)||(minColRg(j, M, i_prime) == infini))
                        rg[i][j]=infini;
                    else
                        rg[i][j] = minRowRg(i, M, j_prime) + minColRg(j, M, i_prime);
                }
            }
        }

        return rg;
    }
    public int MaxRegret(int M[][]){
        int max = 0;
        for(int i=0; i<M.length; i++){
            for(int j=0; j<M.length; j++){
                if(M[i][j]>max)
                    max = M[i][j];
            }
        }
        return max ;
    }

    public int LigneMaxRegret(int M[][], int Max){
        int numligne = 0;
        boolean trouve=false;

        for(int i=0; i<M.length; i++){
            for(int j=0; j<M.length; j++){
                if(M[i][j]== Max){
                    numligne = i;
                    trouve=true;
                    break;
                }
                if(trouve)
                    break;
            }
        }
        return numligne;

    }

    public int ColMaxRegret(int M[][], int Max){
        int numcol = 0;
        boolean trouve=false;
        for(int i=0; i<M.length; i++){
            for(int j=0; j<M.length; j++){
                if(M[i][j]== Max){
                    numcol = j;
                    trouve=true;
                    break;
                }
                if(trouve)
                    break;
            }
        }
        return numcol;

    }
    public int[][] MajM(int M[][], int NumLigneMax1, int NumColMax1){
        int nouveau[][] = new int[M.length-1][M.length-1];
        for(int i=0; i<NumLigneMax1; i++){
            for(int j=0; j<M.length; j++){
                if(j<NumColMax1)
                    nouveau[i][j] = M[i][j];
                else if(j>NumColMax1)
                    nouveau[i][j-1] = M[i][j];
            }
        }
        for(int i=NumLigneMax1+1;i<M.length;i++){
            for(int j=0; j<M.length; j++){
                if(j<NumColMax1)
                    nouveau[i-1][j] = M[i][j];
                else if(j>NumColMax1)
                    nouveau[i-1][j-1] = M[i][j];
            }
        }
        return nouveau;
    }
    public String[] MajLab(String Lab[], int numLab){
        String nouveauLab[]=new String[Lab.length-1];
        for(int i=0;i<Lab.length;i++){
            if(i<numLab)
                nouveauLab[i] = Lab[i];
            else if(i>numLab)
                nouveauLab[i-1] = Lab[i];
        }
        return nouveauLab;
    }
    public String GetNomParasite(String Sommet[]){
        String arc_parasite = "";
        int nb_sommet = 0;
        for(int i=0; i<Sommet.length; i++){
            if(Sommet[i]!= null)
                nb_sommet = nb_sommet+1;
        }
        if(nb_sommet == 2)
            arc_parasite = Sommet[1]+Sommet[0];
        return arc_parasite;
    }

    public int[][] SupprimerParasite(int M[][], int ligne_par, int col_par){
        int nouveau[][] = new int[M.length][M.length];
        for(int i=0; i<M.length; i++){
            for(int j=0; j<M.length; j++){
                if((i==ligne_par)&&(j==col_par))
                    nouveau[i][j] = (int)Double.POSITIVE_INFINITY;
                else
                    nouveau[i][j] = M[i][j];
            }
        }
        return nouveau;
    }
}