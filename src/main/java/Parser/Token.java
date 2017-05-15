package Parser;

/**
 * Created by Stefan Dedalus on 2017-05-15.
 */
public class Token {
    private Integer value;
    Token(int a) {
        value = a;
    }
    public Integer getValue(){
        return value;
    }
}
