import java.util.Comparator;
import java.util.NoSuchElementException;

public class MAXPQ  {
    private Pvector[] data;
    private final int INIT_space = 100 ;
    private int used ;
    private Comparator<Pvector> comp ;

    public MAXPQ(Comparator c){
        data =  new Pvector[INIT_space];
        used = 0 ;
        comp = c ;
    }

    boolean offer(Pvector item){
        data[used] = item ;
        int child = used ;
        int parent = (child-1)/2 ;
        while (parent != child && comp.compare(data[parent],data[child]) < 0 ){
            swap(parent,child);
            child = parent ;
            parent = (child-1)/2 ;
        }
        used++;
        return true ;
    }
    public Pvector poll(){
        if(isEmpty()){
            return null ;
        }
        if (used == 1 ){
            used = 0 ;
            return data[0];
        }
        Pvector temp = data[0];
        int parent = 0 , L_child , R_child , maxchild ;
        data[0] = data[used-1];
        used--;

        while(true){
            L_child = ( parent*2 ) +1 ;
            R_child = L_child + 1  ;
            if(L_child >= used ){
                break;
            }
            maxchild = L_child ;
            if(R_child < used && comp.compare(data[R_child] , data[L_child]) > 0  ){
                maxchild = R_child ;
            }
            if (comp.compare(data[parent],data[maxchild]) < 0){
                swap(parent,maxchild);
                parent = maxchild ;
            }
            else {
                break;
            }

        }
        return temp ;
    }

    public Pvector remove(){
        Pvector temp = this.poll();
        if(temp == null){
            throw new NoSuchElementException();
        }
        else
            return temp ;
    }

    public Pvector peek(){
        if(isEmpty())
            return null ;
        return data[0];
    }

    public Pvector element(){
        if(isEmpty())
            throw new NoSuchElementException();
        return data[0];
    }

    public boolean isEmpty(){
        if(used == 0){
            return true ;
        }
        return false ;
    }

    private void swap(int i , int j){
        Pvector temp = data[i];
        data[i] = data[j];
        data[j] = temp ;
    }


    public static class LEX implements Comparator<Pvector> {
        public int compare(Pvector p , Pvector ch){
            if(p.getRed() > ch.getRed()){
                return 1 ;
            }
            else if (p.getRed() < ch.getRed()){
                return -1 ;
            }
            else if (p.getGreen() > ch.getGreen()){
                return 1 ;
            }
            else if (p.getGreen() < ch.getGreen()){
                return -1 ;
            }
            else if (p.getBlue() > ch.getBlue()){
                return 1 ;
            }
            else if (p.getBlue() < ch.getBlue()){
                return -1 ;
            }
            else
                return 0;

        }
    }

    public static class EUC implements Comparator<Pvector> {
        public int compare(Pvector p , Pvector ch){
            double temp_p , temp_ch ;
            temp_p = Math.sqrt(Math.pow(p.getRed(),2)+Math.pow(p.getGreen(),2)+Math.pow(p.getBlue(),2));
            temp_ch = Math.sqrt(Math.pow(ch.getRed(),2)+Math.pow(ch.getGreen(),2)+Math.pow(ch.getBlue(),2));
            if(temp_p > temp_ch)
                return 1 ;
            else if (temp_p < temp_ch)
                return -1 ;
            else
                return 0 ;
        }
    }

    public static class BMX implements Comparator<Pvector> {
        public int compare(Pvector p , Pvector ch){
            int parent = getmixedbit(p.getRed() , p.getGreen() , p.getBlue());
            int child = getmixedbit(ch.getRed() , ch.getGreen() , ch.getBlue());

            if(parent > child){
                return 1 ;
            }
            else if (parent < child){
                return -1 ;
            }
            return 0 ;
        }

        private int getmixedbit(short r , short g , short b){
            int result = 0 ;
            for(int i = 0 ; i < 24 ; i += 3){
                if(b%2 != 0){
                    result += Math.pow(2,i);
                }
                if(g%2 != 0){
                    result += Math.pow(2,i+1);
                }
                if(r%2 != 0){
                    result += Math.pow(2,i+2);
                }
                b /= 2 ;
                g /= 2 ;
                r /= 2 ;
            }
            return result ;
        }
    }

}


