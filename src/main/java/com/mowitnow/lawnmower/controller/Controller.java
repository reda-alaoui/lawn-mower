package com.mowitnow.lawnmower.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mowitnow.lawnmower.jaxb.Configuration;
import com.mowitnow.lawnmower.jaxb.MowerCommand;
import com.mowitnow.lawnmower.jaxb.MowerDeclaration;
import com.mowitnow.lawnmower.lawn.Lawn;
import com.mowitnow.lawnmower.lawn.LawnFrame;
import com.mowitnow.lawnmower.lawn.Square;
import com.mowitnow.lawnmower.mower.Mower;
import com.mowitnow.lawnmower.mower.MowerImpl;
import com.mowitnow.lawnmower.mower.Orientation;

/**
 * Entry point.
 * 
 * @author Reda
 * 
 */
public class Controller {

	private Map<String, Mower> mowersMap;
	private Configuration configuration;
	private Lawn lawn;
	private LawnFrame lawnFrame;
	private boolean guiActivated;

	public Controller(Configuration configuration, boolean activateGui) {
		this.mowersMap = new HashMap<String, Mower>();
		this.configuration = configuration;

		// Création de la pelouse
		this.lawn = new Lawn(this, configuration.getLawnConfiguration()
				.getExtremeAbscissa(), configuration.getLawnConfiguration()
				.getExtremeOrdinate());
		// Mise en position des tondeuses
		for (MowerDeclaration mowerDeclaration : configuration
				.getMowersConfiguration().getMowersDeclaration()
				.getMowerDeclaration()) {
			mowersMap.put(
					mowerDeclaration.getId(),
					new MowerImpl(mowerDeclaration.getId(), lawn.getSquare(
							mowerDeclaration.getAbscissaDeparture(),
							mowerDeclaration.getOrdinateDeparture()),
							Orientation.getOrientation(mowerDeclaration
									.getOrientationDeparture())));
		}

		if (activateGui) {
			this.lawnFrame = new LawnFrame(this);
			lawnFrame.setVisible(true);
			guiActivated = true;
		}
		lawn.renderAll();
	}

	public void run() {

		Collections
				.sort(configuration.getMowersConfiguration().getMowersCommand()
						.getMowerCommand(), new MowerCommandSorter());

		// Lancement des tondeuses
		for (MowerCommand mowerCommand : configuration.getMowersConfiguration()
				.getMowersCommand().getMowerCommand()) {
			mowersMap.get(mowerCommand.getId()).executeCommands(
					mowerCommand.getCommands());
		}
	}

	/**
	 * @return the mowersMap
	 */
	public Map<String, Mower> getMowersMap() {
		return mowersMap;
	}

	/**
	 * @param mowersMap
	 *            the mowersMap to set
	 */
	public void setMowersMap(Map<String, Mower> mowersMap) {
		this.mowersMap = mowersMap;
	}

	/**
	 * @return the lawn
	 */
	public Lawn getLawn() {
		return lawn;
	}

	/**
	 * @param lawn
	 *            the lawn to set
	 */
	public void setLawn(Lawn lawn) {
		this.lawn = lawn;
	}

	/**
	 * @return the guiActivated
	 */
	public boolean isGuiActivated() {
		return guiActivated;
	}

	/**
	 * @return the lawnFrame
	 */
	public LawnFrame getLawnFrame() {
		return lawnFrame;
	}

	public void renderRotation(Square square) {
		lawnFrame.renderRotation(square);
	}

	public void renderMove(final Square oldSquare, final Square newSquare) {
		// Runnable code = new Runnable() {
		// public void run() {
		//
		// }
		// };
		//
		// if (SwingUtilities.isEventDispatchThread()) {
		// code.run();
		// } else {
		// SwingUtilities.invokeLater(code);
		// }

		lawnFrame.renderMove(oldSquare, newSquare);

	}

	public void renderAll() {
		// Runnable code = new Runnable() {
		// public void run() {
		//
		// }
		// };
		// if (SwingUtilities.isEventDispatchThread()) {
		// code.run();
		// } else {
		// SwingUtilities.invokeLater(code);
		// }

		lawnFrame.renderAll();

	}

}
