package Parser;

/**
 * Created by jacek on 2017-05-15.
 */
public class OperatorPlus extends Operator {
    @Override
    public  Token operator(Token  ... A) throws OperatorUsageException{
        if(A.length != 2) throw new OperatorUsageException();
        return new Token(A[0].getValue() + A[1].getValue());
    }
}
