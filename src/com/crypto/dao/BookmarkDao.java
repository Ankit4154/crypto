package com.crypto.dao;

import java.util.List;

import com.crypto.DataStore;
import com.crypto.entities.Bookmark;
import com.crypto.entities.UserBookmark;

public class BookmarkDao {

	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
}
