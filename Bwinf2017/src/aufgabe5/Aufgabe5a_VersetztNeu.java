package aufgabe5;

import java.awt.Graphics;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Aufgabe5a_VersetztNeu implements Runnable {

	public static Aufgabe5a_VersetztNeu Aufgabe;
	public Renderer_aVersetztNeu renderer;

	Thread t;

	Image image1;
	Image image2;
	Image image3;
	JFrame jframe;

	boolean aufstellung = true;

	int width = 800;
	int height = width;

	// Größe von Bauern und Turm
	int Playerheight = width / 8;

	// Startwert Bauer
	int Pawnposition_x = 0;
	int Pawnposition_y = width / 2;

	// Startwert Turm
	int Rookposition_x = 0;
	int Rookposition_y = 0;

	// Array für die Bauern
	int[] pawn = new int[8];

	Aufgabe5a_VersetztNeu() {

		initComponents();
		renderer = new Renderer_aVersetztNeu();
		jframe.getContentPane().add(renderer);
		t = new Thread(this);
		t.start();
	}

	public void initComponents() {

		jframe = new JFrame();
		jframe.setTitle("Aufgabe 5a");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(width + 10, height + 30);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
	}

	public void repaint(Graphics g) {

		// Hintergrundbild
		ImageIcon img = new ImageIcon("img.png");
		image1 = img.getImage();
		g.drawImage(image1, 0, 0, height, width, null);

		// Bild des Bauerns
		ImageIcon img1 = new ImageIcon("img1.png");
		image2 = img1.getImage();

		// Bild des Turms
		ImageIcon img2 = new ImageIcon("img2.png");
		image3 = img2.getImage();

		// Bauern anzeigen
		int p = 0;
		while (p <= 7) {

			g.drawImage(image2, Pawnposition_x + p * Playerheight, Pawnposition_y - pawn[p] * Playerheight,
					Playerheight, Playerheight, null);
			p++;
		}

		// Turm anzeigen
		g.drawImage(image3, Rookposition_x, Rookposition_y, Playerheight, Playerheight, null);

	}

	@Override
	public void run() {

		// Aufstellung mit versetzten Bauern
		for (int i = 0; i < 4; i++) {
			pawn[i * 2 + 1] = 1;
		}
		update();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Die vier Bauern, die hinten sind ziehen nach vorne
		for (int n = 1; n < 4; n++) {
			pawn[n * 2] = 1;
			update();
			try {
				Thread.sleep(1400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Die mittleren Bauern gehen vor und lassen zwei mögliche Bereiche für den Turm
		// entstehen
		int o = 2;
		for (int m = 0; m < 2; m++) {
			for (int n = m; n < 4 - m; n++) {

				pawn[2 + n] = o;
				update();
				try {
					Thread.sleep(1400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			o++;

		}

		// Einer der beiden vordersten geht vor und kesselt den Turm ein
		pawn[3] = o;
		update();

		// Die Diagonale wird nun zu einer Reihe
		int j = 0;
		o = 3;

		while (o < 6) {

			try {
				Thread.sleep(1400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (pawn[j] < 6) {
				pawn[j] = pawn[j] + 1;
			}

			j++;
			// Aktualisierung der Variablen für den nächsten Zug
			if (j == 6 - o) {
				o++;
				j = 0;
			}
			update();

		}

		t.interrupt();
	}

	public void update() {
		renderer.repaint();
	}

	public static void main(String[] args) {
		Aufgabe = new Aufgabe5a_VersetztNeu();
	}

}