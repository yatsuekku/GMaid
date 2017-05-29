package Parse;
/**
 * Created by korneliusz on 07.05.17.
 */
import java.util.*;
public class InfixOperator extends HashMap<String, Operator> {

    //constructor automatically creates entries for addition, subtraction, multiplication, division, modulo
    //and entry for key "k" which represents throwing the dice with a faces b times
    public InfixOperator() {
        super();

        put("+",
                new Operator() {
                    public int getPriority() {
                        return 1;
                    }
                    public Token myMethod(Token a, Token b) {
                        return new Token(Integer.toString(a.getValue() + b.getValue()));
                    }
                }
        );

        put("-",
                new Operator() {
                    public int getPriority() {
                        return 1;
                    }
                    public Token myMethod(Token a, Token b) {
                        return new Token(Integer.toString(a.getValue() - b.getValue()));
                    }

                }
        );

        put("*",
              new Operator() {
                  public int getPriority() {
                      return 2;
                  }
                  public Token myMethod(Token a, Token b) {
                      return new Token(Integer.toString(a.getValue() * b.getValue()));
                  }

                }
        );

        put("/",
                new Operator() {
                    public int getPriority() {
                        return 2;
                    }

                   public Token myMethod(Token a, Token b) {
                      return new Token((Integer.toString(a.getValue() / b.getValue())));
                  }
                }
        );

        put("%",
                new Operator() {
                    public int getPriority() {
                        return 2;
                    }

                   public Token myMethod(Token a, Token b) {
                      return new Token((Integer.toString(a.getValue() % b.getValue())));
                  }
                }
        );

        put("k",
                new Operator() {

                    public int getPriority() {
                        return 10;
                    }

                    public Token myMethod(Token a, Token b) {
                        Dice D = new Dice(b.getValue());
                        int sum = 0;
                        for (int i = 0; i < a.getValue(); i++) {
                            sum += D.throwDice();
                        }

                        return new Token(Integer.toString(sum));
                    }
                }
        );
    }

}
