package com.google.code.p.keytooliui.shared.swing.button;


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.event.*;

abstract public class BAbs extends JButton
{    
    // ------
    // public
    
    public void destroy()
    {
        if (this._actListenerParent != null)
        {
            removeActionListener(this._actListenerParent);
            this._actListenerParent = null;
        }
    }
   
    // ---------
    // protected
    
    protected BAbs(String strText, String strToolTip, ActionListener actListenerParent)
    {
        this(strText, strToolTip);
        this._actListenerParent = actListenerParent;
        
        if (this._actListenerParent != null)
            addActionListener(this._actListenerParent);
    }
    
    protected BAbs(String strText, String strToolTip)
    {
        if (strText != null)
            setText(strText);
        
        if (strToolTip != null)
            setToolTipText(strToolTip);
    }
    
    private ActionListener _actListenerParent = null;
}
