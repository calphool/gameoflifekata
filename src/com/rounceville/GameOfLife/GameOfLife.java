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
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int iRow = 0; iRow < getRowSize(); iRow++) {
			for(int iCol = 0; iCol < getColumnSize(); iCol++) {
				if(aliveAt(iRow, iCol))
					sb.append("*");
				else
					sb.append(".");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
