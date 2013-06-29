package junit.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * JUnit4からのassertThatアサーションを使いやすくするためのラッパー処理群。<br>
 * isのためのimportが面倒、Boxingは避けたいという御仁向け。<br>
 * assertThatのシグネチャが改善される可能性を考慮してメソッド名は別名にしています。 
 * 
 * @author cobot00
 */
public final class AssertWrapper {

    private AssertWrapper() {
        // コンストラクタの隠蔽
    }

    public static void assertThatWrapper(int actual, int expected) {
        assertThat(Integer.valueOf(actual), is(Integer.valueOf(expected)));
    }

    public static void assertThatWrapper(long actual, long expected) {
        assertThat(Long.valueOf(actual), is(Long.valueOf(expected)));
    }

    public static void assertThatWrapper(double actual, double expected) {
        assertThat(Double.valueOf(actual), is(Double.valueOf(expected)));
    }

    public static void assertThatWrapper(String actual, String expected) {
        assertThat(actual, is(expected));
    }

    public static void assertEqualsWrapper(boolean actual, boolean expected) {
        if (expected) {
            assertTrue("\n Expected [" + expected + "] But actual [" + actual + "]", actual);
        } else {
            assertFalse("\n Expected [" + expected + "] But actual [" + actual + "]", actual);
        }
    }

    public static void assertThatWrapper(int actual, int expected, String message) {
        assertThat(message, Integer.valueOf(actual), is(Integer.valueOf(expected)));
    }

    public static void assertThatWrapper(double actual, double expected, String message) {
        assertThat(message, Double.valueOf(actual), is(Double.valueOf(expected)));
    }

    public static void assertThatWrapper(long actual, long expected, String message) {
        assertThat(message, Long.valueOf(actual), is(Long.valueOf(expected)));
    }

    public static void assertThatWrapper(String actual, String expected, String message) {
        assertThat(message, actual, is(expected));
    }
}
