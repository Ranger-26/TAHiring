public class Main {

    public static void main(String[] args) {
        System.out.println(strDist("catcowcat", "cat"));
    }

    public static int strDist(String str, String sub) {
        //if str length is less than or equal to the sub,
        //return length if substring equals current string, 0 other wise
        if (str.length() <= sub.length()) {
            if (str.equals(sub))
                return str.length();
            return 0;
        }

        int subResult = str.indexOf(sub);
        System.out.println(str+","+subResult);
        if (subResult == -1)
            return 0;
        return subResult + (str.length() - 1) + strDist(str.substring(subResult+sub.length()), sub);
        //check for the index of the first substring and then do it again with the first string cut
    }
}
