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
 
 
 package com.google.code.p.keytooliui.shared.swing.menuitem;
 
 /**
 
    known subclasses:
    . RBMIAlignTopToolMain
    . RBMIAlignTopTabsMain
    . rcr: RBMIAlignTopBmkRdr
    . rcr: RBMIAlignTopSchRdr
 
    
    
    Important: addItemListener is done at init time, instead of at construct time
    thus in order to first do select the item in the parentContainer, and not 
    the respective item to send an event at the starting!
 **/
 
 import com.google.code.p.keytooliui.shared.lang.*;
 
 
 abstract public class RBMIAlignTopAbs extends RBMIAlignAbstract
 { 
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strIcon = "aligntop16.gif"; 
    
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strText = null;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        java.util.ResourceBundle rbeResources;
    
        final String f_strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".RBMIAlignTopAbs" // class name
            ;
    
        
        
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.menuitem.RBMIAlignTopAbs";
        
        try
        {
            rbeResources = java.util.ResourceBundle.getBundle(f_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strText = rbeResources.getString("text");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught");
        }
    }
    
    // ---------
    // PROTECTED
    
    protected RBMIAlignTopAbs(java.awt.event.ItemListener itmListenerParent)
    {
        super(_s_strText, _f_s_strIcon, itmListenerParent);
    }
    
 }