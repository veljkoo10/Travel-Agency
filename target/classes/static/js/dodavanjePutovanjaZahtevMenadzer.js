$(document).ready(function() {

    var prevoznoSredstvoCelija = $("select[name=prevoznoSredstvo]")
    var smestajnaJedinicaCelija = $("select[name=smestajnaJedinica]")
    var nazivDestinacijeInput = $("input[name=nazivDestinacije]")
    var datumVremePolaskaInput = $("input[name=datumVremePolaska]")
    var datumVremePovratkaInput = $("input[name=datumVremePovratka]")
    var brojNocenjaInput = $("input[name=brojNocenja]")
    var cenaAranzmanaInput = $("input[name=cenaAranzmana]")
    var ukupanBrojMestaInput = $("input[name=ukupanBrojMesta]")
    var idKorisnikaInput = $("input[name=idKorisnika]")


    function dodaj() {

        var prevoznoSredstvo = prevoznoSredstvoCelija.find("option:selected").val()
        var smestajnaJedinica = smestajnaJedinicaCelija.find("option:selected").val()
        var nazivDestinacije = nazivDestinacijeInput.val()
        var datumVremePolaska = datumVremePolaskaInput.val()
        var datumVremePovratka = datumVremePovratkaInput.val()
        var brojNocenja = brojNocenjaInput.val()
        var cenaAranzmana = cenaAranzmanaInput.val()
        var ukupanBrojMesta = ukupanBrojMestaInput.val()
        var idPutovanja = ipPutovanjaInput.val()
        var idKorisnika = idKorisnikaInput.val()


        var params = {
            prevoznoSredstvo : prevoznoSredstvo,
            smestajnaJedinica : smestajnaJedinica,
            nazivDestinacije : nazivDestinacije,
            datumVremePolaska : datumVremePolaska,
            datumVremePovratka : datumVremePovratka,
            brojNocenja : brojNocenja,
            cenaAranzmana : cenaAranzmana,
            ukupanBrojMesta : ukupanBrojMesta,
            idPutovanja : idPutovanja,
            idKorisnika : idKorisnika
        }
        console.log(params)
        $.post("putovanja/zahtev/menadzer/add",
            params,
            function(){
                window.location.href = 'putovanja'
            }
        )
        console.log("POST: " + "putovanja/add")

        return false
    }

    $("form").submit(dodaj)
})