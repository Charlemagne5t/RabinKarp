import org.junit.Assert;
import org.junit.Test;

public class RabinKarpTest {
    @Test
    public void test1() {
        String pattern = "abracadabra";
        String text = "abacadabrabracabracadabrabrabracad";
        RabinKarp searcher = new RabinKarp(pattern);
        int expected = 14;
        int actual = searcher.search(text);

        Assert.assertEquals(expected, actual);

    }
}
