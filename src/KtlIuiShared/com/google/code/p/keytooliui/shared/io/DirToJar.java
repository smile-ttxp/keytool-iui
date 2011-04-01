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
package com.google.code.p.keytooliui.shared.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 *
 * @author bantchao
 */
public class DirToJar 
{
    // ---------------------------
    // final static private String
    
    final static private String _STR_MANIFESTVERSION = "1.0";
    
    
    /** Creates a new instance of DirToJar */
    public DirToJar(
            String strPathAbsSourceDir, 
            String strPathAbsTargetJarUnsigned,
            String strManifestCreatedBySuffix // nil value allowed, ie "KeyTool IUI"
            ) 
    {
        this._strPathAbsSourceDir = strPathAbsSourceDir;
        this._strPathAbsTargetJarUnsigned = strPathAbsTargetJarUnsigned;
        this._strManifestCreatedBySuffix = strManifestCreatedBySuffix;
    }
    
    
    /*
     * if error occurs: throwing exception
     *
     * LIMITATIONS:
     * . in case of empty directory, jarring it anyway (the jar will only contain the manifest stuff
     */
    public void doJob() throws Exception
    {
        String strBaseFolder = this._strPathAbsSourceDir + File.separator;
        this._intBaseFolderLength = strBaseFolder.length();
        this._fosTargetJar = new FileOutputStream(this._strPathAbsTargetJarUnsigned);
        Manifest mnfManifest = new Manifest();
        Attributes attAttributesManifest = mnfManifest.getMainAttributes();

        attAttributesManifest.putValue("Manifest-Version", DirToJar._STR_MANIFESTVERSION);

        //if (strMainClass!=null){
          //  attAttributesManifest.putValue("Main-Class",strMainClass);
        //}
        //if (strClassPath!=null){
          //  attAttributesManifest.putValue("Class-Path",strClassPath);
        //}
        
        String strJavaVersion = System.getProperty("java.version");
        
        String strManifestCreatedBy = "JRE " + strJavaVersion;
        


        if (this._strManifestCreatedBySuffix != null)
            strManifestCreatedBy += " with " + this._strManifestCreatedBySuffix;
        
        attAttributesManifest.putValue("Created-By", strManifestCreatedBy);

        java.util.Set entries = attAttributesManifest.entrySet();

        /*for (java.util.Iterator i = entries.iterator(); i.hasNext();)
        {
            System.out.println("Manifest attribute:>> " + i.next().toString());
        }*/

        this._josTargetJar = new JarOutputStream(this._fosTargetJar, mnfManifest);

        _scanDirectory(this._strPathAbsSourceDir);
        // Close the file output streams
        this._josTargetJar.flush();
        this._josTargetJar.close();
        this._fosTargetJar.close();
        
        this._josTargetJar = null;
        this._fosTargetJar = null;
    }
    
    // -------
    // private
    
    // input
    private String _strPathAbsSourceDir = null;
    private String _strPathAbsTargetJarUnsigned = null;
    private String _strManifestCreatedBySuffix = null;
    
    
    private FileOutputStream _fosTargetJar = null;
    private JarOutputStream _josTargetJar = null;
    private int _intBaseFolderLength = 0;
  
    private void _scanDirectory(String strPathDirCur)
        throws Exception
    {
        File fleDirCur = new File(strPathDirCur);
        
        if (fleDirCur.exists()) 
        {
            if (fleDirCur.isDirectory()) 
            {
                File [] flesDirCurChildren = fleDirCur.listFiles();
            
                // Loop through the files
                for (int i = 0; i < flesDirCurChildren.length; i++) 
                {
                    if (flesDirCurChildren[i].isDirectory()) 
                    {
                    	_jarDir(flesDirCurChildren[i].getPath());
                        _scanDirectory(flesDirCurChildren[i].getPath());
                    } 
                    
                    else if (flesDirCurChildren[i].isFile()) 
                    {
                        // Call the zipFunc function
                        _jarFile(flesDirCurChildren[i].getPath());
                    }
                }
            }
            else {
                //System.out.println(strPathDirCur+" is not a directory.");
            }
        }
        else {
            //System.out.println("Directory "+strPathDirCur+" does not exist.");
        }
    }
    
    private void _jarDir(String strPathFile) 
        throws Exception
    {
        String fileDirEntry = strPathFile.substring(this._intBaseFolderLength).replace(File.separatorChar,'/')+"/";
        JarEntry fileEntry = new JarEntry(fileDirEntry);
        this._josTargetJar.putNextEntry(fileEntry);
       
        //System.out.println(fileDirEntry);
    }
  
    private void _jarFile(String strPathFile) 
        throws Exception
    {

        FileInputStream fis = new FileInputStream(strPathFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        String strPathRelativeEntry = strPathFile.substring(this._intBaseFolderLength).replace(File.separatorChar,'/');
        JarEntry jey = new JarEntry(strPathRelativeEntry);
        this._josTargetJar.putNextEntry(jey);

        byte[] byts = new byte[1024];
        int intByteCount;

        while ((intByteCount = bis.read(byts, 0, 1024)) > -1) 
        {
            this._josTargetJar.write(byts, 0, intByteCount);
        }
            
        //System.out.println("strPathRelativeEntry=" + strPathRelativeEntry);
    }
}
