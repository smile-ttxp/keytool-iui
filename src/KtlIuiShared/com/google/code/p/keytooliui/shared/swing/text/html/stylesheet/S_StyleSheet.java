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
 
 
 package com.google.code.p.keytooliui.shared.swing.text.html.stylesheet;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.io.*;

import javax.swing.text.html.*;

 
public class S_StyleSheet
{
    // --------------------
    // FINAL STATIC PRIVATE 
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.swing.text.html.stylesheet.S_StyleSheet.";
    
    final static private String[] _f_s_strsRuleDefault =
    {
        //"body {color: green; background-color: Silver}"
        
        
        "nobr {white-space: nowrap}",
        "ol {margin-left: 50; margin-top: 10; list-style-type: decimal; margin-bottom: 10}",
        "u {text-decoration: underline}",
        "s {text-decoration: line-through}",
        "p {font-weight: normal; margin-top: 15; font-size: medium}",
        "dd p {margin-left: 0; margin-top: 0; margin-bottom: 0}",
        "ol li p {margin-top: 0; margin-bottom: 0}",
        "address {font-style: italic; color: blue}",
        "i {font-style: italic}",
        "h6 {font-weight: bold; margin-top: 10; margin-bottom: 10; font-size: xx-small}",
        "h5 {font-weight: bold; margin-top: 10; margin-bottom: 10; font-size: x-small}",
        "h4 {font-weight: bold; margin-top: 10; margin-bottom: 10; font-size: small}",
        "h3 {font-weight: bold; margin-top: 10; margin-bottom: 10; font-size: medium}",
        "h2 {font-weight: bold; margin-top: 10; margin-bottom: 10; font-size: large}",
        "h1 {font-weight: bold; margin-top: 10; margin-bottom: 10; font-size: x-large}",
        "dir li p {margin-top: 0; margin-bottom: 0}",
        
        "b {font-weight: bold}",
        
        "caption {caption-side: top; text-align: center}",
        "a {text-decoration: underline; color: blue}",
        "ul li ul li ul li {margin-left: 0; margin-top: 0; margin-bottom: 0; margin-right: 0}",
        "menu {margin-left: 40; margin-top: 10; margin-bottom: 10}",
        "menu li p {margin-top: 0; margin-bottom: 0}",
        "sup {vertical-align: sup}",
        "body {margin-left: 0; font-family: Serif; font-size: 14pt; color: black; background-color: #ffffff; margin-right: 0}",
        "ul li ul li ul {margin-left: 25; list-style-type: square}",
        "blockquote {margin-left: 35; margin-top: 5; margin-bottom: 5; margin-right: 35}",
        "samp {font-family: Monospaced; font-size: small}",
        "cite {font-style: italic}",
        "sub {vertical-align: sub}",
        "em {font-style: italic}",
        "ul li p {margin-top: 0; margin-bottom: 0}",
        "ul li ul li {margin-left: 0; margin-top: 0; margin-bottom: 0; margin-right: 0}",
        "var {font-weight: bold; font-style: italic}",
        "table {border-color: Gray; border-style: outset}",
        "dfn {font-style: italic}",
        "menu li {margin-left: 0; margin-top: 0; margin-bottom: 0; margin-right: 0}",
        "strong {font-weight: bold}",
        "ul {margin-left: 50; margin-top: 10; list-style-type: disc; margin-bottom: 10}",
        "center {text-align: center}",
        "ul li ul {margin-left: 25; list-style-type: circle}",
        "kbd {font-family: Monospaced; font-size: small}",
        "dir li {margin-left: 0; margin-top: 0; margin-bottom: 0; margin-right: 0}",
        "ul li menu {margin-left: 25; list-style-type: circle}",
        "dt {margin-top: 0; margin-bottom: 0}",
        "ol li {margin-left: 0; margin-top: 0; margin-bottom: 0; margin-right: 0}",
        "li p {margin-top: 0; margin-bottom: 0}",
  
        "strike {text-decoration: line-through}",
        "dl {margin-left: 0; margin-top: 10; margin-bottom: 10}",
        "tt {font-family: Monospaced}",
        "ul li {margin-left: 0; margin-top: 0; margin-bottom: 0; margin-right: 0}",
        "dir {margin-left: 40; margin-top: 10; margin-bottom: 10}",
        "tr {text-align: left}",
        "pre p {margin-top: 0}",
        "dd {margin-left: 40; margin-top: 0; margin-bottom: 0}",
        "th {padding-right: 3; border-color: Gray; font-weight: bold; padding-bottom: 3; text-align: center; padding-left: 3; padding-top: 3; border-style: inset}",
        "pre {margin-top: 5; margin-bottom: 5; font-family: Monospaced}",
        "td {padding-right: 3; border-color: Gray; padding-bottom: 3; padding-left: 3; padding-top: 3; border-style: inset}",
        "code {font-family: Monospaced; font-size: small}",
        "small {font-size: x-small}",
        "big {font-size: x-large}"
        
    };
    
    
    // -------------
    // STATIC PUBLIC
    
    
    static public boolean s_loadRuleImageBg(StyleSheet sst, FileJar fjrDoc, String strPathAbsoluteJar, String strPathRelativeImage)
    {
        String f_strMethod = _f_s_strClass + "s_loadRuleImageBg(sst, strPathAbsoluteJar, strPathRelativeImage)";
        
        if (sst==null || fjrDoc==null || strPathAbsoluteJar==null || strPathRelativeImage==null)
        {
            MySystem.s_printOutError(f_strMethod, "nil arg");
            return false;
        }
        
        String strPathAbsoluteImageBg = null;
        
        byte[] bytsBufferTemplateImage = _s_getBytesFromTemplateIfAny(fjrDoc, strPathRelativeImage);
        
        if (bytsBufferTemplateImage != null)
        {
            strPathAbsoluteImageBg = _s_getPathAbsoluteImageBgTemp(bytsBufferTemplateImage);
        }
        
        else
        {
            strPathAbsoluteImageBg = _s_getPathAbsoluteImageBg(strPathAbsoluteJar, strPathRelativeImage);
        }
        
        if (strPathAbsoluteImageBg == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil strPathAbsoluteImageBg");
            return false;
        }
        
        String strRuleImageBg = "body {background-image: ";
        strRuleImageBg += strPathAbsoluteImageBg;
        strRuleImageBg += "}";
            
        sst.addRule(strRuleImageBg);
        
        // ending
        return true;
    }
    
