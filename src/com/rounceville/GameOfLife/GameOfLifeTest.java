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
  
  }