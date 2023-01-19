let count = 1
const bt = document.getElementById('bt')
const img = document.getElementById('img')
const txt = document.getElementById('txt')
const help = document.getElementById('popa')
console.log(help)
bt.addEventListener('click', ()=>{
    if(count==-1){
        img.style.transform = 'rotate(0deg)'
        bt.value = 'Уронить'
        count*=-1
        txt.classList.toggle('active')
        img.style.left = '0px'
        img.style.top = '0px'
    }
    else if(count==1){
        img.style.transform = `rotate(90deg)`
        bt.value = 'Поднять'
        img.style.left = '-350px'
        img.style.top = '-140px'
        count*=-1
        txt.classList.toggle('active')
    }
})
