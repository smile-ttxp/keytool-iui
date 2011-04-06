package com.google.code.p.keytooliui.ktl.swing.internalframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import com.google.code.p.keytooliui.ktl.swing.panel.PTabUIAbs;
import com.google.code.p.keytooliui.shared.swing.panel.PTabAbstract;

abstract public class IFAbs extends JInternalFrame
{
    // ------
    // public
    
    public void setContextualHelpID()
    {
        if (this._pnl_ != null)
            this._pnl_.setContextualHelpID();
    }
    
    public boolean init()
    {
        if (! this._pnl_.init())
            return false;
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(this._pnl_, BorderLayout.CENTER);
        
        
        try 
        {
            super.setMaximum(true);
        } 
        
        catch (PropertyVetoException ex) 
        {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }


    
    
    public void destroy()
    {
        if (this._pnl_ != null)
        {
            this._pnl_.destroy();
            this._pnl_ = null;
        }
    }
    
    // ---------
    // protected
    
    protected PTabAbstract /*PTabUIAbs*/ _pnl_ = null;
    
    protected IFAbs()
    {
        setBounds(0, 0, 800, 600);

        setResizable(false);
        setIconifiable(false);
        setMaximizable(false);
    }
    
    
    
}
