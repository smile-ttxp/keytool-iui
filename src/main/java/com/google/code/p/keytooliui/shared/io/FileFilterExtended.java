/*
 *
 * Copyright (c)  2001-2011 RagingCat Project.
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
    BASED ON  Jeff Dinkins
 * A convenience implementation of FileFilter that filters out
 * all files except for those type extensions that it knows about.
 *
 * Extensions are of the type ".foo", which is typically found on
 * Windows and Unix boxes, but not on Macinthosh. Case is ignored.
 *
 * Example - create a new filter that filerts out all files
 * but gif and jpg image files:
 *
 *     JFileChooser chooser = new JFileChooser();
 *     FileFilterExtended filter = new FileFilterExtended(
 *                   new String{"gif", "jpg"}, "JPEG & GIF Images")
 *     chooser.addChoosableFileFilter(filter);
 *     chooser.showOpenDialog(this);
 *

 */
 


import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;
// memo: to remain unchanged, coz there is also java.io.FileFilter
import javax.swing.filechooser.FileFilter;

import java.io.File;
import java.util.*;




 
public class FileFilterExtended extends FileFilter
{
    // ------
    // PUBLIC

    /**
     * Creates a file filter. If no this._hstFilters are added, then all
     * files are accepted.
     *
     * @see #addExtension
     */
    public FileFilterExtended()
    {
	    this((String) null, (String) null);
    }

    /**
     * Creates a file filter that accepts files with the given extension.
     * Example: new FileFilterExtended("jpg");
     *
     * @see #addExtension
     */
    public FileFilterExtended(String strExtension)
    {
	    this(strExtension, null);
    }

    /**
     * Creates a file filter that accepts the given file type.
     * Example: new FileFilterExtended("jpg", "JPEG Image Images");
     *
     * Note that the "." before the strExtension is not needed. If
     * provided, it will be ignored.
     *
     * @see #addExtension
     */
    public FileFilterExtended(String strExtension, String strDescription)
    {
	    this(new String[] {strExtension}, strDescription);
    }

    /**
     * Creates a file filter from the given string array.
     * Example: new FileFilterExtended(String {"gif", "jpg"});
     *
     * Note that the "." before the strExtension is not needed adn
     * will be ignored.
     *
     * @see #addExtension
     */
    public FileFilterExtended(String[] strsFilters)
    {
	    this(strsFilters, null);
    }

    /**
     * Creates a file filter from the given string array and strDescription.
     * Example: new FileFilterExtended(String {"gif", "jpg"}, "Gif and JPG Images");
     *
     * Note that the "." before the strExtension is not needed and will be ignored.
     *
     * @see #addExtension
     */
    public FileFilterExtended(String[] strsFilters, String strDescription)
    {        
        String strMethod = "FileFilterExtended(strsFilters, strDescription)";
        
        if (strsFilters == null)
        {
            MySystem.s_printOutExit(this, strMethod, "nil strsFilters");
        }
        
        if (strsFilters.length < 1)
        {
            MySystem.s_printOutExit(this, strMethod, "strsFilters.length < 1");
        }
        
        
	    this._hstFilters = new Hashtable<String, FileFilterExtended>(strsFilters.length);
	    
	    for (int i=0; i<strsFilters.length; i++)
	    {
	        // add strsFilters one by one
	        if (! addExtension(strsFilters[i]))
	        {
	            MySystem.s_printOutExit(this, strMethod, "failed");
	        }
	    }
	    
	    setDescription(strDescription);
    }

    /**
     * Return true if this file should be shown in the directory pane,
     * false if it shouldn't.
     *
     * Files that begin with "." are ignored.
     *
     * @see #getExtension
     * @see FileFilter#accepts
     */
    public boolean accept(File f)
    {
	    if(f != null)
	    {
	        if(f.isDirectory())
	        {
		        return true;
	        }
	        
	        String strExtension = getExtension(f);
	        
	        if(strExtension != null && this._hstFilters.get(getExtension(f)) != null)
	        {
		        return true;
	        };
	    }
	    
	    return false;
    }

