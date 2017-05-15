package Parser;

/**
 * Created by jacek on 2017-05-15.
 */
public abstract class Operator {
    public abstract Token operator(Token ... A) throws OperatorUsageException;
    private Integer arity;
}
