console.log("script loaded")

// change theme start    ------>

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', ()=>{
    changeTheme()
})
// initial

function changeTheme(){
    changePageTheme(currentTheme,"")
    // set to web page
    document.querySelector('html').classList.add(currentTheme);

    // set listener to change theme button
    const changeThemeBtn = document.querySelector('#theme_change');
    
    
    changeThemeBtn.addEventListener("click",(event)=>{
        let oldTheme = currentTheme;
        
        if(currentTheme === "dark"){
            currentTheme = "light";
        }
        else{
            currentTheme = "dark";
        }

       changePageTheme(currentTheme,oldTheme)
    })
}

// set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}


// get theme from local storage
function getTheme(){
    let theme = localStorage.getItem("theme")

    // if(theme) return theme;
    // else return  "light";
    // or
    return theme ? theme : "light";
}

// change current page theme 
function changePageTheme(theme,oldTheme){
     // update in local storage
     setTheme(currentTheme);
     // remove current theme
     if(oldTheme){
        document.querySelector('html').classList.remove(oldTheme);
     }
     //set current theme
     document.querySelector('html').classList.add(theme);

     // change text of button
     document.querySelector("#theme_change").querySelector("span").textContent = theme == "light" ? "Light" : "Dark";
}


// change theme end    ------>
