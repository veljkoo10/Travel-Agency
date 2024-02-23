$(document).ready(function() {
    var komentarInput = $("input[name=komentar]")
    var idInput = $("input[name=idPutovanja]")

    function potvrdi() {
        var komentar = komentarInput.val()
        var id = idInput.val()

        var params = {
            komentar : komentar,
            id : id
        }
        console.log(params)
        $.ajax({
            type: "POST",
            url: "putovanja/zahtev/odbij",
            contentType: "application/json",
            data: JSON.stringify(params),
            success: function() {
                window.location.href = 'putovanja';
            }
        });
        console.log("POST: " + "putovanja/add")

        return false
    }

    $("form").submit(potvrdi)
})