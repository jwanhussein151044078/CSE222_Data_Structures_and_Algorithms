/**
 * Iterator class
 */

public class Iterator {
    /**
     * no param constructor
     */
    public Iterator(){
        it = null;
    }

    /**
     * one param constructor
     * @param other a node
     */
    public Iterator(ExperimentList other){
        it = other.get_head();
    }

    /**
     * @return true if the iteration has more days
     */
    public boolean has_next_day(){
        if (it != null){
            return true;
        }
        return false ;
    }

    /**
     * @return true if the iteration has more exp
     */
    public boolean has_next_exp(){
        if (it != null){
            return true;
        }
        return false ;
    }

    /**
     * @return next day in the iteration
     * @throws IllegalAccessException
     */
    public Node next_day() throws IllegalAccessException {
        if(has_next_day()){
            Node temp = it ;
            it = it.getNext_day();
            return temp;
        }
        else{
            throw new IllegalAccessException("no next element in itration\n");
        }
    }

    /**
     * @return next exp in the iteration
     * @throws IllegalAccessException
     */
    public Node next_exp() throws IllegalAccessException {
        if(has_next_exp()){
            Node temp = it ;
            it = it.getNext_experiment();
            return temp;
        }
        else{
            throw new IllegalAccessException("no next element in itration\n");
        }
    }

    private Node it ;
}
