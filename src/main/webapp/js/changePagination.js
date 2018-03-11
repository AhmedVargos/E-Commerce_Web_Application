/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function updatePagination(id)
{  
  var i;
                if (id !== undefined ) {
                    for (i = 1; i <= 3; i++) {
                        if ($("#pagePag"+i).hasClass("active"))
                            $("#pagePag"+i).removeClass("active");
                    }
                      $("#pagePag"+id).addClass("active");
                } else {
                    $("#pagePag"+id).addClass("active");
                }  
    
}
function updateCatd(catId)
{
 var i;
                if (catId !== undefined ) {
                    for (i = 1; i <= 3; i++) {
                        if ($("#pagecat"+i).hasClass("active"))
                            $("#pagecat"+i).removeClass("active");
                    }
                      $("#pagecat"+catId).addClass("active");
                } else {
                    $("#pagecat"+catId).addClass("active");
                }        
    
    
}
