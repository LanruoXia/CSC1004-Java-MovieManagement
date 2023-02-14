class testThread extends Thread{
    private Thread t;
    String ThreadName;
    testThread(String name){
        ThreadName = name;
        System.out.println("Creating " + ThreadName);
    }
    public void run(){
        System.out.println("Runnning " + ThreadName);
        try{
            for(int i = 5; i >0; i--){
            System.out.println(ThreadName + " counted "+ i);
            sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println(ThreadName + " is interrupted");


        }
        System.out.println(ThreadName + " finished");
    }

    public void start(){
        System.out.println(ThreadName + " Starting");
        if(t == null){
            t = new Thread(this, ThreadName);
            t.start();//启用run()?

        }
    }
}


public class TestMultithreading {
    public static void main(String[] args){
        testThread thread1 = new testThread("thread1");
        thread1.start();
        testThread thread2 = new testThread("thread2");
        thread2.start();

    }

}
