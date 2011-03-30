package com.google.code.p.keytooliui.ktl.swing.toolbar;

/**
    a toolbar that contains imageButtons:
    . _DEFAULT_ minus buttonPrint?

**/


import com.google.code.p.keytooliui.ktl.swing.button.*;

import javax.swing.*;

import com.google.code.p.keytooliui.shared.swing.toolbar.*;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;

abstract public class TBMainUIAbs extends TBMainAbstract
{
    // -------------------
    // final static public
    
    final static public boolean f_s_blnIsFloatable = true;
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
        }
        
        //super._btnPrint_.setEnabled(false);
        
        // ------------
        // add children
        
        if (! _addTools())
	    {
	        MySystem.s_printOutError(this, strMethod, "failed");
	        return false;
	    }
	    
        
        // ------
        // ending
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected TBMainUIAbs(
        java.awt.event.ActionListener actListenerParentAppli,
        //javax.help.HelpBroker hbrHelpStandard,
        javax.swing.ImageIcon iinFrameFloatable,
        String strTitleAppli)
    {
        super(
            (String) null, // strHelpId
            actListenerParentAppli,
            //hbrHelpStandard,
            SwingConstants.HORIZONTAL,
            
            iinFrameFloatable, // not in use for now
            
            true, // blnDoHelpOnItem
            TBMainUIAbs.f_s_blnIsFloatable
            );
            
        
         setName(strTitleAppli + " - " + "Main toolbar"); // in case of floatable toolbar
         
         this._btnKstView = new BESView24(actListenerParentAppli);
         this._btnKstTool = new BESTool24(actListenerParentAppli);
         
      
    }
    
    
    
    
    // -------
    // PRIVATE
    
    private BEnabledState _btnKstView;
    private BEnabledState _btnKstTool;
    
    private boolean _addTools()
    {
        String strMethod = "_addTools()";
        
        //super._addSeparator_();
        
        add(this._btnKstView);
        add(this._btnKstTool);
        
        if (super._btnPrint_ != null)
            add(super._btnPrint_);
        
        //super._addSeparator_();
        
        if (! super._addButtonHelp_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._btnAboutAppli_ != null)
        {
            if (! super._addButtonAboutAppli_())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            } 
        }
        
        if (! super._addButtonExit_())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
     
        return true;
    }
}