    static public StyleSheet s_getDefault()
    {
        StyleSheet sst = new StyleSheet();
        
        for (int i=0; i<_f_s_strsRuleDefault.length; i++)
            sst.addRule(_f_s_strsRuleDefault[i]);   
        
        // ending
        return sst;
    }
    
    // --------------
    // STATIC PRIVATE
    
    static private byte[] _s_getBytesFromTemplateIfAny(FileJar fjr, String strPathRelative)
    {
        String f_strMethod = _f_s_strClass + "_s_getBytesFromTemplateIfAny(fjr, strPathRelative)";
        
        if (fjr==null || strPathRelative==null)
        {
            MySystem.s_printOutExit(f_strMethod, "nil arg");
            
        }
        
        String strTemplateTest = "." + S_FileExtension.f_s_strProjectReaderTemplate.toLowerCase() + FileJar.f_s_strFileSeparatorMain;
        
        int intIndexOfTemplate = strPathRelative.toLowerCase().indexOf(strTemplateTest.toLowerCase());
        
        if (intIndexOfTemplate == -1) // not in template
        {
            //fjr.printContent();
            //MySystem.s_printOutWarning(f_strMethod, "NOT inside template, strPathRelative=" + strPathRelative);
            return null;
            
        }
        
        // ---------------
        // inside template, extracting template
        
        String strPathRelativeTemplate = null;
        String strPathRelativeImage = null;
            
        try
        {
            strPathRelativeTemplate = strPathRelative.substring(0, intIndexOfTemplate + 1 + S_FileExtension.f_s_strProjectReaderTemplate.length());
            strPathRelativeImage = strPathRelative.substring(intIndexOfTemplate + 1 + S_FileExtension.f_s_strProjectReaderTemplate.length() + FileJar.f_s_strFileSeparatorMain.length());
        }
            
        catch(IndexOutOfBoundsException excIndexOutOfBounds)
        {
            excIndexOutOfBounds.printStackTrace();
            MySystem.s_printOutExit(f_strMethod, "excIndexOutOfBounds caught");
            
        }
        
        if (strPathRelativeTemplate == null)
        {
            MySystem.s_printOutExit(f_strMethod, "nil strPathRelativeTemplate:" + strPathRelative);
            
        }
        
        if (strPathRelativeImage == null)
        {
            MySystem.s_printOutExit(f_strMethod, "nil strPathRelativeImage:" + strPathRelative);
            
        }
        
        // tempo
        String strPathRelativeResTemplate = FileLocation.f_strResource + "/";
        strPathRelativeResTemplate += strPathRelativeTemplate;
        
        // ----
        FileJar fjrTemplate = _s_loadTemplate(fjr, strPathRelativeResTemplate);
        
        if (fjrTemplate == null)
        {
            MySystem.s_printOutExit(f_strMethod, "nil fjrTemplate, strPathRelativeResTemplate=" + strPathRelativeResTemplate);
            
        }
        
        byte[] bytsBuffer = fjrTemplate.getResource(strPathRelativeImage);
        
        if (bytsBuffer == null)
        {
            fjrTemplate.printContent();
            MySystem.s_printOutExit(f_strMethod, "nil bytsBuffer, strPathRelativeImage=" + strPathRelativeImage);
            
        }
        
        
        // ending
        return bytsBuffer;
    }
    
