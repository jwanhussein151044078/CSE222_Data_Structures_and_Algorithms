import java.util.EmptyStackException;

@SuppressWarnings("unchecked")
/**
 * class MYStack
 */
public class MYStack<E> {
    /**
     * no param constructor
     */
    public MYStack(){
        size = 0 ;
        cap = 25 ;
        sp = (E[])new Object[cap];
    }

    /**
     * push the element into the stack
     * @param element the element
     * @return  return element
     */

    public E push(E element){
        if(size >= cap-1){
            resize();
        }
        sp[size++] = element ;
        return element ;
    }

    /**
     * pop the top element of the stack if the stack is not empty
     * @return the element
     * @throws EmptyStackException empty stack exception
     */
    public E pop() throws EmptyStackException {

        if(!isempty()) {
            E temp = sp[size - 1];
            size--;
            return temp;
        }
        else{
            throw new EmptyStackException();
        }
    }

    /**
     * peek at the top element of the stack but not remove it
     * @return the element on top of the stack
     * @throws EmptyStackException empty stack exception
     */
    public E peek() throws EmptyStackException {
        if(!isempty()) {
            return sp[size - 1];
        }
        else{
            throw new EmptyStackException();
        }
    }

    /**
     * check whether the stack is empty
     * @return true if the stack is empty or false if not
     */
    public boolean isempty(){
        if (size == 0 ){
            return true ;
        }
        return  false ;
    }

    /**
     * tostring method
     * @return a string
     */
    public String toString(){
        String temp = "[ ";
        for (int i = 0 ; i < size ; i++){
            temp = temp + sp[i] + " ," ;
        }
        temp = temp + " ]";
        return temp ;
    }
    private void resize(){
        E[] temp = sp ;
        cap *= 2 ;
        sp = (E[])new Object[cap];
        for (int i =0 ; i < size ; i++ ){
            sp[i] = temp [i];
        }
    }
    private E[] sp ;
    private int size ;
    private int cap ;
}
