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

import com.google.code.p.keytooliui.shared.swing.textfield.*;
import com.google.code.p.keytooliui.shared.swing.slider.*;
import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.event.*;


import java.awt.event.*;


public class PRangeSlave extends JPanel
{    
    // ------
    // PUBLIC
    
    public int getValueSlider()
    {
        return this._rtv.getValue();
    }
    
    public void setValueSlider(int intValue)
    {
        this._rtv.setValue(intValue);
    }
    
    public void setMaximumSlider(int intMaxSlider)
    {
        this._rtv.setMaximum(intMaxSlider);
        
        // ----
        
        int intRange = intMaxSlider - this._rtv.getMinimum();
        
        int intMajorTickSpacing = 5;
        int intMinorTickSpacing = 1;
        
        if (intRange > 5)
        {
            intMajorTickSpacing = intRange/3;
            intMinorTickSpacing = intMajorTickSpacing/5;
        }
        
        
        
        
        
        if (this._rtv.getMajorTickSpacing() != intMajorTickSpacing)
        {
            /**
            
            BUG: can't update tickSpacing
            
            **/
            
            
            //this._rtv.setMajorTickSpacing(intMajorTickSpacing);
            //this._rtv.setMinorTickSpacing(intMinorTickSpacing);
            //this.paintComponent(this.getG
            //_rtv.repaint();
            //update(getGraphics());
            //repaint();
            //_rtv.repaint();
            //System.out.println("repainting");
        }
        
     
        
        
        // ----
        
        if (this._rtv.getMaximum() == this._rtv.getMinimum())
        {
            this._rtv.setEnabled(false);
            this._dfd.setEnabled(false);
        }
        
        else
        {
            this._rtv.setEnabled(true);
            this._dfd.setEnabled(true);
        }
    }
    
    public void setDefault()
    {
        if (this._rtv != null)
            this._rtv.setDefault();
    }
    
    public PRangeSlave(
        String strTitleBorder, int intRangeMin, int intRangeDefault,  // default
        int intRangeMax
        )
    {
        
         int intRange = intRangeMax - intRangeMin;
        
        int intMajorTickSpacing = 5;
        int intMinorTickSpacing = 1;
        
        if (intRange > 5)
        {
            intMajorTickSpacing = intRange/3;
            intMinorTickSpacing = intMajorTickSpacing/5;
        }
        
        // ----
        
        
        
         setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(strTitleBorder),
			BorderFactory.createEmptyBorder(5,5,5,5)));
	    
	    // ----
	    
	    //Add the text field.  It initially displays "0" 

        this._dfd = new TFDecimal(0); 
        
	    
        // ----
        
        
        this._rtv = new SRangeToValue(intRangeMin, intRangeMax, intRangeDefault, intMajorTickSpacing, intMinorTickSpacing);
        this._rtv.init();
        
        this._rtv.setPaintTicks(false); // tempo, because of bug
        this._rtv.setPaintLabels(false); // tempo, because of bug
        // ----
        
        this._dfd.setValue(this._rtv.getValue());
	    
	    this._dfd.addActionListener(new ActionListener()
	    {
            public void actionPerformed(ActionEvent e)
            {
                _rtv.setValue((int) _dfd.getValue());
            }
        });
        
        this._rtv.addChangeListener(new ChangeListener()
	    {
	        public void stateChanged(ChangeEvent e)
	        {
		        _dfd.setValue(_rtv.getValue());
	        }
	    });
        
        //Make the textfield/_sld group a fixed size.
	    JPanel pnl = new JPanel()
	    /*{
	        public Dimension getMinimumSize()
	        {
		        return getPreferredSize();
	        }
	        
	        public Dimension getPreferredSize()
	        {
		        return new Dimension(_f_s_intPreferredW, super.getPreferredSize().height);
	        }
	        
	        public Dimension getMaximumSize()
	        {
		        return getPreferredSize();
	        }
	    }*/;
	    
	    
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
        return true;
    }
    
    public void destroy()
    {
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
    
    private SRangeToValue _rtv = null;
    private TFDecimal _dfd = null;
}