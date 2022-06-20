import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * class MyFileReder
 */
public class MyFileReder {
    /**
     * read the first file of the hw
     * @param file_name the name of the file
     * @return  2d array contain the binary image
     */
    public static  int[][] read_file1(String file_name ){
        MYStack<String > stack = new MYStack<String>();
        int[][] array ;
        int col =-1, row=0 ;
        String line = new String();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(file_name);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                stack.push(line);
                row++;
            }
        }catch(IOException e){
            System.out.println(e);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        col = stack.peek().length()/2+1;
        row--;
        array = new int[row+1][col];
        int w = 0 ;
        for(int i = row ; i >= 0 ; i--){
            for(int j = 0 ; j < col*2-1 ; j++){
                if(stack.peek().charAt(j) ==  '1' ) {
                    array[i][w++] = 1;
                }
                else if(stack.peek().charAt(j) ==  '0' ) {
                    array[i][w++] = 0;
                }

            }
            w = 0 ;
            stack.pop();
        }
        return array ;
    }

    /**
     * read the second file of the hw
     * @param file_name the name of the file
     * @return a string array contain the exp and val
     */
    public static String[]  read_file2(String file_name){
        int size = 1 , i = 0 ;
        BufferedReader br = null;
        FileReader fr = null;
        String temp ;
        String[] line = new String[size];
        try {
            fr = new FileReader(file_name);
            br = new BufferedReader(fr);

            while ((temp = br.readLine()) != null) {
                if(temp.compareTo("") != 0 ){
                    if(i < size){
                        line[i++] = temp ;
                    }
                    else{
                        String[] buf = line ;
                        line = new String[size+1];
                        for (int j = 0 ; j < size ; j++){
                            line[j] = buf[j];
                        }
                        size += 1;
                        line[i++] = temp;
                    }
                }

            }
        }catch(IOException e){
            System.out.println(e);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return line;
    }
}
