package com.rounceville.GameOfLife;

public class GameOfLife {

	int iMyRowSize;
	int iMyColumnSize;
	
	public GameOfLife(int iRowSize, int iColumnSize) {
		if(iRowSize < 1)
			throw new IllegalArgumentException("Invalid row size");
		if(iColumnSize < 1)
			throw new IllegalArgumentException("Invalid column size");
		iMyColumnSize = iColumnSize;
		iMyRowSize = iRowSize;
	}

	public int getRowSize() {
		return iMyRowSize;
	}
	
	public int getColumnSize() {
		return iMyColumnSize;
	}

}
