public class Main {
    public static void main(String[] args) {

        // Unpack the args
        double tparam = Double.parseDouble(args[0]);
        double rparam = Double.parseDouble(args[1]);

        // Making the data structures
        Data d = new Graph(72);
        //Data d = new heap()

        d.initialize();

        // Pick the first number
        int first = (int)(Math.random()*72);

        // Remove all the edges that lead to the first row.
        d.RemoveIn(first);

        // Make a list that stores the notes
        Queue products = new Queue();
        products.add(first);

        for (int count = 0; count < 11; count++) {
            Tonality (tparam, d, products);
            Resolution (rparam, d, products);

            Probability (d, products);
            int next = PickNext(d, products);

            products.add(next);
            d.RemoveIn(next);
        }

       // products.print();

       System.out.print("[");
        for (int i=0; i< 12; i++) {
            int note = products.remove();
            if ((note - note%6) < 6) {
                System.out.print("C, ");
            } else if ((note - note%6) < 12) {
                System.out.print("C#, ");
            } else if ((note - note%6) < 18) {
                System.out.print("D, ");
            } else if ((note - note%6) < 24) {
                System.out.print("Eb, ");
            } else if ((note - note%6) < 30) {
                System.out.print("E, ");
            } else if ((note - note%6) < 36) {
                System.out.print("F, ");
            } else if ((note - note%6) < 42) {
                System.out.print("F#, ");
            } else if ((note - note%6) < 48) {
                System.out.print("G, ");
            } else if ((note - note%6) < 54) {
                System.out.print("Ab, ");
            } else if ((note - note%6) < 60) {
                System.out.print("A, ");
            } else if ((note - note%6) < 66) {
                System.out.print("Bb, ");
            } else if ((note - note%6) < 72) {
                System.out.print("B, ");
            }
        }
        System.out.println("]");
    }

