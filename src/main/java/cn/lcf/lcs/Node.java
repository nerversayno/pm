package cn.lcf.lcs;

/**
 * Created by lcf on 2015/1/14.
 */
public class Node {
    Character c;
    Node pre;

    public Node() {
    }

    public Node(Character c, Node pre) {
        this.c = c;
        this.pre = pre;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public void show() {
        Node node = this;
        while (node != null){
            System.out.print(node.getC());
            node = node.getPre();
        }
    }
}
