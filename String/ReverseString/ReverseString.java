/*
    File to implement reversing a string solution
*/
public class ReverseString {

    private static String StringReverse(String s) {
        // "fire" -> i < 2; "water" -> i < 2 --> i < s.length / 2;
        int lastIndex = s.length()-1;
        StringBuilder result = new StringBuilder(s);
        for(int i=0;i<s.length()/2;i++) {
            result.setCharAt(lastIndex-i, s.charAt(i));
            result.setCharAt(i, s.charAt(lastIndex-i));
        }
        return result.toString();
     }

    public static void main(String[] args) {
        String[] strings = {"fire", "water", "hogwarts", "a"};
        String currString, resultString;
        for(int i=0;i<strings.length;i++) {
            currString = strings[i];
            System.out.println("Input to StringReverse method: " + currString);
            resultString = StringReverse(currString);
            System.out.println("Result from StringReverse method: " + resultString + "\n");
        }
    }
}