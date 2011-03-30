package com.google.code.p.keytooliui.ktl.swing.dialog;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;

public class DTblsKstSelPKOpen extends DTblsKstSelPKAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
   
    //final static private String _f_s_strTitleThisSuffix = "keystore";
    final static private String _STR_BODYBUTTONUSAGE = "Usage:\n  Mouse-click  valid candidate row to select respective entry's alias,\n  next enter respective password,\n  then click \"OK\" button.";
    final static private String _STR_TEXTLABELALIAS = "Selected entry's alias:";
    
    final static private String _f_s_strLabelPasswdRespective = "Enter respective password:";
    
    // ------
    // public
    
    public char[] getPassword() { return this._chrsPassword_; }
    
    public DTblsKstSelPKOpen(
        Component cmpFrameOwner, 
        String strTitleAppli,
        java.security.KeyStore kseLoaded,
        String strPathAbs,
        String strTitleSuffix
            )
    {
        super(
                cmpFrameOwner, 
                strTitleAppli, 
                strTitleSuffix,
                kseLoaded,
                strPathAbs,
                DTblsKstSelPKOpen._STR_BODYBUTTONUSAGE,
                DTblsKstSelPKOpen._STR_TEXTLABELALIAS,
                false // blnSave (v/s Open) !!!!!!!!!!!!! always false !!!!!!!!!
                );
        
        this._lblEnterRespective = new JLabel(DTblsKstSelPKOpen._f_s_strLabelPasswdRespective);
        
        this._pfdEnterRespective = new JPasswordField(12);
        
        this._pfdEnterRespective.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        
        super._pnlContentsTextfields_.setLayout(new GridLayout(2, 2, 5, 5)); 
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        // ----
        
        super._pnlContentsTextfields_.add(this._lblEnterRespective);
        super._pnlContentsTextfields_.add(this._pfdEnterRespective);

        return true;
    }
    
    // ---------
    // protected
    
    protected char[] _chrsPassword_ = null;
    
    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        // check alias
        
        if (! super._contentsAliasOk_(
            //false // blnSave, Save v/s Open (create v/s select)
            ))
            return false;
            
        // is alias pointing to valid key?
        boolean blnGotAlias = false;
        
        int intId = 0;
        
        for (; intId<super._strsAliasPKTC_.length; intId++) // memo: aliases are case-insensitive
        {
            if (super.getAlias().toLowerCase().compareTo(super._strsAliasPKTC_[intId].toLowerCase()) == 0)
            {
                blnGotAlias = true;
                break;
            }
        }
        
        if (! blnGotAlias)
            MySystem.s_printOutExit(this, strMethod, "! blnGotAlias");
            
        if (intId > (super._boosIsTCEntryPKTC_.length-1))
            MySystem.s_printOutExit(this, strMethod, "intId > (super._boosIsTCEntryPKTC_.length-1)");
        
        if (super._boosIsTCEntryPKTC_[intId].booleanValue() != false)
        {
            String strBody = "Alias not pointing to valid key:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += super.getAlias();
            strBody += "\"";
                    
            OPAbstract.s_showDialogWarning(this, getTitle(), strBody);
            return false;
        }
        
        
        if (true /*super._blnIsPassword_*/) // should be true
        {
            // check password
            
            String strEnterRespective = new String(this._pfdEnterRespective.getPassword());
            
            if (strEnterRespective.length() < 1)
            {
                OPAbstract.s_showDialogWarning(this, getTitle(), "Please enter respective password");
                
                return false;
            }
            
            
            // is password corrrect?
   
            if (com.google.code.p.keytooliui.ktl.util.jarsigner.UtilKstAbs.s_getKey(
                this, 
                getTitle(),
                super._kstOpen_,
                super.getAlias(),
                strEnterRespective.toCharArray()) == null)
            {
                return false;
            }

            this._chrsPassword_ = strEnterRespective.toCharArray();
        
        }
        
        return true;
    }
    
    // -------
    // private
    
    private JLabel _lblEnterRespective = null;
    
    private JPasswordField _pfdEnterRespective = null;
}  
      
