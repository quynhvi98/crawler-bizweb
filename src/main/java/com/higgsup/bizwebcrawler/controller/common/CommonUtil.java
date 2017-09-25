package com.higgsup.bizwebcrawler.controller.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by viquynh on 26/07/2017.
 */
public class CommonUtil { //class chứa những hàm xử lí nhỏ
    public static final String DATE = "dd-MM-yyyy HH:mm";

    public static  String cutID(String get) {
        String strResult = "";
        for (int i = get.length() - 1; 0 <= i; i--) {
            if (Character.isDigit(get.charAt(i))) {
                strResult = get.charAt(i) + strResult;
            } else {
                break;
            }
        }
        return strResult;
    }

    public static int cutQuantity(String get) {
        String strResult = "";
        for (int i = 0; i < get.length(); i++) {
            if (Character.isDigit(get.charAt(i))) {
                strResult = strResult + get.charAt(i);
            } else {
                break;
            }
        }
        if (strResult != "") {
            return Integer.parseInt(strResult);
        }
        return 0;
    }

    public static String cutNumberToCharStop(String get) {
        String strResult = "";
        for (int i = 0; i < get.length(); i++) {
            if (Character.isDigit(get.charAt(i))) {
                strResult += get.charAt(i);
            } else {
                if (strResult.length() > 1) {
                    break;
                }
            }
        }
        return strResult;
    }

    public static String takeMoneyInString(String strMoney) {
        String strResult = "";
        for (int i = 0; i < strMoney.length(); i++) {
            if (Character.isDigit(strMoney.charAt(i))) {
                strResult += strMoney.charAt(i);
            }
        }
        return strResult;
    }

    public static String fomatDateSQL(String date) throws ParseException {
        date = date.replaceAll("/", "-");
        date = date.substring(0, 16);
        SimpleDateFormat formatDateInput = new SimpleDateFormat(DATE);
        SimpleDateFormat formatDateUot = new SimpleDateFormat(DATE);
        Date date1 = formatDateInput.parse(date);
        return formatDateUot.format(date1);
    }

    public static String cutDateSQL(String date) {
        System.out.println(date + "  vv");
        date = date.replaceAll("/", "-");
        date = date.substring(0, 16);
        return date;
    }
}
