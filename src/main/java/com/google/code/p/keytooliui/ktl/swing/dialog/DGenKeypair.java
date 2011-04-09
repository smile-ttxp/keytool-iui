package com.google.code.p.keytooliui.ktl.swing.dialog;

/**
    a dialog that also generate keypair in a separate Thread,
    generation maybe cancelled by user
    
    dialog's parent is the main frame
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.swing.dialog.*;
import com.google.code.p.keytooliui.shared.swing.button.*;

import javax.swing.*;
import javax.swing.border.*;

// ----
import java.security.KeyPairGenerator;
import java.security.KeyPair;
// ----

import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;

public final class DGenKeypair extends DEscapeAbstract implements
    ActionListener
{
    // --------------------
    // PRIVATE STATIC FINAL
    
    private static final String _f_s_strTitle = "generate keypair";
    private static final String _f_s_strLabel1 = "Now generating keypair";
    
    // ------
    // PUBLIC
    
    
    
    public KeyPair getKeypair() { return this._kprNew; }
    
    public void actionPerformed(ActionEvent evt) 
    {
        this._cancel_();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        // ----
        JLabel lbl1 = new JLabel();
        JLabel lbl2 = new JLabel();
        JButton btnCancel = new BCancel((ActionListener) this);
        
        
        // ----
        
        
        lbl1.setText(DGenKeypair._f_s_strLabel1);
        lbl2.setText(DLoadingAbs.s_strLabelWait);
	    
	    if (DLoadingAbs.s_iinLoading == null)
	    {
	        MySystem.s_printOutError(this, strMethod, "nil DLoadingAbs.s_iinLoading"); 
	        return false;
	    }
        
        lbl1.setIcon(DLoadingAbs.s_iinLoading);
        

        lbl1.setHorizontalAlignment(JLabel.CENTER);
        lbl2.setHorizontalAlignment(JLabel.RIGHT);
        

        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());
        
        pnl.add(lbl1, BorderLayout.CENTER);
        pnl.add(lbl2, BorderLayout.SOUTH);
        
        
        JPanel pnlAction = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlAction.add(btnCancel);

        
        getContentPane().add(pnl, BorderLayout.CENTER);
        getContentPane().add(pnlAction, BorderLayout.SOUTH);

        
        
        setResizable(false);

        // ----
        _doJob();

        
        return true;
    }
    
    
    public DGenKeypair(
        Frame frmParent, 
        KeyPairGenerator kpg
        )
    {
        super(
            frmParent, 
            //strTitleAppli + " - " + DGenKeypair._f_s_strTitle,
            true
            
            
            );
        
        this._kpg = kpg;
        
        // --
        // ----
        JPanel pnl = new JPanel();
        pnl.setLayout(new java.awt.BorderLayout());
        
        Dimension dimSize = new java.awt.Dimension(240, 120); // contentPane's size
        pnl.setPreferredSize(dimSize);
        pnl.setMinimumSize(dimSize);
        
        setContentPane(pnl);
    }
    
    // ---------
    // PROTECTED
    
    protected void _cancel_()
    {
        /*if ((this._thr != null) && (this._thr.isAlive()))
        {
            this._thr.interrupt();
        }*/
        
        this._thr = null;
        
        super._cancel_();
    }
    
    // -------
    // PRIVATE

    
    private KeyPairGenerator _kpg = null;
    private KeyPair _kprNew = null;
    private Thread _thr = null;
    
    /**
     * Start key pair generation in a separate thread.
     */
    private void _doJob()
    {
        GenKeypair gkp = new GenKeypair((JDialog) this);
        
        this._thr = new Thread(gkp);
        this._thr.setPriority(Thread.MIN_PRIORITY);
        this._thr.start();
    }
    
    
    // ---------
    // BEG CLASS
    
    private class GenKeypair implements Runnable
    {
        // ------
        // public
        
        public void run()
        {
            KeyPair kprNew = null;
            
            // do job
            if (_kpg != null)
            {
                kprNew = _kpg.generateKeyPair();
                _kprNew = kprNew;
            }
            // done
            
            

            if (this._dlgOwner != null)
                if (this._dlgOwner.isShowing())
                    _cancel_();    
        }
        
        // -------
        // private
        
        private JDialog _dlgOwner = null;
        
        private GenKeypair(JDialog dlgOwner)
        {
            this._dlgOwner = dlgOwner;
        }
        
        
        
    }
    
    // ---------
    // END CLASS
}