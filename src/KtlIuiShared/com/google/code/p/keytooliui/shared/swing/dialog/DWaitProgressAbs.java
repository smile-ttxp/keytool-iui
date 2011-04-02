/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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

/**
    known subclasses:
    . shared_gen: DWaitProgressCheckHtmlGen (used at export time)
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

abstract public class DWaitProgressAbs extends DEscapeAbstract 
{    
    
        
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strTitleSuffix = null; 
	//static private String _s_strLabel1 = null; 
	static private String _s_strLabel2 = null; 
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final  String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
            ".DWaitProgressAbs" // class name
            ;
            
        final String f_strBundleFileLong = f_strBundleFileShort + ".properties";
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DWaitProgressAbs";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTitleSuffix = rbeResources.getString("titleSuffix"); 
	        //_s_strLabel1 = rbeResources.getString("label1");
	        _s_strLabel2 = rbeResources.getString("label2");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, f_strBundleFileLong + ", excMissingResource caught");
        }
    }
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
   
        
        
        
        setTitle(System.getProperty("_appli.title") + " - " + _s_strTitleSuffix);
    
        if (this._strWaitWhat == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._strWaitWhat");
            return false;
        }
        
        // ---------
        
        java.awt.Container cntContentPane = getContentPane();
       
        
        // ---------
    
        //this._lbl1 = new JLabel(_s_strLabel1 + " " + this._strWaitWhat);
        this._lbl1 = new JLabel(this._strWaitWhat);
        this._lbl2 = new JLabel(_s_strLabel2);
        
        javax.swing.ImageIcon iinLoading = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strWait28_32);
	    
	    if (iinLoading == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil iinLoading"); 
	        return false;
	    }
	    
        this._lbl1.setIcon(iinLoading);
        
        
        
        this._lbl1.setHorizontalAlignment(JLabel.CENTER);
        this._lbl2.setHorizontalAlignment(JLabel.RIGHT);
        
        cntContentPane.add(this._lbl1, java.awt.BorderLayout.CENTER);
        cntContentPane.add(this._lbl2, java.awt.BorderLayout.SOUTH);
        
        
        setSize(new java.awt.Dimension(300, 150));
        
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected DWaitProgressAbs(JFrame fmrOwner, String strLoadingWhat)
    {
        super(fmrOwner, false);
   
        this._strWaitWhat = strLoadingWhat;
    }
    
    
    
    // -------
    // PRIVATE
    
    private JLabel _lbl1 = null;
    private JLabel _lbl2 = null;
   
    private String _strWaitWhat = null;
}