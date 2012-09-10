<!--
####################################################
# @author 	David Kirwan <dkirwan@tssg.org>
# @description 	Docbook XML XSLT Stylesheet for the
#               Asciidoc -> XML + XSLT  -> FO -> PDF
#               Toolchain.
#
# @date 	16-01-2012
####################################################
-->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:xslthl="http://xslthl.sf.net"
                exclude-result-prefixes="xslthl">

<!-- Include basic AsciiDoc FO formatting 
 ####IMPORTANT#### Local Docbook stylesheets are imported in this document also -->
<xsl:import href="../bin/asciidoc-8.6.6/docbook-xsl/fo.xsl"/>

<!-- Header and footer image parameters and their relative paths -->	
<xsl:param name="header.image.filename" select="'conf/whitepaper/template/tssglogo.png'"/>
<xsl:param name="footer.image.filename" select="'conf/whitepaper/template/tssg_bar_full.png'"/>
<xsl:param name="cover.image.filename" select="'conf/whitepaper/template/tssg_logo.png'"/>

<!-- Page Layout parameters 
	see: 	http://sagehill.net/docbookxsl/PrintOutput.html#TopBotMargins 
		http://sagehill.net/docbookxsl/PrintHeaders.html
-->
<xsl:param name="page.margin.top">40pt</xsl:param>
<xsl:param name="region.before.extent">30pt</xsl:param>
<xsl:param name="body.margin.top">50pt</xsl:param> <!-- not sure bout this one yet -->
<xsl:param name="page.margin.bottom">50pt</xsl:param>
<xsl:param name="region.after.extent">45pt</xsl:param>
<xsl:param name="body.margin.bottom">50pt</xsl:param>

<!-- <xsl:param name="generate.toc" select="'nop'"/>  uncomment to disable table of contents -->


<!-- Page Header style -->
<xsl:template name="header.content">  
  <xsl:param name="pageclass" select="''"/>
  <xsl:param name="sequence" select="''"/>
  <xsl:param name="position" select="''"/>
  <xsl:param name="gentext-key" select="''"/>

  <fo:block>
    <!-- sequence can be odd, even, first, blank -->
    <!-- position can be left, center, right -->
    <xsl:choose>

      <xsl:when test="$sequence = 'odd' and $position = 'left'">
	<fo:external-graphic content-height="1cm" content-width="1cm">
    		<xsl:attribute name="src">
      			<xsl:call-template name="fo-external-image">
        			<xsl:with-param name="filename" select="$header.image.filename"/>
      			</xsl:call-template>
    		</xsl:attribute>
  	</fo:external-graphic>
      </xsl:when>

      <xsl:when test="$sequence = 'odd' and $position = 'center'">
        <!-- <xsl:call-template name="draft.text"/> -->
	<xsl:text>http://www.tssg.org</xsl:text>
      </xsl:when>

      <xsl:when test="$sequence = 'odd' and $position = 'right'">
        <fo:page-number/> <!--
	<xsl:text>The Telecommunications Software and Systems Group</xsl:text>-->
      </xsl:when>

      <xsl:when test="$sequence = 'even' and $position = 'left'">  
        <!--<fo:page-number/> -->
      </xsl:when>

      <xsl:when test="$sequence = 'even' and $position = 'center'">
        <!-- <xsl:call-template name="draft.text"/> -->
      </xsl:when>

      <xsl:when test="$sequence = 'even' and $position = 'right'">
        <!--<xsl:apply-templates select="." mode="titleabbrev.markup"/> -->
      </xsl:when>

      <xsl:when test="$sequence = 'first' and $position = 'left'">
      </xsl:when>

      <xsl:when test="$sequence = 'first' and $position = 'right'">  
      </xsl:when>

      <xsl:when test="$sequence = 'first' and $position = 'center'"> 
        <xsl:value-of 
               select="ancestor-or-self::book/bookinfo/corpauthor"/>
      </xsl:when>

      <xsl:when test="$sequence = 'blank' and $position = 'left'">
        <!--<fo:page-number/> -->
      </xsl:when>

      <xsl:when test="$sequence = 'blank' and $position = 'center'">
        <xsl:text>This page intentionally left blank</xsl:text>
      </xsl:when>

      <xsl:when test="$sequence = 'blank' and $position = 'right'">
      </xsl:when>

    </xsl:choose>
  </fo:block>
</xsl:template>


<!-- Page footer style -->
<xsl:template name="footer.content">  
  <xsl:param name="pageclass" select="''"/>
  <xsl:param name="sequence" select="''"/>
  <xsl:param name="position" select="''"/>
  <xsl:param name="gentext-key" select="''"/>
  

  <fo:block>
    <!-- sequence can be odd, even, first, blank -->
    <!-- position can be left, center, right -->
    <xsl:choose>

      <xsl:when test="$sequence = 'odd' and $position = 'left'">

      </xsl:when>

      <xsl:when test="$sequence = 'odd' and $position = 'center'">
	<fo:external-graphic content-height="5cm" content-width="20cm">
    		<xsl:attribute name="src">
      			<xsl:call-template name="fo-external-image">
        			<xsl:with-param name="filename" select="$footer.image.filename"/>
      			</xsl:call-template>
    		</xsl:attribute>
  	</fo:external-graphic>
      </xsl:when> 

      <xsl:when test="$sequence = 'odd' and $position = 'right'">
        <!--<fo:page-number/> -->
      </xsl:when>

      <xsl:when test="$sequence = 'even' and $position = 'left'">  
        <!--<fo:page-number/> -->
      </xsl:when>

      <xsl:when test="$sequence = 'even' and $position = 'center'">
      </xsl:when>

      <xsl:when test="$sequence = 'even' and $position = 'right'">
      </xsl:when>

      <xsl:when test="$sequence = 'first' and $position = 'left'">
      </xsl:when>

      <xsl:when test="$sequence = 'first' and $position = 'right'">  
      </xsl:when>

      <xsl:when test="$sequence = 'first' and $position = 'center'">
      </xsl:when>

      <xsl:when test="$sequence = 'blank' and $position = 'left'">
      </xsl:when>

      <xsl:when test="$sequence = 'blank' and $position = 'center'">
        <xsl:text>This page intentionally left blank</xsl:text>
      </xsl:when>

      <xsl:when test="$sequence = 'blank' and $position = 'right'">
      </xsl:when>

    </xsl:choose>
  </fo:block>
