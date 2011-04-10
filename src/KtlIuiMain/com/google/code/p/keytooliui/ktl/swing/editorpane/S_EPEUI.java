package com.google.code.p.keytooliui.ktl.swing.editorpane;

import java.io.File;

import com.google.code.p.keytooliui.shared.swing.editorpane.S_EPEAbs;
import com.google.code.p.keytooliui.shared.swing.editorpane.S_EPEShared;

public class S_EPEUI extends S_EPEShared
{
    // trick to handle JavaWebStart's cache resources
    public S_EPEUI() {}
    final static private S_EPEUI _f_s_epe = new S_EPEUI();
    
    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strResourcePathRelativeSystem =
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
        "htmls" +
        File.separator +
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
        System.getProperty("_appli.name.short") +
        "/" +
        "htmls" +
        "/" +
        "";
    
    
    // -------------
    // STATIC PUBLIC
    
    static public javax.swing.JEditorPane s_get(String strFileName)
    {
        ClassLoader cld = _f_s_epe.getClass().getClassLoader();
        return S_EPEAbs._s_get_(cld, strFileName, _f_s_strResourcePathRelativeSystem, _f_s_strResourcePathRelativeJar);
    }
    
}
