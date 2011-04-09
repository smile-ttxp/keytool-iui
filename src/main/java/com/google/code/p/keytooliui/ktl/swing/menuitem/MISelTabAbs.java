/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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

package com.google.code.p.keytooliui.ktl.swing.menuitem;

/**
 a MenuItem that allows to select a specific tab (if tab not visible, first add tab to tabbedPane)

 known subclasses:

 . MISelTabCreateKst
 . MISelTabCreateKprDsa (version #1 cert)
 . MISelTabCreateKprRsa (version #1 cert)
 *  . MISelTabCreateKprEc (version #1 cert)
 *  . MISelTabCreateKprV3CDsa (version #3 cert)
 . MISelTabCreateKprV3CRsa (version #3 cert)
 *  . MISelTabCreateKprV3CEc (version #3 cert)
 *
 . MISelTabKprFromKprKst
 . MISelTabKprAnyToCsr
 . MISelTabKprToCrt
 . MISelTabKprAnyFromCrt
 . MISelTabTcrFromCrt
 *  . MISelTabTcrFromCCa
 *  . MISelTabKprFromKprDer
 *  . MISelTabKprFromKprPem // TODO
 *
 . MISelTabJarSign
 . MISelTabJarVerify
 *
 *  . MISelTabArcDir
 **/


import java.awt.event.ActionListener;

import com.google.code.p.keytooliui.shared.swing.menuitem.MIAbstract;

public abstract class MISelTabAbs extends MIAbstract
{
    public void destroy()
    {
    }

    public boolean init()
    {
        return true;
    }

    protected MISelTabAbs(String strText, ActionListener actListenerParent)
    {
        super(strText, actListenerParent);
    }
}