/**
 * ExperimentList class
 */
public class ExperimentList {
    /**
     * no param constructor
     */
    public ExperimentList(){
        head = null;
    }

    /**
     * five param constructor
     * @param exp name of the exp
     * @param d day of the exp
     * @param t time of the exp
     * @param c indicate if the exp if completed
     * @param a accuracy of the exp
     */
    public ExperimentList(String exp , int d , String t , boolean c , float a){
        head = new Node(exp,d,t,c,a);
    }

    /**
     * @param node node to be the head of the list
     */
    public ExperimentList(Node node){
        head = node ;
    }

    /**
     * @return the head of the list
     */
    public Node get_head(){
        return  head ;
    }

    /**
     * @return iterator over the list
     */
    public Iterator iterator(){
        it = new Iterator(this);
        return it ;
    }

    /**
     * insert experiment to the end of the day
     * @param n the exp to be added
     */
    public void addExp(Node n){
        Node node = new Node(n);
        Node temp = get_head();
        Node temp2 = temp , temp3 = temp ;
        boolean found = false ;
        if(head != null){
           while(temp != null){
               if (temp.get_day() == node.get_day()){
                   found = true ;
                   temp2 = temp;
                   while (temp2.getNext_experiment() != null && temp2.getNext_experiment().get_day() == node.get_day()){
                       temp2 = temp2.getNext_experiment();
                   }
                   temp2.set_next_exp(node);
                   temp2 = temp2.getNext_experiment();
                   temp2.set_next_exp(temp.getNext_day());
               }
               temp3 = temp ;
               temp = temp.getNext_day();
           }
           if(!found) {
               temp3.set_next_day(node);
               while (temp3.getNext_experiment() != null) {
                   temp3 = temp3.getNext_experiment();
               }
               temp3.set_next_exp(node);
           }
        }
        else{
            head = node ;
        }
    }

    /**
     * get the experiment with the given day and position
     * @param day the day of the exp
     * @param index the index of the exp
     * @return  the exp in given day and index
     */
    public Node getExp(int day,int index){
        Node temp = head ;
        int i = 0 ;
        if(day < 1 || index <0 ){return null;}
        while(temp != null){
            if (temp.get_day() == day){
                while(temp != null && temp.get_day() == day){
                    if (i == index){
                        return temp;
                    }
                    temp = temp.getNext_experiment();
                    i++;
                }
                System.out.println("out of range !!!!!!");
                return null;
            }
            temp = temp.getNext_day();
        }
        System.out.println("out of range !!!!!!!!");
        return  null ;
    }

    /**
     * set the experiment with the given day and position
     * @param day the day of the exp
     * @param index the index of the exp
     * @param node new exp to be replaced with but the day unchangeable
     */
    public void setExp(int day,int index,Node node ){
        Node temp = getExp(day,index);
        temp.setSetup(node.get_setup());
        temp.setTime(node.get_time());
        temp.setCompleted(node.get_compleated());
        temp.setAccuracy(node.get_accuracy());
    }

    /**
     * remove the experiment specified as index from given day
     * @param day the day of exp
     * @param index the index of exp
     */
    public void removeExp(int day,int  index  ){
        Node temp = get_head();
        Node temp2 = temp ;
        Node temp3 = temp ;
        int i =0 ;
        if(day < 1 || index < 0) {
            return;
        }
        if(day == temp.get_day() && index == 0){
            temp = head;
            head = head.getNext_experiment();
            if(temp.getNext_experiment() == temp.getNext_day())
                head.set_next_day(temp.getNext_day().getNext_day());
            else
                head.set_next_day(temp.getNext_day());
            return;
        }
        while (temp != null ){
            if( temp.get_day() == day ){
                if(index == i ){
                    temp2.set_next_exp(temp.getNext_experiment());
                    if(i == 0){
                        temp3.set_next_day(temp2.getNext_experiment());
                        if(temp.getNext_experiment() != null && temp.getNext_experiment().get_day() == day)
                            temp3.getNext_day().set_next_day(temp.getNext_day());

                    }
                    return;
                }
                i++;
            }
            if(temp.get_day() != temp3.get_day()){
                temp3 = temp ;
            }

            temp2 = temp ;
            temp = temp.getNext_experiment();
        }
    }

