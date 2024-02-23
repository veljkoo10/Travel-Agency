$(document).ready(function() {
    var tabela = $("table tbody");
    function azurirajPrikaz(filtiraneRezervacije) {
        tabela.empty();

        filtiraneRezervacije.forEach(function(rezervacija) {
            var red = $("<tr>");
            red.append($("<td>").text(rezervacija.nazivDestinacije));
            red.append($("<td>").text(rezervacija.datumPolaska));
            red.append($("<td>").text(rezervacija.datumPovratka));
            red.append($("<td>").text(rezervacija.brojMesta));
            red.append($("<td>").text(rezervacija.cenaAranzmana));
            tabela.append(red);
        });
    }

    function pretrazi() {
        var datumRezervacije = $("input[name=datumFilter]").find("option:selected").val();
        var currentDate = new Date().toISOString().split('T')[0];

        var filtriraneRezervacije = rezervacije.filter(function(rezervacija) {
            return (
                (datumRezervacije === "") || (datumRezervacije === "proslost" && rezervacija.datumPolaska < currentDate)
                || (datumRezervacije === "buducnost" && rezervacija.datumPolaska > currentDate)
            );
        });
        azurirajPrikaz(filtriraneRezervacije);

        return false;
    }

    var imeInput = $("input[name=ime]")
    var prezimeInput = $("input[name=prezime]")
    var emailInput = $("input[name=email]")
    var datumRodjenjaInput = $("input[name=datumRodjenja]")
    var jmbgInput = $("input[name=jmbg]")
    var adresaInput = $("input[name=adresa]")
    var brojTelefonaInput = $("input[name=brTelefona]")

    function izmeni() {

        var ime = imeInput.val()
        var prezime = prezimeInput.val()
        var email = emailInput.val()
        var datumRodjenja = datumRodjenjaInput.val()
        var jmbg = jmbgInput.val()
        var adresa = adresaInput.val()
        var brojTelefona = brojTelefonaInput.val()

        var params = {
            ime : ime,
            prezime : prezime,
            email : email,
            datumRodjenja : datumRodjenja,
            jmbg : jmbg,
            adresa : adresa,
            brojTelefona : brojTelefona
        }

        $.post("korisnici/edit",
            params,
            function(){
                window.location.href = 'putovanja'
            }
        )
        return false
    }

    $("form").submit(izmeni)
    $("filterForm").submit(pretrazi)
})