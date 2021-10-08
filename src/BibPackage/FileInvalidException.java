/*
 * Author: The Cam Phan and Hoang Nhan Duyen Nguyen
 * Student ID: 1832329 and 1911876
 * 2. An exception class FileInvalidException to return error message
 */
package BibPackage;
public class FileInvalidException extends Exception{
	//a. A default error message
	public FileInvalidException() {
		super("Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)");
	}
	//b. The passing of any different error message if desired.
	public FileInvalidException(String msg) {
		super(msg);
	}

}
