public class RunLengthEncoding {

    public static void runLengthEncoding(String str) {
        int stringLength = str.length();

        for(int i = 0; i < stringLength; i++) {
            int count = 1;
            while((i < stringLength-1) && (str.charAt(i) == str.charAt(i+1))) {
                count++;
                i++;
            }
            if(count > 1) {
                System.out.print("" + str.charAt(i) + count);
            } else {
                System.out.print("" + str.charAt(i));
            }
        }
    }


    public static void main(String[] args)
    {
        if(args.length > 0) {
            runLengthEncoding(args[0]);
        } else {
            System.out.println("No args given");
        }
    }
}
