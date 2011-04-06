package com.google.code.p.keytooliui.ktl.lang.bool;


import javax.swing.ImageIcon;
import com.google.code.p.keytooliui.ktl.swing.imageicon.S_IINUI;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.lang.bool.*;


public class BOEntryPKTC extends BOAbs
{
    // ---------------------------
    // final static private String
    
    final static public String STR_IMAGEPK = "entry_pk.gif"; // key16x17.jpg";
    final static public String STR_IMAGETC = "entry_tc.gif"; //certificate16x17.jpg";
    
    // ------
    // public
    
    public boolean init() { return true; }
    public void destroy() {}
    
    public void setIsKey(boolean bln) 
    {
        String strMethod = "setIsKey(bln)";
        
        if (this._blnIsKey == bln)
            return;
        
        this._blnIsKey = bln; 
        
         if (this._blnIsKey)
            this._iin = S_IINUI.s_get(BOEntryPKTC.STR_IMAGEPK);
        else
            this._iin = S_IINUI.s_get(BOEntryPKTC.STR_IMAGETC);
        
        if (this._iin == null)
	    MySystem.s_printOutExit(this, strMethod, "this._iin == null, strIcon=" + "strIcon");
    }
    
    public boolean getIsKey() { return this._blnIsKey; }
    
    public BOEntryPKTC(
            boolean blnIsKey // else this is a certificate
            )
    {
        this._blnIsKey = blnIsKey;
        
        // !!! not sure there is a need for the following !!!
        if (this._blnIsKey)
            this._iin = S_IINUI.s_get(BOEntryPKTC.STR_IMAGEPK);
        else
            this._iin = S_IINUI.s_get(BOEntryPKTC.STR_IMAGETC);
        
        String strMethod = "BOEntryPKTC(...)";
        
        if (this._iin == null)
	    MySystem.s_printOutExit(this, strMethod, "this._iin == null, strIcon=" + "strIcon");
    }

    public ImageIcon getIcon() { 
        return this._iin; 
    }
    public String getText() { 
        return null; 
    }
    
    // -------
    // private
    
    private boolean _blnIsKey;
    private ImageIcon _iin = null;
}

