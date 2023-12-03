function flashing() {
    let currentNumber = document.getElementById('queueNumber2');
    let currentDesk = document.getElementById('2');
    currentNumber.classList.add('flash');
    currentDesk.classList.add('flash')
    setTimeout(function () {
        currentNumber.classList.remove('flash')
        currentDesk.classList.remove('flash')
    }, 4000);
}

function rescale() {
    let currentRow = document.getElementById('queueNumber2');
    currentRow.style.transform = 'scale(2)';
    setTimeout(function () {
        currentRow.style.transform = 'scale(1)';
    }, 3000);
}