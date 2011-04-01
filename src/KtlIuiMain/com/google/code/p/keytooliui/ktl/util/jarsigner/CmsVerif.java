/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

package com.google.code.p.keytooliui.ktl.util.jarsigner;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.security.cert.CertStore;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;

public class CmsVerif extends CmsAbs
{
    // --------------------
    // final static private
    
    final static private String _STR_KST_PROVIDER_BC = "BC";
    
    // ------
    // public
    
    public CmsVerif(
            Frame frmOwner, 
            String strTitleAppli,
            String strPathAbsFileData,
            String strPathAbsFileSig)
    {
        super(frmOwner, strTitleAppli, strPathAbsFileData, strPathAbsFileSig);
    }
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        try
        {
            //_validateCmsSignature();
            CMSSignedData cms = _getSignPkcs7();
            
            SignerInformationStore sis = cms.getSignerInfos();
            Collection colSignerInfo = sis.getSigners();
            Iterator itrSignerInfo = colSignerInfo.iterator();
            SignerInformation sin = (SignerInformation) itrSignerInfo.next();
            
            //r�cup�ration du certificat du signataire
            CertStore cse = cms.getCertificatesAndCRLs("Collection", CmsVerif._STR_KST_PROVIDER_BC);
            Iterator itrCert = cse.getCertificates(sin.getSID()).iterator();
            X509Certificate crt = (X509Certificate) itrCert.next();
            
            // Verifie la signature
            boolean blnCoreValidity = sin.verify(crt, CmsVerif._STR_KST_PROVIDER_BC);
            
            if (blnCoreValidity)
            {
                MySystem.s_printOutTrace(this, strMethod, "blnCoreValidity=true");

                String strBody = "CMS Detached signature is OK!"; 

                strBody += "\n\n" + ". CMS signature file location:";            
                strBody += "\n  " + super._strPathAbsFileSig_;
                
                strBody += "\n\n" + ". Data file location:";            
                strBody += "\n  " + super._strPathAbsFileData_;

                OPAbstract.s_showDialogInfo(super._frmOwner_, super._strTitleAppli_, strBody);
                
                //SignerInfo sio = sin.toSignerInfo();
                
                SignerId sid = sin.getSID();
                
                if (sid != null)
                {
                    System.out.println("sid.getSerialNumber()=" + sid.getSerialNumber());
                    System.out.println("sid.getIssuerAsString()=" + sid.getIssuerAsString());
                    System.out.println("sid.getSubjectAsString()=" + sid.getSubjectAsString());
                }      
                
               /*System.out.println("sin.getDigestAlgOID()=" + sin.getDigestAlgOID());
               System.out.println("sin.getEncryptionAlgOID()=" + sin.getEncryptionAlgOID());
               System.out.println("sin.toString()=" + sin.toString());
               System.out.println("sin.getVersion()=" + sin.getVersion());*/
            }

            else
            {
                MySystem.s_printOutWarning(this, strMethod, "blnCoreValidity=true");

                String strBody = "CMS Detached signature is WRONG!"; 

                strBody += "\n\n" + ". CMS signature file location:";            
                strBody += "\n  " + super._strPathAbsFileSig_;
                
                strBody += "\n\n" + ". Data file location:";            
                strBody += "\n  " + super._strPathAbsFileData_;

                OPAbstract.s_showDialogWarning(super._frmOwner_, super._strTitleAppli_, strBody);
            }
            
            
            
           
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
            
            String strBody = "Failed to verify CMS detached signature.";
            
            strBody += "\n\n" + "Possible reason: wrong data file";

	    strBody += "\n\n" + "got exception."; 
            strBody += "\n" + exc.getMessage();            
            strBody += "\n\n" + "More: see your session.log";
            
            OPAbstract.s_showDialogError(super._frmOwner_, super._strTitleAppli_, strBody);
            
            return false;
        }
        
        // TODO
        return true;
    }
    
    // -------
    // private
    
    private CMSSignedData _getSignPkcs7()
        throws Exception
    {
        
        //File fleDoc = new File("D:\\Mes Documents bantchao\\_perso\\prod\\rcp\\_test\\mon_fichier.txt");
        File fleDoc = new File(super._strPathAbsFileData_);
        byte[] bytsDoc = _read(fleDoc);
        
        File fleSigCmsPkcs7 = new File(super._strPathAbsFileSig_);
        byte[] bytsSigCmsPkcs7 = _read(fleSigCmsPkcs7);
        
        
        CMSProcessable cmdProcDoc = new CMSProcessableByteArray(bytsDoc);
        CMSSignedData cms = new CMSSignedData(cmdProcDoc, bytsSigCmsPkcs7);
        return cms;
    }
    
    private byte[] _read(File fle) 
        throws Exception 
    {
        FileInputStream fis = new FileInputStream(fle);
        java.nio.channels.FileChannel fcl = fis.getChannel();
       
        byte[] byts = new byte[(int) fcl.size()];   // fcl.size returns the size of the file which backs the channel
        ByteBuffer bbr = ByteBuffer.wrap(byts);
        fcl.read(bbr);
        return byts;
    }
    
    /*private void _validateCmsSignature() 
        throws 
            Exception
    { */  
        String strMethod = "_validateCmsSignature()";
        /*

        // Create a DOM XMLSignatureFactory that will be used to
        // generate the enveloped signature.
        // ... looks for a service provider that supports DOM
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance(XmlAbs.STR_INSTANCESIGNATURE);
        
        
        // Instantiate the document to be verified.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        
        FileInputStream fis = new FileInputStream(super._strPathAbsFileSig_);
        org.w3c.dom.Document doc = dbf.newDocumentBuilder().parse(fis);
        

        // Find Signature element.
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        
        if (nl.getLength() == 0) 
        {
            throw new Exception("Failed to get Signature element");
        }

        // Create a DOMValidateContext and specify a KeySelector
        // and document context.
        DOMValidateContext valContext = new DOMValidateContext(new KeySelectorXmlVerif(), nl.item(0));

        // --
        valContext.setProperty("javax.xml.crypto.dsig.cacheReference", Boolean.TRUE);


        // Unmarshal the XMLSignature.
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        // Validate the XMLSignature.
        boolean blnCoreValidity = signature.validate(valContext);
        
        if (blnCoreValidity)
        {
            MySystem.s_printOutTrace(this, strMethod, "blnCoreValidity=true");

	    String strBody = "XML's embedded signature is OK!"; 
            
            strBody += "\n\n" + "XML file location:";            
            strBody += "\n  " + super._strPathAbsFileSig_;
            
            OPAbstract.s_showDialogInfo(super._frmOwner_, super._strTitleAppli_, strBody);
        }
        
        else
        {
            MySystem.s_printOutWarning(this, strMethod, "blnCoreValidity=true");

	    String strBody = "XML's embedded signature is not OK!"; 
            
            strBody += "\n\n" + "XML file location:";            
            strBody += "\n  " + super._strPathAbsFileSig_;
            
            OPAbstract.s_showDialogWarning(super._frmOwner_, super._strTitleAppli_, strBody);
        }*/
        
    //}
}
