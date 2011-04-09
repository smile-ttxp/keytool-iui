/*
 *
 * Copyright (c) 2001-2007 RagingCat Project.
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
 
 
 /**
    this order:
    
    1) look and feel
    2) tab placement

**/

package com.google.code.p.keytooliui.shared.io;



public final class LastUserPref extends LastUserAbstract
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    
    private static final String _f_s_strFileNameIni = new String("pref.ini");
    private static final String _f_s_strFileNameBak = new String("pref.bak");
    
    // ------
    // PUBLIC
       
    public LastUserPref(
        // application
        String strPathAbsHomeAppli, 
        String strVersionAppli,
        java.util.Vector<UserChoice> vecUserChoice
        
       )
    {
        super(strPathAbsHomeAppli, strVersionAppli,
                _f_s_strFileNameIni, _f_s_strFileNameBak, vecUserChoice);
    }
    
}