document.addEventListener("DOMContentLoaded", function() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;

            // Lat과 Lon 파라미터를 포함하여 서버에 요청
            fetch(`/weather?lat=${lat}&lon=${lon}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('Weather data:', data); // 디버깅을 위한 로그

                    // 아이콘 URL 생성
                    const weatherIconId = data.weather[0].icon;
                    const weatherIconUrl = `http://openweathermap.org/img/wn/${weatherIconId}.png`;

                    // 아이콘 이미지 업데이트
                    const weatherIconElement = document.getElementById('weather-icon');
                    weatherIconElement.src = weatherIconUrl;

                    // 날씨 설명 업데이트 (옵션)
                    const weatherDescriptionElement = document.getElementById('weather-description');
                    weatherDescriptionElement.textContent = data.weather[0].description;
                })
                .catch(error => console.error('Error fetching weather data:', error));
        }, function(error) {
            console.error('Error getting location:', error);
        });
    } else {
        console.error('Geolocation is not supported by this browser.');
    }
});
