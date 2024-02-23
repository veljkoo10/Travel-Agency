$(document).ready(function() {
    var tabela = $("table tbody");
    function azurirajPrikaz(filtriranaPutovanja) {
        tabela.empty();

        filtriranaPutovanja.forEach(function(putovanje) {
            var red = $("<tr>");
            red.append($("<td>").text(putovanje.prevoznoSredstvo));
            red.append($("<td>").text(putovanje.nazivKategorije));
            red.append($("<td>").text(putovanje.smestajnaJedinica));
            red.append($("<td>").text(putovanje.nazivDestinacije));
            red.append($("<td>").html('<img src="' + putovanje.slikaLokacije + '" alt="Slika lokacije" class="img" width="200" height="150">'));
            red.append($("<td>").text(putovanje.datumVremePolaska));
            red.append($("<td>").text(putovanje.datumVremePovratka));
            red.append($("<td>").text(putovanje.brojNocenja));
            red.append($("<td>").text(putovanje.cenaAranzmana));
            red.append($("<td>").text(putovanje.ukupanBrojMesta));
            red.append($("<td>").text(putovanje.brojSlobodnihMesta));
            red.append($("<td>").text(putovanje.procenatPopusta != null ? putovanje.cenaAranzmana * (1 - (putovanje.procenatPopusta / 100)) : "Nema aktivnih popusta"));
            red.append($("<td>").text(putovanje.datumVazenjaPopusta != null ? putovanje.datumVazenjaPopusta : "Nema aktivnih popusta"));


            tabela.append(red);
        });
    }

    function pretrazi() {
        var prevoznoSredstvo = $("select[name=prevoznoSredstvo]").find("option:selected").val();
        var kategorija = $("select[name=kategorija]").find("option:selected").val();
        var nazivDestinacije = $("input[name=nazivDestinacije]").val();
        var brojNocenja = $("input[name=brojNocenja]").val();
        var minimalnaCena = $("input[name=minimalnaCenaAranzmana]").val();
        var maksimalnaCena = $("input[name=maksimalnaCenaAranzmana]").val();
        var sortiranje = $("#sort").val();
        var filtriranaPutovanja = putovanja.filter(function(putovanje) {
            return (
                (prevoznoSredstvo === "" || putovanje.prevoznoSredstvo === prevoznoSredstvo) &&
                (nazivDestinacije === "" || putovanje.nazivDestinacije.includes(nazivDestinacije)) &&
                (brojNocenja === "" || putovanje.brojNocenja == brojNocenja) &&
                (minimalnaCena === "" || putovanje.cenaAranzmana >= minimalnaCena) &&
                (maksimalnaCena === "" || putovanje.cenaAranzmana <= maksimalnaCena) &&
                (kategorija === "" || putovanje.nazivKategorije === kategorija)
            );
        });

        if (sortiranje === "nazivDestinacije") {
            filtriranaPutovanja.sort(function(a, b) {
                return a.nazivDestinacije.localeCompare(b.nazivDestinacije);
            });
        } else if (sortiranje === "cenaAranzmana") {
            filtriranaPutovanja.sort(function(a, b) {
                return a.cenaAranzmana - b.cenaAranzmana;
            });
        } else if (sortiranje === "brojNocenja") {
            filtriranaPutovanja.sort(function(a, b) {
                return a.brojNocenja - b.brojNocenja;
            });
        }

        azurirajPrikaz(filtriranaPutovanja);

        return false;
    }

    $("form").submit(pretrazi);
});