package de.adv_boeblingen.seegerj.amed.lernsoftware.applets;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class IsoOsiApplet
		extends Applet {
	private static List<Layer> sNetworkingLayers;

	static {
		sNetworkingLayers = new ArrayList<Layer>(7);
		sNetworkingLayers.add(new Layer("Physical", "data"));
		sNetworkingLayers.add(new Layer("Data Link", "data"));
		sNetworkingLayers.add(new Layer("Network", "data"));
		sNetworkingLayers.add(new Layer("Transport", "data"));
		sNetworkingLayers.add(new Layer("Session", "data"));
		sNetworkingLayers.add(new Layer("Presentation", "data"));
		sNetworkingLayers.add(new Layer("Application", "data"));

		// AnalyticsConfigData config = new
		// AnalyticsConfigData(Constants.ANALYTICS_KEY);
		// AnalyticsController.submitAnalytics(config, "applet", "show");
	}

	private static final Color backgroundColor = new Color(52, 52, 52);
	private static final Font font = new Font("Helvetica", Font.PLAIN, 24);

	private final Thread mAnimationThread = new Thread(new AnimationRunnable());

	private final Panel layerPanel = new Panel();
	private final Panel dataPanel = new Panel();
	private final TextArea dataView = new TextArea();

	@Override
	public void init() {
		super.init();
		setBackground(backgroundColor);
		setLayout(new GridBagLayout());
		Collections.reverse(sNetworkingLayers);
		layerPanel.setLayout(new BoxLayout(layerPanel, BoxLayout.PAGE_AXIS));
		for (Layer layer : sNetworkingLayers) {
			Label label = new Label(layer.label);
			label.setBackground(backgroundColor);
			label.setFont(font);
			label.addMouseListener(new LayerHover(layer));
			layerPanel.add(label);
		}

		dataView.setBackground(backgroundColor);
		dataPanel.add(dataView);

		add(layerPanel);
		add(dataPanel);
	}

	@Override
	public void start() {
		super.start();
		mAnimationThread.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public final class AnimationRunnable
			implements Runnable {
		@Override
		public void run() {
			repaint(100);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public final class LayerHover
			extends MouseAdapter {
		private final Layer layer;

		public LayerHover(Layer layer) {
			this.layer = layer;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			dataView.setText(layer.data);
			invalidate();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			dataView.setText("");
			invalidate();
		}
	}

	public static class Layer {
		String label;
		String data;

		public Layer(String label, String data) {
			this.label = label;
			this.data = data;
		}
	}
}
