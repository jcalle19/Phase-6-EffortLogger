package votingProcedure;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.Before;


import org.junit.jupiter.api.Test;

public class VotingTest {

		InputStream systemIn = System.in;
		PrintStream systemOut = System.out;
		
		
		//ByteArrayInputStream testIn = new ByteArrayInputStream(tstString.getBytes()); 
		ByteArrayOutputStream testOut = new ByteArrayOutputStream();
		
		@Before
		String setup(String testString) {
			System.setIn(new ByteArrayInputStream(testString.getBytes()));
			System.setOut(new PrintStream(testOut));
			return testOut.toString();
		}
		
		
		@Test
		void testCase1() { //test the most common use case and see if the correct out-lier values are found
			setup("456789");
			VotingProcedure.main(null);
			String testOutput = setup("46789");
			System.setOut(systemOut);
			System.setIn(systemIn);
			assertEquals(testOutput, "Enter the votes consecutively no spaces: Must identify outliers\r\n"
					+ "4\r\n"
					+ "9\r\n");
			System.out.println(testOutput);
		}
		
		@Test
		void testCase2() { //another test case to see if the correct out-lier values are found. The ordering is different
			setup("398724");
			VotingProcedure.main(null);
			String testOutput = setup("398724");
			System.setOut(systemOut);
			System.setIn(systemIn);
			assertEquals(testOutput, "Enter the votes consecutively no spaces: Must identify outliers\r\n"
					+ "2\r\n"
					+ "9\r\n");
			System.out.println(testOutput);
		}
		
		@Test
		void testCase3() { //test case for when all entered votes are the same
			setup("333");
			VotingProcedure.main(null);
			String testOutput = setup("333");
			System.setOut(systemOut);
			System.setIn(systemIn);
			assertEquals(testOutput, "Enter the votes consecutively no spaces: All votes the same, end session\r\n");
			System.out.println(testOutput);
		}
		
		@Test
		void testCase4() { //test case to see if the function handles variance <=1 cases correctly
			setup("2234");
			VotingProcedure.main(null);
			String testOutput = setup("2234");
			System.setOut(systemOut);
			System.setIn(systemIn);
			assertEquals(testOutput, "Enter the votes consecutively no spaces: Commence Discussion, no notable outliers\r\n");
			System.out.println(testOutput);
		}
		
		@Test
		void testCase5() { //test case to see if the function catches the exception correctly
			setup("22a34");
			VotingProcedure.main(null);
			String testOutput = setup("2a34");
			System.setOut(systemOut);
			System.setIn(systemIn);
			assertEquals(testOutput, "Enter the votes consecutively no spaces: Error: Please only enter integers\r\n");
			System.out.println(testOutput);
		}
}
