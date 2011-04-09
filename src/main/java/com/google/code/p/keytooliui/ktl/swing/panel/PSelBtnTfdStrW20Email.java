package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable, width = 20

    ... used to select a string
    eg: keypass, storepass, alias
    
    
    known subclasses:
    . kst: PSelBtnTfdStrW20Email
**/


import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.lang.*;


import java.awt.*;

public final class PSelBtnTfdStrW20Email extends PSelBtnTfdStrW20
{
    private static final String _f_s_strLabel = "Email:";
    
    // ------
    // PUBLIC
    
    public PSelBtnTfdStrW20Email(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 

        Object objDocPropValue,
        boolean blnFieldRequired
        )
    {
        super(
            docListenerParent,
            frmParent, PSelBtnTfdStrW20Email._f_s_strLabel, objDocPropValue, blnFieldRequired);
        
    }
    
    // ---------
    // PROTECTED
    
    /**
        called by superclass
        overwrite superclass method
        
        should be [x]@[x].[x]
    **/
    protected boolean _validateText_(String str)
    {
        String strMethod = "_validateText_(str)";
        
        if (str == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strText");
            return false;
        }
        
        int intPosSpace = str.indexOf(" ");
        
        
        if (intPosSpace != -1)
        {
            MySystem.s_printOutWarning(this, strMethod, "intPosSpace != -1, intPosSpace=" + intPosSpace);
            // show rule message
            OPAbstract.s_showDialogWarning(this,
                    //"wrong email format",
                    str + "\ncontains space char\n\n eg right format: john.doe@acme.com");
            return true;
        }
        
        int intLength = str.length();
        
        if (intLength < 5)
        {
            MySystem.s_printOutWarning(this, strMethod, "intLength < 5, intLength=" + intLength);
            OPAbstract.s_showDialogWarning(this, 
                    //"wrong email format",
                    str + "\n\n eg right format: john.doe@acme.com");
            return true;
        }
        
        int intPosAt = str.indexOf("@");
        
        if (intPosAt<1 || intPosAt>intLength-1)
        {
            MySystem.s_printOutWarning(this, strMethod, "intPosAt<1 || intPosAt>intLength-1, intPosAt=" + intPosAt);
            OPAbstract.s_showDialogWarning(this, 
                    //"wrong email format",
                    str + "\n\n eg right format: john.doe@acme.com");
            return true;
        }
        
        String strSuffix = str.substring(intPosAt);
        
        int intLengthSuffix = strSuffix.length();
        
        if (intLengthSuffix < 3)
        {
            MySystem.s_printOutWarning(this, strMethod, "intLengthSuffix < 3, intLengthSuffix=" + intLengthSuffix);
            OPAbstract.s_showDialogWarning(this, 
                    //"wrong email format",
                    str + "\n\n eg right format: john.doe@acme.com");
            return true;
        }
        
        int intPosDot = strSuffix.indexOf(".");
        
        if (intPosDot<1 || intPosDot>intLengthSuffix-1)
        {
            MySystem.s_printOutWarning(this, strMethod, "intPosDot<1 || intPosDot>intLengthSuffix-1, intPosDot=" + intPosDot);
            OPAbstract.s_showDialogWarning(this, 
                    //"wrong email format",
                    str + "\n\n eg right format: john.doe@acme.com");
            return true;
        }
        
        super._tfdCurSelection_.setText(str);
        super._setSelectedValue_(true);
        super._btnClearSelection_.setEnabled(true);
        
        // ending
        return true;
    }
}