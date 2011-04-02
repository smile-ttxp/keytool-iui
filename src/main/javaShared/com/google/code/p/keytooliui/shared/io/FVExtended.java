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

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.io.*;
import java.util.*;


final public class FVExtended extends FileView
{
    // ------
    // PUBLIC
    
    public FVExtended()
    {
        super();
    }
    
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! _putIcons())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        return true;
    }
    
    public void destroy()
    {
    }
    

    /**
     * The name of the file.
     * @see #getName
     */
    public void setName(File fle, String strName)
    {
	    this._hshFileNames.put(fle, strName);
    }

    /**
     * The name of the file.
     * @see #setName
     * @see FileView#getName
     */
    public String getName(File f)
    {
	    return (String) _hshFileNames.get(f);
    }

    /**
     * Adds a human readable description of the file.
     */
    public void putDescription(File fle, String strDescription)
    {
	    this._hshFileDescriptions.put(fle, strDescription);
    }

    /**
     * A human readable description of the file.
     *
     * @see FileView#getDescription
     */
    public String getDescription(File f)
    {
	    return (String) _hshFileDescriptions.get(f);
    }

    /**
     * Adds a human readable type description for files. Based on "dot"
     * extension strings, e.g: ".gif". Case is ignored.
     */
    public void putTypeDescription(String extension, String typeDescription)
    {
	    _hshTypeDescriptions.put(extension, typeDescription);
    }

    /**
     * Adds a human readable type description for files of the type of
     * the passed in file. Based on "dot" extension strings, e.g: ".gif".
     * Case is ignored.
     */
    public void putTypeDescription(File f, String typeDescription)
    {
	    putTypeDescription(getExtension(f), typeDescription);
    }

    /**
     * A human readable description of the type of the file.
     *
     * @see FileView#getTypeDescription
     */
    public String getTypeDescription(File f)
    {
	    return (String) _hshTypeDescriptions.get(getExtension(f));
    }

    /**
     * Conveinience method that returnsa the "dot" extension for the
     * given file.
     */
    public String getExtension(File f)
    {
	    String name = f.getName();
	    
	    if(name != null)
	    {
	        int extensionIndex = name.lastIndexOf('.');
	        
	        if(extensionIndex < 0)
	        {
		        return null;
	        }
	        
	        return name.substring(extensionIndex+1).toLowerCase();
	    }
	    
	    return null;
    }

    /**
     * Adds an icon based on the file type "dot" extension
     * string, e.g: ".gif". Case is ignored.
     */
    /*public void putIcon(String extension, Icon icon)
    {
	    _hshIcons.put(extension, icon);
    }*/

    /**
     * Icon that represents this file. Default implementation returns
     * null. You might want to override this to return something more
     * interesting.
     *
     * @see FileView#getIcon
     */
    public Icon getIcon(File f)
    {
	    Icon icon = null;
	    String extension = getExtension(f);
	    
	    if(extension != null)
	    {
	        icon = (Icon) _hshIcons.get(extension);
	    }
	    
	    return icon;
    }

    /**
     * Whether the file is hidden or not. This implementation returns
     * true if the filename starts with a "."
     *
     * @see FileView#isHidden
     */
    public Boolean isHidden(File f)
    {
	    String name = f.getName();
	    
	    if(name != null && !name.equals("") && name.charAt(0) == '.')
	    {
	        return Boolean.TRUE;
	    }
	    
	    else
	    {
	        return Boolean.FALSE;
	    }
    }

    /**
     * Whether the directory is traversable or not. Generic implementation
     * returns true for all directories.
     *
     * You might want to subtype FVExtended to do somethimg more interesting,
     * such as recognize compound documents directories; in such a case you might
     * return a special icon for the diretory that makes it look like a regular
     * document, and return false for isTraversable to not allow users to
     * descend into the directory.
     *
     * @see FileView#isTraversable
     */
    public Boolean isTraversable(File f)
    {
	    if(f.isDirectory())
	    {
	        return Boolean.TRUE;
	    }
	    
	    else
	    {
	        return Boolean.FALSE;
	    }
    }
    
    // -------
    // PRIVATE
    
    private Hashtable<String, Icon> _hshIcons = new Hashtable<String, Icon>(5);
    private Hashtable<File, String> _hshFileNames = new Hashtable<File, String>(5);
    private Hashtable<File, String> _hshFileDescriptions = new Hashtable<File, String>(5);
    private Hashtable<String, String> _hshTypeDescriptions = new Hashtable<String, String>(5);
    
    /**
     * Adds an icon based on the file type "dot" extension
     * string, e.g: ".gif". Case is ignored.
     */
    private void _putIcon(String extension, Icon icon)
    {
	    this._hshIcons.put(extension, icon);
    }
    
    private boolean _putIcons()
    {
        String f_strMethod = "_putIcons()";
		
		// images
		ImageIcon iinImage = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileImage16);
		
		if (iinImage == null)
		{
	        MySystem.s_printOutError(this, f_strMethod, "nil iinImage");
	        return false;
	    }
		
		// htmls
		ImageIcon iinHtml = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileHTML16);
		
		if (iinHtml == null)
		{
	        MySystem.s_printOutError(this, f_strMethod, "nil iinHtml");
	        return false;
	    }
		
		// sound effects
		ImageIcon iinSound = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileSndfx16);
	
	    if (iinSound == null)
		{
	        MySystem.s_printOutError(this, f_strMethod, "nil iinSound");
	        return false;
	    }
	    
	    
	
	    // project RCReader document
		ImageIcon iinProjectReaderDocument = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
		    com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileProjRcr16);
	
	    if (iinProjectReaderDocument == null)
		{
	        MySystem.s_printOutError(this, f_strMethod, "nil iinProjectReaderDocument");
	        return false;
	    }
	
	    // project RCReader template
		ImageIcon iinProjectReaderTemplate = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileTemplate16);
	
	    if (iinProjectReaderTemplate == null)
		{
	        MySystem.s_printOutError(this, f_strMethod, "nil iinProjectReaderTemplate");
	        return false;
	    }
	
	    // project builder document
		ImageIcon iinProjectBuilderDocument = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
		    com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileProjGenDoc16);
		
		if (iinProjectBuilderDocument == null)
		{
	        MySystem.s_printOutError(this, f_strMethod, "nil iinProjectBuilderDocument");
	        return false;
	    }
		
		 // project builder document
		ImageIcon iinProjectBuilderTemplate = com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(
		    com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.f_s_strFileProjGenTpl16);
		
		if (iinProjectBuilderTemplate == null)
		{
	        MySystem.s_printOutError(this, f_strMethod, "nil iinProjectBuilderTemplate");
	        return false;
	    }
	
	    // sound effects
	    for (int i=0; i<S_FileExtension.f_s_strsSndfx.length; i++)
		{
		    _putIcon(S_FileExtension.f_s_strsSndfx[i], iinSound);
		}
		
		// image
		
		for (int i=0; i<S_FileExtension.f_s_strsImage.length; i++)
		{
		    _putIcon(S_FileExtension.f_s_strsImage[i], iinImage);
		}
		
		
		// html
		for (int i=0; i<S_FileExtension.f_s_strsPageTextHTML.length; i++)
		{
		    _putIcon(S_FileExtension.f_s_strsPageTextHTML[i], iinHtml);
		}
		

		
		// project RCReader document
		_putIcon(com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strProjectReaderDocument.toLowerCase(), iinProjectReaderDocument);
	
		// project RCReader template
		_putIcon(com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strProjectReaderTemplate.toLowerCase(), iinProjectReaderTemplate);
	

		// project builder document
		_putIcon(com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strProjectBuilderDocument.toLowerCase(), iinProjectBuilderDocument);
		
		// project builder template
		_putIcon(com.google.code.p.keytooliui.shared.io.S_FileExtension.f_s_strProjectBuilderTemplate.toLowerCase(), iinProjectBuilderTemplate);
	
			
		// ----
		
		return true;
    }
}
