/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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

package com.google.code.p.keytooliui.shared.util.jar;

/**
    
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.optionpane.*;

// ==== beg not API
//import sun.security.util.SignatureFile;
import com.google.code.p.keytooliui.shared.security.util.MySignatureFile;
// ==== end not API


import java.util.jar.*;
import java.io.*;
import java.awt.*;

final public class S_JarOutputStream
{
    // -------------------
    // FINAL STATIC PUBLIC
    
    final static public int f_s_intLengthBytsBuffer = 2048;
    
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.share.util.jar.S_JarOutputStream.";
    
    // -------------
    // STATIC PUBLIC
    
    static public JarOutputStream s_create(File fle)
    {
        String strMethod = _f_s_strClass + "s_create(fle)";
        
        if (fle == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }
        
        JarOutputStream jos = null;
        
        try 
		{ 
		    jos = new JarOutputStream(new FileOutputStream(fle)); 
		} 
		
		catch(FileNotFoundException excFileNotFound) 
		{ 		    
			excFileNotFound.printStackTrace();
			MySystem.s_printOutError(strMethod, "excFileNotFound caught");
			return null;
		}
		
		catch(IOException excIO) 
		{ 		    
			excIO.printStackTrace();
			MySystem.s_printOutError(strMethod, "excIO caught");
			return null;
		}
		
		return jos;
    }
    
    static public JarOutputStream s_createWithManifest(
        String strPathAbs,
        Frame frmOwner,
        String strTitleAppli
        )
    {
        String strMethod = _f_s_strClass + "s_createWithManifest(strPathAbs, frmOwner, strTitleAppli)";
        
        JarOutputStream jos = S_JarOutputStream.s_create(strPathAbs);
        
        if (jos == null)
        {
            MySystem.s_printOutError(strMethod, "nil jos");
            return null;
        }
        
        // --
        
        Manifest man = S_Manifest.s_create();
        
        if (! S_JarOutputStream.s_writeManifest(jos, man, frmOwner, strTitleAppli))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return null;
        }
		
		return jos;
    }
    
    static public JarOutputStream s_create(String strPathAbs)
    {
        String strMethod = _f_s_strClass + "s_create(strPathAbs)";
        
        if (strPathAbs == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return null;
        }
        
        JarOutputStream jos = null;
        
        try 
		{ 
		    jos = new JarOutputStream(new FileOutputStream(strPathAbs)); 
		} 
		
		catch(FileNotFoundException excFileNotFound) 
		{ 		    
			excFileNotFound.printStackTrace();
			MySystem.s_printOutError(strMethod, "excFileNotFound caught");
			return null;
		}
		
		catch(IOException excIO) 
		{ 		    
			excIO.printStackTrace();
			MySystem.s_printOutError(strMethod, "excIO caught");
			return null;
		}
		
		return jos;
    }
    
    static public boolean s_close(JarOutputStream jos)
    {
        String strMethod = _f_s_strClass + "s_close(jos)";
        
        if (jos == null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        try
        {
            jos.flush(); 
			jos.close(); 
	    }
	    
	    catch(IOException excIO)
	    {
	        excIO.printStackTrace();
	        MySystem.s_printOutError(strMethod, "excIO caught");
	        return false;
	    }
	    
	    return true;
    }
    
    
    static public boolean s_writeEntry(
        JarOutputStream jos,
        MySignatureFile.Block blk,
        Frame frmOwner,
        String strTitleAppli
    )
    {
        String strMethod = _f_s_strClass + "s_writeEntry(jos, blk, frmOwner, strTitleAppli)";
        
        if (jos==null || blk==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        String strMetaName = blk.getMetaName();
        JarEntry jey = new JarEntry(strMetaName);
        
        try
        {
            jos.putNextEntry(jey);
            blk.write(jos);
            jos.closeEntry(); // ???????????????
        }
        
        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            
            String strBody = "Got IO exception"; 
            OPAbstract.s_showDialogError(frmOwner, strTitleAppli, strBody);
            
            
            return false;
        }
        
        
        
        return true;
    }
    
    /*
         write out the signature file -- the signatureFile 
         object will name itself appropriately
    */    
    static public boolean s_writeEntry(
        JarOutputStream jos,
        MySignatureFile sfe,
        Frame frmOwner,
        String strTitleAppli
    )
    {
        String strMethod = _f_s_strClass + "s_writeEntry(jos, sfe, frmOwner, strTitleAppli)";
        
        if (jos==null || sfe==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        String strMetaName = sfe.getMetaName();
        JarEntry jey = new JarEntry(strMetaName);
        
        try
        {
            jos.putNextEntry(jey);
            sfe.write(jos);
            jos.closeEntry(); // ???????????????
        }
        
        catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            
            String strBody = "Got IO exception"; 
            OPAbstract.s_showDialogError(frmOwner, strTitleAppli, strBody);
            return false;
        }

        return true;
    }
    
    static public boolean s_writeEntry(
        JarOutputStream jos,
        JarEntry jey,
        byte[] byts
        )
    {
        return S_JarOutputStream.s_writeEntry(jos, jey, byts, (Frame) null, (String) null);
    }
    
    static public boolean s_writeEntry(
        JarOutputStream jos,
        JarEntry jey,
        byte[] byts,
        Frame frmOwner,
        String strTitleAppli
        )
    {
        String strMethod = _f_s_strClass + "s_writeEntry(jos, jey, byts, frmOwner, strTitleAppli)";
        
        if (jos==null || jey==null || byts==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        try
        {
            jos.putNextEntry(jey);
            jos.write(byts);
            jos.closeEntry(); // added oct 29, 2003
	    }

	    catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            String strBody = "Got IO exception"; 
            OPAbstract.s_showDialogError(frmOwner, strTitleAppli, strBody);
            return false;
        }
            
            
        // ending
        return true;
    }
    
    
    
    static public boolean s_writeEntry(
        JarOutputStream jos,
        JarEntry jey,
        JarFile jfeInput,
        Frame frmOwner,
        String strTitleAppli
        )
    {
        String strMethod = _f_s_strClass + "s_writeEntry(jos, jey, jfeInput, frmOwner, strTitleAppli)";
        
        if (jos==null || jey==null || jfeInput==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        try
        {
            jos.putNextEntry(jey);
            
            // beg write   
            InputStream ism = jfeInput.getInputStream(jey);
            byte[] byts = new byte[S_JarOutputStream.f_s_intLengthBytsBuffer];
            int intChr = 0;
        
            while (( intChr = ism.read(byts)) > 0)
            {
                jos.write(byts, 0, intChr);
            }
            // end write
            
            
            jos.closeEntry(); // added oct 29, 2003
	    }

	    catch (IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            String strBody = "Got IO exception"; 
            OPAbstract.s_showDialogError(frmOwner, strTitleAppli, strBody);
            return false;
        }
            
            
        // ending
        return true;
    }
    
    
    static public boolean s_writeManifest(
        JarOutputStream jos,
        Manifest man,
        Frame frmOwner,
        String strTitleAppli
        )
    {
        String strMethod = _f_s_strClass + "s_writeManifest(jos, man, frmOwner, strTitleAppli)";
        
        if (jos==null || man==null)
        {
            MySystem.s_printOutError(strMethod, "nil arg");
            return false;
        }
        
        JarEntry jeyManifest = new JarEntry(com.google.code.p.keytooliui.shared.util.jar.S_Manifest.f_s_strPathRelManifest);
            
        byte bytsManifest[] =
            com.google.code.p.keytooliui.shared.util.jar.S_Manifest.s_toByteArray(man, frmOwner, strTitleAppli);
            
        if (bytsManifest == null)
        {
            MySystem.s_printOutError(strMethod, "nil bytsManifest");
            return false;
        }
        
        try
        {
            jos.putNextEntry(jeyManifest);
            jos.write(bytsManifest, 0, bytsManifest.length);
            jos.closeEntry();
        }
        
        catch(IOException excIO)
        {
            excIO.printStackTrace();
            MySystem.s_printOutError(strMethod, "excIO caught");
            String strBody = "Got IO exception"; 
            OPAbstract.s_showDialogError(frmOwner, strTitleAppli, strBody);
            return false;
        }
        
        // ending
        return true;
    }
}