<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <HTML>
    <BODY>
      <xsl:apply-templates select="//Buch" />
    </BODY>
  </HTML>
</xsl:template>

<xsl:template match="Buch">
  <SPAN STYLE="display:block; margin:15px">
    <xsl:apply-templates select="Bestellnummer" />
    <xsl:apply-templates select="Gebiet" />
    <xsl:apply-templates select="Erschienen" />
    <xsl:apply-templates select="Titel" />
  </SPAN>
</xsl:template>

<xsl:template match="Bestellnummer">
  <SPAN STYLE="display:inline; 
               font-family:Tahoma,Arial,sans-serif; 
               font-size:10pt; 
               font-weight:bold">
    <xsl:value-of select="."/>
  </SPAN>
</xsl:template>

<xsl:template match="Gebiet">
  <SPAN STYLE="display:inline; 
               font-family:Tahoma,Arial,sans-serif; 
               color:darkgray; 
               font-size:12pt; 
               font-weight:bold">
    <xsl:value-of select="."/>
  </SPAN>
</xsl:template>

<xsl:template match="Erschienen">
  <SPAN STYLE="display:inline; 
               font-family:Tahoma,Arial,sans-serif; 
               color:red; 
               font-size:10pt">
    <xsl:value-of select="."/>
  </SPAN>
</xsl:template>

<xsl:template match="Titel">
  <SPAN STYLE="display:inline; 
               font-family:Tahoma,Arial,sans-serif; 
               font-size:12pt; 
               color:white; 
               background-color:black">
    <xsl:value-of select="."/>
  </SPAN>
</xsl:template>

</xsl:stylesheet>