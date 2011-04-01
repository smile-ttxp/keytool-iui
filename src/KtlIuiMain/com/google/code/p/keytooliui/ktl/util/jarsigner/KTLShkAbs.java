package com.google.code.p.keytooliui.ktl.util.jarsigner;

/**

    "Shk" for "Shared Key" (Secret Key)
    
    
    known subclasses:
    . KTLShkSaveAbs: pending
    . KTLShkOpenAbs  

**/


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;
import com.google.code.p.keytooliui.shared.util.jarsigner.*;

// ----
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.KeyStoreException;
import java.security.Provider;
// --
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
// ----

import java.awt.*;
import java.util.*;

abstract public class KTLShkAbs extends KTLAbs
{
    // -------------
    // static public
    
    /* IN PROGRESS, TO BE USED BY keystoreManager 
     static public boolean s_createKey(
        Frame frmOwner,
    
        String strPathAbsOpenKst, // existing keystore
        char[] chrsPasswdOpenKst,
        String strProviderKst, 
        String strSignatureAlgo)
    {
        String strMethod = "s_createKey(...)";
        
        File fleOpen = null;
        
        // ---
        
        if (fleOpen == null)
        {
            return true; // maybe aborted by user
        }
        
        // ----
        
        SecretKey sky = KTLShkAbs._s_readKey_(fleOpen, strSignatureAlgo);
        
        if (sky == null)
        {
            
            return false;
        }
        
        char[] chrsPasswdShk = null;
        
        // ---
        
        if (chrsPasswdShk == null)
        {
            
            return true; // maybe aborted by user
        }
        
        // ----
        if (! UtilKstAbs.s_setKeyEntry(frmOwner, 
            kstOpen, strAliasShk, ShkNew, chrsPasswdShk, null))
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        // ending
        return true;
    }*/
    
    
   
    static protected SecretKey _s_readKey_(File fleOpen, String strSignatureAlgoCandidate)
        throws IOException, NoSuchAlgorithmException,
               InvalidKeyException, InvalidKeySpecException
    {
        String strMethod = "_s_readKey_(...)";
        
        String strSignatureAlgo = null;
        
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
        }
        
        // Read the raw bytes from the keyfile
        DataInputStream dis = new DataInputStream(new FileInputStream(fleOpen));
        byte[] bytsRawKey = new byte[(int)fleOpen.length( )];
        dis.readFully(bytsRawKey);
        dis.close( );
        
        SecretKey sky = null;
        
        if (strSignatureAlgo.equalsIgnoreCase("DES"))
        {
            DESKeySpec obj = new DESKeySpec(bytsRawKey);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(strSignatureAlgo);
            sky = skf.generateSecret(obj);
        }
        
        else if (strSignatureAlgo.equalsIgnoreCase("DESede"))
        {
            DESedeKeySpec obj = new DESedeKeySpec(bytsRawKey);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(strSignatureAlgo);
            sky = skf.generateSecret(obj);
        }
        
        else
        {
            SecretKeySpec obj = new SecretKeySpec(bytsRawKey, strSignatureAlgo);
            // BUG !SecretKeyFactory skf = SecretKeyFactory.getInstance(strSignatureAlgo);
            sky = (SecretKey) obj;   //skf.generateSecret(obj);
        }
        
