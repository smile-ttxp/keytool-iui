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

/*


/** 


*/

import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.beans.*; //Property change stuff


public final class DChoiceEvQuestion extends DChoiceEvAbs 
{
    // -----------------------
    // PUBLIC STATIC FINAL INT
    
    public static final int f_s_intInstall = 1;
    public static final int f_s_intContinue = 2;
    
    // --------------
    // PRIVATE STATIC
    
    private static String _s_strTitleSuffix = null;
    private static String _s_strBodyWhat = null;
    private static String _s_strButtonTextInstall = null; // "YES";
    private static String _s_strButtonTextContinue = null; //"CLOSE";
    
    
    private static Object[] _s_objsOption = null;
    
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DChoiceEvQuestion" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";   
        String strClass = "com.google.code.p.keytooliui.shared.swing.dialog.DChoiceEvQuestion.";
    
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
            DChoiceEvQuestion._s_strTitleSuffix = rbeResources.getString("titleSuffix");   
            
            DChoiceEvQuestion._s_strBodyWhat = rbeResources.getString("bodyWhat");     
	        DChoiceEvQuestion._s_strButtonTextInstall = rbeResources.getString("buttonTextInstall");     
	        DChoiceEvQuestion._s_strButtonTextContinue = rbeResources.getString("buttonTextContinue"); 
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strClass, strBundleFileLong + ", excMissingResource caught");
        }
        
        // --
        DChoiceEvQuestion._s_objsOption = new String[2];
        
        DChoiceEvQuestion._s_objsOption[0] = DChoiceEvQuestion._s_strButtonTextContinue;
        DChoiceEvQuestion._s_objsOption[1] = DChoiceEvQuestion._s_strButtonTextInstall;
    }
    
    public void propertyChange(PropertyChangeEvent evtPropertyChange)
    {
        String strMethod = "propertyChange(evtPropertyChange)";
        
        if (! (evtPropertyChange.getSource() instanceof OPInputQuestion))
            MySystem.s_printOutExit(this, strMethod, "wrong source"); 
            
        if (! isVisible())
            return;
            
        if (evtPropertyChange.getPropertyName().toLowerCase().compareTo("value") != 0)
        {
            //MySystem.s_printOutTrace(this, strMethod, "evtPropertyChange.getPropertyName().toLowerCase().compareTo(\"value\") != 0,\n evtPropertyChange.getPropertyName()=" + evtPropertyChange.getPropertyName());
            return;
        } 
        
        OPInputQuestion opn = (OPInputQuestion) evtPropertyChange.getSource();
        
        
        Object objValue = opn.getValue();
        
        if (objValue == OPInputQuestion.UNINITIALIZED_VALUE)
        {
            //MySystem.s_printOutTrace(this, strMethod, "objValue == OPInputQuestion.UNINITIALIZED_VALUE, objValue:" + objValue);
            return;
        }
        
        if (objValue.equals(DChoiceEvQuestion._s_strButtonTextInstall))
        {
            //MySystem.s_printOutTrace(this, strMethod, "objValue == DChoiceEvQuestion._s_strButtonTextInstall, objValue:" + objValue);
            
            super._intValue_ = DChoiceEvQuestion.f_s_intInstall;
            super._cancel_();
            
            return;
        }
        
        if (objValue.equals(DChoiceEvQuestion._s_strButtonTextContinue))
        { 
            //MySystem.s_printOutTrace(this, strMethod, "objValue == DChoiceEvQuestion._s_strButtonTextContinue, objValue:" + objValue);
            super._intValue_ = DChoiceEvQuestion.f_s_intContinue;
            super._cancel_();
            
            return;
        }
        
        MySystem.s_printOutExit(this, strMethod, "unknown objValue:" + objValue);
    }
    
    public DChoiceEvQuestion(
        String strAllowedEval)
    {
        super(
            DChoiceEvQuestion._s_strTitleSuffix
            );
            
        

        super._intValue_ = DChoiceEvQuestion.f_s_intContinue;
    
        String strBody = DChoiceEvQuestion._s_strBodyWhat;
        strBody += " ";
        strBody += strAllowedEval;

        strBody += "\n\n";
        strBody += com.google.code.p.keytooliui.shared.swing.panel.PHelpAboutAppli.s_strContactPoints;
        strBody += "\n\n";
        
        
        Object[] objsArray = { strBody };
        
        super._opn_ = new OPInputQuestion(objsArray, DChoiceEvQuestion._s_objsOption);
    }
}