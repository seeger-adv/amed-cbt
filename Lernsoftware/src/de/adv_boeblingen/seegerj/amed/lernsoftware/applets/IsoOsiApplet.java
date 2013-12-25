package de.adv_boeblingen.seegerj.amed.lernsoftware.applets;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class IsoOsiApplet
		extends Applet {
	private static List<String> sLayers;

	static {
		sLayers = new ArrayList<String>(7);
		sLayers.add("Physical");
		sLayers.add("Data Link");
		sLayers.add("Network");
		sLayers.add("Transport");
		sLayers.add("Session");
		sLayers.add("Presentation");
		sLayers.add("Application");
	}

	private final Thread mAnimationThread = new Thread(new Runnable() {
		@Override
		public void run() {
			repaint(100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});

	@Override
	public void start() {
		super.start();
		mAnimationThread.start();
	}

	@Override
	public void stop() {
		super.stop();
		// TODO: locking-freundliche stop-Methode implementieren
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		paintLayers(g);
	}

	private void paintLayers(Graphics g) {
		for (int i = 0; i < sLayers.size(); i++) {
			String layer = sLayers.get(i);
			g.drawString(layer, 5, getHeight() - i * 20 - 5);
		}
	}
}
