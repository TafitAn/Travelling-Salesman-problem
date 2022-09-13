package com.example.traveling;


import javax.swing.*;
import java.awt.*;

public class panneauImage extends JComponent {
    boolean dimensionAutomatique = true;
    private Image imageFond = new ImageIcon("Image11.jpg").getImage();
    
   @Override
    public void paintComponent(Graphics fond) {
        if (dimensionAutomatique)
           fond.drawImage(imageFond, 0, 0, getWidth(), getHeight(), null);
        else
           fond.drawImage(imageFond, 0, 0, imageFond.getWidth(null), imageFond.getHeight(null), null);
    }
}
