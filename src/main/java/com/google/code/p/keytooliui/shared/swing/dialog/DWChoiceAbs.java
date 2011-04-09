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
    memo: "OK" means call to destroy right after being used (dispose)

    known subclasses (they should be renamed!):
    . OK DWClipAbstract,
    . OK DWConfirmFileOverwrite,
    . OK DWIOFileAbstract,
    . OK DWTemplateAbstract,
    . ?? DWCheckHtmlGen

/** 

DWChoiceAbs means Dialog Warning Choice, expecting the user's choice
no effectes if clicking on the "close" at top-right of the dialog

*/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*; //Property change stuff

abstract public class DWChoiceAbs extends DEscapeAbstract implements
    PropertyChangeListener
{   
    // ---------------------
    // PRIVATE STATIC STRING
    
    private static String _s_strTitleSuffix = null;
    private static String _s_strBodySuffix = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DWChoiceAbs" // class name
            ;
        
        String strBundleFileLong = strBundleFileShort + ".properties";   
        String strClass = "com.google.code.p.keytooliui.shared.swing.dialog.DWChoiceAbs.";
    
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
            _s_strTitleSuffix = rbeResources.getString("titleSuffix");
            _s_strBodySuffix = rbeResources.getString("bodySuffix");   
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strClass, strBundleFileLong + ", excMissingResource caught");
        }
    }
    
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public void propertyChange(PropertyChangeEvent evtPropertyChange);
    
    // ----------------
    // PUBLIC ACCESSORS
    
    public int getValue() { return this._intValue_; }
    
    // ------
    // PUBLIC
    
    // overwriting superclass's method
    public void windowClosing(WindowEvent evt)
    {
        Toolkit.getDefaultToolkit().beep();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._opn == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._opn");
            return false;
        }
        
        if (! this._opn.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected int _intValue_ = -1; // !!!!!!!!!!!!!!!!!!!!!
    
    protected void _cancel_()
    {
        if (this._opn != null)
        {
            this._opn.removePropertyChangeListener(this);
            this._opn.destroy();
            this._opn = null;
        }
        
        super._cancel_();
    }
    
    protected DWChoiceAbs(
        Frame frm,
       
        String strMessageBody,
        Object[] objsOption)
    {
        super(frm, true);
        
        setTitle(System.getProperty("_appli.title") + " - " + _s_strTitleSuffix);
        
        Object[] objsArray = {strMessageBody + "\n\n" + _s_strBodySuffix + "\n"};
        
        this._opn = new OPInputWarning(objsArray, objsOption);
        this._opn.addPropertyChangeListener(this);
        setContentPane(this._opn);
    }
    
    // -------
    // PRIVATE


    private OPInputWarning _opn = null;    
}