/*
 * Author: The Cam Phan and Hoang Nhan Duyen Nguyen
 * Student ID: 1832329 and 1911876
 * Class BibCreator which contains the main() method
 */
package BibPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BibCreator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int NUMBER_OF_FILE = 10;
		System.out.println("Welcome to BibCreator!");
		// 3.Attempt to open all 10 input files (Latex1.bib to Latex10.bib) for reading.
		Scanner fileReader = null;
		for(int j = 1; j <= NUMBER_OF_FILE; j++) 
		{
			//If any of the files does not exist, the program displays an error message and terminates after closing any opened files
			try {
				fileReader = new Scanner(new FileInputStream("Latex"+j+".bib"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("\nCould not open input file Latex"+ j +".bib for reading.\n\nPlease check if file exists! Program will terminate after closing any opened files." );
				System.exit(0);
			}finally {
				// if there is any file opened, close the file
				fileReader.close();
			}
		}

		//4. If all 10 input files can successfully be opened
		//Attempt to open/create all 30 output files
		PrintWriter pw = null;
		File deleteFile = null;
		for (int i = 1; i <= NUMBER_OF_FILE; i++) {
			try {
				pw = new PrintWriter(new FileOutputStream("IEEE"+i+".json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//a. Display a message to the user indicating which file could not be opened/created;
				System.out.println("\nCould not open/create the file " + "IEEE"+i+".json");
				//b. Delete all other created output files (if any)
				for (int k = 1; k <= i; k++) {
					deleteFile = new File("IEEE"+k+".json");
					deleteFile.delete();
				}
				System.exit(0);
			}finally {
				pw.close();
			}
			try {
				pw = new PrintWriter(new FileOutputStream("ACM"+i+".json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//a. Display a message to the user indicating which file could not be opened/created;
				System.out.println("\nCould not open/create the file " + "ACM"+i+".json");
				//b. Delete all other created output files (if any)
				for (int k = 1; k <= i; k++) {
					deleteFile = new File("IEEE"+k+".json");
					deleteFile.delete();
				}
				System.exit(0);
			}finally {
				pw.close();
			}
			try {
				pw = new PrintWriter(new FileOutputStream("NJ"+i+".json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//a. Display a message to the user indicating which file could not be opened/created;
				System.out.println("\nCould not open/create the file " + "NJ"+i+".json");
				//b. Delete all other created output files (if any)
				for (int k = 1; k <= i; k++) {
					deleteFile = new File("IEEE"+k+".json");
					deleteFile.delete();
				}
				System.exit(0);
			}finally {
				pw.close();
			}

		}

		//5. Write a method called processFilesForValidation
		processFilesForValidation(NUMBER_OF_FILE);

		//7. Ask the user to enter the name of one of the created output files to display
		BufferedReader readfile = null;
		String openFileName = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the name of one of the files that you need to review: ");
		openFileName = scanner.next();	
		try {
			readfile = new BufferedReader(new FileReader(openFileName));	
			String line;
			try {
				line = readfile.readLine();
				System.out.println("Here are the contents of the successfully created JSON File:" + openFileName);
				while (line != null) { 
					System.out.println(line);
					pw.println(line);
					line = readfile.readLine(); 
				}
				readfile.close();
				System.out.println("Goodbye! Hope you have enjoyed creating the needed files using BibCreator.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("\nThere is an error while reading the file.");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("Could not open output file. File does not exist; possibly it could not be created! "+
					"\n\nHowever, you will be allowed another chance to enter another file name.\nPlease enter the name of one of the files that you need to review: ");
			openFileName = scanner.next();
			try {
				readfile = new BufferedReader(new FileReader(openFileName));
				String line;
				try {
					line = readfile.readLine();
					System.out.println("Here are the contents of the successfully created JSON File:" + openFileName);
					while (line != null) { 
						System.out.println(line);
						pw.println(line);
						line = readfile.readLine(); 
					}
					readfile.close();
					System.out.println("Goodbye! Hope you have enjoyed creating the needed files using BibCreator.");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("\nCould not open output file again. Either file does not exist or could not be created!\n"+
						"Sorry! I am unable to display your desired files! Program will exit!");
			}	
		}
		// Close scanner
		scanner.close();

	}// end of main

	// Method processFilesForValidation represents the core engine for processing the input files and creating the output ones
	private static void processFilesForValidation(int number) {
		// TODO Auto-generated method stub
		Scanner fileReader = null;
		int countInvalid = 0;
		for(int j = 1; j <= number; j++) 
		{
			boolean valid = true;
			//Open the input file
			try {
				fileReader = new Scanner(new FileInputStream("Latex"+j+".bib"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("\nCould not open input file Latex"+ j +".bib for reading.\n\nPlease check if file exists! Program will terminate after closing any opened files.");
				if(j!=1) {
					fileReader.close();
				}
				System.exit(0);
			}   

			while (fileReader.hasNext()) {
				String firstInvalid = "";
				String aLine = "";
				//5b. A method must process each of these files to find out whether it is valid or not
				try {
					aLine = fileReader.nextLine();
					//5d. If a file is invalid the method will stop the processing of this file only and throw FileInvalidException
					if (aLine.contains("{}")|| aLine.contains("{ }")) {
						countInvalid++;
						valid = false;
						firstInvalid = aLine.split("\\=")[0];
						//6. Once the processing is done, all unsuccessfully created files will be deleted
						File f = new File("IEEE"+j+".json");
						f.delete();
						f = new File("ACM"+j+".json");
						f.delete();
						f = new File("NJ"+j+".json");
						f.delete();
						//throw Exception
						String message = "\nError: Detected Empty Field!\n============================\nProblem detected with input file: Latex"+j+".bib\n"
								+ "File is Invalid: Field " +"\""+firstInvalid+"\""+" is Empty. Processing stopped at this point. Other empty fields may present as well!";
						throw new FileInvalidException(message);
					}
				}catch(FileInvalidException e) {
					System.out.println(e.getMessage());
					break;
				}

			}
			//c. If a file is valid the method will create the proper records for each of the 3 formats (IEEE, ACM and NJ) and store them in these files
			if(valid) {
				BufferedReader fileRead = null;
				PrintWriter pwIEEE = null;
				PrintWriter pwACM = null;
				PrintWriter pwNJ = null;
				try {
					pwIEEE = new PrintWriter(new FileOutputStream("IEEE"+j+".json"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("\nCould not open/create the file " + "IEEE"+j+".json");
				}

				try {
					pwACM = new PrintWriter(new FileOutputStream("ACM"+j+".json"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("\nCould not open/create the file " + "NJ"+j+".json");
				}

				try {
					pwNJ = new PrintWriter(new FileOutputStream("NJ"+j+".json"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("\nCould not open/create the file " + "NJ"+j+".json");
				}

				try {
					fileRead = new BufferedReader(new FileReader("Latex"+j+".bib"));
					String oneLine = fileRead.readLine();
					Article art = new Article();
					int counter = 0;
					while (oneLine!= null) {
						if (oneLine.contains("@ARTICLE")) {
							art = new Article();
							counter++;
						}else if(oneLine.contains("{") && oneLine.contains("}")){
							String field = oneLine.split("=")[0];
							String content = oneLine.split("\\{")[1];
							content = content.split("\\}")[0];
							content = content.trim();

							switch(field) {
							case "author":
								art.setAuthor(content);
								break;
							case "journal":
								art.setJournal(content);
								break;
							case "title":
								art.setTitle(content);
								break;
							case "number":
								art.setNumber(content);
								break;
							case "year":
								art.setYear(content);
								break;
							case "volume":
								art.setVolume(content);
								break;
							case "month":
								art.setMonth(content);
								break;
							case "pages":
								art.setPages(content);
								break;
							case "doi":
								art.setDoi(content);
								break;
							}

						}else if(oneLine.contains("}")) {
							pwIEEE.println(art.IEEE());
							pwACM.println("["+ counter +"]     " +art.ACM());
							pwNJ.println(art.NJ());
						}
						oneLine = fileRead.readLine();
					}
					fileRead.close();				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pwIEEE.close();
				pwACM.close();
				pwNJ.close();
			}
		}
		fileReader.close();
		System.out.println("\nA total of " + countInvalid + " file were invalid, and could not be procecced. All other " + (number-countInvalid) + " \"Valid\" files have been created.");

	}// end of method

}