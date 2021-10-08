/*
 * Author: The Cam Phan and Hoang Nhan Duyen Nguyen
 * Student ID: 1832329 and 1911876
 * A class Article to convert to IEEE, ACM, NJ format 
 */
package BibPackage;
public class Article {

	//Properties 
	private String author, title, journal, month, year, volume, number, pages, doi;

	//Fully Parameterized Constructor
//	public Article(String author, String journal, String title, String volume,
//			String number, String pages, String month, String year, String doi) {
//		this.author = author;
//		this.title = title;
//		this.journal = journal;
//		this.volume = volume;
//		this.number = number;
//		this.pages = pages;
//		this.month = month;
//		this.year = year;
//		this.doi = doi;
//	}
//	
//	//Default Constructor
//	public Article() {
//		this.author = "";
//		this.title = "";
//		this.journal = "";
//		this.volume = "";
//		this.number = "";
//		this.pages = "";
//		this.month = "";
//		this.year = "";
//		this.doi = "";
//	}
	
	// Getters and Setters
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	// Methods
	// Method to return a record of IEEE format 
	public String IEEE() {
		String ieeeAuthor = author.replace(" and ", ", ");
		return ieeeAuthor+ ". " + "\"" + title +"\", " + journal + ", vol. " + volume +
				", no. " + number +", p. " + pages + ", " + month + " " + year + ".\n";
	}

	// Method to return a record of ACM format 
	public String ACM() {
		String acmAuthor = "";
		// if there are more than 1 author
		if (author.contains(" and ")) {
			acmAuthor = author.split(" and ")[0].trim() + " et al";
		}else { // if there is only 1 author
			acmAuthor = author;
		}
		return acmAuthor +". "+ year +". " + title +". " + journal +". " + volume +", " + number + " (" + year +"), " + pages +". DOI:https://doi.org/" + doi +".\n";
	}

	// Method to return a record of NJ format 
	public String NJ() {
		String njAuthor = author.replace(" and ", " & ");	
		return  njAuthor +". " + title + ". " + journal +". " + volume + ", " + pages +"(" + year +").\n";
	}
	
}
