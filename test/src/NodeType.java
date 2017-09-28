public class NodeType {

    //A=字母
    public static Node nodeA;
    //B=数字
    public static Node nodeB;
    //C=>
    public static Node nodeC;
    //D=<
    public static Node nodeD;
    //E==
    public static Node nodeE;

    public static void initNode(){
        nodeA=new Node();
        nodeA.getNextList().add('A');
        nodeA.getNextList().add('B');
        nodeA.setType('A');
        nodeA.setEndType("ident");
        nodeB=new Node();
        nodeB.getNextList().add('B');
        nodeB.setType('B');
        nodeB.setEndType("num");
        nodeC=new Node();
        nodeC.getNextList().add('E');
        nodeC.setType('C');
        nodeC.setEndType("more");
        nodeD=new Node();
        nodeD.getNextList().add('E');
        nodeD.setType('D');
        nodeD.setEndType("less");
        nodeE=new Node();
        nodeE.setType('E');
        nodeE.setEndType("equl");
    }


}
