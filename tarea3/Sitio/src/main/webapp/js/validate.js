/**
 * Valida que el password y el confirm sean iguales
 */
 function validarPass() {
  const password = document.querySelector('input[name=contraseña]');
  const confirm = document.querySelector('input[name=confcontraseña]');
  if (confirm.value === password.value) {
    confirm.setCustomValidity('');
  } else {
    confirm.setCustomValidity('Passwords do not match');
  }
}