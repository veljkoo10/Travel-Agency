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
        $.ajax({
            type: "POST",
            url: "putovanja/zahtev/revidiranje",
            contentType: "application/json",
            data: JSON.stringify(params),
            success: function() {
                window.location.href = 'putovanja';
            }
        });



        return false
    }

    $("form").submit(potvrdi)
})