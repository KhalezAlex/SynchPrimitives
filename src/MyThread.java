import java.io.*;
import java.util.ArrayDeque;

public class MyThread extends java.lang.Thread {
    private int number;
    private String filePAth;
    private static final ArrayDeque<Integer> arrDeq = new ArrayDeque<>();
    private BufferedReader bR;
    private BufferedWriter bWr;
    private int brSize;
    private static int flag = 0;


    public MyThread(int number, String filePath) throws IOException {
        this.number = number;
        this.filePAth = filePath;
        if (number == 1) {
            bR = new BufferedReader(new FileReader(filePath));
        } else {
            bWr = new BufferedWriter(new FileWriter(filePath));
        }
    }

    public void run() {
        String str;
        int num;
        if (number == 1) {
            while (true) {
                synchronized (arrDeq) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            if ((str = bR.readLine()) == null) {
                                System.out.println("Flag = 1");
                                flag = 1;
                                return;
                            }
                            System.out.println("Добавление " + str + " в очередь");
                            arrDeq.add(Integer.valueOf(str));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (number == 2) {
            System.out.println("Start2");
            while (flag == 0) {
                synchronized (arrDeq) {
                    if (!arrDeq.isEmpty()) {
                        try {
                            num = arrDeq.pollLast();

                            if ((num % 2) == 0) {
                                System.out.println("Второй поток пишет " + num);
                                bWr.write(Integer.toString(num));
                                bWr.write("\n");
                                bWr.flush();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        if (number == 3) {
            System.out.println("Start3");
            while (flag == 0) {
                synchronized (arrDeq) {
                    if (!arrDeq.isEmpty()) {
                        try {
                            num = arrDeq.pollLast();
                            if ((num % 2) != 0) {
                                System.out.println("Третий поток пишет" + num);
                                bWr.write(Integer.toString(num));
                                bWr.write("\n");
                                bWr.flush();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

}
