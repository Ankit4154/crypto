package com.crypto;

import com.crypto.entities.Bookmark;
import com.crypto.entities.User;
import com.crypto.services.BookmarkService;
import com.crypto.services.UserService;

public class Launch {

	private static User[] users;
	private static Bookmark[][] bookmarks;

	private static void loadData() {
		System.out.println("1. Loading Data ...");
		DataStore.loadData();

		users = UserService.getInstance().getUsers();
		bookmarks = BookmarkService.getInstance().getBookmarks();

		/*System.out.println("2. Printing Data ...");
		printUserData();
		printBookmarkData();
		*/
	}

	private static void printBookmarkData() {
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}
	}

	private static void startBookmarking() {
		System.out.println("\n 2. Bookmarking ...");
		for (User user : users) {
			View.bookmark(user, bookmarks);
		}
	}

	public static void main(String args[]) {
		loadData();
		startBookmarking();
	}

}
