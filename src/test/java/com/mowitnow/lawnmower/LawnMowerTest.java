package com.mowitnow.lawnmower;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.mowitnow.lawnmower.controller.Controller;
import com.mowitnow.lawnmower.jaxb.Configuration;
import com.mowitnow.lawnmower.parser.ConfigurationLoader;

public class LawnMowerTest {

	@Test
	public void launchMowers() throws JAXBException {
		ConfigurationLoader configurationLoader = new ConfigurationLoader(
				"com/mowitnow/lawnmower/xml/");

		Configuration configuration = configurationLoader
				.loadConfiguration("conf.xml");
		Controller.run(configuration);
	}

}
