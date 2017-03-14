/**
 * Created by k.filimonov on 14/03/2017.
 */
public class WaitNotify {

    private static class Producer implements Runnable {
        private final Message message;

        private Producer(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            message.send();
        }
    }

    private static class Consumer implements Runnable {
        private final Message message;

        private Consumer(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            message.receive();
        }
    }

    private static class Message {
        private boolean isSent = false;

        synchronized void send() {
            isSent = true;
            System.out.println("Sent");
            notify();
        }

        synchronized void receive() {
            while(!isSent) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Received");
            isSent = false;
            notify();
        }

    }

    public static void main(String[] args) throws Exception {

        Message message = new Message();

        Thread produce = new Thread(new Producer(message));
        Thread consume = new Thread(new Consumer(message));

        consume.start();
        Thread.sleep(2000);
        produce.start();

    }

}
