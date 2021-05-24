package com.crypto.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.crypto.constants.MovieGenre;
import com.crypto.services.BookmarkService;

class MovieTest {

	@Test
	void testIsKidFriendly() {
		// Test 1 - if HORROR -- false
		Movie movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		boolean isKidFriendly = movie.isKidFriendly();
		assertFalse("Movie is horror, false for kids", isKidFriendly);

		// Test 2 - if DOCUMENTARIES -- false
		movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" },
				MovieGenre.DOCUMENTARIES, 8.5);
		isKidFriendly = movie.isKidFriendly();
		assertFalse("Movie is documentaries, false for kids", isKidFriendly);

		// Test 3 - if GAY LESBIANS -- false
		movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" },
				MovieGenre.GAY_AND_LESBIAN, 8.5);
		isKidFriendly = movie.isKidFriendly();
		assertFalse("Movie is gay Lesbian, false for kids", isKidFriendly);

		// Test 4 - if COMEDY -- true
		movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.COMEDY,
				8.5);
		isKidFriendly = movie.isKidFriendly();
		assertTrue("Movie is Comedy, true for kids", isKidFriendly);

	}

}
