/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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
    known subclasses:
    . PListFileAbs 
    . PListHLocalJarAnchor
    
    TODO: 
    1) move the files above to the same directory as the one containing this class
    2) change the name of the subclasses mentionned above
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

abstract public class PListDblClickAbs extends JPanel implements
    ListSelectionListener,
    MouseListener
{
    
    // ------
    // PUBLIC
    
    public boolean doubleClickedSelection(MouseEvent evtMouse)
    { 
        String strMethod = "doubleClickedSelection(evtMouse)";
        
        if (evtMouse.getClickCount() != 2)
            return false;
        
        if (this._lstBox_ == null)
            MySystem.s_printOutExit(this, strMethod, "nil this._lstBox_");
                
        Object objSelection = this._lstBox_.getSelectedValue();
            
            
            
        if (objSelection == null)
            return false;
            
        // ----
            
        Point pntClick = new Point(evtMouse.getX(), evtMouse.getY());
            
        int intId = this._lstBox_.locationToIndex(pntClick);
            
        if (intId == -1)
            return false;
            
        return true;
    }
    
    public void mousePressed(MouseEvent evtMouse) {}
    public void mouseReleased(MouseEvent evtMouse) {}
    public void mouseEntered(MouseEvent evtMouse) {}
    public void mouseExited(MouseEvent evtMouse) {}
    
    public void destroy()
    {
        
        if (this._blnIsSelectable_)
	    {
	        if (this._lstBox_ != null)
	        {
	            this._lstBox_.removeListSelectionListener(this);
	            this._lstBox_.removeMouseListener(this);
	            this._lstBox_ = null;
	        }
        }
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._lstBox_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._lstBox_");
            return false;
        }
        
        this._lstBox_.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
	    
	    
	    if (this._blnIsSelectable_)
	    {
	        this._lstBox_.addListSelectionListener(this);
	        // january 23rd, 2001: handling double-clicking
            this._lstBox_.addMouseListener(this); 
	    }
	    else
	        this._lstBox_.setBackground(getBackground());
	    
	    if (this._speList == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._speList");
            return false;
        }
	    
	    this._speList.setViewportView(this._lstBox_);

	    // ----
	    
	    //com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(this, "anchors list");
	    add(this._speList);
	    
        return true;
    }
    
    public void setEnabled(boolean bln)
    {
        super.setEnabled(bln);
        
        if (this._speList != null) // !! troubleshootings, no effects !!
            this._speList.setEnabled(bln);
        
        if (this._lstBox_ != null)
            this._lstBox_.setEnabled(bln);
    }
    
    // ---------
    // PROTECTED
    
    protected boolean _blnIsSelectable_ = false;
    protected DefaultListModel _dlm_ = null;
    protected JList _lstBox_ = null;
    
    
    
    

    protected PListDblClickAbs(boolean blnIsSelectable)
    {
        super();
        this._blnIsSelectable_ = blnIsSelectable;
        
        this._dlm_ = new DefaultListModel();
        this._speList = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this._lstBox_ = new JList(this._dlm_);
        
        setLayout(new BorderLayout());
    }
    
    // -------
    // PRIVATE

    private JScrollPane _speList = null;
}