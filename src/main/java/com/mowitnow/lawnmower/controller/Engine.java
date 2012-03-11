package com.mowitnow.lawnmower.controller;

import javax.xml.bind.JAXBException;

import com.mowitnow.lawnmower.parser.ConfigurationLoader;

public class Engine {
	public static void main(String[] args) {
		ConfigurationLoader configurationLoader = new ConfigurationLoader(
				args[0]);
		try {
			new Controller(configurationLoader.loadConfiguration(args[1]),
					Boolean.parseBoolean(args[2]));

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
