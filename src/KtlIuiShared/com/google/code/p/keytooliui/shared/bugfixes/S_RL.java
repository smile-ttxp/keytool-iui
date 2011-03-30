/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
package com.google.code.p.keytooliui.shared.bugfixes;

/**
    MEMO:
    "RL" means "Resource Locator"
    an object of class:
    . java.net.URL
    . com.google.code.p.keytooliui.javax.media.rtp.RtpAbs
  
**/
 
import com.google.code.p.keytooliui.shared.lang.*;
 
public class S_RL
{
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.bugfixes.S_RL.";
    
    static public boolean s_samePathUnique(Object objRL1, Object objRL2)
    {
        String strWhere = _f_s_strClass + "s_samePathUnique(objRL1, objRL2)";
        
        if (objRL1==null || objRL2==null)
            MySystem.s_printOutExit(strWhere, "nil arg");
            
        // --
        
        if (objRL1 instanceof java.net.URL && objRL2 instanceof java.net.URL)
        {
            return com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_sameFile(
                (java.net.URL) objRL1, 
                (java.net.URL) objRL2);
        }
           
        if (objRL1 instanceof com.google.code.p.keytooliui.javax.media.rtp.RtpAbs && objRL2 instanceof com.google.code.p.keytooliui.javax.media.rtp.RtpAbs)
        {
            return com.google.code.p.keytooliui.javax.media.rtp.RtpAbs.s_samePathUnique(
                (com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) objRL1,
                (com.google.code.p.keytooliui.javax.media.rtp.RtpAbs) objRL2);
        }
        
        return false;
    }
}