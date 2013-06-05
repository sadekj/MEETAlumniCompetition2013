package search;

/*import java.io.IOException;
 import java.net.URISyntaxException;

 import org.apache.http.client.ClientProtocolException;
 import org.meetproj.musetrek.Coordinates;
 import org.meetproj.musetrek.MuseTrekAPI;*/

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.language.Soundex;

public class Search {

	/**
	 * Return true if haystack contains needle (ignoring capitalization and
	 * accents and so on).
	 */

	public ArrayList<String> search(ArrayList<String> hstck, String ndl) {
		ArrayList<String> ret = new ArrayList<String>();
		String[] haystack = null;
		String needle;
		int[] counters = null;

		for (int i = 0; i < hstck.size(); i++) {
			haystack = hstck.get(i).split(" ");
			counters = new int[haystack.length];
			for (String b : haystack) {
				if (!(b == null) && (roughlyContains(b, ndl))) {
					counters[i] += 1;
				}
			}
		}

		int l = haystack.length;
		for (int u = 0; u < l; u++) {
			int index = maxIndex(counters);
			if ((index < 0)) {
				break;
			}
			ret.add(haystack[index]);
			counters[index] = -1;
		}

		return ret;
	}

	public boolean roughlyContains(String haystack, String needle) {
		if (haystack.trim().isEmpty() || needle.trim().isEmpty()) {
			return false;
		}
		Soundex mySoundex = new Soundex();
		return (mySoundex.soundex(removeAccents(haystack)).equals(mySoundex
				.soundex(removeAccents(needle))));
	}

	public int maxIndex(int[] t) {
		int maximum = t[0];
		int index = 0;
		for (int i = 1; i < t.length; i++) {
			if (t[i] > maximum) {
				maximum = t[i];
				index = i;
			}
		}
		if (maximum == 0) {
			return -1;
		}
		return index;
	}

	/** Takes a string like "HÃ´tel" returns "Hotel". */
	private String removeAccents(String input) {
		Collator tehCollator = Collator.getInstance(Locale.ENGLISH);
		tehCollator.setStrength(Collator.PRIMARY);
		String x = "";
		String haystack = "abcdefghijklmnopqrstuvwxyz ";
		for (int i = 0; i < input.length(); i++) {
			for (int b = 0; b < haystack.length(); b++) {
				boolean sth = tehCollator.equals(
						haystack.substring(b, (b + 1)),
						input.substring(i, (i + 1)));
				if (sth == true) {
					x = x + haystack.substring(b, (b + 1));
				}
			}
		}
		return x;
	}

	public static void main(String[] args) {
		
	}
}