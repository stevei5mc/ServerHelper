package cn.stevei5mc.serverhelper.common.utils;

public class CommonUtils {

    /**
     * String ==> String[]
     * @param string Input string
     * @return String[]
     */
    public static String[] toArray(String string) {
        return new String[]{string};
    }

    /**
     * String ==> String[]
     * Split lines ( "\n" split )
     * @param string Input string
     * @return String[]
     */
    public static String[] splitLines(String string) {
        return string.split("\n");
    }
}