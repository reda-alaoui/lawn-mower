package com.mowitnow.lawnmower.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mowitnow.lawnmower.jaxb.Configuration;

/**
 * Used to load the JAXB configuration object.
 * 
 * @author Reda
 * 
 */
public class ConfigurationLoader {

	private String configurationsDirectory;

	/**
	 * 
	 * @param configurationsDirectory
	 */
	public ConfigurationLoader(String configurationsDirectory) {
		this.configurationsDirectory = configurationsDirectory;
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public Configuration loadConfiguration(String fileName)
			throws JAXBException, FileNotFoundException {
		InputStream is = new FileInputStream(configurationsDirectory + fileName);
		return parse(is, Configuration.class);

	}

	/**
	 * 
	 * @param <T>
	 * @param inputStream
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	private <T> T parse(InputStream inputStream, Class<T> clazz)
			throws JAXBException {
		Unmarshaller unmarshaller = JAXBContext.newInstance(clazz)
				.createUnmarshaller();
		return clazz.cast(unmarshaller.unmarshal(inputStream));
	}

	/**
	 * @return the configurationsDirectory
	 */
	public String getConfigurationsDirectory() {
		return configurationsDirectory;
	}

	/**
	 * @param configurationsDirectory
	 *            the configurationsDirectory to set
	 */
	public void setConfigurationsDirectory(String configurationsDirectory) {
		this.configurationsDirectory = configurationsDirectory;
	}

}
