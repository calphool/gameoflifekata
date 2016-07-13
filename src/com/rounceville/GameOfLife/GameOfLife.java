package com.rounceville.GameOfLife;

public class GameOfLife {

	public GameOfLife(int iRowSize, int iColumnSize) {
		if(iRowSize < 1)
			throw new IllegalArgumentException("Invalid row size");
		if(iColumnSize < 1)
			throw new IllegalArgumentException("Invalid column size");
	}
}
