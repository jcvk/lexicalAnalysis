import java.util.ArrayList;
import java.util.List;

public class Node {

    private char type;
    private List<Character> nextList;
    private String endType;

    public Node() {
        nextList=new ArrayList<>();
    }

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

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }


}
