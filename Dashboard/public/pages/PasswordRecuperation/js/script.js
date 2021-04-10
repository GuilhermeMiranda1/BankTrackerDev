const brand = document.querySelector('.form_side .container .brand');
const forgot_password_txt = document.querySelector('#forgot_password');
const btSend = document.querySelector('.btSend');
const loading = document.querySelector('.loading_screen');
const loading_msg = document.querySelector('#loading_message');

function save(){
    loading_screen();
    setTimeout(() => {
      update_password();
    }, 5000);   
}

function loading_screen(){
    hide_components();
    loading_msg.innerHTML = "<span>A</span>guarde um momento";
    setTimeout(() => {
        loading.style = "display: flex";
    }, 990);
}

function hide_components(){
    brand.style = "animation: fade_out 800ms"
    message.style = "animation: fade_out 800ms";
    password.style = "animation: move_right_out 1000ms";
    confirm_password.style = "animation: move_right_out 1000ms 200ms";
    btSave.style = "animation: fade_out 800ms 300ms";

    setTimeout(() => {
        brand.style = "display: none";
        message.style = "display: none";
        password.style = "display: none";
        confirm_password.style = "display: none";
        btSave.style = "display: none";   
    }, 700);
}

function update_password(){
    if(true){
        //alert("redirect");
        setTimeout(() => {
           success();
        }, 2000);
    }else{
        setTimeout(() => {
            update_failed();
         }, 2000);
       
    }
}

function success(){
    loading_msg.style = "animation: fade_out 800ms";
    loading_gif.style = "animation: fade_out 800ms";
    
    setTimeout(() => {
        loading_msg.style = "animation: fade_in 800ms";
        loading_msg.innerHTML = "Senha atualizada com sucesso";
        loading_gif.style = "display: none";
    }, 790);

    setTimeout(() => {
        window.location.href = "file:///C:/Users/aless/BandTec/Módulo%20II/PI/2ADSB-2021-1-Grupo-01/Dashboard/public/index.html";
    }, 5000);
}

function update_failed(){
    loading_msg.style = "animation: fade_out 800ms";
    loading_gif.style = "animation: fade_out 800ms";
    
    setTimeout(() => {
        loading_msg.style = "animation: fade_in 800ms";
        loading_msg.innerHTML = "Erro ao atualizar senha";
        loading_gif.style = "display: none";
    }, 790);

    setTimeout(() => {
        window.location.reload();
    }, 5000);
}