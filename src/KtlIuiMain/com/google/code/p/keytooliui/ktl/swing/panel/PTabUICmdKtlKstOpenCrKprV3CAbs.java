package com.google.code.p.keytooliui.ktl.swing.panel;

/**
    "V3C" stands for "Version #3 Certificate"

    known subclasses:
    . PTabUICmdKtlKstOpenCrKprV3CDsa
    . PTabUICmdKtlKstOpenCrKprV3CRsa
 *  . PTabUICmdKtlKstOpenCrKprV3CEc
 *
    
**/

import java.util.Vector;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import com.google.code.p.keytooliui.shared.crypto.asnl.x509.MyKeyPurposeId;
import com.google.code.p.keytooliui.ktl.io.*;
import com.google.code.p.keytooliui.ktl.util.jarsigner.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;

abstract public class PTabUICmdKtlKstOpenCrKprV3CAbs extends PTabUICmdKtlKstOpenCrKprAbs implements
        ChangeListener
{
     // --------------------
    // FINAL STATIC PRIVATE
    
    final static private Font _f_s_fntLabelCrtExtEnabled = new Font("Dialog", Font.BOLD, 12);
    
    final static private String _f_s_strTextCrtExtEnabled = "ENABLED?";
    final static private String _f_s_strTextCrtExtCritical = "            - CRITICAL?";
    
    final static private String[] _f_s_strsTextCrtExtKUValue =
    {
        "            - digitalSignature:",
        "            - nonRepudiation:",
        "            - keyEncipherment:",
        "            - dataEncipherment:",
        "            - keyAgreement:",
        "            - keyCertSign:",
        "            - cRLSign:",
        "            - encipherOnly:",
        "            - decipherOnly:"
    };
    
    /* MEMO: kept from http://support.microsoft.com/kb/287547
    Can use strong encryption in export environment
        szOID_SERVER_GATED_CRYPTO               1.3.6.1.4.1.311.10.3.3
        szOID_SERIALIZED                        1.3.6.1.4.1.311.10.3.3.1
     
     Can use encrypted file systems (EFS)
        szOID_EFS_CRYPTO                        1.3.6.1.4.1.311.10.3.4
        szOID_EFS_RECOVERY                      1.3.6.1.4.1.311.10.3.4.1
            */
    
    final static private String[] _f_s_strsTextCrtExtEKUValue =
    {
        "            - serverAuth:",
        "            - clientAuth:",
        "            - codeSigning:",
        "            - emailProtection:",
        "            - ipsecEndSystem:",
        "            - ipsecTunnel:",
        "            - ipsecUser:",
        "            - timeStamping:",
        "            - OCSPSigning:",
        "            - Microsoft Enrollment Infrastructure: smartcardlogon:", // 1.3.6.1.4.1.311.20.2.2
        "            - Microsoft Crypto 2.0: server gated crypto",
        "            - Microsoft Crypto 2.0: serialized",
        "            - Microsoft Crypto 2.0: EFS crypto",
        "            - Microsoft Crypto 2.0: EFS recovery",
        "            - Adobe: CDS PKI",
        "            - unknown key usage"
    };
    
    //
   
    
    final static private DERObjectIdentifier[] _f_s_intsCrtExtEKUValue =
    {
        // BC stuff
        KeyPurposeId.id_kp_serverAuth,
        KeyPurposeId.id_kp_clientAuth,
        KeyPurposeId.id_kp_codeSigning,
        KeyPurposeId.id_kp_emailProtection,
        KeyPurposeId.id_kp_ipsecEndSystem,
        KeyPurposeId.id_kp_ipsecTunnel,
        KeyPurposeId.id_kp_ipsecUser,
        KeyPurposeId.id_kp_timeStamping,
        KeyPurposeId.id_kp_OCSPSigning,
        KeyPurposeId.id_kp_smartcardlogon,
        // my stuff, microsoft
        MyKeyPurposeId.szOID_SERVER_GATED_CRYPTO,
        MyKeyPurposeId.szOID_SERIALIZED,
        MyKeyPurposeId.szOID_EFS_CRYPTO,
        MyKeyPurposeId.szOID_EFS_RECOVERY,
        MyKeyPurposeId.szOID_CDS_PKI,
        // my stuff, other
        MyKeyPurposeId.id_kp_unknownKeyUsage
    };
    

    
    final static private int[] _f_s_intsCrtExtKUValue =
    {
        KeyUsage.digitalSignature,
        KeyUsage.nonRepudiation,
        KeyUsage.keyEncipherment,
        KeyUsage.dataEncipherment,
        KeyUsage.keyAgreement,
        KeyUsage.keyCertSign,
        KeyUsage.cRLSign,
        KeyUsage.encipherOnly,
        KeyUsage.decipherOnly
    };
    
    final static private String _f_s_strTipCrtExtEnabled = _f_s_strTextCrtExtEnabled;
    final static private String _f_s_strTipCrtExtCritical = _f_s_strTextCrtExtCritical;

    // ------
    // PUBLIC
    
    public void stateChanged(ChangeEvent e) 
    {        
        if (! (e.getSource() instanceof JCheckBox))
            return;
        
        JCheckBox cbx = (JCheckBox) e.getSource();
        
        if (cbx == this._cbxCrtExtKUEnabled_)
        {
            boolean bln = cbx.isSelected();
            
            if (this._cbxCrtExtKUCritical_ != null)
                this._cbxCrtExtKUCritical_.setEnabled(bln);
            
            for (int i=0; i<this._f_s_strsTextCrtExtKUValue.length; i++)
            {
                if (this._cbxsCrtExtKUValue!=null && this._cbxsCrtExtKUValue[i] != null)
                    this._cbxsCrtExtKUValue[i].setEnabled(bln);
            }
        }
        
        if (cbx == this._cbxCrtExtEKUEnabled_)
        {
            boolean bln = cbx.isSelected();
            
            if (this._cbxCrtExtEKUCritical_ != null)
                this._cbxCrtExtEKUCritical_.setEnabled(bln);
            
            for (int i=0; i<this._f_s_strsTextCrtExtEKUValue.length; i++)
            {
                if (this._cbxsCrtExtEKUValue!=null && this._cbxsCrtExtEKUValue[i] != null)
                    this._cbxsCrtExtEKUValue[i].setEnabled(bln);
            }
        }
        
        
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        _alignLabelsCrtExtKU();
        _alignLabelsCrtExtEKU();
        
        
        // ----
        return true;
    }
    
    // ---------
    // PROTECTED
    
    protected JCheckBox _cbxCrtExtKUEnabled_ = null;
    protected JCheckBox _cbxCrtExtKUCritical_ = null;
    
    protected JCheckBox _cbxCrtExtEKUEnabled_ = null;
    protected JCheckBox _cbxCrtExtEKUCritical_ = null;
    
    protected Vector<DERObjectIdentifier> _getCrtExtEKUValue_()
    {
        if (! this._cbxCrtExtEKUEnabled_.isEnabled())
            return null;
        
        Vector<DERObjectIdentifier> vec = new Vector<DERObjectIdentifier>();
        
        for (int i=0; i<this._cbxsCrtExtEKUValue.length; i++)
        {
            if (this._cbxsCrtExtEKUValue[i].isSelected())
                vec.add(_f_s_intsCrtExtEKUValue[i]);
        }
        
        if (vec.size() < 1)
            return null;
        
        return vec;
    }
    
    protected int _getCrtExtKUValue_()
    {
        int intVal = 0;
        
        for (int i=0; i<this._cbxsCrtExtKUValue.length; i++)
        {
            if (this._cbxsCrtExtKUValue[i].isSelected())
                intVal |= _f_s_intsCrtExtKUValue[i];
        }
        
        return intVal;
    }


    protected void _fillInPanelOutput_()
    {
        String strMethod = "_fillInPanelOutput_()";
        
        super._fillInPanelOutput_();
        
        JPanel pnlOutputCrtExtKU = _createPanelOutputCrtExtKU();
        
        if (pnlOutputCrtExtKU == null)
            MySystem.s_printOutExit(this, strMethod, "nil pnlOutputCrtExtKU");
        
        super._gbcOutputAll_.gridy ++;
        super._pnlOutputAll_.add(pnlOutputCrtExtKU, super._gbcOutputAll_);
        
        JPanel pnlOutputCrtExtEKU = _createPanelOutputCrtExtEKU();
        
        if (pnlOutputCrtExtEKU == null)
            MySystem.s_printOutExit(this, strMethod, "nil pnlOutputCrtExtEKU");
        
        super._gbcOutputAll_.gridy ++;
        super._pnlOutputAll_.add(pnlOutputCrtExtEKU, super._gbcOutputAll_);
        
        /*JPanel pnlExtension = new JPanel();
        
        pnlExtension.setLayout(new GridBagLayout());
        
        GridBagConstraints gbcExtension = new GridBagConstraints();
        
        //natural height, maximum width
        gbcExtension.fill = GridBagConstraints.HORIZONTAL;
        gbcExtension.fill = GridBagConstraints.VERTICAL;
        
        gbcExtension.anchor = GridBagConstraints.WEST; // ? left side of space
        gbcExtension.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbcExtension.gridx = -1;
        gbcExtension.gridy = 0;
        
        
        
        gbcExtension.gridx ++;
        pnlExtension.add(pnlOutputCrtExtKU, gbcExtension);
        
        gbcExtension.gridx ++;
        pnlExtension.add(pnlOutputCrtExtEKU, gbcExtension);
        
        // ----
        
        super._gbcOutputAll_.gridy ++;
        super._pnlOutputAll_.add(pnlExtension, super._gbcOutputAll_);
        */
    }
    
    
    protected PTabUICmdKtlKstOpenCrKprV3CAbs(
        String strHelpID, 
        Frame frmOwner, 
        String strTitleAppli,
        Integer[] itgsSizeListKpr,
        boolean blnAllowTypePkcs12,
        boolean blnAllowTypeBks,
        boolean blnAllowTypeUber
        )
    {
        super(
            strHelpID, 
            frmOwner, 
            strTitleAppli,
            itgsSizeListKpr,
            blnAllowTypePkcs12,
            blnAllowTypeBks,
            blnAllowTypeUber
            );
        
        // beg keyUsage
        
        this._cbxCrtExtKUEnabled_ = new JCheckBox();
        this._cbxCrtExtKUCritical_ = new JCheckBox();
        
        this._cbxsCrtExtKUValue = new JCheckBox[_f_s_strsTextCrtExtKUValue.length];
        this._pnlsCrtExtKUValue = new JPanel[_f_s_strsTextCrtExtKUValue.length];
        this._lblsCrtExtKUValue = new JLabel[_f_s_strsTextCrtExtKUValue.length];
        
        
        for (int i=0; i<this._f_s_strsTextCrtExtKUValue.length; i++)
        {
            this._cbxsCrtExtKUValue[i] = new JCheckBox();
            this._cbxsCrtExtKUValue[i].setSelected(false);
            this._cbxsCrtExtKUValue[i].setHorizontalTextPosition(SwingConstants.LEFT);
        }
        
        this._cbxsCrtExtKUValue[0].setSelected(true); // digitalSignature
        this._cbxsCrtExtKUValue[2].setSelected(true); // keyEncipherment
        this._cbxsCrtExtKUValue[3].setSelected(true); // dataEncipherment
        
        this._cbxCrtExtKUEnabled_.addChangeListener(this);  
        // ----
        
        this._cbxCrtExtKUEnabled_.setSelected(true);
        this._cbxCrtExtKUEnabled_.setHorizontalTextPosition(SwingConstants.LEFT);
        
        this._cbxCrtExtKUCritical_.setSelected(true);
        this._cbxCrtExtKUCritical_.setHorizontalTextPosition(SwingConstants.LEFT);
        
        // end keyUsage
        
        // beg extKeyUsage
        
        this._cbxCrtExtEKUEnabled_ = new JCheckBox();
        this._cbxCrtExtEKUCritical_ = new JCheckBox();
        
        this._cbxsCrtExtEKUValue = new JCheckBox[_f_s_strsTextCrtExtEKUValue.length];
        this._pnlsCrtExtEKUValue = new JPanel[_f_s_strsTextCrtExtEKUValue.length];
        this._lblsCrtExtEKUValue = new JLabel[_f_s_strsTextCrtExtEKUValue.length];
        
        
        for (int i=0; i<this._f_s_strsTextCrtExtEKUValue.length; i++)
        {
            this._cbxsCrtExtEKUValue[i] = new JCheckBox();
            this._cbxsCrtExtEKUValue[i].setSelected(false);
            this._cbxsCrtExtEKUValue[i].setHorizontalTextPosition(SwingConstants.LEFT);
        }
        
        this._cbxsCrtExtEKUValue[0].setSelected(true); // serverAuth
        this._cbxsCrtExtEKUValue[1].setSelected(true); // clientAuth
        
        this._cbxCrtExtEKUEnabled_.addChangeListener(this);  
        // ----
        
        this._cbxCrtExtEKUEnabled_.setSelected(false);
        this._cbxCrtExtEKUEnabled_.setHorizontalTextPosition(SwingConstants.LEFT);
        
        this._cbxCrtExtEKUCritical_.setSelected(false);
        this._cbxCrtExtEKUCritical_.setHorizontalTextPosition(SwingConstants.LEFT);
        
        // beg the following coz not yet user action
        this._cbxCrtExtEKUCritical_.setEnabled(false);
        
        for (int i=0; i<this._f_s_strsTextCrtExtEKUValue.length; i++)
        {
            this._cbxsCrtExtEKUValue[i].setEnabled(false);
        }
        
        // end the following ...
        
        // end extKeyUsage
    }
    
    // -------
    // PRIVATE
    
    private JPanel[] _pnlsCrtExtKUValue = null;
    private JCheckBox[] _cbxsCrtExtKUValue = null;
    private JLabel[] _lblsCrtExtKUValue = null;
    private JLabel _lblCrtExtKUEnabled = null;
    private JLabel _lblCrtExtKUCritical = null;
    
    private JPanel[] _pnlsCrtExtEKUValue = null;
    private JCheckBox[] _cbxsCrtExtEKUValue = null;
    private JLabel[] _lblsCrtExtEKUValue = null;
    private JLabel _lblCrtExtEKUEnabled = null;
    private JLabel _lblCrtExtEKUCritical = null;
    
    /**
    **/
    private JPanel _createPanelOutputCrtExtKU()
    {
        String strMethod = "_createPanelOutputCrtExtKU()";

        JPanel pnl = new JPanel();
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(pnl, "Cert. Extension - KeyUsage:");
        
        JPanel pnlCrtExtKUEnabled = _createPanelCrtExtKUEnabled();
        JPanel pnlCrtExtKUCritical = _createPanelCrtExtKUCritical();
        
        
        
        for (int i=0; i<this._pnlsCrtExtKUValue.length; i++)
        {
            this._pnlsCrtExtKUValue[i] = _createPanelCrtExtKUValue(i);
        }
        
        pnl.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = -1;
        
        
        
        gbc.gridy ++;
        pnl.add(pnlCrtExtKUEnabled, gbc);
        
        gbc.gridy ++;
        pnl.add(pnlCrtExtKUCritical, gbc);
        
        for (int i=0; i<this._pnlsCrtExtKUValue.length; i++)
        {
            gbc.gridy ++;
            pnl.add(this._pnlsCrtExtKUValue[i], gbc);
        }
        
        // ending
        return pnl;
    }
    
    /**
    **/
    private JPanel _createPanelOutputCrtExtEKU()
    {
        String strMethod = "_createPanelOutputCrtExtEKU()";

        JPanel pnl = new JPanel();
        com.google.code.p.keytooliui.shared.swing.border.S_Border.s_set(pnl, "Cert. Extension - ExtKeyUsage:");
        
        JPanel pnlCrtExtEKUEnabled = _createPanelCrtExtEKUEnabled();
        JPanel pnlCrtExtEKUCritical = _createPanelCrtExtEKUCritical();
        
        
        
        for (int i=0; i<this._pnlsCrtExtEKUValue.length; i++)
        {
            this._pnlsCrtExtEKUValue[i] = _createPanelCrtExtEKUValue(i);
        }
        
        pnl.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        
        gbc.anchor = GridBagConstraints.WEST; // ? left side of space
        //gbc.anchor = GridBagConstraints.NORTH; // ? left side of space
        
        gbc.gridx = 0;
        gbc.gridy = -1;
        
        
        
        gbc.gridy ++;
        pnl.add(pnlCrtExtEKUEnabled, gbc);
        
        gbc.gridy ++;
        pnl.add(pnlCrtExtEKUCritical, gbc);
        
        for (int i=0; i<this._pnlsCrtExtEKUValue.length; i++)
        {
            gbc.gridy ++;
            pnl.add(this._pnlsCrtExtEKUValue[i], gbc);
        }
        
        // ending
        return pnl;
    }
    
    private JPanel _createPanelCrtExtKUEnabled()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblCrtExtKUEnabled = new JLabel();
        this._lblCrtExtKUEnabled.setFont(_f_s_fntLabelCrtExtEnabled);
        this._lblCrtExtKUEnabled.setText(_f_s_strTextCrtExtEnabled);
        this._lblCrtExtKUEnabled.setToolTipText(_f_s_strTipCrtExtEnabled);
        this._lblCrtExtKUEnabled.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblCrtExtKUEnabled);
        pnl.add(this._cbxCrtExtKUEnabled_);

        return pnl;
    }
    
    private JPanel _createPanelCrtExtEKUEnabled()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblCrtExtEKUEnabled = new JLabel();
        this._lblCrtExtEKUEnabled.setFont(_f_s_fntLabelCrtExtEnabled);
        this._lblCrtExtEKUEnabled.setText(_f_s_strTextCrtExtEnabled);
        this._lblCrtExtEKUEnabled.setToolTipText(_f_s_strTipCrtExtEnabled);
        this._lblCrtExtEKUEnabled.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblCrtExtEKUEnabled);
        pnl.add(this._cbxCrtExtEKUEnabled_);

        return pnl;
    }
    
    private JPanel _createPanelCrtExtKUCritical()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblCrtExtKUCritical = new JLabel();
        this._lblCrtExtKUCritical.setFont(PSelAbs.f_s_fntLabel);
        this._lblCrtExtKUCritical.setForeground(Color.RED);
        this._lblCrtExtKUCritical.setText(_f_s_strTextCrtExtCritical);
        this._lblCrtExtKUCritical.setToolTipText(_f_s_strTipCrtExtCritical);
        this._lblCrtExtKUCritical.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblCrtExtKUCritical);
        pnl.add(this._cbxCrtExtKUCritical_);

        return pnl;
    }
    
    private JPanel _createPanelCrtExtEKUCritical()
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
        //pnl.setAlignmentX(LEFT_ALIGNMENT);

        this._lblCrtExtEKUCritical = new JLabel();
        this._lblCrtExtEKUCritical.setFont(PSelAbs.f_s_fntLabel);
        this._lblCrtExtEKUCritical.setForeground(Color.RED);
        this._lblCrtExtEKUCritical.setText(_f_s_strTextCrtExtCritical);
        this._lblCrtExtEKUCritical.setToolTipText(_f_s_strTipCrtExtCritical);
        this._lblCrtExtEKUCritical.setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblCrtExtEKUCritical);
        pnl.add(this._cbxCrtExtEKUCritical_);

        return pnl;
    }
    
    private JPanel _createPanelCrtExtEKUValue(int intID)
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));

        this._lblsCrtExtEKUValue[intID] = new JLabel();
        this._lblsCrtExtEKUValue[intID].setFont(PSelAbs.f_s_fntLabel);
        this._lblsCrtExtEKUValue[intID].setText(_f_s_strsTextCrtExtEKUValue[intID]);
        this._lblsCrtExtEKUValue[intID].setToolTipText(_f_s_strsTextCrtExtEKUValue[intID]);
        this._lblsCrtExtEKUValue[intID].setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblsCrtExtEKUValue[intID]);
        pnl.add(this._cbxsCrtExtEKUValue[intID]);

        return pnl;
    }
    
    private JPanel _createPanelCrtExtKUValue(int intID)
    {
        JPanel pnl = new JPanel();
        
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));

        this._lblsCrtExtKUValue[intID] = new JLabel();
        this._lblsCrtExtKUValue[intID].setFont(PSelAbs.f_s_fntLabel);
        this._lblsCrtExtKUValue[intID].setText(_f_s_strsTextCrtExtKUValue[intID]);
        this._lblsCrtExtKUValue[intID].setToolTipText(_f_s_strsTextCrtExtKUValue[intID]);
        this._lblsCrtExtKUValue[intID].setHorizontalAlignment(SwingConstants.LEFT);

        pnl.add(this._lblsCrtExtKUValue[intID]);
        pnl.add(this._cbxsCrtExtKUValue[intID]);

        return pnl;
    }
    
    /**
        done in the hurry, should take the top-most width of all labels
    **/
    private void _alignLabelsCrtExtKU()
    {
        JLabel lblValueTestMax = this._lblsCrtExtKUValue[3];
        
        Dimension dimPref = lblValueTestMax.getPreferredSize();
        Dimension dimMin = lblValueTestMax.getMinimumSize();
        Dimension dimMax = lblValueTestMax.getMaximumSize();
        
        this._lblCrtExtKUEnabled.setPreferredSize(dimPref);
        this._lblCrtExtKUCritical.setPreferredSize(dimPref);
        
        this._lblCrtExtKUEnabled.setMinimumSize(dimMin);
        this._lblCrtExtKUCritical.setMinimumSize(dimMin);
        
        this._lblCrtExtKUEnabled.setMaximumSize(dimMax);
        this._lblCrtExtKUCritical.setMaximumSize(dimMax);
        
        for (int i=0; i<this._lblsCrtExtKUValue.length; i++)
        {
            if (this._lblsCrtExtKUValue[i] == lblValueTestMax)
                continue;
            
            this._lblsCrtExtKUValue[i].setPreferredSize(dimPref);
            this._lblsCrtExtKUValue[i].setMinimumSize(dimMin);
            this._lblsCrtExtKUValue[i].setMaximumSize(dimMax);
        }
    }

    
    /**
        done in the hurry, should take the top-most width of all labels
    **/
    private void _alignLabelsCrtExtEKU()
    {
        /* does not work!
         int intIdMax = -1;
        int intLengthMax = -1;
        
        for (int i=0; i<_f_s_strsTextCrtExtEKUValue.length; i++)
        {
            int intLengthCur = _f_s_strsTextCrtExtEKUValue[i].length();
            
            if (intLengthCur < intLengthMax)
                continue;
       
            intIdMax = i;
        }
        
        if (intIdMax == -1)
        {
            // !!!!!!!! BUG
            // should throw an exception
            return;
        }
         **/
        
        JLabel lblValueTestMax = this._lblsCrtExtEKUValue[9 /*intIdMax*/];
        
        Dimension dimPref = lblValueTestMax.getPreferredSize();
        Dimension dimMin = lblValueTestMax.getMinimumSize();
        Dimension dimMax = lblValueTestMax.getMaximumSize();
        
        this._lblCrtExtEKUEnabled.setPreferredSize(dimPref);
        this._lblCrtExtEKUCritical.setPreferredSize(dimPref);
        
        this._lblCrtExtEKUEnabled.setMinimumSize(dimMin);
        this._lblCrtExtEKUCritical.setMinimumSize(dimMin);
        
        this._lblCrtExtEKUEnabled.setMaximumSize(dimMax);
        this._lblCrtExtEKUCritical.setMaximumSize(dimMax);
        
        for (int i=0; i<this._lblsCrtExtEKUValue.length; i++)
        {
            if (this._lblsCrtExtEKUValue[i] == lblValueTestMax)
                continue;
            
            this._lblsCrtExtEKUValue[i].setPreferredSize(dimPref);
            this._lblsCrtExtEKUValue[i].setMinimumSize(dimMin);
            this._lblsCrtExtEKUValue[i].setMaximumSize(dimMax);
        }
    }
}


