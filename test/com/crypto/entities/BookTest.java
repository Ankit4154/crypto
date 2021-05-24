package com.crypto.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import com.crypto.constants.BookGenre;
import com.crypto.services.BookmarkService;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD) --default
@DisplayName("When running BookTest")
class BookTest {
	
	Book book;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before class intialization");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		book = new Book();
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
	}
	
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up..");
	}

	@Test
	void testIsKidFriendly() {
		// Test 1 - if Self help -- false
		Book book = BookmarkService.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		boolean isKidFriendly = book.isKidFriendly();
		boolean expected = false;
		boolean actual = book.isKidFriendly();
		assertFalse("Self help is not kid friendly book, false", isKidFriendly);
		assertFalse(isKidFriendly,"Self help is not kid friendly book, false");
		assertEquals(expected, actual);
		
		// Test 2 - if Philosophy -- false
		book = BookmarkService.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		isKidFriendly = book.isKidFriendly();
		assertFalse("Philosophy is not kid friendly book, false", isKidFriendly);

		// Test 3 - if Children -- true
		book = BookmarkService.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.CHILDREN, 4.3);
		isKidFriendly = book.isKidFriendly();
		assertTrue("Children is kid friendly book, true", isKidFriendly);
		
	}
	
	@Test
	@DisplayName("Testing circle area") //Adds display name to test runs
	public void testCircleArea() {
		boolean isServerUp = false;
		assumeTrue(isServerUp); // if server is down skip this test
		assertEquals(314.1592653589793,book.circleArea(10),"Should return right area");

	}
	
	@Test
	@EnabledOnOs(OS.WINDOWS) // Only enabled/run on/if WINDOWS OS else skip.
	public void testDivide() {
		assertThrows(ArithmeticException.class, () -> book.divide(1, 0), "Divide by zero");
		assertAll(
				() -> assertEquals(4,book.divide(8, 2), "Divide 8 by 2"),
				() -> assertEquals(2,book.divide(4, 2)),
				() -> assertEquals(-2,book.divide(2, -1)),
				() -> assertEquals(3,book.divide(6, 2))
				);
	}
	
	@Test
	@Disabled
	@DisplayName("TDD method.. Should not run")
	public void testDisable() {
		fail("This test should be disabled");
	}
	
	@Nested
	@Tag("Math")
	@DisplayName("Inside CircleArea Method")
	class TestCircleArea{
		
		@Test
		@DisplayName("When radius passed is positive")
		public void testCircleAreaPositive() {
			System.out.println("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
			assertEquals(314.1592653589793,book.circleArea(10),"Should return positive area");
		}
		
		@Test
		@DisplayName("When radius passed is negative")
		public void testCircleAreaNegative() {
			//assertEquals(314.1592653589793,book.circleArea(-10),"Should return positive area");
			double expected = 314.1592653589793;
			double actual = book.circleArea(-10);//For performance use lambda
			assertEquals(expected,actual,()->"Should return "+expected+ " but returned "+actual);
		}
	}
	
	@RepeatedTest(3)
	@Tag("Math")
	@DisplayName("Math Divide repeated")
	public void testDivideRepeated(RepetitionInfo repetitionInfo) {
		if(repetitionInfo.getCurrentRepetition() == 1)
			assertThrows(ArithmeticException.class, () -> book.divide(1, 0), "Divide by zero");
		else
			assertEquals(1,book.divide(1, 1), "Divide by 1");
	}
	
	
	@Test
	@DisplayName("Simple Test for Reporter and Info")
	@Tag("TestInfo")
	public void InfoTest() {
		System.out.println("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
		assertEquals(true, true, "This is always true");
	}

}
