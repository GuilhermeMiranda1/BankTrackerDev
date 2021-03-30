const hamburger = document.getElementById('hamburger');
const navUL = document.getElementById('nav-ul');

hamburger.addEventListener('click', () => {
    if(navUL.classList.contains('show')) {
        navUL.style.animationName = 'close';
        setTimeout(() => {
            navUL.classList.toggle('show');
        }, 600);
    } else {
        navUL.style.animationName = 'open';
        navUL.classList.toggle('show');
    }
});