import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by k.filimonov on 13/03/2017.
 */
public class Deadlock {

    private static class PingPong {

        synchronized void ping(PingPong pingPong) {
            System.out.println("ping");
            pingPong.pong(this);
        }

        synchronized void pong(PingPong pingPong) {
            System.out.println("pong");
            pingPong.ping(this);
        }

    }

    public static void main(String[] args) {
        PingPong ping = new PingPong();
        PingPong pong = new PingPong();


        Thread t1 = new Thread(() -> ping.ping(pong));

        Thread t2 = new Thread(() -> pong.pong(ping));

        t1.start();
        t2.start();
    }
}