    public static void Tonality(double param, Data d, Queue store){
        // the current note
        int current = store.peak();

        //make Queue to store the candidate
        Queue preferred = new Queue();
        
        // pick the candidates
        int type = current%6;
        if (type == 0) {
            preferred.add(idx(3*8+current+1));
            preferred.add(idx(3*14+current+2));
        } else if (type == 1) {
            preferred.add(idx(3*6+current+1));
            preferred.add(idx(3*15+current+2));
        } else if (type == 2) {
            preferred.add(idx(3*9+current+1));
            preferred.add(idx(3*17+current+2));
        } else if (type == 3) {
            preferred.add(idx(3*6+current+1));
            preferred.add(idx(3*14+current+2));
        } else if (type == 4) {
            preferred.add(idx(3*8+current+1));
            preferred.add(idx(3*17+current+2));
        } else if (type == 5) {
            preferred.add(idx(3*9+current+1));
            preferred.add(idx(3*15+current+2));
        }

        // how many edges exist in the preferred
        Queue checked = CheckExist(preferred, d, current);
        double np = 0;
        int qsize = checked.size();
        if (checked.size() == 0) {
            np = param;
        } else {
            for (int i = 0; i < qsize; i++) {
                int now = checked.remove();
                d.at(current,now).tonal = param/((double)qsize);
            }
            np = 1-param;
        }

        // Non-preferred notes
        Queue nonpreferred = new Queue();
        if (type == 0) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(3*8+current+1) || i != idx(3*14+current+2)) {
                    nonpreferred.add(i);
                }
            }            
        } else if (type == 1) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(3*6+current+1) || i != idx(3*15+current+2)) {
                    nonpreferred.add(i);
                }
            }      
        } else if (type == 2) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(39+current+1) || i != idx(3*17+current+2)) {
                    nonpreferred.add(i);
                }
            }      
        } else if (type == 3) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(3*6+current+1) || i != idx(3*14+current+2)) {
                    nonpreferred.add(i);
                }
            }      
        } else if (type == 4) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(3*8+current+1) || i != idx(3*17+current+2)) {
                    nonpreferred.add(i);
                }
            }     
        } else if (type == 5) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(3*9+current+1) || i != idx(3*15+current+1)) {
                    nonpreferred.add(i);
                }
            }      
        }

        Queue NonChecked = CheckExist(nonpreferred, d, current);

        int Qsize = NonChecked.size();
        if (NonChecked.size() != 0) {
            for (int i = 0; i < Qsize; i++) {
                int now = NonChecked.remove();
                d.at(current,now).tonal = np/((double)Qsize);
            }
        }

    }

    public static void Resolution (double param, Data d, Queue store) {
        // the current note
        int current = store.peak();

        //make Queue to store the candidate
        Queue preferred = new Queue();
        
        // pick the candidates
        int type = current%6;
        if (type == 0) {
            preferred.add(idx(6*2+current+2));
            preferred.add(idx(6*5+current+0));
            preferred.add(idx(6*7+current+0));
            preferred.add(idx(6*9+current+1));
            preferred.add(idx(6*11+current+1));
        } else if (type == 1) {
            preferred.add(idx(6*1+current+0));
            preferred.add(idx(6*3+current+0));
            preferred.add(idx(6*5+current+1));
            preferred.add(idx(6*7+current+1));
            preferred.add(idx(6*8+current+2));
            preferred.add(idx(6*10+current+2));
        } else if (type == 2) {
            preferred.add(idx(6*2+current+1));
            preferred.add(idx(6*4+current+1));
            preferred.add(idx(6*5+current+2));
            preferred.add(idx(6*7+current+2));
            preferred.add(idx(6*10+current+0));
        } else if (type == 3) {
            preferred.add(idx(6*2+current+2));
            preferred.add(idx(6*5+current+3));
            preferred.add(idx(6*7+current+0));
            preferred.add(idx(6*8+current+4));
            preferred.add(idx(6*11+current+1));
        } else if (type == 4) {
            preferred.add(idx(6*2+current+3));
            preferred.add(idx(6*4+current+0));
            preferred.add(idx(6*5+current+4));
            preferred.add(idx(6*8+current+1));
            preferred.add(idx(6*9+current+5));
            preferred.add(idx(6*11+current+2));
        } else if (type == 5) {
            preferred.add(idx(6*1+current+4));
            preferred.add(idx(6*4+current+1));
            preferred.add(idx(6*5+current+5));
            preferred.add(idx(6*7+current+2));
            preferred.add(idx(6*0+current+3));
        }

        // how many edges exist in the preferred
        Queue checked = CheckExist(preferred, d, current);
        double np = 0;
        int qsize = checked.size();
        if (checked.size() == 0) {
            np = param;
        } else {
            for (int i = 0; i < qsize; i++) {
                int now = checked.remove();
                d.at(current,now).tonal = param/((double)qsize);
            }
            np = 1-param;
        }

        // Non-preferred notes
        Queue nonpreferred = new Queue();
        if (type == 0) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(6*2+current+2) || i != idx(6*5+current+0) || i != idx(6*7+current+0) || 
                i != idx(6*9+current+1) || i != idx(6*11+current+1)) {
                    nonpreferred.add(i);
                }
            }            
        } else if (type == 1) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(6*1+current+0) || i != idx(63+current+0) || i != idx(6*5+current+1) || 
                i != idx(6*7+current+1) || i != idx(6*8+current+2) || i != idx(6*10+current+2)) {
                    nonpreferred.add(i);
                }
            }      
        } else if (type == 2) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(6*2+current+1) || i != idx(6*4+current+1) || i != idx(6*5+current+2) || 
                i != idx(6*7+current+2) || i != idx(6*10+current+0)) {
                    nonpreferred.add(i);
                }
            }      
        } else if (type == 3) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(6*2+current+2) || i != idx(6*5+current+3) || i != idx(6*7+current+0) || 
                i != idx(6*8+current+4) || i != idx(6*11+current+1)) {
                    nonpreferred.add(i);
                }
            }      
        } else if (type == 4) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(6*2+current+3) || i != idx(6*4+current+0) || i != idx(6*5+current+4) || 
                i != idx(6*8+current+1) || i != idx(6*9+current+5) || i != idx(6*11+current+2)) {
                    nonpreferred.add(i);
                }
            }     
        } else if (type == 5) {
            for (int i = 0; i < 72; i++) {
                if (i != idx(6*1+current+4) || i != idx(6*4+current+1) || i != idx(6*5+current+5) || 
                i != idx(6*7+current+2) || i != idx(6*10+current+3)) {
                    nonpreferred.add(i);
                }
            }      
        }

        Queue NonChecked = CheckExist(nonpreferred, d, current);

        int Qsize = NonChecked.size();
        if (NonChecked.size() != 0) {
            for (int i = 0; i < Qsize; i++) {
                int now = NonChecked.remove();
                d.at(current,now).tonal = np/((double)Qsize);
            }
        }
    }

    public static void Probability (Data d, Queue products) {
        int current = products.peak();
        double sum = 0;
        for (int i = 0; i < 72; i++) {
                d.at(current, i).prob = d.at(current , i).tonal + d.at(current, i).resolution;
                sum = sum + d.at(current, i).prob;
        }

        for (int i = 0; i < 72; i++) {
            d.at(current, i).prob = d.at(current , i).prob/sum;
        }


    }

    public static int PickNext(Data d, Queue products) {
        double NextRandom = Math.random();

        int current = products.peak();
        double counter = 0;
    
        for (int i = 0; i < 72; i++) {
            counter = counter + d.at(current, i).prob;
            if (counter >= NextRandom) {
                return i;
            }
        }

        return -1;
    }

    public static Queue CheckExist (Queue q, Data d, int current) {
        Queue toReturn = new Queue();
        int qsize = q.size();
        for (int i = 0; i < qsize; i++) {
            int now = q.remove();
            if (d.at(current,now).exist){
                toReturn.add(now);
            }
        }
        return toReturn;
    }

    public static int idx(int num) {
        if (num >= 0) {
            return num % 72;
        } else {
            return (num % 72) + 72;
        }
    }
}