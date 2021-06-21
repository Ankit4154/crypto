package com.crypto;

import java.util.ArrayList;
import java.util.List;

import com.crypto.constants.BookGenre;
/*
import org.apache.commons.lang3.StringUtils;
import com.crypto.constants.BookGenre;
import com.crypto.constants.MovieGenre;
import com.crypto.constants.UserType;
*/
import com.crypto.constants.Gender;
import com.crypto.constants.MovieGenre;
import com.crypto.entities.Bookmark;
import com.crypto.entities.User;
import com.crypto.entities.UserBookmark;
import com.crypto.services.BookmarkService;
import com.crypto.services.UserService;
import com.crypto.util.IOUtil;

public class DataStore {

	/*
	 * public static final int USER_BOOKMARK_LIMIT = 5; public static final int
	 * BOOKMARK_COUNT_PER_TYPE = 5; public static final int BOOKMARK_TYPE_COUNT = 3;
	 * public static final int TOTAL_USER_COUNT = 5;
	 */
	public static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User");
		for (String row : data) {
			String[] values = row.split("\t");
			Gender gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;

			} else if (values[5].equals("t")) {
				gender = Gender.OTHERS;
			}
			User user = UserService.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3],
					values[4], gender, values[6]);
			users.add(user);
		}
		/*
		 * users[0] = UserService.getInstance().createUser(1000, "user0@crypto.com",
		 * "test", "Ankit", "Singh", Gender.MALE, UserType.CHIEF_EDITOR); users[1] =
		 * UserService.getInstance().createUser(1001, "user1@crypto.com", "test",
		 * "Abhishek", "Tiwari", Gender.MALE, UserType.USER); users[2] =
		 * UserService.getInstance().createUser(1002, "user2@crypto.com", "test",
		 * "Himanshu", "Jatale", Gender.MALE, UserType.EDITOR); users[3] =
		 * UserService.getInstance().createUser(1003, "user3@crypto.com", "test",
		 * "Prachi", "Desai", Gender.FEMALE, UserType.EDITOR); users[4] =
		 * UserService.getInstance().createUser(1004, "user4@crypto.com", "test",
		 * "Pooja", "Rathi", Gender.FEMALE, UserType.USER);
		 */
	}

	private static void loadWebLinks() {

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Web Link");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkService.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
					values[2], values[3]);
			bookmarkList.add(bookmark);
		}

		bookmarks.add(bookmarkList);
		/*
		 * bookmarks[0][0] = BookmarkService.getInstance().createWebLink(2000,
		 * "Taming Tiger, Part 2",
		 * "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
		 * "http://www.javaworld.com"); bookmarks[0][1] =
		 * BookmarkService.getInstance().createWebLink(2001,
		 * "How do I import a pre-existing Java project into Eclipse and get up and running?"
		 * ,
		 * "http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
		 * "http://www.stackoverflow.com"); bookmarks[0][2] =
		 * BookmarkService.getInstance().createWebLink(2002,
		 * "Interface vs Abstract Class",
		 * "http://mindprod.com/jgloss/interfacevsabstract.html",
		 * "http://mindprod.com"); bookmarks[0][3] =
		 * BookmarkService.getInstance().createWebLink(2003,
		 * "NIO tutorial by Greg Travis",
		 * "http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf",
		 * "http://cs.brown.edu"); bookmarks[0][4] =
		 * BookmarkService.getInstance().createWebLink(2004,
		 * "Virtual Hosting and Tomcat",
		 * "http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html",
		 * "http://tomcat.apache.org");
		 */
	}

	private static void loadBooks() {

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkService.getInstance().createBook(Long.parseLong(values[0]), values[1], "",
					Integer.parseInt(values[2]), values[3], values[4].split(","), BookGenre.valueOf(values[5]),
					Double.parseDouble(values[6]));
			bookmarkList.add(bookmark);
		}

		bookmarks.add(bookmarkList);
		/*
		 * bookmarks[2][0] = BookmarkService.getInstance().createBook(4000, "Walden",
		 * "", 1854, "Wilder Publications", new String[] { "Henry David Thoreau" },
		 * BookGenre.PHILOSOPHY, 4.3); bookmarks[2][1] =
		 * BookmarkService.getInstance().createBook(4001,
		 * "Self-Reliance and Other Essays", "", 1993, "Dover Publications", new
		 * String[] { "Ralph Waldo Emerson" }, BookGenre.PHILOSOPHY, 4.5);
		 * bookmarks[2][2] = BookmarkService.getInstance().createBook(4002,
		 * "Light From Many Lamps", "", 1854, "Touchstone", new String[] {
		 * "Lillian Eichler Watson" }, BookGenre.PHILOSOPHY, 5.0); bookmarks[2][3] =
		 * BookmarkService.getInstance().createBook(4003, "Head First Design Patterns",
		 * "", 1854, "O'Reilly Media", new String[] { "Eric Freeman", "Bert Bates",
		 * "Kathy Sierra", "Elisabeth Robson" }, BookGenre.TECHNICAL, 4.5);
		 * bookmarks[2][4] = BookmarkService.getInstance().createBook(4004,
		 * "Effective Java Programming Language Guide", "", 1854, "Prentice Hall", new
		 * String[] { "Joshua Bloch" }, BookGenre.TECHNICAL, 4.9);
		 */
	}

	private static void loadMovies() {

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkService.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",
					Integer.parseInt(values[2]), values[3].split(","), values[4].split(","), MovieGenre.valueOf(values[5]),
					Double.parseDouble(values[6]));
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
		/*
		 * bookmarks[1][0] = BookmarkService.getInstance().createMovie(3000,
		 * "Citizen Kane", "", 1941, new String[] { "Orson Welles", "Joseph Cotten" },
		 * new String[] { "Orson Welles" }, MovieGenre.CLASSICS, 8.5); bookmarks[1][1] =
		 * BookmarkService.getInstance().createMovie(3001, "The Grapes of Wrath", "",
		 * 1940, new String[] { "Henry Fonda", "Jane Darwell" }, new String[] {
		 * "John Ford" }, MovieGenre.CLASSICS, 8.2); bookmarks[1][2] =
		 * BookmarkService.getInstance().createMovie(3002, "A Touch of Greatness", "",
		 * 2004, new String[] { "Orson Welles", "Joseph Cotten" }, new String[] {
		 * "Orson Welles" }, MovieGenre.DOCUMENTARIES, 8.5); bookmarks[1][3] =
		 * BookmarkService.getInstance().createMovie(3003, "The Big Bang Theory", "",
		 * 2007, new String[] { "Kaley Cuoco", "Jim Parsons" }, new String[] {
		 * "Chuck Lorre", "Bill Prady" }, MovieGenre.TV_SHOWS, 8.7); bookmarks[1][4] =
		 * BookmarkService.getInstance().createMovie(3004, "Ikiru", "", 1952, new
		 * String[] { "Takashi Shimura", "Minoru Chiaki" }, new String[] {
		 * "Akira Kurosawa" }, MovieGenre.CLASSICS, 8.4);
		 */
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}
}
