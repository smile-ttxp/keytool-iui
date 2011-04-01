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
 
 
package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    known subclasses:
    . PSelBtnTfdRngIntValidityKpr


    a panel that displays, from left to right:
    
    . 1 buttonSelect
    . 1 buttonClearSelection
    . 1 textfieldSelection
    
    textfield is not editable

    ... used to select an integer
    eg: validity
**/

import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import java.awt.*;

public class PSelBtnTfdRngInt extends PSelBtnTfdAbs implements
    DRangeAbstractListener
{
    // ------
    // PUBLIC
    
    public boolean setValueDefault()
    {            
        
        int[] intsValue = new int[1];
        
        intsValue[0] = com.google.code.p.keytooliui.ktl.util.jarsigner.KTLAbs.f_s_intCertValidityDefault;
        
        DRangeAbstractEvent evtNew = new DRangeAbstractEvent(
            this._dlgRangeIntegers, DRangeAbstractEvent.DRANGEABSTRACTEVENT_VALUECHANGED, intsValue);

        if (this._draListenerThis != null)
            this._draListenerThis.valueChanged(evtNew);
        
        
        return true;
    }
    
    public boolean init()
    {
        final String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // added sept 6, 2002
        super.setEnabledSelect(true);
        
        if (this._dlgRangeIntegers == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._dlgRangeIntegers");
            return false;
        }
        
        if (! this._dlgRangeIntegers.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        /**if (super._tfdCurSelection_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil super._tfdCurSelection_");
            return false;
        }**/
        
        /**javax.swing.SwingUtilities.invokeLater(new Runnable()
	    {
	        public void run()
	        {
	            String strMethod2 = strMethod + "." + "run()";
                        
                if (! setValueDefault())
                    MySystem.s_printOutExit(this, strMethod2, "failed");
	        }
	    });**/
	    

        // ending
        return true;
    }
    
    public void destroy()
    {
        super.destroy();
        
        removeDRangeAbstractListener(this);
        _destroyDialogRangeToValue();
        
         if (super._tfdCurSelection_ != null)
        {
            ((TFAbstract)super._tfdCurSelection_).destroy();
            super._tfdCurSelection_ = null;
        }
    }
    
    public synchronized void addDRangeAbstractListener(DRangeAbstractListener draListener)
	{
	    if (this._draListenerThis == null)
            this._draListenerThis = DRangeAbstractEventMulticaster.add(this._draListenerThis, draListener);
	}
  
	public synchronized void removeDRangeAbstractListener(DRangeAbstractListener draListener)
	{
	    if (this._draListenerThis != null)
	    {
            this._draListenerThis = DRangeAbstractEventMulticaster.remove(this._draListenerThis, draListener);
	        this._draListenerThis = null;
	    }
	}
	
	/**
	    sources:
	    
	    1) RangeToValue
	**/
	
	public void valueChanged(DRangeAbstractEvent evtDRangeAbstract)
	{	    
	    String strMethod = "valueChanged(evtDRangeAbstract)";
	    
	    
	    
	    if (! (evtDRangeAbstract.getSource() instanceof DRangeToValue))
	        MySystem.s_printOutExit(this, strMethod, "wrong instance");
	    
	    DRangeToValue draSource = (DRangeToValue) evtDRangeAbstract.getSource();
	    int[] ints = evtDRangeAbstract.getValueInts();
	    	    
	    if (draSource == this._dlgRangeIntegers)
	    {
	        super._tfdCurSelection_.setText(Integer.toString(ints[0]));
	        super._setSelectedValue_(true);
            super._btnClearSelection_.setEnabled(true); 
            return;
        }
        
        MySystem.s_printOutExit(this, strMethod, "unknown source");
	}
    
    public PSelBtnTfdRngInt(
        javax.swing.event.DocumentListener docListenerParent,
        Frame frmParent, 
        String strTitleAppli, 
        String strLabel,
        
        Object objDocPropValue,
        
        String strDlgRangeToValueTitle, 
        String strDlgRangeToValueLabel, 
        String strDlgRangeToValueTitlePanel,
        int intDialogRangeMin, 
        int intDialogRangeMax,
        int intDialogRangeDefault,
        boolean blnFieldRequired
        )
    {
        super(
            frmParent, 
            strTitleAppli, 
            strLabel,
            blnFieldRequired
            );
        
        String strMethod = "PSelBtnTfdRngInt(...)";
        
        super._btnSelect_ = new BESInteger16(this);
        super._tfdCurSelection_ = new TF4x20SelInt(docListenerParent);
        
        
        
        if (objDocPropValue == null)
            MySystem.s_printOutExit(this, strMethod, "nil objDocPropValue");
        
        super._tfdCurSelection_.getDocument().putProperty(
            com.google.code.p.keytooliui.shared.swing.textfield.TFAbstract.f_s_strDocPropKey, objDocPropValue);
        
        addDRangeAbstractListener(this);
        
        if (! _createDialogRangeToValue(
            strDlgRangeToValueTitle, 
            strDlgRangeToValueLabel, 
            strDlgRangeToValueTitlePanel,
            intDialogRangeMin, 
            intDialogRangeMax,
            intDialogRangeDefault))
        {
            MySystem.s_printOutExit(this, strMethod, "failed");
        }
    }
    
    // ---------
    // PROTECTED
    
    
    
    protected void _showDialog_()
    {
        String strMethod = "_showDialog_()";
        
        if (this._dlgRangeIntegers == null)
                MySystem.s_printOutExit(this, strMethod, "nil this._dlgRangeIntegers");
        
        //this._dlgRangeIntegers.setDefault();
        com.google.code.p.keytooliui.shared.awt.MyToolkit.s_beep();
        this._dlgRangeIntegers.setVisible(true); 
    }
    
    // -------
    // PRIVATE
    
    private DRangeAbstractListener _draListenerThis = null;
    private DRangeToValue _dlgRangeIntegers = null;
    

	private void _destroyDialogRangeToValue()
	{
	    if (this._dlgRangeIntegers == null)
	        return;
	    
	    this._dlgRangeIntegers.destroy();
	    this._dlgRangeIntegers = null;
	}
    
    private boolean _createDialogRangeToValue(
        String strDlgRangeToValueTitle, 
        String strDlgRangeToValueLabel, 
        String strDlgRangeToValueTitlePanel,
        int intDialogRangeMin, 
        int intDialogRangeMax,
        int intDialogRangeDefault)
	{
	    String strMethod = "_createDialogRangeToValue(...)";
	    
	    if (intDialogRangeMax < 1)
	    {
	        MySystem.s_printOutError(this, strMethod, "intDialogRangeMax < 1, intDialogRangeMax=" + intDialogRangeMax);
	        return false;
	    }
	    
	    int intDialogMajorTickSpacing = intDialogRangeMax/3;
	    int intDialogMinorTickSpacing = intDialogMajorTickSpacing/5;
       
        try
        {
        
            this._dlgRangeIntegers = new DRangeToValue(this._draListenerThis, super._frmParent_,
                super._strTitleAppli_, strDlgRangeToValueTitle, strDlgRangeToValueLabel, strDlgRangeToValueTitlePanel,
                intDialogRangeMin, intDialogRangeMax, intDialogRangeDefault,
                intDialogMajorTickSpacing, intDialogMinorTickSpacing);
        }
        
        catch(java.lang.IllegalArgumentException excIllegalArgument)
        {
            excIllegalArgument.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "excIllegalArgument caught");
            return false;
        }
        
        this._dlgRangeIntegers.setDefaultValid(false);
        this._dlgRangeIntegers.setEnabledReset(false);
        this._dlgRangeIntegers.setEnabledOk(true);
        
        return true;
	}
	
	// -------
    // PRIVATE
    
 
    
}