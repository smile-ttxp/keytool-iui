/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.optionpane;

/**
    known subclasses:
    . OPInputQuestion
    . OPInputWarning
**/


import java.io.File;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;
import com.google.code.p.keytooliui.shared.swing.dialog.DViewSourceFileTextSys;

abstract public class OPAbstract extends JOptionPane
{ 
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.";
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".OPAbstract" // class name
        ;
        
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strTitleInfo = null;
    static private String _s_strTitleWarning = null;
    static private String _s_strTitleError = null;
    static private String _s_strButtonClose = null;
    static private String _s_strButtonOk = null;
    static private String _s_strButtonCancel = null;
    
    // ------------------
    // STATIC INITIALIZER
    
    static
    {
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(OPAbstract._f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
            
            OPAbstract._s_strTitleInfo = rbeResources.getString("titleInfo"); 
            OPAbstract._s_strTitleWarning = rbeResources.getString("titleWarning"); 
            OPAbstract._s_strTitleError = rbeResources.getString("titleError"); 
	        OPAbstract._s_strButtonClose = rbeResources.getString("buttonClose"); 
	        OPAbstract._s_strButtonOk = rbeResources.getString("buttonOk"); 
	        OPAbstract._s_strButtonCancel = rbeResources.getString("buttonCancel"); 
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(OPAbstract._f_s_strClass, "excMissingResource caught");
        }
    } 
    
    // -------------
    // STATIC PUBLIC
    
    static public void s_showDialogWarning(Component cmpParent, String strTitleAppli, String strBody)
    {    
        String strTitle = null;
        
        if (strTitleAppli == null)
            strTitle = OPAbstract._s_strTitleWarning;
        else
            strTitle = strTitleAppli + " - " + OPAbstract._s_strTitleWarning;
        
        Object[] objsOption = { OPAbstract._s_strButtonClose };
        
        JOptionPane.showOptionDialog(cmpParent, strBody, strTitle, 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, objsOption, objsOption[0]);
    }
    
    static public void s_showDialogInfo(Component cmpParent, String strTitleAppli, String strBody)
    {  
        String strTitle = null;
        
        if (strTitleAppli == null)
            strTitle = OPAbstract._s_strTitleInfo;
        else
            strTitle = strTitleAppli + " - " + OPAbstract._s_strTitleInfo;
        
        Object[] objsOption = { OPAbstract._s_strButtonClose };
        
        JOptionPane.showOptionDialog(cmpParent, strBody, strTitle, 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, objsOption, objsOption[0]);
                
        if (cmpParent != null)
        {
            if (cmpParent.isVisible())
                if (cmpParent.isShowing())
                    cmpParent.repaint();
        }
    }
    
    /*static public void s_showDialogError(Component cmpParent, String strTitleAppli, String strBody,
            String strPathAbsLog)
    {        
        String strTitle = null;
        
        if (strTitleAppli == null)
            strTitle = OPAbstract._s_strTitleError;
        else
            strTitle = strTitleAppli + " - " + OPAbstract._s_strTitleError;
    
        Object[] objsOption = null;
        
        if (strPathAbsLog == null)
        {
            objsOption = new Object[1];
            objsOption[0] = OPAbstract._s_strButtonClose;
        }
        else
        {
            objsOption = new Object[2];
            objsOption[0] = "Show log";
            objsOption[1] = OPAbstract._s_strButtonClose;
        }
        
        JOptionPane.showOptionDialog(cmpParent, strBody, strTitle, 
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, objsOption, objsOption[0]);
    }*/
    
