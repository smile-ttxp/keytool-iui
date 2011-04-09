package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Kpr" for "KeyPair"
    
    
    known subclasses:
    . KTLKprSaveAbs
    . KTLKprOpenAbs  

**/


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

// ----
import java.security.KeyStore;
import java.security.KeyStoreException;
// --
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;

abstract public class KTLKprAbs extends KTLAbs
{
    // ----------------
    // PROTECTED STATIC
    
    // test
    protected static PrivateKey _s_readKey_(File fleOpen, String strSignatureAlgoCandidate)
        throws Exception
    {
        String strMethod = "_s_readKey_(...)";
        
        /*String strSignatureAlgo = null;
        
        for (int i=0; i<KTLAbs.f_s_strsSigAlgoSKJceks.length; i++)
        {
            if (! strSignatureAlgoCandidate.equalsIgnoreCase(KTLAbs.f_s_strsSigAlgoSKJceks[i]))
                continue;
            
            strSignatureAlgo = KTLAbs.f_s_strsSigAlgoSKJceks[i];
            break;
        }
        
        if (strSignatureAlgo == null)
        {
            MySystem.s_printOutExit(strMethod, "uncaught strSignatureAlgoCandidate:" + strSignatureAlgoCandidate);
        }*/
        
        String strSignatureAlgo = strSignatureAlgoCandidate;
        
        
        
        
        // Read the raw bytes from the keyfile
        DataInputStream dis = new DataInputStream(new FileInputStream(fleOpen));
        byte[] bytsRawKey = new byte[(int)fleOpen.length( )];
        dis.readFully(bytsRawKey);
        dis.close( );
        
        PrivateKey pky = null;
        
        //if (strSignatureAlgo.equalsIgnoreCase("RSA"))
        {
            /*DESKeySpec obj = new DESKeySpec(bytsRawKey);
            PrivateKeyFactory skf = PrivateKeyFactory.getInstance(strSignatureAlgo);
            pky = skf.generateSecret(obj);*/
        }
        
        //else if (strSignatureAlgo.equalsIgnoreCase("DSA"))
        {
            /*DESedeKeySpec obj = new DESedeKeySpec(bytsRawKey);
            PrivateKeyFactory skf = PrivateKeyFactory.getInstance(strSignatureAlgo);
            pky = skf.generateSecret(obj);*/
        }
        
        //else if (strSignatureAlgo.equalsIgnoreCase("EC"))
        {
            /*DESedeKeySpec obj = new DESedeKeySpec(bytsRawKey);
            PrivateKeyFactory skf = PrivateKeyFactory.getInstance(strSignatureAlgo);
            pky = skf.generateSecret(obj);*/
        }
        
        //else
        {
            
            
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytsRawKey);
            pky = KeyFactory.getInstance(strSignatureAlgo).generatePrivate(spec);
            
            
        }
        
        
        
        
        
        
        // ----
        //sun.security.pkcs.PKCS8Key
            //PKCS8Key pkcs8 = new PKCS8Key( in, "changeit".toCharArray() );
        
        
        
        //InputStream in = new FileInputStream(fleOpen);

        // If the provided InputStream is encrypted, we need a password to decrypt
        // it. If the InputStream is not encrypted, then the password is ignored
        // (can be null).  The InputStream can be DER (raw ASN.1) or PEM (base64).
        //sun.security.pkcs.PKCS8Key pkcs8 = new sun.security.pkcs.PKCS8Key( in, "changeit".toCharArray() );

        // If an unencrypted PKCS8 key was provided, then this actually returns
        // exactly what was originally passed in (with no changes).  If an OpenSSL
        // key was provided, it gets reformatted as PKCS #8 first, and so these
        // bytes will still be PKCS #8, not OpenSSL.
        
        
        //byte[] decrypted = pkcs8.getDecryptedBytes();
        //PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec( decrypted );

        // A Java PrivateKey object is born.
        /*PrivateKey pk = null;
        if ( pkcs8.isDSA() )
        {
          pk = KeyFactory.getInstance( "DSA" ).generatePrivate( spec );
        }
        else if ( pkcs8.isRSA() )
        {
          pk = KeyFactory.getInstance( "RSA" ).generatePrivate( spec );
        }

        // For lazier types (like me):
        pk = pkcs8.getPrivateKey();
        */
        
        
        // ----
        
        
        
        
        
        
        
        
        
