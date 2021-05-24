package com.crypto.controllers;

import com.crypto.entities.Bookmark;
import com.crypto.entities.User;
import com.crypto.services.BookmarkService;

public class BookmarkController {

	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkService.getInstance().saveUserBookmark(user, bookmark);
	}

}
