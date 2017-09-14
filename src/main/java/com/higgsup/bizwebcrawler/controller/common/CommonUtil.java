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
     public String cutID(String get) {
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
    public String cutQuantity(String get) {
        String strResult = "";
        for (int i = 0; i<get.length(); i++) {
            if (Character.isDigit(get.charAt(i))) {
                strResult = strResult + get.charAt(i);
            } else {
                break;
            }
        }
        return strResult;
    }    public String cutNumberToCharStop(String get) {
        String strResult ="";
        for (int i = 0; i<get.length(); i++) {
            if (Character.isDigit(get.charAt(i))) {
                strResult +=get.charAt(i);
            } else {
               if(strResult.length()>1){
                   break;
               }
            }
        }
        return strResult;
    }
    public String takeMoneyInString(String strMoney) {
        String strResult ="";
        for (int i = 0; i<strMoney.length(); i++) {
            if (Character.isDigit(strMoney.charAt(i))) {
                strResult += strMoney.charAt(i);
            }
        }
        return strResult;
    }
    public String takeNumberHaveDotInString(String strMoney) {
        Pattern p = Pattern.compile("[\\d]+.[\\d]+");
        Matcher m = p.matcher(strMoney);
        String NumberMoney = "";
        while (m.find()) {
            NumberMoney += m.group();
        }
        return NumberMoney;
    }

    public String fomatDateSQL(String date) throws ParseException {

         date=date.replaceAll("/","-");
        date=date.substring(0,16);
        System.out.println(date+" cat");
        SimpleDateFormat formatDateInput = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat formatDateUot = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = formatDateInput.parse(date);
        return formatDateUot.format(date1);
    }
    public String cutDateSQL(String date){
        System.out.println(date+ "  vv");
        date=date.replaceAll("/","-");
        date=date.substring(0,16);
     return date;
    }
}
