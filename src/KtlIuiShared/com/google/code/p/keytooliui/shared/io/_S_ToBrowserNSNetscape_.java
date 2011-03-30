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
 
package com.google.code.p.keytooliui.shared.io;

/**
    part of an old article (1994) http://home.netscape.com/newsref/std/x-remote.html
   
    REMOTE CONTROL OF UNIX NETSCAPE 
    
    
    SPECIAL ACTIONS 
Invoking any action with no arguments (as above) will have the same effect as selecting the corresponding menu item. However, some actions have slightly different behavior that can be accessed by passing arguments: 

openURL ( ) 
Prompts for a URL with a dialog box. 
openURL (URL) 
Opens the specified document without prompting. 
openURL (URL, new-window) 
Create a new window displaying the the specified document. 
openFile ( ) 
Prompts for a file with a dialog box. 
openFile (File) 
Opens the specified file without prompting. 
saveAs ( ) 
Prompts for a file with a dialog box (like the menu item). 
saveAs (Output-File) 
Writes HTML to the specified file without prompting. 
saveAs (Output-File, Type) 
Writes to the specified file with the type specified - the type may be HTML, Text, or PostScript. 
mailto ( ) 
pops up the mail dialog with the To: field empty. 
mailto (a, b, c) 
Puts the addresses "a, b, c" in the default To: field. 
addBookmark ( ) 
Adds the current document to the bookmark list. 
addBookmark (URL) 
Adds the given document to the bookmark list. 
addBookmark (URL, Title) 
Adds the given document to the bookmark list, with the given title. 
All actions not listed here ignore any arguments given them. However, they may be expanded to take arguments in the future. For a full list of available actions, consult the resource file, Netscape.ad. 


FURTHER CONTROL 
A few other options exist for finer-grained control of the behavior of the remotely executed commands. 
-id window 
You can select a window to control with the -id command-line option; otherwise, the first Netscape Navigator window found will be used. The argument to this option is an X window id, as a decimal or hexadecimal number. (You can find window IDs with the xwininfo(1) or xlswins(1) programs.) For example: 
        netscape -id 0x3c00124 -remote 'openURL(http://home.netscape.com)'

-raise
-noraise 
You can control whether the -remote command will cause the Netscape window to automatically raise itself to the top with the -raise and -noraise options. The default is to raise, but this may be disabled as follows: 
        netscape -noraise -remote 'openURL(http://home.netscape.com)'

The -raise and -noraise options apply to all following -remote commands on the command line, and may be interleaved.For example, to add a bookmark without raising the window, and then open a URL and raise the window, one could do 
        netscape -noraise -remote 'addBookmark(http://foo)'  \
                 -raise   -remote 'openURL(http://bar)'



**/

import com.google.code.p.keytooliui.shared.lang.*;

public class _S_ToBrowserNSNetscape_ extends _S_ToBrowserNSAbs_
{
    // ---------------------------
    // FINAL STATIC PUBLIC STRING
    
    final static protected String _f_s_strName_ = "netscape";
    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.io._S_ToBrowserNSNetscape_.";
    
    
    // ----------------
    // STATIC PROTECTED
    
    
    static protected boolean _s_displayURL_(String strUrl)
    {    
        String strMethod = _f_s_strClass + "_s_displayURL_(strUrl)";
        
        if (! _S_ToBrowserNSAbs_._s_displayURL_(_f_s_strName_, strUrl))
        {
            MySystem.s_printOutError(strMethod, "failed");
            return false;
        }
            
        // ending
        return true;
    }
    
    
    // -------
    // PRIVATE
    
    
	// Preventing to instantiate.
	private _S_ToBrowserNSNetscape_() {}
	
	
	
}
