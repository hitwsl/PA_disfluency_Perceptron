import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;
import java.io.IOException;

public class pipe {

	//Map<String, Integer> dic = new HashMap<String, Integer>();
	Vector<String> sentence = new Vector<String>();
	Vector<String> postag = new Vector<String>();
	Vector<String> sentenceOfTags = new Vector<String>();
	Vector<Vector<String>> features = new Vector<Vector<String>>();
	//Vector<String> charType = new Vector<String>();
	//Vector<Integer> begin = new Vector<Integer>();//下面三个变量保存字典特征相关的内容,现在用不着
	//Vector<Integer> middle = new Vector<Integer>();
	//Vector<Integer> end = new Vector<Integer>();
	//String raw_sen = "";
	//Vector<String> words = new Vector<String>();

	/*
	 * 每次读取一句话，放置在sentence里面，并提取标注序列放到sentenceOfTags里面
	 */

	boolean readInstance(BufferedReader reader) throws IOException {
		sentence.clear();
		postag.clear();
		sentenceOfTags.clear();
		String strLine = "";
		while ((strLine = reader.readLine()) != null) {
			if (strLine.equals("")) {
				continue;
			} else {
				//raw_sen = myLib.ToSBC(strLine.replaceAll(" ", ""));
				String words_pos_tag[] = strLine.split(" ");
				for (int i = 0; i < words_pos_tag.length; i++) {
					String temp[] = words_pos_tag[i].split("/");
					//System.out.println(temp[0]);
					sentence.addElement(temp[0]);
					postag.addElement(temp[1]);
					sentenceOfTags.addElement(temp[2]);					
				}
				//ChartypeAndDicCharactor();
				return true;
			}
		}
		return false;
	}

	/*
	 * 特征提取函数。每次提取一句话的特征用features保存
	 */
	void initSentenceFeatures() {
		features.clear();
		int sentLength = sentence.size();
		// features.setSize(sentLength);
		for (int i = 0; i < sentLength; i++) {
			Vector<String> temp = new Vector<String>();
			features.addElement(temp);
		}
		for (int i = 0; i < sentLength; i++) {
			features.elementAt(i).clear();
			String prevChar = i - 1 >= 0 ? sentence.elementAt(i - 1)
					: PublicVarible.startChar;
			String prev2Char = i - 2 >= 0 ? sentence.elementAt(i - 2)
					: PublicVarible.startChar;
			String nextChar = i + 1 < sentence.size() ? sentence
					.elementAt(i + 1) : PublicVarible.endChar;
			String next2Char = i + 2 < sentence.size() ? sentence
					.elementAt(i + 2) : PublicVarible.endChar;
			String curChar = sentence.elementAt(i);
			String feat = "F0=" + prev2Char;
			features.elementAt(i).addElement(feat);
			feat = "F1=" + prevChar;
			features.elementAt(i).addElement(feat);
			feat = "F2=" + curChar;
			features.elementAt(i).addElement(feat);
			feat = "F3=" + nextChar;
			features.elementAt(i).addElement(feat);
			feat = "F4=" + next2Char;
			features.elementAt(i).addElement(feat);
			feat = "F5=" + prev2Char + PublicVarible.seperateCH + prevChar;
			features.elementAt(i).addElement(feat);
			feat = "F6=" + prevChar + PublicVarible.seperateCH + curChar;
			features.elementAt(i).addElement(feat);
			feat = "F7=" + curChar + PublicVarible.seperateCH + nextChar;
			features.elementAt(i).addElement(feat);
			feat = "F8=" + nextChar + PublicVarible.seperateCH + next2Char;
			features.elementAt(i).addElement(feat);
			feat = "F9=" + prev2Char + PublicVarible.seperateCH + prevChar
					+ PublicVarible.seperateCH + curChar;
			features.elementAt(i).addElement(feat);
			feat = "F10=" + prevChar + PublicVarible.seperateCH + curChar
					+ PublicVarible.seperateCH + nextChar;
			features.elementAt(i).addElement(feat);
			feat = "F11=" + curChar + PublicVarible.seperateCH + nextChar
					+ PublicVarible.seperateCH + next2Char;
			features.elementAt(i).addElement(feat);
			feat = "F12=" + prev2Char + PublicVarible.seperateCH + curChar;
			features.elementAt(i).addElement(feat);
			feat = "F13=" + prevChar + PublicVarible.seperateCH + nextChar;
			features.elementAt(i).addElement(feat);
			feat = "F14=" + curChar + PublicVarible.seperateCH + next2Char;
			features.elementAt(i).addElement(feat);

			String preLabel = i > 0 ? sentenceOfTags.elementAt(i - 1) : "_BL_";
			features.elementAt(i).addElement("BiLabels=" + preLabel);
	
		}
	}

	
}
