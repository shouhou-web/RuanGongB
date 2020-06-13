package com.forum.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostUtil {

    public static String AlterTimeFormat(String sqltime){
        try {
            SimpleDateFormat javaFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            SimpleDateFormat sqlFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date nowDate=new Date();
            //String now=javaFormat.format(nowDate);
            Date date=sqlFormat.parse(sqltime);
            //String time=javaFormat.format(date);

            long l=nowDate.getTime()-date.getTime();
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            long second=(l/1000-day*24*60*60-hour*60*60-min*60);
            if(day>3)
                return sqltime.substring(0,10);
            else if(day>0)
                return day+"天前";
            else if(hour>0)
                return hour+"小时前";
            else if(min>2)
                return min+"分钟前";
            else
                return "刚刚";
        }
        catch (Exception e){
            e.printStackTrace();
            return "time error";
        }
    }

    public static String AlterNumFormat(int num){
        try {
            StringBuilder s=new StringBuilder();
            if(num<1000)
                return Integer.toString(num);
            else if(num<10000){
                int a=num/1000;
                int b=num/100%10;
                s.append(a);
                s.append(".");
                s.append(b);
                s.append("k");
            }
            else{
                int a=num/10000;
                int b=num/1000%10;
                s.append(a);
                s.append(".");
                s.append(b);
                s.append("w");
            }
            return s.toString();
        }
        catch (Exception e){
            System.out.println("数字转换异常");
            e.printStackTrace();
            return "null";
        }
    }

    public static String getPreviewContent(String content){
        try {
            StringBuilder previewContent=new StringBuilder();
            boolean flag=false;
            for(int i=0;i<content.length()&&previewContent.length()<=40;i++){
                char c=content.charAt(i);
                if(c=='<')
                    flag=false;
                if(flag)
                    previewContent.append(c);
                if(c=='>')
                    flag=true;
            }
            return previewContent.toString();
        }
        catch (Exception e){
            System.out.println("预览转换发生了异常");
            e.printStackTrace();
            return "预览出错了QAQ";
        }
    }
}
