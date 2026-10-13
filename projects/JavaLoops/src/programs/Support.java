package programs;

public class Support {
	public static String reverseString(String s) {
		String answer = "";
		
		for (int i = s.length() - 1; i >= 0; i--) {
			answer += s.charAt(i);
		}
		
		return answer;
	}
}
