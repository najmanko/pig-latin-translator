package cz.najman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TranslatorTest {

    Translator translator;
    
    @Before
    public void setup() {
        translator = new Translator();
    }

    @Test
    public void translateBasicConsonantWord() {
        //when
        String translated = translator.translate("Hello");

        //then
        assertEquals("Ellohay", translated);
    }

    @Test
    public void translateBasicVowelWord() {
        //when
        String translated = translator.translate("apple");

        //then
        assertEquals("appleway", translated);
    }

    @Test
    public void translateWordWithWayInTheEnd() {
        //when
        String translated = translator.translate("stairway");

        //then
        assertEquals("stairway", translated);
    }

    @Test
    public void translateBasicPunctuationWord() {
        //when
        String translated = translator.translate("can't");

        //then
        assertEquals("antca'y", translated);
    }

    @Test
    public void translateBasicPunctuationInTheEnd() {
        //when
        String translated = translator.translate("end.");

        //then
        assertEquals("endway.", translated);
    }

    @Test
    public void translateBasicPunctuationOnStart() {
        //when
        String translated = translator.translate("!start");

        //then
        assertEquals("!start", translated);
    }

    @Test
    public void translateWordsWithHyphen() {
        //when
        String translated = translator.translate("this-thing");

        //then
        assertEquals("histay-hingtay", translated);
    }

    @Test
    public void translateWordsWithCapital() {
        //when
        String translated = translator.translate("Beach");

        //then
        assertEquals("Eachbay", translated);
    }

    @Test
    public void translateWordsWithTwoCapitals() {
        //when
        String translated = translator.translate("McCloud");

        //then
        assertEquals("CcLoudmay", translated);
    }

    @Test
    public void translateWordsWithCapitalsConsonant() {
        //when
        String translated = translator.translate("BaNuJkLkMnHyTrVbFdA");

        //then
        assertEquals("AnUjKlKmNhYtRvBfDaBay", translated);
    }

    @Test
    public void translateWordsWithCapitalsVowel() {
        //when
        String translated = translator.translate("AaNuJkLkMnHyTrVbFd");

        //then
        assertEquals("AaNuJkLkMnHyTrVbFdway", translated);
    }

    @Test
    public void translateWordsWithCapitalsWithMixedPuctuations() {
        //when
        String translated = translator.translate("A'B.C,D/F{G[H[I]J!K?L");

        //then
        assertEquals("AbCd'f.g,h/i{j[k[l]w!a?y", translated);
    }

    @Test
    public void translateWordsWithMultiPunctuationsInTheEnd() {
        //when
        String translated = translator.translate("ASDFGHJK'''");

        //then
        assertEquals("ASDFGHJKway'''", translated);
    }

    @Test
    public void translateWordsWithMultiHyphens() {
        //when
        String translated = translator.translate("Hello-apple-stairway-can't-end.-this-thing-Beach-McCloud");

        //then
        assertEquals("Ellohay-appleway-stairway-antca'y-endway.-histay-hingtay-Eachbay-CcLoudmay", translated);
    }

    @Test
    public void translateJustHuphen() {
        //when
        String translated = translator.translate("-");

        //then
        assertEquals("-", translated);
    }

    @Test
    public void translateJustOnePunctuation() {
        //when
        String translated = translator.translate("!");

        //then
        assertEquals("!", translated);
    }

    @Test
    public void translateNumber() {
        //when
        String translated = translator.translate("5");

        //then
        assertEquals("5", translated);
    }
}
