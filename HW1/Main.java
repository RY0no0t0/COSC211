public class Main {

    // For 1 dup dup 1 + dup 100000 "<" -10 swap if jump drop 100 ">" -7 swap if jump drop
    //     Array --> Output: 99
    //               Runtime: 1100389299
    //     List --> Output: 99
    //              Runtime: 1241902279

    // For 1 dup dup 1 + dup 5 shove yank drop 100000 "<" -15 swap if jump drop 100 ">" -7 swap if jump drop
    //     Array --> Output: 99
    //               Runtime: 1800765203
    //     List --> Output: 99
    //              Runtime: 1945698097

    public static void main (String[] args) {
        long start = System.nanoTime();

        Stack stack = new ArrayStack();
        // Stack stack = new ListStack();

        int counter = 0;
        int steps = 0;

        while (counter < args.length) {

            // Stop the program once you execute a billion times
            if (steps == 1000000000) {
                break;
            }
            
            // Increase counter if the counter is negative
            if (counter < 0) {
                counter++;
            } else if (isInteger(args[counter])) { // If input is a number, add to the stack
                stack.push(Integer.parseInt(args[counter]));
                counter++;
            } else { // if input is a string, do what the input asks you to do.
                if ("dup drop jump if shove".contains(args[counter])) {
                    if (stack.size() > 0) {
                        int top = stack.pop();
                        switch(args[counter]) {
                            case "dup":
                                stack.push(top);
                                stack.push(top);
                                break;
                            case "drop":;
                                break;
                            case "jump":
                                counter += top;
                                break;
                            case "if":
                                if (top <= 0) {
                                counter++; }
                                break;
                            case "shove":
                                stack.insertBottom(top);
                                break;
                        }
                    } 
                    counter++;
                } else if (args[counter].equals("yank")) {
                    if (stack.size()>0){
                        stack.push(stack.extractBottom());
                    } counter++;
                } else if ("+ - * / swap = > <".contains(args[counter])) {
                    if (stack.size() > 1) {
                        int top = stack.pop();
                        int sec = stack.pop();
                        switch(args[counter]) {
                            case "+":
                                stack.push(top+sec);
                                break;
                            case "-":
                                stack.push(sec-top);
                                break;
                            case "*":
                                stack.push(top*sec);
                                break;
                            case "/":
                                stack.push(sec/top);
                                break;
                            case "swap":
                                stack.push(top);
                                stack.push(sec);
                                break;
                            case "=":
                                if (top==sec) {stack.push(1);}
                                else {stack.push(0);}
                                break;
                            case ">":
                                if (sec>top) {stack.push(1);}
                                else {stack.push(0);}
                                break;
                            case "<":
                                if (sec<top) {stack.push(1);}
                                else {stack.push(0);}
                                break;
                        }
                    } 
                    counter++;
                } else if (args[counter].equals("rot")) {
                    if (stack.size() > 2) {
                        stack.insertBottom(stack.pop());
                        stack.insertBottom(stack.pop());
                        int third = stack.pop();
                        stack.push(stack.extractBottom());
                        stack.push(stack.extractBottom());
                        stack.push(third);
                    }
                    counter++;
                } else { // if the unknown function is given, skip this argument.
                    counter++;
                }
            }

            // steps increase no matter what
            steps++;
        }

        // print result. if nothing is in the stack, say "nothing." Otherwise print the top of the stack
        System.out.print("Output: ");
        if (stack.size() == 0) {
            System.out.println("nothing");
        } else {
            System.out.println(stack.pop());
        }
        System.out.println("Runtime: " + (System.nanoTime() - start));
    }

    //Checking whether it's integer
    public static boolean isInteger(String str) { 
        try { 
            Integer.parseInt(str); 
            return true; 
        } catch (NumberFormatException e) { 
            return false; 
        } 
    }

}