import java.util.List;

public class Node {

    private char type;
    private List<Character> nextList;

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public List<Character> getNextList() {
        return nextList;
    }

    public void setNextList(List<Character> nextList) {
        this.nextList = nextList;
    }
}
