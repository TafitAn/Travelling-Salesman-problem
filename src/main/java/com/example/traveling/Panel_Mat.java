package com.example.traveling;


import javax.swing.*;
import java.awt.*;

public class Panel_Mat extends JPanel {
	public Panel_Mat() {
		initComponents();
                new JScrollPane(this);
	}

	private void initComponents() {
		setLayout(new GridBagLayout());
		((GridBagLayout)getLayout()).columnWidths = new int[] {500, 0};
		((GridBagLayout)getLayout()).rowHeights = new int[] {476, 44, 0};
		((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
		((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
	}

}

