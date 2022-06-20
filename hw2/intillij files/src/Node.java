/**
 * class Node
 */
public class Node {
    /**
     * no param constractor
     */
    public  Node(){
        this("unknown exp\n",1,"00:00:00" , false , 0);
        next_day = null ;
        next_experiment = null ;
    }

    /**
     * five param constructor
     * @param exp name of the exp
     * @param d day of the exp
     * @param t time of the exp
     * @param c indicate if the exp if completed
     * @param a accuracy of the exp
     */
    public Node(String exp , int d , String t , boolean c , float a){
        try{
            set(exp,d,t,c,a);
            next_day = null ;
            next_experiment = null ;
        }catch(IllegalArgumentException e){
            System.out.println(e);
        }
    }

    /**
     * copy constructor
     * @param node other exp to be copy
     */
    public Node(Node node){
        this(node.get_setup(),node.get_day(),node.get_time(),node.get_compleated(),node.get_accuracy());
    }

    /**
     * return the day of the exp
     * @return return the day of the exp
     */
    public int get_day(){
        return  day ;
    }

    /**
     * return the name of exp
     * @return return the name of exp
     */
    public String get_setup() {
        return setup;
    }

    /**
     * return the time of exp
     * @return return the time of exp
     */
    public String get_time(){
        return  time ;
    }

    /**
     * return the accuracy of the exp
     * @return the accuracy of the exp
     */
    public float get_accuracy(){
        return  accuracy ;
    }

    /**
     * return true if the exp is completed
     * @return true if exp is completed false if not
     */
    public boolean get_compleated(){
        return  completed ;
    }

    /**
     * return the next day or null
     * @return the next day or null ptr if next day was not found
     */
    public Node getNext_day() { return (next_day != null)?next_day:null; }

    /**
     * return the next exp or null
     * @return the next exp or null ptr if next exp was not found
     */
    public Node getNext_experiment() {
        return (next_experiment != null)?next_experiment:null;
    }

    /**
     * @param str the name of the exp
     */
    public void setSetup(String str ){setup = str ;}

    /**
     * @param t time of exp
     */
    public void setTime(String t ){time = t ;}

    /**
     * @param b boolean value
     */
    public void setCompleted(boolean b ){completed = b ;}

    /**
     * @param f accurancy of exp
     */
    public void setAccuracy(float f ){accuracy  = f ;}

    public String toString(){
        return  "day ( "+ day + " )" +
                "\texperiment name --> "+setup +
                "time of start ::  "+  time  +
                "\tis compleate -->  "  + completed +
                "\taccurancy =  " + accuracy +"\n";
    }

    /**
     * @param node next day
     */
    public void set_next_day(Node node){
        next_day = node ;
    }

    /**
     * @param node next exp
     */
    public void set_next_exp(Node node){
        next_experiment = node ;
    }

    private void set(String exp , int d , String t , boolean c , float a ){
        if(check_error(d,a) ){
            setup = exp ;
            day = d ;
            time  = t ;
            completed = c ;
            accuracy = a ;
        }
        else {
            throw new IllegalArgumentException("day and accurancy should be pozitive number \n\n");
        }
    }

    private boolean check_error(int d , float a  ){
        if( d>0 && a >= 0  ){
            return true ;
        }
        return false;
    }

    private String setup ;
    private int day ;
    private String time ;
    private boolean completed ;
    private float accuracy ;
    private Node next_day ;
    private Node next_experiment ;

}
