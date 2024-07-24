document.addEventListener("DOMContentLoaded", function() {
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
                    } else {
                        console.error('Weather icon data is missing');
                    }
                })
                .catch(error => console.error('Error fetching weather data:', error));
        }, function(error) {
            console.error('Error getting location:', error);
        });
    } else {
        console.error('Geolocation is not supported by this browser.');
    }
});
