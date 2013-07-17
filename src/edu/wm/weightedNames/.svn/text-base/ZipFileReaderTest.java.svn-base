package edu.wm.weightedNames;

import junit.framework.TestCase;

/** Tests the methods of ZipFileReader that have not been tested yet
 * @author jdlehman
 *
 */
public class ZipFileReaderTest extends TestCase {

	//private variables
	ZipFileReader validZipFileReader;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		validZipFileReader = new ZipFileReader("./res/names.zip");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		validZipFileReader = null;
	}

	//******************************* BEGIN GETENTRYSIZE TEST *************************************
	/** Purpose: get the number of lines in the first file in the zip file
	 * Expected Outcome: returns the correct number of lines in the first file in the zip file
	 */
	public void testGetEntrySize1(){
		long sizeFirstEntry = validZipFileReader.getEntrySize(0);
		
		assertEquals(416518, sizeFirstEntry);
	}
	
	/** Purpose: get the number of lines in the file that is out of the index of the zip file
	 * Expected Outcome: returns -1
	 */
	public void testGetEntrySize2(){
		long sizeFirstEntry = validZipFileReader.getEntrySize(1000);
		
		assertEquals(-1, sizeFirstEntry);
	}
	//******************************* END GETENTRYSIZE TEST *************************************
	
	
	//******************************* BEGIN GETENTRYNAME TEST *************************************
	/** Purpose: get the name of the first file in the zip file
	 * Expected Outcome: returns the correct name of the first file in the zip file
	 */
	public void testGetEntryName1(){
		String name = validZipFileReader.getEntryName(0);
		
		assertEquals("yob2008.txt", name);
	}
	
	/** Purpose: get the name of the file that is out of the index of the zip file
	 * Expected Outcome: returns -1
	 */
	public void testGetEntryName2(){
		String name = validZipFileReader.getEntryName(10000);
		
		assertEquals(null, name);
	}
	//******************************* END GETENTRYNAME TEST *************************************
}
