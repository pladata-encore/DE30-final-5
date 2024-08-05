document.addEventListener("DOMContentLoaded", function() {
    var header = document.querySelector('header');
    var section = document.querySelector('section');
    var searchContainer = document.querySelector('.search-container');
    var headerText = document.getElementById('header-text');

    // 타자치는 애니메이션을 위한 텍스트 감싸기
    function wrapTextInSpans(text) {
        // '당'의 인덱스를 찾습니다.
        const startAnimationIndex = text.indexOf('당');

        return text.split('').map((char, index) => {
            // '당'부터 애니메이션을 시작하도록 지연 시간 조정
            const delay = index >= startAnimationIndex ? (index - startAnimationIndex) * 0.1 : 0;
            if (char === ' ') {
                // 띄어쓰기를 감싸는 span을 추가
                return `<span class="space"></span>`;
            }
            return `<span style="animation-delay: ${Math.max(delay, 0)}s;">${char}</span>`;
        }).join('');
    }

    // 헤더 텍스트에 타자치는 애니메이션 적용
    headerText.innerHTML = wrapTextInSpans(headerText.textContent);

    // 헤더 애니메이션
    setTimeout(function() {
        header.classList.add('header-animate');

        // 헤더 애니메이션 후 나머지 애니메이션
        setTimeout(function() {
            section.classList.add('content-animate');
            searchContainer.classList.add('content-animate');
        }, 800); // 헤더 애니메이션 시간이 끝난 후 실행
    }, 300); // 페이지 로드 후 0.35초 지연
});

