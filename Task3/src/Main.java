public class Main {
    public static void main(String[] args) {
        myRBTree<Integer> rbTree = new myRBTree<>();
        rbTree.add(10,10,rbTree.root);
        rbTree.add(1,1,rbTree.root);
        rbTree.add(2,2,rbTree.root);
        rbTree.add(5,5,rbTree.root);
        rbTree.add(8,8,rbTree.root);
        rbTree.add(13,13,rbTree.root);
        rbTree.add(16,16,rbTree.root);
        rbTree.add(11,11,rbTree.root);
        rbTree.add(9,9,rbTree.root);
        rbTree.print(rbTree.root);
    }
}
