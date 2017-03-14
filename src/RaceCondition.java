/**
 * Created by k.filimonov on 14/03/2017.
 */
public class RaceCondition {

    private static class Incrementor {
        int i = 0;

        void act() {
            for (int j = 0; j < 10; j++) {
                System.out.println(i++);
            }
        }

    }

    public static void main(String[] args) {
        Incrementor incrementor = new Incrementor();

        for (int i = 0; i < 10; i++) {
            new Thread(incrementor::act).start();
        }
    }

}
