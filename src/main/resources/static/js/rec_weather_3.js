document.addEventListener("DOMContentLoaded", function() {
    const weatherItems = document.querySelectorAll('.weather-item');
    const container = document.querySelector('.weather-selection');
    const containerWidth = container.clientWidth;
    const containerHeight = container.clientHeight;
    const itemWidth = 150; // 버튼의 너비
    const itemHeight = 50; // 버튼의 높이
    const padding = 100; // 버튼 간의 여백
    const maxAttempts = 100; // 위치 중복을 피하기 위한 시도 횟수

    // 버튼의 총 개수
    const numItems = weatherItems.length;

    // 버튼의 가능한 최대 위치 개수
    const maxX = Math.floor(containerWidth / (itemWidth + padding));
    const maxY = Math.floor(containerHeight / (itemHeight + padding));

    function getRandomPosition() {
        return {
            x: Math.random() * (containerWidth - itemWidth),
            y: Math.random() * (containerHeight - itemHeight),
        };
    }

    function isPositionValid(x, y, occupiedPositions) {
        for (const pos of occupiedPositions) {
            const dx = Math.abs(pos.x - x);
            const dy = Math.abs(pos.y - y);
            if (dx < itemWidth + padding && dy < itemHeight + padding) {
                return false;
            }
        }
        return true;
    }

    function generatePositions() {
        const occupiedPositions = [];
        const positions = [];

        for (let i = 0; i < numItems; i++) {
            let position;
            let attempts = 0;

            do {
                position = getRandomPosition();
                attempts++;
            } while (!isPositionValid(position.x, position.y, occupiedPositions) && attempts < maxAttempts);

            if (attempts < maxAttempts) {
                occupiedPositions.push(position);
                positions.push(position);
            }
        }
        return positions;
    }

    const positions = generatePositions();

    weatherItems.forEach((item, index) => {
        const pos = positions[index];

        item.style.position = 'absolute'; // 절대 위치 설정
        item.style.left = `${pos.x}px`;
        item.style.top = `${pos.y}px`;

        // 약간의 랜덤 오프셋 추가
        item.style.transform = `translate(${Math.random() * 10 - 5}px, ${Math.random() * 10 - 5}px)`;

        item.addEventListener('click', function() {
            const selectedWeather = this.getAttribute('data-weather');
            window.location.href = `/rec_weather_3_1?weather=${selectedWeather}`;
        });
    });
    // nav-item 클릭 이벤트 처리
    const navItems = document.querySelectorAll('.nav-item');
    navItems.forEach((item, index) => {
        item.addEventListener('click', function() {
            if (index === 0) {
                window.location.href = '/rec_intro'; // 홈 버튼 클릭 시 /rec_intro로 이동
            } else if (index === 1) {
                window.location.href = '/search'; // 두 번째 nav-item 클릭 시 이동할 페이지
            }
        });
    });
});
