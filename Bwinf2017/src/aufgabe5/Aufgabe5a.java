package aufgabe5;

import java.awt.Graphics;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Aufgabe5a implements Runnable {

	public static Aufgabe5a Aufgabe;
	public Renderer_a renderer;

	Thread t;

	Image image1;
	Image image2;
	Image image3;
	JFrame jframe;

	boolean aufstellung = true;
	
	int j = 0;
	int i = 0;
	int o = 1;

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

	Aufgabe5a() {

		initComponents();
		renderer = new Renderer_a();
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
		g.drawImage(image3, Rookposition_x + i*Playerheight, Rookposition_y, Playerheight, Playerheight, null);
		
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (o < 4) {

			// Bauern ziehen
			update();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Turm zieht
			i = j%8;
			update();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Aktualisierung der Variablen für den nächsten Zug
			if (j == 8) {
				j = 0;
				o++;
			}
			
			
			pawn[j] = o;
			j++;
			
			

			
		}
		t.interrupt();
	}

	public void update() {
		renderer.repaint();
	}

	public static void main(String[] args) {
		Aufgabe = new Aufgabe5a();
	}

}