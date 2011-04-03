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
 
 
 package com.google.code.p.keytooliui.shared.awt.awtevent;

import com.google.code.p.keytooliui.shared.swing.dialog.*;

public class DEditorJarProjTextHtmlEvent extends java.awt.AWTEvent
{
    // ------------------------
    // FINAL STATIC PRIVATE INT
    
    final static private int _f_s_intIdFirst = EPTextAbsEvent.EPTEXTABSEVENT_LAST + 1;
    
    // -----------------------
    // FINAL STATIC PUBLIC INT
    
    final static public int DEDITORJARPROJTEXTHTMLEVENT_LAST = _f_s_intIdFirst;

    final static public int DEDITORJARPROJTEXTHTMLEVENT_FILEMODIFIED = _f_s_intIdFirst;
      
    // -------------
    // PUBLIC ACCESS
    
    public String getStrPathAbsoluteJar() { return this._strPathAbsoluteJar; }
    public String getStrPathRelativeHtml() { return this._strPathRelativeHtml; }
    
    // ------
    // PUBLIC
   
    public DEditorJarProjTextHtmlEvent(DEditorJarProjTextHtml tpwSource, int intId,
        String strPathAbsoluteJar,
        String strPathRelativeHtml)
    {
        super(tpwSource, intId);
        this._strPathAbsoluteJar = strPathAbsoluteJar;
        this._strPathRelativeHtml = strPathRelativeHtml;
    } 
    
    // -------
    // PRIVATE
    
    private String _strPathAbsoluteJar = null;
    private String _strPathRelativeHtml = null;
}