import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            Scanner input_file  = new Scanner(new File("input.txt")) ;
            ADTGRAPH grph = new ADTGRAPH(input_file);
            while (grph.load_edge(input_file)){}
            grph.set_transitive_relation();
            System.out.println(grph.popularNum());
        }catch (FileNotFoundException e ){
            System.out.println(e);
        }

    }
}
