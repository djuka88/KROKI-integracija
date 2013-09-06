from django.contrib import admin
<@compress single_line=true>
<#assign formss="">
<#list st_forms as form>
	<#if form.attributes?has_content>
		<#assign formss=formss+form.name+",">
	</#if>
</#list>

<#if formss!="">
from st_forms.models import ${formss?substring(0,formss?length-1)}
</#if>

</@compress>


<#list st_forms as form>
<#if form.attributes?has_content>
admin.site.register(${form.name})
</#if>
</#list>