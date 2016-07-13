package com.rounceville.GameOfLife;

import java.util.BitSet;

public class TwoDimensionalBitArray {
	protected BitSet[] bsRowArray;

	private int iColumnSize;
	
	public TwoDimensionalBitArray (int iRowSize, int iColumnSize) {
		bsRowArray = new BitSet[iRowSize];
		for (int i = 0; i < iRowSize; i++)
			bsRowArray[i] = new BitSet(iColumnSize);
		
		this.iColumnSize = iColumnSize;
	}

	public int getRowSize() {
		return bsRowArray.length;
	}
	
	public int getColumnSize() {
		return iColumnSize;
	}
	
	public boolean getAt (int iRow, int iColumn) { return bsRowArray[iRow].get(iColumn); }
	public void setOn (int iRow, int iColumn) { bsRowArray[iRow].set(iColumn); }
	public void setOff (int iRow, int iColumn) { bsRowArray[iRow].clear(iColumn); }
}