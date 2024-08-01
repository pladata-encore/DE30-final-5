document.addEventListener('DOMContentLoaded', () => {
    function setupSlideshow(slidesContainer, direction) {
        const slidesList = slidesContainer.querySelector('ul');
        const slides = Array.from(slidesList.querySelectorAll('.slide'));
        const totalSlides = slides.length;

        if (totalSlides === 0) return;

        // 첫 번째 슬라이드와 마지막 슬라이드를 복사하여 무한 루프 효과 생성
        const firstSlideClone = slides[0].cloneNode(true);
        const lastSlideClone = slides[totalSlides - 1].cloneNode(true);
        slidesList.appendChild(firstSlideClone);
        slidesList.insertBefore(lastSlideClone, slides[0]);

        const allSlides = slidesList.querySelectorAll('.slide');
        const slideHeight = slides[0].clientHeight;

        // 슬라이드 컨테이너 높이 설정
        slidesContainer.style.height = `${slideHeight}px`;

        // 슬라이드 리스트 초기 위치 설정
        let currentIndex = 1;
        slidesList.style.transform = `translateY(-${currentIndex * slideHeight}px)`;

        // 슬라이드가 넘어가는 중인지 여부를 나타내는 플래그
        let isTransitioning = false;

        // 슬라이드를 다음으로 넘기는 함수
        function moveToNextSlide() {
            if (!isTransitioning) {
                isTransitioning = true;

                if (direction === 'down') {
                    currentIndex++;
                } else if (direction === 'up') {
                    currentIndex--;
                }

                slidesList.style.transition = 'transform 0.5s ease';
                slidesList.style.transform = `translateY(-${currentIndex * slideHeight}px)`;

                // 슬라이드 전환이 끝나고 무한 루프 효과를 위해 인덱스 조정
                setTimeout(() => {
                    if (currentIndex === totalSlides + 1) {
                        slidesList.style.transition = 'none';
                        currentIndex = 1;
                        slidesList.style.transform = `translateY(-${currentIndex * slideHeight}px)`;
                    } else if (currentIndex === 0) {
                        slidesList.style.transition = 'none';
                        currentIndex = totalSlides;
                        slidesList.style.transform = `translateY(-${currentIndex * slideHeight}px)`;
                    }

                    setTimeout(() => {
                        isTransitioning = false;
                    }, 50); // transition이 끝나기 전에 짧은 지연시간 설정
                }, 500); // transition 시간과 동일하게 설정
            }
        }

        // 자동 슬라이드 시작
        setInterval(moveToNextSlide, 3000);
    }

    // 첫 번째 슬라이드 컨테이너 설정 (아래로 슬라이드)
    const slidesContainer1 = document.querySelector('#slides1 .slides-container');
    setupSlideshow(slidesContainer1, 'down');

    // 두 번째 슬라이드 컨테이너 설정 (위로 슬라이드)
    const slidesContainer2 = document.querySelector('#slides2 .slides-container');
    setupSlideshow(slidesContainer2, 'up');
});