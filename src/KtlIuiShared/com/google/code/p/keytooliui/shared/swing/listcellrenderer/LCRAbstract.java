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
 
 
package com.google.code.p.keytooliui.shared.swing.listcellrenderer;

/**
    known subclasses:
    . tpb:          LCRSetting
    . shared:       LCRString
    . shared_gen:   LCRFileSys
    . shared_gen:   LCRFileJarProj
    . shared_gen:   LCRPropChooser
**/

import javax.swing.*;

import java.awt.*;



abstract public class LCRAbstract extends DefaultListCellRenderer
{
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public Component getListCellRendererComponent(
        JList lst, Object objValue, int intModelIndex,
        boolean blnIsSelected, boolean blnCellHasFocus);
    
    // ------
    // PUBLIC
    
    public boolean init()
	{
	    return true;
	}
	
	public void destroy()
	{
	}
    
    // ---------
    // PROTECTED
    
    
    protected LCRAbstract(boolean blnSelectable)
    {
	    super();
	    this._blnSelectable = blnSelectable;
	}
	
	protected Component _getListCellRendererComponent_(
        JList lst,
        String strName,
        int intModelIndex,
        boolean blnIsSelected,
        boolean blnCellHasFocus)
    {
        if (this._blnSelectable)
	        return super.getListCellRendererComponent(lst, strName, intModelIndex, blnIsSelected, blnCellHasFocus);
        else
	        return super.getListCellRendererComponent(lst, strName, intModelIndex, false, false);
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnSelectable = true;
}