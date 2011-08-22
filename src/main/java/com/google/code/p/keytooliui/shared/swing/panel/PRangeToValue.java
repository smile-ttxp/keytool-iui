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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.swing.slider.*;


import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;

public final class PRangeToValue extends PRangeAbstract implements
    ChangeListener,
    PRangeToValueListener
{  
    // ------
    // PUBLIC
    
    public synchronized void addPRangeToValueListener(PRangeToValueListener rtvListener)
	{
	    if (this._rtvListenerThis == null)
            this._rtvListenerThis = PRangeToValueEventMulticaster.add(this._rtvListenerThis, rtvListener);
	}
  
	public synchronized void removePRangeToValueListener(PRangeToValueListener rtvListener)
	{
	    if (this._rtvListenerThis != null)
            {
                this._rtvListenerThis = PRangeToValueEventMulticaster.remove(this._rtvListenerThis, rtvListener);
                this._rtvListenerThis = null;
            }
        }
	
	public void defaultSwapped(PRangeToValueEvent evtPRangeToValue)
	{	    
	    if (this._rtvListenerParent != null)
	        this._rtvListenerParent.defaultSwapped(evtPRangeToValue);
	}
    
    public void setDefaultValid(boolean bln)
    {
        this._blnDefaultValid = bln;
    }
    
    public void setValue(int[] intsValue)
    {
        setValueSlider(intsValue[0]);
    }
    
    public int[] getValue()
    {
        int[] intsValue = new int[1];
        intsValue[0] = getValueSlider();
        return intsValue;
    }
    
    public boolean owns(SRangeToValue rtv)
    {
        if (rtv == null)
            return false;
            
        if (this._rtv == null)
            return false;
            
        if (rtv == this._rtv)
            return true;
            
        return false;
    }
    
    public int getValueSlider()
    {
        return this._rtv.getValue();
    }
    
    public void setValueSlider(int intValue)
    {
        this._rtv.setValue(intValue);
    }
    
    
    public void addChangeListenerSlider(ChangeListener clr)
    {
        if (this._rtv!=null && clr==null)
            this._rtv.addChangeListener(clr);
    }
    
    void removeChangeListenerSlider(ChangeListener clr)
    {
        if (this._rtv!=null && clr==null)
            this._rtv.removeChangeListener(clr);
    }
    
    public void stateChanged(ChangeEvent evtChange)
    {
        String f_strMethod = "stateChanged(evtChange)";
        
        if (! (evtChange.getSource() instanceof SRangeToValue))
            MySystem.s_printOutExit(this, f_strMethod, "wrong source");
        
        SRangeToValue rtvSource = (SRangeToValue) evtChange.getSource();
        
        int intValueNew = rtvSource.getValue();
        
        this._dfd.setValue(intValueNew);
    
        int intValueDefault = rtvSource.getDefault();
        _checkForDefaultSwapped(intValueDefault, intValueNew);   
    }
    
    public void changeDefault(int intValue)
    {
        if (this._rtv != null)
            this._rtv.changeDefault(intValue);
    }
    
    public void setDefault()
    {
        if (this._rtv != null)
            this._rtv.setDefault();
    }
    
    public PRangeToValue(
        PRangeToValueListener rtvListenerParent,
        String strTitleBorder,
        int intRangeMin,
        int intRangeMax,
        int intRangeDefault,
        int intMajorTickSpacing,
        int intMinorTickSpacing)
    {
        String f_strMethod = "PRangeToValue(...)";
        
        this._rtvListenerParent = rtvListenerParent;
        // listeners
        addPRangeToValueListener(this);
        
        setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(strTitleBorder),
			BorderFactory.createEmptyBorder(5,5,5,5)));
	    
	    // ----
	    
	    //Add the text field.  It initially displays "0"
	    
        this._dfd = new TFDecimal(0); 
	    
        // ----
        
        this._rtv = new SRangeToValue(intRangeMin, intRangeMax, intRangeDefault, intMajorTickSpacing, intMinorTickSpacing);
        
        if (! this._rtv.init())
            MySystem.s_printOutExit(this, f_strMethod, "failed");
        
        // ----
        
        this._dfd.setValue(this._rtv.getValue());
	    
	    this._dfd.addActionListener(new ActionListener()
	    {
            public void actionPerformed(ActionEvent evtAction)
            {
                String f_strMethod = "actionPerfomed(evtAction)";
                
                //try
                {
                    double dblValue = _dfd.getValue();
                    _rtv.setValue((int) dblValue);
                    
                    //System.out.println(">> dblValue=" + dblValue);
                    //System.out.println(">> (int) dblValue=" + (int) dblValue);
                }
                
                /*catch(ParseException excParse)
                {
                    excParse.printStackTrace();
                    MySystem.s_printOutWarning(this, f_strMethod, "exception caught, ignoring");
                    return;
                }
                */
                
            }
        });
        
        this._rtv.addChangeListener(this);
        
        //Make the textfield/_sld group a fixed size.
	    JPanel pnl = new JPanel();
	    
	    pnl.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
	    pnl.add(this._dfd);
	    pnl.add(this._rtv);

	    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(pnl);
	    pnl.setAlignmentY(TOP_ALIGNMENT);
    }
    
    public boolean init()
    {
        return super._init_();
    }
    
    public void destroy()
    {
        super._destroy_();        
        removePRangeToValueListener(this);
   
       
        if (this._dfd != null)
        {
            this._dfd.destroy();
            this._dfd = null;
        }
        
        if (this._rtv != null)
        {
            this._rtv.destroy();
            this._rtv = null;
        }
       
    }
    
    // -------
    // PRIVATE
    
    // listeners
    private PRangeToValueListener _rtvListenerThis = null;
    private PRangeToValueListener _rtvListenerParent = null;
    
    private SRangeToValue _rtv = null;
    private TFDecimal _dfd = null;
    private boolean _blnDefault = true;
    
    private boolean _blnDefaultValid = false;
    
    private void _checkForDefaultSwapped(int intValueDefault, int intValueNew)
    {
        //String f_strMethod = "_checkForDefaultSwapped(intValueDefault, intValueNew)";
        
        if (! _blnDefaultValid)
            return;
        
        PRangeToValueEvent evtNew = new PRangeToValueEvent(this, PRangeToValueEvent.PRANGETOVALUEEVENT_DEFAULTSWAPPED, (intValueDefault == intValueNew));
        
        
        if (this._rtvListenerThis != null)
            this._rtvListenerThis.defaultSwapped(evtNew);
    }

    
}
