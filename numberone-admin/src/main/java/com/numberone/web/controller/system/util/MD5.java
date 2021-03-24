package com.numberone.web.controller.system.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 已经迁移到Md5Utils了
 */
public class MD5 {
    // 加的盐
    private static final String SALT = "HXWcjvQWVG1wI4FQBLZpQ3pWj48AV63d";

    public static String EncoderByMd5(String buf) {
        try {
            MessageDigest digist = MessageDigest.getInstance("MD5");
            byte[] rs = digist.digest(buf.getBytes());
            StringBuffer digestHexStr = new StringBuffer();
            for (int i = 0; i < 16; i++) {
                digestHexStr.append(byteHEX(rs[i]));
            }
            return digestHexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("出现错误");
        }
        return null;

    }

    public static void main(String args[]) {

        System.out.println(MD5.encodeByMd5AndSalt("passsdfsword"));
        System.out.println("机器码:0256E33498242A10A9FB523996CFB7AB："+get12Char("0256E33498242A10A9FB523996CFB7AB"));
    }

    /**
     * 加盐的md5值。这样即使被拖库，仍然可以有效抵御彩虹表攻击
     *
     * @param inbuf 需做md5的字符串
     * @return
     */
    public static String encodeByMd5AndSalt(String inbuf) {

        return EncoderByMd5(EncoderByMd5(inbuf) + SALT);
    }

    public static String byteHEX(byte ib) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    /**
     * 获取短字符
     *
     * @param uuid
     * @return 大写
     */
    public static String get12Char(String uuid) {
        String arr[] = ShortText(uuid);
        String rst = (arr[0] + arr[1]).toUpperCase();
//        String res = rst.substring(0, 4) + "-" + rst.substring(4, 8) + "-" + rst.substring(8, 12);
        String res = rst.substring(0, 4) + rst.substring(4, 8) + rst.substring(8, 12);
        return res;
    }

    private static String[] ShortText(String string) {
        String key = "XuLiang"; // 自定义生成MD5加密字符串前的混合KEY
        String[] chars = new String[]{ // 要使用生成URL的字符
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String hex = MD5.encodeByMd5AndSalt(key + string);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] ShortStr = new String[4];

        for (int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for (int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars += chars[index];
                idx = idx >> 5;
            }
            ShortStr[i] = outChars;
        }

        return ShortStr;
    }

}
