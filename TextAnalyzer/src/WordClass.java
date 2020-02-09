public class WordClass {
	private String word;

	public WordClass() {
		word = "";
	}

	public WordClass(String word) {
		this.word = word.toLowerCase();
	}

	public int length() {
		return word.length();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String input) {
		word = input;
	}

	public int getNumSyllables() {
		if (word.equals("takedown")) {
			return 2;
		} else {
			return Math.max(1, numVowels() - numReductions());
		}
	}

	public int numVowels() {
		int j = 0;
		for (int i = 0; i < word.length(); i++)
			if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
					|| word.charAt(i) == 'u' || word.charAt(i) == 'y')
				j++;
		return j;
	}

	public boolean isVowel(int i) {
		return word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o'
				|| word.charAt(i) == 'u' || word.charAt(i) == 'y';
	}

	public int numReductions() {
		try {
			int numRed = 0;
			if ((word.charAt(word.length() - 1) == 'e'
					&& (word.charAt(word.length() - 2) != 'l' || isVowel(word.length() - 3)))
					|| (word.length() > 2 && (word.length() >= 2
							&& (word.charAt(word.length() - 3) != 'e' && word.charAt(word.length() - 3) != 's')
							&& word.substring(word.length() - 2).equals("es")
							|| (word.substring(word.length() - 2).equals("ed")
									&& word.charAt(word.length() - 3) != 't')))
					|| (word.length() > 2 && (word.charAt(word.length() - 2) == 'a'
							&& (word.charAt(word.length() - 1) == 'o' || word.charAt(word.length() - 1) == 'u')))
					|| (word.length() > 2 && (isVowel(word.length() - 2) && word.charAt(word.length() - 1) == 'y')))
				numRed++;

			if (word.length() > 2 && word.substring(word.length() - 2).equals("sm"))
				numRed--;
			if (word.length() >= 4 && (!isVowel(word.length() - 4)) && (word.substring(word.length() - 3).equals("les")
					|| word.substring(word.length() - 3).equals("led")))
				numRed--;

			for (int i = 0; i < word.length() - 2; i++) {
				if (isVowel(i) && isVowel(i + 1)) {
					if ((word.length() > 3)
							&& !(word.charAt(i) == 'i' && (word.charAt(i + 1) == 'a'
									|| (word.charAt(i + 1) == 'o' && word.charAt(i - 1) != 't'
											&& word.charAt(i - 1) != 'x' && word.charAt(i - 1) != 'c'
											&& (word.charAt(i - 1) != 's') || word.charAt(i - 2) == 'y')))
							&& (word.charAt(i) != 'y' || (word.charAt(i) == 'y' && i == 0))) {
						numRed++;
					}
				}
			}
			return numRed;
		} catch (Exception e) {
			return 0;
		}
	}
}