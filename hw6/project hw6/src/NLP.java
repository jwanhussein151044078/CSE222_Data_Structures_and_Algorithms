import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;


public class NLP
{
    public Word_Map wmap;
    private int num_of_doc ;
    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public NLP(){
        wmap = new Word_Map();
        readDataset("dataset");
    }

    public void readDataset(String dir) {
        File folder = new File(dir);
        String[] files = folder.list();
        num_of_doc = files.length ;
        for (String file : files){
            try {
                String text = new String(Files.readAllBytes(Paths.get(dir+"/"+file)));
                fillmap(file,text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word){
        ArrayList<String> S = new ArrayList<>();
        if(wmap.containsKey(word)){

            final LinkedHashSet<String> fileset = (LinkedHashSet<String>) ((File_Map)wmap.get(word)).keySet() ;

            myiterator it = (myiterator)wmap.iterator();
            while (it.hasNext()){
                Word_Map.Node n = it.next();
                File_Map fm = n.getFmap();
                LinkedHashSet<String> intersection = new LinkedHashSet<>(fileset);
                intersection.retainAll(fm.keySet());
                Iterator it_inter = intersection.iterator();
                while (it_inter.hasNext()){
                    String filename = (String) it_inter.next();
                    ArrayList<Integer> arrayoftheword = (ArrayList<Integer>)((File_Map)wmap.get(word)).get(filename);
                    for (int i = 0 ; i < arrayoftheword.size() ; i++ ){
                        if (((ArrayList)fm.get(filename)).contains((arrayoftheword.get(i))+1) ){
                            if(!S.contains(word+" "+n.getKey())) {
                                S.add(word + " " + n.getKey());
                            }
                        }
                    }
                }
            }
        }
        else {
            System.out.println("the word map dose not contain "+word);
        }
        return S;
    }


    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName) {
        //System.out.println(TF(word ,fileName));
        return TF(word , fileName )*IDF(word);
    }

    /*Print the WordMap by using its iterator*/
    public  void printWordMap(){
        System.out.println(wmap.keySet());
    }


    private void fillmap(String filename , String text ){
        text =  text.replace("\n"," ");
        text =  text.replace("\t"," ");
        text =  text.replace("  "," ");
        String[] str = text.split(" ");
        int j = 0 ;
        for (int i = 0 ; i < str.length;i++){
            str[i] = str[i].trim().replaceAll("\\p{Punct}", "");
            if(str[i].compareTo("") != 0 ) {
                ArrayList<Integer> l = new ArrayList<>(1);
                l.add(j++);
                File_Map fm = new File_Map();
                fm.put(filename, l);
                wmap.put(str[i], fm);
            }
        }
    }

    private float TF (String word , String filenme){
        float tf = 0f ;
        int num_word_occurence = 0  ;
        if(((File_Map)wmap.get(word)) != null ){
            num_word_occurence = ((ArrayList<Integer>)((File_Map)wmap.get(word)).get(filenme)).size();
        }
        int file_size = 0 ;
        myiterator it = (myiterator)wmap.iterator();
        while (it.hasNext()){
            Word_Map.Node n = it.next();
            if(n.getFmap().containsKey(filenme)) {
                file_size += ((ArrayList) n.getFmap().get(filenme)).size();
            }
        }
        tf = (float) num_word_occurence / (float) file_size;
        return  tf ;
    }

    private float IDF(String word ){
        int num_file_with_word  = -1 ;
        if (((File_Map)wmap.get(word)) != null){
            num_file_with_word = ((File_Map)wmap.get(word)).size();
        }
        float idf =(float) Math.sqrt((float)num_of_doc/(float) num_file_with_word);
        return idf ;
    }




}
