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

contains to sliderPanel
. on top: master 
. on bottom: slave

**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.slider.*;
 
import javax.swing.*;
import javax.swing.event.*;
 

final public class PRangeToRange extends PRangeAbstract implements
    ChangeListener
{
    // ------
    // PUBLIC
    
    public void setValue(int[] intsValue)
    {
        setValueSliderMaster(intsValue[0]);
        setValueSliderSlave(intsValue[1]);
    }
    
    public int[] getValue()
    {
        final int[] intsValue = new int[2];
        intsValue[0] = getValueSliderMaster();
        intsValue[1] = getValueSliderSlave();
        return intsValue;
    }
    
    public void setValueSliderMaster(int intVal)
    {
        this._rtv.setValueSlider(intVal);
    }
    
    public void setValueSliderSlave(int intVal)
    {
        this._rsl.setValueSlider(intVal);
    }
    
    public int getValueSliderMaster()
    {
        return this._rtv.getValueSlider();
    }
    
    public int getValueSliderSlave()
    {
        return this._rsl.getValueSlider();
    }
    
    public void stateChanged(ChangeEvent evtChange)
    {
        String f_strMethod = "stateChanged(evtChange)";
        
        if (! (evtChange.getSource() instanceof SRangeToValue))
        {
            MySystem.s_printOutExit(this, f_strMethod, "wrong source");    
        }
        
        SRangeToValue rtvSource = (SRangeToValue) evtChange.getSource();
        
        this._rsl.setMaximumSlider(rtvSource.getValue()-rtvSource.getMinimum());
    }
    
    public void setDefault()
    {
        if (this._rtv != null)
            this._rtv.setDefault();
            
        if (this._rsl != null)
            this._rsl.setDefault();
    }
    
    public PRangeToRange(String strTitleMaster,
        int intRangeMinMaster, int intRangeMaxMaster, int intRangeDefaultMaster,
        int intMajorTickSpacingMaster, int intMinorTickSpacingMaster,
        String strTitleSlave, int intRangeMinSlave, int intRangeDefaultSlave)
    {
        super();
        // -------
        // creates
        
        _createPanelRangeToValue(strTitleMaster, intRangeMinMaster, intRangeMaxMaster, intRangeDefaultMaster, intMajorTickSpacingMaster, intMinorTickSpacingMaster);
        
        int intRangeMaxSlave = intRangeDefaultMaster - intRangeMinMaster;
        _createPanelRangeSlave(strTitleSlave, intRangeMinSlave, intRangeDefaultSlave, intRangeMaxSlave);
        
        
        // listeners
        this._rtv.addChangeListenerSlider(this);
        
        // -------
        // layouts
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final int f_intVerticalStrut = 10;
        add(this._rtv);
        add(Box.createVerticalStrut(f_intVerticalStrut));
        add(this._rsl);
    }
    
    public boolean init()
    {
        return super._init_();
    }
    
    public void destroy()
    {
        super._destroy_();
        
        if (this._rtv != null)
        {
            this._rtv.removeChangeListenerSlider(this);
            this._rtv.destroy();
            this._rtv = null;
        }
        
        if (this._rsl != null)
        {
            this._rsl.destroy();
            this._rsl = null;
        }
    }
    
    // -------
    // PRIVATE
    
    private PRangeToValue _rtv = null;
    private PRangeSlave _rsl = null;
    
    private void _createPanelRangeToValue(String strTitlePanel,
        int intRangeMin, int intRangeMax, int intRangeDefault,
        int intMajorTickSpacing, int intMinorTickSpacing)
    {
        String f_strMethod = "_createPanelRangeToValue(...)";
        
        try
        {
            this._rtv = new PRangeToValue(null, strTitlePanel, intRangeMin, intRangeMax, intRangeDefault, intMajorTickSpacing, intMinorTickSpacing);
        }
        
        catch(java.lang.IllegalArgumentException excIllegalArgument)
        {
            excIllegalArgument.printStackTrace();
            MySystem.s_printOutExit(this, f_strMethod, "excIllegalArgument caught");
            
        }
        
        if (! this._rtv.init())
            MySystem.s_printOutExit(this, f_strMethod, "failed");
    }
    
    private void _createPanelRangeSlave(String strTitlePanel,
        int intRangeMin, int intRangeDefault, int intRangeMax)
    {
        String f_strMethod = "_createPanelRangeSlave(...)";
        
        try
        {
            this._rsl = new PRangeSlave(strTitlePanel, intRangeMin, intRangeDefault,
                intRangeMax);
        }
        
        catch(java.lang.IllegalArgumentException excIllegalArgument)
        {
            excIllegalArgument.printStackTrace();
            MySystem.s_printOutExit(this, f_strMethod, "excIllegalArgument caught");
            
        }
        
 
        if (! this._rsl.init())
            MySystem.s_printOutExit(this, f_strMethod, "failed");
    }
}