package edu.wm.weightedNames;

import java.io.*;
import java.util.zip.*;

/**
 * Class Name: ZipCompressor
 * Responsibilities: compress files into zip files
 * Collaborators: Names
 * 
 * @author jonathanlehman
 *
 */

public class ZipCompressor {

	/** Compress file to zip file
	 * 
	 * @param zipFileName name of ZipFile for file to be compressed to
	 * @param textFileName name of file to be compressed
	 * @throws IOException 
	 */
	public static void compressToZipFile(String zipFileName, String fileName) throws IOException{
		//set output stream, path to place zip file
	    FileOutputStream fos = new FileOutputStream(zipFileName);
	    ZipOutputStream zos = new ZipOutputStream(fos);
	    
	    //set input stream, path to get file from
	    FileInputStream fis = new FileInputStream(fileName);
	      
	    //set zip entry name for file to be stored in zip file
	    ZipEntry ze= new ZipEntry(fileName);
	    zos.putNextEntry(ze);
	    
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	    //read bytes in file and transfer from fileinputstream to zipoutputstream
	    while ((bytesRead = fis.read(buffer)) != -1){
	      zos.write(buffer, 0, bytesRead);
	    }
	    
	    //close file input stream
	    fis.close();
	    //close zip output stream
	    zos.closeEntry();
	    zos.close();
	    
	    
		      
	}
	
}