    static public void s_showDialogError(Component cmpParent, String strTitleAppli, String strBody)
    {        
        String strTitle = null;
        
        if (strTitleAppli == null)
            strTitle = OPAbstract._s_strTitleError;
        else
            strTitle = strTitleAppli + " - " + OPAbstract._s_strTitleError;
    
        String strPathAbsLog = MySystem.s_getPathLogSession();
     
        
        Object[] objsOption = null;
        
        if (strPathAbsLog == null)
        {
            objsOption = new Object[1];
        }
        
        else
        {
            objsOption = new Object[2];
            objsOption[1] = "SHOW SESSION LOG";
            
            strBody += "\n\n";
            strBody += "Session log file path:";
            strBody += "\n" + "  " + strPathAbsLog + "\n";
        }
        
        objsOption[0] = OPAbstract._s_strButtonClose;
        

        
        int intRetVal = JOptionPane.showOptionDialog(cmpParent, strBody, strTitle, 
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, objsOption, objsOption[0]);
        
        //if (strPathAbsLog == null)
          //  return;
        
        
        if (intRetVal == 1)
        {
            File fle = new File(strPathAbsLog);
            
            if (! fle.exists())
            {
                // TBRL
                return;
            }
            
            if (! fle.canRead())
            {
                // TBRL
                return;
            }
            
            DViewSourceFileTextSys dlg = new  DViewSourceFileTextSys(cmpParent, strTitleAppli);
            
            if (! dlg.init())
            {
                // TBRL
                return;
            }
            
            Dimension dim = new Dimension(500, 400);
            dlg.setPreferredSize(dim);
            
            if (! dlg.show(fle))
            {
                // TBRL
                return;
            }
        }
    }
    
    /**
        if any error, exiting
    **/
    static public boolean s_showConfirmDialog(Component cmpParent, String strTitle, String strBody)
    {
        String strWhere = OPAbstract._f_s_strClass + "s_showConfirmDialog(cmpParent, strTitle, strBody)";    
          
        Object[] objsOption = { OPAbstract._s_strButtonOk, OPAbstract._s_strButtonCancel};
        
        int intReply = JOptionPane.showOptionDialog(cmpParent, strBody, strTitle, 
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, objsOption, objsOption[0]);

        if (intReply==1 || intReply==-1) // cancelButton or "X" on topRight
        {
            //MySystem.s_printOutTrace(strWhere, "action cancelled");
            return false;
        }
        
        if (intReply != 0)
           MySystem.s_printOutExit(strWhere, "unexpected value: " + intReply);
        
        return true;
    }
    
    // there is a warning, the user may choose to solve the problem
    // icon=warning
    // buttons: choice between Y/N
    static public boolean s_showWarningConfirmDialog(Component cmpParent, String strTitle, String strBody)
    {
        String strWhere = OPAbstract._f_s_strClass + "s_showWarningConfirmDialog(cmpParent, strTitle, strBody)";    
          
        Object[] objsOption = { OPAbstract._s_strButtonOk, OPAbstract._s_strButtonCancel};
        
        int intReply = JOptionPane.showOptionDialog(cmpParent, strBody, strTitle, 
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, objsOption, objsOption[0]);

        if (intReply==1 || intReply==-1) // cancelButton or "X" on topRight
        {
            //MySystem.s_printOutTrace(strWhere, "action cancelled");
            return false;
        }
        
        if (intReply != 0)
        {
           MySystem.s_printOutExit(strWhere, "unexpected value: " + intReply);
           
        }
        
        return true;
    }
    
    static public String s_showQuestionInputDialog(
        Component cmpParent, // nil value allowed 
        String strTitle, 
        String strBody, 
        Object[] objsOptions, 
        Object objInitialValue)
    {
        String strWhere = OPAbstract._f_s_strClass + "s_showQuestionInputDialog(...)";    
        
        if (strTitle==null || strBody==null || objsOptions==null || objInitialValue==null)
            MySystem.s_printOutExit(strWhere, "nil arg");
      
        int intMessageType = JOptionPane.QUESTION_MESSAGE;
        
        String strResult = (String) JOptionPane.showInputDialog(
                cmpParent, strBody, strTitle, intMessageType,
                null, objsOptions, objInitialValue);
        
        // ending
        return strResult;
    }
    
    // ------
    // PUBLIC
    
    public boolean init() { return true; }
    public void destroy() {}
    
    
    // ---------
    // PROTECTED
    
    protected OPAbstract(
        Object[] objsArray,
        int intMessageType,
        Object[] objsOption)
    {
        super(objsArray, 
            intMessageType,
            JOptionPane.YES_NO_OPTION,
            null,
            objsOption,
           objsOption[0]);
    }
}