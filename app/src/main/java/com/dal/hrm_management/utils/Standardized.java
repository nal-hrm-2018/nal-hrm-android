package com.dal.hrm_management.utils;

public class Standardized {
    public static String standardizedText(String text){
        //Xóa bỏ những dấu cách thừa ở đầu và cuối
        text = text.trim();
        //Xóa bỏ dấu cách ở khúc giữa
        text = text.replaceAll("\\s+"," ");
        //Viết hoa chữ cái đầu
        String s = String.valueOf(text.charAt(0)).toUpperCase();
        text = text.substring(1);
        return s+text;
    }
}
