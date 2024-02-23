$(document).ready(function() {

    var vazenjePopustaInput = $("input[name=vazenjePopusta]")
    var popustInput = $("input[name=procenatPopusta]")
    var idInput = $("input[name=id]")

    function azuriraj() {

        var vazenjePopusta = vazenjePopustaInput.val()
        var popust = popustInput.val()
        var id = idInput.val()

        var params = {
            datumVazenjaPopusta : vazenjePopusta,
            popust : popust,
            id : id
        }
        console.log(params)
        $.post("putovanja/akcije/update",
            params,
            function(){
                window.location.href = 'putovanja'
            }
        )
        console.log("POST: " + "putovanja/add")

        return false
    }

    $("form").submit(azuriraj)
})