package com.mowitnow.lawnmower.mower;

import com.mowitnow.lawnmower.lawn.Square;

/**
 * Describes commands that a mower can execute.
 * 
 * @author Reda
 * 
 */
public interface Mower {
	void executeCommands(String commands);

	void turnRight();

	void turnLeft();

	void moveForward();

	Orientation getOrientation();

	Square getSquare();
}
