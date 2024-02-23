$(document).ready(function() {
    var tabela = $("table tbody");

    function azurirajPrikaz(filtriraneRezervacije) {
        tabela.empty();

        filtriraneRezervacije.forEach(function(rezervacija) {
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

        var sortiranje = $("#datumFilter").val();
        var now = new Date();

        var filtriraneRezervacije = rezervacije.filter(function(rezervacija) {
            return (
                (sortiranje === "") || (sortiranje === "proslost" && new Date(rezervacija.datumPolaska) < now) ||
                (sortiranje === "buducnost" && new Date(rezervacija.datumPolaska) > now)
            );
        });

        azurirajPrikaz(filtriraneRezervacije);

        return false;
    }

    $("#search").click(pretrazi);

});
