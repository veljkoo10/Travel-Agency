$(document).ready(function() {
    var prevoznoSredstvoCelija = $("select[name=prevoznoSredstvo]")
    var smestajnaJedinicaCelija = $("select[name=smestajnaJedinica]")
    var nazivDestinacijeInput = $("input[name=nazivDestinacije]")
    var nazivKategorijeInput = $("input[name=nazivKategorije]")
    var slikaLokacijeInput = $("input[name=slikaLokacije]")
    var datumVremePolaskaInput = $("input[name=datumVremePolaska]")
    var datumVremePovratkaInput = $("input[name=datumVremePovratka]")
    var brojNocenjaInput = $("input[name=brojNocenja]")
    var cenaAranzmanaInput = $("input[name=cenaAranzmana]")
    var ukupanBrojMestaInput = $("input[name=ukupanBrojMesta]")
    var brojSlobodnihMestaInput = $("input[name=brojSlobodnihMesta]")

    function dodaj() {

        var prevoznoSredstvo = prevoznoSredstvoCelija.find("option:selected").val()
        var smestajnaJedinica = smestajnaJedinicaCelija.find("option:selected").val()
        var nazivDestinacije = nazivDestinacijeInput.val()
        var nazivKategorije = nazivKategorijeInput.val()
        var slikaLokacije = slikaLokacijeInput.val()
        var datumVremePolaska = datumVremePolaskaInput.val()
        var datumVremePovratka = datumVremePovratkaInput.val()
        var brojNocenja = brojNocenjaInput.val()
        var cenaAranzmana = cenaAranzmanaInput.val()
        var ukupanBrojMesta = ukupanBrojMestaInput.val()
        var brojSlobodnihMesta = brojSlobodnihMestaInput.val()

        var params = {
            prevoznoSredstvo : prevoznoSredstvo,
            smestajnaJedinica : smestajnaJedinica,
            nazivDestinacije : nazivDestinacije,
            nazivKategorije : nazivKategorije,
            slikaLokacije : slikaLokacije,
            datumVremePolaska : datumVremePolaska,
            datumVremePovratka : datumVremePovratka,
            brojNocenja : brojNocenja,
            cenaAranzmana : cenaAranzmana,
            ukupanBrojMesta : ukupanBrojMesta,
            brojSlobodnihMesta : brojSlobodnihMesta
        }

        var data = new FormData(jQuery('form')[0]);
        $.ajax({
            type: "POST",
            url: "putovanja/add",
            contentType: false,
            data: data,
            success: function() {
                window.location.href = 'putovanja';
            }
        });
        console.log("POST: " + "putovanja/add")

        return false
    }

    $("form").submit(dodaj)
})