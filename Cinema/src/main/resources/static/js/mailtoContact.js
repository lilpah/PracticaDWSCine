const $form = document.querySelector('#form')
const $buttonMailto = document.querySelector('#mail_pe')
$form.addEventListener('submit', handleSubmit)

function handleSubmit(event){
    event.preventDefault()
    const form = new FormData(this)
    $buttonMailto.setAttribute('href', `mailto:laCarteleraCine2.0@gmail.com?subject=${form.get('email')}&body=${form.get('message')}`)
    $buttonMailto.click()
}