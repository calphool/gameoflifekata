package com.rounceville.GameOfLife;
  
 import static org.junit.Assert.*;
  
 import org.junit.Rule;
 import org.junit.Test;
 import org.junit.rules.ExpectedException;

 public class GameOfLifeTest {
 
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
		assertEquals(12, sGrid.length());
		sGrid = sGrid.replace("\n", "");
		assertEquals(9, sGrid.length());
		assertEquals(".........", sGrid);
	}

	@Test
	public void testEmitCurrentStateToStringArbitraryAliveAndDead() {
		GameOfLife gol = new GameOfLife(3,3);
		gol.setAliveAt(0, 0);
		String sGrid = gol.toString();
		assertEquals(12, sGrid.length());
		assertEquals("*..\n...\n...\n", sGrid);

		gol.setAliveAt(2, 2);
		sGrid = gol.toString();
		assertEquals("*..\n...\n..*\n", sGrid);

		gol.setAliveAt(1, 1);
		sGrid = gol.toString();
		assertEquals("*..\n.*.\n..*\n", sGrid);
		
		assertEquals(true, gol.aliveAt(1, 1));
		gol.setDeadAt(1, 1);
		sGrid = gol.toString();
		assertEquals("*..\n...\n..*\n", sGrid);
	}

  }