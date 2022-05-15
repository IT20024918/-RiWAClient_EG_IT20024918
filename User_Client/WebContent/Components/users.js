$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "UserAPI",
type : type,
data : $("#formItem").serialize(),
dataType : "text",
complete : function(response, status)
{
onItemSaveComplete(response.responseText, status);
}
});
});







// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)



{
$("#hidItemIDSave").val($(this).data("itemid"));
$("#accountNo").val($(this).closest("tr").find('td:eq(1)').text());
$("#Name").val($(this).closest("tr").find('td:eq(2)').text());
$("#Address").val($(this).closest("tr").find('td:eq(3)').text());
$("#NIC").val($(this).closest("tr").find('td:eq(4)').text());
$("#Email").val($(this).closest("tr").find('td:eq(5)').text());
$("#Phone").val($(this).closest("tr").find('td:eq(6)').text());
$("#Username").val($(this).closest("tr").find('td:eq(7)').text());
$("#Password").val($(this).closest("tr").find('td:eq(8)').text());
});







// DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "UserAPI",
type : "DELETE",
data : "userId=" + $(this).data("itemid"),
dataType : "text",
complete : function(response, status)
{
onItemDeleteComplete(response.responseText, status);
}
});
});







// CLIENT-MODEL================================================================







function onItemSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#hidItemIDSave").val("");
$("#formItem")[0].reset();
}







function onItemDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}
function validateItemForm()
{
// CODE
if ($("#accountNo").val().trim() == "")
{
return "Insert Account No";
}

// NAME
if ($("#Name").val().trim() == "")
{
return "Insert Name";
}

//Address
if ($("#Address").val().trim() == "")
{
return "Insert Address";
}

//NIC
if ($("#NIC").val().trim() == "")
{
return "Insert NIC";
}

//EMAIL
if ($("#Email").val().trim() == "")
{
return "Insert Email";
}

//PHONE
if ($("#PHONE").val().trim() == "")
{
return "Insert Phone";
}

//USERNAME
if ($("#Username").val().trim() == "")
{
return "Insert Username";
}

//NAME
if ($("#Password").val().trim() == "")
{
return "Insert Password";
}






return true;
}

