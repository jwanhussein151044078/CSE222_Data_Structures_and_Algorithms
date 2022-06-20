import java.util.*;

public class Word_Map implements Map, Iterable
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];
    private int size ;
    private Node lastnode ;
    private Node firstnode ;

    static class Node {
        private File_Map fmap ;
        private String key ;
        private Node next ;

        public Node(String word){
            key = word ;
            fmap = new File_Map() ;
        }

        public Node getNext() {
            return next;
        }

        public String getKey() {
            return key;
        }
        public File_Map getFmap(){
            return fmap;
        }
    }

    public Word_Map() {
        this.table = new Node[INITCAP];
        size = 0 ;
        lastnode = null ;
    }

    @Override
    public Iterator iterator() {
        return new myiterator(firstnode);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {
        int index = hashCode((String) key);
        if (table[index] == null ){
            return false ;
        }
        else if (table[index].key.compareTo((String) key) == 0 ){
            return true ;
        }
        else {
            myiterator it = new myiterator(table[index]) ;
            while (it.hasNext()){
                if(it.next().key.compareTo((String)key) == 0){
                    return true ;
                }
            }
            return false ;
        }
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        myiterator it = (myiterator) this.iterator();
        while (it.hasNext()){
            if(it.next().fmap == value){
                return true ;
            }
        }
        return false ;
    }

    @Override
    public Object get(Object key) {
        int index = hashCode((String) key);
        if (table[index] == null ){
            return null ;
        }
        else if (table[index].key.compareTo((String) key) == 0 ){
            return table[index].fmap ;
        }
        else {
            myiterator it = new myiterator(table[index]) ;
            while (it.hasNext()){
                Node t = it.next() ;
                if(t.key.compareTo((String)key) == 0){
                    return t.fmap ;
                }
            }
            return null ;
        }

    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {

        int index = hashCode((String) key);
        if(table[index] == null ){
            table[index] = new Node((String) key);
            table[index].fmap = (File_Map) value;
            if(lastnode != null ){
                lastnode.next = table[index];
                lastnode = table[index];
            }
            else{
                lastnode = table[index];
            }
            if(size == 0 ){
                firstnode = table[index];
            }
            size++;
        }
        else if (table[index].key.compareTo((String) key) == 0 ){
            table[index].fmap.putAll( (File_Map) value );
        }
        else {
            index = (index+1)%CURRCAP ;
            while (table[index] != null && table[index].key.compareTo((String) key) != 0 ){
                index = (index+1)%CURRCAP ;
            }
            if(table[index] == null) {
                table[index] = new Node((String) key);
                table[index].fmap.putAll( (File_Map)value );
                lastnode.next = table[index];
                lastnode = table[index];
                size++;
            }
            else {
                table[index].fmap.putAll((File_Map) value);
            }
        }
        if (((float)size/CURRCAP) > LOADFACT){

            rehash();
            return null ;
        }
        return null ;
    }

    @Override
    /*You do not need to implement remove function
    * */
    public Object remove(Object key) {
        if (containsKey(key)){
            Node n ;
            if(firstnode.key.compareTo((String)key) == 0){
                n = firstnode ;
                firstnode = firstnode.next;
            }
            else{
                Node temp = firstnode;
                while (temp.next != null && temp.next.key.compareTo((String) key) != 0) {
                    temp = temp.next;
                }
                n = temp.next;
                temp.next = temp.next.next;
            }
            size-- ;
            for(int i = 0 ; i < CURRCAP ; i++){
                if( table[i] != null && table[i].key.compareTo((String) key) == 0){
                    table[i] = null ;
                    break;
                }
            }
            return n ;
        }
        return null ;
    }

    @Override
    public void putAll(Map m) {
        myiterator it = (myiterator) ((Word_Map)m).iterator();
        while (it.hasNext()){
            Node n = it.next();
            put(n.key,n.fmap);
        }
    }

    @Override
    public void clear() {
        CURRCAP = INITCAP ;
        table = new Node[CURRCAP];
        size = 0 ;
        lastnode = null ;
        firstnode = null ;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        LinkedHashSet<String> S = new LinkedHashSet<String>() ;
        myiterator it = (myiterator) this.iterator();
        while (it.hasNext()){
            S.add(it.next().key);
        }
        return S ;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        LinkedHashSet<File_Map> S = new LinkedHashSet<File_Map>() ;
        myiterator it = (myiterator) this.iterator();
        while (it.hasNext()){
            S.add(it.next().fmap);
        }
        return S ;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }

    public int hashCode(String key){
        int hc = 0;
        Character ch ;
        for(int i = key.length()-1 ; i > 0 ; i--){
            ch = key.charAt(i);
            hc += ch.hashCode()*i ;
        }
        return hc%CURRCAP ;
    }

    @Override
    public String toString(){
        String buff = new String();
        for (int i = 0 ; i < CURRCAP ; i++){
            if(table[i] != null){
                buff +=  String.format("%s%s\n" , table[i].key , table[i].fmap) ;
            }
        }
        return buff ;
    }

    private void rehash(){
        Node[] temp = table ;
        myiterator it = new myiterator(firstnode) ;
        CURRCAP *= 2;
        table = new Node[CURRCAP];
        size = 0 ;
        lastnode = null ;
        //firstnode = null ;
        while (it.hasNext()){
            Node n = it.next();
            put(n.key,n.fmap);
        }
    }
}
