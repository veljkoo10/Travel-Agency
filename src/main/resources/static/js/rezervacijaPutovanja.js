$(document).ready(function() {
    var brojPutnikaInput = $("input[name=brojPutnika]")
    var idPutovanjaInput = $("input[name=idPutovanja]")

    function dodaj() {
        var brojPutnika = brojPutnikaInput.val()
        var idPutovanja = idPutovanjaInput.val()

        var params = {
            idPutovanja : idPutovanja,
            brojPutnika : brojPutnika
        }
        $.post("rezervacija/add",
            params,
            function(){
                window.location.href = 'putovanja'
            }
        )


        return false
    }

    $("form").submit(dodaj)
})