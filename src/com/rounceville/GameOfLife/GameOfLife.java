package com.rounceville.GameOfLife;

public class GameOfLife {

	TwoDimensionalBitArray baMatrix = null;
	char cMyDeadChar = '.';
	char cMyLiveChar = '*';
	char cMyRowDelim = '\n';

	public GameOfLife(int iRowSize, int iColumnSize) {
		if(iRowSize < 1)
			throw new IllegalArgumentException("Invalid row size");
		if(iColumnSize < 1)
			throw new IllegalArgumentException("Invalid column size");
		
		baMatrix = new TwoDimensionalBitArray(iRowSize, iColumnSize);
	}

	
	public GameOfLife() {
		this(1,1); 
	}


	public GameOfLife setGridByString(char cDeadChar, char cLiveChar, char cRowDelim, String sBuffer) {
		if(cDeadChar == cLiveChar)
			throw new IllegalArgumentException("Can't have live and dead chars the same");
		if(cDeadChar == cRowDelim || cLiveChar == cRowDelim)
			throw new IllegalArgumentException("Can't have live and dead chars the same as row delimiter");
		
		String[] sRows = sBuffer.split(String.valueOf(cRowDelim));
		int iNumRows = sRows.length;
		
		int iMaxColSize = -1;
		for(int iCtr = 0; iCtr < iNumRows; iCtr++) {
			if(sRows[iCtr].length() > iMaxColSize)
				iMaxColSize = sRows[iCtr].length();
		}
		if(iMaxColSize < 1)
			throw new IllegalArgumentException("String doesn't have any columns");
		
		baMatrix = new TwoDimensionalBitArray(iNumRows, iMaxColSize);
		for(int iCtr = 0; iCtr < iNumRows; iCtr++) {
			for(int iCharCtr = 0; iCharCtr < sRows[iCtr].length(); iCharCtr++) {
				if(sRows[iCtr].charAt(iCharCtr) == cLiveChar)
					setAliveAt(iCtr, iCharCtr);
			}
		}		
		
		cMyRowDelim = cRowDelim;
		cMyDeadChar = cDeadChar;
		cMyLiveChar = cLiveChar;
		return this;
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
					sb.append(cMyLiveChar);
				else
					sb.append(cMyDeadChar);
			}
			if(iRow < getRowSize()-1)
				sb.append(cMyRowDelim);
		}
		return sb.toString();
	}

	public int countNeighborsAt(int iRow, int iCol) {
		int iNeighbors = 0;
		
		if(iRow-1 >= 0)
			if(aliveAt(iRow-1, iCol))
				iNeighbors++;
	
		if(iRow+1 < getRowSize())
			if(aliveAt(iRow+1, iCol))
				iNeighbors++;
		
		if(iCol-1 >= 0)
			if(aliveAt(iRow, iCol-1))
				iNeighbors++;
		
		if(iCol+1 < getColumnSize())
			if(aliveAt(iRow, iCol+1))
				iNeighbors++;
		
		if(iRow+1 < getRowSize() && iCol+1 < getColumnSize())
			if(aliveAt(iRow+1, iCol+1))
				iNeighbors++;
		
		if(iRow-1 >= 0 && iCol-1 >= 0)
			if(aliveAt(iRow-1, iCol-1))
				iNeighbors++;
		
		if(iRow+1 < getRowSize() && iCol-1 >= 0)
			if(aliveAt(iRow+1, iCol-1))
				iNeighbors++;
		
		if(iRow-1 >= 0 && iCol+1 < getColumnSize())
			if(aliveAt(iRow-1, iCol+1))
				iNeighbors++;
		
		return iNeighbors;
	}


	public String nextGeneration() {
		GameOfLife outputGOL = new GameOfLife().setGridByString(cMyDeadChar, cMyLiveChar, cMyRowDelim, this.toString());
				
		for(int iRow = 0; iRow < getRowSize(); iRow++) {
			for(int iCol = 0; iCol < getColumnSize(); iCol++) {
				if(countNeighborsAt(iRow, iCol) < 2)
					outputGOL.setDeadAt(iRow, iCol);
			}
 		}
		
		this.baMatrix = outputGOL.baMatrix;
		return outputGOL.toString();
	}

}
