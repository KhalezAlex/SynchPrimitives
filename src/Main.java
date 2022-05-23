import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MyThread thread1 = new MyThread(1, "First.txt");
        MyThread thread2 = new MyThread(2, "Second.txt");
        MyThread thread3 = new MyThread(3, "Third.txt");
        //чистка файлов
        System.out.println("Стартуют потоки");
        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Стартуют джоины");
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Джоины закончены");
    }
}