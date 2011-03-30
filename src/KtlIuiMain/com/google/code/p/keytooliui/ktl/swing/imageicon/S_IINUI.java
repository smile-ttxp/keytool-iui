package com.google.code.p.keytooliui.ktl.swing.imageicon;



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.imageicon.*;

public class S_IINUI extends S_IINShared
{
    // trick to handle JavaWebStart's cache resources
    public S_IINUI() {}
    final static private S_IINUI _f_s_iin = new S_IINUI();
    
    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strResourcePathRelativeSystem =
        "com" +
        MySystem.s_getFileSeparator() +
        "google" +
        MySystem.s_getFileSeparator() +
        "code" +
        MySystem.s_getFileSeparator() +
        "p" +
        MySystem.s_getFileSeparator() +
        "keytooliui" +
        MySystem.s_getFileSeparator() +
        com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strApplicationDir +
        MySystem.s_getFileSeparator() +
        "images" +
        MySystem.s_getFileSeparator() +
        "";
    
    
    
    final static private String _f_s_strResourcePathRelativeJar =
        "com" +
        "/" +
         "google" +
        "/" +
        "code" +
        "/" +
        "p" +
        "/" +
        "keytooliui" +
        "/" +
        com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strApplicationDir +
        "/" +
        "images" +
        "/" +
        "";
    
    
    // -------------
    // STATIC PUBLIC
    
    static public javax.swing.ImageIcon s_get(String strFileName)
    {
        ClassLoader cld = _f_s_iin.getClass().getClassLoader();
        return S_IINAbstract._s_get_(cld, strFileName, _f_s_strResourcePathRelativeSystem, _f_s_strResourcePathRelativeJar);
    }
    
}