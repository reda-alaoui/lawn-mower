package com.mowitnow.lawnmower.parser;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mowitnow.lawnmower.jaxb.Configuration;

/**
 * Used to load configuration JAXB object.
 * 
 * @author Reda
 * 
 */
public class ConfigurationLoader {

	private String configurationsDirectory;

	public ConfigurationLoader(String configurationsDirectory) {
		this.configurationsDirectory = configurationsDirectory;
	}

	public Configuration loadConfiguration(String fileName)
			throws JAXBException {
		URL xmlURL = getClass().getClassLoader().getResource(
				configurationsDirectory + fileName);
		return parse(xmlURL, Configuration.class);

	}

	private <T> T parse(URL url, Class<T> clazz) throws JAXBException {
		Unmarshaller unmarshaller = JAXBContext.newInstance(clazz)
				.createUnmarshaller();
		return clazz.cast(unmarshaller.unmarshal(url));
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
