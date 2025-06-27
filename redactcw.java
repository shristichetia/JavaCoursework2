public class redactcw {
  public static void main(String[] args) {
    redactcw redactcwi = new redactcw();
    String arg1 = "The quick brown fox jumps over the lazy dog!";
    String[] arg2 = { "Fox", "jumps", "dog" };
    String resString = redactcwi.redact(arg1, arg2);

  }

  public static String redact(String content, String[] redactWords) {
    String retVal = "";

    String[] strArrContent = content.split(" ");

    for (int i = 0; i < strArrContent.length; i++) {
      String contentSubString = strArrContent[i] + "";
      String redactedString = "";
      for (int j = 0; j < redactWords.length; j++) {
        String redactSubString = redactWords[j] + "";
        //redactedString = "";
        if (contentSubString.trim().toLowerCase().equals(redactSubString.trim().toLowerCase())) {
          int strLen = contentSubString.length();
          while (strLen > 0) {
            strLen--;
            redactedString = redactedString + "*";
            //System.out.println(" exiting strLen : " + strLen + " redactedString :: " + redactedString);
          }
        } //else {
          //redactedString = contentSubString.trim();
        //}
      }
      //System.out.println(" exiting redactedString in i loop :: " + redactedString);
      if (retVal.equals("")) {
        if(!redactedString.equals("")){
          retVal = redactedString;
        }else{
          retVal = contentSubString;
        }
      } else {
        if(!redactedString.equals("")){
          retVal = retVal + " " + redactedString;
        }else{
          retVal = retVal + " " + contentSubString;
        }
      }
    }

    System.out.println(" retVal : " + retVal);
    return retVal;
  }
}
