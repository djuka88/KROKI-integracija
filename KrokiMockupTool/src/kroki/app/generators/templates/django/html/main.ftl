{% load staticfiles %}

<#import "./treeView.ftl" as treeView>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>Standard form</title>
        <link type="text/css" href="{% static 'st_forms/main.css' %}" rel="stylesheet" />
        <link type="text/css" href="{% static 'st_forms/treeView.css' %}" rel="stylesheet" />
        <script type="text/javascript">
        
        var isRowSelected=false;
        var tableRowBackColor;
        var selectedRow;
        
            
            function ChangeColor(tableRow){
            //ako nema selektovanog reda u tabeli, selektuj ga
                if (!isRowSelected){
                    tableRowBackColor=tableRow.style.backgroundColor;
                    tableRow.style.backgroundColor = '#FA5858';
                    isRowSelected=true;
                    selectedRow=tableRow;
                }
                //ako ima selektovanog reda
                else{
                    //i ako je kliknuto na selektovani red, deselektuj ga
                    if(selectedRow.rowIndex==tableRow.rowIndex){
                        tableRow.style.backgroundColor = tableRowBackColor;
                        isRowSelected=false;
                    }
                    //ako je kliknuto na drugi red, deselektuj selektovani, i selektuj drugi
                    else{
                        selectedRow.style.backgroundColor=tableRowBackColor;
                        
                        tableRowBackColor=tableRow.style.backgroundColor;
                        tableRow.style.backgroundColor = '#FA5858';
                        isRowSelected=true;
                        selectedRow=tableRow;
                    }
                }
            }
            
            // navigacija na sledeci element u tabeli
            function goNext(){
                if(isRowSelected){
                    var table = document.getElementById("tableData");
                    var row=table.rows[selectedRow.rowIndex+1];
                    
                    ChangeColor(row);
                }
                else{
                    //ako nema selektovanih redova, selektuj prvi
                    var table = document.getElementById("tableData");
                    var row=table.rows[1];
                    
                    ChangeColor(row);
                }
            }
            
            //navigacija na prethodni element u tabeli
            function  goPrev(){
                if(isRowSelected){
                    var table = document.getElementById("tableData");
                    var row=table.rows[selectedRow.rowIndex-1];
                    
                    if(row.rowIndex>0)
                        ChangeColor(row);
                }
                else{
                    //ako nema selektovanih redova, selektuj poslednji
                    var table = document.getElementById("tableData");
                    var row=table.rows[table.rows.length-1];
                    
                    if(row.rowIndex>0)
                        ChangeColor(row);
                }
            }
            
            //navigacija na prvi element u tabeli
            function goFirst(){
                var table = document.getElementById("tableData");
                var row=table.rows[1];
                
                ChangeColor(row);
            }
            
            //navigacija na poslednji element u tabeli
            function goLast(){
                // table = document.getElementById("tableData");
                //var row=table.rows[table.rows.length-1];
                
                //ChangeColor(row);
            //}
            
            var table = document.getElementById("tableData");
            var row=table.insertRow(1);
            var cell1=row.insertCell(0);
            var cell2=row.insertCell(1);
            var cell3=row.insertCell(2);
            cell1.innerHTML="JE";
            cell2.innerHTML="BE";
            cell3.innerHTML="KEVU";
            }
  </script>
    </head>
    
    <body>
    
    	<div class="header">
    	</div>
    
	    <div class="treeView">
	    	<@treeView.tree/>
	    </div>
	    
	    <div class="operations">
	    </div>
	    
	    <div class="toolbar" align="center">
	    <div style="margin-top: 6px">
	    	<button type="button" onclick="goFirst()"><img src="{% static 'st_forms/first.png' %}"/></button>
            <button type="button" onclick="goPrev()"><img src="{% static 'st_forms/prev.png' %}"/></button>
            <button type="button" onclick="goNext()"><img src="{% static 'st_forms/next.png' %}"/></button>
            <button type="button" onclick="goLast()"><img src="{% static 'st_forms/last.png' %}"/></button>
        </div>
	    </div>
	    
	    <div class="mainPanel">
	    </div>
	    
    </body>
</html>