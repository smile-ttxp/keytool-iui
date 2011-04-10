package com.google.code.p.keytooliui.ktl.util.jarsigner;

import java.awt.Frame;
import java.io.FileInputStream;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;
import org.w3c.dom.NodeList;
import javax.xml.crypto.dsig.XMLSignature;

public class XmlVerif extends XmlAbs
{
    // ------
    // public
    
    public XmlVerif(
            Frame frmOwner, 
            String strPathAbsFile)
    {
        super(frmOwner, strPathAbsFile);
    }
    
    public boolean doJob()
    {
        String strMethod = "doJob()";
        
        try
        {
            _validateXmlSignature();
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
        
        // TODO
        return true;
    }
    
    // -------
    // private
    
    private void _validateXmlSignature() 
        throws 
            Exception
    {   
        String strMethod = "_validateXmlSignature()";
        
        //XMLSignatureFactory fac = null;
        // Create a DOM XMLSignatureFactory that will be used to
        // generate the enveloped signature.
        // ... looks for a service provider that supports DOM
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance(XmlAbs.STR_INSTANCESIGNATURE);
        
        
        // Instantiate the document to be verified.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        
        FileInputStream fis = new FileInputStream(super._strPathAbsFile_);
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
            strBody += "\n  " + super._strPathAbsFile_;
            
            OPAbstract.s_showDialogInfo(super._frmOwner_, strBody);
        }
        
        else
        {
            MySystem.s_printOutWarning(this, strMethod, "blnCoreValidity=true");

	    String strBody = "XML's embedded signature is not OK!"; 
            
            strBody += "\n\n" + "XML file location:";            
            strBody += "\n  " + super._strPathAbsFile_;
            
            OPAbstract.s_showDialogWarning(super._frmOwner_, strBody);
        }
        
    }
}
