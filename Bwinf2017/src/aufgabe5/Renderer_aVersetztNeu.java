package aufgabe5;

import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Renderer_aVersetztNeu extends JPanel {


	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Aufgabe5a_VersetztNeu.Aufgabe.repaint(g);

	}

}
