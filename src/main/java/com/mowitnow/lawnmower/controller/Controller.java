package com.mowitnow.lawnmower.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mowitnow.lawnmower.jaxb.Configuration;
import com.mowitnow.lawnmower.jaxb.MowerCommand;
import com.mowitnow.lawnmower.jaxb.MowerDeclaration;
import com.mowitnow.lawnmower.lawn.Lawn;
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

	public static void run(Configuration configuration) {

		Map<String, Mower> mowersMap = new HashMap<String, Mower>();

		// Création de la pelouse
		Lawn lawn = new Lawn(configuration.getLawnConfiguration()
				.getExtremeAbscissa(), configuration.getLawnConfiguration()
				.getExtremeOrdinate());

		// Mise en position des tondeuses
		for (MowerDeclaration mowerDeclaration : configuration
				.getMowersConfiguration().getMowersDeclaration()
				.getMowerDeclaration()) {
			mowersMap.put(
					mowerDeclaration.getId(),
					new MowerImpl(lawn.getSquare(
							mowerDeclaration.getAbscissaDeparture(),
							mowerDeclaration.getOrdinateDeparture()),
							Orientation.getOrientation(mowerDeclaration
									.getOrientationDeparture())));
		}

		Collections.sort(configuration.getMowersConfiguration()
				.getMowersCommand().getMowerCommand(),
				new MowerCommandComparator());

		// Lancement des tondeuses
		for (MowerCommand mowerCommand : configuration.getMowersConfiguration()
				.getMowersCommand().getMowerCommand()) {
			mowersMap.get(mowerCommand.getId()).executeCommands(
					mowerCommand.getCommands());
		}
	}

}
