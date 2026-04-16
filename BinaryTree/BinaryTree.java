package BinaryTree;

import java.util.*;

public class BinaryTree {
    class Node  {
        int val;
        Node left;
        Node right;
    }
    Node root;
    Scanner sc = new Scanner(System.in);
    public BinaryTree(){
        root = CreateTree();
    }
    public Node CreateTree(){
        int item = sc.nextInt();
        Node nn = new Node();
        nn.val = item;
        boolean hlc = sc.nextBoolean();
        if(hlc){
            nn.left = CreateTree();
        }
        boolean hrc = sc.nextBoolean();
        if(hrc){
            nn.right =  CreateTree();
        }
        return nn;
    }
    private void display(Node root){
        if (root  == null) return;
        String str = "<-"+root.val+"->";
        if(root.left!=null){
            str = root.left.val  +str;
        }else {
            str  = "."+  str;
        }

        if(root.right!=null){
            str=str +  root.right.val;

        }else{
            str  = str +".";
        }
        System.out.println(str);
        display(root.left);
        display(root.right);

    }
    public void Display(){
        display(root);
    }

    public static void main(String[] args) {
        BinaryTree bt = new  BinaryTree();
        bt.Display();
    }
}
