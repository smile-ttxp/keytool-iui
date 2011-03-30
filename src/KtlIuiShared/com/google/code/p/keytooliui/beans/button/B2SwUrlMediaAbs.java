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

package com.google.code.p.keytooliui.beans.button;

/**
    a button displayed as a label, while clicked: open up a secondary window
    
    "B" for "Button"
    "Sw" for "Secondary Window"
    "Url" for "Uniform Resource Locator"
    "Media" means window displays a page of type "media", JMF's related
    "Abs" for "Abstract class"


    Known subclasses:
    . B2SwUrlMediaAudio
    . B2SwUrlMediaVideo
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.net.*;

abstract public class B2SwUrlMediaAbs extends B2SwUrlAbs 
{      
    // ---------
    // PROTECTED
    
    protected B2SwUrlMediaAbs()
    {
        super();
    }
    
    /*
        overwriting superclass's method in order to handle URL starting by "jar:file:"
        
        // 1) make a copy of the media (jarred) file in a temp file that is deleted at session's exit
        
        // 2) return the url of newly generated temp file
    */
    protected URL _getUrl_()
    {
        String strMethod = "_getUrl_()";
        
        MySystem.s_printOutFlagDev(this, strMethod, "only jarred ogg files supported for now");
        
        URL url = super._getUrl_();
        
        if (url == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil url");
            return null;
        }
        
        // if jarred
        if (url.getProtocol().toLowerCase().equals("jar"))
        {
            // if ogg file format
            if (com.google.code.p.keytooliui.javax.media.protocol.PullDataSourceJarNoOgg.s_isValidExtension(url))
                return url;
        }
        
        // unjarred
        else 
        {
            return url;
        }
        
        // -----------------------
        // jarred, all but not ogg
        
        String str = com.google.code.p.keytooliui.shared.bugfixes.net.S_Url.s_getToString(url);
        URL urlCloneTemp = com.google.code.p.keytooliui.shared.io.FileJar.s_getUrlCloneTemp(str);
        
        if (urlCloneTemp == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil urlCloneTemp, url.toString()=" + url.toString());
            return null;
        }
	    
	    return urlCloneTemp;
    }
}