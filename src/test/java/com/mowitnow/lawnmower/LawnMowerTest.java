package com.mowitnow.lawnmower;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.junit.Test;

import com.mowitnow.lawnmower.controller.Controller;
import com.mowitnow.lawnmower.jaxb.Configuration;
import com.mowitnow.lawnmower.mower.Mower;
import com.mowitnow.lawnmower.parser.ConfigurationLoader;

public class LawnMowerTest {

	@Test
	public void launchTest1() throws JAXBException {
		ConfigurationLoader configurationLoader = new ConfigurationLoader(
				"com/mowitnow/lawnmower/xml/");

		Configuration configuration = configurationLoader
				.loadConfiguration("test1.xml");

		Controller controller = new Controller(configuration, false);
		controller.run();

		Mower mower1 = controller.getMowersMap().get("mower1");
		Mower mower2 = controller.getMowersMap().get("mower2");

		// Vérification position tondeuse 1
		Assert.assertEquals(1, mower1.getSquare().getX());
		Assert.assertEquals(3, mower1.getSquare().getY());
		Assert.assertEquals("N", mower1.getOrientation().getStringCode());

		// vérification position tondeuse 2
		Assert.assertEquals(5, mower2.getSquare().getX());
		Assert.assertEquals(1, mower2.getSquare().getY());
		Assert.assertEquals("E", mower2.getOrientation().getStringCode());
	}
}
