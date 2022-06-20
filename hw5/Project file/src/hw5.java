public class hw5 {


    public static void main (String[] args){
        img_reader_printer.img_reader thread1 = new img_reader_printer.img_reader(args[0]);
        img_reader_printer.print_LEX  thread2 = new img_reader_printer.print_LEX();
        img_reader_printer.print_EUC  thread3 = new img_reader_printer.print_EUC();
        img_reader_printer.print_BMX  thread4 = new img_reader_printer.print_BMX();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
