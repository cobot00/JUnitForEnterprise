package junit.reflection;

import static junit.util.AssertWrapper.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

/**
 * ForcedValueSetterのテストクラス。
 * 
 * @author cobot_1
 * 
 */
public class ForcedValueSetterTest {

    /**
     * 動作検証用のやっつけクラス。
     * 
     * @author Kobori
     * 
     */
    private class TargetCalss {
        private int intValue;
        private long longValue;
        private Integer integerValue;
        private String stringValue;
        private Map<String, String> map = new HashMap<String, String>();

        private TargetCalss(int aInt, long aLong, Integer aInteger, String aString, String[][] aValues) {
            intValue = aInt;
            longValue = aLong;
            integerValue = aInteger;
            stringValue = aString;
            for (String[] each : aValues) {
                map.put(each[0], each[1]);
            }
        }

        private int getIntValue() {
            return intValue;
        }

        private long getLongValue() {
            return longValue;
        }

        private Integer getInteger() {
            return integerValue;
        }

        private String getString() {
            return stringValue;
        }

        private String getMapValue(String key) {
            return map.get(key);
        }
    }

    private static final String FIELD_INT_VALUE = "intValue";
    private static final String FIELD_LONG_VALUE = "longValue";
    private static final String FIELD_INTEGER_VALUE = "integerValue";
    private static final String FIELD_STRING_VALUE = "stringValue";

    private static final int INITIAL_INT_VALUE = 1;
    private static final long INITIAL_LONG_VALUE = 2;
    private static final int INITIAL_INTEGER_VALUE = 100;
    private static final String INITIAL_STRING_VALUE = "A";
    private static final String[][] VALUES = { { "1", "a" }, { "2", "b" }, { "3", "c" } };

    private TargetCalss target;

    @Before
    public void setUp() throws Exception {
        target = new TargetCalss(INITIAL_INT_VALUE, INITIAL_LONG_VALUE, new Integer(INITIAL_INTEGER_VALUE), "A", VALUES);
    }

    /**
     * int値が置き換わるかの確認。<br>
     * 他の変数に影響がないかも確認しています。
     */
    @Test
    public void setValueInt() {
        final int intValue = 10;
        ForcedValueSetter.setValue(target, FIELD_INT_VALUE, intValue);

        assertEquals(target.getIntValue(), intValue);
        assertEquals(target.getLongValue(), INITIAL_LONG_VALUE);
        assertEquals(target.getInteger(), Integer.valueOf(INITIAL_INTEGER_VALUE));
        assertEquals(target.getString(), INITIAL_STRING_VALUE);
    }

    /**
     * long値が置き換わるかの確認。<br>
     * 他の変数に影響がないかも確認しています。
     */
    @Test
    public void setValueLong() {
        final long longValue = 123;
        ForcedValueSetter.setValue(target, FIELD_LONG_VALUE, longValue);

        assertEquals(target.getIntValue(), INITIAL_INT_VALUE);
        assertEquals(target.getLongValue(), longValue);
        assertEquals(target.getInteger(), Integer.valueOf(INITIAL_INTEGER_VALUE));
        assertEquals(target.getString(), INITIAL_STRING_VALUE);
    }

    /**
     * Integerインスタンスが置き換わるかの確認。<br>
     * 他の変数に影響がないかも確認しています。
     */
    @Test
    public void setValueInteger() {
        final Integer integerValue = new Integer(65536);
        ForcedValueSetter.setValue(target, FIELD_INTEGER_VALUE, integerValue);

        assertEquals(target.getIntValue(), INITIAL_INT_VALUE);
        assertEquals(target.getLongValue(), INITIAL_LONG_VALUE);
        assertEquals(target.getInteger(), integerValue);
        assertEquals(target.getString(), INITIAL_STRING_VALUE);
    }

    /**
     * Stringインスタンスが置き換わるかの確認。<br>
     * 他の変数に影響がないかも確認しています。
     */
    @Test
    public void setValueString() {
        final String stringValue = "abcde";
        ForcedValueSetter.setValue(target, FIELD_STRING_VALUE, stringValue);

        assertEquals(target.getIntValue(), INITIAL_INT_VALUE);
        assertEquals(target.getLongValue(), INITIAL_LONG_VALUE);
        assertEquals(target.getInteger(), Integer.valueOf(INITIAL_INTEGER_VALUE));
        assertEquals(target.getString(), stringValue);
    }

    /**
     * Mapインスタンスが置き換わるかの確認。
     */
    @Test
    public void setValueMap() {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("a", "A1");
        map.put("b", "B2");
        map.put("c", "C3");

        ForcedValueSetter.setValue(target, "map", map);

        assertThatWrapper(target.getMapValue("a"), "A1");
        assertThatWrapper(target.getMapValue("b"), "B2");
        assertThatWrapper(target.getMapValue("c"), "C3");

        assertThatWrapper(target.getMapValue("1"), null);
        assertThatWrapper(target.getMapValue("2"), null);
        assertThatWrapper(target.getMapValue("3"), null);
    }

    /**
     * Mapインスタンスの指定した要素が置き換わるかの確認。
     */
    @Test
    public void setValueToMap() {
        ForcedValueSetter.setValueToMap(target, "map", "1", "A");
        ForcedValueSetter.setValueToMap(target, "map", "2", "B");
        ForcedValueSetter.setValueToMap(target, "map", "3", "C");
        ForcedValueSetter.setValueToMap(target, "map", "4", "D");

        assertThatWrapper(target.getMapValue("1"), "A");
        assertThatWrapper(target.getMapValue("2"), "B");
        assertThatWrapper(target.getMapValue("3"), "C");
        assertThatWrapper(target.getMapValue("4"), "D");
    }
}
