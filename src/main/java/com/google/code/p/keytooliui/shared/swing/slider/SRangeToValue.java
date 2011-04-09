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
 
 
 package com.google.code.p.keytooliui.shared.swing.slider;

import com.google.code.p.keytooliui.shared.swing.boundedrangemodel.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

public final class SRangeToValue extends JSlider
{
    // ------
    // PUBLIC
    
    public int getDefault() { return this._rtv.getDefault(); }
    
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
    
    public SRangeToValue(int intRangeMin, int intRangeMax, int intRangeDefault, int intMajorTickSpacing, int intMinorTickSpacing)
    {
        this._rtv = new MRangeToValue(intRangeDefault, intRangeMin, intRangeMax);
        
        
        this.setMajorTickSpacing(intMajorTickSpacing);
        this.setMinorTickSpacing(intMinorTickSpacing);
        
    }
    
    public boolean init()
    {
        String f_strMethod = "init()";
        
        if (this._rtv == null)
        {
            MySystem.s_printOutError(this, f_strMethod, "nil this._rtv");
            return false;
        }
        
        if (! this._rtv.init())
        {
            MySystem.s_printOutError(this, f_strMethod, "failed");
            return false;
        }
        
        this.setModel(this._rtv);
        
        this.setPaintTicks(true);
        // turns on labels at major tick marks
        // requires spacing for major ticks to be non-zero
        this.setPaintLabels(true);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        
        return true;
    }
    
    public void destroy()
    {
        if (this._rtv != null)
        {
            this._rtv.destroy();
            this._rtv = null;
        }
    }
    
    // -------
    // PRIVATE
    
    private MRangeToValue _rtv = null;
}