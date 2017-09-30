import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    private static int textSize;
    private static String context;
    private static int position = -1;
    private static Node beforeNode = null;
    private static StringBuilder word;
    private static List<String> wsym;

    public static void main(String[] args) {

        File file = new File("/home/jvch/test.txt");
        word = new StringBuilder();
        wsym = new ArrayList<>();
        NodeType.initNode();
        wsym.add("if");
        wsym.add("for");
        wsym.add("which");
//        context=getTextString(file);
        context = replaceBlank(getTextString(file));
        textSize = context.length();
        System.out.println(context);
        function1();
    }

    //读取文件内容转化为String类型
    private static String getTextString(File file) {
        StringBuilder result = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    private static void function1() {
        char singleWorld = getNextChar();
        if (singleWorld != '$') {
            if (beforeNode == null) {
                beforeNode = getNextWoldType(singleWorld);
                word.append(singleWorld);
                function1();
            } else {
                //可能出错
                String contextWord;
                if (beforeNode.getNextList().contains(getNextWoldType(singleWorld).getType())) {
                    word.append(singleWorld);
                    beforeNode = getNextWoldType(singleWorld);
                    if (textSize == 0) {
                        contextWord = word.toString();
                        getWorldType(contextWord, beforeNode);
                    }
                    function1();
                } else {
                    //如果没有后继，下一个错误的单词就是singleWorld
                    contextWord = word.toString();
                    //把这个contextWord进行分类
                    getWorldType(contextWord, beforeNode);
                    word.delete(0, word.length());
                    beforeNode = getNextWoldType(singleWorld);
                    word.append(singleWorld);
                    if (textSize == 0) {
                        contextWord = word.toString();
                        getWorldType(contextWord, beforeNode);
                    }
                    function1();
                }
            }
        }
    }

    private static Node getNextWoldType(char singleWorld) {
        //判断是字母

        if (singleWorld >= 'A' && singleWorld <= 'z') {
            return NodeType.nodeA;
        } else if (singleWorld >= '0' && singleWorld <= '9') {
            return NodeType.nodeB;
        } else if (singleWorld == '>') {
            return NodeType.nodeC;
        } else if (singleWorld == '<') {
            return NodeType.nodeD;
        } else if (singleWorld == '=') {
            return NodeType.nodeE;
        } else if (singleWorld == '(') {
            return NodeType.nodeF;
        } else if (singleWorld == ')') {
            return NodeType.nodeG;
        } else if (singleWorld == ';') {
            return NodeType.nodeH;
        }else {
            return null;
        }

    }

    private static char getNextChar() {
        if (textSize != 0) {
            textSize--;
            position++;
            return context.charAt(position);

        } else {
            System.out.println("读取完毕");
            return '$';
        }
    }

    private static void getWorldType(String contextWord, Node lastNode) {
        WorldType worldType = new WorldType();
        switch (lastNode.getEndType()) {
            case "num":
                worldType.setType("num");
                worldType.setValue(Long.valueOf(contextWord));
                break;
            case "ident":
                worldType.setType("ident");
                for (String string : wsym) {
                    if (contextWord.equals(string)) {
                        worldType.setType("sym");
                        break;
                    }
                }
                break;
            case "more":
                worldType.setType("more");
                break;
            case "less":
                worldType.setType("less");
                break;
            case "equl":
                if (contextWord.startsWith("<")) {
                    worldType.setType("lessEqul");
                } else if (contextWord.startsWith(">")) {
                    worldType.setType("moreEqul");
                } else {
                    worldType.setType("equl");
                }
                break;
            case "left_bracket":
                worldType.setType(lastNode.getEndType());
                break;
            case "right_bracket":
                worldType.setType(lastNode.getEndType());
                break;
            case "end_line":
                worldType.setType(lastNode.getEndType());
                break;
        }
        worldType.setWorld(contextWord);
        worldType.tostring();
    }

    private static String replaceBlank(String str1) {
        String tempStr = "";
        if (str1 != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str1);
            tempStr = m.replaceAll("");
        }
        return tempStr;
    }


}
