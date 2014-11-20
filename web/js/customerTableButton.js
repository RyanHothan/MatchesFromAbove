/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function doSomething()
{
    alert("YOOO");  
   $.ajax({
       url : 'http://localhost:8080/MatchesFromAbove/getAllCustomers',
       type : 'GET', 
       dataType : 'JSON',
       success : function(data){
        var something = data[0].ssn;
        alert(something);
        }
    })
    };
   
