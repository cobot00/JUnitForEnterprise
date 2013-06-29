package junit.reflection;

import java.lang.reflect.*;
import java.util.*;

/**
 * privateなフィールドに強制的に値、オブジェクトを変更する際に使用してください。
 * 
 * @author cobot00
 */

public class ForcedValueSetter {

    private ForcedValueSetter() {
        // コンストラクタの隠蔽
    }

    /**
     * Objectを参照として保持するフィールドに強制的に指定したオブジェクトをセットします。
     * 
     * @param aTargetObject
     * @param aFieldName
     * @param aValue
     */
    public static void setValue(Object aTargetObject, String aFieldName, Object aValue) {
        try {
            Field field = ForcedValueSetter.getField(aTargetObject, aFieldName);
            ForcedValueSetter.setObject(aTargetObject, field, aValue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Reflection Error");
        }
    }

    /**
     * intを値として保持するフィールドに強制的に指定したint値をセットします。
     * 
     * @param aTargetObject
     * @param aFieldName
     * @param aValue
     */
    public static void setValue(Object aTargetObject, String aFieldName, int aValue) {
        try {
            Field field = ForcedValueSetter.getField(aTargetObject, aFieldName);
            ForcedValueSetter.setIntValue(aTargetObject, field, aValue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Reflection Error");
        }
    }

    /**
     * longを値として保持するフィールドに強制的に指定したlong値をセットします。
     * 
     * @param aTargetObject
     * @param aFieldName
     * @param aValue
     */
    public static void setValue(Object aTargetObject, String aFieldName, long aValue) {
        try {
            Field field = ForcedValueSetter.getField(aTargetObject, aFieldName);
            ForcedValueSetter.setLongValue(aTargetObject, field, aValue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Reflection Error");
        }
    }

    /**
     * 指定されたprivateなMapインスタンスの指定されたkeyの値をvalueで上書きします。<br>
     * 
     * @param target
     * @param fieldName
     * @param key
     * @param value
     */
    public static void setValueToMap(Object target, String fieldName, Object key, Object value) {
        try {
            final Field field = ForcedValueSetter.getField(target, fieldName);
            field.setAccessible(true);

            try {
                @SuppressWarnings("unchecked")
                Map<Object, Object> map = (Map<Object, Object>)field.get(target);
                map.put(key, value);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Reflection Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Reflection Error");
        }
    }

    private static Field getField(Object aTargetObject, String aFieldName) throws NoSuchFieldException {
        return aTargetObject.getClass().getDeclaredField(aFieldName);
    }

    private static void setObject(Object aTargetObject, Field aField, Object aObject) throws IllegalAccessException {
        aField.setAccessible(true);
        aField.set(aTargetObject, aObject);
    }

    private static void setIntValue(Object aTargetObject, Field aField, int aIntValue) throws IllegalAccessException {
        aField.setAccessible(true);
        aField.setInt(aTargetObject, aIntValue);
    }

    private static void setLongValue(Object aTargetObject, Field aField, long aLongValue) throws IllegalAccessException {
        aField.setAccessible(true);
        aField.setLong(aTargetObject, aLongValue);
    }
}
