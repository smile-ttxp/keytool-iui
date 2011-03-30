package com.google.code.p.keytooliui.ktl.swing.panel;
 
 /**
    contains the following:
    . panelMode
    . panelJWSOnOff (JWS means Java(TM) Web Start

 
 **/
 
 import com.google.code.p.keytooliui.ktl.util.jarsigner.*;
 import com.google.code.p.keytooliui.ktl.swing.menuitem.*;
 
 import com.google.code.p.keytooliui.shared.swing.panel.*;
 import com.google.code.p.keytooliui.shared.swing.label.*;
 import com.google.code.p.keytooliui.shared.lang.*;
 import com.google.code.p.keytooliui.shared.io.*;

 import javax.swing.border.*;
 import javax.swing.*;
 
 import java.awt.*;
 import java.util.*;
 import java.io.*;
 
 final public class PTabHelpAppliAdvancedUI extends PTabHelpAppliAdvancedAbs
 {

    // ------
    // PUBLIC
    
    public PTabHelpAppliAdvancedUI()
    {
        super();       
        
        String strMethod = "PTabHelpAppliAdvancedUI()";
        
        JPanel pnlCerts = _createPanelCerts();
        
        if (pnlCerts == null)
            MySystem.s_printOutExit(this, strMethod, "nil pnlCerts");
             
        add(Box.createRigidArea(new Dimension(1,30)));
        add(pnlCerts);
    }
    
    // -------
    // PRIVATE
    
    private JPanel _createPanelCerts()
    {
        String strMethod = "_createPanelCerts()";
        
        JPanel pnl = new JPanel();
        
        // --
        
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(pnl, MIKstJksCrtAbs.s_strSuffix + "s:");
        
        File fleCertsTrustCASys = UtilKstJks.s_getFileKstCertsTrustCASys();
        
        String strCertsTrustCASys = "File not found!";
        
        if (fleCertsTrustCASys != null)
        {
            if (fleCertsTrustCASys.exists()) // not really needed, alreadey checked
                strCertsTrustCASys = fleCertsTrustCASys.getAbsolutePath();
        }
        
        JPanel pnlCertsSys = _createPanelPathAbs(MIViewKstJksSysRootCA.s_strPrefix + ":", strCertsTrustCASys);
         
        // --
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        
        pnl.add(pnlCertsSys);

        // ----
        // the following: only for windows or unix or linux

        
        if (! (S_OperatingSystem.s_isUnix() || S_OperatingSystem.s_isWindows()))
            return pnl;
            
        File fleCertsTrustUsr = UtilKstJks.s_getFileKstCertsTrustUsr();

        if (fleCertsTrustUsr == null)
        {
            MySystem.s_printOutTrace(this, strMethod, "nil fleCertsTrustUsr");
            return pnl;
        }
        
        if (! fleCertsTrustUsr.exists())
        {
            MySystem.s_printOutTrace(this, strMethod, "! fleCertsTrustUsr.exists()" + fleCertsTrustUsr.getAbsolutePath());
            return pnl;
        }
        
        
        String strCertsTrustUsr = fleCertsTrustUsr.getAbsolutePath();
        
        JPanel pnlCertsUsr = _createPanelPathAbs(MIViewKstJksUsrTrusSig.s_strPrefix + ":", strCertsTrustUsr);
         
        // --
        
        pnl.add(pnlCertsUsr);
        
        
        // ----
        
        
        // --
        return pnl;
    }
        
    private JPanel _createPanelPathAbs(String strLabel, String strTextField)
    {
        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        
        JLabel lbl = new JLabel(strLabel);
        
        JTextField tfd = super._createTextField_(strTextField); 

        pnl.add(lbl);
        pnl.add(Box.createHorizontalStrut(10));
        
        pnl.add(tfd);
        
        return pnl;
    }
    
 }
