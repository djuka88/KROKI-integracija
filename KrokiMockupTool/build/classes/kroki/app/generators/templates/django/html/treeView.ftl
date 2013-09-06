<#macro tree>
	<div class="css-treeview">
		<ul>
			<li><input type="checkbox" id="" checked="checked" /><label for="">${project.label}</label>
			<ul>
				<#list menus as node>
					<@treeGenerator node=node/>
				</#list>
			</ul>
			</li>
		</ul>
	</div>
</#macro>

<#-- Makro, za generisanje rekurzivne liste -->
<#macro treeGenerator node>
	<#if !node.name?ends_with("_st") && !node.name?ends_with("_pc") && node.submenus?size &gt; 1>
		<li><input type="checkbox" id="" checked="checked" /><label for="">${node.label}</label>
		<ul>
			<#if node.submenus?size &gt; 0>
				<#list node.submenus as submenu>
					<li><a href="./">${submenu.label}</a></li>
				</#list>
			</#if>
			
			<#if node.menus?size &gt; 0>
				<#list node.menus as menu>
					<@treeGenerator node=menu/>
				</#list>
			</#if>
		</ul>
	<#else>
		<li><a href="./">${node.label}</a></li>
	</#if>
</#macro>

