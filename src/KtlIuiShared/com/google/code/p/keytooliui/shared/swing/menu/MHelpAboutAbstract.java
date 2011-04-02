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
 
 
 package com.google.code.p.keytooliui.shared.swing.menu;

/**
    a simple help menu that displays an "about" menuItem
    
    known subclasses:
    . rcr: MHelpAboutNoteAppli
    . rcr: MHelpAboutNoteDoc
    . jhr: MHelpAboutHSViewer
    . shared: MHelpAboutEditorDefault
    . shared: MHelpAboutEditorNote
    . shared: MHelpAboutViewerHtmlPreview ==> preview HTMLs, used in DocBuilder & TplBuilder
    . shared: MHelpAboutViewerHtmlView ==> secondary viewer, used in DocReader
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;


import java.awt.event.*;

import java.awt.*;


abstract public class MHelpAboutAbstract extends MAbstract implements
    ActionListener
{
    
    /** ##############
        STATIC PRIVATE
        ##############
    **/
    
    
    static private java.util.ResourceBundle _s_rbeResources;
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared._F_STR_PATH_BUNDLE +
        ".MHelpAboutAbstract" // class name
        ;
    
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menu.MHelpAboutAbstract";
        
        try
        {
            _s_rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, _f_s_strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    
    // ------
    // PUBLIC
    
    
    
    public void destroy()
    {
        _destroyListeners();
    }

    
    public void actionPerformed(ActionEvent evtAction)
    {
        String f_strMethod = "actionPerformed(evtAction)";
        
        if (! (evtAction.getSource() instanceof JMenuItem))
        {
            MySystem.s_printOutExit(this, f_strMethod, "wrong source");
        }
        
        JMenuItem mim = (JMenuItem) evtAction.getSource();
        
        if (mim == this._mimAbout)
        {
	        Toolkit.getDefaultToolkit().beep();
	        
	        OPAbstract.s_showDialogInfo(
	            this._cmpFrameOwner,
	        
	            this._strInfoAboutBody_);
            
            return;
        }
        
         MySystem.s_printOutExit(this, f_strMethod, "unexpected menuItem");
         
    }
    
    // ---------
    // PROTECTED
    
    protected String _strInfoAboutBody_ = null;
   
    
    protected boolean _init_()
    {
        String f_strMethod = "_init_()";
        
        if (! _loadResourceBundle())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        if (this._mimAbout == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._mimAbout");
            return false;
        }
        
        add(this._mimAbout);
        
        
        
        // -------------------------------------------------------------
        // the following should be assigned at init time in subclasses
        
        if (this._strInfoAboutBody_ == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._strInfoAboutBody_");
            return false;
        }
        
        // ending
        return true;
    }
    
    protected MHelpAboutAbstract(Component cmpFrameOwner)
    {
        super();
        this._cmpFrameOwner = cmpFrameOwner;
   
        
        _createChildren();
        _createListeners();
        
    }
 
    
    
    /** #######
        PRIVATE
        #######
    **/
    
   
    
    // --------
    // children
    private JMenuItem _mimAbout = null;
    
    // ---------
    private Component _cmpFrameOwner = null;
   
    
    
    private void _createChildren()
    {
        this._mimAbout = new JMenuItem();
    }
    
    private void _createListeners()
    {
        this._mimAbout.addActionListener(this);
    }
    
    
    private boolean _loadResourceBundle()
    {
        String f_strMethod = "_loadResourceBundle()";
        
         /* MEMO: trim() not necessary
        */
        try
        {   
            String strValue = null;
            
            // TEXTS
	        strValue = _s_rbeResources.getString("text_this");
	        setText(strValue);
	        
	        strValue = _s_rbeResources.getString("text_about");
	        this._mimAbout.setText(strValue);
	        
	        
	        // MNEMONICS
	        
	        char chrValue;
	        
	        strValue = _s_rbeResources.getString("mnemo_this");
	        chrValue = strValue.trim().charAt(0);
	        setMnemonic(chrValue);
	    }
	    
	    catch (java.util.MissingResourceException excMissingResource)
	    {
	        excMissingResource.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excMissingResource caught");
	        return false;
	    }
	    
	    catch(IndexOutOfBoundsException excIndexOutOfBounds) // for charAt(0)
	    {
	        excIndexOutOfBounds.printStackTrace();
	        MySystem.s_printOutError(this, f_strMethod, _f_s_strBundleFileLong + ", excIndexOutOfBounds caught");
	        return false;
	    }
	    
	    // ending
	    return true;
    }
    
    private void _destroyListeners()
    {
        if (this._mimAbout != null)
        {
            this._mimAbout.removeActionListener(this);
            this._mimAbout = null;
        }
    }
}