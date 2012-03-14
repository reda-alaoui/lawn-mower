package com.mowitnow.lawnmower.controller;

import java.util.Comparator;

import com.mowitnow.lawnmower.jaxb.MowerCommand;

/**
 * Sort mowers commands following their precedences.
 * 
 * @author Reda
 * 
 */
public class MowerCommandSorter implements Comparator<MowerCommand> {

	public int compare(MowerCommand o1, MowerCommand o2) {
		if (o1 == null || o2 == null) {
			return 0;
		}
		if (o1.getPrecedency() < o2.getPrecedency()) {
			return -1;
		} else if (o1.getPrecedency() > o2.getPrecedency()) {
			return 1;
		}
		return 0;
	}

}
