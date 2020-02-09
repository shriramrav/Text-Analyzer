import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileOptions {

	public static Object[] config(String str) {
		ArrayList<String> list = new ArrayList<String>();
		int endOfSentenceCtr = 0;
		String[] temp1 = str.split(" ");
		for (int i = 0; i < temp1.length; i++) {
			String[] temp2 = temp1[i].split("");
			boolean bool = false;
			switch (temp2[temp2.length - 1]) {
			case ".":
				if (Character.isLowerCase(temp2[temp2.length - 2].toCharArray()[0])) {
					endOfSentenceCtr++;
					bool = true;
				}
				break;
			case "!":
				endOfSentenceCtr++;
				bool = true;
				break;
			case "?":
				endOfSentenceCtr++;
				bool = true;
				break;
			case ",":
				bool = true;
				break;
			}
			if (bool) {
				String temp = "";
				for (int u = 0; u < (temp2.length - 1); u++) {
					temp += temp2[u];
				}
				temp1[i] = temp;
			}
			if ((!temp1[i].equals(" ")) && (!temp1[i].equals(""))) {
				list.add(temp1[i]);
			}

		}
		return (new Object[] { list, endOfSentenceCtr });
	}

	@SuppressWarnings("finally")
	public static String getWhole(String path) {
		String temp = "";
		try {
			BufferedReader input = new BufferedReader(new FileReader("src\\" + path));
			String str = null;
			while (((str = input.readLine()) != null))
				temp += " " + str;
		} catch (IOException e) {
			System.out.println("Couldn't find file");
		} finally {
			System.out.println(temp);
			return temp;
		}
	}

}
