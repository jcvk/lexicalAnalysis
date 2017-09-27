import java.io.*;

public class test {

    private static int textSize;
    private static String context;
    private static int position=-1;
    private static Node beforeNode=null;
    private static StringBuilder word;
    private static String contextWord;

    public static void main(String[] args) {

        File file=null;
        word=new StringBuilder();
        System.out.println("hello world");
        context=getTextString(file);
    }

    //读取文件内容转化为String类型
    private static String getTextString(File file){
        StringBuilder result=new StringBuilder();
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(file));
            String s=null;
            while ((s=br.readLine())!=null){
                result.append(System.lineSeparator()).append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    private void function1(){
//        Node beforeNode=null;
//        for (int i=0;i<str.length();i++){
//            char sigleWold=str.charAt(i);
//            //对读取到的字符进行判断种类
//            if (beforeNode==null){
//                beforeNode=getType(sigleWold);
//            }else {
//                //能够进行下去
//                if (beforeNode.getNextList().contains(getType(sigleWold).getType())){
//
//                }
//            }
//        }
        char singleWorld=getNextChar();
        if (beforeNode==null){
            beforeNode=getNextWoldType(singleWorld);
            word.append(singleWorld);
        }else {
            //可能出错
            if (beforeNode.getNextList().contains(getNextWoldType(singleWorld).getType())){
                word.append(singleWorld);
                function1();
            }else {
                //如果没有后继，下一个错误的单词就是singleWorld
                contextWord=word.toString();
                //把这个contextWord进行分类
                word.delete(0,word.length());
                beforeNode=getNextWoldType(singleWorld);
                word.append(singleWorld);
                function1();
            }
        }
    }

    private Node getNextWoldType(char singleWorld){
        //判断是字母

        if (singleWorld>='a'&&singleWorld<='Z'){
            return NodeType.nodeA;
        }else if (singleWorld>='0'&&singleWorld<='9'){
            return NodeType.nodeB;
        }else if (singleWorld=='>'){
            return NodeType.nodeC;
        }else if (singleWorld=='<'){
            return NodeType.nodeD;
        }else if (singleWorld=='='){
            return NodeType.nodeE;
        }else {
            return null;
        }

    }

    private static char getNextChar(){
        while (textSize!=0){
            textSize--;
            position++;
            return context.charAt(position);

        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return 0;
    }
}
