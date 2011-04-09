package com.google.code.p.keytooliui.shared.swing.dialog;

/*

    known subclasses:
    . DWChoice3DNameMiss


/** WARNING! neither init nor destroy!

DWClip means Dialog Warning Clip load

*/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.beans.*; //Property change stuff
import java.awt.*;

abstract public class DWChoice3Abs extends DWChoiceAbs 
{
    // -----------------------
    // PUBLIC STATIC FINAL INT
    
    public static final int f_s_intYes = 1;
    public static final int f_s_intYesAllways = 2;
    public static final int f_s_intClose = 3;
    
   
    
    // --------------
    // PRIVATE STATIC 
    
    private static final String _f_s_strBodyQuestion = "Continue anyway?";
    private static final String _f_s_strBtnOk = "OK"; // "YES";
    private static final String _f_s_strBtnOkAllways = "OK, dont't ask again"; 
    private static final String _f_s_strBtnClose = "Cancel";
    
    
    // ------------------
    // STATIC INITIALIZER
    private static Object[] _s_objsOption = new String[3];
    
    static
    {        
        _s_objsOption[0] = DWChoice3Abs._f_s_strBtnOk;
        _s_objsOption[1] = DWChoice3Abs._f_s_strBtnOkAllways;
        _s_objsOption[2] = DWChoice3Abs._f_s_strBtnClose;
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
        
        if (objValue.equals(DWChoice3Abs._f_s_strBtnOk))
        {
            super._intValue_ = f_s_intYes;
            super._cancel_();
            
            return;
        }
        
        if (objValue.equals(DWChoice3Abs._f_s_strBtnOkAllways))
        { 
            super._intValue_ = f_s_intYesAllways;
            super._cancel_();
            
            return;
        }
        
        if (objValue.equals(DWChoice3Abs._f_s_strBtnClose))
        { 
            super._intValue_ = f_s_intClose;
            super._cancel_();
            
            return;
        }
        
        MySystem.s_printOutExit(this, strMethod, "unknown objValue:" + objValue);
        
    }
    
    // ---------
    // PROTECTED
        
    protected DWChoice3Abs(
        Frame frm,
     
        String strBodyWhat)
    {
        super(
            frm,
     
            
           strBodyWhat + 
                "\n\n" + 
                DWChoice3Abs._f_s_strBodyQuestion,
            
            DWChoice3Abs._s_objsOption
            );
        
        
        
        super._intValue_ = DWChoice3Abs.f_s_intClose;
    }  
}