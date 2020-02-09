import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextAnalyzer {
	interface Lambda {
		boolean check(String word);
	}

	private ArrayList<String> list = new ArrayList<String>();
	private int num_sentences;
	private int num_syllables;

	public TextAnalyzer(String str, boolean b) {
		Object[] obj = FileOptions.config(str);
		list = (ArrayList<String>) (obj[0]);
		num_sentences = (int) (obj[1]);
		num_syllables = 0;
		for (int i = 0; i < list.size(); i++) {
			num_syllables += (new WordClass(list.get(i))).getNumSyllables();
		}
	}

	public double Flesch() {
		return formatDouble(
				206.835 - (1.015 * list.size() / num_sentences) - (84.6 * num_syllables / list.size()) );
	}

	public double Flesch_Kincaid() {
		return formatDouble((0.39 * list.size() / num_sentences) + (11.9 * num_syllables / list.size()) - 15.59);
	}

	public double GunningFog() {
		Lambda t = (word) -> {
			if (new WordClass(word).getNumSyllables() >= 3) {
				String[] temp = word.split("");
				if (((temp[temp.length - 2] + temp[temp.length - 1]).equals("ed"))
						|| ((temp[temp.length - 2] + temp[temp.length - 1]).equals("es"))
						|| ((temp[temp.length - 3] + temp[temp.length - 2] + temp[temp.length - 1]).equals("ing")))
					return false;
				else
					return true;
			} else
				return false;
		};

		int temp = 0;

		Dictionary d = new Dictionary("dictionary.txt");

		for (int i = 0; i < list.size(); i++)
			if (t.check(list.get(i)))
				if ((i + 1) != list.size())
					if ((d.isWord(list.get(i).toLowerCase())) && (d.isWord(list.get(i + 1).toLowerCase())))
						temp++;
					else
						i += 2;

		return (formatDouble(0.4 * (((double) list.size() / num_sentences) + ((100.0 * temp) / list.size()))));

	}

	public double SMOG() {
		int temp = list.stream().filter(t -> (new WordClass(t)).getNumSyllables() >= 3).collect(Collectors.toList())
				.size();
		return formatDouble((1.0430 * Math.sqrt((double) temp * ((double) 30 / num_sentences))) + 3.1291);
	}

	public double Coleman_Liau() {
		List<Integer> l = list.stream().map(t -> t.length()).collect(Collectors.<Integer>toList());
		int temp = 0;
		for (Integer i : l)
			temp += i;

		return formatDouble(((0.0588 * ((double) 100 * (double) temp / list.size()))
				- (0.296 * ((double) 100 * (double) num_sentences / list.size())) - 15.8));
	}

	private double formatDouble(double temp) {
		return (Double.valueOf(String.format("%.2f", temp)));
	}

	public int getWords() {
		return list.size();
	}

	public int getSyllables() {
		return num_syllables;
	}

	public int getSentences() {
		return num_sentences;
	}
}
