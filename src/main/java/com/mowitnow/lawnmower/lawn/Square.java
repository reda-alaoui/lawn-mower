package com.mowitnow.lawnmower.lawn;

import com.mowitnow.lawnmower.mower.Mower;

/**
 * A lawn parcel.
 * 
 * @author Reda
 * 
 */
public class Square {
	private Lawn lawn;
	private int x;
	private int y;
	private Mower mower;
	private boolean wasMown;

	/**
	 * 
	 * @param lawn
	 * @param x
	 * @param y
	 */
	public Square(Lawn lawn, int x, int y) {
		this.lawn = lawn;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return true if this square is mower free.
	 */
	public boolean isFree() {
		return mower == null;
	}

	/**
	 * 
	 * @return true if this lawn square was mown.
	 */
	public boolean wasMown() {
		return wasMown;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the lawn
	 */
	public Lawn getLawn() {
		return lawn;
	}

	/**
	 * @param mower
	 *            the mower to set
	 */
	public void setMower(Mower mower) {
		this.mower = mower;
		if (!wasMown) {
			wasMown = true;
		}
	}

	/**
	 * @return the mower
	 */
	public Mower getMower() {
		return mower;
	}
}
