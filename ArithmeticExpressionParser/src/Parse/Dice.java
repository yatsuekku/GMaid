package Parse; /**
 * Created by korneliusz on 07.05.17.
 */
import java.util.*;
public class Dice {
    Random gen = new Random();
    int faces;
    public Dice(int k) {
        faces = k;
    }
    public int throwDice() {
        return gen.nextInt(faces);
    }
}