        return sky;
    }
    
    
    // ----------------
    // STATIC PROTECTED

    
    /*
     *      Memo: SecretKeyFactory for SunJCE, with JRE1.6
            DES
            DESede
            PBEWithMD5AndDES
            PBEWithMD5AndTripleDES
            PBEWithSHA1AndDESede
            PBEWithSHA1AndRC2_40
            PBKDF2WithHmacSHA1 
    */
    static protected void _writeKey_(SecretKey sky, File fleSave)
        throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, Exception
    {
        
        String strMethod = "KTLShkAbs._writeKey_(...)";

           
        String strAlgoSecretKey = sky.getAlgorithm();
        
        //KeySpec ksc = null;
        byte[] bytsRawKey = null;
        
        if (strAlgoSecretKey.equalsIgnoreCase("DES"))
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(strAlgoSecretKey);
            DESKeySpec spec = (DESKeySpec) skf.getKeySpec(sky, DESKeySpec.class);
            bytsRawKey = spec.getKey();
        }
        
        else if (strAlgoSecretKey.equalsIgnoreCase("DESede"))
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(strAlgoSecretKey);
            DESedeKeySpec spec = (DESedeKeySpec) skf.getKeySpec(sky, DESedeKeySpec.class);
            bytsRawKey = spec.getKey( );
        }
        
        else
        {
            // beg test
            //SecretKeyFactory skf = SecretKeyFactory.getInstance(strAlgoSecretKey/*, "BC"*/);
            
            //SecretKeySpec spec = (SecretKeySpec) skf.getKeySpec(sky, KeySpec.class);
            //SecretKeySpec spec = (SecretKeySpec) sky; // test, suggested by sean mullan
            //bytsRawKey = spec.getEncoded();
            
            
            
            /*
            byte[] raw = sky.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");
            */
            
            
            
            SecretKeySpec sks = (SecretKeySpec) sky; // test, suggested by sean mullan
            bytsRawKey = sks.getEncoded();
            
            
            // end test
            
            
            //String strBody = "DEV ERROR, unsupported algo, strAlgoSecretKey=" + strAlgoSecretKey;
            //throw new Exception(strBody);
        }
                

        // Write the raw key to the file
        FileOutputStream fos = new FileOutputStream(fleSave);
        fos.write(bytsRawKey);
        fos.close( );
    }
    
    
    
    /** 
     * Use the specified key to decrypt bytes ready from the input 
     * stream and write them to the output stream.  This method 
     * uses Cipher directly to show how it can be done without 
     * CipherInputStream and CipherOutputStream.
     **/
    static protected void _s_decrypt_(
            SecretKey key, 
            InputStream in, 
            OutputStream out,
            String strInstanceCipher)
        throws 
            NoSuchAlgorithmException, 
            InvalidKeyException, 
            IOException,
            IllegalBlockSizeException, 
            NoSuchPaddingException,
            BadPaddingException
    {
        
        // Create and initialize the decryption engine
        Cipher cipher = Cipher.getInstance(strInstanceCipher);
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Read bytes, decrypt, and write them out.
        byte[] buffer = new byte[2048];
        int bytesRead;
        
        while((bytesRead = in.read(buffer)) != -1) {
            out.write(cipher.update(buffer, 0, bytesRead));
        }

        // Write out the final bunch of decrypted bytes
        out.write(cipher.doFinal( ));
        out.flush( );
    }
    
    /** 
     * Use the specified key to encrypt bytes from the input stream
     * and write them to the output stream.  This method uses 
     * CipherOutputStream to perform the encryption and write bytes at the
     * same time.
     **/
    static protected void _s_encrypt_(
            SecretKey sky, 
            InputStream ism, 
            OutputStream osm,
            String strInstanceCipher)
        throws 
            NoSuchAlgorithmException, 
            InvalidKeyException,
            NoSuchPaddingException, 
            IOException
    {
        // Create and initialize the encryption engine
        Cipher cip = Cipher.getInstance(strInstanceCipher);
        cip.init(Cipher.ENCRYPT_MODE, sky);

        // Create a special output stream to do the work for us
        CipherOutputStream cos = new CipherOutputStream(osm, cip);

        // Read from the input and write to the encrypting output stream
        byte[] bytsBuffer = new byte[2048];
        int intBytesRead;
        
        while((intBytesRead = ism.read(bytsBuffer)) != -1) 
        {
            cos.write(bytsBuffer, 0, intBytesRead);
        }
        
        cos.close( );

        // For extra security, don't leave any plaintext hanging around memory.
        java.util.Arrays.fill(bytsBuffer, (byte) 0);
    }
    
    /*
     *public static byte[] encrypt(Key key, String text) throws Exception {
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
byte[] encrypted = cipher.doFinal(text.getBytes());
return encrypted;
}

public static String decrypt(Key key, byte[] encrypted) throws Exception {
Cipher cipher = Cipher.getInstance("RSA");
cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
byte[] decrypted = cipher.doFinal(encrypted);
return new String(decrypted);
}
     */
    
    
   
    
    // ---------
    // PROTECTED
    

    protected KTLShkAbs(
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
