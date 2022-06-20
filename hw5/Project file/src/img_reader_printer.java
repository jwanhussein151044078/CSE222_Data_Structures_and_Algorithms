import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
class img_reader_printer {


    static final int N = 100 ;
    static private MAXPQ PQLEX = new MAXPQ(new MAXPQ.LEX());
    static private MAXPQ PQEUC = new MAXPQ(new MAXPQ.EUC());
    static private MAXPQ PQBMX = new MAXPQ(new MAXPQ.BMX());
    static private monitor m_LEX = new monitor();
    static private monitor m_EUC = new monitor();
    static private monitor m_BMX = new monitor();

    static private int PIXEL_S;


    static class img_reader extends Thread {
        private BufferedImage img;
        private int W , H;
        public img_reader(String filename) {
            try {
                img = ImageIO.read(new File(filename));
                W = img.getWidth();
                H = img.getHeight();
                PIXEL_S = W*H ;

            } catch (IllegalArgumentException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }

        }

        public void run() {
            Pvector p;
            int counter = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++){
                    p = getnextpixel(j, i);
                    System.out.println("Thread 1 : " + p + "   counter  = " + counter++);
                    m_LEX.insert(p , PQLEX);
                    m_EUC.insert(p , PQEUC);
                    m_BMX.insert(p , PQBMX);
                }
            }
        }

        private Pvector getnextpixel(int i, int j) {
            Color C = new Color(img.getRGB(i, j));
            Pvector p = new Pvector(C.getRed(), C.getGreen(), C.getBlue());
            return p;
        }
    }

    static class print_LEX extends Thread {
        public void run(){
            Pvector p ;
            int counter = 0 ;
            while (counter <PIXEL_S){
                 p = m_LEX.remove(PQLEX);
                System.out.println("Thread 2 : " + p + "   counter  = " + counter++);
            }
        }
    }
    static class print_EUC extends Thread {
        public void run(){
            Pvector p ;
            int counter = 0 ;
            while (counter < PIXEL_S){
                p = m_EUC.remove(PQEUC);
                System.out.println("Thread 3 : " + p + "   counter  = " + counter++);
            }
        }
    }
    static class print_BMX extends Thread {
        public void run(){
            Pvector p ;
            int counter = 0 ;
            while (counter < PIXEL_S){
                p = m_BMX.remove(PQBMX);
                System.out.println("Thread 4 : " + p + "   counter  = " + counter++);
            }
        }
    }


    static public class monitor {
        private int count = 0  ;

        public synchronized void insert(Pvector P , MAXPQ PQ){
            if(count == N )
                interruptme();
            PQ.offer(P);
            count = count + 1 ;
            if (count == 1)
                notifyAll();
        }

        public synchronized Pvector remove(MAXPQ PQ ){
            Pvector P ;
            if(count == 0)
                interruptme();
            P = PQ.poll();
            count = count - 1 ;
            if(count == N-1 )
                notifyAll();
            return P ;
        }
        private void interruptme(){
            try{
                wait();
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}

