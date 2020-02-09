import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
	private ArrayList<String> list = new ArrayList<String>();

	public Dictionary(String path) {
		try {
			BufferedReader input = new BufferedReader(new FileReader("src\\" + path));
			String str = null;
			while (((str = input.readLine()) != null))
				list.add(str.toLowerCase());
			input.close();
		} catch (IOException e) {
			System.out.println("breaking");
		}
	}

	public boolean isWord(String word) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).equals(word))
				return true;
		return false;
	}
}
