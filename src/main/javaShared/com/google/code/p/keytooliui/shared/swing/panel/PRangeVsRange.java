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
 
 
 package com.google.code.p.keytooliui.shared.swing.panel;

/**

contains to sliderPanel
. on top: master 
. on bottom: slave

**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.slider.*;
 
import javax.swing.*;
import javax.swing.event.*;


final public class PRangeVsRange extends PRangeAbstract implements
    ChangeListener
{
    // ------
    // PUBLIC
    
     public void setValue(int[] intsValue)
    {
        setValueSliderTop(intsValue[0]);
        setValueSliderBottom(intsValue[1]);
    }
    
    public int[] getValue()
    {
        final int[] intsValue = new int[2];
        intsValue[0] = getValueSliderTop();
        intsValue[1] = getValueSliderBottom();
        return intsValue;
    }
    
    public void setValueSliderTop(int intVal)
    {
        this._rtvTop.setValueSlider(intVal);
    }
    
    public void setValueSliderBottom(int intVal)
    {
        this._rtvBottom.setValueSlider(intVal);
    }
    
    
    public int getValueSliderTop()
    {
        return this._rtvTop.getValueSlider();
    }
    
    public int getValueSliderBottom()
    {
        return this._rtvBottom.getValueSlider();
    }
    
    public void stateChanged(ChangeEvent evtChange)
    {
        String f_strMethod = "stateChanged(evtChange)";
        
        if (! (evtChange.getSource() instanceof SRangeToValue))
        {
            MySystem.s_printOutExit(this, f_strMethod, "wrong source");
            
        }
        
         int intValueTop = _rtvTop.getValueSlider();
         int intValueBottom = _rtvBottom.getValueSlider();
        
        SRangeToValue rtvSource = (SRangeToValue) evtChange.getSource();
        
        if (this._rtvTop.owns(rtvSource))
        {
             if (intValueBottom < intValueTop+this._intSpacingMin)
                _rtvBottom.setValueSlider(intValueTop+this._intSpacingMin);
            
            return;
        }
        
        if (this._rtvBottom.owns(rtvSource))
        {
            if (intValueTop > intValueBottom-this._intSpacingMin)
                _rtvTop.setValueSlider(intValueBottom-this._intSpacingMin);    
            
            return;
        }
        
        MySystem.s_printOutExit(this, f_strMethod, "unknown SRangeToValue source");
        
    }
    
    public void setDefault()
    {
        if (_rtvTop != null)
            _rtvTop.setDefault();
           
        if (_rtvBottom != null)
            _rtvBottom.setDefault();
            
    }
    
    /**
        rule: 
            top slider value <= bottom slider - spacing
    **/
    
    public PRangeVsRange(
        String strTitleTop, int intRangeMinTop, int intRangeMaxTop, int intRangeDefaultTop,
        String strTitleBottom, int intRangeDefaultBottom,
        int intMajorTickSpacing, int intMinorTickSpacing, int intSpacingMin
        )
    {
        super();
        
        // not really necessary, should be retrieved by "minTop minus minBottom"
        this._intSpacingMin = intSpacingMin;
        // -------
        // creates
        
        this._rtvTop =  _createPanelRangeToValue(strTitleTop, intRangeMinTop, intRangeMaxTop, intRangeDefaultTop, intMajorTickSpacing, intMinorTickSpacing);
        this._rtvBottom =  _createPanelRangeToValue(strTitleBottom, intRangeMinTop+intSpacingMin, intRangeMaxTop+intSpacingMin, intRangeDefaultBottom, intMajorTickSpacing, intMinorTickSpacing);
        
        
        //int intRangeMaxSlave = intRangeDefaultMaster - intRangeMinMaster;
        //_createPanelRangeSlave(strTitleSlave, intRangeMinSlave, intRangeDefaultSlave, intRangeMaxSlave);
        
        
        // listeners
        this._rtvTop.addChangeListenerSlider(this);
        this._rtvBottom.addChangeListenerSlider(this);
        
        // -------
        // layouts
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        int f_intVerticalStrut = 10;
        add(this._rtvTop);
        add(Box.createVerticalStrut(f_intVerticalStrut));
        add(this._rtvBottom);
    }
    
    public boolean init()
    {
        return super._init_();
    }
    
    public void destroy()
    {
        super._destroy_();
        
        if (this._rtvTop != null)
        {
            this._rtvTop.removeChangeListenerSlider(this);
            this._rtvTop.destroy();
            this._rtvTop = null;
        }
        
        if (this._rtvBottom != null)
        {
            this._rtvBottom.removeChangeListenerSlider(this);
            this._rtvBottom.destroy();
            this._rtvBottom = null;
        }
    }
    
    // -------
    // PRIVATE
    
    private PRangeToValue _rtvTop = null;
    private PRangeToValue _rtvBottom = null;
    private int _intSpacingMin = 0; // dummy value
    
    private PRangeToValue _createPanelRangeToValue(String strTitlePanel,
        int intRangeMin, int intRangeMax, int intRangeDefault,
        int intMajorTickSpacing, int intMinorTickSpacing)
    {
        String f_strMethod = "_createPanelRangeToValue(...)";
        
        PRangeToValue rtv = null;
        
        try
        {
            rtv = new PRangeToValue(null, strTitlePanel, intRangeMin, intRangeMax, intRangeDefault, intMajorTickSpacing, intMinorTickSpacing);
        }
        
        catch(java.lang.IllegalArgumentException excIllegalArgument)
        {
            excIllegalArgument.printStackTrace();
            MySystem.s_printOutExit(this, f_strMethod, "excIllegalArgument caught");
            
        }
        
        rtv.init();
        return rtv;
    }
}