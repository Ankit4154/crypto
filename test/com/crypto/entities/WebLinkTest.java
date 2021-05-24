package com.crypto.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import com.crypto.services.BookmarkService;

class WebLinkTest {

	@Test
	void testIsKidFriendly() {
		// Test 1 porn in url -- false
		WebLink webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com");
		boolean isKidFriendlyEligible = webLink.isKidFriendly();
		assertFalse("For porn in url - isKidFriendlyEligible must false", isKidFriendlyEligible);

		// Test 2 porn in title -- false
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming porn, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertFalse("For porn in title - isKidFriendlyEligible must false", isKidFriendlyEligible);

		// Test 3 adult in host -- false
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertFalse("For adult in host - isKidFriendlyEligible must false", isKidFriendlyEligible);

		// Test 4 adult in url, but not in host -- true
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.adult.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaWorld.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertTrue("For adult in url, but not in host - isKidFriendlyEligible must true", isKidFriendlyEligible);

		// Test 5 adult in title only -- true
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming adult, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertTrue("For adult in title only - isKidFriendlyEligible must true", isKidFriendlyEligible);
	}

}
