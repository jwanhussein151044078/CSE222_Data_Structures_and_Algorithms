/**
 * class main_test
 */
public class main_test {

    public static void main(String[] args){
        System.out.println("<<<<<<<<<<<<<< part1 >>>>>>>>>>>>>>>>>>");
        int[][] array =  MyFileReder.read_file1("src/test_file_part1.txt");
        System.out.printf("\nthe number of components is ( %d )\n\n\n",componentConter.Counting_components(array));
        String[] infix = MyFileReder.read_file2("src/test_file_part2.txt") ;
        InfixToPostfix a = new InfixToPostfix(infix);
        try {
            System.out.println("<<<<<<<<<<<<<<<<< part2 >>>>>>>>>>>>>>>>>>\n");
            System.out.println("the postfix expretion-->    "+ a.convert());
            System.out.println("the value of the expretion -->   "+ a.eval());
        }catch (InfixToPostfix.SyntaxErrorException e){
            System.out.println(e);
        }
     //   System.out.printf("%f\n" ,math.pow(3,4));
     //   System.out.printf("%d\n" ,math.factorial(7));
     //   System.out.printf("%f\n" ,math.sin(60f));
     //   System.out.printf("%f\n" ,math.cos(30f));
    }




}
