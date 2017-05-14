package cz.najman;

import java.util.Iterator;

public class StringBackwardIterator implements Iterator<Character> {

    private final String str;
    private int pos;

    public StringBackwardIterator(String str) {
        this.str = str;
        this.pos = str.length() - 1;
<<<<<<< Updated upstream
        System.out.println("blahova");
=======
        System.out.println("blahovablokada");
>>>>>>> Stashed changes
    }

    public boolean hasNext() {
        return pos >= 0;
    }

    public Character next() {
        return str.charAt(pos--);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
