package de.adv_boeblingen.seegerj.amed.lernsoftware.applets;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;

@SuppressWarnings("serial")
public class SubnetApplet extends Applet {

	private final JTextField ipInput = new JTextField();
	private final JComboBox<String> nmInput = new JComboBox<String>();
	private final JLabel type = new JLabel();
	private final JLabel first = new JLabel();
	private final JLabel last = new JLabel();
	private final JLabel sn = new JLabel();
	private final JLabel brd = new JLabel();
	private final JLabel num = new JLabel();

	@Override
	public void init() {
		super.init();
		setBackground(new Color(0xee, 0xee, 0xee));
		Panel panel = new Panel();
		panel.setBackground(new Color(0xee, 0xee, 0xee));
		panel.setLayout(new MigLayout());

		this.ipInput.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent arg0) {
				Border border = null;
				try {
					recalcAddress();
					border = new LineBorder(Color.black);
				} catch (IllegalArgumentException e) {
					border = new LineBorder(Color.red);
				}
				SubnetApplet.this.ipInput.setBorder(border);
				return true;
			}
		});

		panel.add(createLabel("IP Address"), "span 2, grow");
		panel.add(createLabel("Type"), "wrap");
		panel.add(this.ipInput, "span 2, grow");
		panel.add(this.type, "wrap");
		panel.add(createLabel("Network Mask"), "span 3, grow, wrap");
		panel.add(this.nmInput, "span 3, grow, wrap");

		boolean isCidr = "cidr".equals(getMode());
		if (isCidr) {
			this.type.setText("CIDR");
			for (int i = 1; i <= 32; i++) {
				this.nmInput.addItem("/" + i);
			}
		} else {
			this.type.setText("Classful");
			this.nmInput.addItem("255.0.0.0");
			this.nmInput.addItem("255.255.0.0");
			this.nmInput.addItem("255.255.255.0");
		}

		panel.add(new JSeparator(SwingConstants.HORIZONTAL), "span 3, grow, wrap");

		JButton button = new JButton("recalculate");
		panel.add(button, "grow, wrap");
		panel.add(new JSeparator(SwingConstants.HORIZONTAL), "span 3, grow, wrap");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recalcAddress();
			}
		});

		panel.add(createLabel("Network"));
		panel.add(this.sn, "wrap");
		panel.add(createLabel("First Usable"));
		panel.add(this.first, "wrap");
		panel.add(createLabel("Last Usable "));
		panel.add(this.last, "wrap");
		panel.add(createLabel("Broadcast"));
		panel.add(this.brd, "wrap");
		panel.add(createLabel("# of Hosts"));
		panel.add(this.num, "wrap");

		panel.setPreferredSize(getSize());
		add(panel);
	}

	private static JLabel createLabel(String caption) {
		JLabel label = new JLabel(caption);
		return label;
	}

	private String getMode() {
		return getParameter("mode");
	}

	private void recalcAddress() {
		String host = this.ipInput.getText();
		String nm = (String) this.nmInput.getSelectedItem();

		SubnetUtils sn = null;
		boolean isCidr = "cidr".equals(getMode());
		if (isCidr) {
			host = host + nm;
			sn = new SubnetUtils(host);
		} else {
			sn = new SubnetUtils(host, nm);
		}
		SubnetInfo info = sn.getInfo();

		this.sn.setText(info.getNetworkAddress());
		this.brd.setText(info.getBroadcastAddress());
		this.first.setText(info.getLowAddress());
		this.last.setText(info.getHighAddress());
		this.num.setText(Integer.toString(info.getAddressCount()));
	}
}
