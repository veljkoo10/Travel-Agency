$(document).ready(function() {
    var prevoznoSredstvoCelija = $("select[name=prevoznoSredstvo]")
    var nazivDestinacijeInput = $("input[name=nazivDestinacije]")
    var datumVremePolaskaInput = $("input[name=datumVremePolaska]")
    var datumVremePovratkaInput = $("input[name=datumVremePovratka]")
    var ukupanBrojMestaInput = $("input[name=ukupanBrojMesta]")

    function dodaj() {
        var prevoznoSredstvo = prevoznoSredstvoInput.find("option:selected").val()
        var nazivDestinacije = nazivDestinacijeInput.val()
        var datumVremePolaska = datumVremePolaskaInput.val()
        var datumVremePovratka = datumVremePovratkaInput.val()
        var ukupanBrojMesta = ukupanBrojMestaInput.val()

        var params = {
            prevoznoSredstvo : prevoznoSredstvo,
            nazivDestinacije : nazivDestinacije,
            datumVremePolaska : datumVremePolaska,
            datumVremePovratka : datumVremePovratka,
            ukupanBrojMesta : ukupanBrojMesta
        }
        console.log(params)
        $.post("putovanja/zahtev/add",
            params,
            function(){
                window.location.href = 'putovanja'
            }
        )

        return false
    }

    $("form").submit(dodaj)
})