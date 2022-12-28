function przeslij() {
    let tekst1 = document.getElementById("haslo1").value;
    let tekst2 = document.getElementById("haslo2").value;
    if (tekst1 != tekst2) {
    alert("Hasła Admina nie są takie same");
    } else {
        alert("Dane Admina zostały zmienione");
        sumbit1();
    }
}

    function sumbit1(){
    document.forms["formularz"].submit();
}
function przeslij1() {
    let tekst1 = document.getElementById("haslo3").value;
    let tekst2 = document.getElementById("haslo4").value;
    if (tekst1 != tekst2) {
        alert("Hasła Pracownika nie są takie same");
    } else{
        alert("Dane Pracownika zostały zmienione");
        submit();
    }
}
    function submit(){
    document.forms["formularz1"].submit();
}