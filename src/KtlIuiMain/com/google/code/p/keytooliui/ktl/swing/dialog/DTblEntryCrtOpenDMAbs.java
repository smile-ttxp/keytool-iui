package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    "Crt" means "Certificate"
    
    "DM" ==> "Dummy", should be removed
**/


import com.google.code.p.keytooliui.ktl.swing.panel.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.awt.*;
import java.util.*;

abstract public class DTblEntryCrtOpenDMAbs extends DTblEntryCrtOpenAbs 
{        
    // ---------
    // PROTECTED
    
    
    
    protected DTblEntryCrtOpenDMAbs(
        Component cmpFrameOwner, 
    
        String strTitleThis,
        java.security.KeyStore kseLoaded
        )
    {
        super(
            cmpFrameOwner, 
         
            strTitleThis,
            kseLoaded
            ); 
            
        
        super._pnlContentsTextfields_.setLayout(new GridLayout(1, 2, 5, 5)); 
    }
    
    protected boolean _contentsOk_()
    {
        String strMethod = "_contentsOk_()";
        
        // check alias
        
        if (! super._contentsAliasOk_(
            false // blnSave, Save v/s Open (create v/s select)
            ))
            return false;
            
        // is alias pointing to valid certificate?
        boolean blnGotAlias = false;
        
        int intId = 0;
        
        for (; intId<super._strsAlias_.length; intId++) // memo: aliases are case-insensitive
        {
            if (super.getAlias().toLowerCase().compareTo(super._strsAlias_[intId].toLowerCase()) == 0)
            {
                blnGotAlias = true;
                break;
            }
        }
        
        if (! blnGotAlias)
            MySystem.s_printOutExit(this, strMethod, "! blnGotAlias");
            
        if (intId > (super._boosIsValidCrt_.length-1))
            MySystem.s_printOutExit(this, strMethod, "intId > (super._boosIsValidCrt_.length-1)");
        
        if (super._boosIsValidCrt_[intId].booleanValue() != true)
        {
            String strBody = "Alias not pointing to valid certificate:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += super.getAlias();
            strBody += "\"";
                    
            OPAbstract.s_showDialogWarning(this, strBody);
            return false;
        }

        return true;
    }
}
