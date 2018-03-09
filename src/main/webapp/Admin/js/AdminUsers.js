function viewDetails(id) {
    $.get(
        "/AdminUserDetailsServlet",
        {userId: id},
        function (user) {
            $("#userTable").fadeOut("slow");
            $("#userId").val(user.userId);
            $("#userName").val(user.userName);
            $("#userEmail").val(user.email);
            $("#userBDate").val(user.birthdate);
            $("#userJob").val(user.job);
            $("#userAddress").val(user.address);
            $("#userCredit").val(user.creditLimit);
            $("#viewForm").show("slow");
        });
}

function closeForm() {
    $("#viewForm").fadeOut("slow");
    $("#userTable").show("slow");
}