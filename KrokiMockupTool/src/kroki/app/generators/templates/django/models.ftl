from django.db import models

# Create your models here.

<#compress>

<#assign listOfFieldArgs="">
<#assign defVal="">
<#assign comboMap="{">
<#assign keysComboMap=""><#-- kljucevi su imena atributa -->
<#assign valuesComboMap="">

</#compress>
<#list st_forms as form>

class ${form.name}(models.Model):
<#compress>


</#compress>
    <#if form.attributes?has_content>
    	<#list form.attributes as attr><#-- prva lista kreira enumeracije -->
    			<#if attr.enumeration??>
					<#if attr.enumeration.values??>

    ${attr.name?upper_case}_CHOICES = (
    				<#list attr.enumeration.values as enum>
        ('${enum_index+1}','${enum}'),
        			<#if attr.defaultValue??>
        				<#if attr.defaultValue == enum>
        					<#assign defVal = enum_index + 1>
        					<#assign comboMap = comboMap + "\"" + attr.name + "\":\"" + defVal + "\",">
        				</#if>
        			</#if>
        			</#list>
    )
    				</#if>
    			</#if>
    	</#list>

    	<#t><#-- Sredjivanje comboMap promenljive (trim zareza) i kreiranje hes mape-->
        <#assign comboMap = comboMap?substring(0,comboMap?length-1)+"}">
        <#if comboMap?length &gt; 1>
	        <#assign comboMap=comboMap?eval><#-- ovim redom se kreira hes mapa -->
	        <#assign keysComboMap=comboMap?keys>
	        <#assign valuesComboMap=comboMap?values>
        </#if>
		<#list form.attributes as attr><#-- druga lista kreira propertije propertija -->
			<#compress>
		    	<#if attr.enumeration?? && attr.enumeration.values??>
		    		<#assign listOfFieldArgs = listOfFieldArgs + "choices = " + attr.name?upper_case + "_CHOICES, ">
		    	</#if>

		    	<#if !attr.mandatory>
		    		<#assign listOfFieldArgs = listOfFieldArgs + "null = True, blank = True, ">
		    	</#if>

		    	<#if attr.defaultValue??>
		    		<#if attr.enumeration??><#-- Ako jeste combo box -->
		    			<#list keysComboMap as k>
		    				<#if k==attr.name>
		    					<#assign listOfFieldArgs = listOfFieldArgs + "default = '" + valuesComboMap[k_index] + "', ">
		    					<#break>
		    				</#if>
		    			</#list>
		    		<#else>
		    			<#assign listOfFieldArgs = listOfFieldArgs + "default = '" + attr.defaultValue + "', ">
		    		</#if>
		    	</#if>
		    	
		    	<#if attr.type == "CharField"><#-- moram dodati max_length zbog djanga... Posto nigde nemam podatak o duzini stringa zakucacu ga na 1024 -->
		    		<#assign listOfFieldArgs = listOfFieldArgs + "max_length = 1024, ">
		    	</#if>
		    	
		    </#compress>	    
    ${attr.name} = models.${attr.type}('${attr.label}'<#if listOfFieldArgs != "">,${listOfFieldArgs?substring(0,listOfFieldArgs?length-2)}</#if>)<#-- Substringovi zbog trimovanja zareza na kraju -->
    <#assign listOfFieldArgs="">
    <#assign comboMap="{">
    <#assign keys="">
	<#assign values="">

		</#list>
	</#if>
<#compress>


</#compress>
</#list>