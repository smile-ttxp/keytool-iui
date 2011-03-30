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

import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;

import com.google.code.p.keytooliui.shared.swing.panel.*;


import java.awt.*;


final public class DRangeToValue extends DRangeAbstract implements
    PRangeToValueListener
{
    // ------
    // PUBLIC
    
    public void setEnabledOk(boolean bln)
    {
        super._btnOk_.setEnabled(bln);
    }
    
    public void changeDefault(int intValue)
    {
        if (super._pra_ != null)
        {
            ((PRangeToValue)super._pra_).changeDefault(intValue);
        }
    }
    
      // a page is already displayed! if bln==true
    public void setDefaultValid(boolean bln)
    {
        if (super._pra_ != null)
        {
            ((PRangeToValue)super._pra_).setDefaultValid(bln);
        }
    }
    
    
    public synchronized void addPRangeToValueListener(PRangeToValueListener rtvListener)
	{
	    if (this._rtvListenerThis == null)
            this._rtvListenerThis = PRangeToValueEventMulticaster.add(this._rtvListenerThis, rtvListener);
	}
  
	public synchronized void removePRangeToValueListener(PRangeToValueListener rtvListener)
	{
	    if (this._rtvListenerThis != null)
            this._rtvListenerThis = PRangeToValueEventMulticaster.remove(this._rtvListenerThis, rtvListener);
	}
	
	public void defaultSwapped(PRangeToValueEvent evtPRangeToValue)
	{	    
	    boolean bln = evtPRangeToValue.getValueBoolean();
	    
	    super._btnReset_.setEnabled(! bln);
	    super._btnOk_.setEnabled(! bln);
	}
    
    
    public DRangeToValue(DRangeAbstractListener draListenerParent, Component cmpFrameOwner,
        String strTitleApplication, String strTitleDialog, String strTextArea, String strTitlePanel,
        int intRangeMin, int intRangeMax, int intRangeDefault,
            int intMajorTickSpacing, int intMinorTickSpacing)
    {
        
        super(draListenerParent, cmpFrameOwner, strTitleApplication, strTitleDialog, strTextArea);
        addPRangeToValueListener(this);
        super._pra_ = new PRangeToValue(this._rtvListenerThis, strTitlePanel, intRangeMin, intRangeMax, intRangeDefault, intMajorTickSpacing, intMinorTickSpacing);
    }
    
    public void destroy()
    {
        removePRangeToValueListener(this);
        super.destroy();
        
    }
    
    // -------
    // PRIVATE
    
    // listeners
    private PRangeToValueListener _rtvListenerThis = null;
}