        return pky;
    }
    
    
    // "Kpg" means "KeyPair Generator"
    // !!!! should be in subclass named KTLKprSaveNewAbs or? KTLKprSaveAbs !!!!
    protected static boolean _s_isProviderKpgAllowed_(String strTypeKpr, String strProvider)
    {
        if (strTypeKpr==null || strProvider==null)
            return false;
            
        // DSA
        if (strTypeKpr.toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairDsa.toLowerCase()) == 0)
        {
            for (int i=0; i<KTLAbs.f_s_strsProviderKpgDsa.length; i++)
            {
                if (strProvider.toLowerCase().compareTo(KTLAbs.f_s_strsProviderKpgDsa[i].toLowerCase()) == 0)
                    return true;
            }
            
            return false;
        }
        
        // RSA
        if (strTypeKpr.toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairRsa.toLowerCase()) == 0)
        {
            for (int i=0; i<KTLAbs.f_s_strsProviderKpgRsa.length; i++)
            {
                if (strProvider.toLowerCase().compareTo(KTLAbs.f_s_strsProviderKpgRsa[i].toLowerCase()) == 0)
                    return true;
            }
            
            return false;
        }
        
        // EC
        if (strTypeKpr.toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairEc.toLowerCase()) == 0)
        {
            for (int i=0; i<KTLAbs.f_s_strsProviderKpgEc.length; i++)
            {
                if (strProvider.toLowerCase().compareTo(KTLAbs.f_s_strsProviderKpgEc[i].toLowerCase()) == 0)
                    return true;
            }
            
            return false;
        }
             
        return false;
    }
    
    // ---------
    // PROTECTED
    
    
    /**
        "Any" means either "DSA" or "RSA"
        
        keypairEntry & DSA-RSA & X.509
    **/
    protected Boolean[] _getBoosElligibleAny_(
        Boolean[] boosEntryKpr,
        String[] strsAlgoKeyPubl,
        Boolean[] boosTypeCertX509
        )
    {
        String strMethod = "_getBoosElligibleAny_(...)";
        
        if (boosEntryKpr==null || strsAlgoKeyPubl==null || boosTypeCertX509==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosElligible = new Boolean[strsAlgoKeyPubl.length];
        
        for (int i=0; i<strsAlgoKeyPubl.length; i++)
        {
            boolean blnOk = true;
            
            // should be keypair entry, not trusted certificate entry
            
            if (boosEntryKpr[i].booleanValue() == false)
            {
                blnOk = false;
            }

   
            // should be either of type RSA or of type DSA (test: , or of type EC)
            else if (
                (strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairRsa.toLowerCase())!=0)
                &&
                (strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairDsa.toLowerCase())!=0)
                
                // beg test
                &&
                (strsAlgoKeyPubl[i].toLowerCase().compareTo(KTLAbs.f_s_strTypeKeypairEc.toLowerCase())!=0)
                // end test
                
                )
            {
                blnOk = false;
            }
            
            // also, certificate should be of type X509
            else if (boosTypeCertX509[i].booleanValue() == false)
            {
                blnOk = false;
            }

            boosElligible[i] = new Boolean(blnOk);
        }

        // ----
        return boosElligible;
    }
    
    protected Boolean[] _getBoosTypeCertX509_(KeyStore kstOpen, String[] strsAlias)
    {
        String strMethod = "_getBoosTypeCertX509_(kstOpen, strsAlias)";
        
        if (kstOpen==null || strsAlias==null)
            MySystem.s_printOutExit(this, strMethod, "nil arg");

        Boolean[] boosTypeCertX509 = new Boolean[strsAlias.length];
        
        for (int i=0; i<strsAlias.length; i++)
        {
            Certificate crt = null;
            
            try
            {
                crt = kstOpen.getCertificate(strsAlias[i]);
            }
            
            catch(KeyStoreException excKeyStore) // memo: if the keystore has not been initialized (loaded).
            {
                MySystem.s_printOutError(this, strMethod, "Got keystore Exception");
                
                String strBody = "Got keystore Exception.";
                
                OPAbstract.s_showDialogWarning(
                    super._frmOwner_, strBody);
                
                return null;
            }
            
            boolean blnOk = true;
            
            if (crt == null)
                blnOk = false;
            else if (! (crt instanceof X509Certificate)) 
                blnOk = false;
                 
            boosTypeCertX509[i] = new Boolean(blnOk);
        }

        // ----
        return boosTypeCertX509;
    }

    protected KTLKprAbs(
        Frame frmOwner, 
  
        
        // input
        String strPathAbsKst, // existing keystore of type [JKS-JCEKS-PKCS12]
        char[] chrsPasswdKst,
        
        String strProviderKst
        
        )
    {
        super(frmOwner,  strPathAbsKst, chrsPasswdKst, strProviderKst);
        
    }

}