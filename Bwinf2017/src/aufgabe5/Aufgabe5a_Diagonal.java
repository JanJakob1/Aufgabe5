package aufgabe5;

import java.awt.Graphics;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Aufgabe5a_Diagonal implements Runnable {

	public static Aufgabe5a_Diagonal Aufgabe;
	public Renderer_aDiagonal renderer;

	Thread t;

	Image image1;
	Image image2;
	Image image3;
	JFrame jframe;

	boolean aufstellung = true;

	int j = 0;
	int i = 0;
	int o = 0;

	int width = 800;
	int height = width;

	// Gr��e von Bauern und Turm
	int Playerheight = width / 8;

	// Startwert Bauer links unten
	int Pawnposition_x = 0;
	int Pawnposition_y = width - Playerheight;

	// Startwert Turm
	int Rookposition_x = 0;
	int Rookposition_y = 0;

	// Array f�r die Bauern
	int[] pawn = new int[8];

	Aufgabe5a_Diagonal() {

		initComponents();
		renderer = new Renderer_aDiagonal();
		for (int m = 0; m < 8; m++) {
			pawn[m] = m;
		}
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
		g.drawImage(image3, Rookposition_x + i * Playerheight, Rookposition_y, Playerheight, Playerheight, null);

	}

	@Override
	public void run() {

		update();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (o < 6) {

			// Bauern ziehen
			// Der Turm kann nur versuchen so lange wie m�glich nicht gefangen zu werden,
			// indem er sich nicht aus der Ecke bewegt
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (pawn[j] < 6) {
				pawn[j] = pawn[j] + 1;
			}

			j++;
			// Aktualisierung der Variablen f�r den n�chsten Zug
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
		Aufgabe = new Aufgabe5a_Diagonal();
	}

}