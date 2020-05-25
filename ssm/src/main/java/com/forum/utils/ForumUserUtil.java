package com.forum.utils;

public class ForumUserUtil {
    public static int UserNum=10000;
    public static boolean checkPasswordIllegal(String password){
        return password.matches("^(?!\\d*$|[a-z]*$|[A-Z]*$|\\W*$)[!-~]{6,18}$");
    }
}
