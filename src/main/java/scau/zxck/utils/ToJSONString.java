package scau.zxck.utils;

import org.junit.Test;

public class ToJSONString {
    public static String toJSON(String jsonStr){
        String r="";
        String str="";
        for(int i=0;i<jsonStr.length();i++){
            str+=jsonStr.charAt(i);
            if(jsonStr.charAt(i)==':'){
                if(str.charAt(2)=='i'&&str.charAt(3)=='s'){
                    r+=str;
                    if(jsonStr.charAt(i+2)=='t'){
                        r+="true";
                        i+=6;
                    }
                    else {
                        r+="false";
                        i+=7;
                    }
                }
                else {
                    String ss="";
                    if(str.matches("(.*)_id(.*)")&&str.length()>6){
//                        System.out.println("str="+str);
                        for(int k=0;k<str.length();k++){
                            if(str.charAt(k)!='_'){
                                ss+=str.charAt(k);
                            }
                            else {
                                ss+=str.charAt(k);
                                break;
                            }
                        }
                        str=ss.charAt(0)+ss.substring(1,2).toUpperCase()+ss.substring(2)+"PK"+"\""+":";
                    }
                    else if(str.matches("(.*)id(.*)")){
//                        System.out.println("strstr="+str);
                    }
                    else {
                        int flag=0;
                        for(int p=0;p<str.length();p++){
                            if(str.charAt(p)=='_'){
                                flag=p;
                                break;
                            }
                        }

//                        try {
                          if(flag!=0)  str=str.substring(0,1)+str.substring(1,2).toUpperCase()+str.substring(2,flag+1)+str.substring(flag+1,flag+2).toUpperCase()+str.substring(flag+2);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            System.out.println("jsonStr.length="+jsonStr.length());
//                            System.out.println("i="+i);
//                            System.out.println("flag="+flag);
//                            System.out.println(jsonStr.charAt(i)+" "+jsonStr.charAt(i+1));//+" "+jsonStr.charAt(i+2));
//                        }
                    }
                    r+=str;
                }
                str="";
            }
            else if(jsonStr.charAt(i)==','||jsonStr.charAt(i)=='}'){
                r+=str;
                str="";
            }
        }
        return r;
    }
    @Test
    public void test(){
//        String jsonStr="{\"isAdmin\":\"true\",\"admin_Password\":\"12345678\",\"admin_Cell\":\"\",\"admin_Name\":\"1\",\"admin_Email\":\"\"}";
//        System.out.println(jsonStr);
//        System.out.println(toJSON(jsonStr));
//        String jsonStr2="{\"id\":null,\"admin_password\":\"12345678\",\"admin_cell\":\"\",\"admin_name\":\"1\",\"admin_email\":\"\"}";
//        System.out.println(jsonStr2);
//        System.out.println(toJSON(jsonStr2));
//        String jsonStr3="{\"isAdmin\":\"true\",\"goods_info_id\":\"123456\",\"user_info_id\":\"123456\",\"id\":null,\"admin_Password\":\"12345678\",\"admin_Cell\":\"\",\"admin_Name\":\"1\",\"admin_Email\":\"\"}";
//        System.out.println(jsonStr3);
//        System.out.println(toJSON(jsonStr3));
        String jsonStr4="{\"id\":null,\"goods_PK\":null,\"goods_name\":null,\"goods_type\":6,\"goods_num\":0,\"goods_price\":0.0,\"goods_mark\":\"\\u0000\",\"goods_show\":\"\\u0000\",\"goods_picture\":null,\"goods_season\":0,\"goods_blossom\":null,\"goods_fruit\":null,\"goods_mature\":null,\"goods_expiration\":null,\"goods_reserve_1\":null,\"goods_reserve_2\":null,\"remark\":null}";
        System.out.println(jsonStr4);
        System.out.println(toJSON(jsonStr4));
    }
}
