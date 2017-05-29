package Parse;

/**
 * Created by korneliusz on 07.05.17.
 */

public interface Operator {
    int getPriority();
    Token myMethod(Token a, Token b);
}
