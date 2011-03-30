package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**
    known subclasses:
    . RBTypeSigPkcs7      *.p7b
    . RBTypeSigDer        *.??
    . RBTypeSigPem        *.??
    
    may be either enabled or disabled
    
 **/
  
 import java.awt.event.*;
 
 abstract public class RBTypeSigAbs extends RBTypeAbs
 {
    // ------
    // PUBLIC
    
    public String getFormatFile() { return this._strFormat; }
    
    // ---------
    // PROTECTED
    
    protected RBTypeSigAbs(
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
