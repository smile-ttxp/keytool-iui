package com.google.code.p.keytooliui.ktl.swing.editorpane;



import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.editorpane.*;

public class S_EPEUI extends S_EPEShared
{
    // trick to handle JavaWebStart's cache resources
    public S_EPEUI() {}
    final static private S_EPEUI _f_s_epe = new S_EPEUI();
    
    
    
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
        "htmls" +
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
