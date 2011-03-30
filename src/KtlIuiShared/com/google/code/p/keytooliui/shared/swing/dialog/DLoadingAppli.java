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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

final public class DLoadingAppli extends DLoadingAbs
{
    // --------------
    // STATIC PRIVATE
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".DLoadingAppli" // class name
        ;
    
	static private String _s_strLoadingWhat = null; 
    
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DLoadingAppli";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
	        _s_strLoadingWhat = rbeResources.getString("loadingWhat");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, _f_s_strBundleFileLong + " not found, excMissingResource caught");
        }
        
        strWhere = null;
    }
    
    
    // ------
    // PUBLIC
    
    public DLoadingAppli(Frame fmrOwner, String strTitleApplication)
    {
        super(
            fmrOwner, 
            strTitleApplication, 
            DLoadingAppli._s_strLoadingWhat,
            new java.awt.Dimension(240, 120) // contentPane's size
            );
    }
}