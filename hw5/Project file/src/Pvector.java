public class Pvector {
    private short red ;
    private short green ;
    private short blue ;

    public Pvector(){
        set((short)(0),(short)(0),(short)(0));
    }

    public Pvector(int r , int g , int b){
        set((short) (r),(short) (g),(short) (b));
    }

    void set(int r , int g , int b){
        if (r < 0){ r *= -1; }
        if (g < 0){ g *= -1; }
        if (b < 0){ b *= -1; }
        red = (short) (r%256);
        green = (short) (g%256);
        blue = (short) (b%256);
    }

    public short getRed(){return red ;}
    public short getGreen(){return green ;}
    public short getBlue(){return blue ;}
    public String toString (){
        return  "[ "+red+" , "+green+" , "+blue+" ]";
    }
}
