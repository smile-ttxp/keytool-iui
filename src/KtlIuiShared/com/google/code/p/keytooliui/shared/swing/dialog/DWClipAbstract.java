package com.google.code.p.keytooliui.shared.swing.dialog;

/*

    known subclasses:
    . DWClipIllegalArgument,
    . DWClipLineUnavailable,
    . DWClipSecurity,
    . DWClipUnsupportedSndfxFile,
    . DWClipIO,
    . DWClipFileNotFound.


/** WARNING! neither init nor destroy!

DWClip means Dialog Warning Clip load

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.beans.*; //Property change stuff
import java.awt.*;

abstract public class DWClipAbstract extends DWChoiceAbs 
{
    // -----------------------
    // FINAL STATIC PUBLIC INT
    
    final static public int f_s_intYes = 1;
    final static public int f_s_intYesAll = 2;
    final static public int f_s_intClose = 3;
    
   
    
    // --------------
    // STATIC PRIVATE 
    
    static private String _s_strBodyWhat = null;
    static private String _s_strBodyWhyPrefix = null;
    static private String _s_strBodyQuestion = null;
    static private String _s_strButtonTextYes = null; // "YES";
    static private String _s_strButtonTextYesAll = null; //"YES FOR ALL";
    static private String _s_strButtonTextClose = null; //"CLOSE";
    
    static private Object[] _s_objsOption = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DWClipAbstract" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";   
        String strClass = "com.google.code.p.keytooliui.shared.swing.dialog.DWClipAbstract.";
    
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
            _s_strBodyWhat = rbeResources.getString("bodyWhat");   
            _s_strBodyWhyPrefix = rbeResources.getString("bodyWhyPrefix");   
            _s_strBodyQuestion = rbeResources.getString("bodyQuestion");   
	        _s_strButtonTextYes = rbeResources.getString("buttonTextYes");     
	        _s_strButtonTextYesAll = rbeResources.getString("buttonTextYesAll"); 
	        _s_strButtonTextClose = rbeResources.getString("buttonTextClose"); 
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strClass, strBundleFileLong + ", excMissingResource caught");
        }
        
        // --
        _s_objsOption = new String[3];
        _s_objsOption[0] = _s_strButtonTextYes;
        _s_objsOption[1] = _s_strButtonTextYesAll;
        _s_objsOption[2] = _s_strButtonTextClose;
    }
    
    // ------
    // PUBLIC
        
    
    public void propertyChange(PropertyChangeEvent evtPropertyChange)
    {
        String strMethod = "propertyChange(evtPropertyChange)";
  
        if (! (evtPropertyChange.getSource() instanceof OPInputWarning))
            MySystem.s_printOutExit(this, strMethod, "wrong source");     
            
        if (! isVisible())
            return;
        
        if (evtPropertyChange.getPropertyName().toLowerCase().compareTo("value") != 0)
        {
            //MySystem.s_printOutTrace(this, strMethod, "evtPropertyChange.getPropertyName().toLowerCase().compareTo(\"value\") != 0,\n evtPropertyChange.getPropertyName()=" + evtPropertyChange.getPropertyName());
            return;
        }  
        
        
        OPInputWarning opn = (OPInputWarning) evtPropertyChange.getSource();
        
        
        Object objValue = opn.getValue();
        
        if (objValue == OPInputWarning.UNINITIALIZED_VALUE)
        {
            MySystem.s_printOutTrace(this, strMethod, "objValue == OPInputWarning.UNINITIALIZED_VALUE, objValue:" + objValue);
            return;
        }
        
        if (objValue.equals(_s_strButtonTextYes))
        {
            super._intValue_ = f_s_intYes;
            super._cancel_();
            
            return;
        }
        
        if (objValue.equals(_s_strButtonTextYesAll))
        { 
            super._intValue_ = f_s_intYesAll;
            super._cancel_();
            
            return;
        }
        
        if (objValue.equals(_s_strButtonTextClose))
        { 
            super._intValue_ = f_s_intClose;
            super._cancel_();
            
            return;
        }
        
        MySystem.s_printOutExit(this, strMethod, "unknown objValue:" + objValue);
        
    }
    
    // ---------
    // PROTECTED
        
    protected DWClipAbstract(
        Frame frm,
        String strTitleApplication,
        String strPathRelative,
        String strBodyWhySuffix)
    {
        super(
            frm,
            strTitleApplication, 
            _s_strBodyWhat + "\n" + strPathRelative + "\n\n" + _s_strBodyWhyPrefix + " " + strBodyWhySuffix + "\n\n" + _s_strBodyQuestion,
            _s_objsOption
            );
        
        
        
        super._intValue_ = f_s_intClose;
    }  
}