function validar(form) {
    if (form.nameMovie.options[form.nameMovie.selectedIndex].value == "") {
        alert("Por favor, seleccione una opción válida");
        form.combo1.focus(); return true;

    }
    form.submit();
}