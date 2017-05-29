package Parse;

/**
 * Created by korneliusz on 22.05.17.
 */
public abstract class Function {
    String name;
    Integer numberOfArguments;
    abstract Token myFunction(Token... args);
    public Token useMyFunction(Token... args) {
        if (args.length != numberOfArguments) {
            throw new WrongNumberOfArgumentsException();
        }
        else {
            return myFunction(args);
        }
    }

}
