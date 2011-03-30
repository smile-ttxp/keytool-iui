/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.dialog;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

final public class DViewerFileTextSysHtml extends DViewerFileTextSysAbs 
{    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".DViewerFileTextSysHtml" // class name
        ;
    
    // --------------
    // STATIC PRIVATE
    
    static private String _s_strTitleSuffix = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DViewerFileTextSysHtml";
        
        String strBundleFileLong = _f_s_strBundleFileShort + ".properties";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        _s_strTitleSuffix = rbeResources.getString("titleSuffix");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, strBundleFileLong + " not found, excMissingResource caught");
        }
    }
    
    // -------------
    // PUBLIC ACCESS
   
    
    // ------
    // PUBLIC
    
    public boolean doFileOpen(String strPathAbsoluteSys)
        throws Exception
    {
        String strMethod = "doFileOpen(strPathAbsoluteSys)";
        
        if (strPathAbsoluteSys == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathAbsoluteSys");
            return false;
        }
        
        String strPathAbsoluteJava = com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.s_systemPathToJavaPath(
            strPathAbsoluteSys);
                
        if (strPathAbsoluteJava == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strPathAbsoluteJava");
            return false;
        }
        
        URL url = null;
        
        try
        { 
            url = new URL("file", "", strPathAbsoluteJava); 
        }
        
        catch (MalformedURLException excMalformedURL)
        { 
            // Can't happen. 
            excMalformedURL.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excMalformedURL caught");
            return false;
        } 
        
        
        if (! ((PViewerFileTextHtmlPreviewSys)super._pnd_).doFileOpen(url))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (super._strTitleApplication_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._strTitleApplication_");
            return false;
        }
        
        setTitle(super._strTitleApplication_ + " - " + url.toString());
        
        // ending
        return true;
    }
        
    
    public DViewerFileTextSysHtml(Component cmpFrameOwner, String strTitleApplication)
    {
        super(cmpFrameOwner, strTitleApplication, _s_strTitleSuffix);
        
        String strMethod = "DViewerFileTextSysHtml(cmpFrameOwner, strTitleApplication)";
        
        try
	    {
	        super._pnd_ = new PViewerFileTextHtmlPreviewSys((ActionListener) this, cmpFrameOwner, strTitleApplication);
	    }
	    
	    catch(Exception exc)
	    {
	        exc.printStackTrace();
	        MySystem.s_printOutExit(this, strMethod, "exc caught");
	    }
    }
    
    // -------
    // PRIVATE
    
}