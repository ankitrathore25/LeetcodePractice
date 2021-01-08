package practice.Text_Justify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Text_Justify {
	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> result = new ArrayList<>();

		StringBuilder currLine = new StringBuilder();

		for (String w : words) {
			if (currLine.length() + w.length() == maxWidth) {
				currLine.append(w);
				result.add(currLine.toString());
				currLine = new StringBuilder();
				continue;
			} else if (currLine.length() + w.length() > maxWidth) {
				String currLineStr = currLine.toString().trim();
				String[] wordsInLine = currLineStr.split(" ");
				int remainingSpace = maxWidth - currLineStr.length() + wordsInLine.length - 1;
				int wordCount = wordsInLine.length;
				int j = 0, slot = wordCount - 1;
				int spacelength = remainingSpace / slot;
				int remainderSpaceLen = remainingSpace % slot; // some slots can have more space(leftmost)
				currLine = new StringBuilder();
				for (; j < wordCount - 1; j++) {
					String word = wordsInLine[j];
					addSpace(currLine.append(word), spacelength);
					if (remainderSpaceLen-- > 0) {
						addSpace(currLine, 1);
					}
				}
				currLine.append(wordsInLine[j]);
				if (currLine.length() < maxWidth) {
					addSpace(currLine, maxWidth - currLine.length());
				}
				result.add(currLine.toString());
				currLine = new StringBuilder();
			}
			currLine.append(w).append(" ");
		}
		String lastLine = currLine.toString().trim();
		if (!lastLine.isEmpty()) {
			addSpace(currLine, maxWidth - lastLine.length());
			result.add(lastLine.toString());
		}

		return result;
	}

	public static void addSpace(StringBuilder sb, int count) {
		// call by reference for stringBuilder
		for (int i = 0; i < count; i++)
			sb.append(" ");
	}

	public static void main(String[] args) {
		List<String> wordList = Arrays.asList("Science", "is", "what", "we", "understand", "well", "enough", "to",
				"explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do");
		int maxWidth = 16;
		String[] words = new String[wordList.size()];
		List<String> res = fullJustify(wordList.toArray(words), maxWidth);

		for (int i = 0; i < res.size(); i++)
			System.out.println(res.get(i));
	}

}
