
public class Index {
	private String[] edu_Levels = new String[] { "College Graduate", "College Student", "10th - 12th Grade",
			"8th - 9th Grade", "7th Grade", "6th Grade", "5th Grade" };

	interface Lambda {
		int func(double[] p, double s);
	}

	public String findIndex(String type, double score) {
		Lambda t = (index, s) -> {
			for (int i = (index.length - 1); i >= 1; i--) {
				if ((s >= index[i]) && (s < index[i - 1])) {
					return i - 1;
				}
			}
			return 0;
		};

		switch (type) {
		case ("Flesch"):
			return edu_Levels[((edu_Levels.length - 1)
					- t.func(new double[] { 100, 90, 80, 70, 60, 50, 30, 0 }, score))];
		case ("Flesch_Kincaid"):
			return "~";
		case ("SMOG"):
			return "~";
		case ("GunningFog"):
			return edu_Levels[(t.func(new double[] { 100, 17, 13, 10, 8, 7, 6, 0 }, score))];
		case ("Coleman_Liau"):
			return "~";
		}
		return "~";
	}
}
