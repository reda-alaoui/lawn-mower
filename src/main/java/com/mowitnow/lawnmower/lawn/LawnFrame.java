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

/**
 * Lawn graphical representation.
 * 
 * @author Reda
 * 
 */
public class LawnFrame extends JFrame {

	private static final long serialVersionUID = 7830602841341724859L;
	private Controller controller;
	private JPanel gridPanel;

	private final ImageIcon mowerNorthImage = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/mower_north.jpg"));
	private final ImageIcon mowerWestImage = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/mower_west.jpg"));
	private final ImageIcon mowerSouthImage = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/mower_south.jpg"));
	private final ImageIcon mowerEastImage = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/mower_east.jpg"));

	private final ImageIcon grassHighImage = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/grass_high.jpg"));

	private final ImageIcon grassLowImage = new ImageIcon(getClass()
			.getClassLoader().getResource(
					"com/mowitnow/lawnmower/image/grass_low.jpg"));

	/**
	 * 
	 * @param controller
	 */
	public LawnFrame(Controller controller) {
		super();
		this.controller = controller;
		build();
	}

	/**
	 * 
	 */
	private void build() {
		setTitle("My Beautiful Lawn");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildMainPanel());
	}

	/**
	 * 
	 * @return
	 */
	private JPanel buildMainPanel() {
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.white);

		mainPanel.add(buildGridPanel(), BorderLayout.CENTER);
		mainPanel.add(buildActionPanel(), BorderLayout.SOUTH);

		return mainPanel;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel buildGridPanel() {
		gridPanel = new JPanel();

		int extremeAbscissa = controller.getLawn().getExtremeAbscissa();
		int extremeOrdinate = controller.getLawn().getExtremeOrdinate();

		gridPanel.setLayout(new GridLayout(extremeAbscissa + 1,
				extremeOrdinate + 1));
		gridPanel.setBackground(Color.white);
		return gridPanel;
	}

	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @param square
	 */
	public void renderRotation(Square square) {
		renderAll();
	}

	/**
	 * 
	 * @param oldSquare
	 * @param newSquare
	 */
	public void renderMove(Square oldSquare, Square newSquare) {
		renderAll();
	}

	/**
	 * 
	 */
	public void renderAll() {
		int n = 0;
		gridPanel.removeAll();
		for (int j = ((GridLayout) gridPanel.getLayout()).getColumns() - 1; j >= 0; j--) {
			for (int i = 0; i < ((GridLayout) gridPanel.getLayout()).getRows(); i++) {
				Square square = controller.getLawn().getSquare(i, j);
				if (!square.isFree()) {
					switch (square.getMower().getOrientation()) {
					case NORTH:
						gridPanel.add(new JLabel(mowerNorthImage));
						break;
					case SOUTH:
						gridPanel.add(new JLabel(mowerSouthImage));
						break;
					case WEST:
						gridPanel.add(new JLabel(mowerWestImage));
						break;
					case EAST:
						gridPanel.add(new JLabel(mowerEastImage));
						break;
					default:
						break;
					}

				} else {
					if (square.wasMown()) {
						gridPanel.add(new JLabel(grassLowImage));
					} else {
						gridPanel.add(new JLabel(grassHighImage));
					}
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
