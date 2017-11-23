package aufgabe5;

import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Renderer_aDiagonal extends JPanel {


	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Aufgabe5a_Diagonal.Aufgabe.repaint(g);

	}

}
