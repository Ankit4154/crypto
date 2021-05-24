package com.crypto.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import com.crypto.services.BookmarkService;

class WebLinkTest {

	@Test
	void testIsKidFriendly() {
		// Test 1 horror in url -- false
		WebLink webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-horror--part-2.html",
				"http://www.javaworld.com");
		boolean isKidFriendlyEligible = webLink.isKidFriendly();
		assertFalse("For horror in url - isKidFriendlyEligible must false", isKidFriendlyEligible);

		// Test 2 horror in title -- false
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming horror, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertFalse("For horror in title - isKidFriendlyEligible must false", isKidFriendlyEligible);

		// Test 3 scary in host -- false
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.scary.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertFalse("For scary in host - isKidFriendlyEligible must false", isKidFriendlyEligible);

		// Test 4 scary in url, but not in host -- true
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.scary.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaWorld.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertTrue("For scary in url, but not in host - isKidFriendlyEligible must true", isKidFriendlyEligible);

		// Test 5 scary in title only -- true
		webLink = BookmarkService.getInstance().createWebLink(2000, "Taming scary, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = webLink.isKidFriendly();
		assertTrue("For scary in title only - isKidFriendlyEligible must true", isKidFriendlyEligible);
	}

}