</xsl:template>


<!-- ############################################################################################ 
-->

<!--
Template for adding style to the top foremost section of the titlepage
-->
<xsl:template name="article.titlepage.recto">

<xsl:apply-templates select="../article/coverpage/minititle"/>
 <xsl:apply-templates select="../article/coverpage/mininame"/>

 <fo:block>
  <fo:external-graphic content-height="5cm" content-width="5cm">
   <xsl:attribute name="src">
    <xsl:call-template name="fo-external-image">
      <xsl:with-param name="filename" select="$cover.image.filename"/>
    </xsl:call-template>
   </xsl:attribute>
  </fo:external-graphic>  
 </fo:block>

 <xsl:apply-templates select="../article/coverpage/covertitle"/>
 <xsl:apply-templates select="../article/coverpage/subtitle"/>
 <xsl:apply-templates select="../article/coverpage/group"/>
 <xsl:apply-templates select="../article/coverpage/address"/>
 <xsl:apply-templates select="../article/coverpage/authorlist"/>
 <xsl:apply-templates select="../article/coverpage/emaillist"/>
 <xsl:apply-templates select="../article/coverpage/revdate"/>
<xsl:apply-templates select="../article/coverpage/revnumber"/>
 <xsl:apply-templates select="../article/coverpage/date"/>
 <xsl:apply-templates select="../article/coverpage/legal"/>
 <xsl:apply-templates select="../article/coverpage/synopsis"/>


</xsl:template>


<!--
	Custom template for styling the <coverpage> element.
-->
<xsl:template name="coverpage" match="article/coverpage">

</xsl:template>


<!--
	Custom template for styling the <emaillist> element.
-->
<xsl:template name="emaillist" match="article/coverpage/emaillist">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Email: </xsl:text>
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>



<!--
	Custom template for styling the <revdate> element.
-->
<xsl:template name="revdate" match="article/coverpage/revdate">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Rev Date: </xsl:text>
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <revnumber> element.
-->
<xsl:template name="revnumber" match="article/coverpage/revnumber">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Rev Number: </xsl:text>
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>


<!--
	Custom template for styling the <minititle> element.
-->
<xsl:template name="minititle" match="article/coverpage/minititle">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="0em" space-before.optimum="0.5em" 
space-before.maximum="0.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="end">
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <mininame> element.
-->
<xsl:template name="mininame" match="article/coverpage/mininame">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="0em" space-before.optimum="0.5em" 
space-before.maximum="0.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="end">
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <covertitle> element.
-->
<xsl:template name="covertitle" match="article/coverpage/covertitle">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="3em" space-before.optimum="3.5em" 
space-before.maximum="4.0em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="15pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <subtitle> element.

I've removed the subtitle element from the template. - dkirwan 17th Feb 2012.

<xsl:template name="subtitle" match="article/coverpage/subtitle">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="15pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>
-->

<!--
	Custom template for styling the <group> element.
-->
<xsl:template name="group" match="article/coverpage/group">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="4em" space-before.optimum="4.5em"
space-before.maximum="4.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Research Group: </xsl:text>
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <address> element.
-->
<xsl:template name="address" match="article/coverpage/address">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Address: </xsl:text>
  <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <authorlist> element.
-->
<xsl:template name="authorlist" match="article/coverpage/authorlist">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Authors: </xsl:text>
   <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <date> element.
-->
<xsl:template name="date" match="article/coverpage/date">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Date: </xsl:text>
   <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <legal> element.
-->
<xsl:template name="legal" match="article/coverpage/legal">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Legal: </xsl:text>
   <xsl:value-of select="."/>
 </fo:block>
</xsl:template>

<!--
	Custom template for styling the <synopsis> element.
-->
<xsl:template name="synopsis" match="article/coverpage/synopsis">
 <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1.5em" 
space-before.maximum="1.5em" space-after="0.5em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="start">
  <xsl:text>Description: </xsl:text>
   <fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xsl:use-attribute-sets="list.of.tables.titlepage.recto.style" 
space-before.minimum="1em" space-before.optimum="1em" 
space-before.maximum="2em" space-after="25em" 
margin-left="{$title.margin.left}" start-indent="0pt" 
font-size="10pt" font-weight="normal" 
font-family="{$title.fontset}" text-align="justify">
   <xsl:value-of select="."/>
   <fo:leader leader-pattern="space" />
   <xsl:text></xsl:text>
  </fo:block>
 </fo:block>
</xsl:template>

<xsl:template match="processing-instruction('hard-pagebreak')">
   <fo:block break-after='page'/>
 </xsl:template>

</xsl:stylesheet>

