

public class inClass12 {
    public static int fib(int index) {

        if (index == 0) {
            return 0;
        } else if (index == 1) {
            return 1;
        } else {
            return fib(index-1) + fib(index-2);
        }
    }

    public static boolean isPalindrome(String s){
        if(s.length() == 1){
            return true;
        } else if(s.charAt(0) != s.charAt(s.length()-1))
            return false;
        else {
            return isPalindrome(s.substring(1, s.length()-1));
        }
    }
    public static boolean isPalindrome1(String s, int low, int high) {
        if(high <= low)
            return true;
        else if(s.charAt(high) != s.charAt(low)) 
            return false;
        else
            return isPalindrome1(s, low+1, high-1);
    }

    public static void main(String[] args) {
        String s = "radar";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome1(s, 0, s.length()-1));
    }
    
}
