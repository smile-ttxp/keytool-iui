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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;

/**
abstract class for all dialogRange
contains from top to bottom:

a panel with a text area

a panel that contains the range stuff

a button panel that contains from left to right:
. reset
. cancel
. ok


 known subclasses:
 . DRangeToRange
 . DRangeToValue
 . DRangeVsRange

**/


import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.*;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

 
import javax.swing.*;
 
import java.awt.*;
import java.awt.event.*;

abstract public class DRangeAbstract extends DEscapeAbstract implements
    ActionListener,
    DRangeAbstractListener
{
     // --------------------
    // FINAL STATIC PRIVATE
    
    
    final static private String _f_s_strBundleFileShort =
        com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
        ".DRangeAbstract" // class name
        ;
        
    final static private String _f_s_strBundleFileLong = _f_s_strBundleFileShort + ".properties";
    
    // --------------
    // STATIC PRIVATE
        
    static private String _s_strTextButtonReset;
    static private String _s_strTextButtonCancel;
    static private String _s_strTextButtonOk;
    
    static private String _s_strTipButtonReset;
    static private String _s_strTipButtonCancel;
    static private String _s_strTipButtonOk;
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        final String f_strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DRangeAbstract";
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(_f_s_strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTextButtonReset = rbeResources.getString("textReset");     
	        _s_strTextButtonCancel = rbeResources.getString("textCancel");
	        _s_strTextButtonOk = rbeResources.getString("textOk"); 
	        _s_strTipButtonReset = rbeResources.getString("tipReset");     
	        _s_strTipButtonCancel = rbeResources.getString("tipCancel");
	        _s_strTipButtonOk = rbeResources.getString("tipOk"); 
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(f_strWhere, "excMissingResource caught, " + _f_s_strBundleFileLong);
        }
    }
    
    // ------
    // PUBLIC
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (this._pra_ == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pra_");
            return false;
        }
        
        if (! this._pra_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._pnlAll == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._pnlAll");
            return false;
        }
        
        this._pnlAll.add(this._pra_, BorderLayout.CENTER);
    
        return true;
    }
    
    public void destroy()
    {        
        removeDRangeAbstractListener(this);
        
        if (this._pra_ != null)
        {
            this._pra_.destroy();
            this._pra_ = null;
        }
        
        if (this._btnReset_ != null)
        {
            this._btnReset_.removeActionListener(this);
            this._btnReset_ = null;
        }
        
        
        if (this._btnCancel != null)
        {
            this._btnCancel.removeActionListener(this);
            this._btnCancel = null;
        }
        
        if (this._btnOk_ != null)
        {
            this._btnOk_.removeActionListener(this);
            this._btnOk_ = null;
        }
        
        super.destroy();
    }
    
    
    
    public void setEnabledReset(boolean bln)
    {
        this._btnReset_.setEnabled(bln);
    }
    
    public void setDefault()
    {
        if (this._pra_ != null)
            this._pra_.setDefault();
    }
    
    public void setValue(int[] intsValue)
    {
        if (this._pra_ != null)
            this._pra_.setValue(intsValue);
    }
    
    public int[] getValue()
    {
        return this._pra_.getValue();
    }
    
    public synchronized void addDRangeAbstractListener(DRangeAbstractListener draListener)
	{
	    if (this._draListenerThis == null)
            this._draListenerThis = DRangeAbstractEventMulticaster.add(this._draListenerThis, draListener);
	}
  
	public synchronized void removeDRangeAbstractListener(DRangeAbstractListener draListener)
	{
	    if (this._draListenerThis != null)
            this._draListenerThis = DRangeAbstractEventMulticaster.remove(this._draListenerThis, draListener);
	}
	
	public void valueChanged(DRangeAbstractEvent evtDRangeAbstract)
	{	    
	    if (this._draListenerParent != null)
	        this._draListenerParent.valueChanged(evtDRangeAbstract);
	}
    
    public void actionPerformed(ActionEvent aet)
    {
        String strMethod = "actionPerformed(aet)";
        
        JButton btnSource = (JButton) aet.getSource();
        
        if (btnSource == this._btnReset_)
        {
            this._pra_.setDefault();
            return;
        }
        
        if (btnSource == this._btnCancel)
        {
            super._cancel_();
            return;
        }
        
        if (btnSource == this._btnOk_)
        {
            int[] intsValue = this._pra_.getValue(); // tempo
            
            DRangeAbstractEvent evtNew = new DRangeAbstractEvent(
                this, DRangeAbstractEvent.DRANGEABSTRACTEVENT_VALUECHANGED, intsValue);

            if (this._draListenerThis != null)
                this._draListenerThis.valueChanged(evtNew);
            
            super._cancel_();
            return;
        }
           
        MySystem.s_printOutExit(this, strMethod, "unknown source");
        
    }
    
    
    
    
    // ---------
    // PROTECTED
    
    
    protected PRangeAbstract _pra_ = null;
    
    protected JButton _btnReset_ = null;
    protected JButton _btnOk_ = null;
    
    
    
    
    protected DRangeAbstract(
        DRangeAbstractListener draListenerParent, 
        Component cmpFrameOwner, 
        String strTitleApplication, 
        String strTitleDialog, 
        String strTextArea)
    {
        super((Frame) cmpFrameOwner, true);        
        this._draListenerParent = draListenerParent;
        
        
        setTitle(strTitleApplication + " - " + strTitleDialog);
        
        // listeners
        addDRangeAbstractListener(this);
        
        // children
        
        // --
        this._btnReset_ = new JButton();
        this._btnCancel = new JButton();
        this._btnOk_ = new JButton();
        
        JPanel pnlInfo = _createPanelLabel(strTextArea);
        JPanel pnlButtons = _createPanelButtons();
        
        
        
        // ----
        
        this._pnlAll = new JPanel();
        
        this._pnlAll.setLayout(new BorderLayout());
        
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_setEtched(this._pnlAll);
        
        // ????
           
        
        // ----
        
        this._pnlAll.add(pnlInfo, BorderLayout.NORTH);
	    this._pnlAll.add(pnlButtons, BorderLayout.SOUTH);
	    
	     // ----
        Container cntContentPane = getContentPane();
        cntContentPane.setLayout(new BorderLayout());
        cntContentPane.add(this._pnlAll, BorderLayout.CENTER);
    }
    
    // -------
    // PRIVATE
    
    // listeners
    private DRangeAbstractListener _draListenerThis = null;
    private DRangeAbstractListener _draListenerParent = null;
    
    
    private JButton _btnCancel = null;
    
    
    private JPanel _pnlAll = null;
    
    private JPanel _createPanelLabel(String strTextArea)
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BorderLayout());
        JTextArea taa = new JTextArea(strTextArea);
        taa.setEditable(false);
        taa.setBackground(getBackground());
        
        pnl.add(taa, BorderLayout.CENTER);
        return pnl;
    }
    
    private JPanel _createPanelButtons()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        
        
        this._btnReset_.addActionListener(this);
        this._btnReset_.setText(_s_strTextButtonReset);
        this._btnReset_.setToolTipText(_s_strTipButtonReset);
        
        
        this._btnCancel.addActionListener(this);
        this._btnCancel.setText(_s_strTextButtonCancel);
        this._btnCancel.setToolTipText(_s_strTipButtonCancel);
        
        
        this._btnOk_.addActionListener(this);
        this._btnOk_.setText(_s_strTextButtonOk);
        this._btnOk_.setToolTipText(_s_strTipButtonOk);
        
        
        
        // ----
        pnl.setAlignmentX(LEFT_ALIGNMENT);
	    pnl.setAlignmentY(TOP_ALIGNMENT);
        
        pnl.add(this._btnReset_);
        pnl.add(Box.createHorizontalStrut(10));
        pnl.add(this._btnCancel);
        pnl.add(Box.createHorizontalStrut(10));
        pnl.add(this._btnOk_);  
        
        
        
        return pnl;
    }
}