    /**
     * Return the strExtension portion of the file's name .
     *
     * @see #getExtension
     * @see FileFilter#accept
     */
     public String getExtension(File f)
     {
	    if(f != null)
	    {
	        String filename = f.getName();
	        int i = filename.lastIndexOf('.');
	        
	        if(i>0 && i<filename.length()-1)
	        {
		        return filename.substring(i+1).toLowerCase();
	        };
	    }
	
	    return null;
    }

    /**
     * Adds a filetype "dot" strExtension to filter against.
     *
     * For example: the following code will create a filter that strsFilters
     * out all files except those that end in ".jpg" and ".tif":
     *
     *   FileFilterExtended filter = new FileFilterExtended();
     *   filter.addExtension("jpg");
     *   filter.addExtension("tif");
     *
     * Note that the "." before the strExtension is not needed and will be ignored.
     */
    public boolean addExtension(String strExtension)
    {
        String strMethod = "addExtension(strExtension)";
        
        if (strExtension == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil strExtension");
            return false;
        }
        
	    if (this._hstFilters == null)
	    {
	        this._hstFilters = new Hashtable<String, FileFilterExtended>(5);
	    }
	    
	    this._hstFilters.put(strExtension.toLowerCase(), this);
	    this._strDescriptionFull = null;
	    return true;
    }


    /**
     * Returns the human readable strDescription of this filter. For
     * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     * @see FileFilter#getDescription
     */
    public String getDescription()
    {
	    if (this._strDescriptionFull == null)
	    {
	        if(this._strDescription == null || isExtensionListInDescription())
	        {
		        if(this._strDescription != null)
		        {
		            this._strDescriptionFull = this._strDescription;
		        }
		        
		        this._strDescriptionFull += " (";
		        // build the this._strDescription from the strExtension list
		        Enumeration extensions = this._hstFilters.keys();
		        
		        if(extensions != null)
		        {
		            this._strDescriptionFull += "." + (String) extensions.nextElement();
		            
		            while (extensions.hasMoreElements())
		            {
			            this._strDescriptionFull += ", " + (String) extensions.nextElement();
		            }
		        }
		        
		        this._strDescriptionFull += ")";
	        }
	        
	        else
	        {
		        this._strDescriptionFull = this._strDescription;
	        }
	    }
	    
	    return this._strDescriptionFull;
    }

    /**
     * Sets the human readable strDescription of this filter. For
     * example: filter.setDescription("Gif and JPG Images");
     *
     * @see setDescription
     * @see setExtensionListInDescription
     * @see isExtensionListInDescription
     */
    public void setDescription(String strDescription)
    {
	    this._strDescription = strDescription;
	    this._strDescriptionFull = null;
    }

    /**
     * Determines whether the strExtension list (.jpg, .gif, etc) should
     * show up in the human readable strDescription.
     *
     * Only relevent if a strDescription was provided in the constructor
     * or using setDescription();
     *
     * @see getDescription
     * @see setDescription
     * @see isExtensionListInDescription
     */
    public void setExtensionListInDescription(boolean bln)
    {
	    this._blnUseExtensionsInDescription = bln;
	    this._strDescriptionFull = null;
    }

    /**
     * Returns whether the strExtension list (.jpg, .gif, etc) should
     * show up in the human readable strDescription.
     *
     * Only relevent if a strDescription was provided in the constructor
     * or using setDescription();
     *
     * @see getDescription
     * @see setDescription
     * @see setExtensionListInDescription
     */
    public boolean isExtensionListInDescription()
    {
	    return this._blnUseExtensionsInDescription;
    }
    
    // -------
    // PRIVATE
    
    private Hashtable<String, FileFilterExtended> _hstFilters = null;
    private String _strDescription = null;
    private String _strDescriptionFull = null;
    private boolean _blnUseExtensionsInDescription = true;
}