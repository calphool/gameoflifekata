package com.rounceville.GameOfLife;
  
 import static org.junit.Assert.*;
  
 import org.junit.Rule;
 import org.junit.Test;
 import org.junit.rules.ExpectedException;

 public class GameOfLifeTest {
 
	 
	private char cRowDelimChar = '\n';
	private char cAliveChar = '*';
	private char cDeadChar = '.';
	
 	@Rule
 	public final ExpectedException exception = ExpectedException.none();
 		
 	@Test
 	public void testThatInvalidSizedGridsCannotBeCreatedTest1() {
 		exception.expect(IllegalArgumentException.class);
 		new GameOfLife(0, 1);
 	}
 
 	@Test
 	public void testThatInvalidSizedGridsCannotBeCreatedTest2() {
 		exception.expect(IllegalArgumentException.class);
 		new GameOfLife(1, 0);
 	}
 
 	@Test
 	public void testThatInvalidSizedGridsCannotBeCreatedTest3() {
 		exception.expect(IllegalArgumentException.class);
 		new GameOfLife(-1, 0);
  	}
  
 	@Test
 	public void testThatInvalidSizedGridsCannotBeCreatedTest4() {
		exception.expect(IllegalArgumentException.class);
 		new GameOfLife(0, -1);
  	}
 
 	@Test
 	public void testGameOfLifeConstructsAnObject() {
 		GameOfLife gol = new GameOfLife(1, 1);
 		assertNotEquals(gol, null);
  	}

 	@Test
 	public void testGameOfLifeRemembersItsSizes() {
 		GameOfLife gol = new GameOfLife(1, 2);
 		assertEquals(1, gol.getRowSize());
 		assertEquals(2, gol.getColumnSize());
  	}
 	
 	@Test
 	public void testAssertInitializedGOLIsAllDead() {
 		GameOfLife gol = new GameOfLife(3,4);
 		for(int iRows = 0; iRows < 3; iRows++)
 			for(int iCols = 0; iCols < 4; iCols++)
 				assertEquals(false, gol.aliveAt(iRows, iCols));
 	}
 	
 	@Test
 	public void testCanMakeArbitraryCellsAlive() {
 		GameOfLife gol = new GameOfLife(3,3);
 		
 		assertEquals(false, gol.aliveAt(0, 0));
 		gol.setAliveAt(0,0);
 		assertEquals(true, gol.aliveAt(0, 0));

 		assertEquals(false, gol.aliveAt(1, 1));
 		gol.setAliveAt(1,1);
 		assertEquals(true, gol.aliveAt(1, 1));

 		assertEquals(false, gol.aliveAt(2, 2));
 		gol.setAliveAt(2,2);
 		assertEquals(true, gol.aliveAt(2, 2));

 		assertEquals(false, gol.aliveAt(2, 1));
 		gol.setAliveAt(2,1);
 		assertEquals(true, gol.aliveAt(2, 1));
 	}
 	
 	@Test
 	public void testCanMakeArbitraryCellDead() {
 		GameOfLife gol = new GameOfLife(3,3);
 		gol.setAliveAt(0,0);
 		assertEquals(true, gol.aliveAt(0, 0));
 		gol.setDeadAt(0,0);
 		assertEquals(false, gol.aliveAt(0, 0));

 		gol.setAliveAt(1,1);
 		assertEquals(true, gol.aliveAt(1, 1));
 		gol.setDeadAt(1,1);
 		assertEquals(false, gol.aliveAt(1, 1));
 
		gol.setAliveAt(2,2);
 		assertEquals(true, gol.aliveAt(2, 2));
 		gol.setDeadAt(2,2);
 		assertEquals(false, gol.aliveAt(2, 2));

		gol.setAliveAt(2,1);
 		assertEquals(true, gol.aliveAt(2, 1));
 		gol.setDeadAt(2,1);
 		assertEquals(false, gol.aliveAt(2, 1));
 	}
 	
	@Test
 	public void testCantExceedBoundariesWhenSettingAlive() {
		GameOfLife gol = new GameOfLife(3,3);
		gol.setAliveAt(2, 2); // no problem
		exception.expect(IndexOutOfBoundsException.class);
		gol.setAliveAt(3, 3);
	}

	@Test
 	public void testCantExceedBoundariesWhenSettingDead() {
		GameOfLife gol = new GameOfLife(3,3);
		gol.setDeadAt(2, 2); // no problem
		exception.expect(IndexOutOfBoundsException.class);
		gol.setDeadAt(3, 3);
	}

	@Test
 	public void testCantExceedBoundariesLowWhenSettingAlive() {
		GameOfLife gol = new GameOfLife(3,3);
		gol.setAliveAt(0, 0); // no problem
		exception.expect(IndexOutOfBoundsException.class);
		gol.setAliveAt(-1, -1);
	}

	@Test
 	public void testCantExceedBoundariesLowWhenSettingDead() {
		GameOfLife gol = new GameOfLife(3,3);
		gol.setDeadAt(0, 0); // no problem
		exception.expect(IndexOutOfBoundsException.class);
		gol.setDeadAt(-1, -1);
	}
	
	@Test
	public void testEmitCurrentStateToStringAllDead() {
		GameOfLife gol = new GameOfLife(3,3);
		String sGrid = gol.toString();
		assertEquals(11, sGrid.length());
		sGrid = sGrid.replace("\n", "");
		assertEquals(9, sGrid.length());
		assertEquals(".........", sGrid);
	}

	@Test
	public void testEmitCurrentStateToStringArbitraryAliveAndDead() {
		GameOfLife gol = new GameOfLife(3,3);
		gol.setAliveAt(0, 0);
		String sGrid = gol.toString();
		assertEquals(11, sGrid.length());
		assertEquals("*..\n...\n...", sGrid);

		gol.setAliveAt(2, 2);
		sGrid = gol.toString();
		assertEquals("*..\n...\n..*", sGrid);

		gol.setAliveAt(1, 1);
		sGrid = gol.toString();
		assertEquals("*..\n.*.\n..*", sGrid);
		
		assertEquals(true, gol.aliveAt(1, 1));
		gol.setDeadAt(1, 1);
		sGrid = gol.toString();
		assertEquals("*..\n...\n..*", sGrid);
	}
	
	@Test
	public void testEmptyConstructor() {
		GameOfLife gol = new GameOfLife();
		assertNotEquals(null, gol);
		assertEquals(1, gol.getColumnSize());
		assertEquals(1, gol.getRowSize());
	}
	
	@Test
	public void testCannotSetGridContentsWithInvalidString() {
		exception.expect(IllegalArgumentException.class);
		new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "");
	}

	@Test
	public void testCannotSetGridContentsWithInvalidCharsLiveAndDead() {
		exception.expect(IllegalArgumentException.class);
		new GameOfLife().setGridByString('.', '.', 'A', "***...***");
	}

	@Test
	public void testCannotSetGridContentsWithInvalidCharsLiveAndDeadAndDelim() {
		exception.expect(IllegalArgumentException.class);
		new GameOfLife().setGridByString('A', 'B', 'A', "***...***");
	}
	
	
	@Test
	public void testCanSetGridContentsByString() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "***\n...\n***"));
		assertEquals(3, gol.getColumnSize());
		assertEquals(3, gol.getRowSize());
		assertEquals(true, gol.aliveAt(0, 0));
		assertEquals(true, gol.aliveAt(0, 1));
		assertEquals(true, gol.aliveAt(0, 2));
		assertEquals(false, gol.aliveAt(1, 0));
		assertEquals(false, gol.aliveAt(1, 1));
		assertEquals(false, gol.aliveAt(1, 2));
		assertEquals(true, gol.aliveAt(2, 0));
		assertEquals(true, gol.aliveAt(2, 1));
		assertEquals(true, gol.aliveAt(2, 2));
	}
	
	@Test
	public void testCanCountNeighbors() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "...\n.*.\n..."));
		assertEquals(0, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(1, 0);
		assertEquals(1, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(1, 2);
		assertEquals(2, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(0, 1);
		assertEquals(3, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(2, 1);
		assertEquals(4, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(0, 0);
		assertEquals(5, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(0, 2);
		assertEquals(6, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(2, 0);
		assertEquals(7, gol.countNeighborsAt(1, 1));
		gol.setAliveAt(2, 2);
		assertEquals(8, gol.countNeighborsAt(1, 1));
	}

	@Test
	public void testCanCountNeighborsAtBoundaries() {
		GameOfLife gol = new GameOfLife();
		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*..\n...\n...");
		assertEquals(0, gol.countNeighborsAt(0, 0));
		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "...\n...\n..*");
		assertEquals(0, gol.countNeighborsAt(2, 2));
		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "..*\n...\n...");
		assertEquals(0, gol.countNeighborsAt(0, 2));
		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "...\n...\n*..");
		assertEquals(0, gol.countNeighborsAt(2, 0));

		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*..\n.*.\n...");
		assertEquals(1, gol.countNeighborsAt(0, 0));
		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "...\n.*.\n..*");
		assertEquals(1, gol.countNeighborsAt(2, 2));
		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "..*\n.*.\n...");
		assertEquals(1, gol.countNeighborsAt(0, 2));
		gol.setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "...\n.*.\n*..");
		assertEquals(1, gol.countNeighborsAt(2, 0));
	}
	
	
	@Test
	public void testAnyLiveCellWithFewerThanTwoLiveNeighboursDies1() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "...\n.*.\n..."));
		assertEquals(true, gol.aliveAt(1, 1));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(1, 1));
	}

	@Test
	public void testAnyLiveCellWithFewerThanTwoLiveNeighboursDies2() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*..\n.*.\n..."));
		assertEquals(true, gol.aliveAt(1, 1));
		gol.nextGeneration();
		for(int iRow = 0; iRow < 3; iRow++)
			for(int iCol = 0; iCol < 3; iCol++)
				assertEquals(false, gol.aliveAt(iRow, iCol));
		
	}
	
	@Test
	public void testAnyLiveCellWithFewerThanTwoLiveNeighboursDies3() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*..\n.*.\n..*"));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(0, 0));
		assertEquals(false, gol.aliveAt(0, 1));
		assertEquals(false, gol.aliveAt(0, 2));
		assertEquals(false, gol.aliveAt(2, 0));
		assertEquals(false, gol.aliveAt(2, 1));
		assertEquals(false, gol.aliveAt(2, 2));
		assertEquals(false, gol.aliveAt(1, 0));
		assertEquals(false, gol.aliveAt(1, 2));
		assertEquals(true, gol.aliveAt(1, 1));
	}

	@Test
	public void testAnyLiveCellWithFewerThanTwoLiveNeighboursDies4() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*.*\n...\n*.*"));
		gol.nextGeneration();
		for(int iRow = 0; iRow < 3; iRow++)
			for(int iCol = 0; iCol < 3; iCol++)
				assertEquals(false, gol.aliveAt(iRow, iCol));
	}
	
	@Test
	public void testAnyLiveCellWithMoreThanThreeNeighboursDies1() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "***\n.*.\n.*."));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(1, 1));
	}

	@Test
	public void testAnyLiveCellWithMoreThanThreeNeighboursDies2() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*.*\n.*.\n*.*"));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(1, 1));
	}

	@Test
	public void testAnyLiveCellWithMoreThanThreeNeighboursDies3() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "***\n***\n***"));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(1, 1));
		assertEquals(false, gol.aliveAt(1, 0));
		assertEquals(false, gol.aliveAt(1, 2));
		assertEquals(false, gol.aliveAt(0, 1));
		assertEquals(false, gol.aliveAt(2, 1));
	}
	
	@Test
	public void testAnyLiveCellWithTwoOrThreeLiveNeighboursLives() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "***\n...\n..."));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(0, 0));
		assertEquals(true, gol.aliveAt(0, 1));
		assertEquals(false, gol.aliveAt(0, 2));

		gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*.*\n.*.\n*.."));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(0, 0));
		assertEquals(false, gol.aliveAt(0, 2));
		assertEquals(true, gol.aliveAt(1, 1));
		assertEquals(false, gol.aliveAt(0, 2));
	}

	@Test
	public void testAnyDeadCellWithThreeLiveNeighboursReanimates() {
		GameOfLife gol = (new GameOfLife().setGridByString(cDeadChar, cAliveChar, cRowDelimChar, "*.*\n...\n*.."));
		gol.nextGeneration();
		assertEquals(false, gol.aliveAt(0, 0));
		assertEquals(false, gol.aliveAt(0, 2));
		assertEquals(false, gol.aliveAt(2, 0));
		assertEquals(true, gol.aliveAt(1, 1));
	}

  }