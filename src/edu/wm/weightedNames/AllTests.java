package edu.wm.weightedNames;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for edu.wm.weightedNames");
		//$JUnit-BEGIN$
		suite.addTestSuite(WeightedNameTest.class);
		suite.addTestSuite(ZipFileReaderTest.class);
		suite.addTestSuite(NamesTest.class);
		suite.addTestSuite(NamesTest2.class);
		suite.addTestSuite(ExtendedNamesTest.class);
		//$JUnit-END$
		return suite;
	}

}
