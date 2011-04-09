package com.google.code.p.keytooliui.ktl.swing.imageicon;

import java.io.File;

import com.google.code.p.keytooliui.shared.swing.imageicon.S_IINAbstract;
import com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared;

public class S_IINUI extends S_IINShared
{
    // trick to handle JavaWebStart's cache resources
    public S_IINUI() {}
    private static final S_IINUI _f_s_iin = new S_IINUI();
    
    
    
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strResourcePathRelativeSystem =
        "com" +
        File.separator +
        "google" +
        File.separator +
        "code" +
        File.separator +
        "p" +
        File.separator +
        "keytooliui" +
        File.separator +
        System.getProperty("_appli.name.short") +
        File.separator +
        "images" +
        File.separator +
        "";
    
    
    
    private static final String _f_s_strResourcePathRelativeJar =
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
        System.getProperty("_appli.name.short") +
        "/" +
        "images" +
        "/" +
        "";
    
    
    // -------------
    // PUBLIC STATIC
    
    public static javax.swing.ImageIcon s_get(String strFileName)
    {
        ClassLoader cld = _f_s_iin.getClass().getClassLoader();
        return S_IINAbstract._s_get_(cld, strFileName, _f_s_strResourcePathRelativeSystem, _f_s_strResourcePathRelativeJar);
    }
    
}