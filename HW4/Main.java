public class Main {

    public static void main(String[] args) throws java.io.IOException {
        // First test the hash table functionality
        tableTest();

        // Then play HashAttack!
        String fileContents;
        String[] attack;

        fileContents = new String(java.nio.file.Files.readAllBytes
                (java.nio.file.Paths.get("attack1.txt")));
        attack = fileContents.split("\\r?\\n");

        System.out.println("Here comes attack #1!");
        System.out.println("Defense by hash1() earns " +
                hashAttack(attack, 1) + " points.");
        System.out.println("Defense by hash2() earns " +
                hashAttack(attack, 2) + " points.");

        fileContents = new String(java.nio.file.Files.readAllBytes
                (java.nio.file.Paths.get("attack2.txt")));
        attack = fileContents.split("\\r?\\n");

        System.out.println("Here comes attack #2!");
        System.out.println("Defense by hash1() earns " +
                hashAttack(attack, 1) + " points.");
        System.out.println("Defense by hash2() earns " +
                hashAttack(attack, 2) + " points.");
    }
    
    static void tableTest() {
        System.out.println("Testing table functionality:");
        System.out.print("Creating a Table with size 3. ");
        Table table = new Table(3, 1);
        System.out.println("Adding a, b, c, and d.");
        table.add("a");
        table.add("b");
        table.add("c");
        table.add("d");
        System.out.print("Now maxInSlot() must be at least 2, and it is: ");
        System.out.println(table.maxInSlot());
        System.out.println("Testing table.find(\"b\"): " + table.find("b"));
        System.out.println("Testing table.find(\"e\"): " + table.find("e"));
        System.out.println("Removing b.");
        table.remove("b");
        System.out.println("Testing table.find(\"b\"): " + table.find("b"));
    }

    static int hashAttack(String[] attack, int whichHash) {
        int width = 100;
        int height = 10;
        Table table = new Table(width, whichHash);
        int step;
        for (step = 0; step < width * height && step < attack.length; step++) {
            table.add(attack[step]);
            if (table.maxInSlot() > height) {
                break;
            }
        }
        return step;
    }
}