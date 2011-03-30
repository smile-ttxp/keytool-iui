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

final public class DWClipIO extends DWClipAbstract
{    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strWhy = null; // "illegal argument.";
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final  String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DWClipIO" // class name
            ;
        
        final String f_strBundleFileLong = f_strBundleFileShort + ".properties";   
        final String f_strClass = "com.google.code.p.keytooliui.shared.swing.dialog.DWClipIO.";
    
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            // resources      
	        _s_strWhy = rbeResources.getString("why");     
	  	}
	    
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strClass, f_strBundleFileLong + ", excMissingResource caught");
        }
    }
    
    
    
    // ------
    // PUBLIC
    
    
    
    
    public DWClipIO(
        java.awt.Frame frm,
        String strTitleApplication,
        String strPathRelativeClip)
    {
        super(frm, strTitleApplication, strPathRelativeClip, _s_strWhy);
    }
    
    // -------
    // PRIVATE
}