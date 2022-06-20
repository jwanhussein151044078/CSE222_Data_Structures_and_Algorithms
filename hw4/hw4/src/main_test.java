
public class main_test {
    public static void main (String[] args){
        int[][] a = {
                { 1, 2, 3, 4},
                {10,20,30,40},
                {11,21,31,41},
                {61,62,63,64},
                {55,88, 0, 8},
        };
        System.out.printf("\nthe clockwise iterator ::  ");
        myit e = new myit(a);
        while (e.hasnext()){
            System.out.printf("%d ",e.next());
            if(e.hasnext()){
                System.out.printf("-> ");
            }
        }
    }
}
