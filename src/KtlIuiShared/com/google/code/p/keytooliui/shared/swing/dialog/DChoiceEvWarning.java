/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;



import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.beans.*; //Property change stuff


final public class DChoiceEvWarning extends DChoiceEvAbs 
{
    // -----------------------
    // FINAL STATIC PUBLIC INT
    
    final static public int f_s_intInstall = 1;
    final static public int f_s_intQuit = 2;
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strTitleSuffix = null;
    static private String _s_strBodyWhat1 = null;
    static private String _s_strBodyWhat2 = null;
    static private String _s_strButtonTextInstall = null; // "YES";
    static private String _s_strButtonTextQuit = null; //"CLOSE";
    
    
    static private Object[] _s_objsOption = null;
    
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DChoiceEvWarning" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";   
        String strClass = "com.google.code.p.keytooliui.shared.swing.dialog.DChoiceEvWarning.";
    
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
            DChoiceEvWarning._s_strTitleSuffix = rbeResources.getString("titleSuffix");   
            
            DChoiceEvWarning._s_strBodyWhat1 = rbeResources.getString("bodyWhat1");   
            DChoiceEvWarning._s_strBodyWhat2 = rbeResources.getString("bodyWhat2");   
	        DChoiceEvWarning._s_strButtonTextInstall = rbeResources.getString("buttonTextInstall");     
	        DChoiceEvWarning._s_strButtonTextQuit = rbeResources.getString("buttonTextQuit"); 
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strClass, strBundleFileLong + ", excMissingResource caught");
        }
        
        // --
        DChoiceEvWarning._s_objsOption = new String[2];
        DChoiceEvWarning._s_objsOption[0] = DChoiceEvWarning._s_strButtonTextInstall;
        DChoiceEvWarning._s_objsOption[1] = DChoiceEvWarning._s_strButtonTextQuit;
    }
    
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
            //MySystem.s_printOutTrace(this, strMethod, "objValue == OPInputWarning.UNINITIALIZED_VALUE, objValue:" + objValue);
            return;
        }
        
        if (objValue.equals(DChoiceEvWarning._s_strButtonTextInstall))
        {
            //MySystem.s_printOutTrace(this, strMethod, "objValue == DChoiceEvWarning._s_strButtonTextInstall, objValue:" + objValue);
            
            super._intValue_ = DChoiceEvWarning.f_s_intInstall;
            super._cancel_();
            
            return;
        }
        
        if (objValue.equals(DChoiceEvWarning._s_strButtonTextQuit))
        { 
            //MySystem.s_printOutTrace(this, strMethod, "objValue == DChoiceEvWarning._s_strButtonTextQuit, objValue:" + objValue);
            super._intValue_ = DChoiceEvWarning.f_s_intQuit;
            super._cancel_();
            
            return;
        }
        
        MySystem.s_printOutExit(this, strMethod, "unknown objValue:" + objValue);
    }
    
    public DChoiceEvWarning(
        String strTitleAppli,
        String strAllowedEval)
    {
        super(
            strTitleAppli,
            DChoiceEvWarning._s_strTitleSuffix
            );
            
        

        super._intValue_ = DChoiceEvWarning.f_s_intQuit;
    
        String strBody = DChoiceEvWarning._s_strBodyWhat1;
        strBody += " ";
        strBody += strAllowedEval;
        strBody += "\n\n";
        strBody += DChoiceEvWarning._s_strBodyWhat2;
        strBody += " ";
        strBody += strTitleAppli;
        strBody += "\n\n";
        strBody += com.google.code.p.keytooliui.shared.swing.panel.PHelpAboutAppli.s_strContactPoints;
        strBody += "\n\n";
        
        
        Object[] objsArray = { strBody };
        
        super._opn_ = new OPInputWarning(objsArray, DChoiceEvWarning._s_objsOption);
    }
}