/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.ktl.swing.button;
 
 
 /**
    "Xpd" means "keyTool IUI Project Document"
    
 **/
 
 import java.awt.event.*;
 
 public final class RBTypeJarRcr extends RBTypeJarAbs
 {
    // -------------------
    // PUBLIC STATIC FINAL 
    
    public static final String f_s_strDesc = "RCReader doc";    
    public static final String[] f_s_strsFileExt = { com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strProjectReaderDocument };
   
    
    // ------
    // PUBLIC
    
    public RBTypeJarRcr(
        boolean blnIsEnabled,
        ItemListener itmListenerParent)
    {
        super(
            blnIsEnabled,
            itmListenerParent,
            RBTypeJarRcr.f_s_strDesc,
            RBTypeJarRcr.f_s_strsFileExt
            );
    }
 }
