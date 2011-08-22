/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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


public final class PSelBtnTfdFileJarSaveUnsigned extends PSelBtnTfdFileJarSaveAbs
{
    // -------------------
    // PUBLIC STATIC FINAL 
    
    public static final String f_s_strDocPropVal = "select_file_jar_save_unsigned";
    
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strLabel = "Unsigned JAR file:";
    
    // ------
    // PUBLIC
    
    public PSelBtnTfdFileJarSaveUnsigned(
        javax.swing.event.DocumentListener docListenerParent, 
        Frame frmParent, 
        String strDirNameDefault,
        boolean blnFieldRequired
        )
    {
        super(
            docListenerParent,
            frmParent, 
       
            
            PSelBtnTfdFileJarSaveUnsigned._f_s_strLabel, 
            PSelBtnTfdFileJarSaveUnsigned.f_s_strDocPropVal,

            strDirNameDefault,
            blnFieldRequired
            );
    }
}
