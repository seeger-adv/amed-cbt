package de.adv_boeblingen.seegerj.amed.lernsoftware.applets;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.InputVerifier;
import javax.swing.JButton;
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
	private final JTextField nmInput = new JTextField();
	private final JLabel type = new JLabel();
	private final JLabel clazz = new JLabel();
	private final JLabel first = new JLabel();
	private final JLabel last = new JLabel();
	private final JLabel sn = new JLabel();
	private final JLabel brd = new JLabel();
	private final JLabel num = new JLabel();

	@Override
	public void init() {
		super.init();
		Panel panel = new Panel();
		panel.setLayout(new MigLayout());

		this.ipInput.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent arg0) {
				try {
					recalcAddress();
					Border border = new LineBorder(Color.black);
					SubnetApplet.this.ipInput.setBorder(border);
					SubnetApplet.this.nmInput.setBorder(border);
				} catch (IllegalArgumentException e) {
					Border border = new LineBorder(Color.red);
					SubnetApplet.this.ipInput.setBorder(border);
					SubnetApplet.this.nmInput.setBorder(border);
				}
				return true;
			}
		});

		panel.add(createLabel("IP Address"), "span 2, grow");
		panel.add(createLabel("Type"), "wrap");
		panel.add(this.ipInput, "span 2, grow");
		panel.add(this.type, "wrap");
		panel.add(createLabel("Network Mask"), "span 2, grow");
		panel.add(createLabel("Class"), "wrap");
		panel.add(this.nmInput, "span 2, grow");
		panel.add(this.clazz, "wrap");

		panel.add(new JSeparator(SwingConstants.HORIZONTAL), "span 3, grow, wrap");
		panel.add(new JButton(new Action() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				recalcAddress();
			}

			@Override
			public void setEnabled(boolean arg0) {
			}

			@Override
			public void removePropertyChangeListener(PropertyChangeListener arg0) {
			}

			@Override
			public void putValue(String arg0, Object arg1) {
			}

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public Object getValue(String arg0) {
				if ("Name".equals(arg0)) {
					return "recalculate";
				}
				return null;
			}

			@Override
			public void addPropertyChangeListener(PropertyChangeListener arg0) {
			}
		}), "grow, wrap");
		panel.add(new JSeparator(SwingConstants.HORIZONTAL), "span 3, grow, wrap");

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
		String nm = this.nmInput.getText();
		if ("".equals(getMode())) {
			host = host + nm;
		}
		SubnetUtils sn = new SubnetUtils(host);
		SubnetInfo info = sn.getInfo();

		String[] addrs = info.getAllAddresses();
		this.sn.setText(info.getNetworkAddress());
		this.brd.setText(info.getBroadcastAddress());
		this.first.setText(addrs[0]);
		this.last.setText(addrs[addrs.length - 1]);
		this.num.setText(Integer.toString(info.getAddressCount()));
	}
}
