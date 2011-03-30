package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable, width = 10

    ... used to select a string
    eg: keypass, storepass, alias
    
 
**/


import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.*;


import java.awt.*;

final public class PSelBtnTfdStrW10DateBirth extends PSelBtnTfdStrW10
{
    final static private String _f_s_strLabel = "Date of birth (yyyymmdd):";
    
    // ------
    // PUBLIC
    
    public PSelBtnTfdStrW10DateBirth(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        Object objDocPropValue,
        boolean blnFieldRequired
        )
    {
        super(
            docListenerParent,
            frmParent, strTitleAppli, PSelBtnTfdStrW10DateBirth._f_s_strLabel, objDocPropValue, blnFieldRequired);
        
    }
    
    // ---------
    // PROTECTED
    
    /**
        called by superclass
        overwrite superclass method
        
        should be "yyyymmdd"
    **/
    protected boolean _validateText_(String str)
    {
        String strMethod = "_validateText_(str)";
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strText");
            return false;
        }
        
        
        if (! com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_isAllowedDateBirth(str))
        {
             MySystem.s_printOutWarning(this, strMethod, "failed");
             
            String strBody = "Date of birth value not allowed:";
            strBody += "\n";
            strBody += "  ";
            strBody += "\"";
            strBody += str;
            strBody += "\"";
                    
            strBody += "\n\n";
            strBody += com.google.code.p.keytooliui.ktl.util.filter.StringFilterUI.s_getRuleDateBirth();
                    
            OPAbstract.s_showDialogWarning(super._frmParent_, super._strTitleAppli_, strBody);
            return true;
        }
        
        super._tfdCurSelection_.setText(str);
        super._setSelectedValue_(true);
        super._btnClearSelection_.setEnabled(true);
        
        // ending
        return true;
    }
}
