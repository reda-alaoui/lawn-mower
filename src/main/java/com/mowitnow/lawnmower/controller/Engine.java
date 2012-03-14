package com.mowitnow.lawnmower.controller;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import com.mowitnow.lawnmower.parser.ConfigurationLoader;

/**
 * Entry point.
 * 
 * @author Reda
 * 
 */
public class Engine {
	public static void main(String[] args) {
		try {
			ConfigurationLoader configurationLoader = new ConfigurationLoader(
					args[0]);

			Controller controller = new Controller(
					configurationLoader.loadConfiguration(args[1]),
					Boolean.parseBoolean(args[2]));
			if (!controller.isGuiActivated()) {
				controller.run();
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("You must specify the following parameters:");
			System.out.println("1- configuration path");
			System.out.println("2- configuration filename");
			System.out.println("3- graphic interface activation flag");
		}
	}
}
