package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**
    known subclasses:
    . RBTypeCrtPkcs7      *.p7b
    . RBTypeCrtPkcs12     *.pfx ??
    . RBTypeCrtX509Der    *.cer PENDING
    . RBTypeCrtX509Base64 *.cer PENDING
    . RBTypeCrtDer        *.??
    . RBTypeCrtPem        *.??
    
    may be either enabled or disabled
    
 **/
  
 import java.awt.event.*;
 
 public abstract class RBTypeCrtAbs extends RBTypeAbs
 {
    // ------
    // PUBLIC
    
    public String getFormatFile() { return this._strFormat; }
    
    // ---------
    // PROTECTED
    
    protected RBTypeCrtAbs(
        boolean blnIsEnabled,
        ItemListener itmListenerParent,
        String strFormat,
        String[] strsExtension
        )
    {
        super(blnIsEnabled, itmListenerParent, strFormat, strsExtension);
        
        this._strFormat = strFormat;
    }
    
    // -------
    // PRIVATE
    
    private String _strFormat = null;
 }