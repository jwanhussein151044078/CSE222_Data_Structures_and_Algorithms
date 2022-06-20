

public class Experiment_test {
    public static void main(String[] args) {
        Node node = new Node("c\n", 1, "7:30:00", false, 2);
        Node node1 = new Node("c++\n", 7, "8:30:00", true, 7);
        Node node2 = new Node("java\n", 2, "2:30:00", true, 9);
        Node node3 = new Node("python\n", 2, "12:30:00", false, 3);
        Node node4 = new Node("math\n", 2, "2:00:00", true, 2);
        Node node5 = new Node("physics\n", 2, "8:10:00", true, 1);
        Node node6 = new Node("logic\n", 7, "9:14:00", false, 4);
        Node node7 = new Node("programming\n", 4, "6:30:00", true, 2);
        Node node8 = new Node("data structur\n",1,"4:45:00",false,1);
        Node node9 = new Node("operating system\n",3,"5:55:00",false,1);
        ExperimentList x = new ExperimentList();
        ExperimentList x2 ;
        Iterator it;
        x.addExp(node);
        x.addExp(node1);
        x.addExp(node2);
        x.addExp(node3);
        x.addExp(node4);
        x.addExp(node5);
        x.addExp(node6);
        x.addExp(node7);
        x.addExp(node8);
        x.addExp(node9);
        it = x.iterator();
        Node temp = x.get_head() ;
        try {
            System.out.println("<<<<<<<<<<<<  printing the linked list after adding the exp according to exp pointers >>>>>>>>>>>\n");
            while (it.has_next_exp()) {
                System.out.println(it.next_exp());
            }

            System.out.println("__________________________________________________________________________\n");
            it = x.iterator();
            System.out.println("<<<<<<<<<<<<  printing the linked list after adding the exp according to day pointers >>>>>>>>>>>\n");
            while (it.has_next_day()) {
                System.out.println(it.next_day());
            }

            System.out.println("__________________________________________________________________________\n");
            System.out.println("<<<<<<<<<<<<  testing the getExp method >>>>>>>>>>>\n");
            System.out.println(x.getExp(2,3));
            System.out.println("__________________________________________________________________________\n");
            System.out.println("<<<<<<<<<<<<  testing the setExp method >>>>>>>>>>>\n");
            x.setExp(7,1,new Node("spor\n",7,"11:20:00",true,11));
            it = x.iterator();
            while (it.has_next_exp()) {
                System.out.println(it.next_exp());
            }
            System.out.println("__________________________________________________________________________\n");
            System.out.println("<<<<<<<<<<<<  testing the removeexp and removeday method >>>>>>>>>>>\n");
            x.removeExp(2,2);
            x.removeDay(3);
            it = x.iterator();
            while (it.has_next_exp()) {
                System.out.println(it.next_exp());
            }
            System.out.println("__________________________________________________________________________\n");
            System.out.println("<<<<<<<<<<<<  testing the listexp method >>>>>>>>>>>\n");
            x2 = x.listExp(2);
            it = x2.iterator();
            while (it.has_next_exp()) {
                System.out.println(it.next_exp());
            }
            System.out.println("__________________________________________________________________________\n");
            System.out.println("<<<<<<<<<<<<  testing the orderDay method >>>>>>>>>>>\n");
            x.orderDay(2);
            it = x.iterator();
            while (it.has_next_exp()) {
                System.out.println(it.next_exp());
            }
            System.out.println("__________________________________________________________________________\n");
            System.out.println("<<<<<<<<<<<<  testing the orderExperiments method >>>>>>>>>>>\n");
            x2 = x .orderExperiments() ;
            it = x2.iterator();
            while (it.has_next_exp()) {
                System.out.println(it.next_exp());
            }
            System.out.println("__________________________________________________________________________\n");
        }catch (IllegalAccessException e){
            System.out.println(e);
        }
    }
}
