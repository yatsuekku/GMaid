package Parse;

/**
 * Created by korneliusz on 22.05.17.
 */
class Token {
    String name;
    Token(String S) {
        name = S;
    }
    boolean isNumber() {
        return name.matches("^[0-9]+?");
    }

    boolean isSeparator() {
        return (name.equals(","));
    }

    boolean isLeft() {
        return (name.equals("("));
    }

    boolean isRight() {
        return (name.equals(")"));
    }

    Integer getValue() {
        //at this moment the only tokens for which we can get values are the ones representing raw number
        if (isNumber()) return Integer.parseInt(name);
        throw new NotANumberException();
    }
    String getName() {
        if (name.charAt(0) != '"') {
            return name;
        }
        else
            return name.substring(1, name.length() - 1);
    }
    public String toString() {
        return name;
    }
}
