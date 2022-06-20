import java.util.NoSuchElementException;

public class myit {
    private int[] data ;
    private int size ;
    private int cap ;
    private int it ;

    public myit(int[][] array){
        cap = array.length*array[0].length;
        data = new int[cap];
        size = 0 ;
        it = 0 ;
        int[][] temp = new int[array.length][array[0].length] ;
        for (int i = 0 ; i < array.length ; i++){
            for (int j =0 ; j < array[0].length ;j++){
                temp[i][j] = array[i][j];
            }
        }
        getit(temp,0,0,true,true,data);
    }

    public int next(){
        if(it < size){
            return data[it++];
        }
        else{
            throw new NoSuchElementException("no more element !!\n");
        }
    }

    public boolean hasnext(){
        if(it < size){
            return true ;
        }
        return false ;
    }

    public void remove(){
        throw new UnsupportedOperationException("the remove method is not supported !! \n");
    }

    private int getit(int[][] input , int x , int y , boolean left ,boolean up , int[] output ){
        if (size < cap ) {
           if (left && up ){
               int i ;
               for (i = y ; i < input[0].length && input[x][i] >= 0 ; i++){
                   output[size++] = input[x][i];
                   input[x][i] = -1 ;
               }
               return getit(input,x+1 , i-1 , true , false , output);
           }
           else if (left && !up){
               int i ;
               for (i = x ; i < input.length && input[i][y] >= 0 ; i++){
                   output[size++] = input[i][y];
                   input[i][y] = -1 ;
               }
               return getit(input,i-1 , y-1 , false , false , output);
           }
           else if (!left && !up){
               int i ;
               for (i = y ; i >= 0 && input[x][i] >= 0 ; i--){
                   output[size++] = input[x][i];
                   input[x][i] = -1 ;
               }
               return getit(input,x-1 , i+1 , false , true , output);
           }
           else if ( !left && up ){
               int i ;
               for (i = x ; i >= 0 && input[i][y] >= 0 ; i--){
                   output[size++] = input[i][y];
                   input[i][y] = -1 ;
               }
               return getit(input,i+1 , y+1 , true , true , output);
           }

        }
        return 0;
    }

}
