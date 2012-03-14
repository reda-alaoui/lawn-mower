package com.mowitnow.lawnmower.mower;

/**
 * Mower's cardinal orientation.
 * 
 * @author Reda
 * 
 */
public enum Orientation {
	NORTH("N"), EAST("E"), WEST("W"), SOUTH("S");

	private String stringCode;

	private Orientation(String stringCode) {
		this.stringCode = stringCode;
	}

	/**
	 * @return the stringCode
	 */
	public String getStringCode() {
		return stringCode;
	}

	/**
	 * Translates a string code acronym into an {@link Orientation} value.
	 * 
	 * @param stringCode
	 * @return
	 */
	public static Orientation getOrientation(String stringCode) {
		for (Orientation orientation : values()) {
			if (orientation.getStringCode().equals(stringCode)) {
				return orientation;
			}
		}
		return null;
	}
}
