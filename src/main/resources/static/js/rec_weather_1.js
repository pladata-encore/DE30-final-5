document.addEventListener("DOMContentLoaded", function() {
    // 기존 코드
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;

            fetch(`/weather?lat=${lat}&lon=${lon}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Weather data:', data); // 응답 데이터 확인
                    if (data && data.weather && data.weather.length > 0) {
                        const iconCode = data.weather[0].icon;
                        const weatherIconUrl = `http://openweathermap.org/img/wn/${iconCode}.png`;
                        document.getElementById('weather-icon').src = weatherIconUrl;
                        document.getElementById('location').textContent = data.name; // 도시 이름
                        document.getElementById('temperature').textContent = `${data.main.temp}°C`; // 온도
                    } else {
                        console.error('Weather data is missing');
                    }
                })
                .catch(error => console.error('Error fetching weather data:', error));
        }, function(error) {
            console.error('Error getting location:', error);
        });
    } else {
        console.error('Geolocation is not supported by this browser.');
    }

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

    // 클릭 이벤트 처리 추가
    const optionHeaders = document.querySelectorAll('.option h2');
    optionHeaders.forEach(header => {
        header.addEventListener('click', function() {
            if (this.textContent.includes('직접 날씨 선택')) {
                window.location.href = '/rec_weather_3';
            } else {
                window.location.href = '/rec_weather_2';
            }
        });
    });
});
