import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Lab13Main {
	public ArrayList<Movie> movieList = new ArrayList<Movie>();
	private static Scanner keyboard = new Scanner(System.in);
    public static final int MOVIE_COUNT = 5; // Return this many movies in the searches
	public static Map<String, Movie> byNameMap = new HashMap<>();
	public static Map<Integer, ArrayList<Movie>> byYearMap = new HashMap<>();
	public static Map<String, ArrayList<Movie>> byGenreMap = new HashMap<>();

    /*
      Partially complete
      Add your code where needed
    */
	public static void main(String[] args) {
		Lab13Main lab13main = new Lab13Main();
		lab13main.readMovies("movies.tsv");
        // As a test: comment out this line after you're sure
        // that readMovies works
        // lab13main.displayMovies(lab13main.getList());
		int choice;
		do {
			choice = getMenuChoice();
			switch (choice) {
				// case 1 is done for you
				case 1: lab13main.sortBy("ID");
					lab13main.displayMovies(lab13main.getList());
					break;
				case 2:
					lab13main.sortBy("Name");
					lab13main.displayMovies(lab13main.getList());
					break;
				case 3:
					lab13main.sortBy("Year");
					lab13main.displayMovies(lab13main.getList());
					break;
				case 4:
					lab13main.sortBy("ReverseYear");
					lab13main.displayMovies(lab13main.getList());
					break;
				case 5:
					System.out.print("Enter the movie name: ");
					// Use nextLine() everywhere!
					String name = keyboard.nextLine();
					ArrayList<Movie> results_name = lab13main.searchByName(name);
					lab13main.displayMovies(results_name);
					break;
				case 6:
					System.out.print("Enter the movie year: ");
					String year_str = keyboard.nextLine();
					int year = Integer.parseInt(year_str);
					ArrayList<Movie> results_year = lab13main.searchByYear(year);
					lab13main.displayMovies(results_year);
					break;
				case 7:
					System.out.print("Enter the movie genre: ");
					String genre = keyboard.nextLine();
					ArrayList<Movie> results_genre = lab13main.searchByGenre(genre);
					lab13main.displayMovies(results_genre);
					break;
				case 8: 
					lab13main.displayTotals();
					break;
			}
		} while (choice != 8);
	}
	
    /*
       Don't change this method
    */
	public static int getMenuChoice() {
		System.out.println("1. Display by ID\n2. Display by name\n3. Display by year\n" + 
				"4. Display by year in reverse\n5. Search by name\n6. Search by year\n" +
				"7. Search by Genre\n8. Quit");
		System.out.print("Enter your choice: ");
		int choice = Integer.parseInt(keyboard.nextLine());
		if (choice < 1 || choice > 8) choice = 8;
		return choice;
	}
		
	public void readMovies(String filename) {
		Scanner fileInput = null;
		
		try {
			fileInput = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
        
        /* Your code here
           While there are lines in the file
            read a line and split it on \t
            create a Movie using toMovie
            add it to movieList
        */
		while (fileInput.hasNextLine()){
			String movieString = fileInput.nextLine();
			String[] movieinfo = movieString.split("\t");
			String movieName = movieinfo[1].strip().replace("\"", "");
			int year = Integer.parseInt(movieinfo[2].strip());
			ArrayList<String> genres = new ArrayList<String>();
			for (int i=4; i<movieinfo.length; i++) {
				genres.add(movieinfo[i].strip());
			}

			movieList.add(toMovie(movieinfo));

			// also populate byNameMap
			byNameMap.put(movieName, toMovie(movieinfo));

			// also populate byYearMap
			if (!byYearMap.containsKey(year)){
				ArrayList<Movie> newList = new ArrayList<>();
				newList.add(toMovie(movieinfo));
				byYearMap.put(year, newList);				
			} else {
				ArrayList<Movie> existingList =  byYearMap.get(year);
				existingList.add(toMovie(movieinfo));
			}

			// also populate byGenreMap
			for (String genre: genres){
				if (!byGenreMap.containsKey(genre)){
					ArrayList<Movie> newList = new ArrayList<>();
					newList.add(toMovie(movieinfo));
					byGenreMap.put(genre, newList);				
				} else {
					ArrayList<Movie> existingList =  byGenreMap.get(genre);
					existingList.add(toMovie(movieinfo));
				}
			}


		}
		
	}
	
	public Movie toMovie(String[] str) {
        /*  Returns one Movie from the data in str
            Each line of str should contain one field
            Change last one to ArrayList<String> for genres
        */
        int movieID = Integer.parseInt(str[0].strip());
		String movieName = str[1].strip().replace("\"", "");
		int year = Integer.parseInt(str[2].strip());
		String country = str[3].strip().replace("\"", "");
		ArrayList<String> genres = new ArrayList<String>();
        // Start at position #4
        // Strip and add to the ArrayList of genres
		for (int i=4; i<str.length; i++) {
			genres.add(str[i].strip());
		}
		return new Movie(movieID, movieName, year, country, genres);
	}
	
    // Don't change this, even if you don't like my table spacing
	private void displayMovies(ArrayList<Movie> list) {
		if (list.size() == 0) {
			System.out.println("Nothing to display");
		} else {
			System.out.format("%7s %50s %5s %30s %6s\n", "ID", "Name", "Year", "Country", "Genres");
			for (Movie m: list) {
				System.out.format("%7s %50s %5d %30s ", m.getMovieID(), m.getMovieName(), 
					m.getYear(), m.getCountry());
				for (int i=0; i<m.getGenres().size(); i++) {
					System.out.print(m.getGenres().get(i) + " ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	
    // Sort according to the field indicated by s
	public void sortBy(String s) {
        switch (s) {
            case "ID":
                // Use Movie's built-in compareTo
                Collections.sort(movieList);
                break;
            case "Name":
				Collections.sort(movieList, new Comparator<Movie>() {
					@Override
					public int compare(Movie m1, Movie m2){
						return m1.getMovieName().compareTo(m2.getMovieName());
					}
				});
                break;
            case "Year":
				Collections.sort(movieList, new SortByYear());
                break;
            case "ReverseYear":
				Comparator<Movie> c = Collections.reverseOrder(new SortByYear());
				Collections.sort(movieList, c);
                break;
        }
	}

	// A seperate class SortByYear()
	public class SortByYear implements Comparator<Movie> {
		@Override
		public int compare(Movie m1, Movie m2) {
			// Sorts in ascending order (oldest to newest)
			return Integer.compare(m1.getYear(), m2.getYear());
		}
	}
	
    // Search for MOVIE_COUNT movies by name
	public ArrayList<Movie> searchByName(String name) {
        // Sort by id before searches for consistent results
        sortBy("ID");

        // List of results
		ArrayList<Movie> list = new ArrayList<Movie>();

        // // Count the # of matches
		// int count = 0;
		// for (Movie m: movieList) {
        //     // Does m match on the name key?
		// 	if (m.getMovieName().equals(name)) {
        //         // Yes, so add it to the result list
		// 		list.add(m);
		// 		count++;

        //         // Quit if we hit the maximum # of movies to return
		// 		if (count == Lab13Main.MOVIE_COUNT) break;
		// 	}
		// }
		// return list;

		Movie m = byNameMap.get(name);
		list.add(m);
		return list;
	}
	
    // Search for MOVIE_COUNT movies by year
	public ArrayList<Movie> searchByYear(int year) {
		ArrayList<Movie> list = new ArrayList<Movie>();
		// Sort by id before searches for consistent results
        sortBy("ID");

        // Count the # of matches
		// int count = 0;
		// for (Movie m: movieList) {
        //     // Does m match on the name key?
		// 	if (m.getYear() == year) {
        //         // Yes, so add it to the result list
		// 		list.add(m);
		// 		count++;

        //         // Quit if we hit the maximum # of movies to return
		// 		if (count == Lab13Main.MOVIE_COUNT) break;
		// 	}
		// }

		list = byYearMap.get(year);
		return list;
	}
	
    // Search for MOVIE_COUNT movies by genre
	public ArrayList<Movie> searchByGenre(String genre) {
		ArrayList<Movie> list = new ArrayList<Movie>();
		// Sort by id before searches for consistent results
        sortBy("ID");

        // Count the # of matches
		// int count = 0;
		// for (Movie m: movieList) {
        //     // Does m match on the name key?
		// 	if (m.getGenres().contains(genre)) {
        //         // Yes, so add it to the result list
		// 		list.add(m);
		// 		count++;

        //         // Quit if we hit the maximum # of movies to return
		// 		if (count == Lab13Main.MOVIE_COUNT) break;
		// 	}
		// }

		list = byGenreMap.get(genre);
		return list;
	}
	
    // Breaks encapsulation, boo!
	public ArrayList<Movie> getList() { return movieList; }

	public void displayTotals(){
		System.out.println("Movie totals");
		int size_list = movieList.size();
		int size_name = byNameMap.size();
		int size_year = byYearMap.size();
		int size_genre = byGenreMap.size();
		System.out.println("movieList size: " + size_list);
		System.out.println("byNameMap size: " + size_name);
		System.out.println("byYearMap size: " + size_year);
		System.out.println("byGenreMap size: " + size_genre);
	}

}
