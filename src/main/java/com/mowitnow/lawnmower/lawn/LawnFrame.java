package com.mowitnow.lawnmower.lawn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mowitnow.lawnmower.controller.Controller;

public class LawnFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7830602841341724859L;
	private Controller controller;
	private JPanel gridPanel;
	private final ImageIcon mowerIcon = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/mower.jpg"));
	private final ImageIcon grassImage = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/grass_high.jpg"));

	public LawnFrame(Controller controller) {
		super();
		this.controller = controller;
		build();
	}

	private void build() {
		setTitle("My Beautiful Lawn");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildMainPanel());
	}

	private JPanel buildMainPanel() {
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.white);

		mainPanel.add(buildGridPanel(), BorderLayout.CENTER);
		mainPanel.add(buildActionPanel(), BorderLayout.SOUTH);

		return mainPanel;
	}

	private JPanel buildGridPanel() {
		gridPanel = new JPanel();

		int extremeAbscissa = controller.getLawn().getExtremeAbscissa();
		int extremeOrdinate = controller.getLawn().getExtremeOrdinate();

		gridPanel.setLayout(new GridLayout(extremeAbscissa + 1,
				extremeOrdinate + 1));
		gridPanel.setBackground(Color.white);
		return gridPanel;
	}

	private JPanel buildActionPanel() {
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new FlowLayout());
		actionPanel.setBackground(Color.white);

		final JButton button = new JButton("Mow It Now !");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(false);
				new Thread(new Runnable() {
					public void run() {
						controller.run();
					}
				}).start();

			}
		});
		actionPanel.add(button);
		return actionPanel;
	}

	public void renderRotation(Square square) {

	}

	public void renderMove(Square oldSquare, Square newSquare) {
		renderAll();
	}

	public void renderAll() {
		int n = 0;
		gridPanel.removeAll();
		for (int j = ((GridLayout) gridPanel.getLayout()).getColumns() - 1; j >= 0; j--) {
			for (int i = 0; i < ((GridLayout) gridPanel.getLayout()).getRows(); i++) {

				if (!controller.getLawn().getSquare(i, j).isFree()) {
					gridPanel.add(new JLabel(mowerIcon));
				} else {
					gridPanel.add(new JLabel(grassImage));
				}
				gridPanel.getComponent(n).validate();
				gridPanel.getComponent(n).repaint();
				n++;
			}
		}
		gridPanel.validate();
		gridPanel.repaint();
	}
}
