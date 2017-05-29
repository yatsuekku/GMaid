package Parse;
/**
 * Created by korneliusz on 07.05.17.
 */
import java.beans.Expression;
import java.util.*;
import java.io.*;
public class Parser {
    static Map<String, Operator> infixOperators = new InfixOperator();
    static Map<String, Function> prefixFunctions = new PrefixOperator();

    static public Integer evaluate(String expression) {
        try {
            Integer k = compute(toReversePolish(tokenize(expression))).getValue();
            return k;
        }catch (ExpressionMakesNoSenseException e) {
            System.out.println("What you've just written makes no sense whatsoever, buddy");
        }catch (NotANumberException e) {
            System.out.println("*sigh* It's not even a number.");
        }
        return null;
    }

    static public List<Token> tokenize (String S) {
        List<Token> tokens = new ArrayList<>();
        int mode = 0;

    /**
     * mode variable describes what tokenizer think it's reading
     * 0 means it's unquoted, it's default
     * 1 means quoted string
     * these may or may not be functions/variables etc.
     * brackets and infix operators have no mode of their own as they are only one letter long
     **/
        StringBuilder currentSymbol = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char currentChar = S.charAt(i);
            if (mode == 1) {
                if (currentChar == '"') {
                    currentSymbol.append(currentChar);
                    tokens.add(new Token(currentSymbol.toString()));
                    currentSymbol = new StringBuilder();
                    mode = 0;
                }
                else
                    currentSymbol.append(currentChar);
            }

            else if (currentChar == '"') {
                tokens.add(new Token(currentSymbol.toString()));
                currentSymbol = new StringBuilder("\"");
                mode = 1;
            }

            else if (currentChar == ' ') {
                if (i > 0 && S.charAt(i - 1) != ' ')
                    tokens.add(new Token(currentSymbol.toString()));
                currentSymbol = new StringBuilder();
            }

            else if (currentChar == '(' || currentChar == ')' ||
                    (currentChar != 'k' && infixOperators.containsKey(Character.toString(currentChar)))
                    || currentChar == ',' || currentChar == ';') {

                if (!currentSymbol.toString().equals("")) {
                    tokens.add(new Token(currentSymbol.toString()));
                }

                tokens.add(new Token(Character.toString(currentChar)));
                currentSymbol = new StringBuilder();

            }

            else {
                currentSymbol.append(currentChar);
            }

        }
        if (mode == 1) {
            throw new ExpressionMakesNoSenseException();
        }
        if (!currentSymbol.toString().equals("")) {
            tokens.add(new Token(currentSymbol.toString()));
        }
        return tokens;
    }
    static private Deque<Token> toReversePolish(List<Token> tokens) {
        Deque<Token> Stack = new ArrayDeque<>();
        Deque<Token> Queue = new ArrayDeque<>();
        for (Token T : tokens) {
            if (prefixFunctions.containsKey(T.getName())) {
                Stack.push(T);
            } else if (T.isSeparator()) {
                while (!Stack.peekLast().isLeft()) {
                    Queue.addLast(Stack.pop());
                    if (Stack.peekLast() == null) {
                        throw new ExpressionMakesNoSenseException();
                    }
                }
                Stack.pop();
            } else if (infixOperators.containsKey(T.getName())) {
                while (Stack.peekLast() != null &&
                        infixOperators.containsKey(Stack.peekLast().getName()) &&
                        infixOperators.get(T.getName()).getPriority() <=
                                infixOperators.get(Stack.peekLast().getName()).getPriority()) {

                    Queue.addLast(Stack.pop());

                }
                Stack.addLast(T);
            } else if (T.isLeft()) {
                Stack.addLast(T);
            } else if (T.isRight()) {
                while (!Stack.peekLast().isLeft()) {
                    Queue.addLast(Stack.pop());
                    if (Stack.peekLast() == null) {
                        throw new ExpressionMakesNoSenseException();
                    }
                }
                Stack.pop();
                if (prefixFunctions.containsKey(Stack.peekLast().getName())) {
                    Queue.addLast(Stack.pop());
                }
            } else {
                Queue.addLast(T);
            }
        }
        while (Stack.size() != 0) {
            if (Stack.peekLast().isRight() || Stack.peekLast().isLeft()) {
                throw new ExpressionMakesNoSenseException();
            }
            Queue.addLast(Stack.pop());
        }

        return Queue;
    }

    static public Token compute(Deque<Token> Queue) {
        Deque<Token> Stack = new ArrayDeque<>();
        while (Queue.size() != 0) {
            Token T = Queue.pollFirst();
            if (infixOperators.containsKey(T.getName())) {
                if (Stack.size() < 2) {
                    throw new ExpressionMakesNoSenseException();
                }
                try {
                    Stack.addLast((infixOperators.get(T.getName())).myMethod(Stack.pop(), Stack.pop()));
                } catch (Exception e) {
                    throw new ExpressionMakesNoSenseException();
                }
            }
            else if (prefixFunctions.containsKey(T.getName())) {
                Function f = prefixFunctions.get(T.getName());
                int n = f.numberOfArguments;
                if (Stack.size() < n) {
                    throw new ExpressionMakesNoSenseException();
                }
                else {
                    Token[] params = new Token[n];
                    for (int i = 0; i < n; i++) {
                        params[i] = Stack.pop();
                    }
                    try {
                        Stack.addLast(f.useMyFunction(params));
                    } catch (Exception e) {
                        throw new ExpressionMakesNoSenseException();
                    }
                }
            }
            else {
                Stack.addLast(T);
            }
        }
        if (Stack.size() == 1) {
            return Stack.pop();
        }
        throw new ExpressionMakesNoSenseException();
    }
    

}