    /**
     * remove all experiments in a given day
     * @param day the day to be removed
     */
    public void removeDay(int day){
        Node temp = get_head();
        Node temp2 = temp , temp4 = temp ;
        if (day == temp.get_day()){
            head = head.getNext_day();
            return;
        }
        while (temp != null && day != temp.get_day() ){
            temp2 = temp ;
            temp = temp.getNext_day();
        }
        if(temp == null){
            return;
        }
        temp4 = temp2 ;
        while (temp4.getNext_experiment() != null && temp4.getNext_experiment().get_day() == temp2.get_day()){
            temp4 = temp4.getNext_experiment();
        }
        temp2.set_next_day(temp.getNext_day());
        temp4.set_next_exp(temp.getNext_day());
    }

    /**
     * list all completed experiments in a given day
     * @param day the day to be listed
     * @return new ExperimentList with the given day and compleated exp
     */
    public ExperimentList listExp(int day){
        ExperimentList newExplist = new ExperimentList();
        Node temp = get_head() ;
        while(temp != null){
            if (temp.get_day() == day){
                while(temp != null && temp.get_day() == day){
                    if(temp.get_compleated()){
                        newExplist.addExp(temp);
                    }
                    temp = temp.getNext_experiment();
                }
                return newExplist;
            }
            temp = temp.getNext_day();
        }
        return newExplist;
    }

    /**
     * sorts the experiments in a given day according to the accuracy, the changes will be done on the list
     * @param day the day to be ordered
     */
    public void orderDay(int day){
        Node temp = get_head();
        Node temp2 = temp ;
        boolean first_exp =  true;
        while (temp.getNext_day() != null && day != temp.get_day() ){
            temp = temp.getNext_day();
        }
        while(temp.getNext_experiment() != null && temp.get_day() == day){
            temp2 = temp.getNext_experiment();
            while(temp2 != null && temp2.get_day() == day ){
                if (temp.get_accuracy() > temp2.get_accuracy()){
                    swap(temp,temp2);
                }
                temp2 = temp2.getNext_experiment();
            }

            temp = temp.getNext_experiment();
        }
    }

    /**
     * sorts all the experiments in the list according to the accuracy
     * @return new ExperimentList with sorted exp
     */
    public ExperimentList orderExperiments(){
        ExperimentList newlist = new ExperimentList();
        Node temp = get_head() , temp2 ;
        Node newtemp ;

        while(temp != null){
            Node node = new Node(temp);
            newtemp = newlist.get_head();
            if(newtemp == null){

                newlist.head = node ;
                newlist.head.set_next_exp(null);
                newlist.head.set_next_day(null);
            }
            else if (temp.get_accuracy() <= newtemp.get_accuracy()){
                node.set_next_exp(newlist.head);
                newlist.head = node ;
            }
            else {
                while (newtemp.getNext_experiment() != null && newtemp.getNext_experiment().get_accuracy() < temp.get_accuracy()) {
                    newtemp = newtemp.getNext_experiment();
                }
                temp2 = newtemp.getNext_experiment();
                newtemp.set_next_exp(node);
                newtemp.getNext_experiment().set_next_exp(temp2);
            }
            temp = temp.getNext_experiment();
        }
        return  newlist;
    }

    private void swap(Node n1 , Node n2){
        Node temp = new Node();
        temp.setSetup(n1.get_setup());
        temp.setAccuracy(n1.get_accuracy());
        temp.setCompleted(n1.get_compleated());
        temp.setTime(n1.get_time());
        n1.setSetup(n2.get_setup());
        n1.setAccuracy(n2.get_accuracy());
        n1.setCompleted(n2.get_compleated());
        n1.setTime(n2.get_time());
        n2.setSetup(temp.get_setup());
        n2.setAccuracy(temp.get_accuracy());
        n2.setCompleted(temp.get_compleated());
        n2.setTime(temp.get_time());
    }
    private Node head;
    private Iterator it ;
}
