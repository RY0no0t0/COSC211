public class Main {

    public static void main (String[] args) {

        int[] toAdd = {20, 3, 2, 5, 9, 100, 50, 21, 200, 8, 7, 10};
        int[] toFind = {5, 9, 10, 12, 20, 100, 150, 200, 300};
        int[] toRemove = {20, 2, 9, 100, 300};

        Tree tree = new Tree();

        for(int i : toAdd) {
            tree.add(i);
        }

        System.out.println("Deepest: "+ Tree.deepest(tree.r).value);
        System.out.println("Total: "+ Tree.total(tree.r));

        for (int i : toFind) {
            System.out.print("The value "+i+" was searched, and it was ");
            if (tree.find(i)) { System.out.println("found."); }
            else { System.out.println("not found."); }
        }

        for (int i : toRemove) {
            tree.remove(i);
        }

        System.out.println("Deepest: "+ Tree.deepest(tree.r).value);
        System.out.println("Total: "+ Tree.total(tree.r));


        for (int i : toFind) {
            System.out.print("The value "+i+" was searched, and it was ");
            if (tree.find(i)) { System.out.println("found."); }
            else { System.out.println("not found."); }
        }

    }

}