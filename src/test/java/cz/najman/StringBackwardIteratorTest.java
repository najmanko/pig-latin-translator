package cz.najman;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class StringBackwardIteratorTest {

    @Test
    public void iterateBackward() {
        //given
        Iterator<Character> iter = new StringBackwardIterator("Ab");

        //when
        char first = iter.next();
        boolean hasSecond = iter.hasNext();
        char second = iter.next();
        boolean hasThird = iter.hasNext();

        //then
        assertEquals('b', first);
        assertEquals(true, hasSecond);
        assertEquals('A', second);
        assertEquals(false, hasThird);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void iterateOver() {
        //given
        Iterator<Character> iter = new StringBackwardIterator("1");

        //when
        iter.next();
        iter.next();
    }
}
