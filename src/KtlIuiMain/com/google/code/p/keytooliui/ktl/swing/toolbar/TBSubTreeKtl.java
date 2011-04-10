package com.google.code.p.keytooliui.ktl.swing.toolbar;

import javax.swing.SwingConstants;
import com.google.code.p.keytooliui.shared.swing.button.*;
import com.google.code.p.keytooliui.shared.swing.toolbar.*;

import com.google.code.p.keytooliui.ktl.swing.button.*;

public class TBSubTreeKtl extends TBSubAbstract
{
    public void setEnabledButtonFolderOpen(boolean bln)
    {
        if (this._btnFolderOpen != null)
            this._btnFolderOpen.setEnabled(bln);
    }
    
    public void setEnabledButtonFolderClose(boolean bln)
    {
        if (this._btnFolderClose != null)
            this._btnFolderClose.setEnabled(bln);
    }
    
    public TBSubTreeKtl(
            java.awt.event.ActionListener actListenerParentTree, // treeExpandOrCollapse
            java.awt.event.ActionListener actListenerParentPrint
            )
    {
        super("strHelpID",
        actListenerParentPrint,
        SwingConstants.HORIZONTAL, //int intOrientation,
        com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
                com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strAppliUIKtl16) //javax.swing.ImageIcon iinFrameFloatable // should be used once bug fixed "setFloatable(true), see below
        );
        
        
        this._btnFolderOpen = new BESFolderOpen16(actListenerParentTree);
        this._btnFolderClose = new BESFolderClose16(actListenerParentTree);
        
        
        // ----
        setBorderPainted(true);
        this.setFloatable(true);
        setName(System.getProperty("_appli.title") + " - " + "Tree's toolbar"); // in case of floatable toolbar
    }
    
    public boolean init()
    {
        if (! super.init())
            return false;
        
        /*if (! this._btnFolderOpen.init())
            return false;
            
        if (! this._btnFolderClose.init())
            return false;
          */  
         

        
        add(this._btnFolderOpen);
        add(this._btnFolderClose);
        add(super._btnPrint_);
        
        
        this._btnFolderOpen.setEnabled(false);
        this._btnFolderClose.setEnabled(false);
        
        super._btnPrint_.setToolTipText("print tree explorer ..."); 
         
        return true;
    }
    
    // -------
    // private
    
    private BEnabledState _btnFolderOpen = null;
    private BEnabledState _btnFolderClose = null;
    
}
