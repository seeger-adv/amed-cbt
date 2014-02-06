package de.adv_boeblingen.seegerj.amed.lernsoftware.applets;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SubnetApplet extends Applet {

	private static final Color backgroundColor = new Color(52, 52, 52);
	private static final Font font = new Font("Helvetica", Font.BOLD, 12);

	private final JTextField ipInput = new JTextField();
	private final JTextField nmInput = new JTextField();
	private final JLabel first = new JLabel();
	private final JLabel last = new JLabel();
	private final JLabel sn = new JLabel();
	private final JLabel brd = new JLabel();

	@Override
	public void init() {
		super.init();
		setBackground(backgroundColor);

		Panel inputPanel = new Panel();
		inputPanel.setLayout(new GridLayout(0, 1));
		Panel outputPanel = new Panel();
		outputPanel.setLayout(new GridLayout(0, 2));

		inputPanel.add(createLabel("IP Address"));
		inputPanel.add(this.ipInput);
		inputPanel.add(createLabel("Network Mask"));
		inputPanel.add(this.nmInput);

		outputPanel.add(createLabel("Broadcast"));
		outputPanel.add(this.sn);
		outputPanel.add(createLabel("First Usable"));
		outputPanel.add(this.first);
		outputPanel.add(createLabel("Last Usable "));
		outputPanel.add(this.last);
		outputPanel.add(createLabel("Broadcast"));
		outputPanel.add(this.brd);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, outputPanel);
		splitPane.setBackground(backgroundColor);
		splitPane.setPreferredSize(getSize());
		add(splitPane);
	}

	private static JLabel createLabel(String caption) {
		JLabel label = new JLabel(caption);
		label.setFont(font);
		label.setForeground(Color.white);
		return label;
	}

}
