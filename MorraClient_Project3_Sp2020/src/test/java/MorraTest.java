import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MorraTest {

	@Test
	void initMorraInfo() {
		MorraInfo morraInfo = new MorraInfo(0, -1, -1, -1, -1, -1, -1, false, false); 
	}

	@Test
	void testPlayer1() {
		MorraInfo morraInfo = new MorraInfo(0, -1, -1, -1, -1, -1, -1, true, false); 
		assertEquals(true, morraInfo.p1, "not true p1"); 
		assertNotEquals(true, morraInfo.p2, "not false p2");
	}
	
	@Test
	void testPlayer2() {
		MorraInfo morraInfo = new MorraInfo(0, -1, -1, -1, -1, -1, -1, false, true); 
		assertEquals(true, morraInfo.p2, "not true p2"); 
		assertNotEquals(true, morraInfo.p1, "not false p1");
	}
	
	@Test
	void testGameMode() {
		MorraInfo morraInfo = new MorraInfo(3, -1, -1, -1, -1, -1, -1, false, true); 
		assertEquals(3, morraInfo.gameMode, "not correct gameMode"); 
		assertNotEquals(2, morraInfo.gameMode, "not correct gameMode"); 
	}
	
	@Test
	void testP1Points() {
		MorraInfo morraInfo = new MorraInfo(3, 0, -1, -1, -1, -1, -1, false, true); 
		assertEquals(0, morraInfo.p1Points, "not correct p1Points"); 
		assertNotEquals(2, morraInfo.p1Points, "not correct p1Points"); 
	}
	
	@Test
	void testP2Points() {
		MorraInfo morraInfo = new MorraInfo(3, 0, 0, -1, -1, -1, -1, false, true); 
		assertEquals(0, morraInfo.p2Points, "not correct p2Points"); 
		assertNotEquals(2, morraInfo.p2Points, "not correct p2Points"); 
	}
	
	@Test
	void testP1Plays() {
		MorraInfo morraInfo = new MorraInfo(3, 0, -1, 5, -1, -1, -1, false, true); 
		assertEquals(5, morraInfo.p1Plays, "not correct p1Plays"); 
		assertNotEquals(2, morraInfo.p1Plays, "not correct p1Plays"); 
	}
	
	@Test
	void testP2Plays() {
		MorraInfo morraInfo = new MorraInfo(3, 0, -1, -1, 2, -1, -1, false, true); 
		assertEquals(2, morraInfo.p2Plays, "not correct p2Plays"); 
		assertNotEquals(3, morraInfo.p1Plays, "not correct p2Plays"); 
	}
	
	@Test
	void testP1Guess() {
		MorraInfo morraInfo = new MorraInfo(3, 0, -1, -1, 5, 0, -1, false, true); 
		assertEquals(0, morraInfo.p1Guess, "not correct p1Guess"); 
		assertNotEquals(2, morraInfo.p1Guess, "not correct p1Guess"); 
	}
	
	@Test
	void testP2Guess() {
		MorraInfo morraInfo = new MorraInfo(3, 0, -1, -1, 2, -1, 8, false, true); 
		assertEquals(8, morraInfo.p2Guess, "not correct p2Guess"); 
		assertNotEquals(3, morraInfo.p1Guess, "not correct p2Guess"); 
	}
}