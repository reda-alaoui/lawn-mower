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
 * Control center.
 * 
 * @author Reda
 * 
 */
public class Controller {

	/**
	 * Stores mowers. The map key describes mower's id.
	 */
	private Map<String, Mower> mowersMap;
	/**
	 * Loaded configuration JAXB object.
	 */
	private Configuration configuration;
	private Lawn lawn;

	/**
	 * Lawn graphical representation.
	 */
	private LawnFrame lawnFrame;
	/**
	 * This flag provides the graphical interface activation status.
	 */
	private boolean guiActivated;

	/**
	 * Constructs a lawn, a list mowers then put last ones on the first one.
	 * 
	 * @param configuration
	 * @param activateGui
	 */
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

		// Si interface graphique activée alors on crée notre fenêntre swing.
		if (activateGui) {
			this.lawnFrame = new LawnFrame(this);
			lawnFrame.setVisible(true);
			guiActivated = true;
		}
		lawn.renderAll();
	}

	/**
	 * Orders mowers to follow their commands sequences.
	 */
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
		lawnFrame.renderMove(oldSquare, newSquare);
	}

	public void renderAll() {
		lawnFrame.renderAll();
	}

}
