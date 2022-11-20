import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestFractionTests {

    @Test
    public void testEqualsFULL() {
        Fraction temp = new Fraction(1,1);
        assertThat(temp, equalTo(Fraction.FULL));
    }

    @Test
    public void testEqualsHALF() {
        Fraction temp = new Fraction(1,2);
        assertThat(temp, is(equalTo(Fraction.HALF)));
    }

    @Test
    public void testEqualsONE_THIRD() {
        Fraction temp = new Fraction(1,3);
        assertThat(temp, is(Fraction.ONE_THIRD));
    }

    @Test
    public void testEqualsONE_FOURTH() {
        Fraction temp = new Fraction(1,4);
        assertThat(temp, instanceOf(Fraction.class));
    }
}
