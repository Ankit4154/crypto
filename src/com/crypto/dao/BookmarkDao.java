package com.crypto.dao;

import java.util.ArrayList;
import java.util.List;

import com.crypto.DataStore;
import com.crypto.entities.Bookmark;
import com.crypto.entities.UserBookmark;
import com.crypto.entities.WebLink;

public class BookmarkDao {

	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
	
	public List<WebLink> getAllWebLinks(){
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks = bookmarks.get(0);
		for(Bookmark bookmark : allWebLinks) {
			result.add((WebLink)bookmark);
		}
		return result;
	}
	// Get weblink with particular download status value
	public List<WebLink> getWebLink(WebLink.DownloadStatus downloadStatus){
		List<WebLink> result = new ArrayList<>();
		List<WebLink> allWebLinks = getAllWebLinks();
		
		for(WebLink weblink : allWebLinks) {
			if(weblink.getDownloadStatus().equals(downloadStatus)) {
				result.add(weblink);
			}
		}
		
		return result;
	}
}
