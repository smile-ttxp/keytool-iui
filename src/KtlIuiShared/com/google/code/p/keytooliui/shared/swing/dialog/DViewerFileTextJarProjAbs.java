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

/**
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    IMPORTANT:
    
    do not change extension from JDIALOG to JFRAME,
    as this window is opened thanks to a dialog window.
    ==> if extending from JFRAME, then this frame will freeze, as the appli wait for actions on the dialog window
    
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    
    known subclasses:
    . DViewerFileTextJarProjHtml ==> preview, builders
    . DViewerFileTextJarProjRtf ==> preview, builders
    
**/


import java.awt.*;

abstract public class DViewerFileTextJarProjAbs extends DViewerFileTextAbs 
{
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean doFileOpen(String strPathAbsoluteJarSys, String strPathRelativeFile)
        throws Exception;
    
    
    // ---------
    // PROTECTED
    

    protected DViewerFileTextJarProjAbs(
        Component cmpFrameOwner,
        String strTitleApplication,
        String strTitleSuffix)
    {
        super(cmpFrameOwner, strTitleApplication, strTitleSuffix);
    }
}