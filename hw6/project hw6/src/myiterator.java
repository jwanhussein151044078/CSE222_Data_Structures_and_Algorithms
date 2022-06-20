import java.util.Iterator;

public class myiterator implements Iterator {

    private Word_Map.Node node ;

    public myiterator (Word_Map.Node n){
        node = n ;
    }


    @Override
    public boolean hasNext() {
        return node != null ;
    }

    @Override
    public Word_Map.Node next() {
        Word_Map.Node temp = node ;
        node = node.getNext();
        return temp ;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
