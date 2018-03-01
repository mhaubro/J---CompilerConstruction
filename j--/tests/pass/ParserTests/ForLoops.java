package pass.ParserTests;

public class ForLoops {
    public static void main(String[] args) {

        String sourceChar = "ABCDEFG";
        int[] numbers = {1,2,3,4,5,6,7,8};

        for(int i = 0; i < 6; i++) {
            System.out.print(sourceChar.charAt(i));
        }
        System.out.println();

        for(int item: numbers) {
            System.out.print(item);
        }
    }
}
