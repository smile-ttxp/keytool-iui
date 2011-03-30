/*
 *
 * Copyright (c) 2001-2008 RagingCat Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
 
 
package com.google.code.p.keytooliui.shared.util.jarsigner;

/**
    "UtilJsrFile" ==> "Utility, Jarsigner, File to open File to save"
**/


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

// ----
// ----


import java.io.*;
import java.awt.*;

final public class UtilJsrFile
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.util.jarsigner.UtilJsrFile";
    
    // --------------
    // STATIC PRIVATE
    
    static private boolean _s_blnFileSaveOverwriteAlways = false;
    
    // -------------
    // STATIC PUBLIC
    
    
    /**
        if any code or config error, exiting
        else if any other type of error, show warning/error dialog, then return null
    **/
    static public File s_getFileSave(
        Frame frmOwner, 
        String strTitleAppli,
        String strPathAbs,
        boolean blnShowDlgOverwrite
        )
    {
        String strMethod = UtilJsrFile._f_s_strClass + "." + "s_getFileSave(...)";
        
        if (strPathAbs == null)
        {
            MySystem.s_printOutExit(strMethod, " nil strPathAbs");
        }
        
        File fleSave = new File(strPathAbs);
        
        if (! fleSave.exists())
            return fleSave;
            
        // ----
        
        if (! blnShowDlgOverwrite)
            return fleSave;
        
        
        if (UtilJsrFile._s_blnFileSaveOverwriteAlways)
            return fleSave;
        
        // ----
        // show option dialog

        Object[] objsOptions = {"Cancel", "Overwrite", "Always Overwrite"}; 
        Object objInitialValue = objsOptions[0]; // "Cancel"  
            
            
        String strMessageTitle = strTitleAppli + " - " + "confirm file overwrite";
        String strMessageBody = "Confirm overwrite:";
            
        strMessageBody += "\n";
        strMessageBody += "  ";
        strMessageBody += fleSave.getAbsolutePath();
        strMessageBody += "\n\n";
        strMessageBody += "Please choose an option below?"; 
            

        String strResult = OPAbstract.s_showQuestionInputDialog(
            frmOwner, strMessageTitle, strMessageBody, objsOptions, objInitialValue);
            
        if (strResult == null) // "Abort"
        {
            MySystem.s_printOutTrace(strMethod, "nil strResult");
            return null;
        }
            
        // -----
        strResult = strResult.trim();
                            
        if (strResult.length() < 1)
        {
            MySystem.s_printOutExit(strMethod, "strResult.length()<1: " + strResult);
        }
                
        if (strResult.equalsIgnoreCase((String) objsOptions[0]))
        {
            // abort
            return null;
        } 
            
        if (strResult.equalsIgnoreCase((String) objsOptions[1]))
        {
            // overwrite
            return fleSave;
        } 
            
        if (strResult.equalsIgnoreCase((String) objsOptions[2]))
        {
            // always overwrite
            UtilJsrFile._s_blnFileSaveOverwriteAlways = true;
            return fleSave;
        } 
            
        MySystem.s_printOutExit(strMethod, "uncaught value, strResult=" + strResult);
        // statement never reached!
        return null;
    }
    
    /**
        if any code or config error, exiting
        else if any other type of error, show warning/error dialog, then return null
    **/
    static public File s_getFileOpen(
        Frame frmOwner, 
        String strTitleAppli,
        String strPathAbs
        )
    {
        String strMethod = UtilJsrFile._f_s_strClass + "." + "s_getFileOpen(...)";
        
        if (strPathAbs == null)
        {
            MySystem.s_printOutExit(strMethod, " nil strPathAbs");
        }
        
        File fleOpen = new File(strPathAbs);
        
        if (! fleOpen.exists())
        {
            MySystem.s_printOutError(strMethod, 
                "! fleOpen.exists(), fleOpen.getAbsolutePath()=" + fleOpen.getAbsolutePath());
            
            String strBody = "File not found:";
            strBody += "\n  " + fleOpen.getAbsolutePath();
            
            OPAbstract.s_showDialogWarning(frmOwner, strTitleAppli, strBody);
                
            return null;
        }
        
        if (! fleOpen.canWrite()) // write protected !
        {
            MySystem.s_printOutError(strMethod, 
                "! fleOpen.canWrite(), fleOpen.getAbsolutePath()=" + fleOpen.getAbsolutePath());
                
            String strBody = "File is write-protected:";
            strBody += "\n  " + fleOpen.getAbsolutePath();
            
            OPAbstract.s_showDialogWarning(frmOwner, strTitleAppli, strBody);
                
            return null;
        }
        
        if (! fleOpen.isFile()) // directory!
        {
            MySystem.s_printOutError(strMethod, 
                "! fleOpen.isFile(), fleOpen.getAbsolutePath()=" + fleOpen.getAbsolutePath());
                
            String strBody = "File is directory:";
            strBody += "\n  " + fleOpen.getAbsolutePath();
            
            OPAbstract.s_showDialogWarning(frmOwner, strTitleAppli, strBody);
                
            return null;
        }
        
        return fleOpen;
    }
}