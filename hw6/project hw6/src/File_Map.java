
import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
   ArrayList<String> fnames;
   ArrayList<List<Integer>> occurances;

    public File_Map(){
        fnames = new ArrayList<String>();
        occurances = new ArrayList<List<Integer>>();
    }
    @Override
    public int size() {
            return fnames.size() ;
    }

    @Override
    public boolean isEmpty() {
        return fnames.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return ( fnames.indexOf(key) != -1 ) ;
    }

    @Override
    public boolean containsValue(Object value) {
        return ( occurances.indexOf(value) != -1 );
    }

    @Override
    public Object get(Object key) {
        if(containsKey(key)){
            return occurances.get(fnames.indexOf(key)) ;
        }
        return null ;
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        value = new ArrayList<Integer>((ArrayList)value);
        int index_of_key = fnames.indexOf(key) ;
        if( index_of_key != -1 ){
            List<Integer> temp = (List<Integer>) occurances.get(index_of_key);
            occurances.get(index_of_key).addAll((List)value);
            return temp ;
        }
        else {
            occurances.add((List<Integer>) value);
            fnames.add((String) key);
            return null ;
        }
    }

    @Override
    public Object remove(Object key) {
        if(occurances.remove(get(key))){
            return fnames.remove(key);
        }
        return false ;
    }

    @Override
    public void putAll(Map m) {
        m = (File_Map) m ;
        for (int i =0 ; i < m.size() ; i++){
            this.put(((File_Map) m).fnames.get(i) , ((File_Map) m).occurances.get(i));
        }
    }

    @Override
    public void clear() {
        fnames.clear();
        occurances.clear();
    }

    @Override
    public Set keySet() {
        LinkedHashSet<String> S = new LinkedHashSet<String>() ;
        for(int i = 0 ; i < fnames.size() ; i++ ){
            S.add(fnames.get(i));
        }
        return S ;
    }

    @Override
    public Collection values() {
        LinkedHashSet<LinkedHashSet<Integer>> S = new LinkedHashSet<>();
        LinkedHashSet<Integer> temp ;
        for(int i = 0 ; i < occurances.size() ; i++){
            temp = new LinkedHashSet<>(occurances.get(i));
            S.add(temp);
        }
        return S;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    @Override
    public String toString(){
        String buff = new String();
        for(int i = 0 ; i < fnames.size() ; i ++ ){
            buff += String.format("\t\t\t%s    %s\n" , fnames.get(i) , occurances.get(i)) ;

        }
        return buff ;
    }
}