    static private String _s_getPathAbsoluteImageBg(String strPathAbsoluteJar, String strPathRelativeImage)
    {
        String f_strMethod = _f_s_strClass + "_s_getPathAbsoluteImageBg(strPathAbsoluteJar, strPathRelativeImage)";
        
        if (strPathAbsoluteJar==null || strPathRelativeImage==null)
        {
            MySystem.s_printOutError(f_strMethod, "nil arg");
            return null;
        }
        
        
        String strPathAbsoluteImage = com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.s_systemPathToJavaPath(strPathAbsoluteJar);
                    
        if (strPathAbsoluteImage == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil strPathAbsoluteImage");
            return null;
        }
            
        strPathAbsoluteImage = "jar:file:" + strPathAbsoluteImage;
        strPathAbsoluteImage += FileJar.f_s_strFileSeparatorMain;
        strPathAbsoluteImage += FileLocation.f_strResource + "/";
            
        // --
        strPathAbsoluteImage += strPathRelativeImage;
        
        
        // ending
        return strPathAbsoluteImage;
    }
    
    /**
        image inside template,
        extracting image as "byte[]"
        then writing to temporary file,
        finally returns (temporary file's) absolute path.
    **/
    static private String _s_getPathAbsoluteImageBgTemp(byte[] bytsBuffer)
    {
        String f_strMethod = _f_s_strClass + "_s_getPathAbsoluteImageBgTemp(bytsBuffer)";
        
        if (bytsBuffer == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil bytsBuffer");
            return null;
        }
        
        // -------------------------
        // creates a temp file
        final String f_strSuffix = "." + "gif";
        
        java.io.File fle = S_FileSys.s_getFileTempFromBytes(bytsBuffer, f_strSuffix);
        
        if (fle == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil fle");
            return null;
        }
        
        String strPathJava = com.google.code.p.keytooliui.shared.lang.system.S_SystemShared.s_systemPathToJavaPath(fle.getAbsolutePath());
                    
        if (strPathJava == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil strPathJava");
            return null;
        }
        
        return "file:" + strPathJava;
    }
    
    /**
        if any error, returning nil
    
        x) loading template object (.fjr) from resources in jar file (.rcr) 
        x) return template
    **/
    static private FileJar _s_loadTemplate(FileJar fjrDoc, String strPathRelativeResTemplate)
    {
        String f_strMethod = _f_s_strClass + "_s_loadTemplate(fjrDoc, strPathRelativeResTemplate)";
        
        if (strPathRelativeResTemplate == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil strPathRelativeResTemplate");
            return null;
        }
        
        if (fjrDoc == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil fjrDoc");
            return null;
        }
        
        // -------------------------------------------------------------------
        // 1) loading template object (.fjr) from resources in jar file (.rcr) 
        
        byte[] bytsFileJarObj = fjrDoc.getResource(strPathRelativeResTemplate);
        
        if (bytsFileJarObj == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil bytsFileJarObj, strPathRelativeResTemplate=" + strPathRelativeResTemplate);
            return null;
        }
        
        FileJar fjr = null;
        
        try
        {
            java.io.ByteArrayInputStream fis = new java.io.ByteArrayInputStream(bytsFileJarObj);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(fis);
        
            fjr = (FileJar) ois.readObject();
            //MySystem.s_printOutTrace(f_strMethod, "(FileJar) ois.readObject()");
        }
        
        catch(Exception exc)
        {
            exc.printStackTrace();
            MySystem.s_printOutError(f_strMethod, "exc caught");
            return null;
        }
        
        
        if (fjr == null)
        {
            MySystem.s_printOutError(f_strMethod, "nil fjr");
            return null;
        }
        
        
        // ------------------
        // 2) return template
        
        return fjr;
    }
}