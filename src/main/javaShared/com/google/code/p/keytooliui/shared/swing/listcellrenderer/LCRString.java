/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.listcellrenderer;

import com.google.code.p.keytooliui.shared.swing.cellrenderer.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;



public class LCRString extends LCRAbstract
{
    // ------
    // PUBLIC
    
    public LCRString(boolean blnSelectable)
    {
	    super(blnSelectable);
	}
	

	
	public Component getListCellRendererComponent(
        JList lst,
        Object objValue,
        int intModelIndex,
        boolean blnIsSelected,
        boolean blnCellHasFocus)
    {
        String strMethod = "getListCellRendererComponent(...)";
        
        if (! (objValue instanceof CRStringAbstract))
            MySystem.s_printOutExit(this, strMethod, "wrong instance");

        CRStringAbstract frr = (CRStringAbstract) objValue;
 
        //setIcon(frr.getIcon());
        setFont(frr.getFont());
        
	    String strName = frr.getName();
	    
	    
	    // MODIF from jdk1.2.2 to jdk1.3final, see Bug Id  4281986
	    //return super._getListCellRendererComponent_(lst, strName, intModelIndex, blnIsSelected, blnCellHasFocus);
	    Component c = super._getListCellRendererComponent_(lst, strName, intModelIndex, blnIsSelected, blnCellHasFocus);
	    setIcon(frr.getIcon());
	    return c;
	}
	
	// -------
	// PRIVATE
}