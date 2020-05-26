package com.forum.utils;

public class ForumUserUtil
{

    public static int UserNum=10000;

    public boolean checkPasswordIllegal(String password)
    {
        return password.matches("^(?!\\d*$|[a-z]*$|[A-Z]*$|\\W*$)[!-~]{6,18}$");
    }

    public boolean checkPasswordSame(String pw1,String pw2)
    {
        return pw1.equals(pw2);
    }

}
