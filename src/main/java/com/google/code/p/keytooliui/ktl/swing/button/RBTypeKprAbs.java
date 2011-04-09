package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**
    known subclasses:

    . RBTypeKprDer        *.der, .p8
    . RBTypeKprPem        *.pem
    
    may be either enabled or disabled
    
 **/
  
 import java.awt.event.*;
 
 public abstract class RBTypeKprAbs extends RBTypeAbs
 {
    // ------
    // PUBLIC
    
    public String getFormatFile() { return this._strFormat; }
    
    // ---------
    // PROTECTED
    
    protected RBTypeKprAbs(
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
