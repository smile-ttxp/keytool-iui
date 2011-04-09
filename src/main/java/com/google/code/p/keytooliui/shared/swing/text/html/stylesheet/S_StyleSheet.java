/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
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

import javax.swing.text.html.*;

 
public class S_StyleSheet
{
    // --------------------
    // PRIVATE STATIC FINAL 
    
    private static final String[] _f_s_strsRuleDefault =
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
    // PUBLIC STATIC


    public static StyleSheet s_getDefault()
    {
        StyleSheet sst = new StyleSheet();
        
        for (int i=0; i<_f_s_strsRuleDefault.length; i++)
            sst.addRule(_f_s_strsRuleDefault[i]);   
        
        // ending
        return sst;
    }
    
}
