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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . shared: PBarContainerPagerAbstract
    . shared: PBarContainerPathAbstract
    . shared: PBarContainerSec ==> secondary (window or bean inside HTML)

**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

abstract public class PBarContainerAbstract extends JPanel 
{
    // ----------------------
    // FINAL STATIC PROTECTED
    
    final static protected Font _f_s_fntChildren_ = new Font("Dialog", Font.PLAIN, 12);
    final static protected int _f_s_intH_ = 28;
    final static protected Color _f_s_colFgChildren_ = Color.black;
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public boolean init();
    abstract public void destroy();
 
    // ---------
    // PROTECTED
    
    
    protected PBarContainerAbstract()
    {        
        super();
    }
    
}