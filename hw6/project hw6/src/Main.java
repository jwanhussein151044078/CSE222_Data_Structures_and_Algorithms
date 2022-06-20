import java.io.*;

public class Main {
    public static void main( String[] args ) {

        NLP n = new NLP();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] words = line.split(" ");
                if(words[0].compareTo("bigram") == 0){
                    System.out.println(n.bigrams(words[1])+"\n");
                }
                else if (words[0].compareTo("tfidf") == 0){
                    System.out.println(n.tfIDF(words[1],words[2])+"\n");
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
