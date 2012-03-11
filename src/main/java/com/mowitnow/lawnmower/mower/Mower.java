package com.mowitnow.lawnmower.mower;

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
}
