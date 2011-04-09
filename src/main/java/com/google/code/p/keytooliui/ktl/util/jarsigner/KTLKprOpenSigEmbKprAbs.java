package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
    known subclasses:
    . ?

    "Kpr" for "KeyPair" entry
    

**/

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;




import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.KeyStore;
// --
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public abstract class KTLKprOpenSigEmbKprAbs extends KTLKprOpenSigEmbAbs
{    
    // ------------------
    // protected abstract
    
    protected abstract KeyStore _getKeystoreOpen_(File fleOpen);
    
    protected abstract boolean _doJobSelectKpr_(
        File fleOpenData,
        File fleSaveData,
        KeyStore kstOpen,
            
        // below: about PKTC (Private Key & Trusted Certificate)
        String[] strsAliasPKTC, 
        Boolean[] boosIsTCEntryPKTC, 
        Boolean[] boosValidDatePKTC, 
        Boolean[] boosSelfSignedCertPKTC, 
        Boolean[] boosTrustedCertPKTC, 
        String[] strsSizeKeyPublPKTC,
        String[] strsTypeCertPKTC, 
        String[] strsAlgoSigCertPKTC, 
        Date[] dtesLastModifiedPKTC,
        // below: about SK (Secret Key)
        String[] strsAliasSK,
        Date[] dtesLastModifiedSK
        );
    
    // ------
    // PUBLIC
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileSave SigEmb (MEMO: check for files prior to open up trusted certificate entries selection dialog)
        . open keystore
        . if no entry in keystore, show warning dialog, then return false
        . if no entry of type "Trusted certificate" in keystore, show warning dialog, then return false
        . fill in table with entrie
        . show dialog trusted certificate select, to get:
          . alias trusted certificate
        . get X509 trusted certificate 
        . generate certificate ?string from trusted certificate
        
        . write SigEmb string to fileSave
    **/
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        super._setEnabledCursorWait_(true);
        
        // ---
        // check providers
        
        if (super._strProviderKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strProviderKst_");
        }
        
        // ---
        // get fileOpen keystore
        
        
        File fleOpenKst = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsKst_);
        
        if (fleOpenKst == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil fleOpenKst");
            return false;
        }
        
        // ----
        // . get fileSave SigEmb
        
        if (super._strPathAbsFileSaveData_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileSaveData_");
        }
        
        File fleOpenData = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,super._strPathAbsFileOpenData_
            );
        
        if (fleOpenData == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenData");
            return false;
        }
        
        File fleSaveData = UtilJsrFile.s_getFileSave(
            super._frmOwner_,  super._strPathAbsFileSaveData_,
            true // blnShowDlgOverwrite
            );
        
        if (fleSaveData == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleSaveData");
            return false;
        }
        
        // ----
        // open keystore
        
        if (super._chrsPasswdKst_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._chrsPasswdKst_"); 
        }
        
        KeyStore kstOpen = _getKeystoreOpen_(fleOpenKst);
        
        if (kstOpen == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutError(this, strMethod, "nil kstOpen");
            return false;
        }
        
        
        // NEW
        
        String[] strsAliasPKTC = UtilKstAbs.s_getStrsAliasPKTC(
            super._frmOwner_,
         
            kstOpen);
        
        if (strsAliasPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
       
            kstOpen);
        
        if (strsAliasSK == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        // --
        // get arrays for dialogTableSelectKeypair
        // TC versus PK
        Boolean[] boosIsTCEntryPKTC = 
            UtilKstAbs.s_getBoosEntryTcr(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosIsTCEntryPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
           kstOpen, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
             kstOpen, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
         kstOpen, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
           kstOpen, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
        kstOpen, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
         kstOpen, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
         kstOpen, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
                
        
        if (! _doJobSelectKpr_(
            fleOpenData,
            fleSaveData,
            kstOpen,
            // below: about PKTC (Private Key & Trusted Certificate)
            strsAliasPKTC, 
            boosIsTCEntryPKTC, 
            boosValidDatePKTC, 
            boosSelfSignedCertPKTC, 
            boosTrustedCertPKTC, 
            strsSizeKeyPublPKTC,
            strsTypeCertPKTC, 
            strsAlgoSigCertPKTC, 
            dtesLastModifiedPKTC,
            // below: about SK (Secret Key)
            strsAliasSK,
            dtesLastModifiedSK
                ))
        {
            MySystem.s_printOutTrace(this, strMethod, "either aborted or failed");
            return false;
        }
        
        // ending
        return true;
    }
    
    
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileSave SigEmb (MEMO: check for files prior to open up trusted certificate entries selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . fill in table entries
        . show dialog trusted certificate select, to get:
          . alias
        . get trusted certificate
        . generate SigEmb string from tcr
        
        . write SigEmb string to fileSave
    **/
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasPrivateKey,
        char[] chrsPasswdPrivateKey,
        File fleOpenData,
        File fleSaveData
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, fleOpenData, fleSaveData)";
        
        if (kstOpen==null || strAliasPrivateKey==null || fleOpenData==null || fleSaveData==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
        
        if (! _doSign(kstOpen, strAliasPrivateKey, chrsPasswdPrivateKey, fleOpenData, fleSaveData))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }

        // ending
        return true;
    }

    protected KTLKprOpenSigEmbKprAbs(
        Frame frmOwner, 

        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenData,
        String strPathAbsFileSaveData,

        String strProviderKst
        )
    {
        super(
            frmOwner, 

            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenData,
            strPathAbsFileSaveData,
            strProviderKst
            );
            
    }
    
    // -------
    // private
    
    private org.w3c.dom.Document _doc = null;
    private XMLSignatureFactory _fac = null;
    private SignedInfo _si = null;
    private KeyStore.PrivateKeyEntry _keyEntry = null;
    private KeyInfo _ki = null;
    
    // tempo value
    private String _strAlgorithmDigest = DigestMethod.SHA1;  // value = "http://www.w3.org/2000/09/xmldsig#sha1"
       
    private boolean _doSign(
            KeyStore kstOpen,
            String strAliasPrivateKey,
            char[] chrsPasswdPrivateKey,
            File fleOpenData,
            File fleSaveData) 
    {
        String strMethod = "_doSign(...)";
        
        
        String strCrtSigAlgo = UtilKstAbs.s_getCertSigAlgo((Component) super._frmOwner_, 
                kstOpen, strAliasPrivateKey);
        
        if (strCrtSigAlgo == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strCrtSigAlgo");
            
            String strBody = "Failed to get certificate signature algorithm for private key entry aliased:"; 
            strBody += "\n" + strAliasPrivateKey;            
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            return false;
        }
        
        /*
         *MEMO: TBRL with PKCS12 keystores:
         *certSigAlgo for PKCS12 returns:
         *SHA1withDSA: DSA
         *SHA1withRSA: ?SHA1withRSAEncryption?
        */
        String strAlgorithmSignature = null;
        
        if (strCrtSigAlgo.toLowerCase().startsWith("sha1withrsa"))
            strAlgorithmSignature = SignatureMethod.RSA_SHA1; // value = "http://www.w3.org/2000/09/xmldsig#rsa-sha1"
        else if (strCrtSigAlgo.toLowerCase().startsWith("sha1withdsa"))
            strAlgorithmSignature = SignatureMethod.DSA_SHA1; // value = "http://www.w3.org/2000/09/xmldsig#dsa-sha1"
        else if (strCrtSigAlgo.toLowerCase().compareTo("dsa") == 0) // trick
            strAlgorithmSignature = SignatureMethod.DSA_SHA1; // value = "http://www.w3.org/2000/09/xmldsig#dsa-sha1"
        else // MEMO: statement never reached, unless coding error!
        {
            MySystem.s_printOutError(this, strMethod, "uncaught strCrtSigAlgo, strCrtSigAlgo=" + strCrtSigAlgo);
            
            String strBody = "Not a valid certificate signature algorithm: " + strCrtSigAlgo; 
            strBody += "\nfor private key entry aliased: " + strAliasPrivateKey;            
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            return false;
        }
        
        try
        {
            _generateXmlSignature(strAlgorithmSignature);
            _createObjectKeyInfo(kstOpen, strAliasPrivateKey, chrsPasswdPrivateKey);
            _signDocument(fleOpenData);  
            _saveSignedDocument(fleSaveData);
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");

	    String strBody = "got exception."; 
            strBody += "\n" + exc.getMessage();            
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_, strBody);
            
            return false;
        }
        
        // ------
        // ending
        return true;
    }
    
    private void _generateXmlSignature(
            String strAlgorithmSignature
            ) 
        throws 
            Exception
    {
        
        // Create a DOM XMLSignatureFactory that will be used to
        // generate the enveloped signature.
        // ... looks for a service provider that supports DOM
        this._fac = XMLSignatureFactory.getInstance(XmlAbs.STR_INSTANCESIGNATURE);

        // Create a Reference to the enveloped document (in this case,
        // you are signing the whole document, so a URI of "" signifies
        // that, and also specify the SHA1 digest algorithm and
        // the ENVELOPED Transform.
        
        
        
        DigestMethod dmdDigestMethod = this._fac.newDigestMethod(this._strAlgorithmDigest, null);
        Transform trsTransform = this._fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null); 
        java.util.List lst = Collections.singletonList(trsTransform);
        Reference ref = this._fac.newReference("", dmdDigestMethod, lst, null, null);
        

        // Create the SignedInfo.
        CanonicalizationMethod cmdCanonicalizationMethod = this._fac.newCanonicalizationMethod
            (CanonicalizationMethod.INCLUSIVE,
            (C14NMethodParameterSpec) null);
        
        
        SignatureMethod smdSignatureMethod = this._fac.newSignatureMethod(strAlgorithmSignature, null);
        java.util.List lst2 = Collections.singletonList(ref);
        this._si = this._fac.newSignedInfo(cmdCanonicalizationMethod, smdSignatureMethod, lst2);
    }
    
    private void _createObjectKeyInfo(
            KeyStore kstOpen,
            String strAliasPrivateKey,
            char[] chrsPasswdPrivateKey
            ) 
        throws 
            Exception
    {
        
        // Load the KeyStore and get the signing key and certificate.
        /*KeyStore ks = KeyStore.getInstance(this._strInstanceKst);
        
        FileInputStream fisPasswdKst = new FileInputStream(this._strPathAbsKst);
        char[] chrsPasswdKst = this._strPasswdKst.toCharArray();
        ks.load(fisPasswdKst, chrsPasswdKst);*/
        
        //char[] chrsPasswdPrivateKey = this._strPasswdPrivateKey.toCharArray();
        KeyStore.PasswordProtection ppnPasswordProtection = new KeyStore.PasswordProtection(chrsPasswdPrivateKey);
        KeyStore.Entry ent = kstOpen.getEntry(strAliasPrivateKey, ppnPasswordProtection);
        
        if (ent == null)
        {
            // no such entry
            throw new Exception("No such entry for alias: " + strAliasPrivateKey);
        }
        
        this._keyEntry = (KeyStore.PrivateKeyEntry) ent;
        
        java.security.cert.Certificate cert = (java.security.cert.Certificate) this._keyEntry.getCertificate();
        
        java.security.cert.X509Certificate certX509 = (java.security.cert.X509Certificate) cert;

        // Create the KeyInfo containing the X509Data.
        KeyInfoFactory kif = this._fac.getKeyInfoFactory();
        java.util.List x509Content = new ArrayList();
        
        String strNameX500Principal = certX509.getSubjectX500Principal().getName();
        x509Content.add(strNameX500Principal);
        x509Content.add(certX509);
        X509Data xd = kif.newX509Data(x509Content);
        this._ki = kif.newKeyInfo(Collections.singletonList(xd));
    }
    
    private void _signDocument(File fleOpenData) 
        throws 
            Exception
    {
        // Instantiate the document to be signed.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        
        
        FileInputStream fis = new FileInputStream(fleOpenData);
        this._doc = dbf.newDocumentBuilder().parse(fis);

        // Create a DOMSignContext and specify the RSA PrivateKey and
        // location of the resulting XMLSignature's parent element.
        DOMSignContext dsc = new DOMSignContext
            (this._keyEntry.getPrivateKey(), this._doc.getDocumentElement());

        // Create the XMLSignature, but don't sign it yet.
        XMLSignature signature = this._fac.newXMLSignature(this._si, this._ki);

        // Marshal, generate, and sign the enveloped signature.
        signature.sign(dsc);
    }
    
    private void _saveSignedDocument(File fleSaveData) 
        throws 
            Exception
    {
        
        OutputStream os = new FileOutputStream(fleSaveData);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(this._doc), new StreamResult(os));
    }
}
