import java.util.Scanner;

public class ADTGRAPH {
    private int[][] Adjacency_Matrix ;
    private int[] Vertices;
    private int numV ;
    private int numE ;
    private int usedV ;

    public ADTGRAPH(Scanner input){
        setNumV(input.nextInt());
        setNumE(input.nextInt());
        Adjacency_Matrix = new int[numV][numV];
        Vertices = new int[numV];
        usedV = 0 ;
    }

    public int getNumV() {
        return numV;
    }
    public int getNumE() {
        return numE;
    }
    public int[] getVertices(){
        return Vertices ;
    }
    private void setNumV(int num){
        numV = num ;
    }
    private void setNumE(int num){
        numE = num ;
    }
    public boolean load_edge(Scanner scan){
        int V1 = -1 , V2 = -1 ;
        boolean flag = false ;
        if(scan.hasNextInt()){
            V1 = scan.nextInt();
            if(!is_exist(V1)) {
                Vertices[usedV++] = V1;
            }
            flag = true ;
        }
        if(scan.hasNextInt()){
            V2 = scan.nextInt();
            if(!is_exist(V2)) {
                Vertices[usedV++] = V2;
            }
        }
        if(flag){
            Adjacency_Matrix[indexof(V1)][indexof(V2)] = 1 ;
        }
        return flag ;

    }
    public void set_transitive_relation(){
        for(int i = 0 ; i < usedV*2 ; i++){
            for(int j = 0 ; j < usedV*2 ; j++){
                if(Adjacency_Matrix[i%usedV][j%usedV] == 1 && i%usedV != j%usedV){
                    for (int k = 0 ; k < usedV ; k++ ){
                        if(k != i%usedV && k != j%usedV && Adjacency_Matrix[j%usedV][k] == 1 ){
                            Adjacency_Matrix[i%usedV][k] = Adjacency_Matrix[j%usedV][k];
                        }
                    }
                }
            }
        }
    }
    public int popularNum(){
        int num = 0 ;
        int temp = 0 ;
        for(int i = 0 ; i < usedV ; i++){
            temp = 0 ;
            for (int j = 0 ; j < usedV ; j++){
                if(Adjacency_Matrix[j][i] ==1 && i != j){
                    temp++;
                }
            }
            if(temp >= usedV -1 ){
                num++;
            }
        }
        return num;
    }
    public boolean is_edge(int src , int dest){
        int indexsrc = indexof(src );
        int indexdest = indexof(dest);
        if(Adjacency_Matrix[indexsrc][indexdest] == 1){
            return true ;
        }
        return false ;
    }
    private boolean is_exist(int i){
        for(int j = 0 ; j < usedV ; j++){
            if(Vertices[j] == i ){
                return true ;
            }
        }
        return false ;
    }
    private int indexof(int i){
        for(int j = 0 ; j < usedV ; j++){
            if(Vertices[j] == i ){
                return j ;
            }
        }
        return -1 ;
    }
    public void p(){
        for(int i = 0 ; i<usedV ; i++){
            System.out.printf("\t%d",Vertices[i]);
        }
        System.out.println("\n");
        for (int i = 0 ; i < usedV ; i++){
            System.out.printf("%d\t",Vertices[i]);
            for (int j = 0 ; j < usedV ; j++){
                System.out.printf("%d\t",Adjacency_Matrix[i][j]);
            }
            System.out.println("\n");
        }
    }


}
