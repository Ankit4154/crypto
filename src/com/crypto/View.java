package com.crypto;

import com.crypto.controllers.BookmarkController;
import com.crypto.entities.Bookmark;
import com.crypto.entities.User;

public class View {

	public static void bookmark(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + " is bookmarking.");
		for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
			int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPE_COUNT);
			int bookmarkOffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
			Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];

			BookmarkController.getInstance().saveUserBookmark(user, bookmark);

			System.out.println("View " + bookmark);
		}
	}

}
