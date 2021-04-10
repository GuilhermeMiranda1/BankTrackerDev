document.querySelector("#bt_icon").onclick = reloadPage;
document.querySelector("#extend_icon").onclick = extendHeader;

let open_menu = false;

function reloadPage() { 
    location.reload();
}

function extendHeader() {
    let sectionHeader = document.querySelector("#header");
    let spanHeader = document.querySelectorAll(".subtitle");
    let divIconsHeader = document.querySelectorAll(".icon");
    let extendIcon = document.querySelector("#icon_extend");

    sectionHeader.classList.toggle('extend');
    
    spanHeader.forEach(title => {
        title.classList.toggle('hide');
    });
    
    divIconsHeader.forEach(div_icon => {
        div_icon.classList.toggle('extendIcons');
    });

    if(!open_menu) {
        extendIcon.src = "../../img/icons/extend-enabled.png";
        open_menu = true;
    } else if(open_menu) {
        extendIcon.src = "../../img/icons/extend-disabled.png";
        open_menu = false;
    }
}