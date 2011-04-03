package com.google.code.p.keytooliui.shared.lang.string;


import java.io.File;
import java.util.StringTokenizer;

import com.google.code.p.keytooliui.shared.lang.MySystem;

public class S_StringShared
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.lang.string.S_StringShared.";
    
    // -------------
    // STATIC PUBLIC 
    
    /*
        eg: input = { "foo", "foo2", "foo3" }
        ==> output: "foo foo2 foo3"
        if any error, return nil
    */
    
    static public String s_get(String[] strs)
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
    
    /**
        EG:
            strFolderAncestor = "D:\foo1\foo2\foo3"
            fle.getAbsolutePath() = "D:\foo1\foo2\foo3\foo4\foo5\foo6.gif"
            
            ==> strResult = "foo4\foo5\"
    
    **/
    
    static public String s_getFileRelativeLocation(String strFolderAncestor, java.io.File fle)
    {
        String strWhere = S_StringShared._f_s_strClass + "s_getFileRelativeLocation(strFolderAncestor, fle)";
       
        String strFolderTarget = fle.getParent();
        
        String strResult = S_StringShared._s_getFolderRelativeLocation(strFolderAncestor, strFolderTarget);
        
        if (strResult == null)
        {
            MySystem.s_printOutError(strWhere, "failed");
            return null;   
        }
        
        return strResult;
    }
    
    
    
    static public String[] s_getArrayFromStringSeparator(String str2Split, String strSeparator)
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
    
    static public String s_getExtension(String str)
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
    static public String[] s_getArrayFromStringFileSeparatorSystem(String str)
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
    static public String[] s_getArrayFromStringFileSeparatorJava(String str)
    {
        return s_getArrayFromStringSeparator(str, "/");
    }
    
    
    
    /**
    transforming a color to a string,
    EG: input: col = (120, 40, 255)
    output = String = "120:40:255"
    
    IMPORTANT: if nil col, then output = _void_
    (w/o braces)
    
    **/
    
    /**
    static public String s_get(java.awt.Color col)
    {    
        if (col == null)
            return com.google.code.p.keytooliui.shared.Shared.f_s_strValueNil;
        
        // no nil
        String str = "\"";
            
        str += col.getRed();
        str += ":";
        str += col.getGreen();
        str += ":";
        str += col.getBlue();
            
        str += "\"";
        
        return str;
    }
    **/
    
    // --------------
    // STATIC PRIVATE
    
    /**
        EG:
            strFolderAncestor = "D:\foo1\foo2\foo3"
            strFolderTarget = "D:\foo1\foo2\foo3\foo4\foo5\foo6\"
            
            ==> strResult = "foo4\foo5\foo6\"
    
        WARNING: if the same, returning :"", instead of for ex. in Windows: "\"
    **/
    
    static private String _s_getFolderRelativeLocation(String strFolderAncestor, String strFolderTarget)
    {
        String strWhere = S_StringShared._f_s_strClass + "_s_getFolderRelativeLocation(strFolderAncestor, strFolderTarget)";
    
        if (strFolderAncestor==null || strFolderTarget==null)
        {
            MySystem.s_printOutError(strWhere, "nil arg");
            return null;
        }
    
        if (! strFolderTarget.startsWith(strFolderAncestor))
        {
            MySystem.s_printOutError(strWhere, "wrong strFolderTarget: " + strFolderTarget);
            return null;
        }
        

        String strResult = new String("");
        String strResultSuffix = new String(strFolderTarget);
        
        // ??????????????????????? compareTo instead ?????????????????
        if (! strResultSuffix.equals(strFolderAncestor))
        {
            int intIndex = strFolderAncestor.length();
            intIndex ++;
		    
		    try
		    {
		        strResultSuffix = strFolderTarget.substring(intIndex);
	        }
	        
	        catch(IndexOutOfBoundsException excIndexOutOfBounds)
	        {
	            excIndexOutOfBounds.printStackTrace();
	            MySystem.s_printOutError(strWhere, "excIndexOutOfBounds caught, strFolderTarget=" + strFolderTarget);
                return null;
	        }
	        
	        strResult += strResultSuffix;
        }
        
        // modif may 5, 2000
        // strResult += com.google.code.p.keytooliui.shared.lang.File.separator;
        
        return strResult;
    
    }
}