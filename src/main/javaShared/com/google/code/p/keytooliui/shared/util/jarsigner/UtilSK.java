package com.google.code.p.keytooliui.shared.util.jarsigner;

import java.awt.Frame;
import java.security.KeyStore;
import com.google.code.p.keytooliui.shared.lang.MySystem;
import com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract;

/*
 * "SK" for "secret Key" (shared key)
 * displays:
 * . key algorithm
 * . key format
 * . key size 
 * . key value in hexadecimal (if format equals "RAW")
 */

public class UtilSK
{
    static private String _s_dumpIt(
        Frame frmParent, 
    
        KeyStore kst,
        String strAlias,
        char[] chrsPasswdEntry
            )
        throws 
            java.security.UnrecoverableKeyException,
            Exception
    {
        
        // --
        KeyStore.PasswordProtection ppnPasswordProtection = new KeyStore.PasswordProtection(chrsPasswdEntry);
        
        KeyStore.Entry ent = kst.getEntry(strAlias, ppnPasswordProtection);
 
       
        
        if (ent == null)
        {
            // no such entry
            throw new Exception("No such entry for alias: " + strAlias);
        }
        
        KeyStore.SecretKeyEntry keyEntry = (KeyStore.SecretKeyEntry) ent;
        
        javax.crypto.SecretKey keySecret = keyEntry.getSecretKey();
        
        byte[] bytsEncoded = keySecret.getEncoded();
        int intSize = bytsEncoded.length;
        
        String strValHexa = null;
        
        if (keySecret.getFormat().equalsIgnoreCase("RAW"))
        {
            strValHexa = new String("");
            
            for (int i=0; i<bytsEncoded.length; i++)
            {
                String strCur = Integer.toHexString((((int)bytsEncoded[i]) & 0xFF));
                
                if (strCur.length() == 1)
                    strValHexa += "0";
                
                strValHexa += strCur;
                
                if (i <bytsEncoded.length-1)
                    strValHexa += ":";
            }
        }
        
        
        String strResult = new String("Key infos:");
        strResult += "\n";
        strResult += ". Algorithm: " + keySecret.getAlgorithm();
        strResult += "\n";
        strResult += ". Format: " + keySecret.getFormat();
        strResult += "\n";
        strResult += ". Size: " + intSize + " bits";
        
        if (strValHexa != null)
        {
            strResult += "\n";
            strResult += ". Value in hexa: " + strValHexa;
        }
        
        return strResult;
    }
    
    
    static public void s_show(
        Frame frmParent, 
    
        KeyStore kst,
        String strAlias,
        char[] chrsPasswdEntry)
    {
        String strMethod = "com.google.code.p.keytooliui.shared.util.jarsigner" + "." + "s_show(...)";
        
        
        if (kst==null || strAlias==null || chrsPasswdEntry==null)
        {
            MySystem.s_printOutExit(strMethod, "nil arg");
        }
        
        String strContents = null;
        
        try
        {
            strContents = UtilSK._s_dumpIt(frmParent, kst, strAlias, chrsPasswdEntry);
        }
        
        catch(java.security.UnrecoverableKeyException excUnrecoverableKey)
        {
            excUnrecoverableKey.printStackTrace();
            String strBody = "Got UnrecoverableKeyException.";
            strBody += "\n  " + excUnrecoverableKey.getMessage();
            strBody += "\n \n" + "Given password may be wrong!";
 
            OPAbstract.s_showDialogError(frmParent, strBody);
            
            // ending
            return;
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            String strBody = "Got exception.";
            strBody += "\n  " + exc.getMessage();
 
            OPAbstract.s_showDialogError(frmParent, strBody);
            
            // ending
            return;
        }
        
        
        if (strContents == null)
        {
            MySystem.s_printOutError(strMethod, "nil strContents");
            
            String strBody = "Failed to get contents.";
 
            OPAbstract.s_showDialogError(frmParent, strBody);
            
            // --
            return;
        }
        
        // launch dialog 
        
        
        
        com.google.code.p.keytooliui.shared.swing.dialog.DViewString vsg = new
            com.google.code.p.keytooliui.shared.swing.dialog.DViewString(
            frmParent,
            //System.getProperty("_appli.title") + " - " + "view" + " " + "SecretKey infos",
            strContents
            );
                    
        if (! vsg.init())
            MySystem.s_printOutExit(strMethod, "failed");
                
        vsg.setVisible(true);
    }
    

}