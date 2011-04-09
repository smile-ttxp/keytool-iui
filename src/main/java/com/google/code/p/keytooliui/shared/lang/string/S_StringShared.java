package com.google.code.p.keytooliui.shared.lang.string;


import java.io.File;
import java.util.StringTokenizer;

import com.google.code.p.keytooliui.shared.lang.MySystem;

public class S_StringShared
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.lang.string.S_StringShared.";
    
    // -------------
    // PUBLIC STATIC 
    
    /*
        eg: input = { "foo", "foo2", "foo3" }
        ==> output: "foo foo2 foo3"
        if any error, return nil
    */
    
    public static String s_get(String[] strs)
    {
        if (strs == null)
            return null;
            
        if (strs.length < 1)
            return null;
            
        String strCur = strs[0];
        
        if (strCur == null)
            return null;
            
        strCur = strCur.trim();
        
        if (strCur.length() < 1)
            return null;
        
        String str = new String(strCur);
        
        for (int i=1; i<strs.length; i++)
        {
            if (strs[i] == null)
                return null;
                
            strCur = strs[i].trim();
            
            if (strCur.length() < 1)
                return null;
                
            str += " ";
            str += strCur;
        }
        
        return str;
    }


    public static String[] s_getArrayFromStringSeparator(String str2Split, String strSeparator)
    {
        String strWhere = S_StringShared._f_s_strClass + "s_getArrayFromStringSeparator(str2Split, strSeparator)";
   
        if (str2Split==null || strSeparator==null)
        {
            MySystem.s_printOutError(strWhere, "nil arg");
            return null;
        }
        
        StringTokenizer stk = new StringTokenizer(str2Split, strSeparator);
        
        int intCountTokens = stk.countTokens();
        
        String[] strs = new String[intCountTokens];
        int i = 0;
        while (stk.hasMoreElements())
        {
            strs[i] = new String((String) stk.nextElement());
            i++;
        }
        
        return strs;
    }
    
    public static String s_getExtension(String str)
    {
        String strMethod = S_StringShared._f_s_strClass + "s_getExtension(str)";
        
        if (str == null)
            MySystem.s_printOutExit(strMethod, "nil str");
        
	    int i = str.lastIndexOf('.');
	        
	    if(i>0 && i<str.length()-1)
	    {
	        try
	        {
		        return str.substring(i+1);
	        }
	        
	        catch(IndexOutOfBoundsException excIndexOutOfBounds)
	        {
	            excIndexOutOfBounds.printStackTrace();
	            MySystem.s_printOutExit(strMethod, "excIndexOutOfBounds caught");
                
	        }
	    }
	    
	    return null;
    }
    
    
    /*
     IMPORTANT: the string's arg represents a system path
     EG:
      str=".\foo1\foo2\foo3\"
      ==>
        strsResult[0]="."
        strsResult[1]="foo1"
        strsResult[2]="foo2"
        strsResult[3]="foo3"
        
        if any error, returning nil
    */
    public static String[] s_getArrayFromStringFileSeparatorSystem(String str)
    {
        return S_StringShared.s_getArrayFromStringSeparator(str, File.separator);
    }
    
    
    /*
     IMPORTANT: the string's arg represents a java path
     EG:
      str="./foo1/foo2/foo3/"
      ==>
        strsResult[0]="."
        strsResult[1]="foo1"
        strsResult[2]="foo2"
        strsResult[3]="foo3"
        
        if any error, returning nil
    */
    public static String[] s_getArrayFromStringFileSeparatorJava(String str)
    {
        return s_getArrayFromStringSeparator(str, "/");
    }


}