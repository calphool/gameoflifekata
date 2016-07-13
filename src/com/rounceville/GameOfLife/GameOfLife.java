package com.rounceville.GameOfLife;

public class GameOfLife {

	TwoDimensionalBitArray baMatrix = null;
	
	public GameOfLife(int iRowSize, int iColumnSize) {
		if(iRowSize < 1)
			throw new IllegalArgumentException("Invalid row size");
		if(iColumnSize < 1)
			throw new IllegalArgumentException("Invalid column size");
		
		baMatrix = new TwoDimensionalBitArray(iRowSize, iColumnSize);
	}

	public int getRowSize() {
		return baMatrix.getRowSize();
	}
	
	public int getColumnSize() {
		return baMatrix.getColumnSize();
	}
	
	public boolean aliveAt(int iRow, int iCol) {
		return baMatrix.getAt(iRow, iCol);
	}

	public void setAliveAt(int iRow, int iColumn) {
		baMatrix.setOn(iRow, iColumn);
	}

	public void setDeadAt(int iRow, int iColumn) {
		baMatrix.setOff(iRow, iColumn);
	}

}
