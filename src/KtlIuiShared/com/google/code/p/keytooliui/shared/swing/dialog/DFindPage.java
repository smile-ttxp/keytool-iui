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


contains from top to bottom:

a panel with:
 left: a label
 right: a text area

a panel with:
 2 checkBoxes
 
a labeled panel with:
 2 radioButtons

a button panel that contains from left to right:
. cancel
. find nex

**/


import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import com.google.code.p.keytooliui.shared.awt.awtevent.DFindPageEvent;
import com.google.code.p.keytooliui.shared.awt.awteventmulticaster.DFindPageEventMulticaster;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.util.eventlistener.DFindPageListener;

final public class DFindPage extends DFindAbstract implements DFindPageListener
{
    // ---------------------
    // STATIC PRIVATE STRING
    
    static private String _s_strTitle = null;
    static private String _s_strButtonTextFindNext = null;
    static private String _s_strButtonTipFindNext = null;
    static private String _s_strBorderTitleDirection = null;
    static private String _s_strRadioTextUp = null;
    static private String _s_strRadioTextDown = null;
    static private String _s_strRadioTipUp = null;
    static private String _s_strRadioTipDown = null;
    
    
    // ------------------
    // STATIC INITIALIZER

    static
    {
        String strWhere = "com.google.code.p.keytooliui.shared.swing.dialog.DFindPage";
        
        String strBundleFileShort =
            com.google.code.p.keytooliui.shared.Shared.f_s_strBundleDir +
            ".DFindPage" // class name
            ;
        
        try
        {
            java.util.ResourceBundle rbeResources = java.util.ResourceBundle.getBundle(strBundleFileShort, 
                java.util.Locale.getDefault());
                
            _s_strTitle = rbeResources.getString("title"); 

            _s_strBorderTitleDirection = rbeResources.getString("borderTitleDirection"); 
            
            _s_strRadioTextUp = rbeResources.getString("radioTextUp");
            _s_strRadioTextDown = rbeResources.getString("radioTextDown");
            _s_strRadioTipUp = rbeResources.getString("radioTipUp");
            _s_strRadioTipDown = rbeResources.getString("radioTipDown");
            
            _s_strButtonTextFindNext = rbeResources.getString("buttonTextFindNext");
            _s_strButtonTipFindNext = rbeResources.getString("buttonTipFindNext");
        }
        
        catch (java.util.MissingResourceException excMissingResource)
        {
            excMissingResource.printStackTrace();
            MySystem.s_printOutExit(strWhere, "excMissingResource caught");
        }
        
        strWhere = null;
        strBundleFileShort = null;
    } 
     
    // ------
    // PUBLIC
    
    public void keyReleased(KeyEvent evtKey)
    {
        //String strMethod = "keyReleased(evtKey)";
        //MySystem.s_printOutTrace(this, strMethod, "evtKey.getKeyCode()=" + evtKey.getKeyCode());
    
        int intKeyCode = evtKey.getKeyCode();
        
        if (intKeyCode == java.awt.event.KeyEvent.VK_ENTER)
        {
            //MySystem.s_printOutTrace(this, strMethod, "intKeyCode == java.awt.event.KeyEvent.VK_ENTER");
            
            if (super._btnFindNow_.isEnabled())
            {
                if (this._dfpListenerThis != null)
                    this._dfpListenerThis.findNext(
                        new DFindPageEvent(this, DFindPageEvent.DFINDPAGEEVENT_FINDNEXT,
                            super._tfd_.getText(),
                            super._cbxWholeWord_.isSelected(),
                            super._cbxMatchCase_.isSelected(),
                            this._rbnDirectionDown.isSelected()));
            }
        }
    }
    
    public void destroy()
    {
        removeDFindPageListener(this);
        this._dfpListenerParent = null;
        this._rbnDirectionDown = null;
        super.destroy();
    }
    
    public DFindPage(DFindPageListener dfpListenerParent, Component cmpFrameOwner, String strTitleApplication)
    {
        super((Frame) cmpFrameOwner, strTitleApplication, _s_strTitle, _s_strButtonTextFindNext, _s_strButtonTipFindNext);
        
        this._dfpListenerParent = dfpListenerParent;


        addDFindPageListener(this);
        
        // children
        JPanel pnlRadio = _createPanelRadio();
       
        super._pnlTop_.add(Box.createVerticalStrut(10));
        super._pnlTop_.add(pnlRadio);
    }
    
    
    public synchronized void addDFindPageListener(DFindPageListener dfpListener)
	{
	    if (this._dfpListenerThis == null)
            this._dfpListenerThis = DFindPageEventMulticaster.add(this._dfpListenerThis, dfpListener);
	}
  
	public synchronized void removeDFindPageListener(DFindPageListener dfpListener)
	{
	    if (this._dfpListenerThis != null)
	    {
            this._dfpListenerThis = DFindPageEventMulticaster.remove(this._dfpListenerThis, dfpListener);
            this._dfpListenerThis = null;
	    }
	}
	
	public void findNext(DFindPageEvent evtDFindPage)
	{	    
	    if (this._dfpListenerParent != null)
	        this._dfpListenerParent.findNext(evtDFindPage);
	}
	
    
    
    public void actionPerformed(ActionEvent evtAction)
    {
        String strMethod = "actionPerformed(evtAction)";
       
        if (! (evtAction.getSource() instanceof JButton))
        {
            MySystem.s_printOutExit(this, strMethod, "wrong instance");
        }
        
        JButton btnSource = (JButton) evtAction.getSource();
        
        if (btnSource == super._btnFindNow_)
        {  
            
            if (this._dfpListenerThis != null)
                this._dfpListenerThis.findNext(
                    new DFindPageEvent(this, DFindPageEvent.DFINDPAGEEVENT_FINDNEXT,
                        super._tfd_.getText(),
                        super._cbxWholeWord_.isSelected(),
                        super._cbxMatchCase_.isSelected(),
                        this._rbnDirectionDown.isSelected()));
                    
            return;
        }
           
        
        super.actionPerformed(evtAction);
    }
    
    
    // -------
    // PRIVATE
    
    
    private JRadioButton _rbnDirectionDown = null;
    
    // listeners
    private DFindPageListener _dfpListenerThis = null;
    private DFindPageListener _dfpListenerParent = null;
    
    
    
    private JPanel _createPanelRadio()
    {
        JPanel pnl = new JPanel();
      
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        pnl.setBorder(new TitledBorder(_s_strBorderTitleDirection));
        
        ButtonGroup bgp = new ButtonGroup();
 
        JRadioButton rbnDirectionUp = new JRadioButton();
        this._rbnDirectionDown = new JRadioButton();
        
        bgp.add(rbnDirectionUp);
        bgp.add(this._rbnDirectionDown);
        
        this._rbnDirectionDown.setSelected(true);
        
        rbnDirectionUp.setText(_s_strRadioTextUp);
        this._rbnDirectionDown.setText(_s_strRadioTextDown);
        
        rbnDirectionUp.setToolTipText(_s_strRadioTipUp);
        this._rbnDirectionDown.setToolTipText(_s_strRadioTipDown);
        
        pnl.add(rbnDirectionUp);
        pnl.add(Box.createHorizontalStrut(10));
        pnl.add(this._rbnDirectionDown);
         
        return pnl;
    }
    
}