package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**
 *
 *note: some codes come from:
 *https://bugs.internet2.edu/jira/secure/attachment/10050/ExtKeyTool.java

**/

import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAKey;
import javax.crypto.Cipher;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;


// ----
import java.security.KeyStore;
import java.security.PrivateKey;
// --
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.io.*;
import java.util.*;

public abstract class KTLKprOpenKprFromKprDerAbs extends KTLKprOpenKprFromKprAbs
{
    // ------------------
    // protected abstract
    
    protected abstract KeyStore _getKeystoreOpen_(File fleOpen);
    
    protected abstract boolean _doJobSelectKpr_(
        File fleOpenKpr,
        File fleOpenCrts,
        KeyStore kstOpen,
            
        // NEW
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
        . get fileOpen crt (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . WRONG !!!!!!!!!! if no entry of type "RSA-self-ned" in keystore, show warning dialog, then return false
        . fill in table keypair
        . WRONG !!!!!!!!!  show dialog keypair select RSA self, to get:
          . alias keypair
          . password keypair
        . get private key
        . get first X509 cert in cert chain
        . generate CRT string from cert and private key
        
        . write crt string to fileOpen
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
        // . get fileOpen Kpr
        
        if (super._strPathAbsFileKpr_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileKpr_");
        }
        
        File fleOpenKpr = UtilJsrFile.s_getFileOpen(
            super._frmOwner_, super._strPathAbsFileKpr_//,
            //true // blnShowDlgOverwrite
            );
        
        if (fleOpenKpr == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenKpr");
            return false;
        }

        // ----
        // . get fileOpen Kpr
        
        if (super._strPathAbsFileCrts_ == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutExit(this, strMethod, "nil super._strPathAbsFileCrts_");
        }
        
        File fleOpenCrts = UtilJsrFile.s_getFileOpen(
            super._frmOwner_,  super._strPathAbsFileCrts_//,
            //true // blnShowDlgOverwrite
            );
        
        if (fleOpenCrts == null)
        {
            super._setEnabledCursorWait_(false);
            MySystem.s_printOutWarning(this, strMethod, "nil fleOpenCrts");
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
            MySystem.s_printOutExit(strMethod, "nil strsAliasPKTC");
        }
        
        String[] strsAliasSK = UtilKstAbs.s_getStrsAliasSK(
            super._frmOwner_,
       
            kstOpen);
        
        if (strsAliasSK == null)
        {
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
            MySystem.s_printOutExit(strMethod, "nil boosIsTCEntryPKTC");
        }
        
        Boolean[] boosValidDatePKTC = 
            UtilKstAbs.s_getBoosValidDatePKTC(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (boosValidDatePKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosValidDatePKTC");
        }

        Boolean[] boosSelfSignedCertPKTC = 
            UtilKstAbs.s_getBoosSelfSigned(super._frmOwner_,
          kstOpen, strsAliasPKTC);
        
        if (boosSelfSignedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosSelfSignedCertPKTC");
        }
        
        Boolean[] boosTrustedCertPKTC = 
            UtilKstAbs.s_getBoosTrusted(super._frmOwner_,
            kstOpen, strsAliasPKTC);
        
        if (boosTrustedCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil boosTrustedCertPKTC");
        }
        
        String[] strsSizeKeyPublPKTC = UtilKstAbs.s_getStrsSizeKeyPubl(super._frmOwner_,
           kstOpen, strsAliasPKTC);
        
        if (strsSizeKeyPublPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsSizeKeyPublPKTC");
        }
        
        String[] strsTypeCertPKTC = UtilKstAbs.s_getStrsTypeCertificatePKTC(super._frmOwner_,
           kstOpen, strsAliasPKTC);
        
        if (strsTypeCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsTypeCertPKTC");
        }
        
        String[] strsAlgoSigCertPKTC = UtilKstAbs.s_getStrsAlgoSigCertPKTC(super._frmOwner_,
           kstOpen, strsAliasPKTC);
        
        if (strsAlgoSigCertPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil strsAlgoSigCertPKTC");
        }

        Date[] dtesLastModifiedPKTC = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
           kstOpen, strsAliasPKTC);

        if (dtesLastModifiedPKTC == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        Date[] dtesLastModifiedSK = UtilKstAbs.s_getDtesLastModified(super._frmOwner_,
             kstOpen, strsAliasSK);

        if (dtesLastModifiedSK == null)
        {
            MySystem.s_printOutExit(strMethod, "nil dtesLastModifiedPKTC");
        }
        
        // --
        // assign default cursor
        
        super._setEnabledCursorWait_(false);
                
        
        if (! _doJobSelectKpr_(
            fleOpenKpr,
            fleOpenCrts,
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
    
    private X509Certificate[] _readCrts(
            File fleOpenCrts,
            PrivateKey pkyPrivate
            )
        throws Exception
    {
         return _readCrtsDer(fleOpenCrts, pkyPrivate);
    }
    
   
    
    private X509Certificate[] _readCrtsDer(
            File fleOpenCrts,
            PrivateKey pkyPrivate
            )
        throws Exception
    {
        InputStream ismChain = new FileInputStream(fleOpenCrts);

        CertificateFactory cfyFactory = CertificateFactory.getInstance("X.509", KTLAbs.f_s_strProviderKstBC);
        Collection colCerts = cfyFactory.generateCertificates(new BufferedInputStream(ismChain));

        if (colCerts.isEmpty()) 
        {
            throw new Exception("No valid certificates found in certificates chain file");
        }

        return _linkChain(this._strAlgoKey, 
            (X509Certificate[]) colCerts.toArray(new X509Certificate[0]), pkyPrivate);
    }
    
    private PrivateKey _readKprDer(
            File fleOpenKpr
            )
        throws Exception
    {
        return KTLKprAbs._s_readKey_(fleOpenKpr, this._strAlgoKey);
    }
    
   
    private PrivateKey _readKpr(
            File fleOpenKpr
            )
        throws Exception
    {
        return _readKprDer(fleOpenKpr);
    }
    
    /**
    if any error in code, exiting
        in case of trbrl: open up a warning dialog, and return false;
        
        algo:
        . get fileOpen keystore
        . get fileOpen crt (MEMO: check for files prior to open up keypair selection dialog)
        . open keystore
        . !! if no entry in keystore, show warning dialog, then return false
        . WRONG LINE !! if no entry of type "RSA-self-IOned" in keystore, show warning dialog, then return false
        . fill in table keypair
        . WRONG LINE show dialog keypair select RSA self, to get:
          . alias keypair
          . password keypair (if not from pkcs12 kst)
        . get private key
        . get first X509 cert in cert chain
        . generate CRT string from cert and private key
        
        . write crt string to fileOpen
    **/
    protected boolean _doJob_(
        KeyStore kstOpen,
        String strAliasKpr,
        char[] chrsPasswdKpr,
        File fleOpenKpr,
        File fleOpenCrts
        )
    {
        String strMethod = "_doJob_(kstOpen, strAliasKpr, chrsPasswdKpr, fleOpenKpr, fleOpenCrts)";
        
        if (kstOpen==null || strAliasKpr==null || chrsPasswdKpr==null || fleOpenKpr==null || fleOpenCrts==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");
  
        // ----
        
        PrivateKey pky = null;
        X509Certificate[] crtsX509 = null;

        try
        {
            pky = _readKpr(fleOpenKpr);
            
            if (pky == null)
            {
                MySystem.s_printOutWarning(this, strMethod, "nil pky, user may have cancelled!");
                return false;
            }
            
            crtsX509 = _readCrts(fleOpenCrts, pky);
            
            if (crtsX509 == null)
            {
                throw new Exception("Failed to read X509 certificates chain");
            }
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(this, strMethod, "exc caught");
                
            String strBody = "Got exception:";
            strBody += "\n  " + exc.getMessage();

            strBody += "\n\n" + "More: see your session.log";

            OPAbstract.s_showDialogError(super._frmOwner_,  strBody);
            return false;
        }

        if (! UtilKstAbs.s_setKeyEntry(
                super._frmOwner_,  kstOpen,
                    strAliasKpr, pky, chrsPasswdKpr, crtsX509
                ))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --- saving keystore
        
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
        
        if (! super._saveKeyStore_(kstOpen, fleOpenKst, super._chrsPasswdKst_))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
            
        
        // ending
        return true;
    }
    
   
  

    protected KTLKprOpenKprFromKprDerAbs(
        Frame frmOwner, 
     
        
        // input
        String strPathAbsOpenKst, // existing keystore 
        char[] chrsPasswdOpenKst,
        
        String strPathAbsFileOpenKpr,
        String strPathAbsFileOpenCrts,
            
        String strAlgoKey,
        String strProviderKst
        )
    {
        super(
            frmOwner, 
         
            strPathAbsOpenKst, 
            chrsPasswdOpenKst, 
            strPathAbsFileOpenKpr,
            strPathAbsFileOpenCrts,
            "DER", // files format
            strProviderKst
            );
        
        this._strAlgoKey = strAlgoKey; // needed in case of DER format file
    }
    
    // -------
    // private
    
    private String _strAlgoKey = null;
    


    
    /**
	 * Converts an array of certificates into an ordered chain. A certificate that matches the specified private key
	 * will be returned first and the root certificate will be returned last.
	 *
	 * @param crtsX509Untested
	 *            array of certificates
	 * @param privKey
	 *            the private key used to determine the first cert in the chain
	 * @throws InvalidCertificateChainException
	 *             thrown if a chain cannot be constructed from the specified elements
	 */

	private X509Certificate[] _linkChain(
                String keyAlgorithm, 
                X509Certificate[] crtsX509Untested, 
                PrivateKey privKey)
            throws Exception 
        {

            //log.debug("Located " + crtsX509Untested.length + " cert(s) in input file");

            //log.info("Finding end cert in chain.");
            ArrayList replyCerts = new ArrayList();
            
            for (int i = 0; crtsX509Untested.length > i; i++) 
            {
                if (keyAlgorithm.compareToIgnoreCase("RSA") == 0)
                {
                    
                    if (_isMatchingKeyRsa(keyAlgorithm, crtsX509Untested[i].getPublicKey(), privKey)) 
                    {
                        //log.debug("Found matching end cert: " + crtsX509Untested[i].getSubjectDN());
                        replyCerts.add(crtsX509Untested[i]);
                    }
                }
                
                     // IMPORTANT NOTICE!
                else // MEMO: Cipher.getInstance(...): does not work for DSA, and EC which is for now ECDSA
                {
                    replyCerts.add(crtsX509Untested[i]);
                }
            }
            
            if (replyCerts.size() < 1) 
            {
                String strException = "No certificate in chain that matches specified private key";
                strException += "\n\n" + "Make sure key algorithm parameter is OK";
                
                //log.error("No certificate in chain that matches specified private key");
                throw new Exception(strException);
            }
            
            if (replyCerts.size() > 1) 
            {
                //log.error("More than one certificate in chain that matches specified private key");
                throw new Exception("More than one certificate in chain that matches specified private key");
            }

            //log.info("Populating chain with remaining certs.");
            if (crtsX509Untested.length > 1)
                _walkChain(crtsX509Untested, replyCerts);

            //log.info("Verifying that each link in the cert chain is signed appropriately");
            for (int i = 0; i < replyCerts.size() - 1; i++) 
            {
                PublicKey pubKey = ((X509Certificate) replyCerts.get(i + 1)).getPublicKey();
                
                try 
                {
                    ((X509Certificate) replyCerts.get(i)).verify(pubKey);
                } 
                
                catch (Exception e) 
                {
                    //log.error("Certificate chain cannot be verified: " + e.getMessage());
                    throw new Exception("Certificate chain cannot be verified: " + e.getMessage());
                }
            }
            
            //log.info("All signatures verified. Certificate chain successfully created.");

            return (X509Certificate[]) replyCerts.toArray(new X509Certificate[0]);
	}
        
        /**
	 * Boolean indication of whether a given private key and public key form a valid keypair.
	 *
	 * @param pubKey
	 *            the public key
	 * @param privKey
	 *            the private key
	 */

        // OK for RSA, KO for DSA, ?? for EC
	private boolean _isMatchingKeyRsa(String algorithm, PublicKey pubKey, PrivateKey privKey) 
        {
            String strMethod = "_isMatchingKeyRsa(...)";

            try 
            {
                String controlString = "asdf";
                //log.debug("Checking for matching private key/public key pair");

                /*
                 * If both keys are RSA, compare the modulus. They can't be a pair if that doesn't match. Doing this early
                 * check means we don't have to do a trial encryption for every public key (faster) and avoids warning
                 * messages from the depths of the crypto provider if the key lengths differ.
                 */
                
                if (privKey instanceof RSAKey && pubKey instanceof RSAKey) 
                {
                        RSAKey pubRSA = (RSAKey) pubKey;
                        RSAKey privRSA = (RSAKey) privKey;
                        
                        if (!privRSA.getModulus().equals(pubRSA.getModulus())) 
                        {
                      
                            MySystem.s_printOutError(this, strMethod, "public/private keys: RSA modulus mismatch ");
                                //log.debug("RSA modulus mismatch");
                                return false;
                        }
                }
                
                
                

                Cipher cipher = Cipher.getInstance(algorithm, "BC");
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                byte[] encryptedData = cipher.doFinal(controlString.getBytes("UTF-8"));

                cipher.init(Cipher.DECRYPT_MODE, privKey);
                byte[] decryptedData = cipher.doFinal(encryptedData);
                
                if (controlString.equals(new String(decryptedData, "UTF-8"))) 
                {
                        //log.debug("Found match.");
                        return true;
                }
                
            } 
            
            catch (Exception exc) 
            {
                exc.printStackTrace();
                MySystem.s_printOutError(this, strMethod, "exc caught");
                return false;
               //log.warn(e);
            }
            
            //log.debug("This pair does not match.");
            return false;
	}

        /**
	 * Given an ArrayList containing a base certificate and an array of unordered certificates, populates the ArrayList
	 * with an ordered certificate chain, based on subject and issuer.
	 *
	 * @param chainSource
	 *            array of certificates to pull from
	 * @param chainDest
	 *            ArrayList containing base certificate
	 * @throws InvalidCertificateChainException
	 *             thrown if a chain cannot be constructed from the specified elements
	 */

	private void _walkChain(X509Certificate[] crtsX509Source, ArrayList altTarget)
			throws Exception 
        {

            X509Certificate crtCur = (X509Certificate) altTarget.get(altTarget.size() - 1);
            
            if (crtCur.getSubjectDN().equals(crtCur.getIssuerDN())) 
            {
                //log.debug("Found self-signed root cert: " + crtCur.getSubjectDN());
                return;
            } 
            
            else 
            {
                for (int i=0; crtsX509Source.length>i; i++) 
                {
                    if (crtCur.getIssuerDN().equals(crtsX509Source[i].getSubjectDN())) 
                    {
                        altTarget.add(crtsX509Source[i]);
			_walkChain(crtsX509Source, altTarget);
			return;
                    }
		}
		
                //log.error("Incomplete certificate chain.");
		throw new Exception("Incomplete certificate chain.");
            }
	}
}
