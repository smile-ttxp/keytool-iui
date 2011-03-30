/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable

    ... used to select a file (not a directory) located in user's system, in open mode
    eg: keytool
**/


import javax.swing.*;

import java.awt.*;


final public class PSelBtnTfdFileJarSaveUnsigned extends PSelBtnTfdFileJarSaveAbs
{
    // -------------------
    // FINAL STATIC PUBLIC 
    
    final static public String f_s_strDocPropVal = "select_file_jar_save_unsigned";
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strLabel = "Unsigned JAR file:";
    
    // ------
    // PUBLIC
    
    public PSelBtnTfdFileJarSaveUnsigned(
        javax.swing.event.DocumentListener docListenerParent, 
        Frame frmParent, String strTitleAppli,
        String strDirNameDefault,
        boolean blnFieldRequired
        )
    {
        super(
            docListenerParent,
            frmParent, 
            strTitleAppli, 
            
            PSelBtnTfdFileJarSaveUnsigned._f_s_strLabel, 
            PSelBtnTfdFileJarSaveUnsigned.f_s_strDocPropVal,

            strDirNameDefault,
            blnFieldRequired
            );
    }
}
