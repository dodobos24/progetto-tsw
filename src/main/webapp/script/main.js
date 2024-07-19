document.getElementById('cerca').addEventListener('submit', function(event) {
    var input1 = document.getElementById('dove').value;
    var input2 = document.getElementById('quando').value;
    var input3 = document.getElementById('categoria').value;
    var input4 = document.getElementById('artista').value;
    console.log(input1)
    console.log(input2)
    console.log(input3)
    console.log(input4)

    if (!input1 && input2=="" && !input3 && !input4) {
        event.preventDefault();
        alert('Almeno un campo deve essere compilato.');
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const slides = document.getElementById('slides');
    const slide = document.querySelectorAll('.slide');
    let currentIndex = 0;

    setInterval(() => {
        currentIndex = (currentIndex + 1) % slide.length;
        slides.style.transform = `translateX(-${currentIndex * 100}%)`;
    }, 3000);
});
