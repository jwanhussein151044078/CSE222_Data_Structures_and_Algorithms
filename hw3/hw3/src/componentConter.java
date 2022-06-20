/**
 * class componentConter
 */
public class componentConter {
    /**
     * count the object
     * @param array 2d array
     * @return the number of object
     */
    public static int Counting_components(int[][] array ){
        MYStack<Integer> stack = new MYStack<Integer>();
        for(int i =0 ; i < array.length ; i++){
            for(int j = 0 ; j < array[0].length ; j++){
                if(array[i][j] == 1){
                    if(i > 0 && j > 0) {
                        if (array[i][j - 1] == 0 && array[i - 1][j] == 0) {
                            if (stack.isempty()) {
                                array[i][j] = 2;
                                stack.push(2);
                            } else {
                                array[i][j] = stack.peek() + 1;
                                stack.push(array[i][j]);
                            }
                        }
                        else if (array[i][j - 1] != 0 && array[i - 1][j] == 0){
                            array[i][j] = array[i][j - 1] ;
                        }
                        else if (array[i][j - 1] == 0 && array[i - 1][j] != 0){
                            array[i][j] = array[i-1][j] ;
                        }
                        else if (array[i][j - 1] == array[i - 1][j] ){
                            array[i][j] = array[i-1][j] ;
                        }
                        else if (array[i][j - 1] != array[i - 1][j] ){
                            array[i][j] = array[i-1][j] ;
                            stack.pop();
                        }

                    }
                    else if (i == 0 && j > 0){
                        if (array[i][j - 1] == 0 ) {
                            if (stack.isempty()) {
                                array[i][j] = 2;
                                stack.push(2);
                            } else {
                                array[i][j] = stack.peek() + 1;
                                stack.push(array[i][j]);
                            }
                        }
                        else if (array[i][j - 1] != 0){
                            array[i][j] = array[i][j-1];
                        }
                    }
                    else if (i > 0 && j == 0){
                        if(array[i-1][j] == 0){
                            if (stack.isempty()) {
                                array[i][j] = 2;
                                stack.push(2);
                            } else {
                                array[i][j] = stack.peek() + 1;
                                stack.push(array[i][j]);
                            }
                        }
                        else if (array[i-1][j] != 0){
                            array[i][j] = array[i-1][j];
                        }
                    }
                    else if (i == 0 && j == 0){
                        array[i][j] = 2 ;
                        stack.push( 2);
                    }
                }
            }
        }
        return  stack.peek()-1;
    }
}
