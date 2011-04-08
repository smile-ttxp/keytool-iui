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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;

/**
    known subclasses:
    . PSelBtnAbs ("Btn" for "Button")
    . PSelCmbAbs ("Cmb" for "ComboBox")

    a panel that contains 1 label on the left.
    
    The label may be resized in width
**/


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.google.code.p.keytooliui.shared.lang.MySystem;
import net.miginfocom.swing.MigLayout;

abstract public class PSelAbs extends JPanel
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    //final static public Dimension f_s_dimBoxX = new java.awt.Dimension(1, 1); // new java.awt.Dimension(4,1);
    
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_strClass = "com.google.code.p.keytooliui.shared.swing.panel.PSelAbs.";
        
    // -------------
    // STATIC PUBLIC
    
    
   
    
    /**
        tool used to align a vertical list of panelSelection
    **/
    
    static public boolean s_alignLabels(Vector<PSelAbs> vecPanels)
    {
        String strMethod = _f_strClass + "s_alignLabels(vecPanels)";
        
        
        if (vecPanels == null)
        {
            MySystem.s_printOutError(strMethod, "nil vecPanels");
            return false;
        }
        
        if (vecPanels.size() < 2)
            return true;
        
        // ----
        
        /** ----------------------------------
            equalizing the width of the labels
        **/
        
        int intMin = -1;
        int intPref = -1;
        int intMax = -1;
        PSelAbs pnlCur = null;
        
        for (int i=0; i<vecPanels.size(); i++)
        {            
            try
            {
                pnlCur = (PSelAbs) vecPanels.elementAt(i);
            }
            
            catch(ClassCastException excClassCast)
            {
                excClassCast.printStackTrace();
                MySystem.s_printOutError(strMethod, "excClassCast caught");
                return false;
            }
            
            JLabel lblCur = pnlCur.getLabel();
            
            int intMinCur = lblCur.getMinimumSize().width;
            
            if (intMinCur > intMin)
                intMin = intMinCur;
                
            int intMaxCur = lblCur.getMaximumSize().width;
            
            if (intMaxCur > intMax)
                intMax = intMaxCur;
                
            int intPrefCur = lblCur.getPreferredSize().width;
            
            if (intPrefCur > intPref)
                intPref = intPrefCur;
        }
        
        // ----
        
        for (int i=0; i<vecPanels.size(); i++)
        {
            try
            {
                pnlCur = (PSelAbs) vecPanels.elementAt(i);
            }
            
            catch(ClassCastException excClassCast)
            {
                excClassCast.printStackTrace();
                MySystem.s_printOutError(strMethod, "excClassCast caught");
                return false;
            }
            
            JLabel lblCur = pnlCur.getLabel();
        
            lblCur.setMinimumSize(new Dimension(intMin, lblCur.getMinimumSize().height));
            lblCur.setMaximumSize(new Dimension(intMax, lblCur.getMaximumSize().height));
            lblCur.setPreferredSize(new Dimension(intPref, lblCur.getPreferredSize().height));
        }
        
        // ending
        return true;
    }
    
    // ------
    // PUBLIC
    
    public JLabel getLabel() { return this._lbl; }
    
    public void destroy()
    {
        this._lbl = null;
        this._pnl_ = null;
        this._iinReqFieldOk_ = null;
        this._iinReqFieldWrong_ = null;
    }
    
    public boolean init()
    {    
        this.setAlignmentX(LEFT_ALIGNMENT);
	 
	    _addItem();

        // ending
        return true;
    }
    
    public void setFieldRequired(boolean bln)
    {
        String strMethod ="setFieldRequired(bln)";
        
        if (bln)
        {    
            this._lbl.setToolTipText("required field");
            
            //String strName = null;
            
            String strNameWrong = "star_red.gif"; // tempo
            String strNameOk = "star_green.gif"; // tempo
            
            this._iinReqFieldWrong_ = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strNameWrong);
            this._iinReqFieldOk_ = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strNameOk);
            
            if (this._iinReqFieldWrong_ == null)
                MySystem.s_printOutExit(this, strMethod, strNameWrong + ": nil this._iinReqFieldWrong_");
                
            if (this._iinReqFieldOk_ == null)
                MySystem.s_printOutExit(this, strMethod, strNameOk + ": nil this._iinReqFieldOk_");
                
            this._lbl.setIcon(this._iinReqFieldWrong_);
        }
        
        else
        {
            this._lbl.setToolTipText("optional field");
        
            String strName = "trg_trs11.gif"; // tempo
            ImageIcon iin = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strName);
            
            if (iin == null)
                MySystem.s_printOutExit(this, strMethod, strName + ": nil iin");
                
            this._lbl.setIcon(iin);
        }
    }
    
    // ---------
    // PROTECTED
    
    protected JPanel _pnl_ = null;
    
    protected ImageIcon _iinReqFieldOk_ = null;
    protected ImageIcon _iinReqFieldWrong_ = null;
    
    protected PSelAbs(String strLabel, boolean blnFieldRequired)
    {
        setLayout(new MigLayout("fill, ins 0"));
        
        this._pnl_ = new JPanel();
        this._pnl_.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        this._lbl = new JLabel(strLabel);

        this.setFieldRequired(blnFieldRequired);
	    this._lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
    }
    
    // -------
    // PRIVATE
    
    private JLabel _lbl = null;
    
    
    
    private void _addItem()
    {
	    this._pnl_.add(this._lbl);

	    add(this._pnl_);
    }
}