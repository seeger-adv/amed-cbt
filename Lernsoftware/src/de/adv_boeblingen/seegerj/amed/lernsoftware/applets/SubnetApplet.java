package de.adv_boeblingen.seegerj.amed.lernsoftware.applets;

import java.applet.Applet;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SubnetApplet extends Applet {
	private final JTextField ipInput = new JTextField();
	private final JTextField nmInput = new JTextField();
	private final JLabel type = new JLabel();
	private final JLabel clazz = new JLabel();
	private final JLabel first = new JLabel();
	private final JLabel last = new JLabel();
	private final JLabel sn = new JLabel();
	private final JLabel brd = new JLabel();

	@Override
	public void init() {
		super.init();
		Panel inputPanel = new Panel();
		inputPanel.setLayout(new MigLayout());
		Panel outputPanel = new Panel();
		outputPanel.setLayout(new MigLayout());

		inputPanel.add(createLabel("IP Address"), "span 2, grow");
		inputPanel.add(createLabel("Type"), "wrap");
		inputPanel.add(this.ipInput, "span 2, grow");
		inputPanel.add(this.type, "wrap");
		inputPanel.add(createLabel("Network Mask"), "span 2, grow");
		inputPanel.add(createLabel("Class"), "wrap");
		inputPanel.add(this.nmInput, "span 2, grow");
		inputPanel.add(this.clazz, "wrap");

		outputPanel.add(createLabel("Network"));
		outputPanel.add(this.sn, "wrap");
		outputPanel.add(createLabel("First Usable"));
		outputPanel.add(this.first, "wrap");
		outputPanel.add(createLabel("Last Usable "));
		outputPanel.add(this.last, "wrap");
		outputPanel.add(createLabel("Broadcast"));
		outputPanel.add(this.brd);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, outputPanel);
		splitPane.setPreferredSize(getSize());
		add(splitPane);
	}

	private static JLabel createLabel(String caption) {
		JLabel label = new JLabel(caption);
		return label;
	}

	private String getMode() {
		return getParameter("mode");
	}

}
